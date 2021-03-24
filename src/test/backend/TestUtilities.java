package backend;

import static frontend.TestUtilities.getFolderNames;
import static frontend.TestUtilities.getTestNames;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class TestUtilities {

  public static String EXT_VALID_DIR =
      "src/test/examples/custom/valid/";

  /**
   * Gets text file path of .wacc file
   */
  static String getTextFilePath(String folderPath, String name) {
    name = name.replace(".wacc", ".txt");
    folderPath = folderPath.replace("valid", "expectedOutputs");
    return folderPath + name;
  }

  /**
   * Checks that the example compiles with a certain exit code.
   */
  public static void executablesFromOurCompilerMatchesReferenceCompiler(
      String folderPath) throws IOException {
    List<String> names = getTestNames(folderPath);
    List<String> folderNames = getFolderNames(folderPath);

    for (String folder : folderNames) {
      executablesFromOurCompilerMatchesReferenceCompiler(
          folderPath + folder + "/");
    }

    for (String name : names) {
      String sourceFilePath = folderPath + name;
      String textFilePath = getTextFilePath(folderPath, name);
      try {
        assertTrue(
            valuesFromOurCompilerMatchesReferenceCompiler(sourceFilePath,
                textFilePath));
      } catch (AssertionError e) {
        StringBuilder   errorMsg = new StringBuilder(name)
            .append(": Output did not match with reference compiler.\n");
        errorMsg.append("Expected output: ")
            .append(getReferenceCompilerStdOut(textFilePath)).append("\n");
        errorMsg.append("Actual output: ")
            .append(getOurCompilerStdOut(sourceFilePath)).append("\n");
        fail(errorMsg.toString());
      } catch (IOException e) {
        fail(e.toString());
      }
    }
  }

  /**
   * Checks for a given folder, if the .wacc files return with a correct exit code
   */
  public static void exitCodeFromOurCompilerExitsWithCorrectCode(
      String folderPath, int code) throws IOException {
    List<String> names = getTestNames(folderPath);
    List<String> folderNames = getFolderNames(folderPath);

    for (String folder : folderNames) {
      executablesFromOurCompilerMatchesReferenceCompiler(
          folderPath + folder + "/");
    }

    for (String name : names) {
      String sourceFilePath = folderPath + name;
      String textFilePath = getTextFilePath(folderPath, name);
      try {
        assertTrue(
            exitCodeMatches(sourceFilePath, code));
      } catch (AssertionError e) {
        StringBuilder errorMsg = new StringBuilder(name)
            .append(": Exit code isn't " + code + ".\n");
        fail(errorMsg.toString());
      } catch (IOException e) {
        fail(e.toString());
      }
    }
  }

  /**
   * Returns if the exit codes match
   */
  private static boolean exitCodeMatches(String filePath, int code) throws IOException {
    int actualCode = getOurCompilerExitCode(filePath);
    return actualCode == code;
  }

  /**
   * Returns the standard output from a process containing terminal commands.
   */
  static String getOutputFromProcess(ProcessBuilder builder)
      throws IOException {
    // Start process
    Process process = builder.start();
    BufferedReader reader = new BufferedReader(
        new InputStreamReader(process.getInputStream()));
    PrintStream out = new PrintStream(
        new BufferedOutputStream(process.getOutputStream()));
    out.close();
    try {
      process.waitFor();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Get output from reference compiler
    StringBuilder stringBuilder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      stringBuilder.append(line);
      stringBuilder.append(System.getProperty("line.separator"));
    }

    return stringBuilder.toString();
  }

  /**
   * Assembles a .wacc file and returns the file path to the resulting .s file.
   */
  private static String assembleFileWithOurCompiler(String filePath)
      throws IOException {
    // Bash command to use our compiler
    ProcessBuilder builder = new ProcessBuilder();
    builder.command("./compile", filePath);

    getOutputFromProcess(builder);

    String[] splitPaths = filePath.split("/");
    filePath = splitPaths[splitPaths.length - 1];
    return filePath.replace(".wacc", ".s");
  }

  /**
   * Compiles and emulates a .wacc file, returning the exit code from the
   * resulting executable compiled by our compiler.
   */
  private static int getOurCompilerExitCode(String filePath) throws IOException {
    // Get assembly file path
    String assFilePath = assembleFileWithOurCompiler(filePath);
    String exeFilePath = assFilePath.replace(".s", ".exe");

    // Get standard output stream from reference emulator
    ProcessBuilder builder = new ProcessBuilder();
    String os = System.getProperty("os.name").toLowerCase();

    if (os.contains("mac")) {
      // Uses ref emulate to send HTTP request to retrieve assembly outputs
      // If you want to run the pipeline tests, build Docker image from Dockerfile and run the image
      builder.command("./refEmulate", assFilePath);

      // Gets output from command line
      String output = getOutputFromProcess(builder);
      String[] splitOutput = output.split("\n");

      for (String line : splitOutput) {
        if (line.contains("The exit code is:")) {
          // retrieve exit code from program
          String[] exitCodeSplit = line.split(": ");
          String exitCode = exitCodeSplit[exitCodeSplit.length - 1];
          exitCode = exitCode.replace(".", "");
          return Integer.parseInt(exitCode);
        }
      }
    } else {
      // For Linux and CI/CD, uses Linux GCC and QEMU to retrieve assembly output
      builder
          .command("arm-linux-gnueabi-gcc", "-o", exeFilePath,
              "-mcpu=arm1176jzf-s", "-mtune=arm1176jzf-s", assFilePath);
      Process process = builder.start();
      try {
        process.waitFor();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      builder.command("qemu-arm", "-L", "/usr/arm-linux-gnueabi/", exeFilePath);
      Process exec = builder.start();
      try {
        exec.waitFor();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return exec.exitValue();
    }

    // shouldn't get to this point
    return 0;
  }

  /**
   * Compiles and emulates a .wacc file, returns the filtered standard output
   * stream from the resulting executable compiled by our compiler.
   */
  private static List<String> getOurCompilerStdOut(String filePath)
      throws IOException {
    // Get assembly file path
    String assFilePath = assembleFileWithOurCompiler(filePath);
    String exeFilePath = assFilePath.replace(".s", ".exe");

    // Get standard output stream from reference emulator
    ProcessBuilder builder = new ProcessBuilder();
    String os = System.getProperty("os.name").toLowerCase();

    if (os.contains("mac")) {
      // Uses ref emulate to send HTTP request to retrieve assembly outputs
      // If you want to run the pipeline tests, build Docker image from Dockerfile and run the image
      builder.command("./refEmulate", assFilePath);

      // Gets output from command line
      String output = getOutputFromProcess(builder);
      String[] splitOutput = output.split("\n");

      List<String> actualStdOuts = new ArrayList<>();
      boolean nextLineIsOutput = false;

      for (String line : splitOutput) {
        // Checks if the next line is unwanted output
        if (nextLineIsOutput) {
          if (line.equals(
              "---------------------------------------------------------------")) {
            return actualStdOuts;
          } else {
            if (actualStdOuts.isEmpty() && line.isEmpty() || line.equals("\u0000")) {
              continue;
            } else {
              line = line.replaceAll("0x[a-zA-Z0-9]+", "0xaaaaaaaa");
              actualStdOuts.add(line);
            }
          }
        }

        // Checks if the next line is wanted output from the compiled program
        if (line.contains("Emulation Output")) {
          nextLineIsOutput = true;
        }
      }
      return actualStdOuts;

    } else {
      // For Linux and CI/CD, uses Linux GCC and QEMU to retrieve assembly output
      builder
          .command("arm-linux-gnueabi-gcc", "-o", exeFilePath,
              "-mcpu=arm1176jzf-s", "-mtune=arm1176jzf-s", assFilePath);
      Process process = builder.start();
      try {
        process.waitFor();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      builder.command("qemu-arm", "-L", "/usr/arm-linux-gnueabi/", exeFilePath);
      String output = getOutputFromProcess(builder);
      output = output.replaceAll("0x[a-zA-Z0-9]+", "0xaaaaaaaa");
      String[] splitOutput =
          output.equals("") ? new String[0] : Arrays.stream(output.split("\n")).filter(s -> !s.equals("\u0000")).toArray(String[]::new);
      return Arrays.asList(splitOutput.clone());
    }
  }

  /**
   * Retrieves cached expected values of the .wacc file and returns the filtered
   * standard output stream stored in the file.
   */
  private static List<String> getReferenceCompilerStdOut(String filePath)
      throws IOException {
    // Get standard output stream from reference compiler and emulator
    BufferedReader br = new BufferedReader(new FileReader(filePath));
    List<String> expectedValues = new ArrayList<>();

    String line;
    while ((line = br.readLine()) != null) {
      expectedValues.add(line);
    }

    return expectedValues;
  }

  /**
   * Separately compiles a .wacc file through our compiler and the reference
   * compiler respectively, emulates two resulting executables through the
   * reference emulator, checks if the standard output from the executables are
   * equal.
   */
  private static boolean valuesFromOurCompilerMatchesReferenceCompiler(
      String sourceFilePath, String textFilePath) throws IOException {
    List<String> actualOutput = getOurCompilerStdOut(sourceFilePath);
    int actualHash = actualOutput.hashCode();
    List<String> expectedOutput = getReferenceCompilerStdOut(
        textFilePath);
    int expectedHash = expectedOutput.hashCode();
    if (actualOutput.size() != expectedOutput.size()) {
      return false;
    }
    return actualHash == expectedHash;
  }


  public static void main(String[] args) throws IOException {
    System.out.println(exitCodeMatches("test.wacc", 255));
  }
}
