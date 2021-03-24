package frontend;

import antlr.WaccLexer;
import antlr.WaccParser;
import frontend.abstractsyntaxtree.AST;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestUtilities {

  public static String EXT_SEMANTIC_ERR_DIR =
      "src/test/examples/custom/invalid/semanticErr/";
  public static String EXT_SYNTAX_ERR_DIR =
      "src/test/examples/custom/invalid/syntaxErr/";
  public static String EXT_VALID_DIR =
      "src/test/examples/custom/valid/";

  public static FrontEndAnalyser buildFrontEndAnalyser(String sourceFilePath)
      throws IOException {
    CharStream source = CharStreams
        .fromStream(new FileInputStream(sourceFilePath));
    return new FrontEndAnalyser(source);
  }

  /**
   * Returns a list of all the file names of example .wacc files from a given
   * directory, to be used for tests.
   */
  public static List<String> getTestNames(String sourcesFolderPath)
      throws IOException {
    return Files.list(Paths.get(sourcesFolderPath))
        .filter(Files::isRegularFile)
        .map(p -> p.getFileName().toString())
        .filter(f -> f.endsWith(".wacc"))
        .collect(Collectors.toList());
  }

  /**
   * Returns a list of all the folder names
   */
  public static List<String> getFolderNames(String folderPath) throws IOException {
    return Files.list(Paths.get(folderPath))
        .filter(Files::isDirectory)
        .map(p -> p.getFileName().toString())
        .collect(Collectors.toList());
  }

  /**
   * Checks that the example compiles with a certain exit code.
   */
  public static void exitsWith(String folderPath, int exitCode)
      throws IOException {
    List<String> names = getTestNames(folderPath);
    for (String name : names) {
      String sourceFilePath = folderPath + name;
      // Redirects standard output to prevent clogging up the CI pipeline 
      OutputStream os = new ByteArrayOutputStream();
      System.setOut(new PrintStream(os));
      FrontEndAnalyser analyser = buildFrontEndAnalyser(sourceFilePath);
      try {
        assertEquals(analyser.run(), exitCode);
      } catch (AssertionError e) {
        fail("Test " + name + " did not exit with exit code " + exitCode);
      }
    }
  }

  public static void singleExitsWith(String filePath, int exitCode)
      throws IOException {
    OutputStream os = new ByteArrayOutputStream();
    System.setOut(new PrintStream(os));
    FrontEndAnalyser analyser = buildFrontEndAnalyser(filePath);
    try {
      assertEquals(analyser.run(), exitCode);
    } catch (AssertionError e) {
      String[] filePathSplit = filePath.split("/");
      fail("Test " + filePathSplit[filePathSplit.length - 1] + " did not exit with exit code " + exitCode);
    }
  }

  /**
   * Only call on syntactically valid programs. Used to test semantics.
   */

  public static AST buildAST(String filePath) throws IOException {
    CharStream input = CharStreams
        .fromStream(new FileInputStream(filePath));

    // Create a lexer that reads from the input stream
    WaccLexer lexer = new WaccLexer(input);
    // Create a buffer of tokens read from the lexer
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    // Create a parser that reads from the tokens buffer
    WaccParser parser = new WaccParser(tokens);
    // Parse tokens with the program rule
    ParseTree tree = parser.program();

    // Semantic checking
    TreeVisitor treeVisitor = new TreeVisitor();
    return (AST) treeVisitor.visit(tree);
  }
}
