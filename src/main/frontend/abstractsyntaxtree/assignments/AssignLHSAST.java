package frontend.abstractsyntaxtree.assignments;

import backend.BackEndGenerator;
import backend.instructions.ADD;
import backend.instructions.AddrMode;
import backend.instructions.BRANCH;
import backend.instructions.Condition;
import backend.instructions.Instr;
import backend.instructions.LDR;
import backend.instructions.Label;
import backend.instructions.MOV;
import frontend.abstractsyntaxtree.Node;
import frontend.abstractsyntaxtree.expressions.ArrayElemAST;
import frontend.abstractsyntaxtree.pairs.PairElemAST;
import frontend.symboltable.SymbolTable;
import java.util.ArrayList;
import java.util.List;

public class AssignLHSAST extends Node {

  private Node assignNode = null;
  private SymbolTable symtab = null;
  private final String assignName;

  public AssignLHSAST(SymbolTable symtab, Node assignNode, String assignName) {
    super(assignNode.getIdentifier());
    this.symtab = symtab;
    this.assignNode = assignNode;
    this.assignName = assignName;
  }

  public AssignLHSAST(SymbolTable symtab, String assignName) {
    super(symtab.lookupAll(assignName));
    this.symtab = symtab;
    this.assignName = assignName;
  }

  public String getIdentName() {
    return assignName;
  }

  public Node getAssignNode() {
    return assignNode;
  }

  @Override
  public void check() {
  }

  @Override
  public void toAssembly() {
    assert (symtab != null);
    assert (assignNode != null);

    List<Instr> instrs = new ArrayList<>();
    if (assignNode instanceof ArrayElemAST) {
      String fstReg = Instr.getTargetReg();
      // Get pointer to array
      Instr.addToCurLabel(new ADD(false, fstReg, Instr.SP,
          AddrMode.buildImm(symtab.getStackOffset(assignName))));
      String sndReg = Instr.incDepth();

      List<Node> exprs = ((ArrayElemAST) assignNode).getExprs();
      for (Node e : exprs) {
        // Evaluate the index
        e.toAssembly();

        // Get the size of array
        instrs.add(new LDR(fstReg, AddrMode.buildAddr(fstReg)));

        // Check size
        instrs.add(new MOV(Condition.NO_CON, Instr.R0, AddrMode.buildReg(sndReg)));
        instrs.add(new MOV(Condition.NO_CON, Instr.R1, AddrMode.buildReg(fstReg)));

        // Check array bounds
        BackEndGenerator.addToPreDefFuncs(Label.P_CHECK_ARRAY_BOUNDS);
        instrs.add(new BRANCH(true, Condition.NO_CON, Label.P_CHECK_ARRAY_BOUNDS));

        // Skip over 5 bytes (size + typeNumber)
        instrs.add(new ADD(false, fstReg, fstReg, AddrMode.buildImm(Instr.WORD_SIZE + Instr.BYTE_SIZE)));

        // Index to the target element
        int size = assignNode.getIdentifier().getType().getBytes();
        if (size > 1) {
          instrs.add(new ADD(false, fstReg, fstReg, AddrMode.buildReg(sndReg),
              AddrMode.buildImmWithLSL(size / 2)));
        } else {
          instrs.add(new ADD(false, fstReg, fstReg, AddrMode.buildReg(sndReg)));
        }
      }

      Instr.decDepth();
    } else { // Pair elem
      String reg = Instr.getTargetReg();
      instrs.add(new LDR(reg, AddrMode
          .buildAddrWithOffset(Instr.SP, symtab.getStackOffset(assignName))));
      instrs.add(new MOV(Condition.NO_CON, Instr.R0, AddrMode.buildReg(reg)));

      // Check null pointer
      BackEndGenerator.addToPreDefFuncs(Label.P_CHECK_NULL_POINTER);
      instrs.add(new BRANCH(true, Condition.NO_CON, Label.P_CHECK_NULL_POINTER));

      // Pair stored as 2 pointers (i.e. fst at index 0, snd at index 4)
      instrs.add(new LDR(reg, AddrMode.buildAddrWithOffset(reg,
          ((PairElemAST) assignNode).isFirst() ? 0 : Instr.WORD_SIZE)));
    }
    Instr.addToCurLabel(instrs);
  }
}
