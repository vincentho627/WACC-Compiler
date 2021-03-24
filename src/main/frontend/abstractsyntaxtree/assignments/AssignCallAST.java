package frontend.abstractsyntaxtree.assignments;

import static backend.Utils.buildClassFunctionInstr;
import static backend.Utils.dynamicTypeCheckIfNeeded;
import static backend.instructions.Instr.addToCurLabel;

import antlr.WaccParser.Call_assignRHSContext;
import backend.instructions.ADD;
import backend.instructions.AddrMode;
import backend.instructions.BRANCH;
import backend.instructions.Condition;
import backend.instructions.Instr;
import backend.instructions.Label;
import backend.instructions.MOV;
import backend.instructions.STR;
import frontend.abstractsyntaxtree.Node;
import frontend.abstractsyntaxtree.Utils;
import frontend.abstractsyntaxtree.functions.ArgListAST;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.FuncID;
import frontend.symboltable.Identifier;
import frontend.symboltable.NullID;
import frontend.symboltable.SymbolTable;
import frontend.symboltable.TypeID;
import java.util.ArrayList;
import java.util.List;

public class AssignCallAST extends AssignRHSAST {

  private final String funcName;
  private final ArgListAST args;
  private final Call_assignRHSContext ctx;
  private final SymbolTable symtab;

  public AssignCallAST(
      String funcName, SymbolTable symtab, ArgListAST args, Call_assignRHSContext ctx) {
    super(symtab.lookupAll("func " + funcName), symtab);
    this.funcName = funcName;
    this.args = args;
    this.ctx = ctx;
    this.symtab = symtab;
  }

  @Override
  public void check() {
    Identifier funcID = getIdentifier();
    int line = ctx.getStart().getLine();
    int identPos = ctx.IDENT().getSymbol().getCharPositionInLine();

    // Function not defined yet
    if (funcID == null) {
      SemanticErrorCollector.addFunctionUndefined(funcName, line, identPos);
    } else {
      if (funcID instanceof FuncID) {
        List<TypeID> params = ((FuncID) funcID).getParams();
        List<Node> argsAST = args.getArguments();
        int paramSize = params.size();
        int argsSize = argsAST.size();

        int pos =
            (argsSize == 0)
                ? ctx.getStart().getCharPositionInLine()
                : ctx.argList().getStart().getCharPositionInLine();
        // If given number of arguments are not the same as the number of params
        if (paramSize != argsSize) {
          SemanticErrorCollector.addFuncInconsistentArgsError(
              line, pos, funcName, paramSize, argsSize);
        } else {
          for (int i = 0; i < paramSize; i++) {
            TypeID currParam = params.get(i);
            Node currArg = argsAST.get(i);
            TypeID argType = currArg.getIdentifier().getType();
            if (!(argType instanceof NullID)) {

              // If argument and param types don't match
              if (!Utils.typeCompat(argType, currParam)) {
                SemanticErrorCollector.addFuncInconsistentArgTypeError(
                    line,
                    ctx.argList().expr(i).getStart().getCharPositionInLine(),
                    funcName,
                    i,
                    currParam.getTypeName(),
                    argType.getTypeName());
              }
            }
          }
        }
      } else {
        // Given function name is not actually a function type
        SemanticErrorCollector.addIsNotFuncError(
            line, identPos, funcName, funcID.getType().getTypeName());
      }
    }
  }

  @Override
  public void toAssembly() {
    boolean classFunction = false;
    String className = symtab.getClassName();

    // checks if its a class function call
    if (!(symtab.isTopLevel())) {
      if (symtab.getParent().getClassContext()) {
        if (symtab.getParent().lookup("func " + funcName) != null) {
          className = symtab.getParent().getClassName();
          classFunction = true;
        }
      }
    }

    if (classFunction) {
      buildClassFunctionInstr(4, symtab, args, className, funcName);
    } else {
      List<Instr> instructions = new ArrayList<>();

      int accOffset = 0;
      String transferReg = Instr.getTargetReg();
      // Allocate in reverse so that first argument directly on top of LR
      List<TypeID> params = ((FuncID) getIdentifier()).getParams();
      for (int i = args.getArguments().size() - 1; i >= 0; i--) {
        Node argNode = args.getArguments().get(i);

        // Puts the next argument into the transfer register
        argNode.toAssembly();
        dynamicTypeCheckIfNeeded(argNode, Utils.getTypeNumber(params.get(i)));

        // Record total offset to destroy stack after
        int offset = argNode.getIdentifier().getType().getBytes();
        accOffset += offset;

        // Temporary offset so that arguments are accessed correctly
        symtab.incrementFuncOffset(offset);

        // Add to stack
        Instr.addToCurLabel(
            new STR(
                offset,
                Condition.NO_CON,
                transferReg,
                AddrMode.buildAddrWithWriteBack(Instr.SP, -offset)));
      }
      // Function call
      instructions.add(new BRANCH(true, Condition.NO_CON, Label.FUNC_HEADER + funcName));

      // Destroy stack
      if (accOffset > 0) {
        instructions.add(new ADD(false, Instr.SP, Instr.SP, AddrMode.buildImm(accOffset)));
      }
      // Reset temporary offset
      symtab.resetFuncOffset();
      // Move result
      instructions.add(new MOV(Condition.NO_CON, transferReg, AddrMode.buildReg(Instr.R0)));

      addToCurLabel(instructions);
    }
  }
}
