package frontend.symboltable;

import frontend.abstractsyntaxtree.Node;
import java.util.List;

public class FuncID extends Identifier {

  private final Node returnType;
  private final List<TypeID> params;
  private SymbolTable symtab;

  public FuncID(Node returnType, List<TypeID> params, SymbolTable symtab) {
    this.returnType = returnType;
    this.params = params;
    this.symtab = symtab;
  }

  @Override
  public TypeID getType() {
    return returnType.getIdentifier().getType();
  }

  public void setSymtab(SymbolTable symtab) {
    this.symtab = symtab;
  }

  public List<TypeID> getParams() {
    return params;
  }

  public SymbolTable getSymtab() {
    return symtab;
  }
}
