package frontend.abstractsyntaxtree.statements;

import antlr.WaccParser.Return_statContext;
import backend.Utils;
import backend.instructions.AddrMode;
import backend.instructions.Condition;
import backend.instructions.Instr;
import backend.instructions.MOV;
import backend.instructions.POP;
import frontend.abstractsyntaxtree.Node;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.SymbolTable;
import java.util.ArrayList;
import java.util.List;

import static backend.instructions.Instr.addToCurLabel;

public class ReturnAST extends Node {

  private final SymbolTable symtab;
  private final Node expr;
  private final Return_statContext ctx;

  public ReturnAST(SymbolTable symtab, Node expr, Return_statContext ctx) {
    super(expr.getIdentifier());
    this.symtab = symtab;
    symtab.setFuncContext();
    this.expr = expr;
    this.ctx = ctx;
  }

  public Node getExpr() {
    return expr;
  }

  @Override
  public void check() {
    if (symtab.isTopLevel()) {
      SemanticErrorCollector.addGlobalReturnError(
          ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    }
  }

  @Override
  public void toAssembly() {
    // Evaluate the expression
    expr.toAssembly();
    List<Instr> instructions = new ArrayList<>();

    // Move argument to R0
    instructions.add(new MOV(Condition.NO_CON, Instr.R0, AddrMode.buildReg(Instr.getTargetReg())));
    addToCurLabel(instructions);

    // Restore the stack pointer by incrementing the function's stack
    addToCurLabel(Utils.getEndRoutine(symtab, false));
    addToCurLabel(new POP(Instr.PC));
  }
}
