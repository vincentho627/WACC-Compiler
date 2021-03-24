package backend.instructions;

public class ADD extends Instr {

  private final boolean setFlags; //Set overflow
  private final String dst;
  private final String src;
  private final AddrMode operand;
  private final AddrMode shift;

  public ADD(boolean setFlags, String dst, String src, AddrMode operand) {
    this(setFlags, dst, src, operand, null);
  }

  public ADD(boolean setFlags, String dst, String src, AddrMode operand,
      AddrMode shift) {
    this.setFlags = setFlags;
    this.dst = dst;
    this.src = src;
    this.operand = operand;
    this.shift = shift;
  }

  /**
   * Returns ADD{S} dest, dest, operand2; and shifts if necessary.
   */
  @Override
  public String translateToArm() {
    String shiftArg = "";
    if (shift != null) {
      shiftArg = ", " + shift.translateToArm();
    }
    return "ADD" + (setFlags ? "S " : " ")
        + dst + ", " + src + ", " + operand.translateToArm() + shiftArg;
  }
}
