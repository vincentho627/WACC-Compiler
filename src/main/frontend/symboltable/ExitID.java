package frontend.symboltable;

public class ExitID extends TypeID {

  public ExitID() {
    super("exit");
  }

  @Override
  public TypeID getType() {
    return this;
  }
}
