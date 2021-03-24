package backend.instructions;

public class MOV extends Instr {

  private final String conditions;
  private final String dst;
  private final AddrMode src;

  public MOV(String conditions, String dst, AddrMode src) {
    this.conditions = conditions;
    this.dst = dst;
    this.src = src;
  }

  @Override
  public String translateToArm() {
    return "MOV" + conditions + " " + dst + ", " + src.translateToArm();
  }
}
