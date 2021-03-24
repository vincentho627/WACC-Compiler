package frontend;

import antlr.WaccParser;
import antlr.WaccParser.*;
import antlr.WaccParserBaseVisitor;
import frontend.abstractsyntaxtree.*;
import frontend.abstractsyntaxtree.array.ArrayLiterAST;
import frontend.abstractsyntaxtree.array.ArrayTypeAST;
import frontend.abstractsyntaxtree.classes.AccessClassAttributeAST;
import frontend.abstractsyntaxtree.classes.CallClassFunctionAST;
import frontend.abstractsyntaxtree.classes.ClassAST;
import frontend.abstractsyntaxtree.classes.ClassAttributeAST;
import frontend.abstractsyntaxtree.classes.ClassAttributeListAST;
import frontend.abstractsyntaxtree.classes.ClassConstructorAST;
import frontend.abstractsyntaxtree.classes.ClassFuncAST;
import frontend.abstractsyntaxtree.classes.ClassInstanceAST;
import frontend.symboltable.Visibility;
import frontend.abstractsyntaxtree.functions.ArgListAST;
import frontend.abstractsyntaxtree.functions.FuncAST;
import frontend.abstractsyntaxtree.functions.ParamAST;
import frontend.abstractsyntaxtree.functions.ParamListAST;
import frontend.abstractsyntaxtree.pairs.PairElemAST;
import frontend.abstractsyntaxtree.pairs.PairElemTypeAST;
import frontend.abstractsyntaxtree.pairs.PairTypeAST;
import frontend.abstractsyntaxtree.statements.*;
import frontend.abstractsyntaxtree.assignments.AssignCallAST;
import frontend.abstractsyntaxtree.assignments.AssignLHSAST;
import frontend.abstractsyntaxtree.assignments.AssignRHSAST;
import frontend.abstractsyntaxtree.expressions.*;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.*;
import java.util.ArrayList;
import java.util.List;

public class TreeVisitor extends WaccParserBaseVisitor<Node> {

  private SymbolTable currSymTab;

  public TreeVisitor() {
    // Initialised with top level symtab
    this.currSymTab = new SymbolTable();
  }

  // Root of parse tree
  @Override
  public AST visitProgram(ProgramContext ctx) {
    // Functions analysis
    List<Node> fs = new ArrayList<>();
    List<Node> cs = new ArrayList<>();
    List<ClassesContext> classContexts = ctx.classes();
    List<FuncContext> funcContexts = ctx.func();

    // Add all functions to global scope first in order to support recursion and
    // class calling global functions
    for (FuncContext fc : funcContexts) {
      fs.add(visitFunc(fc));
    }

    // Add all classes to global scope first in order
    for (ClassesContext cc : classContexts) {
      cs.add(visitClasses(cc));
    }

    // Iterates through functions and checks its body
    for (int i = 0; i < fs.size(); i++) {
      visitFuncWrapper(funcContexts.get(i), (FuncAST) fs.get(i));
    }

    // visit stat
    Node statAST = visit(ctx.stat());

    return new AST(cs, fs, statAST, currSymTab);
  }

  @Override
  public ClassAST visitClasses(ClassesContext ctx) {
    // visits a class
    // sets new scope for class
    SymbolTable globalScope = currSymTab;
    currSymTab = new SymbolTable(globalScope);
    currSymTab.setClassContext();

    String className = ctx.IDENT().getText();
    currSymTab.setClassName(className);

    ClassAttributeListAST classAttrListAST =
        ctx.attributeList() == null ? new ClassAttributeListAST()
            : visitAttributeList(ctx.attributeList());

    ClassID classID = new ClassID(className, currSymTab, classAttrListAST);

    ConstructorID constructID = new ConstructorID(classID, classAttrListAST, currSymTab);

    ClassConstructorAST constructAST =
        new ClassConstructorAST(constructID, currSymTab, className, classAttrListAST);

    // checks if constructor contains all the attributes defined
    constructAST.check();

    List<ClassFuncContext> classFuncContexts = ctx.classFunc();
    List<ClassFuncAST> classFunctions = new ArrayList<>();

    for (ClassFuncContext cfc : classFuncContexts) {
      ClassFuncAST currFunc = visitClassFunc(cfc);
      currFunc.setClassName(className);
      classFunctions.add(currFunc);
    }

    ClassAST classAST = new ClassAST(classID, globalScope, className,
        classAttrListAST, constructAST, classFunctions, ctx);

    // final checks on the class, checks that constructor is the same name as the class name
    classAST.check();
    // checks if the class is already defined
    classAST.addClassToGlobalScope();

    // Swap back symbol table
    currSymTab = globalScope;

    return classAST;
  }

