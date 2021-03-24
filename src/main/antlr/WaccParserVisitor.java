// Generated from ./WaccParser.g4 by ANTLR 4.9.1
package antlr;

  import frontend.errorlistener.SyntaxErrorListener;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link WaccParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface WaccParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link WaccParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(WaccParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#classes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClasses(WaccParser.ClassesContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute(WaccParser.AttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#attributeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeList(WaccParser.AttributeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#classFunc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassFunc(WaccParser.ClassFuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#classInstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassInstant(WaccParser.ClassInstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#callClassFunc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallClassFunc(WaccParser.CallClassFuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#classAttr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassAttr(WaccParser.ClassAttrContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(WaccParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(WaccParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(WaccParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code print_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint_stat(WaccParser.Print_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code begin_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBegin_stat(WaccParser.Begin_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_stat(WaccParser.Assign_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code println_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintln_stat(WaccParser.Println_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code return_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_stat(WaccParser.Return_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exit_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExit_stat(WaccParser.Exit_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code skip_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkip_stat(WaccParser.Skip_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code free_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFree_stat(WaccParser.Free_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code for_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_stat(WaccParser.For_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code var_decl_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_decl_stat(WaccParser.Var_decl_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code while_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_stat(WaccParser.While_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stat(WaccParser.If_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sequence_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequence_stat(WaccParser.Sequence_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code read_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead_stat(WaccParser.Read_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code do_while_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDo_while_stat(WaccParser.Do_while_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code class_var_decl_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_var_decl_stat(WaccParser.Class_var_decl_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ident_assignLHS}
	 * labeled alternative in {@link WaccParser#assignLHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent_assignLHS(WaccParser.Ident_assignLHSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayElem_assignLHS}
	 * labeled alternative in {@link WaccParser#assignLHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayElem_assignLHS(WaccParser.ArrayElem_assignLHSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pairElem_assignLHS}
	 * labeled alternative in {@link WaccParser#assignLHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElem_assignLHS(WaccParser.PairElem_assignLHSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classattr_assignLHS}
	 * labeled alternative in {@link WaccParser#assignLHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassattr_assignLHS(WaccParser.Classattr_assignLHSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_assignRHS}
	 * labeled alternative in {@link WaccParser#assignRHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_assignRHS(WaccParser.Expr_assignRHSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayLiter_assignRHS}
	 * labeled alternative in {@link WaccParser#assignRHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiter_assignRHS(WaccParser.ArrayLiter_assignRHSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newPair_assignRHS}
	 * labeled alternative in {@link WaccParser#assignRHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewPair_assignRHS(WaccParser.NewPair_assignRHSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pairElem_assignRHS}
	 * labeled alternative in {@link WaccParser#assignRHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElem_assignRHS(WaccParser.PairElem_assignRHSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code call_assignRHS}
	 * labeled alternative in {@link WaccParser#assignRHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall_assignRHS(WaccParser.Call_assignRHSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callClassFunc_assignRHS}
	 * labeled alternative in {@link WaccParser#assignRHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallClassFunc_assignRHS(WaccParser.CallClassFunc_assignRHSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instantiate_assignRHS}
	 * labeled alternative in {@link WaccParser#assignRHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstantiate_assignRHS(WaccParser.Instantiate_assignRHSContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(WaccParser.ArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#pairElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElem(WaccParser.PairElemContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(WaccParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType(WaccParser.BaseTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(WaccParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#pairType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairType(WaccParser.PairTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#pairElemType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElemType(WaccParser.PairElemTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrElemExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrElemExpr(WaccParser.ArrElemExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classAttrExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassAttrExpr(WaccParser.ClassAttrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binOpExpr_2}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOpExpr_2(WaccParser.BinOpExpr_2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(WaccParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intLiter}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLiter(WaccParser.IntLiterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolLiter}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiter(WaccParser.BoolLiterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentExpr(WaccParser.IdentExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code strLiter}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrLiter(WaccParser.StrLiterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitOrExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitOrExpr(WaccParser.BitOrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arithOpExpr_2}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithOpExpr_2(WaccParser.ArithOpExpr_2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code pairLiter}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairLiter(WaccParser.PairLiterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arithOpExpr_1}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithOpExpr_1(WaccParser.ArithOpExpr_1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code binIntLiter}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinIntLiter(WaccParser.BinIntLiterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binOpExpr_1}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOpExpr_1(WaccParser.BinOpExpr_1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code charLiter}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharLiter(WaccParser.CharLiterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hexIntLiter}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexIntLiter(WaccParser.HexIntLiterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitAndExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitAndExpr(WaccParser.BitAndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code octIntLiter}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctIntLiter(WaccParser.OctIntLiterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paranExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParanExpr(WaccParser.ParanExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unOpExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnOpExpr(WaccParser.UnOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(WaccParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#unaryOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOper(WaccParser.UnaryOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#arithmeticOper1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticOper1(WaccParser.ArithmeticOper1Context ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#arithmeticOper2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticOper2(WaccParser.ArithmeticOper2Context ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#binaryOper1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOper1(WaccParser.BinaryOper1Context ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#binaryOper2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOper2(WaccParser.BinaryOper2Context ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#arrayElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayElem(WaccParser.ArrayElemContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#arrayLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiter(WaccParser.ArrayLiterContext ctx);
}