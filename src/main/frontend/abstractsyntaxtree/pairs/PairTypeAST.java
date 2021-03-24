package frontend.abstractsyntaxtree.pairs;

import frontend.abstractsyntaxtree.Node;
import frontend.symboltable.Identifier;
import frontend.symboltable.NullID;
import frontend.symboltable.TypeID;


public class PairTypeAST extends Node {

  private final TypeID fst;
  private final TypeID snd;

  public PairTypeAST(Identifier identifier, TypeID fst, TypeID snd) {
    super(identifier);
    // Assigns its children to a NullID if its types are not specified
    this.fst = fst == null ? new NullID() : fst;
    this.snd = snd == null ? new NullID() : snd;
  }

  public TypeID getFst() {
    return fst;
  }

  public TypeID getSnd() {
    return snd;
  }

  @Override
  public void check() {

  }

  @Override
  public void toAssembly() {}
}