  @Override
  public ClassAttributeAST visitAttribute(AttributeContext ctx) {
    // Don't need to check, checking gets done in ClassAttributeListAST
    Identifier identifier = visit(ctx.type()).getIdentifier();
    String varName = ctx.IDENT().getText();
    Visibility visibility = ctx.VISIBILITY().getText().equals("public")
        ? Visibility.PUBLIC : Visibility.PRIVATE;

    ClassAttributeID classAttributeID = new ClassAttributeID((TypeID) identifier, varName);
    return new ClassAttributeAST(classAttributeID, currSymTab, visibility,
        varName, ctx);
  }

  @Override
  public ClassAttributeListAST visitAttributeList(AttributeListContext ctx) {
    // stores all the attributes of the class
    List<ClassAttributeAST> classAttributes = new ArrayList<>();

    for (AttributeContext attrCtx : ctx.attribute()) {
      classAttributes.add(visitAttribute(attrCtx));
    }

    ClassAttributeListAST classAttrListAST = new ClassAttributeListAST(classAttributes);

    // checks if variables are overlapped within the class
    classAttrListAST.check();

    return classAttrListAST;
  }

  @Override
  public ClassFuncAST visitClassFunc(ClassFuncContext ctx) {
    FuncContext classFuncContexts = ctx.func();
    FuncAST fc = visitFunc(classFuncContexts);
    visitFuncWrapper(classFuncContexts, fc);
    Visibility visibility = ctx.VISIBILITY().getText().equals("public")
        ? Visibility.PUBLIC : Visibility.PRIVATE;
    ClassFuncAST classFunc = new ClassFuncAST(fc.getIdentifier(), currSymTab,
        visibility, fc, ctx);

    // checks if the function is already defined and checks if the function is the same as the className
    // aka the constructor name
    classFunc.check();
    return classFunc;
  }

  @Override
  public Node visitClassInstant(ClassInstantContext ctx) {
    ArgListAST argsList = visitArgList(ctx.argList());
    String className = ctx.IDENT().getText();
    ClassInstanceAST newInstant = new ClassInstanceAST(className, currSymTab, argsList, ctx);
    newInstant.check();
    return newInstant;
  }

  @Override
  public Node visitCallClassFunc(CallClassFuncContext ctx) {
    String varName = ctx.IDENT(0).getText();
    String funcName = ctx.IDENT(1).getText();
    ArgListAST argListAST = visitArgList(ctx.argList());

    CallClassFunctionAST callClassFunc = new CallClassFunctionAST(
        varName, funcName, argListAST, currSymTab, ctx);
    callClassFunc.check();
    return callClassFunc;
  }

  @Override
  public AccessClassAttributeAST visitClassAttr(ClassAttrContext ctx) {
    String variableName = ctx.IDENT().get(0).getText();
    String attributeName = ctx.IDENT().get(1).getText();

    AccessClassAttributeAST accessClassAttr = new AccessClassAttributeAST(
        variableName, attributeName, currSymTab, ctx);
    accessClassAttr.check();
    return accessClassAttr;
  }

  // Sets up FuncAST and adds to global scope
  @Override
  public FuncAST visitFunc(FuncContext ctx) {
    // Create symbol table
    SymbolTable globalScope = currSymTab;
    currSymTab = new SymbolTable(globalScope);

    Node returnType = visitType(ctx.type());

    // Initialises empty ParamListAST if no params
    ParamListAST params =
        ctx.paramList() == null ? new ParamListAST()
            : visitParamList(ctx.paramList());

    FuncID funcID = new FuncID(returnType, params.convertToParamIDs(),
        currSymTab);
    String funcName = ctx.IDENT().getText();

    FuncAST funcAST = new FuncAST(funcID, globalScope, funcName, params, ctx);
    funcAST.addFuncToGlobalScope();

    // Swap back symbol table
    currSymTab = globalScope;

    return funcAST;
  }

