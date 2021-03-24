package frontend.abstractsyntaxtree.functions;

import backend.instructions.Instr;
import frontend.abstractsyntaxtree.Node;
import frontend.symboltable.TypeID;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ParamListAST extends Node {

  private List<ParamAST> paramASTs;

  public ParamListAST() {
    this.paramASTs = Collections.emptyList();
  }

  public ParamListAST(List<ParamAST> paramASTs) {
    this.paramASTs = paramASTs;
  }

  public List<ParamAST> getParamList() { return paramASTs; }

  // Used in the visitor to convert ASTs to IDs
  public List<TypeID> convertToParamIDs() {
    return paramASTs.stream()
        .map(paramAST -> paramAST.getIdentifier().getType())
        .collect(Collectors.toList());
  }

  // Get sum of the param bytes
  public int getParamBytes() {
    return paramASTs.stream()
        .map(paramAST -> paramAST.getIdentifier().getType().getBytes())
        .reduce(0, Integer::sum);
  }

  @Override
  public void check() {
    for (ParamAST paramAST : paramASTs) {
      paramAST.check();
    }
  }

  @Override
  public void toAssembly() {}
}
