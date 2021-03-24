package backend;

import static frontend.TestUtilities.getTestNames;
import static frontend.TestUtilities.getFolderNames;
import static backend.TestUtilities.getTextFilePath;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GenerateExpectedValues {

  static String validFolderPath = "src/test/examples/expectedOutputs";

  /**
   * Iterates through the examples/valid folder and makes all test directories with their expected
   * test outputs in txt.
   */
  public static void storeExpectedValues(String folderPath) throws IOException {
    Files.createDirectories(Paths.get(validFolderPath));

    List<String> folderNames = getFolderNames(folderPath);

    for (String folder : folderNames) {
      String textTestFolderPath = getTextFilePath(folderPath, folder);
      String folderTestPath = folderPath + folder;

      Files.createDirectories(Paths.get(textTestFolderPath));

      textTestFolderPath += "/";
      List<String> subFolderNames = getFolderNames(folderTestPath);
      List<String> names = getTestNames(folderTestPath);

      if (!subFolderNames.isEmpty()) {
        storeExpectedValues(folderTestPath + "/");
      }

      for (String name : names) {
        System.out.println("Getting expected output from test " + name);
        String filepath = folderTestPath + "/" + name;
        List<String> expectedValues = generateExpectedValues(filepath);
        String txtFilepath = getTextFilePath(textTestFolderPath, name);
        writeInFile(txtFilepath, expectedValues);
      }
    }
  }

  /**
   * Writes expected values into the specified file path.
   */
  private static void writeInFile(String filepath, List<String> expectedValues)
      throws IOException {
    FileWriter txtWriter = new FileWriter(filepath, false);
    for (String expectedValue : expectedValues) {
      txtWriter.write(expectedValue);
      txtWriter.write('\n');
    }
    txtWriter.close();
  }

  /**
   * Generates expected output from filePath.
   */
  public static List<String> generateExpectedValues(String filePath)
      throws IOException {
    // Get standard output stream from reference compiler and emulator
    ProcessBuilder builder = new ProcessBuilder();
    builder.command("./refCompile", "-x", filePath);
    String output = TestUtilities.getOutputFromProcess(builder);

    String[] separatedOutputByLine = output.split("\n");
    boolean nextLineIsOutput = false;
    List<String> expectedStdOuts = new ArrayList<>();

    for (String line : separatedOutputByLine) {
      // Next few lines might be expectedStdOuts
      if (nextLineIsOutput) {
        if (line.equals(
            "===========================================================")) {
          // End of expectedStdOuts
          return expectedStdOuts;
        } else {
          // Continue adding StdOuts
          if (expectedStdOuts.isEmpty() && line.isEmpty()) {
            continue;
          } else {
            line = line.replaceAll("0x[a-zA-Z0-9]+", "0xaaaaaaaa");
            expectedStdOuts.add(line);
          }
        }
      } else {

        // Toggle nextLineIsOutput to guard if the next few lines are StdOuts
        if (line.equals(
            "===========================================================")) {
          nextLineIsOutput = true;
        }
      }

    }

    return expectedStdOuts;
  }

  /**
   * Run this to generate the src/test/examples/expectedOutput directory for tests. This is not to
   * be an automated process.
   */
  public static void main(String[] args) throws IOException {
    storeExpectedValues("src/test/examples/valid/");
  }
}
