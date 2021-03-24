package frontend.symboltable;

public class ClassAttributeID extends TypeID {

  private TypeID typeID;
  private String varName;

  public ClassAttributeID(TypeID typeID, String varName) {
    super(typeID.getTypeName());
    this.typeID = typeID;
    this.varName = varName;
  }

  @Override
  public TypeID getType() {
    return this.typeID;
  }
}
