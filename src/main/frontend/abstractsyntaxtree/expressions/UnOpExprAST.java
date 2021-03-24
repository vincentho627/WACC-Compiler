  package frontend.abstractsyntaxtree.expressions;

import antlr.WaccParser.UnaryOperContext;
import backend.BackEndGenerator;
import backend.instructions.AddrMode;
import backend.instructions.BRANCH;
import backend.instructions.Condition;
import backend.instructions.Instr;
import backend.instructions.LDR;
import backend.instructions.Label;
import backend.instructions.ORR;
import backend.instructions.SUB;
import frontend.abstractsyntaxtree.Node;
import frontend.abstractsyntaxtree.Utils;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.ArrayID;
import frontend.symboltable.BoolID;
import frontend.symboltable.CharID;
import frontend.symboltable.Identifier;
import frontend.symboltable.IntID;
import frontend.symboltable.StringID;
import frontend.symboltable.SymbolTable;
import frontend.symboltable.TypeID;
import frontend.symboltable.VarID;
import java.util.ArrayList;
import java.util.List;

import static backend.Utils.dynamicTypeCheckIfNeeded;
import static backend.instructions.Instr.*;

public class UnOpExprAST extends Node {

  private final SymbolTable symbtab;
  private final UnaryOperContext ctx;
  private final Node exprAST;
  private List<TypeID> correctExprType;

  public UnOpExprAST(SymbolTable symbtab, Node exprAST, UnaryOperContext ctx) {
    super();
    this.symbtab = symbtab;
    this.ctx = ctx;
    this.exprAST = exprAST;
    correctExprType = new ArrayList<>();
  }

  // Different unOp defined for different types
  @Override
  public void check() {
    TypeID exprType = exprAST.getIdentifier().getType();
    Identifier unOpExprType = null;
    boolean error = false;

    if (ctx.NOT() != null) { // NOT defined for bool only
      if (!(exprType instanceof BoolID || exprType instanceof VarID)) {
        error = true;
      }
      correctExprType.add((TypeID) symbtab.lookupAll("bool"));
      unOpExprType = symbtab.lookupAll("bool");

    } else if (ctx.MINUS() != null) { // MINUS defined for int only
      if (!(exprType instanceof IntID || exprType instanceof VarID)) {
        error = true;
      }
      correctExprType.add((TypeID) symbtab.lookupAll("int"));
      unOpExprType = symbtab.lookupAll("int");

    } else if (ctx.CHR() != null) { // CHR defined for int only
      if (!(exprType instanceof IntID || exprType instanceof VarID)) {
        error = true;
      }
      correctExprType.add((TypeID) symbtab.lookupAll("int"));
      unOpExprType = symbtab.lookupAll("char");

    } else if (ctx.LEN() != null) { // LEN defined for string and array
      if (!(exprType instanceof StringID || exprType instanceof ArrayID || exprType instanceof VarID)) {
        error = true;
      }
      correctExprType.add((TypeID) symbtab.lookupAll("string"));
      unOpExprType = symbtab.lookupAll("int");

    } else if (ctx.ORD() != null) { // ORD defined for char only
      if (!(exprType instanceof CharID || exprType instanceof VarID)) {
        error = true;
      }
      correctExprType.add((TypeID) symbtab.lookupAll("char"));
      unOpExprType = symbtab.lookupAll("int");
    } else if (ctx.BIT_NOT() != null) { // BIT_NOT defined for int only
      if (!(exprType instanceof IntID || exprType instanceof VarID)) {
        error = true;
      }
      correctExprType.add((TypeID) symbtab.lookupAll("int"));
      unOpExprType = symbtab.lookupAll("int");
    }

    if (error) {
      SemanticErrorCollector
          .addIncompatibleType(unOpExprType.getType().getTypeName(),
              exprType.getTypeName(),
              ctx.getText(), ctx.getStart().getLine(),
              ctx.getStart().getCharPositionInLine());
    }

    setIdentifier(unOpExprType);
  }

  @Override
  public void toAssembly() {
    // op exprAST
    // Evaluate exprAST, then op

    // Evaluate exprAST
    String targetReg = Instr.getTargetReg();
    exprAST.toAssembly();
    dynamicTypeCheckIfNeeded(exprAST, Utils.getTypeNumber(correctExprType));

    // Evaluate op
    List<Instr> instrs = new ArrayList<>();
    if (ctx.NOT() != null) {
      // Use XOR to flip bits
      instrs.add(new ORR(true, targetReg, AddrMode.buildImm(TRUE_VAL)));
    } else if (ctx.MINUS() != null) {
      // Reverse subtract
      instrs
          .add(new SUB(true, true, targetReg, targetReg, AddrMode.buildImm(0)));
      // Check for overflow
      BackEndGenerator.addToPreDefFuncs(Label.P_THROW_OVERFLOW_ERROR);
      instrs.add(new BRANCH(true, Condition.VS, Label.P_THROW_OVERFLOW_ERROR));
    } else if (ctx.LEN() != null) {
      // Length of array stored at 0th index of its corresponding memory addr
      instrs.add(new LDR(WORD_SIZE, Condition.NO_CON, targetReg,
          AddrMode.buildAddr(targetReg)));
    } else if (ctx.BIT_NOT() != null) {
      instrs.add(new LDR(Instr.incDepth(), AddrMode.buildVal(0)));
      instrs.add(new SUB(false,false, getTargetReg(), getTargetReg(),
          AddrMode.buildImm(1)));
      instrs
          .add(new ORR(true, targetReg, AddrMode.buildReg(getTargetReg())));
      Instr.decDepth();
    }
    /*
    No need additional instructions for chr and ord as long as the correct value
    is loaded when evaluating expression.
    */

    addToCurLabel(instrs);
  }
}
