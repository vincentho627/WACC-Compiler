package frontend.abstractsyntaxtree.expressions;

import antlr.WaccParser.ExprContext;
import backend.BackEndGenerator;
import backend.instructions.ADD;
import backend.instructions.AND;
import backend.instructions.AddrMode;
import backend.instructions.BRANCH;
import backend.instructions.CMP;
import backend.instructions.Condition;
import backend.instructions.Instr;
import backend.instructions.LDR;
import backend.instructions.Label;
import backend.instructions.MOV;
import backend.instructions.ORR;
import frontend.abstractsyntaxtree.Node;
import frontend.abstractsyntaxtree.Utils;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.BoolID;
import frontend.symboltable.CharID;
import frontend.symboltable.IntID;
import frontend.symboltable.SymbolTable;
import frontend.symboltable.TypeID;
import frontend.symboltable.UnknownID;
import frontend.symboltable.VarID;
import java.util.ArrayList;
import java.util.List;

import static backend.Utils.ALL_TYPES_SUPPORTED;
import static backend.Utils.dynamicTypeCheckIfNeeded;
import static backend.instructions.Instr.FALSE_VAL;
import static backend.instructions.Instr.TRUE_VAL;
import static backend.instructions.Instr.addToCurLabel;

public class BinOpExprAST extends Node {

  private final String op;
  private final SymbolTable symtab;
  private final int expectedExprTypes;
  private final Node eL;
  private final Node eR;
  private final ExprContext ctx;
  private int typeNumber;

  public BinOpExprAST(SymbolTable symtab, int expectedExprTypes,
      String op, Node eL, Node eR, ExprContext ctx) {
    super(symtab.lookupAll("bool")); // BinOpExpr always has bool return type
    this.op = op;
    this.symtab = symtab;
    this.expectedExprTypes = expectedExprTypes;
    this.eL = eL;
    this.eR = eR;
    this.ctx = ctx;
    this.typeNumber = 0;
  }

  @Override
  public void check() {
    TypeID eLType = eL.getIdentifier().getType();
    TypeID eRType = eR.getIdentifier().getType();

    // Different binOps are compatible w different types

    if (expectedExprTypes == Utils.ALL_TYPES) { // Defined for all types
      if (!Utils.typeCompat(eLType, eRType)) {
        SemanticErrorCollector
            .addTypeMismatch(ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(), op);
      }
      typeNumber = ALL_TYPES_SUPPORTED;
    } else {
      // Check for left and right expr of binOp separated to pin point error

      boolean errorL = false;
      boolean errorR = false;
      String expectedTypes = "";

      List<TypeID> types = new ArrayList<>();
      if (expectedExprTypes == Utils.INT_CHAR) { // Defined for int and char
        errorL = !(eLType instanceof IntID || eLType instanceof CharID
            || eLType instanceof UnknownID || eLType instanceof VarID);
        if (!(eLType instanceof VarID)) {
          errorR = !(eRType == eLType || eRType instanceof UnknownID ||
              eRType instanceof VarID);
        }
        expectedTypes = "int or char";
        types.add((TypeID) symtab.lookupAll("int"));
        types.add((TypeID) symtab.lookupAll("char"));
      } else if (expectedExprTypes == Utils.BOOL) { // Defined for bool only
        errorL = !(eLType instanceof BoolID || eLType instanceof UnknownID
            || eLType instanceof VarID);
        errorR = !(eRType instanceof BoolID || eRType instanceof UnknownID
            || eRType instanceof VarID);
        expectedTypes = "bool";
        types.add((TypeID) symtab.lookupAll("bool"));
      }

      if (errorL) {
        SemanticErrorCollector
            .addIncompatibleType(expectedTypes, eLType.getTypeName(),
                ctx.getText(),
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine());
      }

      if (errorR) {
        SemanticErrorCollector
            .addIncompatibleType(expectedTypes, eRType.getTypeName(),
                ctx.getText(),
                ctx.getStop().getLine(),
                ctx.getStop().getCharPositionInLine());
      }

      typeNumber = Utils.getTypeNumber(types);
    }
  }

