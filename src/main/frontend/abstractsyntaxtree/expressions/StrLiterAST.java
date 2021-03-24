package frontend.abstractsyntaxtree.expressions;

import backend.BackEndGenerator;
import backend.instructions.AddrMode;
import backend.instructions.Instr;
import backend.instructions.LDR;
import frontend.abstractsyntaxtree.Node;
import frontend.symboltable.SymbolTable;
import java.util.ArrayList;
import java.util.List;

import static backend.instructions.Instr.addToCurLabel;

public class StrLiterAST extends Node {

  private final String val; //For backend

  public StrLiterAST(SymbolTable symtab, String val) {
    super(symtab.lookupAll("string"));
    assert (super.identifier != null);
    this.val = val.substring(1, val.length() - 1);
  }

  @Override
  public void check() {}

  @Override
  public void toAssembly() {
    // Add string to data segment and load message directly into target register
    int index = BackEndGenerator.addToDataSegment(val);
    Instr loadMsg = new LDR(Instr.getTargetReg(), AddrMode.buildStringVal(index));
    addToCurLabel(loadMsg);
  }
}
