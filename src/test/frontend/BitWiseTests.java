package frontend;

import static frontend.TestUtilities.exitsWith;

import java.io.IOException;
import org.junit.Test;

public class BitWiseTests {

  // Made test cases for extension: Bit-wise Operators '&', '|' and '~'
  @Test
  public void invalidSyntaxExtensionBitwiseTest() throws IOException {
    exitsWith(TestUtilities.EXT_SYNTAX_ERR_DIR + "bitwise/", 100);
  }

  @Test
  public void invalidSemanticExtensionBitwiseTests() throws IOException {
    exitsWith(TestUtilities.EXT_SEMANTIC_ERR_DIR + "bitwise/", 200);
  }

  @Test
  public void validExtensionBitwiseTest() throws IOException {
    exitsWith(TestUtilities.EXT_VALID_DIR + "bitwise/", 0);
  }

}
