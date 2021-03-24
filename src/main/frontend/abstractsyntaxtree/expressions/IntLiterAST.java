package frontend.abstractsyntaxtree.expressions;

import backend.instructions.AddrMode;
import backend.instructions.Instr;
import backend.instructions.LDR;
import frontend.abstractsyntaxtree.Node;
import frontend.symboltable.SymbolTable;
import java.util.ArrayList;
import java.util.List;

import static backend.instructions.Instr.addToCurLabel;

public class IntLiterAST extends Node {

  private String val;

  public IntLiterAST(SymbolTable symtab, boolean positive, String val) {
    super(symtab.lookupAll("int"));
    this.val = (positive ? "" : "-") + val;
  }

  public String getVal() {
    return val;
  }

  @Override
  public void check() {
  }

  @Override
  public void toAssembly() {
    // Remove leading zeros
    if (!val.equals("0")) {
      boolean zero = false;
      int i = 0;
      while (i < val.length() && val.charAt(i) == '0') {
        i++;
        if (i == val.length()) {
          zero = true;
          break;
        }
      }
      val = zero ? "0" : val.substring(i);
    }

    // Load value directly into target register
    Instr loadInt = new LDR(Instr.getTargetReg(), AddrMode.buildVal(val));
    addToCurLabel(loadInt);
  }
}