  // Verifies function body
  private void visitFuncWrapper(FuncContext ctx, FuncAST funcAST) {
    FuncID funcID = (FuncID) funcAST.getIdentifier();

    SymbolTable globalScope = currSymTab;
    currSymTab = funcID.getSymtab(); // Set correct scope

    Node stat = visit(ctx.stat());
    funcAST.setStatements(stat);
    funcAST.check();

    currSymTab = globalScope; // Swap back to global scope
  }

  @Override
  public PrintAST visitPrint_stat(Print_statContext ctx) {
    Node expr = visit(ctx.expr());
    return new PrintAST(expr);
  }

  @Override
  public AssignStatAST visitAssign_stat(Assign_statContext ctx) {
    AssignLHSAST lhs = (AssignLHSAST) visit(ctx.assignLHS());
    AssignRHSAST rhs = (AssignRHSAST) visit(ctx.assignRHS());

    AssignStatAST assignStatAST =
        new AssignStatAST(ctx.assignLHS(), ctx.assignRHS(), lhs, rhs,
            currSymTab);
    assignStatAST.check();

    return assignStatAST;
  }

  @Override
  public PrintlnAST visitPrintln_stat(Println_statContext ctx) {
    Node expr = visit(ctx.expr());
    return new PrintlnAST(expr);
  }

  @Override
  public ReturnAST visitReturn_stat(Return_statContext ctx) {
    Node expr = visit(ctx.expr());

    ReturnAST returnAST = new ReturnAST(currSymTab, expr, ctx);
    returnAST.check();

    return returnAST;
  }

  @Override
  public ExitAST visitExit_stat(Exit_statContext ctx) {
    Node expr = visit(ctx.expr());

    ExitAST exitAST = new ExitAST(expr, ctx,
        (TypeID) currSymTab.lookupAll("int"));
    exitAST.check();

    return exitAST;
  }

  @Override
  public SkipAST visitSkip_stat(Skip_statContext ctx) {
    return new SkipAST();
  }

  @Override
  public FreeAST visitFree_stat(Free_statContext ctx) {
    Node expr = visit(ctx.expr());

    FreeAST freeAST = new FreeAST(expr, ctx.expr());
    freeAST.check();

    return freeAST;
  }

  @Override
  public VarDecAST visitVar_decl_stat(Var_decl_statContext ctx) {
    AssignRHSAST assignRHS = (AssignRHSAST) visit(ctx.assignRHS());

    TypeID decType;
    if (ctx.VAR() != null) {
      decType = new VarID(assignRHS.getIdentifier().getType().getBytes());
    } else {
      Node typeAST = visit(ctx.type());
      decType = typeAST.getIdentifier().getType();
    }

    String varName = ctx.IDENT().getText();
    VarDecAST varDec = new VarDecAST(currSymTab, decType, varName, assignRHS,
        ctx);
    varDec.check();

    return varDec;
  }

  @Override
  public Node visitClass_var_decl_stat(Class_var_decl_statContext ctx) {
    String className = ctx.IDENT().get(0).getText();
    String varName = ctx.IDENT().get(1).getText();

    AssignRHSAST assignRHSAST = (AssignRHSAST) visit(ctx.assignRHS());

    ClassVarDecAST classVarDec = new ClassVarDecAST(
        className, varName, assignRHSAST, currSymTab, ctx);
    classVarDec.check();
    return classVarDec;
  }

  @Override
  public WhileAST visitWhile_stat(While_statContext ctx) {
    SymbolTable encScope = currSymTab;
    Node expr = visit(ctx.expr());

    currSymTab = new SymbolTable(encScope); // New scope
    Node stat = visit(ctx.stat());

    WhileAST whileAST = new WhileAST(expr, stat, ctx.expr(), currSymTab);
    whileAST.check();

    currSymTab = encScope; // Swap back to parent scope

    return whileAST;
  }

  @Override public DoWhileAST visitDo_while_stat(Do_while_statContext ctx) {
    SymbolTable encScope = currSymTab;
    Node expr = visit(ctx.expr());

    currSymTab = new SymbolTable(encScope); // New scope
    Node stat = visit(ctx.stat());

    DoWhileAST doWhileAST = new DoWhileAST(expr, stat, ctx.expr(), currSymTab);
    doWhileAST.check();

    currSymTab = encScope; // Swap back to parent scope

    return doWhileAST;
  }

