package backend;

import static backend.TestUtilities.executablesFromOurCompilerMatchesReferenceCompiler;
import static backend.TestUtilities.exitCodeFromOurCompilerExitsWithCorrectCode;

import java.io.IOException;
import org.junit.Test;

public class ValidExtensionTests {
  // Made test cases for extension: Bit-wise Operators '&', '|' and '~'
  @Test
  public void validExtensionBitwiseTest() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        TestUtilities.EXT_VALID_DIR + "bitwise/");
  }

  // Made test cases for extension: Binary, Octal and Hexadecimal literals
  @Test
  public void validExtensionIntegerLiteralTest() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        TestUtilities.EXT_VALID_DIR + "integerLiterals/");
  }

  // Made test cases for extension: Additional Loop Control Statements
  @Test
  public void validExtensionForLoopTest() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        TestUtilities.EXT_VALID_DIR + "forLoop/");
  }

  @Test
  public void validExtensionDoWhileTest() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        TestUtilities.EXT_VALID_DIR + "doWhile/");
  }

  // Made test cases for extension: Classes
  @Test
  public void validExtensionForClassesTest() throws IOException {
    executablesFromOurCompilerMatchesReferenceCompiler(
        TestUtilities.EXT_VALID_DIR + "class/");
  }

  // Made test cases for extension: Classes with dynamic variables (var)
  @Test
  public void validExtensionForClassesRunTimeErrTest() throws IOException {
    exitCodeFromOurCompilerExitsWithCorrectCode(
        TestUtilities.EXT_VALID_DIR + "/runtimeErr/class/", 255);
  }

  @Test
  public void validExtensionForDynamicVariableTest() throws IOException {
    exitCodeFromOurCompilerExitsWithCorrectCode(
        TestUtilities.EXT_VALID_DIR + "/dynamicVariables/", 0);
    executablesFromOurCompilerMatchesReferenceCompiler(
        TestUtilities.EXT_VALID_DIR + "/dynamicVariables/");
  }

  @Test
  public void validExtensionForDynamicVariableRunTimeErrTest() throws IOException {
    exitCodeFromOurCompilerExitsWithCorrectCode(
        TestUtilities.EXT_VALID_DIR + "/runtimeErr/dynamicVariables/", 255);
  }
}
