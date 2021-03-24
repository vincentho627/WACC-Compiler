package frontend.symboltable;

import frontend.abstractsyntaxtree.classes.ClassAttributeListAST;
import java.util.ArrayList;
import java.util.List;

public class ConstructorID extends Identifier {
  private final ClassAttributeListAST attributes;
  private final SymbolTable symtab;
  private final Identifier identifier;

  public ConstructorID(Identifier identifier, ClassAttributeListAST attributes, SymbolTable symtab) {
    this.identifier = identifier;
    this.attributes = attributes;
    this.symtab = symtab;
  }

  @Override
  public TypeID getType() {
    return identifier.getType();
  }

  public ClassAttributeListAST getAttributesList() {
    return attributes;
  }

  public SymbolTable getSymtab() {
    return symtab;
  }
}
