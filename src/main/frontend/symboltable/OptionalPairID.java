package frontend.symboltable;

/**
 * An OptionalPairID can either be a PairID or a NullID.
 */
public abstract class OptionalPairID extends TypeID {

  protected OptionalPairID(String typeName) {
    super(typeName);
  }
}
