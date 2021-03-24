package backend.instructions;

public class BRANCH extends Instr {

  /**
   * Indicates if its B or BL, i.e. branch with link.
   */
  private final boolean link;
  private final String conditions;
  private final String label;

  public BRANCH(boolean link, String conditions, String label) {
    this.link = link;
    this.conditions = conditions;
    this.label = label;
  }

  @Override
  public String translateToArm() {
    return (link ? "BL" : "B") + conditions + " " + label;
  }
}