  @Override
  public void toAssembly() {
    // eL op eR
    // Evaluate eL first, then eR and finally op

    // Evaluate eL
    String fstReg = Instr.getTargetReg();
    eL.toAssembly();
    dynamicTypeCheckIfNeeded(eL, typeNumber);

    // Evaluate eR
    String sndReg = Instr.incDepth();
    eR.toAssembly();
    dynamicTypeCheckIfNeeded(eR, typeNumber);
    Instr.decDepth();

    List<Instr> instrs = new ArrayList<>();

    TypeID eLType = eL.getIdentifier().getType();
    TypeID eRType = eR.getIdentifier().getType();
    boolean eLIsVar = eLType instanceof VarID;
    boolean eRIsVar = eRType instanceof VarID;

    if (eLIsVar || eRIsVar) {
      if (eLIsVar) {
        assert (eL instanceof IdentExprAST);
        int offset = symtab.getStackOffset(((IdentExprAST) eL).getName());

        // Get addr into R0
        instrs.add(
            new ADD(false, Instr.R0, Instr.SP, AddrMode.buildImm(offset)));
        // Load typeNumber (byte) from "box" into R0
        instrs.add(new LDR(-Instr.BYTE_SIZE, Condition.NO_CON, Instr.R0,
            AddrMode.buildAddrWithOffset(Instr.R0, Instr.WORD_SIZE)));
      } else {
        instrs.add(new MOV(Condition.NO_CON, Instr.R0,
            AddrMode.buildImm(Utils.getTypeNumber(eLType))));
      }

      if (eRIsVar) {
        assert (eR instanceof IdentExprAST);
        int offset = symtab.getStackOffset(((IdentExprAST) eR).getName());

        // Get addr into R1
        instrs.add(
            new ADD(false, Instr.R1, Instr.SP, AddrMode.buildImm(offset)));
        // Load typeNumber (byte) from "box" into R1
        instrs.add(new LDR(-Instr.BYTE_SIZE, Condition.NO_CON, Instr.R1,
            AddrMode.buildAddrWithOffset(Instr.R1, Instr.WORD_SIZE)));
      } else {
        instrs.add(new MOV(Condition.NO_CON, Instr.R1,
            AddrMode.buildImm(Utils.getTypeNumber(eRType))));
      }

      // Jump to dynamic type check
      BackEndGenerator.addToPreDefFuncs(Label.P_DYNAMIC_TYPE_CHECK);
      instrs.add(new BRANCH(true, Condition.NO_CON, Label.P_DYNAMIC_TYPE_CHECK));
    }

    // AND and ORR have different bodies, we deal with them first
    if (op.equals("&&")) {
      instrs.add(new AND(fstReg, AddrMode.buildReg(sndReg)));
      addToCurLabel(instrs);
      return;
    } else if (op.equals("||")) {
      instrs.add(new ORR(false, fstReg, AddrMode.buildReg(sndReg)));
      addToCurLabel(instrs);
      return;
    }

    // Compare registers, set result conditions depending on op
    instrs.add(new CMP(fstReg, AddrMode.buildReg(sndReg)));
    String c1 = "";
    String c2 = "";
    switch (op) {
      case ">":
        c1 = Condition.GT;
        c2 = Condition.LE;
        break;

      case ">=":
        c1 = Condition.GE;
        c2 = Condition.LT;
        break;

      case "<":
        c1 = Condition.LT;
        c2 = Condition.GE;
        break;

      case "<=":
        c1 = Condition.LE;
        c2 = Condition.GT;
        break;

      case "==":
        c1 = Condition.EQ;
        c2 = Condition.NE;
        break;

      case "!=":
        c1 = Condition.NE;
        c2 = Condition.EQ;
        break;

      default:
        assert (false); // UNREACHABLE
    }

    // Set result at target register depending on condition
    instrs.add(new MOV(c1, fstReg, AddrMode.buildImm(TRUE_VAL)));
    instrs.add(new MOV(c2, fstReg, AddrMode.buildImm(FALSE_VAL)));

    addToCurLabel(instrs);
  }
}
