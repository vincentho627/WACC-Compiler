package frontend.abstractsyntaxtree.expressions;

import backend.instructions.AddrMode;
import backend.instructions.Condition;
import backend.instructions.Instr;
import backend.instructions.MOV;
import frontend.abstractsyntaxtree.Node;
import frontend.symboltable.SymbolTable;
import java.util.ArrayList;
import java.util.List;

import static backend.instructions.Instr.FALSE_VAL;
import static backend.instructions.Instr.TRUE_VAL;
import static backend.instructions.Instr.addToCurLabel;

public class BoolLiterAST extends Node {

  private final boolean val;

  public BoolLiterAST(SymbolTable symtab, Boolean val) {
    super(symtab.lookupAll("bool"));
    this.val = val;
  }

  @Override
  public void check() {
  }

  private AddrMode getVal() {
    return (this.val ? AddrMode.buildImm(TRUE_VAL) : AddrMode.buildImm(FALSE_VAL));
  }

  @Override
  public void toAssembly() {
    // Move boolean into target register
    Instr movBool = new MOV(Condition.NO_CON, Instr.getTargetReg(), getVal());
    addToCurLabel(movBool);
  }
}
