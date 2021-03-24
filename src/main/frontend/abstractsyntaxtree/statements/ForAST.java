package frontend.abstractsyntaxtree.statements;

import antlr.WaccParser;
import backend.BackEndGenerator;
import backend.Utils;
import backend.instructions.*;
import frontend.abstractsyntaxtree.Node;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.IntID;
import frontend.symboltable.SymbolTable;
import frontend.symboltable.TypeID;
import frontend.symboltable.VarID;

import static backend.instructions.Instr.*;
import static frontend.abstractsyntaxtree.Utils.getTypeNumber;

public class ForAST extends Node {

  private final String varName;
  private final Node startExpr;
  private final Node endExpr;
  private final Node stat;
  private final SymbolTable currSymtab;
  private final WaccParser.For_statContext ctx;

  public ForAST(String varName, Node startExpr, Node endExpr, Node stat,
      SymbolTable currSymTab, WaccParser.For_statContext ctx) {
    this.varName = varName;
    this.startExpr = startExpr;
    this.endExpr = endExpr;
    this.stat = stat;
    this.currSymtab = currSymTab;
    this.ctx = ctx;
  }

  @Override
  public void check() {
    // Only allow ints
    TypeID startType = startExpr.getIdentifier().getType();
    TypeID endType = endExpr.getIdentifier().getType();

    if (!(endType instanceof IntID || endType instanceof VarID)) {
      SemanticErrorCollector.addIncompatibleType(
          "int",
          endType.getTypeName(),
          ctx.expr(1).getText(),
          ctx.expr(1).getStart().getLine(),
          ctx.expr(1).getStart().getCharPositionInLine());
    }

    if (!(startType instanceof IntID || startType instanceof VarID)) {
      SemanticErrorCollector.addIncompatibleType(
          "int",
          startType.getTypeName(),
          ctx.expr(0).getText(),
          ctx.expr(0).getStart().getLine(),
          ctx.expr(0).getStart().getCharPositionInLine());
    }

    currSymtab.getParent().incrementSize(startType.getBytes());
  }

  @Override
  public void toAssembly() {
    // For start and end expr only works with int
    int intTypeNumber = getTypeNumber((TypeID) currSymtab.lookupAll("int"));

    // **** Variable Declaration ******
    // Generate the offset of the variable
    int offset =
        currSymtab.getParent().getSmallestOffset() - startExpr.getIdentifier()
            .getType().getBytes();
    // Add the offset to the enclosing symbol table's hashmap of variables'
    // offsets
    currSymtab.getParent().addOffset(varName, offset);
    startExpr.toAssembly();
    Utils.dynamicTypeCheckIfNeeded(startExpr, intTypeNumber);
    // Stores the value in the offset stack address
    STR strInstr = new STR(Instr.R4,
        AddrMode.buildAddrWithOffset(Instr.SP, offset));
    addToCurLabel(strInstr);

    String nextStatLabel = getNextLabel();
    String bodyLabel = getNextLabel();
    // Branch to this the nextStat label
    addToCurLabel(new BRANCH(false, Condition.NO_CON, nextStatLabel));
    setCurLabel(nextStatLabel);

    // **** Condition: VARNAME < END Expr ****
    String fstReg = Instr.getTargetReg();
    Instr loadVar = new LDR(Instr.getTargetReg(),
        AddrMode.buildAddrWithOffset(Instr.SP, offset));
    addToCurLabel(loadVar);
    String sndReg = Instr.incDepth();
    endExpr.toAssembly();
    Utils.dynamicTypeCheckIfNeeded(endExpr, intTypeNumber);
    Instr.decDepth();
    addToCurLabel(new CMP(fstReg, AddrMode.buildReg(sndReg)));
    addToCurLabel(new MOV(Condition.LT, fstReg, AddrMode.buildImm(TRUE_VAL)));
    addToCurLabel(new MOV(Condition.GE, fstReg, AddrMode.buildImm(FALSE_VAL)));
    // Test if the expression is true if it is we branch the bodyLabel
    addToCurLabel(new CMP(Instr.R4, AddrMode.buildImm(1)));
    addToCurLabel(new BRANCH(false, Condition.EQ, bodyLabel));

    // *** BODY LABEL ****
    setCurLabel(bodyLabel);
    // Body label should be printed before nextStatLabel
    addToLabelOrder(bodyLabel);
    // Make space on the stack for while's new scope
    addToCurLabel(Utils.getStartRoutine(currSymtab, false));
    stat.toAssembly();
    // **** VARNAME ++ ***
    addToCurLabel(new LDR(Instr.R4, AddrMode.buildAddrWithOffset(Instr.SP,
        offset)));
    addToCurLabel(new ADD(false, Instr.R4, Instr.R4, AddrMode.buildImm(1)));
    BackEndGenerator.addToPreDefFuncs(Label.P_THROW_OVERFLOW_ERROR);
    addToCurLabel(
        new STR(Instr.R4, AddrMode.buildAddrWithOffset(Instr.SP, offset)));
    addToCurLabel(Utils.getEndRoutine(currSymtab, false));

    setCurLabel(nextStatLabel);
    addToLabelOrder(nextStatLabel);
  }
}
