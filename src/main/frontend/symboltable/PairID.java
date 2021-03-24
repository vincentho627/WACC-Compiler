package frontend.symboltable;

public class PairID extends OptionalPairID {

  private TypeID fst;
  private TypeID snd;

  public void setFst(TypeID fst) {
    this.fst = fst;
  }

  public void setSnd(TypeID snd) {
    this.snd = snd;
  }

  public TypeID getFstType() {
    return fst;
  }

  public TypeID getSndType() {
    return snd;
  }

  public PairID() {
    super("pair");
    this.fst = new NullID();
    this.snd = new NullID();
  }

  public PairID(TypeID fst, TypeID snd) {
    super("pair");
    if (fst == null) {
      this.fst = new NullID();
    } else {
      this.fst = fst;
    }

    if (snd == null) {
      this.snd = new NullID();
    } else {
      this.snd = snd;
    }
  }

  @Override
  public TypeID getType() {
    return this;
  }

  /**
   * Returns the detailed type name of the pair if its child types are
   * well-defined. Otherwise, if it is a generic pair, simply returns "pair".
   */
  @Override
  public String getTypeName() {
    if (!(fst instanceof NullID) && !(snd instanceof NullID)) {
      return String.format("pair(%s,%s)", fst.getTypeName(),
          snd.getTypeName());
    }
    return super.getTypeName();
  }
}
