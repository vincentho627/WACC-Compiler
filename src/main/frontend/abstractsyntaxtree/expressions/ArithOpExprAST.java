package frontend.abstractsyntaxtree.expressions;

import static backend.Utils.dynamicTypeCheckIfNeeded;
import static backend.instructions.Instr.addToCurLabel;

import antlr.WaccParser.ExprContext;
import backend.BackEndGenerator;
import backend.instructions.ADD;
import backend.instructions.AND;
import backend.instructions.AddrMode;
import backend.instructions.BRANCH;
import backend.instructions.CMP;
import backend.instructions.Condition;
import backend.instructions.Instr;
import backend.instructions.Label;
import backend.instructions.MOV;
import backend.instructions.MUL;
import backend.instructions.ORR;
import backend.instructions.SUB;
import frontend.abstractsyntaxtree.Node;
import frontend.abstractsyntaxtree.Utils;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.IntID;
import frontend.symboltable.SymbolTable;
import frontend.symboltable.TypeID;
import frontend.symboltable.UnknownID;
import frontend.symboltable.VarID;
import java.util.ArrayList;
import java.util.List;

public class ArithOpExprAST extends Node {

  private final String op;
  private final Node eL;
  private final Node eR;
  private final ExprContext ctx;

  public ArithOpExprAST(SymbolTable symtab, String op, Node eL, Node eR,
      ExprContext ctx) {
    super(symtab.lookupAll("int"));
    this.op = op;
    this.eL = eL;
    this.eR = eR;
    this.ctx = ctx;
  }

  @Override
  public void check() {
    int fstStatPosition = 0;
    int sndStatPosition = 2;

    TypeID eLType = eL.getIdentifier().getType();
    TypeID eRType = eR.getIdentifier().getType();

    // Both eL and eR must be of type int to do arithmetic

    if (!(eLType instanceof IntID || eLType instanceof UnknownID || eLType instanceof VarID)) {
      SemanticErrorCollector
          .addIncompatibleType("int (For " + op + ")", eLType.getTypeName(),
              ctx.children.get(fstStatPosition).getText(),
              ctx.getStart().getLine(),
              ctx.getStart().getCharPositionInLine());
    }

    if (!(eRType instanceof IntID || eRType instanceof UnknownID || eRType instanceof VarID)) {
      SemanticErrorCollector
          .addIncompatibleType("int (For " + op + ")", eRType.getTypeName(),
              ctx.children.get(sndStatPosition).getText(),
              ctx.getStart().getLine(),
              ctx.getStop().getCharPositionInLine());
    }

  }

  @Override
  public void toAssembly() {
    // eL op eR
    // Evaluate eL first, then eR and finally op
    int intTypeNumber = Utils.getTypeNumber(getIdentifier().getType());

    // Evaluate eL
    eL.toAssembly();
    dynamicTypeCheckIfNeeded(eL, intTypeNumber);


    // Evaluate eR (Use next register)
    String sndReg = Instr.incDepth();
    eR.toAssembly();
    dynamicTypeCheckIfNeeded(eR, intTypeNumber);
    String fstReg = Instr.decDepth();


    // Handle special case where we run out of registers and stack is used
    boolean regsOnStack = Instr.regsOnStack();
    String targetReg = regsOnStack ? sndReg : fstReg;

    List<Instr> instrs = new ArrayList<>();

    // To check which pre-defined function to add to assembly program
    boolean addOverflow = true;
    switch (op) {
      case "&":
        instrs.add(new AND(fstReg, AddrMode.buildReg(sndReg)));
        break;

      case "|":
        instrs.add(new ORR(false, fstReg, AddrMode.buildReg(sndReg)));
        break;

      case "+":
        // Do addition and branch if overflow
        instrs.add(new ADD(true, targetReg, fstReg, AddrMode.buildReg(sndReg)));
        instrs.add(new BRANCH(true, Condition.VS, Label.P_THROW_OVERFLOW_ERROR));
        break;

      case "-":
        // Do subtraction and branch if overflow
        instrs.add(new SUB(false, true, targetReg, fstReg, AddrMode.buildReg(sndReg)));
        instrs.add(new BRANCH(true, Condition.VS, Label.P_THROW_OVERFLOW_ERROR));
        break;

      case "*":
        // Do multiplication
        instrs.add(new MUL(fstReg, sndReg, regsOnStack));
        // Compare with arithmetic shift to detect overflow
        if (regsOnStack) {
          instrs.add(new CMP(fstReg, AddrMode.buildReg(sndReg), AddrMode.buildImmWithASR(Instr.WORD_BIT_LIMIT)));
        } else {
          instrs.add(new CMP(sndReg,  AddrMode.buildReg(fstReg), AddrMode.buildImmWithASR(Instr.WORD_BIT_LIMIT)));
        }
        instrs.add(new BRANCH(true, Condition.NE, Label.P_THROW_OVERFLOW_ERROR));
        break;

      case "/":
        addOverflow = false;
        // If not division by zero, do division and store result as needed
        instrs.add(new MOV(Condition.NO_CON, Instr.R0, AddrMode.buildReg(fstReg)));
        instrs.add(new MOV(Condition.NO_CON, Instr.R1, AddrMode.buildReg(sndReg)));
        instrs.add(new BRANCH(true, Condition.NO_CON, Label.P_CHECK_DIVIDE_BY_ZERO));
        instrs.add(new BRANCH(true, Condition.NO_CON, Label.DIV_FUNC));
        instrs.add(new MOV(Condition.NO_CON, Instr.getTargetReg(), AddrMode.buildReg(Instr.R0)));
        break;

      case "%": // Modulus
        addOverflow = false;
        // If not mod zero, do div and mod, store result as needed
        instrs.add(new MOV(Condition.NO_CON, Instr.R0, AddrMode.buildReg(fstReg)));
        instrs.add(new MOV(Condition.NO_CON, Instr.R1, AddrMode.buildReg(sndReg)));
        instrs.add(new BRANCH(true, Condition.NO_CON, Label.P_CHECK_DIVIDE_BY_ZERO));
        instrs.add(new BRANCH(true, Condition.NO_CON, Label.MOD_FUNC));
        instrs.add(new MOV(Condition.NO_CON, Instr.getTargetReg(), AddrMode.buildReg(Instr.R1)));
        break;

      default:
        assert (false); // UNREACHABLE
    }

    // Add pre-defined function
    if (addOverflow) {
      BackEndGenerator.addToPreDefFuncs(Label.P_THROW_OVERFLOW_ERROR);
    } else {
      BackEndGenerator.addToPreDefFuncs(Label.P_CHECK_DIVIDE_BY_ZERO);
    }

    addToCurLabel(instrs);
  }
}
