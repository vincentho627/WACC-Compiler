package frontend.abstractsyntaxtree.statements;

import antlr.WaccParser.ExprContext;
import backend.instructions.*;
import frontend.abstractsyntaxtree.Node;
import frontend.abstractsyntaxtree.Utils;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.BoolID;
import frontend.symboltable.SymbolTable;
import frontend.symboltable.TypeID;
import frontend.symboltable.VarID;

import static backend.Utils.dynamicTypeCheckIfNeeded;
import static backend.Utils.getEndRoutine;
import static backend.Utils.getStartRoutine;
import static backend.instructions.Instr.*;

public class IfAST extends Node {

  private final Node expr;
  private final Node thenStat;
  private final Node elseStat;
  // BACKEND: symbol tables of then and else stats
  private final SymbolTable thenScope;
  private final SymbolTable elseScope;
  private final ExprContext exprCtx;

  public IfAST(Node expr, Node thenStat, Node elseStat, SymbolTable thenScope,
      SymbolTable elseScope, ExprContext ctx) {
    // Set identifier to be same as expressions
    super(expr.getIdentifier());
    this.expr = expr;
    this.thenStat = thenStat;
    this.elseStat = elseStat;
    this.thenScope = thenScope;
    this.elseScope = elseScope;
    this.exprCtx = ctx;
  }

  public Node getThenStat() {
    return thenStat;
  }

  public Node getElseStat() {
    return elseStat;
  }

  @Override
  public void check() {
    TypeID exprType = expr.getIdentifier().getType();
    if (!(exprType instanceof BoolID || exprType instanceof VarID)) {
      SemanticErrorCollector.addIncompatibleType(
          "bool",
          expr.getIdentifier().getType().getTypeName(),
          exprCtx.getText(),
          exprCtx.getStart().getLine(),
          exprCtx.getStart().getCharPositionInLine());
    }
  }

  @Override
  public void toAssembly() {
    // Evaluate the boolean expression
    expr.toAssembly();
    dynamicTypeCheckIfNeeded(expr,
        Utils.getTypeNumber((TypeID) thenScope.lookupAll("bool")));

    String elseStatLabel = getNextLabel();
    String nextStatLabel = getNextLabel();

    // Testing the boolean expression
    // If it evaluates to false jump to elseStat label
    addToCurLabel(new CMP(Instr.R4, AddrMode.buildImm(0)));
    addToCurLabel(new BRANCH(false, Condition.EQ, elseStatLabel));

    //Evaluate thenStat body in current label
    addToCurLabel(getStartRoutine(thenScope, false));
    thenStat.toAssembly();
    addToCurLabel(getEndRoutine(thenScope, false));
    // Branch to the nextStatLabel to skip over the elseStatlabel
    addToCurLabel(new BRANCH(false, Condition.NO_CON, nextStatLabel));

    // Add elseStatLabel before evaluating elseStat
    addToLabelOrder(elseStatLabel);

    // Evaluate the elseStat in a new label
    setCurLabel(elseStatLabel);
    addToCurLabel(getStartRoutine(elseScope, false));
    elseStat.toAssembly();
    addToCurLabel(getEndRoutine(elseScope, false));

    // Create and set a new label for the next statements after the ifAST
    setCurLabel(nextStatLabel);
    addToLabelOrder(nextStatLabel);
  }
}
