package frontend.abstractsyntaxtree.statements;

import antlr.WaccParser.AssignLHSContext;
import backend.BackEndGenerator;
import backend.instructions.*;
import frontend.abstractsyntaxtree.Node;
import frontend.abstractsyntaxtree.assignments.AssignLHSAST;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.*;

import java.util.ArrayList;
import java.util.List;

import static backend.instructions.AddrMode.buildReg;
import static backend.instructions.Condition.NO_CON;
import static backend.instructions.Instr.addToCurLabel;
import static backend.instructions.Label.P_READ_CHAR;
import static backend.instructions.Label.P_READ_INT;

public class ReadAST extends Node {

  private final AssignLHSAST lhs;
  private final AssignLHSContext ctx;
  private final Identifier lhsType;
  private final SymbolTable symtab;

  public ReadAST(AssignLHSAST assignLHS, AssignLHSContext ctx, SymbolTable symtab) {
    super();
    this.lhs = assignLHS;
    this.ctx = ctx;
    this.lhsType = lhs.getIdentifier();
    this.symtab = symtab;
  }

  @Override
  public void check() {
    int line = ctx.getStart().getLine();
    int pos = ctx.getStart().getCharPositionInLine();

    // Don't allow reading into dynamic variable as it messes with size
    if (!(lhsType instanceof IntID || lhsType instanceof CharID)) {
      SemanticErrorCollector.addIncompatibleType(
          "int or char", lhsType.getType().getTypeName(), ctx.getText(),
          line, pos);
    }

  }

  @Override
  public void toAssembly() {
    // Evaluate the expression
    if (lhs.getAssignNode() == null) { // Variable
      Instr.addToCurLabel(new ADD(false, Instr.R4, Instr.SP,
          AddrMode.buildImm(symtab.getStackOffset(lhs.getIdentName()))));
    } else {
      lhs.toAssembly();
    }

    List<Instr> instrs = new ArrayList<>();
    MOV movInstr = new MOV(NO_CON, Instr.R0, buildReg(Instr.R4));
    instrs.add(movInstr);

    // Branch to appropriate read label according to its type
    String label = lhsType instanceof IntID ? P_READ_INT : P_READ_CHAR;
    BackEndGenerator.addToPreDefFuncs(label);
    BRANCH brInstr = new BRANCH(true, NO_CON, label);
    instrs.add(brInstr);

    addToCurLabel(instrs);
  }
}
