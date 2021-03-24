package frontend.abstractsyntaxtree.statements;

import frontend.abstractsyntaxtree.Node;


public class SkipAST extends Node {

  public SkipAST() {}

  // No need to check as it is a terminal node
  @Override
  public void check() {}

  @Override
  public void toAssembly() {}
}
