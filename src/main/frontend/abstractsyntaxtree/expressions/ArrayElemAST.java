package frontend.abstractsyntaxtree.expressions;

import antlr.WaccParser.ArrayElemContext;
import backend.BackEndGenerator;
import backend.instructions.ADD;
import backend.instructions.AddrMode;
import backend.instructions.BRANCH;
import backend.instructions.Condition;
import backend.instructions.Instr;
import backend.instructions.LDR;
import backend.instructions.Label;
import backend.instructions.MOV;
import frontend.abstractsyntaxtree.Node;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.ArrayID;
import frontend.symboltable.Identifier;
import frontend.symboltable.StringID;
import frontend.symboltable.SymbolTable;
import java.util.ArrayList;
import java.util.List;

import static backend.instructions.Instr.BYTE_SIZE;
import static backend.instructions.Instr.WORD_SIZE;
import static backend.instructions.Instr.addToCurLabel;

public class ArrayElemAST extends Node {

  private Identifier currIdentifier;
  private final SymbolTable symtab;
  private final String val;
  private final List<Node> exprs;
  private final ArrayElemContext ctx;

  public ArrayElemAST(Identifier currIdentifier, SymbolTable symtab, String val,
      List<Node> exprs, ArrayElemContext ctx) {
    super();
    this.symtab = symtab;
    this.currIdentifier = currIdentifier;
    this.val = val;
    this.exprs = exprs;
    this.ctx = ctx;
  }

  public String getName() {
    return this.val;
  }

  public List<Node> getExprs() {
    return exprs;
  }

  @Override
  public void check() {
    for (Node e : exprs) {
      //We cannot index further into something that's not an array
      if (!(currIdentifier instanceof ArrayID)) {
        SemanticErrorCollector.addCannotBeIndexed(ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(), val,
            currIdentifier.getType().getTypeName());
        //Treat string as array of chars in error case
        if (currIdentifier instanceof StringID) {
          currIdentifier = symtab.lookupAll("char");
        }
        break;
      } else {
        currIdentifier = ((ArrayID) currIdentifier).getElemType();
      }
    }
    setIdentifier(currIdentifier);
  }

  private String getOffset() {
    int offset = symtab.getStackOffset(val);
    return "#" + offset;
  }

  @Override
  public void toAssembly() {
    // Store address of array at target register
    String target = Instr.getTargetReg();
    addToCurLabel(new ADD(false, target, Instr.SP, AddrMode.buildReg(getOffset())));
    String sndReg = Instr.incDepth();

    int size = getIdentifier().getType().getBytes();
    // Iterate through nested arrays
    for (Node e : exprs) {
      List<Instr> instrs = new ArrayList<>();

      // Evaluate index expression
      e.toAssembly();

      // Check whether index is positive and within array bounds
      instrs.add(new LDR(target, AddrMode.buildAddr(target)));
      instrs.add(new MOV(Condition.NO_CON, Instr.R0, AddrMode.buildReg(sndReg)));
      instrs.add(new MOV(Condition.NO_CON, Instr.R1, AddrMode.buildReg(target)));
      instrs.add(new BRANCH(true, Condition.NO_CON, Label.P_CHECK_ARRAY_BOUNDS));

      // Skip over first 5 bytes (size and typeNumber)
      instrs.add(new ADD(false, target, target, AddrMode.buildImm(WORD_SIZE + BYTE_SIZE)));

      //Index to correct element position
      if (size > 1) {
        instrs.add(new ADD(false, target, target, AddrMode.buildReg(sndReg), AddrMode.buildImmWithLSL(size / 2)));
      } else {
        instrs.add(new ADD(false, target, target, AddrMode.buildReg(sndReg)));
      }

      addToCurLabel(instrs);
    }

    // Clean up and load element into target register
    Instr.decDepth();
    BackEndGenerator.addToPreDefFuncs(Label.P_CHECK_ARRAY_BOUNDS);
    addToCurLabel(new LDR(size, Condition.NO_CON, target, AddrMode.buildAddr(target)));
  }
}
