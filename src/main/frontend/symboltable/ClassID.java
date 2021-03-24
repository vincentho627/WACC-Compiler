package frontend.symboltable;


import frontend.abstractsyntaxtree.classes.ClassAttributeListAST;

public class ClassID extends TypeID {

  private final SymbolTable symtab;
  private final int attributeBytes;
  private final ClassAttributeListAST classAttributeListAST;

  public ClassID(String className, SymbolTable symtab, ClassAttributeListAST classAttributeListAST) {
    super(className);
    this.symtab = symtab;
    this.attributeBytes = classAttributeListAST.getAttributeBytes();
    this.classAttributeListAST = classAttributeListAST;
  }

  public ClassAttributeListAST getClassAttributes() { return classAttributeListAST; }

  @Override
  public TypeID getType() {
    return this;
  }

  @Override public int getBytes() {return attributeBytes;}

  public SymbolTable getSymtab() {
    return symtab;
  }
}
