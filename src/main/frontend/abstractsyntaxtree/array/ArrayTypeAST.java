package frontend.abstractsyntaxtree.array;

import frontend.abstractsyntaxtree.Node;
import frontend.symboltable.Identifier;

public class ArrayTypeAST extends Node {

  public ArrayTypeAST(Identifier identifier) {
    super(identifier);
  }

  @Override
  public void check() {}

  @Override
  public void toAssembly() {}
}
