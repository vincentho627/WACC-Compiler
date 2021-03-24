package frontend.abstractsyntaxtree.classes;

import static backend.instructions.Instr.addToCurLabel;

import antlr.WaccParser.CallClassFuncContext;
import backend.Utils;
import backend.instructions.ADD;
import backend.instructions.AddrMode;
import backend.instructions.BRANCH;
import backend.instructions.Condition;
import backend.instructions.Instr;
import backend.instructions.Label;
import backend.instructions.MOV;
import backend.instructions.STR;
import frontend.abstractsyntaxtree.Node;
import frontend.abstractsyntaxtree.assignments.AssignRHSAST;
import frontend.abstractsyntaxtree.functions.ArgListAST;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.ClassID;
import frontend.symboltable.FuncID;
import frontend.symboltable.Identifier;
import frontend.symboltable.NullID;
import frontend.symboltable.SymbolTable;
import frontend.symboltable.TypeID;
import frontend.symboltable.UnknownID;
import frontend.symboltable.Visibility;
import java.util.ArrayList;
import java.util.List;

public class CallClassFunctionAST extends AssignRHSAST {

  private final String varName;
  private final String funcName;
  private final SymbolTable symtab;
  private final CallClassFuncContext ctx;
  private final ArgListAST args;
  private final Identifier classID;

  public CallClassFunctionAST(
      String varName, String funcName, ArgListAST args, SymbolTable symtab, CallClassFuncContext ctx) {
    super(symtab.lookupAll(varName), symtab);
    this.varName = varName;
    this.classID = symtab.lookupAll(varName);
    this.funcName = funcName;
    this.symtab = symtab;
    this.ctx = ctx;
    this.args = args;
  }

  @Override
  public void check() {
    int line = ctx.getStart().getLine();
    int position = ctx.getStart().getCharPositionInLine();

    // Variable is not defined in the symbol table yet
    if (identifier == null) {
      SemanticErrorCollector.addVariableUndefined(
          varName, line, position);
      setIdentifier(new UnknownID());
      return;
    }

    // Variable defined is not a class instance
    if (!(identifier instanceof ClassID)) {
      SemanticErrorCollector.addVariableIsNotAnInstanceOfAClass(
          varName, line, position);
      return;
    }

    ClassID classID = (ClassID) identifier;
    SymbolTable classSymtab = classID.getSymtab();

    Identifier functionIdent = classSymtab.lookup("func " + funcName);
    String className = classID.getTypeName();

    // Function does not exist in the class
    if (functionIdent == null) {
      SemanticErrorCollector.addClassDoesNotHaveFunction(
          className, funcName, line, position);
      return;
    }

    if (!(functionIdent instanceof FuncID)) {
      // Given function name is not actually a function type
      SemanticErrorCollector.addIsNotClassFuncError(
          line, position, className, funcName, functionIdent.getType().getTypeName());
      setIdentifier(functionIdent.getType());
      return;
    }

    // Class function is not private
    if (functionIdent.getVisibility() == Visibility.PRIVATE) {
      SemanticErrorCollector.addClassFunctionIsPrivate(
          className, funcName, line, position);
      setIdentifier(functionIdent.getType());
      return;
    }

    // Check if function has the correct param
    List<TypeID> params = ((FuncID) functionIdent).getParams();
    List<Node> argsAST = args.getArguments();
    int paramSize = params.size();
    int argsSize = argsAST.size();

    int pos = (argsSize == 0) ? ctx.getStart().getCharPositionInLine():
        ctx.argList().getStart().getCharPositionInLine();
    // If given number of arguments are not the same as the number of params
    if (paramSize != argsSize) {
      SemanticErrorCollector.addClassFuncInconsistentArgsError(
          line,
          pos,
          className,
          funcName,
          paramSize,
          argsSize);
    } else {
      for (int i = 0; i < paramSize; i++) {
        TypeID currParam = params.get(i);
        Node currArg = argsAST.get(i);
        TypeID argType = currArg.getIdentifier().getType();
        if (!(argType instanceof NullID)) {

          // If argument and param types don't match
          if (currParam.getClass() != argType.getClass()) {
            SemanticErrorCollector.addClassFuncInconsistentArgTypeError(
                line,
                ctx.argList().expr(i).getStart().getCharPositionInLine(),
                className,
                funcName,
                i,
                currParam.getTypeName(),
                argType.getTypeName());
          }
        }
      }
    }
    setIdentifier(functionIdent.getType());
  }

  @Override
  public void toAssembly() {
    int varNameOffset = symtab.getStackOffset(varName);
    Utils.buildClassFunctionInstr(varNameOffset, symtab, args, classID.getType().getTypeName(), funcName);
  }
}
