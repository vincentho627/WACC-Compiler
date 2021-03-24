package frontend.symboltable;

public class IntID extends TypeID {

  public IntID() {
    super("int");
  }

  @Override
  public TypeID getType() {
    return this;
  }

}
