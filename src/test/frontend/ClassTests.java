package frontend;

import static frontend.TestUtilities.exitsWith;

import java.io.IOException;
import org.junit.Test;

public class ClassTests {

  @Test
  public void invalidSyntaxClassTest() throws IOException {
    exitsWith(TestUtilities.EXT_SYNTAX_ERR_DIR + "class/", 100);
  }

  @Test
  public void invalidSemanticClassTests() throws IOException {
    exitsWith(TestUtilities.EXT_SEMANTIC_ERR_DIR + "class/", 200);
  }

  @Test
  public void validClassTests() throws IOException {
    exitsWith(TestUtilities.EXT_VALID_DIR + "class/", 0);
  }
}
