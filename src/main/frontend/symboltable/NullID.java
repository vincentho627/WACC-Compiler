package frontend.symboltable;

public class NullID extends OptionalPairID {

  public NullID() {
    super("null");
  }

  @Override
  public TypeID getType() {
    return this;
  }
}
