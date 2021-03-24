package backend;

import static backend.TestUtilities.executablesFromOurCompilerMatchesReferenceCompiler;

import java.io.IOException;
import org.junit.Ignore;
import org.junit.Test;


public class ValidTests {

  String baseDir = "src/test/examples/valid/";

  @Ignore
  @Test
  public void validAdvancedTests() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        baseDir + "advanced/");
  }

  @Test
  public void validArrayTests() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        baseDir + "array/");
  }

  @Test
  public void validBasicExitTests() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        baseDir + "basic/exit/");
  }

  @Test
  public void validBasicSkipTests() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        baseDir + "basic/skip/");
  }

  @Test
  public void validExprTests() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        baseDir + "expressions/");
  }

  @Test
  public void validNestedFuncTest() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        baseDir + "function/nested_functions/");
  }
  
  @Test
  public void validSimpleFuncTest() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        baseDir + "function/simple_functions/");
  }
  
  @Test
  public void validIfTest() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(baseDir + "if/");
  }

  @Test
  public void validIOTest() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(baseDir + "IO/");
  }

  @Test
  public void validPairsTest() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        baseDir + "pairs/");
  }

  @Test
  public void validRuntimeErrTest() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        baseDir + "runtimeErr/");
  }

  @Test
  public void validScopeTest() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        baseDir + "scope/");
  }

  @Test
  public void validSequenceTest() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        baseDir + "sequence/");
  }

  
  @Test
  public void validVariablesTest() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        baseDir + "variables/");
  }

  @Test
  public void validWhileTest() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        baseDir + "while/");
  }
}