  @Override
  public Node visitFor_stat(For_statContext ctx) {
    SymbolTable encScope = currSymTab;
    Node startExpr = visit(ctx.expr(0));
    Node endExpr = visit(ctx.expr(1));
    // Add the variable to the enclosing scope
    String varName = ctx.IDENT().getText();
    currSymTab.add(varName, new IntID());

    currSymTab = new SymbolTable(encScope); // New scope
    Node stat = visit(ctx.stat());
    ForAST forAST = new ForAST(varName, startExpr, endExpr, stat, currSymTab,
        ctx);
    forAST.check();

    currSymTab = encScope; // Swap back to parent scope

    return forAST;
  }

  @Override
  public IfAST visitIf_stat(If_statContext ctx) {
    Node expr = visit(ctx.expr());

    SymbolTable encScope = currSymTab;

    // Each branch is executed in its own scope
    SymbolTable thenScope = new SymbolTable(encScope);
    currSymTab = thenScope;
    Node thenStat = visit(ctx.stat(0));
    SymbolTable elseScope = new SymbolTable(encScope);
    currSymTab = elseScope;
    Node elseStat = visit(ctx.stat(1));

    currSymTab = encScope; // Swap back to parent scope

    IfAST ifAST = new IfAST(expr, thenStat, elseStat, thenScope,
        elseScope, ctx.expr());
    ifAST.check();

    return ifAST;
  }

  @Override
  public BeginStatAST visitBegin_stat(WaccParser.Begin_statContext ctx) {
    SymbolTable encScope = currSymTab;
    currSymTab = new SymbolTable(encScope); // Create new scope

    Node stat = visit(ctx.stat());
    BeginStatAST beginAST = new BeginStatAST(stat, currSymTab);

    currSymTab = encScope; // Swap back to parent scope

    return beginAST;
  }

  @Override
  public SequenceAST visitSequence_stat(Sequence_statContext ctx) {
    List<Node> statASTs = new ArrayList<>();

    List<StatContext> statCtxs = ctx.stat();
    for (StatContext s : statCtxs) {
      statASTs.add(visit(s));
    }

    return new SequenceAST(statASTs);
  }

  @Override
  public ReadAST visitRead_stat(Read_statContext ctx) {
    AssignLHSAST assignLHSAST = (AssignLHSAST) visit(ctx.assignLHS());

    ReadAST readAST = new ReadAST(assignLHSAST, ctx.assignLHS(), currSymTab);
    readAST.check();

    return readAST;
  }

  @Override
  public ParamListAST visitParamList(ParamListContext ctx) {
    List<ParamAST> paramASTs = new ArrayList<>();

    for (ParamContext paramCtx : ctx.param()) {
      paramASTs.add(visitParam(paramCtx));
    }

    ParamListAST paramListAST = new ParamListAST(paramASTs);
    paramListAST.check();

    return paramListAST;
  }

  /**
   * Pre-condition: currSymTab already set to be the inner-scope symbol table
   */
  @Override
  public ParamAST visitParam(ParamContext ctx) {
    Identifier paramID = new ParamID(
        visitType(ctx.type()).getIdentifier().getType());
    String varName = ctx.IDENT().getText();

    // Assume function sets currSymTab to be the inner-scope symbol table
    return new ParamAST(paramID, currSymTab, varName, ctx);
  }

  @Override
  public AssignLHSAST visitIdent_assignLHS(Ident_assignLHSContext ctx) {
    String assignName = ctx.IDENT().getText();
    return new AssignLHSAST(currSymTab, assignName);
  }

  @Override
  public AssignLHSAST visitPairElem_assignLHS(PairElem_assignLHSContext ctx) {
    PairElemAST pairElem = visitPairElem((ctx.pairElem()));
    return new AssignLHSAST(currSymTab, pairElem, pairElem.getName());
  }

  @Override
  public AssignLHSAST visitArrayElem_assignLHS(ArrayElem_assignLHSContext ctx) {
    ArrayElemAST arrayElem = visitArrayElem((ctx.arrayElem()));
    return new AssignLHSAST(currSymTab, arrayElem, arrayElem.getName());
  }

