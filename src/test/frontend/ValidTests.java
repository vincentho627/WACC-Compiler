package frontend;

import java.io.IOException;
import org.junit.Ignore;
import org.junit.Test;

import static backend.TestUtilities.executablesFromOurCompilerMatchesReferenceCompiler;
import static frontend.TestUtilities.exitsWith;

public class ValidTests {

  String baseDir = "src/test/examples/valid/";

  @Test
  public void validAdvancedTests() throws IOException {
    exitsWith(baseDir + "advanced/", 0);
  }

  @Test
  public void validArrayTests() throws IOException {
    exitsWith(baseDir + "array/", 0);
  }

  @Test
  public void validBasicExitTests() throws IOException {
    exitsWith(baseDir + "basic/exit/", 0);
  }

  @Test
  public void validBasicSkipTests() throws IOException {
    exitsWith(baseDir + "basic/skip/", 0);
  }

  @Test
  public void validExprTests() throws IOException {
    exitsWith(baseDir + "expressions/", 0);
  }

  @Test
  public void validNestedFuncTest() throws IOException {
    exitsWith(baseDir + "function/nested_functions/", 0);
  }

  @Test
  public void validSimpleFuncTest() throws IOException {
    exitsWith(baseDir + "function/simple_functions/", 0);
  }

  @Test
  public void validIfTest() throws IOException {
    exitsWith(baseDir + "if/", 0);
  }

  @Test
  public void validIOTest() throws IOException {
    exitsWith(baseDir + "IO/", 0);
  }

  @Test
  public void validPairsTest() throws IOException {
    exitsWith(baseDir + "pairs/", 0);
  }

  @Test
  public void validRuntimeErrTest() throws IOException {
    exitsWith(baseDir + "runtimeErr/", 0);
  }

  @Test
  public void validScopeTest() throws IOException {
    exitsWith(baseDir + "scope/", 0);
  }

  @Test
  public void validSequenceTest() throws IOException {
    exitsWith(baseDir + "sequence/", 0);
  }

  @Test
  public void validVariablesTest() throws IOException {
    exitsWith(baseDir + "variables/", 0);
  }

  @Test
  public void validWhileTest() throws IOException {
    exitsWith(baseDir + "while/", 0);
  }
}
