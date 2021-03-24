package frontend.abstractsyntaxtree.expressions;

import antlr.WaccParser.IdentExprContext;
import backend.instructions.*;
import frontend.abstractsyntaxtree.Node;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.ClassAttributeID;
import frontend.symboltable.Identifier;
import frontend.symboltable.SymbolTable;
import frontend.symboltable.UnknownID;
import java.util.ArrayList;
import java.util.List;

public class IdentExprAST extends Node {

  private final SymbolTable currsymtab;
  private final IdentExprContext ctx;

  public IdentExprAST(SymbolTable currsymtab, IdentExprContext ctx) {
    super();
    this.currsymtab = currsymtab;
    this.ctx = ctx;
  }

  public String getName() {
    return ctx.getText();
  }

  public int getOffset() { return currsymtab.getStackOffset(getName()); }

  @Override
  public void check() {
    String varName = getName();
    Identifier identifier = currsymtab.lookupAll(varName);

    if (identifier == null) { //Unknown variable
      SemanticErrorCollector
          .addVariableUndefined(varName, ctx.getStart().getLine(),
              ctx.getStart().getCharPositionInLine());
      setIdentifier(new UnknownID());
    } else {
      setIdentifier(identifier);
    }
  }

  @Override
  public void toAssembly() {
    List<Instr> instrs = new ArrayList<>();

    // Load from (SP + offset) into target register
    Identifier identifier = currsymtab.lookupAll(getName());
    int offset;

    if (identifier instanceof ClassAttributeID) {
      offset = currsymtab.getStackOffset("object_addr");
      int attributeOffset = currsymtab.getStackOffset(getName());
      String targetReg = Instr.getTargetReg();
      // Storing the instance's address into a register
      Instr.addToCurLabel(
          new LDR(targetReg, AddrMode.buildAddrWithOffset(Instr.SP, offset)));
      // Adding the offset of the attribute to the regeister which holds the
      // instance's address
      if (attributeOffset > 0) {
        Instr.addToCurLabel(new ADD(false, targetReg, targetReg,
            AddrMode.buildImm(attributeOffset)));
      }
      Instr.addToCurLabel(
          new LDR(targetReg, AddrMode.buildAddrWithOffset(targetReg
              , 0)));
    } else {
      // Regular variable (Get from stack into target register)

      offset = currsymtab.getStackOffset(getName());
      Instr loadVar = new LDR(identifier.getType().getBytes(), Condition.NO_CON,
          Instr.getTargetReg(), AddrMode.buildAddrWithOffset(Instr.SP, offset));
      instrs.add(loadVar);

      Instr.addToCurLabel(instrs);
    }
  }
}
