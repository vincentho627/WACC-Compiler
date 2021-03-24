package frontend.abstractsyntaxtree.statements;

import backend.Utils;
import backend.instructions.AddrMode;
import backend.instructions.BRANCH;
import backend.instructions.Condition;
import backend.instructions.Instr;
import backend.instructions.MOV;
import frontend.abstractsyntaxtree.Node;
import frontend.symboltable.TypeID;
import frontend.symboltable.VarID;
import java.util.ArrayList;
import java.util.List;

import static backend.instructions.Instr.addToCurLabel;

public class PrintAST extends Node {

  private final Node expr;

  public PrintAST(Node expr) {
    super(expr.getIdentifier());
    this.expr = expr;
  }

  public Node getExpr() {
    return expr;
  }

  @Override
  public void check() {}

  @Override
  public void toAssembly() {
    // Evaluate the expression
    expr.toAssembly();
    List<Instr> instrs = new ArrayList<>();

    // Move argument from R4 to R0
    MOV movInstr = new MOV(Condition.NO_CON, Instr.R0, AddrMode.buildReg(Instr.R4));
    instrs.add(movInstr);

    // Branch to print label according to its type
    TypeID type = expr.getIdentifier().getType();
    if (type instanceof VarID) {
      type = ((VarID) type).getTypeSoFar();
    }
    BRANCH brInstr = Utils.getPrintBranch(type);
    instrs.add(brInstr);

    addToCurLabel(instrs);
  }


}
