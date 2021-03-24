package backend.instructions;

public class LDR extends Instr {

  // Number of bytes determines the type of LDR instruction
  private final int bytes;
  private final String conditions;
  private final AddrMode addrMode;
  private final String dest;

  public LDR(int bytes, String conditions, String dest, AddrMode addrMode) {
    this.addrMode = addrMode;
    this.bytes = bytes;
    this.conditions = conditions;
    this.dest = dest;
  }

  // Default to 4 bytes (word size) if not specified
  public LDR(String dest, AddrMode addrMode) {
    this(4, Condition.NO_CON, dest, addrMode);
  }

  /**
   * Gets the correct type of LDR instruction depending on size of operand.
   */
  private String getLdr() {
    String ldr = "LDR";
    switch (bytes) {
      // Byte
      case -1:
        ldr += "B";
        break;
      // Signed byte
      case 1:
        ldr += "SB";
        break;
      // Signed half word (Added for extensibility)
      case -2:
        ldr += "SH";
        break;
      // Half word
      case 2:
        ldr += "H";
        break;
      // Word
      default:
    }
    return ldr + conditions;
  }

  @Override
  public String translateToArm() {
    return getLdr() + " " + dest + ", " + addrMode.translateToArm();
  }
}
