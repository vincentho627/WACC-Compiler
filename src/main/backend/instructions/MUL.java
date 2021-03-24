package backend.instructions;

public class MUL extends Instr {

  private final String regLow;  // Also first operand
  private final String regHigh; // Also second operand

  public MUL(String regLow, String regHigh, boolean flip) {
    this.regLow = flip ? regHigh : regLow;
    this.regHigh = flip ? regLow : regHigh;
  }

  @Override
  public String translateToArm() {
    return "SMULL " + regLow + ", " + regHigh + ", " + regLow + ", " + regHigh;
  }
}
