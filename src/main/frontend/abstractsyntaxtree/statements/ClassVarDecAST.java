package frontend.abstractsyntaxtree.statements;

import static backend.instructions.Instr.WORD_SIZE;
import static backend.instructions.Instr.addToCurLabel;

import antlr.WaccParser.Class_var_decl_statContext;
import backend.instructions.AddrMode;
import backend.instructions.Condition;
import backend.instructions.Instr;
import backend.instructions.STR;
import frontend.abstractsyntaxtree.Node;
import frontend.abstractsyntaxtree.Utils;
import frontend.abstractsyntaxtree.assignments.AssignRHSAST;
import frontend.abstractsyntaxtree.classes.ClassAttributeAST;
import frontend.abstractsyntaxtree.classes.ClassInstanceAST;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.ClassID;
import frontend.symboltable.ConstructorID;
import frontend.symboltable.FuncID;
import frontend.symboltable.Identifier;
import frontend.symboltable.SymbolTable;
import frontend.symboltable.TypeID;
import frontend.symboltable.UnknownID;
import java.util.ArrayList;
import java.util.List;

public class ClassVarDecAST extends Node {

  private final String className;
  private final String varName;
  private final SymbolTable symtab;
  private final AssignRHSAST assignRHS;
  private final Class_var_decl_statContext ctx;

  public ClassVarDecAST(String className, String varName,
      AssignRHSAST assignRHS, SymbolTable symtab, Class_var_decl_statContext ctx) {
    super(symtab.lookupAll("class " + className));
    this.className = className;
    this.varName = varName;
    this.assignRHS = assignRHS;
    this.symtab = symtab;
    this.ctx = ctx;
  }

  @Override
  public void check() {
    // check if class is defined
    if (identifier == null || !(identifier instanceof ClassID)) {
      SemanticErrorCollector.addClassNotDefined(
          className, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
      if (identifier == null) {
        setIdentifier(new UnknownID());
      }
      return;
    }

    int line = ctx.getStart().getLine();
    int pos = ctx.getStart().getCharPositionInLine();

    ClassID classID = (ClassID) identifier;
    TypeID rhsType = assignRHS.getIdentifier().getType();

    Identifier variable = symtab.lookup(varName);

    SymbolTable classScope = classID.getSymtab();
    ConstructorID constructorID = (ConstructorID) classScope.lookup(className);
    List<ClassAttributeAST> attributesList = constructorID.getAttributesList().getAttributesList();

    // we checked the class instance before this check to ensure that the args and attributes
    // are consistent
    for (int i = 0; i < attributesList.size(); i++) {
      Identifier arg = attributesList.get(i).getIdentifier();
      String paramName = attributesList.get(i).getName();
      symtab.add(varName + "." + paramName, arg);
    }

    // Check if var is already declared unless it is a function name
    if (variable != null && !(variable instanceof FuncID)) {
      SemanticErrorCollector.addSymbolAlreadyDefined(varName, line, pos);
    }

    if (!Utils.typeCompat(classID, rhsType)) {
      SemanticErrorCollector.addIncompatibleType(
          className, rhsType.getTypeName(), varName, line, pos);
    }

    symtab.incrementSize(identifier.getType().getBytes());
    symtab.add(varName, classID);
  }

  @Override
  public void toAssembly() {
    // We know its going to be class ID
    ClassID classType = (ClassID) identifier.getType();

    // Generate the offset of the variable
    int offset = symtab.getSmallestOffset() - classType.getBytes();

    // Add the offset to the symbol table's hashmap of variables' offsets
    symtab.addOffset(varName, offset);

    // get arguments of class constructor call
    ClassInstanceAST classInstanceAST = (ClassInstanceAST) assignRHS;
    ClassID classID = (ClassID) classInstanceAST.getIdentifier();
    SymbolTable classScope = classID.getSymtab();
    ConstructorID constructorID = (ConstructorID) classScope.lookup(className);
    List<ClassAttributeAST> params = constructorID.getAttributesList().getAttributesList();
    List<Node> args = classInstanceAST.getArgList().getArguments();

    assert(params.size() == args.size());

    for (int i = 0; i < args.size(); i++) {
      Node arg = args.get(i);
      String paramName = params.get(i).getName();
      arg.toAssembly();
      int argOffset = arg.getIdentifier().getType().getBytes();
      symtab.addOffset(varName + "." + paramName, offset);

      // Stores the value in the offset stack address
      STR strInstr = new STR(argOffset, Condition.NO_CON,
          Instr.R4, AddrMode.buildAddrWithOffset(Instr.SP, offset));
      offset += argOffset;
      addToCurLabel(strInstr);
    }

  }

}
