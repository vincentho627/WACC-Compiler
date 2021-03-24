package frontend.symboltable;

/**
 * ArrayIDs may contain an EmptyID if the contents of the array are empty and
 * the type of its elements are not yet determined.
 */
public class EmptyID extends TypeID {

  public EmptyID() {
    super("empty");
  }

  @Override
  public TypeID getType() {
    return this;
  }
}
