package backend;

import backend.instructions.Instr;
import frontend.abstractsyntaxtree.AST;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static backend.instructions.Instr.*;

public class BackEndGenerator {

  private static final List<String> dataSegmentStrings = new ArrayList<>();
  private static int dataSegmentIndex = 0;

  private static final List<String> preDefFuncs = new ArrayList<>();

  private final AST ast;

  public BackEndGenerator(AST ast) {
    this.ast = ast;
    dataSegmentStrings.clear();
    preDefFuncs.clear();
  }

  public String run() {
    StringBuilder output = new StringBuilder();
    // Generates the main body of instructions, e.g.: main, L0, L1 etc.
    generateMainInstructions();

    // Build body for pre-defined functions required
    Map<String, List<Instr>> preDefFuncInstrs = Utils
        .getPreDefFunc(preDefFuncs);
    for (Map.Entry<String, List<Instr>> pdf : preDefFuncInstrs.entrySet()) {
      String pdfName = pdf.getKey();
      List<Instr> pdfInstrs = pdf.getValue();

      setCurLabel(pdfName);
      addToLabelOrder(pdfName);
      addToCurLabel(pdfInstrs);
    }

    // Writes the data segment
    if (dataSegmentStrings.size() > 0) {
      output.append(".data\n\n");
      int msgIndex = 0;
      for (String dataSegmentString : dataSegmentStrings) {
        output.append("msg_").append(msgIndex).append(":\n");

        // Calculates the length correctly (accounts for escape characters)
        int limit = dataSegmentString.length();
        int len = limit;
        for (int i = 0; i < limit; ) {
          if (dataSegmentString.charAt(i) == '\\') {
            len--;
            i++;
          }
          i++;
        }

        output.append("\t.word ").append(len).append("\n");
        output.append("\t.ascii \"").append(dataSegmentString).append("\"\n");
        msgIndex++;
      }
      output.append("\n");
    }

    // Writes the text segment
    output.append(".text\n\n.global main\n");

    // Writes every labelled section and their corresponding instructions
    // Sections include user-defined functions, main etc.
    for (String label : getLabelOrder()) {
      output.append(writeTextSection(label, getLabels().get(label)));
    }

    return output.toString();
  }

  // Starts the translation to assembly
  private void generateMainInstructions() {
    addToCurLabel(Utils.getStartRoutine(ast.getSymtab(),true));
    ast.toAssembly();
    addToCurLabel(Utils.getEndRoutine(ast.getSymtab(),true));
  }

  /**
   * Uses fileWriter to write a .text section of instructions, named
   * sectionName, in assembly format.
   */
  private String writeTextSection(String sectionName,
      List<Instr> instructions) {
    StringBuilder output = new StringBuilder();
    output.append(sectionName).append(":\n");
    for (Instr instruction : instructions) {
      output.append("\t").append(instruction.translateToArm()).append("\n");
    }
    return output.toString();
  }

  // Adds msg to dataSegment and return index for instruction use
  public static int addToDataSegment(String msg) {
    dataSegmentStrings.add(msg);
    return dataSegmentIndex++;
  }

  // Adds a pre-defined function
  public static void addToPreDefFuncs(String func) {
    preDefFuncs.add(func);
  }
}
