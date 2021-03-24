package backend.instructions;

public class STR extends Instr {

  // Number of bytes determines the type of STR instruction
  private final int bytes;
  private final String conditions;
  private final String src;
  private final AddrMode dst;

  // NOTE that for STR: source is lhs and destination is rhs
  public STR(int bytes, String conditions, String src, AddrMode dst) {
    this.bytes = bytes;
    this.conditions = conditions;
    this.src = src;
    this.dst = dst;
  }

  // Without byte and condition parameters
  // Default to 4 bytes (word size) if not specified
  public STR(String src, AddrMode dst) {
    this(4, Condition.NO_CON, src, dst);
  }


  private String getStr() {
    String str = "STR" + conditions;
    switch (bytes) {
      // Byte
      case 1:
        return str + "B";
      // Half word
      case 2:
        return str + "H";
      // Double word
      case 8:
        return str + "D";
      // Word
      default:
        return str;
    }
  }

  @Override
  public String translateToArm() {
    return getStr() + " " + src + ", " + dst.translateToArm();
  }
}
