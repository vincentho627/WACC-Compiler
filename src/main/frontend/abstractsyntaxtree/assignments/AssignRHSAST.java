package frontend.abstractsyntaxtree.assignments;

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
import frontend.abstractsyntaxtree.expressions.IdentExprAST;
import frontend.abstractsyntaxtree.pairs.PairElemAST;
import frontend.symboltable.Identifier;
import frontend.symboltable.PairID;
import frontend.symboltable.SymbolTable;
import frontend.symboltable.TypeID;

import frontend.symboltable.VarID;
import java.util.List;

import static backend.Utils.dynamicArrayCheck;
import static backend.Utils.dynamicPairCheck;
import static backend.Utils.dynamicPairElemCheck;
import static backend.Utils.dynamicTypeCheckIfNeeded;
import static backend.instructions.Instr.WORD_SIZE;
import static backend.instructions.Instr.addToCurLabel;

public class AssignRHSAST extends Node {

  protected final SymbolTable symtab;
  protected List<Node> children;
  private final boolean isNewpair;
  // For dynamic variables
  private boolean lhsIsDynamic;
  private int dynamicTypeNumber;
  private int fstType;
  private int sndType;
  private int arrayType;


  public AssignRHSAST(Identifier identifier, SymbolTable symtab,
      List<Node> children, boolean isNewpair) {
    super(identifier);
    this.symtab = symtab;
    this.children = children;
    this.isNewpair = isNewpair;
    this.arrayType = 0;
  }

  public AssignRHSAST(Identifier identifier, SymbolTable symtab) {
    super(identifier);
    this.symtab = symtab;
    this.isNewpair = false;
  }

  public void setDynamicTypeNumber(int dynamicTypeNumber) {
    this.dynamicTypeNumber = dynamicTypeNumber;
  }

  public void setFstType(int fstType) {
    this.fstType = fstType;
  }

  public void setSndType(int sndType) {
    this.sndType = sndType;
  }

  public void setLhsIsDynamic() {
    this.lhsIsDynamic = true;
  }

  public void setArrayType(int arrayType) {
    this.arrayType = arrayType;
  }

  @Override
  public void check() {
  }

  @Override
  public void toAssembly() {
    if (identifier instanceof PairID) {
      // We only malloc if it's newpair
      // malloc pair struct
      if (isNewpair) {
        // Pair has size 8 to hold 2 pointers
        addToCurLabel(new LDR(Instr.R0, AddrMode.buildVal(Instr.PAIR_SIZE)));
        addToCurLabel(new BRANCH(true, Condition.NO_CON, Label.MALLOC));
        addToCurLabel(new MOV(Condition.NO_CON, Instr.R4, AddrMode.buildReg(Instr.R0)));
        // Need to increment register as we using R4
        Instr.incDepth();
      }

      // First pair elem
      Node fstChild = children.get(0);
      fstChild.toAssembly();
      if (isNewpair) {
        Instr.decDepth();
        TypeID child_1 = fstChild.getIdentifier().getType();
        addToCurLabel(new LDR(Instr.R0, AddrMode.buildVal(child_1.getBytes())));

        // Malloc cause its a new pair
        addToCurLabel(new BRANCH(true, Condition.NO_CON, Label.MALLOC));
        addToCurLabel(new STR(child_1.getBytes(), Condition.NO_CON, Instr.R5,
            AddrMode.buildAddr(Instr.R0)));
        addToCurLabel(new STR(Instr.R0, AddrMode.buildAddr(Instr.R4)));

        // Store typenumber of snd type at offset 9 (for dynamic typing)
        Identifier fstType = fstChild.getIdentifier();
        addToCurLabel(new MOV(Condition.NO_CON, Instr.R5, AddrMode.buildImm(
            Utils.getTypeNumber(fstType instanceof VarID ?
                ((VarID) fstType).getTypeSoFar() : fstType.getType()))));
        addToCurLabel(new STR(Instr.BYTE_SIZE, Condition.NO_CON, Instr.R5,
            AddrMode.buildAddrWithOffset(Instr.R4, Utils.FST_TYPE_NUM)));

        Instr.incDepth();
      }

      // Second pair elem
      // If children is NOT an array of pairs elem
      if (children.size() == 2) {
        Node sndChild = children.get(1);
        sndChild.toAssembly();
        if (isNewpair) {
          Instr.decDepth();
          TypeID child_2 = sndChild.getIdentifier().getType();
          addToCurLabel(
              new LDR(Instr.R0, AddrMode.buildVal(child_2.getBytes())));

          // Malloc cause its a new pair
          addToCurLabel(new BRANCH(true, Condition.NO_CON, Label.MALLOC));
          addToCurLabel(new STR(child_2.getBytes(), Condition.NO_CON, Instr.R5,
              AddrMode.buildAddr(Instr.R0)));
          addToCurLabel(
              new STR(Instr.R0, AddrMode.buildAddrWithOffset(Instr.R4, WORD_SIZE)));

          // Store typenumber of snd type at offset 9 (for dynamic typing)
          Identifier sndType = sndChild.getIdentifier();
          addToCurLabel(new MOV(Condition.NO_CON, Instr.R5, AddrMode.buildImm(
              Utils.getTypeNumber(sndType instanceof VarID ?
                  ((VarID) sndType).getTypeSoFar() : sndType.getType()))));
          addToCurLabel(new STR(Instr.BYTE_SIZE, Condition.NO_CON, Instr.R5,
              AddrMode.buildAddrWithOffset(Instr.R4, Utils.SND_TYPE_NUM)));
        }
      }

    } else {
      // if not newPair
      for (Node expr : children) {
        expr.toAssembly();

        // LHS static type RHS possibly dynamic
        if (!lhsIsDynamic) {
          if (expr instanceof PairElemAST) {
            // PairElem case
            dynamicPairElemCheck(expr, dynamicTypeNumber);
          } else if (fstType > 0 && sndType > 0) {
            // Full pair case
            dynamicPairCheck(expr, fstType, sndType);
          } else if (arrayType > 0) {
            // Array case
            dynamicArrayCheck(expr, arrayType);
          } else {
            // Regular variable case
            dynamicTypeCheckIfNeeded(expr, dynamicTypeNumber);
          }
        }
      }
    }
  }
}
