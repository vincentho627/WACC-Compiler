package backend.instructions;

public class SUB extends Instr {

  private final boolean reverse;  // For RSB instruction (negation)
  private final boolean setFlags; // Set overflow  
  private final String dest;
  private final String operand1;
  private final AddrMode operand2;


  public SUB(boolean reverse, boolean setFlags, String dest, String operand1,
      AddrMode operand2) {
    this.reverse = reverse;
    this.setFlags = setFlags;
    this.dest = dest;
    this.operand1 = operand1;
    this.operand2 = operand2;
  }

  /**
   * Examples of returned instructions: SUB{S} dest, dest, operand2; RSBS dest,
   * dest, operand2.
   */
  @Override
  public String translateToArm() {
    String base = reverse ? "RSB" : "SUB";
    if (setFlags) {
      base += "S";
    }
    base += " ";
    return (base + dest + ", " + operand1 + ", " + operand2.translateToArm());
  }
}
