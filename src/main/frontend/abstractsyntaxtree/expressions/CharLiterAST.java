package frontend.abstractsyntaxtree.expressions;

import backend.instructions.AddrMode;
import backend.instructions.Condition;
import backend.instructions.Instr;
import backend.instructions.MOV;
import frontend.abstractsyntaxtree.Node;
import frontend.symboltable.SymbolTable;
import java.util.ArrayList;
import java.util.List;

import static backend.instructions.Instr.addToCurLabel;

public class CharLiterAST extends Node {

  private final String val;

  public CharLiterAST(SymbolTable symtab, String val) {
    super(symtab.lookupAll("char"));
    this.val = val;
  }

  @Override
  public void check() {
  }

  private AddrMode getVal() {
    return (val.equals("\\0") ? AddrMode.buildImm(0)
        : AddrMode.buildImm("'" + val + "'"));
  }

  @Override
  public void toAssembly() {
    // Move character into target register
    Instr movChar = new MOV(Condition.NO_CON, Instr.getTargetReg(), getVal());
    addToCurLabel(movChar);
  }
}
