package backend;

import static backend.instructions.Instr.addToCurLabel;
import static backend.instructions.Instr.BYTE_SIZE;

import backend.instructions.*;
import frontend.abstractsyntaxtree.Node;
import frontend.abstractsyntaxtree.functions.ArgListAST;
import frontend.abstractsyntaxtree.expressions.IdentExprAST;
import frontend.abstractsyntaxtree.pairs.PairElemAST;
import frontend.symboltable.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Utils {

  // Size constant for stack growth
  private static final int TEN_BITS = 1024;

  // String constants for messages
  private static final String INT_MSG = "%d\\0";
  private static final String TRUE_MSG = "true\\0";
  private static final String FALSE_MSG = "false\\0";
  private static final String CHAR_MSG
      = " %c\\0";
  private static final String PTR_MSG
      = "%p\\0";
  private static final String STRING_MSG = "%.*s\\0";
  private static final String LN_MSG = "\\0";
  private static final String OVERFLOW_MSG
      = "OverflowError: the result is too small/large to store in a 4-byte signed-integer.\\n";
  private static final String DIV_BY_ZERO_MSG
      = "DivideByZeroError: divide or modulo by zero\\n\\0";
  private static final String NEG_INDEX_MSG
      = "ArrayIndexOutOfBoundsError: negative index\\n\\0";
  private static final String BIG_INDEX_MSG
      = "ArrayIndexOutOfBoundsError: index too large\\n\\0";
  private static final String NULL_MSG
      = "NullReferenceError: dereference a null reference\\n\\0";
  private static final String INCOMPATIBLE_DYN_VAR_MSG
      = "DynamicTypeError: dynamic variable has incompatible type\\n\\0";

  // Pair typing indexes
  private static final int FST_TYPE_NUM = 8;
  private static final int SND_TYPE_NUM = 9;

  // Generates start routine instructions
  public static List<Instr> getStartRoutine(SymbolTable symtab,
      boolean backEndGenerator) {
    List<Instr> instrs = new ArrayList<>();

    // push LR
    if (backEndGenerator) {
      instrs.add(new PUSH(Instr.LR));
    }

    // stack growth (limited to 10 bits at a time)
    int size = symtab.getSize();
    if (size > 0) {
      // If greater than 10 bits
      while (size > TEN_BITS) {
        size = size - TEN_BITS;
        instrs.add(new SUB(false, false, Instr.SP, Instr.SP,
            AddrMode.buildImm(TEN_BITS)));
      }
      instrs.add(
          new SUB(false, false, Instr.SP, Instr.SP, AddrMode.buildImm(size)));
    }

    return instrs;
  }

  // Returns the end routine instructions for every scope
  public static List<Instr> getEndRoutine(SymbolTable symtab,
      boolean backEndGenerator) {
    List<Instr> instrs = new ArrayList<>();

    int stackSize = 0;
    // If returning from function, need to destroy parent stacks
    if (symtab.isTopLevel()) {
      stackSize = symtab.getSize();
    } else {
      if (symtab.getParent().getClassContext()) {
        stackSize = symtab.getSize();
      } else {
        if (symtab.getFuncContext()) {
          SymbolTable temp = symtab;
          while (!temp.isTopLevel()) {
            stackSize += temp.getSize();
            temp = temp.getParent();
          }
        } else {
          stackSize = symtab.getSize();
        }
      }
    }

    // Destroy stack (limited to 10 bits at a time)
    if (stackSize > 0) {
      // If greater than 10 bits
      while (stackSize > TEN_BITS) {
        stackSize = stackSize - TEN_BITS;
        instrs.add(
            new ADD(false, Instr.SP, Instr.SP, AddrMode.buildImm(TEN_BITS)));
      }
      instrs.add(
          new ADD(false, Instr.SP, Instr.SP, AddrMode.buildImm(stackSize)));
    }

    // End routine (LDR r0 =0; POP {pc}; .ltorg;)
    if (backEndGenerator) {
      instrs.add(new LDR(Instr.R0, AddrMode.buildVal(0)));
      instrs.add(new POP(Instr.PC));
      instrs.add(new LTORG());
    }

    return instrs;
  }

  /**
   * Adds functions to the list of pre-defined functions that need to be in the
   * assembly file depending on the type of identifier we are dealing with, e.g.
   * integers need to print in case of an overflow.
   */
  public static BRANCH getPrintBranch(TypeID type) {
    BRANCH brInstr = null;
    if (type instanceof IntID) {
      BackEndGenerator.addToPreDefFuncs(Label.P_PRINT_INT);
      brInstr = new BRANCH(true, Condition.NO_CON, Label.P_PRINT_INT);
    } else if (type instanceof BoolID) {
      BackEndGenerator.addToPreDefFuncs(Label.P_PRINT_BOOL);
      brInstr = new BRANCH(true, Condition.NO_CON, Label.P_PRINT_BOOL);
    } else if (type instanceof CharID) {
      brInstr = new BRANCH(true, Condition.NO_CON, Label.PUTCHAR);
    } else if (type instanceof StringID) {
      BackEndGenerator.addToPreDefFuncs(Label.P_PRINT_STRING);
      brInstr = new BRANCH(true, Condition.NO_CON, Label.P_PRINT_STRING);
    } else if (type instanceof OptionalPairID) {
      BackEndGenerator.addToPreDefFuncs(Label.P_PRINT_REFERENCE);
      brInstr = new BRANCH(true, Condition.NO_CON, Label.P_PRINT_REFERENCE);
    } else if (type instanceof ArrayID) {
      if (((ArrayID) type).getElemType() instanceof CharID) {
        BackEndGenerator.addToPreDefFuncs(Label.P_PRINT_STRING);
        brInstr = new BRANCH(true, Condition.NO_CON, Label.P_PRINT_STRING);
      } else {
        BackEndGenerator.addToPreDefFuncs(Label.P_PRINT_REFERENCE);
        brInstr = new BRANCH(true, Condition.NO_CON, Label.P_PRINT_REFERENCE);
      }
    }
    // brInstr should not be null
    return brInstr;
  }

  // Function which builds the instructions when a call to a class's function
  // is made
  public static void buildClassFunctionInstr(int varNameOffset,
      SymbolTable symtab, ArgListAST args, String className, String funcName){
    List<Instr> instructions = new ArrayList<>();
    int accOffset = 0;
    String transferReg = Instr.getTargetReg();

    // Allocate in reverse so that first argument directly on top of LR
    for (int i = args.getArguments().size() - 1; i >= 0; i--) {
      Node argNode = args.getArguments().get(i);

      // Puts the next argument into the transfer register
      argNode.toAssembly();

      // Record total offset to destroy stack after
      int offset = argNode.getIdentifier().getType().getBytes();
      accOffset += offset;

      // Temporary offset so that arguments are accessed correctly
      symtab.incrementFuncOffset(offset);

      // Add to stack
      Instr.addToCurLabel(new STR(offset,
          Condition.NO_CON, transferReg,
          AddrMode.buildAddrWithWriteBack(Instr.SP, -offset)));
    }
    addToCurLabel(new ADD(false, transferReg, Instr.SP,
        AddrMode.buildImm(varNameOffset + accOffset)));
    addToCurLabel(new STR(transferReg, AddrMode.buildAddrWithWriteBack(Instr.SP, -(4))));

    // Function call
    instructions
        .add(new BRANCH(true, Condition.NO_CON, Label.CLASS_FUNC_HEADER + className + "_" + funcName));

    // Destroy stack - STACK ALWAYS HAS TO BE DESTROYED AS IT WILL ALWAYS
    // CONTAIN THE INSTANCE'S ADDRESS
    instructions.add(
        new ADD(false, Instr.SP, Instr.SP, AddrMode.buildImm(accOffset + 4)));

    // Reset temporary offset
    symtab.resetFuncOffset();
    // Move result
    instructions.add(
        new MOV(Condition.NO_CON, transferReg, AddrMode.buildReg(Instr.R0)));

    addToCurLabel(instructions);
  }


  //****** METHODS FOR PRE DEFINED FUNCTIONS ******

  /**
   * Returns a map of every pre-defined function and their instructions.
   */
  public static Map<String, List<Instr>> getPreDefFunc(List<String> pdfs) {
    // Keep track of unique preDefFunc that has already been added
    Set<String> pdfTracker = new HashSet<>();
    Map<String, List<Instr>> preDefFuncInstrs = new HashMap<>();

    for (int i = 0; i < pdfs.size(); i++) {
      String f = pdfs.get(i);
      // Prevent duplicates
      if (!pdfTracker.contains(f)) {
        // Get method by name through reflection instead of a huge if-else / switch case
        try {
          Method method = Utils.class.getDeclaredMethod(f, Map.class);
          method.invoke(null, preDefFuncInstrs);
          pdfTracker.add(f);
        } catch (NoSuchMethodException e) {
          // Helps with debugging (for preDefFuncs which have not been defined yet)
          System.err.println("No such predefined method! " + f);
        } catch (IllegalAccessException | InvocationTargetException e) {
          e.printStackTrace();
        }
      }
    }

    return preDefFuncInstrs;
  }

  private static List<Instr> printf() {
    List<Instr> instrs = new ArrayList<>();
    instrs.add(new ADD(false, Instr.R0, Instr.R0, AddrMode.buildImm(4)));
    instrs.add(new BRANCH(true, Condition.NO_CON, Label.PRINTF));
    instrs.add(new MOV(Condition.NO_CON, Instr.R0, AddrMode.buildImm(0)));
    instrs.add(new BRANCH(true, Condition.NO_CON, Label.FFLUSH));
    return instrs;
  }

  private static void p_print_string(Map<String, List<Instr>> pdf) {
    List<Instr> instrs = new ArrayList<>();
    instrs.add(new PUSH(Instr.LR));
    instrs.add(new LDR(Instr.R1, AddrMode.buildAddr(Instr.R0)));
    instrs.add(new ADD(false, Instr.R2, Instr.R0, AddrMode.buildImm(4)));
    instrs.add(new LDR(Instr.R0, AddrMode
        .buildStringVal(BackEndGenerator.addToDataSegment(STRING_MSG))));
    instrs.addAll(printf());
    instrs.add(new POP(Instr.PC));

    pdf.put(Label.P_PRINT_STRING, instrs);
  }

  private static void p_throw_overflow_error(Map<String, List<Instr>> pdf) {
    List<Instr> instrs = new ArrayList<>();
    instrs.add(new LDR(Instr.R0, AddrMode
        .buildStringVal(BackEndGenerator.addToDataSegment(OVERFLOW_MSG))));
    BackEndGenerator.addToPreDefFuncs(Label.P_THROW_RUNTIME_ERROR);
    instrs.add(new BRANCH(true, Condition.NO_CON, Label.P_THROW_RUNTIME_ERROR));

    pdf.put(Label.P_THROW_OVERFLOW_ERROR, instrs);
  }

  private static void p_throw_runtime_error(Map<String, List<Instr>> pdf) {
    List<Instr> instrs = new ArrayList<>();
    BackEndGenerator.addToPreDefFuncs(Label.P_PRINT_STRING);
    instrs.add(new BRANCH(true, Condition.NO_CON, Label.P_PRINT_STRING));
    instrs.add(new MOV(Condition.NO_CON, Instr.R0, AddrMode.buildImm(-1)));
    instrs.add(new BRANCH(true, Condition.NO_CON, Label.EXIT));

    pdf.put(Label.P_THROW_RUNTIME_ERROR, instrs);
  }

  private static void p_print_int(Map<String, List<Instr>> pdf) {
    List<Instr> instrs = new ArrayList<>();
    instrs.add(new PUSH(Instr.LR));
    instrs
        .add(new MOV(Condition.NO_CON, Instr.R1, AddrMode.buildReg(Instr.R0)));
    instrs.add(new LDR(Instr.R0, AddrMode
        .buildStringVal(BackEndGenerator.addToDataSegment(INT_MSG))));
    instrs.addAll(printf());
    instrs.add(new POP(Instr.PC));

    pdf.put(Label.P_PRINT_INT, instrs);
  }

  private static void p_print_ln(Map<String, List<Instr>> pdf) {
    List<Instr> instrs = new ArrayList<>();
    instrs.add(new PUSH(Instr.LR));
    instrs.add(new LDR(Instr.R0,
        AddrMode.buildStringVal(BackEndGenerator.addToDataSegment(LN_MSG))));
    instrs.add(new ADD(false, Instr.R0, Instr.R0, AddrMode.buildImm(4)));
    instrs.add(new BRANCH(true, Condition.NO_CON, Label.PUTS));
    instrs.add(new MOV(Condition.NO_CON, Instr.R0, AddrMode.buildImm(0)));
    instrs.add(new BRANCH(true, Condition.NO_CON, Label.FFLUSH));
    instrs.add(new POP(Instr.PC));

    pdf.put(Label.P_PRINT_LN, instrs);
  }

  private static void p_print_bool(Map<String, List<Instr>> pdf) {
    List<Instr> instrs = new ArrayList<>();
    instrs.add(new PUSH(Instr.LR));
    instrs.add(new CMP(Instr.R0, AddrMode.buildImm(0)));
    instrs.add(new LDR(4, Condition.NE, Instr.R0, AddrMode
        .buildStringVal(BackEndGenerator.addToDataSegment(TRUE_MSG))));
    instrs.add(new LDR(4, Condition.EQ, Instr.R0, AddrMode
        .buildStringVal(BackEndGenerator.addToDataSegment(FALSE_MSG))));
    instrs.addAll(printf());
    instrs.add(new POP(Instr.PC));

    pdf.put(Label.P_PRINT_BOOL, instrs);
  }

  private static void p_check_divide_by_zero(Map<String, List<Instr>> pdf) {
    List<Instr> instrs = new ArrayList<>();
    instrs.add(new PUSH(Instr.LR));
    instrs.add(new CMP(Instr.R1, AddrMode.buildImm(0)));
    instrs.add(new LDR(4, Condition.EQ, Instr.R0, AddrMode.buildStringVal(
        BackEndGenerator.addToDataSegment(DIV_BY_ZERO_MSG))));
    BackEndGenerator.addToPreDefFuncs(Label.P_THROW_RUNTIME_ERROR);
    instrs.add(new BRANCH(true, Condition.EQ, Label.P_THROW_RUNTIME_ERROR));
    instrs.add(new POP(Instr.PC));

    pdf.put(Label.P_CHECK_DIVIDE_BY_ZERO, instrs);
  }

  private static void p_check_array_bounds(Map<String, List<Instr>> pdf) {
    List<Instr> instrs = new ArrayList<>();
    instrs.add(new PUSH(Instr.LR));
    instrs.add(new CMP(Instr.R0, AddrMode.buildImm(0)));
    instrs.add(new LDR(4, Condition.LT, Instr.R0, AddrMode
        .buildStringVal(BackEndGenerator.addToDataSegment(NEG_INDEX_MSG))));
    BackEndGenerator.addToPreDefFuncs(Label.P_THROW_RUNTIME_ERROR);
    instrs.add(new BRANCH(true, Condition.LT, Label.P_THROW_RUNTIME_ERROR));
    instrs.add(new LDR(Instr.R1, AddrMode.buildAddr(Instr.R1)));
    instrs.add(new CMP(Instr.R0, AddrMode.buildReg(Instr.R1)));
    instrs.add(new LDR(4, Condition.CS, Instr.R0, AddrMode
        .buildStringVal(BackEndGenerator.addToDataSegment(BIG_INDEX_MSG))));
    instrs.add(new BRANCH(true, Condition.CS, Label.P_THROW_RUNTIME_ERROR));
    instrs.add(new POP(Instr.PC));

    pdf.put(Label.P_CHECK_ARRAY_BOUNDS, instrs);
  }

  private static void p_check_null_pointer(Map<String, List<Instr>> pdf) {
    List<Instr> instrs = new ArrayList<>();
    instrs.add(new PUSH(Instr.LR));
    instrs.add(new CMP(Instr.R0, AddrMode.buildImm(0)));
    instrs.add(new LDR(4, Condition.EQ, Instr.R0, AddrMode
        .buildStringVal(BackEndGenerator.addToDataSegment(NULL_MSG))));
    BackEndGenerator.addToPreDefFuncs(Label.P_THROW_RUNTIME_ERROR);
    instrs.add(new BRANCH(true, Condition.EQ, Label.P_THROW_RUNTIME_ERROR));
    instrs.add(new POP(Instr.PC));

    pdf.put(Label.P_CHECK_NULL_POINTER, instrs);
  }

  private static void p_read_int(Map<String, List<Instr>> pdf) {
    List<Instr> instrs = new ArrayList<>();
    instrs.add(new PUSH(Instr.LR));
    instrs
        .add(new MOV(Condition.NO_CON, Instr.R1, AddrMode.buildReg(Instr.R0)));
    instrs.add(new LDR(Instr.R0, AddrMode
        .buildStringVal(BackEndGenerator.addToDataSegment(INT_MSG))));
    instrs.add(new ADD(false, Instr.R0, Instr.R0, AddrMode.buildImm(4)));
    instrs.add(new BRANCH(true, Condition.NO_CON, Label.SCANF));
    instrs.add(new POP(Instr.PC));

    pdf.put(Label.P_READ_INT, instrs);
  }

  private static void p_free_pair(Map<String, List<Instr>> pdf) {
    List<Instr> instrs = new ArrayList<>();
    instrs.add(new PUSH(Instr.LR));
    instrs.add(new CMP(Instr.R0, AddrMode.buildImm(0)));
    instrs.add(new LDR(4, Condition.EQ, Instr.R0, AddrMode
        .buildStringVal(BackEndGenerator.addToDataSegment(NULL_MSG))));
    BackEndGenerator.addToPreDefFuncs(Label.P_THROW_RUNTIME_ERROR);
    instrs.add(new BRANCH(true, Condition.EQ, Label.P_THROW_RUNTIME_ERROR));
    instrs.add(new PUSH(Instr.R0));
    instrs.add(new LDR(Instr.R0, AddrMode.buildAddr(Instr.R0)));
    instrs.add(new BRANCH(true, Condition.NO_CON, Label.FREE));
    instrs.add(new LDR(Instr.R0, AddrMode.buildAddr(Instr.SP)));
    instrs.add(new LDR(Instr.R0, AddrMode.buildAddrWithOffset(Instr.R0, 4)));
    instrs.add(new BRANCH(true, Condition.NO_CON, Label.FREE));
    instrs.add(new POP(Instr.R0));
    instrs.add(new BRANCH(true, Condition.NO_CON, Label.FREE));
    instrs.add(new POP(Instr.PC));

    pdf.put(Label.P_FREE_PAIR, instrs);
  }

  private static void p_read_char(Map<String, List<Instr>> pdf) {
    List<Instr> instrs = new ArrayList<>();
    instrs.add(new PUSH(Instr.LR));
    instrs
        .add(new MOV(Condition.NO_CON, Instr.R1, AddrMode.buildReg(Instr.R0)));
    instrs.add(new LDR(Instr.R0, AddrMode
        .buildStringVal(BackEndGenerator.addToDataSegment(CHAR_MSG))));
    instrs.add(new ADD(false, Instr.R0, Instr.R0, AddrMode.buildImm(4)));
    instrs.add(new BRANCH(true, Condition.NO_CON, Label.SCANF));
    instrs.add(new POP(Instr.PC));

    pdf.put(Label.P_READ_CHAR, instrs);
  }

  private static void p_print_reference(Map<String, List<Instr>> pdf) {
    List<Instr> instrs = new ArrayList<>();
    instrs.add(new PUSH(Instr.LR));
    instrs
        .add(new MOV(Condition.NO_CON, Instr.R1, AddrMode.buildReg(Instr.R0)));
    instrs.add(new LDR(Instr.R0, AddrMode
        .buildStringVal(BackEndGenerator.addToDataSegment(PTR_MSG))));
    instrs.addAll(printf());
    instrs.add(new POP(Instr.PC));

    pdf.put(Label.P_PRINT_REFERENCE, instrs);
  }

  private static void p_dynamic_type_check(Map<String, List<Instr>> pdf) {
    List<Instr> instrs = new ArrayList<>();
    instrs.add(new PUSH(Instr.LR));

    instrs.add(new AND(Instr.R0, AddrMode.buildReg(Instr.R1)));
    instrs.add(new CMP(Instr.R0, AddrMode.buildImm(0)));
    instrs.add(new LDR(4, Condition.EQ, Instr.R0, AddrMode
        .buildStringVal(
            BackEndGenerator.addToDataSegment(INCOMPATIBLE_DYN_VAR_MSG))));
    BackEndGenerator.addToPreDefFuncs(Label.P_THROW_RUNTIME_ERROR);
    instrs.add(new BRANCH(true, Condition.EQ, Label.P_THROW_RUNTIME_ERROR));

    instrs.add(new POP(Instr.PC));

    pdf.put(Label.P_DYNAMIC_TYPE_CHECK, instrs);
  }

  public static final int ALL_TYPES_SUPPORTED = 127;

  public static void dynamicTypeCheckIfNeeded(Node node, int typeNumber) {
    if (node.getIdentifier() instanceof VarID
        && typeNumber < ALL_TYPES_SUPPORTED) {
      List<Instr> instrs = new ArrayList<>();

      if (node instanceof IdentExprAST) {
        int stackOffset = ((IdentExprAST) node).getOffset();

        // Get addr into R0
        instrs.add(
            new ADD(false, Instr.R0, Instr.SP, AddrMode.buildImm(stackOffset)));
        // Load typeNumber (byte) from "box" into R0
        instrs.add(new LDR(-Instr.BYTE_SIZE, Condition.NO_CON, Instr.R0,
            AddrMode.buildAddrWithOffset(Instr.R0, Instr.WORD_SIZE)));
        // Load actual typeNumber needed
        instrs.add(new MOV(Condition.NO_CON, Instr.R1,
            AddrMode.buildImm(typeNumber)));
        // Jump to dynamic type check
        BackEndGenerator.addToPreDefFuncs(Label.P_DYNAMIC_TYPE_CHECK);
        instrs.add(
            new BRANCH(true, Condition.NO_CON, Label.P_DYNAMIC_TYPE_CHECK));
        Instr.addToCurLabel(instrs);
      }
    }
  }

  public static void dynamicPairElemCheck(Node node, int typeNumber) {
    if (node.getIdentifier() instanceof VarID && node instanceof PairElemAST) {
      List<Instr> instrs = new ArrayList<>();

      BackEndGenerator.addToPreDefFuncs(Label.P_DYNAMIC_TYPE_CHECK);

      // Get base addr of var into R5
      instrs.add(new ADD(false, Instr.R5, Instr.SP,
          AddrMode.buildImm(((PairElemAST) node).getOffset())));
      instrs.add(new LDR(Instr.R5, AddrMode.buildAddr(Instr.R5)));

      // Load typeNumber of var
      instrs.add(new LDR(-BYTE_SIZE, Condition.NO_CON, Instr.R0,
          AddrMode.buildAddrWithOffset(Instr.R5,
              ((PairElemAST) node).isFirst() ? FST_TYPE_NUM : SND_TYPE_NUM)));
      // Load actual typeNumber needed
      instrs.add(
          new MOV(Condition.NO_CON, Instr.R1, AddrMode.buildImm(typeNumber)));
      // Jump to dynamic type check
      instrs
          .add(new BRANCH(true, Condition.NO_CON, Label.P_DYNAMIC_TYPE_CHECK));

      Instr.addToCurLabel(instrs);
    }
  }

  private static final int PAIR_NUMBER = 32;

  public static void dynamicPairCheck(Node node, int fstType, int sndType) {
    if (node.getIdentifier() instanceof VarID) {
      List<Instr> instrs = new ArrayList<>();

      if (node instanceof IdentExprAST || node instanceof PairElemAST) {
        int stackOffset;
        if (node instanceof IdentExprAST) {
          stackOffset = ((IdentExprAST) node).getOffset();
        } else {
          stackOffset = ((PairElemAST) node).getOffset();
        }

        BackEndGenerator.addToPreDefFuncs(Label.P_DYNAMIC_TYPE_CHECK);

        // Get addr into R0
        instrs.add(
            new ADD(false, Instr.R4, Instr.SP, AddrMode.buildImm(stackOffset)));
        // Load typeNumber (byte) from "box" into R0
        instrs.add(new LDR(-Instr.BYTE_SIZE, Condition.NO_CON, Instr.R0,
            AddrMode.buildAddrWithOffset(Instr.R4, Instr.WORD_SIZE)));
        // Load actual typeNumber needed
        instrs.add(new MOV(Condition.NO_CON, Instr.R1,
            AddrMode.buildImm(PAIR_NUMBER)));
        // Jump to dynamic type check
        instrs.add(
            new BRANCH(true, Condition.NO_CON, Label.P_DYNAMIC_TYPE_CHECK));

        instrs.add(new LDR(Instr.R4, AddrMode.buildAddr(Instr.R4)));
        // Get fst typeNumber into r0
        instrs
            .add(new LDR(Instr.R0, AddrMode.buildAddrWithOffset(Instr.R4, FST_TYPE_NUM)));
        // Get actual fst Type into R0
        instrs.add(
            new MOV(Condition.NO_CON, Instr.R1, AddrMode.buildImm(fstType)));
        // Jump to dynamic check
        instrs.add(
            new BRANCH(true, Condition.NO_CON, Label.P_DYNAMIC_TYPE_CHECK));

        // Get snd typeNumber into r0
        instrs
            .add(new LDR(Instr.R0, AddrMode.buildAddrWithOffset(Instr.R4, SND_TYPE_NUM)));
        // Get actual snd Type into R0
        instrs.add(
            new MOV(Condition.NO_CON, Instr.R1, AddrMode.buildImm(sndType)));
        // Jump to dynamic check
        instrs.add(
            new BRANCH(true, Condition.NO_CON, Label.P_DYNAMIC_TYPE_CHECK));

        Instr.addToCurLabel(instrs);
      }
    }
  }

  private static final int ARRAY_NUMBER = 64;

  public static void dynamicArrayCheck(Node node, int typeNumber) {
    if (node.getIdentifier() instanceof VarID) {
      List<Instr> instrs = new ArrayList<>();

      if (node instanceof IdentExprAST) {
        BackEndGenerator.addToPreDefFuncs(Label.P_DYNAMIC_TYPE_CHECK);

        int stackOffset = ((IdentExprAST) node).getOffset();

        // Get addr into R0
        instrs.add(
            new ADD(false, Instr.R4, Instr.SP, AddrMode.buildImm(stackOffset)));
        // Load typeNumber (byte) from "box" into R0
        instrs.add(new LDR(-Instr.BYTE_SIZE, Condition.NO_CON, Instr.R0,
            AddrMode.buildAddrWithOffset(Instr.R4, Instr.WORD_SIZE)));
        // Load actual typeNumber needed
        instrs.add(new MOV(Condition.NO_CON, Instr.R1,
            AddrMode.buildImm(ARRAY_NUMBER)));
        // Jump to dynamic type check
        instrs.add(
            new BRANCH(true, Condition.NO_CON, Label.P_DYNAMIC_TYPE_CHECK));

        // Load address of array
        instrs.add(new LDR(Instr.R4, AddrMode.buildAddr(Instr.R4)));
        // Load child element number
        instrs.add(new LDR(-Instr.BYTE_SIZE, Condition.NO_CON, Instr.R0,
            AddrMode.buildAddrWithOffset(Instr.R4, Instr.WORD_SIZE)));
        // Load type number
        instrs.add(
            new MOV(Condition.NO_CON, Instr.R1, AddrMode.buildImm(typeNumber)));
        // Jump to dtynamic check
        instrs.add(
            new BRANCH(true, Condition.NO_CON, Label.P_DYNAMIC_TYPE_CHECK));

        Instr.addToCurLabel(instrs);
      }
    }
  }
}
