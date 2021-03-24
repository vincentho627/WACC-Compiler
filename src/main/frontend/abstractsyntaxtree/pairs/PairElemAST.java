package frontend.abstractsyntaxtree.pairs;

import antlr.WaccParser.PairElemContext;
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
import frontend.abstractsyntaxtree.Utils;
import frontend.abstractsyntaxtree.expressions.ArrayElemAST;
import frontend.abstractsyntaxtree.expressions.IdentExprAST;
import frontend.abstractsyntaxtree.expressions.PairLiterAST;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static backend.instructions.Instr.BYTE_SIZE;
import static backend.instructions.Instr.addToCurLabel;

public class PairElemAST extends Node {

  private final SymbolTable symtab;
  private final Identifier childIdentifier;
  private final boolean first;
  private final Node child;
  private String identName;
  private final PairElemContext ctx;

  public PairElemAST(
      Identifier childIdentifier,
      SymbolTable symtab,
      boolean first,
      Node child,
      PairElemContext ctx) {
    super();
    this.childIdentifier = childIdentifier;
    this.symtab = symtab;
    this.first = first;
    this.child = child;
    this.identName = ctx.getStart().getText();
    this.ctx = ctx;
  }

  public String getName() {
    return identName;
  }

  public boolean isFirst() {
    return first;
  }

  public int getOffset() {
    return symtab.getStackOffset(identName);
  }


  @Override
  public void check() {
    // Checking expr has compatible type
    if (child instanceof IdentExprAST) {
      identName = ((IdentExprAST) child).getName();
      Identifier type = symtab.lookupAll(identName);
      if (!(type instanceof PairID || type instanceof VarID)) {
        addIncompatibleTypeSemanticError();
      }

    } else if (child instanceof ArrayElemAST) {
      if (!(child.getIdentifier().getType() instanceof PairID)) {
        addIncompatibleTypeSemanticError();
      }

    } else if (child instanceof PairLiterAST) {
      SemanticErrorCollector.addIncompatibleType(
          "pair",
          "null",
          identName,
          ctx.getStart().getLine(),
          ctx.getStart().getCharPositionInLine());

    } else {
      addIncompatibleTypeSemanticError();
    }

    // Set identifier
    if (childIdentifier instanceof NullID || childIdentifier instanceof VarID) {
      setIdentifier(childIdentifier);
    } else {
      PairID childIDAsPair =
          childIdentifier instanceof ParamID
              ? ((PairID) (childIdentifier).getType())
              : ((PairID) childIdentifier);
      Identifier childIDPairElem = first ? childIDAsPair.getFstType() : childIDAsPair.getSndType();

      setIdentifier(Objects.requireNonNullElseGet(childIDPairElem, NullID::new));
    }
  }

  private void addIncompatibleTypeSemanticError() {
    SemanticErrorCollector.addIncompatibleType(
        "pair",
        child.getIdentifier().getType().getTypeName(),
        identName,
        ctx.getStart().getLine(),
        ctx.getStart().getCharPositionInLine());
  }

  @Override
  public void toAssembly() {
    List<Instr> instructions = new ArrayList<>();

    // gets the stack offset of the pair variable
    int stackPointerOffset = symtab.getStackOffset(identName);
    instructions.add(new LDR(Instr.R4, AddrMode.buildAddrWithOffset(Instr.SP, stackPointerOffset)));

    // jumps to branch to check if null pointer error
    instructions.add(new MOV(Condition.NO_CON, Instr.R0, AddrMode.buildReg(Instr.R4)));
    BackEndGenerator.addToPreDefFuncs(Label.P_CHECK_NULL_POINTER);
    instructions.add(new BRANCH(true, Condition.NO_CON, Label.P_CHECK_NULL_POINTER));

    TypeID childType = childIdentifier.getType();
    int bytes;

    // If VarID, get the size of its type so far
    if (first) {
      // if its the first elem of pair
      bytes = childType instanceof PairID ?
          ((PairID) childType).getFstType().getBytes() :
          Utils.getSizeFromTypeNumber(((VarID) childType).getTypeSoFar());
      instructions.add(new LDR(Instr.R4, AddrMode.buildAddr(Instr.R4)));
    } else {
      bytes = childType instanceof PairID ?
          ((PairID) childType).getSndType().getBytes() :
          Utils.getSizeFromTypeNumber(((VarID) childType).getTypeSoFar());
      // snd at offset 4 (i.e. 2nd pointer)
      instructions.add(new LDR(Instr.R4,
          AddrMode.buildAddrWithOffset(Instr.R4, Instr.WORD_SIZE)));
    }

    // Load value out of addr
    instructions.add(new LDR(bytes, Condition.NO_CON, Instr.R4,
        AddrMode.buildAddr(Instr.R4)));

    addToCurLabel(instructions);
  }
}
