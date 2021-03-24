package frontend.abstractsyntaxtree.pairs;

import frontend.abstractsyntaxtree.Node;
import frontend.symboltable.Identifier;


public class PairElemTypeAST extends Node {

  public PairElemTypeAST(Identifier identifier) {
    super(identifier);
  }

  @Override
  public void check() {}

  @Override
  public void toAssembly() {}
}
