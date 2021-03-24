package frontend;

import frontend.abstractsyntaxtree.AST;
import frontend.abstractsyntaxtree.Node;
import frontend.abstractsyntaxtree.statements.SequenceAST;
import frontend.abstractsyntaxtree.statements.VarDecAST;
import frontend.symboltable.*;

import java.io.IOException;
import org.junit.Test;

public class FullPairTypeTests {

  String testDir = TestUtilities.EXT_VALID_DIR + "fullPairTypes/";

  // Creates a simple pair of type int and bool
  @Test
  public void validSimplePair() throws IOException {
    AST ast =
        TestUtilities.buildAST(testDir + "simplePair.wacc");
    VarDecAST varDecAST = (VarDecAST) ast.getStatAST();

    TypeID type = varDecAST.getDecType();
    assert (type instanceof PairID);
    PairID pairType = (PairID) type;
    assert (pairType.getFstType() instanceof IntID);
    assert (pairType.getSndType() instanceof BoolID);

    TypeID rhsType = varDecAST.getAssignRHS().getIdentifier().getType();
    assert (rhsType instanceof PairID);
    PairID pairID = (PairID) rhsType;
    assert (pairID.getFstType() instanceof IntID);
    assert (pairID.getSndType() instanceof BoolID);
  }

  // Creates a simple pair of type int and bool
	@Test
  public void validNestedPair() throws IOException {
    AST ast =
        TestUtilities.buildAST(testDir + "nestedPair.wacc");
    Node statAST = ast.getStatAST();
    assert (statAST instanceof SequenceAST);
    VarDecAST varDecAST = (VarDecAST) ((SequenceAST) statAST).getStatements().get(1);

    TypeID type = varDecAST.getDecType();
    assert (type instanceof PairID);

    //LHS : Check outer pair
    PairID pairType = (PairID) type;
    TypeID fstTypeLHS = pairType.getFstType();
    TypeID sndTypeLHS = pairType.getSndType();
    assert (fstTypeLHS instanceof PairID);
    assert (sndTypeLHS instanceof PairID);

    // LHS : Check inner pair
    PairID fstPairLHS = (PairID) fstTypeLHS.getType();
    PairID sndPairLHS = (PairID) sndTypeLHS.getType();
    assert (fstPairLHS.getFstType() instanceof IntID);
    assert (fstPairLHS.getSndType() instanceof BoolID);
    assert (sndPairLHS.getFstType() instanceof CharID);
    assert (sndPairLHS.getSndType() instanceof StringID);

    // RHS : Check outer pair
    TypeID rhsType = varDecAST.getAssignRHS().getIdentifier().getType();
    assert (rhsType instanceof PairID);
    PairID pairRHS = (PairID) rhsType;
    TypeID fstTypeRHS = pairRHS.getFstType();
    TypeID sndTypeRHS = pairRHS.getSndType();
    assert (fstTypeRHS instanceof PairID);
    assert (sndTypeRHS instanceof PairID);

    // RHS : Check inner pair
    PairID fstPairRHS = (PairID) fstTypeRHS.getType();
    PairID sndPairRHS = (PairID) sndTypeRHS.getType();
    assert (fstPairRHS.getFstType() instanceof IntID);
    assert (fstPairRHS.getSndType() instanceof BoolID);
    assert (sndPairRHS.getFstType() instanceof CharID);
    assert (sndPairRHS.getSndType() instanceof StringID);
  }
}
