package frontend.abstractsyntaxtree.classes;

import antlr.WaccParser.ClassesContext;
import frontend.abstractsyntaxtree.Node;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.Identifier;
import frontend.symboltable.SymbolTable;
import java.util.List;

public class ClassAST extends Node {

  private final String className;
  private SymbolTable globalScope;
  private ClassesContext ctx;
  private ClassAttributeListAST classAttrListAST;
  private ClassConstructorAST constructorAST;
  private List<ClassFuncAST> classFuncASTSList;

  public ClassAST(Identifier identifier, SymbolTable globalScope,
      String className, ClassAttributeListAST classAttrListAST,
      ClassConstructorAST constructorAST, List<ClassFuncAST> classFuncASTList,
      ClassesContext ctx) {
    super(identifier);
    this.className = className;
    this.ctx = ctx;
    this.globalScope = globalScope;
    this.classAttrListAST = classAttrListAST;
    this.constructorAST = constructorAST;
    this.classFuncASTSList = classFuncASTList;
  }

  public void addClassToGlobalScope() {
    Identifier c = globalScope.lookupAll("class " + className);

    // class already defined
    if (c != null) {
      SemanticErrorCollector.addSymbolAlreadyDefined(
          className, ctx.getStart().getLine(),
          ctx.getStart().getCharPositionInLine());
      return;
    }

    globalScope.add("class " + className, identifier);
  }

  @Override
  public void check() {
  }

  @Override
  public void toAssembly() {
    // dont need to call classAttrListAST to assembly since the attributes are the exact same
    // as the constructor param
    constructorAST.toAssembly();

    // calls assembly for functions to make their own branches
    for (ClassFuncAST classFuncAST: classFuncASTSList) {
      classFuncAST.toAssembly();
    }
  }
}
