package frontend.symboltable;

/**
 * UnknownID is assigned to identifier ASTs when an identifier has not been
 * previously declared or cannot be found in the symbol table.
 */
public class UnknownID extends TypeID {

  public UnknownID() {
    super("unknown");
  }

  @Override
  public TypeID getType() {
    return this;
  }
}
