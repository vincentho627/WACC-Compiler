package frontend.abstractsyntaxtree.array;

import antlr.WaccParser.ArrayLiterContext;
import backend.instructions.AddrMode;
import backend.instructions.BRANCH;
import backend.instructions.Condition;
import backend.instructions.Instr;
import backend.instructions.LDR;
import backend.instructions.Label;
import backend.instructions.MOV;
import backend.instructions.STR;
import frontend.abstractsyntaxtree.Node;
import frontend.abstractsyntaxtree.Utils;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.Identifier;
import frontend.symboltable.SymbolTable;
import frontend.symboltable.TypeID;
import java.util.List;

import static backend.instructions.Instr.BYTE_SIZE;
import static backend.instructions.Instr.WORD_SIZE;
import static backend.instructions.Instr.addToCurLabel;

public class ArrayLiterAST extends Node {

  private final List<Node> children;
  private final ArrayLiterContext ctx;
  private final SymbolTable symtab;

  private TypeID childrenType;

  public ArrayLiterAST(
      SymbolTable symtab, Identifier identifier, List<Node> children,
      ArrayLiterContext ctx) {
    super(identifier);
    this.children = children;
    this.ctx = ctx;
    this.symtab = symtab;
  }

  @Override
  public void check() {
    if (!children.isEmpty()) {
      childrenType = children.get(0).getIdentifier().getType();
      String type = children.get(0).getIdentifier().getType().getTypeName();
      int line = ctx.getStart().getLine();
      for (int i = 1; i < children.size(); i++) {
        Node child = children.get(i);
        String childType = child.getIdentifier().getType().getTypeName();
        if (!childType.equals(type)) {
          SemanticErrorCollector.addArrayInconsistentTypes(
              line, ctx.expr(i).getStart().getCharPositionInLine(), i, type,
              childType);
        }
      }
    }
  }

  @Override
  public void toAssembly() {
    String lengthOfArray = "" + children.size();
    int bytesNeeded = 0;

    // if array is empty, then the bytesNeeded will be 0
    if (!children.isEmpty()) {
      Identifier childrenType = children.get(0).getIdentifier();
      bytesNeeded = childrenType.getType().getBytes();
    }

    String byteOfArray = Integer.toString(WORD_SIZE + BYTE_SIZE + children.size() * bytesNeeded);
    addToCurLabel(new LDR(Instr.R0, AddrMode.buildVal(byteOfArray)));

    // Add malloc branch to allocate memory
    addToCurLabel(new BRANCH(true, Condition.NO_CON, Label.MALLOC));

    // Move r4 to r0
    addToCurLabel(new MOV(Condition.NO_CON, Instr.R4, AddrMode.buildReg(Instr.R0)));

    // Go through expressions in array to add to assembly code
    for (int i = 0; i < children.size(); i++) {
      Node curr_expr = children.get(i);
      Identifier curr_ident = curr_expr.getIdentifier();

      String sndReg = Instr.incDepth();
      curr_expr.toAssembly();
      String fstReg = Instr.decDepth();

      // storing the offsets of the expressions (skip WORD_SIZE + BYTE_SIZE bytes which stores length and typeNumber)
      int offset = i * bytesNeeded + WORD_SIZE + BYTE_SIZE;

      addToCurLabel(
          new STR(curr_ident.getType().getBytes(), Condition.NO_CON, sndReg,
              AddrMode.buildAddrWithOffset(fstReg, offset)));
    }

    // stores type number
    addToCurLabel(new LDR(Instr.R5, AddrMode.buildVal(Utils.getTypeNumber(childrenType))));
    addToCurLabel(new STR(BYTE_SIZE, Condition.NO_CON, Instr.R5, AddrMode.buildAddrWithOffset(Instr.R4, WORD_SIZE)));

    // stores length of array into registers
    addToCurLabel(new LDR(Instr.R5, AddrMode.buildVal(lengthOfArray)));
    addToCurLabel(new STR(Instr.R5, AddrMode.buildAddr(Instr.R4)));
  }
}