  @Override
  public Node visitClassattr_assignLHS(Classattr_assignLHSContext ctx) {
    return visitClassAttr(ctx.classAttr());
  }

  /**
   * Pre-condition: ExprAST calls check()
   */
  @Override
  public AssignRHSAST visitExpr_assignRHS(Expr_assignRHSContext ctx) {
    List<Node> children = new ArrayList<>();

    Node exprAST = visit(ctx.expr());
    children.add(exprAST);

    return new AssignRHSAST(exprAST.getIdentifier().getType(), currSymTab,
        children, false);
  }

  /**
   * Pre-condition: ExprAST calls check()
   */
  @Override
  public AssignRHSAST visitArrayLiter_assignRHS(
      ArrayLiter_assignRHSContext ctx) {
    List<Node> children = new ArrayList<>();

    Node arrayLiterAST = visitArrayLiter(ctx.arrayLiter());
    children.add(arrayLiterAST);

    return new AssignRHSAST(arrayLiterAST.getIdentifier().getType(), currSymTab,
        children, false);
  }

  /**
   * Pre-condition: ExprAST calls check()
   */
  @Override
  public AssignRHSAST visitNewPair_assignRHS(NewPair_assignRHSContext ctx) {
    Node firstExprAST = visit(ctx.expr(0));
    Node secondExprAST = visit(ctx.expr(1));

    List<Node> children = new ArrayList<>();
    children.add(firstExprAST);
    children.add(secondExprAST);
    PairID pairID = new PairID(firstExprAST.getIdentifier().getType(),
        secondExprAST.getIdentifier().getType());

    return new AssignRHSAST(pairID, currSymTab, children, true);
  }

  /**
   * Pre-condition: ExprAST calls check()
   */
  @Override
  public AssignRHSAST visitPairElem_assignRHS(PairElem_assignRHSContext ctx) {
    Node pairElemAST = visitPairElem(ctx.pairElem());

    List<Node> children = new ArrayList<>();
    children.add(pairElemAST);

    return new AssignRHSAST(pairElemAST.getIdentifier().getType(), currSymTab,
        children, false);
  }

  @Override
  public AssignCallAST visitCall_assignRHS(Call_assignRHSContext ctx) {
    String funcName = ctx.IDENT().getText();
    ArgListAST argListAST = visitArgList(ctx.argList());

    AssignCallAST assignCallAST = new AssignCallAST(funcName, currSymTab,
        argListAST, ctx);
    assignCallAST.check();

    return assignCallAST;
  }


  @Override
  public Node visitCallClassFunc_assignRHS(CallClassFunc_assignRHSContext ctx) {
    return visitCallClassFunc(ctx.callClassFunc());
  }

  @Override
  public Node visitInstantiate_assignRHS(Instantiate_assignRHSContext ctx) {
    return visitClassInstant(ctx.classInstant());
  }

  @Override
  public ArgListAST visitArgList(ArgListContext ctx) {
    List<Node> expressions = new ArrayList<>();
    if (ctx != null) {
      for (ExprContext exprContext : ctx.expr()) {
        expressions.add(visit(exprContext));
      }
    }

    // Don't need to check the since creating the exprASTs will call check
    return new ArgListAST(expressions);
  }

  @Override
  public PairElemAST visitPairElem(PairElemContext ctx) {
    Node exprAST = visit(ctx.expr());
    Identifier ident = exprAST.getIdentifier();

    PairElemAST pairElemAST;
    if (ctx.FST() != null) {
      pairElemAST = new PairElemAST(ident, currSymTab, true, exprAST, ctx);
    } else {
      pairElemAST = new PairElemAST(ident, currSymTab, false, exprAST, ctx);
    }
    pairElemAST.check();

    return pairElemAST;
  }

