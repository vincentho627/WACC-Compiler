package frontend.abstractsyntaxtree.statements;

import frontend.abstractsyntaxtree.Node;
import frontend.symboltable.SymbolTable;

import static backend.Utils.getEndRoutine;
import static backend.Utils.getStartRoutine;
import static backend.instructions.Instr.addToCurLabel;

public class BeginStatAST extends Node {

  private final Node stat;
  private final SymbolTable symtab;

  public BeginStatAST(Node stat, SymbolTable symtab) {
    super(stat.getIdentifier());
    this.stat = stat;
    this.symtab = symtab;
  }

  public Node getStat() {
    return stat;
  }

  @Override
  public void check() {}

  @Override
  public void toAssembly() {
    // Decrement stack pointer to make space for the stack of new scope
    addToCurLabel(getStartRoutine(symtab, false));
    // Evaluate statements inside the new scope
    stat.toAssembly();
    // Increment stack pointer to restore its position in the previous scope
    addToCurLabel(getEndRoutine(symtab,false));
  }
}
