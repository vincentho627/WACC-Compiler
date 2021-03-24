package frontend.symboltable;

/**
 * An abstract generic identifier object with a known type.
 */
public abstract class TypeID extends Identifier {

  private final String typeName;

  protected TypeID(String typeName) {
    this.typeName = typeName;
  }

  public String getTypeName() {
    return this.typeName;
  }

  // size of word by default
  public int getBytes() {
    return 4;
  }
}
