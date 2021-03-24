package backend.instructions;

public class LTORG extends Instr {

  @Override
  public String translateToArm() {
    return ".ltorg";
  }
}
