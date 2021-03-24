package backend.instructions;

import java.util.*;

public abstract class Instr {

  // Size and value constants
  public static final int NULL_PAIR = 0;
  public static final int FALSE_VAL = 0;
  public static final int TRUE_VAL = 1;
  public static final int BYTE_SIZE = 1;
  public static final int WORD_SIZE = 4;
  public static final int PAIR_SIZE = 10;
  public static final int WORD_BIT_LIMIT = 31;

  // Registers

  public static final String SP = "sp";
  public static final String LR = "lr";
  public static final String PC = "pc";

  // Reserved for argument passing in function calls
  public static final String R0 = "r0";
  public static final String R1 = "r1";
  public static final String R2 = "r2";
  public static final String R3 = "r3";

  // General registers
  public static final String R4 = "r4";
  public static final String R5 = "r5";
  public static final String R6 = "r6";
  public static final String R7 = "r7";
  public static final String R8 = "r8";
  public static final String R9 = "r9";
  public static final String R10 = "r10";
  public static final String R11 = "r11";

  private static final String[] regs = {R4, R5, R6, R7, R8, R9, R10};
  private static final int MAX_REG_INDEX = 6;
  private static int regsDepth = 0;

  // Program labels
  private static int nextLabelNumber = 0;
  private static String curLabel = Label.MAIN;
  private static Map<String, List<Instr>> labels = new HashMap<>();
  // List of labels to keep track of the order to print the labels
  private static List<String> labelOrder = new ArrayList<>(
      Collections.singletonList(Label.MAIN));


  // ****** Register methods ******

  public static String getTargetReg() {
    return regsDepth >= MAX_REG_INDEX ? R10 : regs[regsDepth];
  }

  // If regsDepth overflows, R10 pushed onto the stack and reused
  public static String incDepth() {
    if (++regsDepth > MAX_REG_INDEX) {
      addToCurLabel(new PUSH(R10));
      return R10;
    }
    return regs[regsDepth];
  }

  // If regsDepth has overflowed, pop stack into R11
  public static String decDepth() {
    assert (regsDepth > 0);
    if (--regsDepth >= MAX_REG_INDEX) {
      addToCurLabel(new POP(R11));
      return R11;
    }
    return regs[regsDepth];
  }

  // Called after decreasing depth to see if stack was used to store reg values
  public static boolean regsOnStack() {
    return regsDepth >= MAX_REG_INDEX;
  }


  // ****** Label methods ******

  public static void setCurLabel(String label) {
    curLabel = label;
    // Creates an entry in the labels if it is not there yet
    if (!labels.containsKey(curLabel)) {
      labels.put(curLabel, new ArrayList<>());
    }
  }

  public static String getNextLabel() {
    String nextLabel = "L" + nextLabelNumber;
    nextLabelNumber++;
    return nextLabel;
  }

  /**
   * Adds to the label order, meaning that this label should be printed next.
   */
  public static void addToLabelOrder(String label) {
    labelOrder.add(label);
  }

  // Updates current label's instrs by adding instrs to it if label is a key in labels
  public static void addToCurLabel(List<Instr> instrs) {
    if (labels.containsKey(curLabel)) {
      List<Instr> curInstrs = labels.get(curLabel);
      curInstrs.addAll(instrs);
      labels.put(curLabel, curInstrs);
    } else {
      labels.put(curLabel, instrs);
    }
  }

  /**
   * Adds instructions to the text section named with the current label.
   */
  public static void addToCurLabel(Instr instr) {
    List<Instr> curInstrs = labels.containsKey(curLabel) ?
        labels.get(curLabel) : new ArrayList<>();
    curInstrs.add(instr);
    labels.put(curLabel, curInstrs);
  }

  public static List<String> getLabelOrder() {
    return labelOrder;
  }

  public static Map<String, List<Instr>> getLabels() {
    return labels;
  }



  /**
   * Converts the generalised instruction object into ARM-specific assembly
   * code. Subclasses of Instr should store sufficient metadata about the
   * instruction to do this translation. If necessary, we could also have
   * similar methods such as translateToX86(), translateToMips() etc., without
   * having to change the implementation of the Instr object.
   */
  public abstract String translateToArm();
}
