package frontend;

import static frontend.TestUtilities.exitsWith;

import java.io.IOException;
import org.junit.Test;

public class IntLiteralExtensionTests {

  // Made test cases for extension: Binary, Octal and Hexadecimal literals
  @Test
  public void validExtensionIntegerLiteralsTest() throws IOException {
    exitsWith(TestUtilities.EXT_VALID_DIR + "integerLiterals/", 0);
  }

}
