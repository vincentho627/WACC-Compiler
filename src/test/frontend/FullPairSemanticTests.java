package frontend;

import static frontend.TestUtilities.singleExitsWith;

import java.io.IOException;
import org.junit.Test;

public class FullPairSemanticTests {

  String testDir = "src/test/examples/custom/";
  String semanticErrDir = testDir + "invalid/semanticErr/fullPairTypes/";
  String validDir = testDir + "valid/fullPairTypes/";

  // ****** INVALID TESTS ******

  @Test
  public void invalidAssignmentTest() throws IOException {
    singleExitsWith(semanticErrDir + "invalidAssign.wacc", 200);
  }

  @Test
  public void nullDecAndBadReassignTest() throws IOException {
    singleExitsWith(semanticErrDir + "nullDecBadReassign.wacc", 200);
  }

  @Test
  public void assignNullBadReassignTest() throws IOException {
    singleExitsWith(semanticErrDir + "assignNullBadReassign.wacc", 200);
  }

  @Test
  public void nestedNullBadReassignTest() throws IOException {
    singleExitsWith(semanticErrDir + "nestedNullBadReassign.wacc", 200);
  }

  @Test
  public void badRepeatNewPairTest() throws IOException {
    singleExitsWith(semanticErrDir + "badRepeatNewPair.wacc", 200);
  }

  @Test
  public void deepNestedPairsTest() throws IOException {
    singleExitsWith(semanticErrDir + "deepNestedPairs.wacc", 200);
  }

  // ****** VALID TESTS ******

  @Test
  public void nullDecAndCorrectReassignTest() throws IOException {
    singleExitsWith(validDir + "nullDec.wacc", 0);
  }

  @Test
  public void assignNullReassignTest() throws IOException {
    singleExitsWith(validDir + "assignNullReassign.wacc", 0);
  }

  @Test
  public void nestedNullReassignTest() throws IOException {
    singleExitsWith(validDir + "nestedNullReassign.wacc", 0);
  }

  @Test
  public void repeatNewPairTest() throws IOException {
    singleExitsWith(validDir  + "repeatNewPair.wacc", 0);
  }
}
