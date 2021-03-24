package frontend.symboltable;

public class ParamID extends Identifier {

  private final TypeID type;

  public ParamID(TypeID type) {
    this.type = type;
  }

  @Override
  public TypeID getType() {
    return type;
  }
}
