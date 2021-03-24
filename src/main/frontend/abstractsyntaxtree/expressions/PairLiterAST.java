package frontend.abstractsyntaxtree.expressions;

import backend.instructions.AddrMode;
import backend.instructions.Instr;
import backend.instructions.LDR;
import backend.instructions.MOV;
import frontend.abstractsyntaxtree.Node;
import frontend.symboltable.NullID;
import java.util.ArrayList;
import java.util.List;

import static backend.instructions.Instr.NULL_PAIR;
import static backend.instructions.Instr.addToCurLabel;

public class PairLiterAST extends Node {

  public PairLiterAST() {
    super(new NullID());
  }

  @Override
  public void check() {}

  @Override
  public void toAssembly() {
    // Load null (0) into target register
    Instr loadNull = new LDR(Instr.getTargetReg(), AddrMode.buildVal(NULL_PAIR));
    addToCurLabel(loadNull);
  }
}
