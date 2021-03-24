package backend.instructions;

public class CMP extends Instr {

  private final String reg; // First operand
  private final AddrMode operand; // Second operand
  private final AddrMode shift;

  public CMP(String reg, AddrMode operand, AddrMode shift) {
    this.reg = reg;
    this.operand = operand;
    this.shift = shift;
  }

  public CMP(String reg, AddrMode operand) {
    this(reg, operand, null);
  }

  @Override
  public String translateToArm() {
    String instr = "CMP " + reg + ", " + operand.translateToArm();

    if (shift != null) {
      instr += (", " + shift.translateToArm());
    }
    return instr;
  }
}
