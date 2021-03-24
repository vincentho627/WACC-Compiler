import backend.BackEndGenerator;
import backend.instructions.Instr;
import frontend.FrontEndAnalyser;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.*;

public class Main {

  public static void main(String[] args) throws Exception {

    String srcFilePath = args[0];

    // Create a CharStream that reads from an input file
    CharStream input = CharStreams.fromStream(new FileInputStream(srcFilePath));

    // Initialises and runs the lexer, parser and semantic analyser
    FrontEndAnalyser frontEndAnalyser = new FrontEndAnalyser(input);
    int status = frontEndAnalyser.run();
    if (status != 0) {
      System.exit(status);
    }

    // Initialises and runs the code generator
    BackEndGenerator backEndGenerator = new BackEndGenerator(
        frontEndAnalyser.getAst());
    String armFilePath = srcFilePath.replace(".wacc", ".s");
    String[] splitPaths = armFilePath.split("/");
    armFilePath = splitPaths[splitPaths.length - 1];
    File armFile = new File(armFilePath);
    if (armFile.createNewFile()) {
      System.out.println("File created: " + armFile.getName());
    }

    FileWriter armFileWriter = new FileWriter(armFilePath, false);
    String output = backEndGenerator.run();
    armFileWriter.write(output);
    armFileWriter.close();
  }
}
