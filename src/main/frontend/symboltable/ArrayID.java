package frontend.symboltable;

public class ArrayID extends TypeID {

    private final TypeID type;

    public ArrayID(TypeID type) {
        super("array");
        this.type = type;
    }

    public TypeID getElemType(){
        return type;
    }

    @Override public TypeID getType() {
        return this;
    }

}
