package frontend.abstractsyntaxtree.classes;

import backend.instructions.Instr;
import backend.instructions.LTORG;
import backend.instructions.Label;
import backend.instructions.POP;
import backend.instructions.PUSH;
import frontend.abstractsyntaxtree.Node;
import frontend.symboltable.ConstructorID;
import frontend.symboltable.Identifier;
import frontend.symboltable.SymbolTable;
import java.util.ArrayList;
import java.util.List;

import static backend.Utils.getStartRoutine;
import static backend.instructions.Instr.addToCurLabel;
import static backend.instructions.Instr.addToLabelOrder;
import static backend.instructions.Instr.setCurLabel;

public class ClassConstructorAST extends Node {

  private final SymbolTable classScope;
  private final String className;
  private final ClassAttributeListAST classAttrListAST;

  public ClassConstructorAST(Identifier identifier, SymbolTable classScope,
      String className, ClassAttributeListAST classAttrListAST) {
    // identifier will be the classID
    super(identifier);
    this.classScope = classScope;
    this.className = className;
    this.classAttrListAST = classAttrListAST;
  }

  public String getClassName() { return className; }

  @Override
  public void check() {
    classScope.add(className, identifier);
  }

  @Override
  public void toAssembly() {
    String labelName = Label.CLASS_HEADER + className;
    setCurLabel(labelName);
    addToLabelOrder(labelName);

    // Calculate the size of stack frame that needs to be allocated
    SymbolTable classSymtab = ((ConstructorID) identifier).getSymtab();
    int offset = 0;
    boolean skipLR = false;
    for (ClassAttributeAST attributeAST : classAttrListAST.getAttributesList()) {
      skipLR = true;
      String varName = attributeAST.getName();
      classSymtab.addOffset(varName, offset);
      offset += attributeAST.getIdentifier().getType().getBytes();
    }

    List<Instr> instructions = new ArrayList<>();
    addToCurLabel(new PUSH(Instr.LR));
    addToCurLabel(getStartRoutine(classSymtab, false));

    // If there is at least one parameter, we need to account for LR on the stack
    if (skipLR) {
      classSymtab.setSkipLR();
    }

    // Clean up routine
    instructions.add(new POP(Instr.PC));
    instructions.add(new LTORG());
    addToCurLabel(instructions);

    setCurLabel(Label.MAIN);
  }
}
