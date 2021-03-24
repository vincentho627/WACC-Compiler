package frontend.abstractsyntaxtree;

import frontend.symboltable.SymbolTable;
import java.util.List;

/**
 * A top-level AST node for the whole program.
 */
public class AST extends Node {

  /**
   * A list of all the functions within the program scanned in the first pass.
   */
  private final List<Node> classASTs;
  private final List<Node> funcASTs;
  private final Node statAST;
  private final SymbolTable symtab;

  public AST(List<Node> classASTs, List<Node> funcASTs, Node statAST, SymbolTable symtab) {
    super();
    this.classASTs = classASTs;
    this.funcASTs = funcASTs;
    this.statAST = statAST;
    this.symtab = symtab;
  }

  public List<Node> getFuncASTs() {
    return funcASTs;
  }

  public Node getStatAST() {
    return statAST;
  }

  public SymbolTable getSymtab() {
    return symtab;
  }

  @Override
  public void check() {
  }

  @Override
  public void toAssembly() {
    for (Node classAST : classASTs) {
      classAST.toAssembly();
    }

    for (Node funcAST : funcASTs) {
      funcAST.toAssembly();
    }
    statAST.toAssembly();
  }
}
