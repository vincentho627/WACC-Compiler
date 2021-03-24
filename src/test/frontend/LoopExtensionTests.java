package frontend;

import static frontend.TestUtilities.exitsWith;

import java.io.IOException;
import org.junit.Test;

public class LoopExtensionTests {

  // Made test cases for extension: Additional Loop Control Statements
  @Test
  public void invalidSemanticExtensionForLoopTests() throws IOException {
    exitsWith(TestUtilities.EXT_SEMANTIC_ERR_DIR + "forLoop/", 200);
  }

  // Made test cases for extension: Additional Loop Control Statements
  @Test
  public void validExtensionForLoopTest() throws IOException {
    exitsWith(TestUtilities.EXT_VALID_DIR + "forLoop/", 0);
  }

}
