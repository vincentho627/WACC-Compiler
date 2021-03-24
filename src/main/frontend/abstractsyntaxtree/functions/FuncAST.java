package frontend.abstractsyntaxtree.functions;

import antlr.WaccParser.FuncContext;
import backend.instructions.*;
import frontend.abstractsyntaxtree.Node;
import frontend.abstractsyntaxtree.Utils;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.*;
import java.util.ArrayList;
import java.util.List;

import static backend.Utils.getStartRoutine;
import static backend.instructions.Instr.WORD_SIZE;
import static backend.instructions.Instr.addToCurLabel;
import static backend.instructions.Instr.addToLabelOrder;
import static backend.instructions.Instr.setCurLabel;

public class FuncAST extends Node {

  private final String funcName;
  private final ParamListAST params;

  private SymbolTable globalScope;
  private Node statements;

  private FuncContext ctx;

  public FuncAST(Identifier identifier, SymbolTable globalScope,
      String funcName, ParamListAST params, FuncContext ctx) {
    super(identifier);
    this.funcName = funcName;
    this.params = params;
    this.globalScope = globalScope;
    this.ctx = ctx;
  }

  public String getFuncName() { return this.funcName; }

  public void setStatements(Node statements) {
    this.statements = statements;
  }

  public void addFuncToGlobalScope() {
    Identifier f = globalScope.lookupAll("func " + funcName);

    // f already defined
    if (f != null) {
      SemanticErrorCollector.addSymbolAlreadyDefined(
          funcName, ctx.getStart().getLine(),
          ctx.getStart().getCharPositionInLine());
      return;
    }

    globalScope.add("func " + funcName, identifier);
  }

  @Override
  public void check() {
    // Return type of body
    TypeID bodyReturnType = Utils
        .inferFinalReturnType(statements, ctx.getStart().getLine());
    // Declared return type
    TypeID funcReturnType = identifier.getType();

    // Body can just exit and match any return type
    if (!(bodyReturnType instanceof ExitID || Utils
        .typeCompat(funcReturnType, bodyReturnType))) {
      SemanticErrorCollector
          .addIncompatibleReturnTypes(funcReturnType.getTypeName(),
              bodyReturnType.getTypeName(), "function " + funcName,
              ctx.getStart().getLine(),
              ctx.getStart().getCharPositionInLine());
    }
  }

  @Override
  public void toAssembly() {
    int offset;
    SymbolTable funcSymTab = ((FuncID) identifier).getSymtab();
    offset = WORD_SIZE + funcSymTab.getSize();

    boolean skipLR = false;
    if (globalScope.isTopLevel()) {
      String labelName = Label.FUNC_HEADER + funcName;
      setCurLabel(labelName);
      addToLabelOrder(labelName);
    } else {
      skipLR = true;
      funcSymTab.addOffset("object_addr", offset);
      offset += 4;
    }

    // Calculate the size of stack frame that needs to be allocated
    for (Node paramAST : params.getParamList()) {
      skipLR = true;
      String varName = ((ParamAST) paramAST).getName();
      funcSymTab.addOffset(varName, offset);
      offset += paramAST.getIdentifier().getType().getBytes();
    }

    List<Instr> instructions = new ArrayList<>();
    addToCurLabel(new PUSH(Instr.LR));
    addToCurLabel(getStartRoutine(funcSymTab, false));

    // If there is at least one parameter, we need to account for LR on the stack
    if (skipLR) {
      funcSymTab.setSkipLR();
    }
    statements.toAssembly();

    // Clean up routine
    instructions.add(new POP(Instr.PC));
    instructions.add(new LTORG());
    addToCurLabel(instructions);
    setCurLabel(Label.MAIN);
  }
}