  @Override
  public BaseTypeAST visitBaseType(BaseTypeContext ctx) {
    TypeID baseTypeID;

    if (ctx.INT() != null) {
      baseTypeID = currSymTab.lookupAll(ctx.INT().getText()).getType();
    } else if (ctx.BOOL() != null) {
      baseTypeID = currSymTab.lookupAll(ctx.BOOL().getText()).getType();
    } else if (ctx.CHAR() != null) {
      baseTypeID = currSymTab.lookupAll(ctx.CHAR().getText()).getType();
    } else {
      baseTypeID = currSymTab.lookupAll(ctx.STRING().getText()).getType();
    }

    BaseTypeAST baseTypeAST = new BaseTypeAST(baseTypeID, currSymTab, ctx);
    baseTypeAST.check();

    return baseTypeAST;
  }

  @Override
  public ArrayTypeAST visitArrayType(ArrayTypeContext ctx) {
    int dimensions = ctx.OPEN_SQUARE_BRACKETS().size();

    if (ctx.baseType() != null) {
      Node baseTypeAST = visit(ctx.baseType());

      ArrayID nestedID = new ArrayID(baseTypeAST.getIdentifier().getType());
      for (int i = 1; i < dimensions; i++) {
        nestedID = new ArrayID(nestedID);
      }
      currSymTab.add(nestedID);

      return new ArrayTypeAST(nestedID);
    } else {
      // Pair-type array
      Node pairTypeAST = visit(ctx.pairType());

      ArrayID pairArrayID = new ArrayID(pairTypeAST.getIdentifier().getType());
      currSymTab.add(pairArrayID);

      return new ArrayTypeAST(pairArrayID);
    }
  }

  @Override
  public PairTypeAST visitPairType(PairTypeContext ctx) {
    assert (ctx.PAIR() != null);

    Node fst = visitPairElemType(ctx.pairElemType(0));
    Node snd = visitPairElemType(ctx.pairElemType(1));
    TypeID pairID = new PairID(fst.getIdentifier().getType(),
        snd.getIdentifier().getType());

    PairTypeAST pairTypeAST = new PairTypeAST(pairID, fst.getIdentifier().getType(), snd.getIdentifier().getType());

    return pairTypeAST;
  }

  @Override
  public Node visitPairElemType(PairElemTypeContext ctx) {
    if (ctx.baseType() != null) {
      return visitBaseType(ctx.baseType());
    }
    if (ctx.arrayType() != null) {
      return visitArrayType(ctx.arrayType());
    }

    Identifier pairGenericID = new PairID();
    PairElemTypeAST pairElemTypeAST = new PairElemTypeAST(pairGenericID);
    currSymTab.add(pairGenericID.getType().getTypeName(), pairGenericID);

    return pairElemTypeAST;
  }

  @Override
  public IntLiterAST visitIntLiter(IntLiterContext ctx) {
    IntLiterAST intLiterAST =
        new IntLiterAST(currSymTab, ctx.MINUS() == null,
            ctx.INTEGER().getText());
    intLiterAST.check();

    return intLiterAST;
  }

  @Override
  public IntLiterAST visitBinIntLiter(BinIntLiterContext ctx) {
    // Substring removes '0b' portion of token
    String token = ctx.BINARY_INTEGER().getText().substring(2);
    int val = Integer.parseInt(token, 2);

    IntLiterAST intLiterAST =
        new IntLiterAST(currSymTab, ctx.MINUS() == null, Integer.toString(val));
    intLiterAST.check();

    return intLiterAST;
  }

  @Override
  public Node visitOctIntLiter(OctIntLiterContext ctx) {
    // Substring removes '0o' portion of token
    String token = ctx.OCTAL_INTEGER().getText().substring(2);
    int val = Integer.parseInt(token, 8);

    IntLiterAST intLiterAST =
        new IntLiterAST(currSymTab, ctx.MINUS() == null, Integer.toString(val));
    intLiterAST.check();

    return intLiterAST;
  }

  @Override
  public IntLiterAST visitHexIntLiter(HexIntLiterContext ctx) {
    // Substring removes '0x' portion of token
    String token = ctx.HEXADECIMAL_INTEGER().getText().substring(2);
    int val = Integer.parseInt(token, 16);

    IntLiterAST intLiterAST =
        new IntLiterAST(currSymTab, ctx.MINUS() == null, Integer.toString(val));
    intLiterAST.check();

    return intLiterAST;
  }

  @Override
  public BoolLiterAST visitBoolLiter(BoolLiterContext ctx) {
    return new BoolLiterAST(currSymTab, ctx.FALSE() == null);
  }

