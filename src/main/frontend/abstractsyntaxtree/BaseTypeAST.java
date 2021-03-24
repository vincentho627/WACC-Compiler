package frontend.abstractsyntaxtree;

import antlr.WaccParser.BaseTypeContext;
import backend.instructions.Instr;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.Identifier;
import frontend.symboltable.SymbolTable;
import java.util.List;

public class BaseTypeAST extends Node {

  private final String typeName;
  private final SymbolTable symtab;
  private final BaseTypeContext ctx;

  public BaseTypeAST(Identifier identifier, SymbolTable symtab,
      BaseTypeContext ctx) {
    super(identifier);
    this.symtab = symtab;
    this.typeName = identifier.getType().getTypeName();
    this.ctx = ctx;
  }

  @Override
  public void check() {
    Identifier t = symtab.lookupAll(typeName);

    if (t == null) {
      SemanticErrorCollector.addUnknownType(typeName, ctx.getStart().getLine(),
          ctx.getStart().getCharPositionInLine());
    }
  }

  @Override
  public void toAssembly() {}
}
