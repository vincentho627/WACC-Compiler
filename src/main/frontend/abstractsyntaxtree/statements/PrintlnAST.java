package frontend.abstractsyntaxtree.statements;

import backend.BackEndGenerator;
import backend.instructions.*;
import frontend.abstractsyntaxtree.Node;
import frontend.symboltable.ArrayID;
import frontend.symboltable.TypeID;
import frontend.symboltable.VarID;
import java.util.ArrayList;
import java.util.List;

import static backend.Utils.getPrintBranch;
import static backend.instructions.Instr.addToCurLabel;

public class PrintlnAST extends Node {

  private final Node expr;

  public PrintlnAST(Node expr) {
    super(expr.getIdentifier());
    this.expr = expr;
  }

  public Node getExpr() {
    return expr;
  }

  @Override
  public void check() {
  }

  @Override
  public void toAssembly(){
    // Evaluate the expression
    expr.toAssembly();
    List<Instr> instrs = new ArrayList<>();

    // Move argument from R4 to R0
    Instr instr;
    if (identifier instanceof ArrayID) {
      instr = new ADD(false, Instr.R0, Instr.R4, AddrMode.buildImm(1));
    } else {
      instr = new MOV(Condition.NO_CON, Instr.R0, AddrMode.buildReg(Instr.R4));
    }
    instrs.add(instr);

    // Branch to appropriate print label according to its type
    // Branch to print label according to its type
    TypeID type = expr.getIdentifier().getType();
    if (type instanceof VarID) {
      type = ((VarID) type).getTypeSoFar();
    }
    instrs.add(getPrintBranch(type));

    // Branch to p_print_ln label
    BackEndGenerator.addToPreDefFuncs(Label.P_PRINT_LN);
    instrs.add(new BRANCH(true, Condition.NO_CON, Label.P_PRINT_LN));
    addToCurLabel(instrs);
  }
}