  @Override
  public CharLiterAST visitCharLiter(CharLiterContext ctx) {
    String s = ctx.CHAR_LITER().getText();
    // Substring removes quotes
    return new CharLiterAST(currSymTab, s.substring(1, s.length() - 1));
  }

  @Override
  public StrLiterAST visitStrLiter(StrLiterContext ctx) {
    String val = ctx.STR_LITER().getText();
    return new StrLiterAST(currSymTab, val);
  }

  @Override
  public Node visitType(TypeContext ctx) {
    if (ctx.baseType() != null) {
      return visitBaseType(ctx.baseType());
    }

    if (ctx.arrayType() != null) {
      return visitArrayType(ctx.arrayType());
    }

    return visitPairType(ctx.pairType());
  }

  @Override
  public PairLiterAST visitPairLiter(PairLiterContext ctx) {
    return new PairLiterAST();
  }

  @Override
  public IdentExprAST visitIdentExpr(IdentExprContext ctx) {
    IdentExprAST identExprAST = new IdentExprAST(currSymTab, ctx);
    identExprAST.check();

    return identExprAST;
  }

  @Override
  public Node visitArrElemExpr(ArrElemExprContext ctx) {
    return visit(ctx.arrayElem());
  }

  @Override
  public Node visitParanExpr(ParanExprContext ctx) {
    return visit(ctx.expr());
  }

  @Override
  public UnOpExprAST visitUnOpExpr(UnOpExprContext ctx) {
    Node exprAST = visit(ctx.expr()); // Build AST to expr

    UnOpExprAST unOpAST = new UnOpExprAST(currSymTab, exprAST, ctx.unaryOper());
    unOpAST.check();

    return unOpAST;
  }

  @Override
  public ArithOpExprAST visitBitAndExpr(BitAndExprContext ctx) {
    Node eL = visit(ctx.expr(0));
    Node eR = visit(ctx.expr(1));

    ArithOpExprAST arithOpExprAST = new ArithOpExprAST(currSymTab, "&", eL, eR,
        ctx);
    arithOpExprAST.check();

    return arithOpExprAST;
  }

  @Override
  public ArithOpExprAST visitBitOrExpr(BitOrExprContext ctx) {
    Node eL = visit(ctx.expr(0));
    Node eR = visit(ctx.expr(1));

    ArithOpExprAST arithOpExprAST = new ArithOpExprAST(currSymTab, "|", eL, eR,
        ctx);
    arithOpExprAST.check();

    return arithOpExprAST;
  }

  @Override
  public ArithOpExprAST visitArithOpExpr_1(ArithOpExpr_1Context ctx) {
    Node eL = visit(ctx.expr(0));
    Node eR = visit(ctx.expr(1));

    // Set correct operator depending on context
    ArithmeticOper1Context arithCtx = ctx.arithmeticOper1();
    String op = null;
    if (arithCtx.MULT() != null) {
      op = "*";
    } else if (arithCtx.DIV() != null) {
      op = "/";
    } else if (arithCtx.MOD() != null) {
      op = "%";
    }
    assert (op != null);

    ArithOpExprAST arithOpExprAST = new ArithOpExprAST(currSymTab, op, eL, eR,
        ctx);
    arithOpExprAST.check();

    return arithOpExprAST;
  }

  @Override
  public ArithOpExprAST visitArithOpExpr_2(ArithOpExpr_2Context ctx) {
    Node eL = visit(ctx.expr(0));
    Node eR = visit(ctx.expr(1));

    // Set correct operator depending on context
    ArithmeticOper2Context arithCtx = ctx.arithmeticOper2();
    String op = null;
    if (arithCtx.PLUS() != null) {
      op = "+";
    } else if (arithCtx.MINUS() != null) {
      op = "-";
    }
    assert (op != null);

    ArithOpExprAST arithOpExprAST = new ArithOpExprAST(currSymTab, op, eL, eR,
        ctx);
    arithOpExprAST.check();

    return arithOpExprAST;
  }

