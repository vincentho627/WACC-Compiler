package frontend;

import org.junit.Test;

import java.io.*;

import static frontend.TestUtilities.exitsWith;

public class InvalidSyntaxTests {

  String baseDir = "src/test/examples/invalid/syntaxErr/";

  @Test
  public void invalidSyntaxArrayTest() throws IOException {
    exitsWith(baseDir + "array/", 100);
  }

  @Test
  public void invalidSyntaxBasicTest() throws IOException {
    exitsWith(baseDir + "basic/", 100);
  }

  @Test
  public void invalidSyntaxExprTest() throws IOException {
    exitsWith(baseDir + "expressions/", 100);
  }

  @Test
  public void invalidSyntaxFunctionTest() throws IOException {
    exitsWith(baseDir + "function/", 100);
  }

  @Test
  public void invalidSyntaxIfTest() throws IOException {
    exitsWith(baseDir + "if/", 100);
  }

  @Test
  public void invalidSyntaxPairsTest() throws IOException {
    exitsWith(baseDir + "pairs/", 100);
  }

  @Test
  public void invalidSyntaxPrintTest() throws IOException {
    exitsWith(baseDir + "print/", 100);
  }

  @Test
  public void invalidSyntaxSequenceTest() throws IOException {
    exitsWith(baseDir + "sequence/", 100);
  }

  @Test
  public void invalidSyntaxVariablesTest() throws IOException {
    exitsWith(baseDir + "variables/", 100);
  }

  @Test
  public void invalidSyntaxWhileTest() throws IOException {
    exitsWith(baseDir + "while/", 100);
  }
}
