package frontend.abstractsyntaxtree.statements;

import antlr.WaccParser.AssignRHSContext;
import antlr.WaccParser.Var_decl_statContext;
import backend.instructions.*;
import frontend.abstractsyntaxtree.Node;
import frontend.abstractsyntaxtree.Utils;
import frontend.abstractsyntaxtree.assignments.AssignRHSAST;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.*;

import java.util.ArrayList;
import java.util.List;

public class VarDecAST extends Node {

  private static final int DYNAMIC_BOX_SIZE = 5;

  private final SymbolTable symtab;
  private TypeID decType;
  private final String varName;
  private final AssignRHSAST assignRHS;
  private final Var_decl_statContext ctx;
  private final AssignRHSContext rhsCtx;
  private boolean isDynamic;

  private TypeID rhsType;

  public VarDecAST(
      SymbolTable symtab,
      TypeID decType,
      String varName,
      AssignRHSAST assignRHS,
      Var_decl_statContext ctx) {
    super();
    this.symtab = symtab;
    this.decType = decType;
    this.varName = varName;
    this.ctx = ctx;
    this.rhsCtx = ctx.assignRHS();
    this.assignRHS = assignRHS;
    this.isDynamic = false;
  }

  @Override
  public void setIdentifier(Identifier identifier) {
    super.setIdentifier(identifier);
    this.isDynamic = identifier instanceof VarID;
  }

  @Override
  public void check() {
    symtab.incrementSize(
        decType instanceof VarID ? DYNAMIC_BOX_SIZE : decType.getBytes());

    // if function is not defined or class is not defined
    if (assignRHS.getIdentifier() == null) {
      return;
    }

    rhsType = assignRHS.getIdentifier().getType();

    int line = rhsCtx.getStart().getLine();
    int pos = rhsCtx.getStart().getCharPositionInLine();

    Identifier variable;
    if (symtab.isTopLevel()) {
      variable = symtab.lookup(varName);
    } else {
      if (symtab.getParent().getClassContext()) {
        variable = symtab.lookupAll(varName);
      } else {
        variable = symtab.lookup(varName);
      }
    }

    // ****** CANNOT DECLARE NESTED DYNAMIC VARIABLE PAIRS ******

    // VarDec of nested pairs
    if (decType instanceof PairID && rhsType instanceof PairID) {

      PairID pairRhsType = (PairID) rhsType;
      PairID pairDecType = (PairID) decType;
      TypeID pairDecFst = pairDecType.getFstType();
      TypeID pairDecSnd = pairDecType.getSndType();

      // Fst is a pair, set type based on RHS if not null
      if (pairDecFst instanceof PairID) {
        TypeID pairRhsTypeFst = pairRhsType.getFstType();
        if (!(pairRhsTypeFst instanceof NullID)) {
          pairDecType.setFst(pairRhsTypeFst);
        }
      }

      // Snd is a pair, set type based on RHS if not null
      if (pairDecSnd instanceof PairID) {
        TypeID pairRhsTypeSnd = pairRhsType.getSndType();
        if (!(pairRhsTypeSnd instanceof NullID)) {
          pairDecType.setSnd(pairRhsTypeSnd);
        }
      }
    }

    if (decType instanceof ArrayID && rhsType instanceof ArrayID) {
      if (((ArrayID) rhsType).getElemType() instanceof VarID) {
        SemanticErrorCollector.addIncompatibleWithDynamicVariables(line, pos);
      }
    }

    // VarDec with dynamic array elements

    // Check if var is already declared unless it is a function name
    if (variable != null && !(variable instanceof FuncID)) {
      SemanticErrorCollector.addSymbolAlreadyDefined(varName, line, pos);
    }

    // Check if types are compatible
    if (!Utils.typeCompat(decType, rhsType)) {
      SemanticErrorCollector.addIncompatibleType(
          decType.getTypeName(), rhsType.getTypeName(), rhsCtx.getText(), line,
          pos);
    }

    // No need type check if RHS is a dynamic variable
    symtab.add(varName, decType);
    setIdentifier(decType);
  }


  @Override
  public void toAssembly() {
    if (isDynamic) {
      // informs rhs that lhs is dynamic, hence needs no runtime type checking
      assignRHS.setLhsIsDynamic();
      if (rhsType instanceof VarID) {
        rhsType = ((VarID) rhsType).getTypeSoFar();
      }
      // Updates typeSoFar to call print correctly if needed
      ((VarID) decType).setTypeSoFar(rhsType);
    } else {
      // If lhs not dynamic but rhs is, we need to do runtime check
      if (rhsType instanceof VarID) {
        assignRHS.setDynamicTypeNumber(Utils.getTypeNumber(decType));
        if (decType instanceof PairID) {
          assignRHS
              .setFstType(Utils.getTypeNumber(((PairID) decType).getFstType()));
          assignRHS
              .setSndType(Utils.getTypeNumber(((PairID) decType).getSndType()));
        } else if (decType instanceof ArrayID) {
          assignRHS.setArrayType(
              Utils.getTypeNumber(((ArrayID) decType).getElemType()));
        }
      }
    }

    List<Instr> instrs = new ArrayList<>();
    // Generate the offset of the variable
    int offset = symtab.getSmallestOffset() -
        (decType instanceof VarID ? DYNAMIC_BOX_SIZE : decType.getBytes());

    // Add the offset to the symbol table's hashmap of variables' offsets
    symtab.addOffset(varName, offset);
    assignRHS.toAssembly();

    // Stores the value in the offset stack address
    STR strInstr = new STR(rhsType.getBytes(), Condition.NO_CON, Instr.R4,
        AddrMode.buildAddrWithOffset(Instr.SP, offset));
    instrs.add(strInstr);

    // If dynamic variable, set type number in "box" (5th byte)
    if (isDynamic) {
      // Get addr of variable
      instrs.add(new ADD(false, Instr.R4, Instr.SP, AddrMode.buildImm(offset)));
      // Load the type number
      instrs.add(new MOV(Condition.NO_CON, Instr.R5,
          AddrMode.buildImm(Utils.getTypeNumber(rhsType))));
      // Store (byte) into "box"
      instrs.add(new STR(Instr.BYTE_SIZE, Condition.NO_CON, Instr.R5,
          AddrMode.buildAddrWithOffset(Instr.R4, Instr.WORD_SIZE)));
    }

    Instr.addToCurLabel(instrs);
  }

  public TypeID getDecType() {
    return decType;
  }

  public AssignRHSAST getAssignRHS() {
    return assignRHS;
  }
}