  @Override
  public BinOpExprAST visitBinOpExpr_1(BinOpExpr_1Context ctx) {
    // BinOpExpr_1 only defined for int and char only
    Node eL = visit(ctx.expr(0));
    Node eR = visit(ctx.expr(1));

    // Set operation correctly depending on context
    BinaryOper1Context binOp1Ctx = ctx.binaryOper1();
    String operation = null;
    if (binOp1Ctx.GT() != null) {
      operation = ">";
    } else if (binOp1Ctx.GTE() != null) {
      operation = ">=";
    } else if (binOp1Ctx.LT() != null) {
      operation = "<";
    } else if (binOp1Ctx.LTE() != null) {
      operation = "<=";
    }
    assert (operation != null);

    BinOpExprAST binOpExprAST =
        new BinOpExprAST(currSymTab, Utils.INT_CHAR, operation, eL, eR, ctx);
    binOpExprAST.check();

    return binOpExprAST;
  }

  @Override
  public BinOpExprAST visitBinOpExpr_2(BinOpExpr_2Context ctx) {
    // BinOpExpr_2 defined for all types
    Node eL = visit(ctx.expr(0));
    Node eR = visit(ctx.expr(1));

    // Set operation correctly depending on context
    BinaryOper2Context binOp2Ctx = ctx.binaryOper2();
    String operation = null;
    if (binOp2Ctx.EQ() != null) {
      operation = "==";
    } else if (binOp2Ctx.NE() != null) {
      operation = "!=";
    }
    assert (operation != null);

    BinOpExprAST binOpExprAST =
        new BinOpExprAST(currSymTab, Utils.ALL_TYPES, operation, eL, eR, ctx);
    binOpExprAST.check();

    return binOpExprAST;
  }

  @Override
  public BinOpExprAST visitAndExpr(AndExprContext ctx) {
    // AND only compatible with bools
    Node eL = visit(ctx.expr(0));
    Node eR = visit(ctx.expr(1));

    BinOpExprAST binOpExprAST =
        new BinOpExprAST(currSymTab, Utils.BOOL, "&&", eL, eR, ctx);
    binOpExprAST.check();

    return binOpExprAST;
  }

  @Override
  public BinOpExprAST visitOrExpr(OrExprContext ctx) {
    // OR only compatible with bools
    Node eL = visit(ctx.expr(0));
    Node eR = visit(ctx.expr(1));

    BinOpExprAST binOpExprAST =
        new BinOpExprAST(currSymTab, Utils.BOOL, "||", eL, eR, ctx);
    binOpExprAST.check();

    return binOpExprAST;
  }

  @Override
  public Node visitClassAttrExpr(ClassAttrExprContext ctx) {
    return visitClassAttr(ctx.classAttr());
  }

  @Override
  public ArrayElemAST visitArrayElem(ArrayElemContext ctx) {
    String arrayName = ctx.IDENT().getText();

    // Check that each expression (index into the array) is an int
    Identifier identifier = currSymTab.lookupAll(arrayName);
    List<Node> indexes = new ArrayList<>();

    // Check for multidimensional arrays (all indexes have to be of type int)
    List<ExprContext> expressions = ctx.expr();
    for (ExprContext e : expressions) {
      Node exprAST = visit(e);
      TypeID exprType = exprAST.getIdentifier().getType();
      if (!(exprType instanceof IntID)) {
        SemanticErrorCollector
            .addIncompatibleType("int", exprType.getTypeName(),
                e.getText(), ctx.getStart().getLine(),
                e.getStart().getCharPositionInLine());
      }
      indexes.add(exprAST);
    }

    ArrayElemAST arrayElemAST =
        new ArrayElemAST(identifier, currSymTab, arrayName, indexes, ctx);
    arrayElemAST.check();

    return arrayElemAST;
  }

  @Override
  public ArrayLiterAST visitArrayLiter(ArrayLiterContext ctx) {
    List<Node> children = new ArrayList<>();

    for (ExprContext expr : ctx.expr()) {
      Node exprAST = visit(expr);
      children.add(exprAST);
    }
    ArrayID arrayID;
    if (children.isEmpty()) {
      arrayID = new ArrayID(new EmptyID());
    } else {
      arrayID = new ArrayID(children.get(0).getIdentifier().getType());
    }

    ArrayLiterAST arrayLiterAST = new ArrayLiterAST(currSymTab, arrayID,
        children, ctx);
    arrayLiterAST.check();
    return arrayLiterAST;
  }
}
