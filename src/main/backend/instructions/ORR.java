package backend.instructions;

public class ORR extends Instr {

  private final boolean exclusive; // Toggle between OR and XOR
  private final String dest;
  private final AddrMode operand2;

  public ORR(boolean exclusive, String dest, AddrMode operand2) {
    this.exclusive = exclusive;
    this.dest = dest;
    this.operand2 = operand2;
  }

  @Override
  public String translateToArm() {
    String or = exclusive ? "EOR " : "ORR ";
    return (or + dest + ", " + dest + ", " + operand2.translateToArm());
  }
}
