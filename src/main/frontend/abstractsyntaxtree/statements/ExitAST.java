package frontend.abstractsyntaxtree.statements;

import antlr.WaccParser.Exit_statContext;
import antlr.WaccParser.ExprContext;
import backend.Utils;
import backend.instructions.*;
import frontend.abstractsyntaxtree.Node;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.ExitID;
import frontend.symboltable.IntID;
import frontend.symboltable.TypeID;
import frontend.symboltable.VarID;
import java.util.ArrayList;
import java.util.List;

import static backend.instructions.Instr.addToCurLabel;
import static frontend.abstractsyntaxtree.Utils.getTypeNumber;

public class ExitAST extends Node {

  Exit_statContext ctx;
  private final Node expr;
  private final TypeID refToInt;

  public ExitAST(Node expr, Exit_statContext ctx, TypeID refToInt) {
    super(new ExitID());
    this.ctx = ctx;
    this.expr = expr;
    this.refToInt = refToInt;
  }

  public Node getExpr() {
    return expr;
  }

  @Override
  public void check() {
    TypeID exprType = expr.getIdentifier().getType();
    ExprContext exprCtx = ctx.expr();
    if (!(exprType instanceof IntID || exprType instanceof VarID)) {
      int line = exprCtx.getStart().getLine();
      int pos = exprCtx.getStart().getCharPositionInLine();
      SemanticErrorCollector.addIncompatibleType(
          "int", exprType.getTypeName(), exprCtx.getText(), line, pos);
    }
  }

  @Override
  public void toAssembly() {
    // Evaluate the expression
    expr.toAssembly();
    // Exit only works with int
    Utils.dynamicTypeCheckIfNeeded(expr, getTypeNumber(refToInt));

    List<Instr> instrs = new ArrayList<>();
    // Move the expression from R4 to R0
    MOV movInstr = new MOV(Condition.NO_CON, Instr.R0, AddrMode.buildReg(Instr.R4));
    instrs.add(movInstr);

    // Branch to exit label
    BRANCH brInstr = new BRANCH(true, Condition.NO_CON, Label.EXIT);
    instrs.add(brInstr);

    addToCurLabel(instrs);
  }
}
