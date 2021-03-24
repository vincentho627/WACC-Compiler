package frontend;

import org.junit.Test;

import java.io.IOException;

import static frontend.TestUtilities.exitsWith;

public class InvalidSemanticTests {

  String baseDir = "src/test/examples/invalid/semanticErr/";

  @Test
  public void invalidSemanticExitTests() throws IOException {
    exitsWith(baseDir + "exit/", 200);
  }

  @Test
  public void invalidSemanticExprTests() throws IOException {
    exitsWith(baseDir + "expressions/", 200);
  }


  @Test
  public void invalidSemanticFunctionTests() throws IOException {
    exitsWith(baseDir + "function/", 200);
  }

  @Test
  public void invalidSemanticIfTests() throws IOException {
    exitsWith(baseDir + "if/", 200);
  }

  @Test
  public void invalidSemanticIOTests() throws IOException {
    exitsWith(baseDir + "IO/", 200);
  }

  @Test
  public void invalidSemanticMultipleTests() throws IOException {
    exitsWith(baseDir + "multiple/", 200);
  }

  @Test
  public void invalidSemanticPairsTests() throws IOException {
    exitsWith(baseDir + "pairs/", 200);
  }

  @Test
  public void invalidSemanticPrintTests() throws IOException {
    exitsWith(baseDir + "print/", 200);
  }

  @Test
  public void invalidSemanticReadTests() throws IOException {
    exitsWith(baseDir + "read/", 200);
  }

  @Test
  public void invalidSemanticScopeTests() throws IOException {
    exitsWith(baseDir + "scope/", 200);
  }

  @Test
  public void invalidSemanticVariablesTests() throws IOException {
    exitsWith(baseDir + "variables/", 200);
  }

  @Test
  public void invalidSemanticWhileTests() throws IOException {
    exitsWith(baseDir + "while/", 200);
  }
}
