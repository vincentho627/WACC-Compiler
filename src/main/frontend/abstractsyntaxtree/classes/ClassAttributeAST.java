package frontend.abstractsyntaxtree.classes;

import antlr.WaccParser.AttributeContext;
import frontend.abstractsyntaxtree.Node;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.Identifier;
import frontend.symboltable.SymbolTable;
import frontend.symboltable.Visibility;

public class ClassAttributeAST extends Node {

  private final String varName;
  private final SymbolTable symtab;
  private final AttributeContext ctx;

  public ClassAttributeAST(Identifier identifier, SymbolTable symtab,
      Visibility visibility, String varName, AttributeContext ctx) {
    super(identifier);
    identifier.setVisibility(visibility);
    this.symtab = symtab;
    this.varName = varName;
    this.ctx = ctx;
  }

  public String getName() {
    return varName;
  }

  @Override
  public void check() {
    // checks if the variable is defined in the symbol table, if not, it gets added in
    Identifier v = symtab.lookup(varName);

    if (v != null) {
      SemanticErrorCollector.addSymbolAlreadyDefined(
          varName, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
      return;
    }
    symtab.add(varName, identifier);
  }

  @Override
  public void toAssembly() {
  }
}
