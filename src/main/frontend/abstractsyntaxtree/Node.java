package frontend.abstractsyntaxtree;

import backend.instructions.Instr;
import frontend.symboltable.Identifier;
import java.util.List;

/**
 * A node within the abstract syntax tree.
 */
public abstract class Node {

  protected Identifier identifier;

  protected Node(Identifier identifier) {
    this.identifier = identifier;
  }

  protected Node() {
    this(null);
  }

  public Identifier getIdentifier() {
    return identifier;
  }

  public void setIdentifier(Identifier identifier) {
    this.identifier = identifier;
  }

  public abstract void check();

  public abstract void toAssembly();

  public String getValue(){
    return null;
  }
}
