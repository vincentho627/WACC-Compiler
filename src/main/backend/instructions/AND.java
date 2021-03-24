package backend.instructions;

public class AND extends Instr {

  private final String dest; // Also first operand
  private final AddrMode operand2;

  public AND(String dest, AddrMode operand2) {
    this.dest = dest;
    this.operand2 = operand2;
  }

  @Override
  public String translateToArm() {
    return ("AND " + dest + ", " + dest + ", " + operand2.translateToArm());
  }
}
