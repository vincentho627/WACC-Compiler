package frontend.abstractsyntaxtree.functions;

import frontend.abstractsyntaxtree.Node;
import java.util.List;

public class ArgListAST extends Node {

  private final List<Node> expressions;

  public ArgListAST(List<Node> expressions) {
    this.expressions = expressions;
  }

  public List<Node> getArguments() {
    return expressions;
  }

  @Override
  public void check() {
  }

  @Override
  public void toAssembly() {
    for (Node expr: expressions) {
      expr.toAssembly();
    }
  }
}
