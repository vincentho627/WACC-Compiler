package frontend.abstractsyntaxtree.functions;

import antlr.WaccParser.ParamContext;
import backend.instructions.Instr;
import frontend.abstractsyntaxtree.Node;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.Identifier;
import frontend.symboltable.SymbolTable;
import java.util.Collections;
import java.util.List;

public class ParamAST extends Node {

  private final String varName;
  private final SymbolTable symtab;
  private final ParamContext ctx;

  public ParamAST(Identifier identifier, SymbolTable symtab, String varName, ParamContext ctx) {
    super(identifier);
    this.symtab = symtab;
    this.varName = varName;
    this.ctx = ctx;
  }

  public String getName() {
    return varName;
  }

  @Override
  public void check() {
    Identifier v;

    if (symtab.isTopLevel()) {
      v = symtab.lookup(varName);
    } else {
      if (symtab.getParent().getClassContext()) {
        v = symtab.lookupAll(varName);
      } else {
        v = symtab.lookup(varName);
      }
    }

    if (v != null) {
      SemanticErrorCollector.addSymbolAlreadyDefined(
          varName, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
      return;
    }
    symtab.add(varName, identifier);
  }

  @Override
  public void toAssembly() {}
}
