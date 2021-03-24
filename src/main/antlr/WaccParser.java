// Generated from ./WaccParser.g4 by ANTLR 4.9.1
package antlr;

  import frontend.errorlistener.SyntaxErrorListener;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WaccParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		END=1, BEGIN=2, IS=3, COMMA=4, FULL_STOP=5, ASSIGN=6, CLASS=7, NEW=8, 
		SKIP_LITER=9, READ=10, FREE=11, RETURN=12, EXIT=13, PRINT=14, PRINTLN=15, 
		IF=16, THEN=17, ELSE=18, FI=19, WHILE=20, DO=21, DONE=22, SEMI_COLON=23, 
		NEWPAIR=24, CALL=25, OPEN_SQUARE_BRACKETS=26, CLOSE_SQUARE_BRACKETS=27, 
		PAIR=28, FST=29, SND=30, FOR=31, IN=32, UNTIL=33, VAR=34, INT=35, BOOL=36, 
		CHAR=37, STRING=38, PLUS=39, MINUS=40, MULT=41, DIV=42, MOD=43, GT=44, 
		GTE=45, LT=46, LTE=47, EQ=48, NE=49, AND=50, OR=51, BIT_AND=52, BIT_OR=53, 
		NOT=54, LEN=55, ORD=56, CHR=57, BIT_NOT=58, OPEN_PARENTHESES=59, CLOSE_PARENTHESES=60, 
		TRUE=61, FALSE=62, NULL=63, VISIBILITY=64, INTEGER=65, BINARY_INTEGER=66, 
		OCTAL_INTEGER=67, HEXADECIMAL_INTEGER=68, WS=69, IDENT=70, CHAR_LITER=71, 
		STR_LITER=72, COMMENT=73;
	public static final int
		RULE_program = 0, RULE_classes = 1, RULE_attribute = 2, RULE_attributeList = 3, 
		RULE_classFunc = 4, RULE_classInstant = 5, RULE_callClassFunc = 6, RULE_classAttr = 7, 
		RULE_func = 8, RULE_paramList = 9, RULE_param = 10, RULE_stat = 11, RULE_assignLHS = 12, 
		RULE_assignRHS = 13, RULE_argList = 14, RULE_pairElem = 15, RULE_type = 16, 
		RULE_baseType = 17, RULE_arrayType = 18, RULE_pairType = 19, RULE_pairElemType = 20, 
		RULE_expr = 21, RULE_unaryOper = 22, RULE_arithmeticOper1 = 23, RULE_arithmeticOper2 = 24, 
		RULE_binaryOper1 = 25, RULE_binaryOper2 = 26, RULE_arrayElem = 27, RULE_arrayLiter = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "classes", "attribute", "attributeList", "classFunc", "classInstant", 
			"callClassFunc", "classAttr", "func", "paramList", "param", "stat", "assignLHS", 
			"assignRHS", "argList", "pairElem", "type", "baseType", "arrayType", 
			"pairType", "pairElemType", "expr", "unaryOper", "arithmeticOper1", "arithmeticOper2", 
			"binaryOper1", "binaryOper2", "arrayElem", "arrayLiter"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'end'", "'begin'", "'is'", "','", "'.'", "'='", "'class'", "'new'", 
			"'skip'", "'read'", "'free'", "'return'", "'exit'", "'print'", "'println'", 
			"'if'", "'then'", "'else'", "'fi'", "'while'", "'do'", "'done'", "';'", 
			"'newpair'", "'call'", "'['", "']'", "'pair'", "'fst'", "'snd'", "'for'", 
			"'in'", "'until'", "'var'", "'int'", "'bool'", "'char'", "'string'", 
			"'+'", "'-'", "'*'", "'/'", "'%'", "'>'", "'>='", "'<'", "'<='", "'=='", 
			"'!='", "'&&'", "'||'", "'&'", "'|'", "'!'", "'len'", "'ord'", "'chr'", 
			"'~'", "'('", "')'", "'true'", "'false'", "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "END", "BEGIN", "IS", "COMMA", "FULL_STOP", "ASSIGN", "CLASS", 
			"NEW", "SKIP_LITER", "READ", "FREE", "RETURN", "EXIT", "PRINT", "PRINTLN", 
			"IF", "THEN", "ELSE", "FI", "WHILE", "DO", "DONE", "SEMI_COLON", "NEWPAIR", 
			"CALL", "OPEN_SQUARE_BRACKETS", "CLOSE_SQUARE_BRACKETS", "PAIR", "FST", 
			"SND", "FOR", "IN", "UNTIL", "VAR", "INT", "BOOL", "CHAR", "STRING", 
			"PLUS", "MINUS", "MULT", "DIV", "MOD", "GT", "GTE", "LT", "LTE", "EQ", 
			"NE", "AND", "OR", "BIT_AND", "BIT_OR", "NOT", "LEN", "ORD", "CHR", "BIT_NOT", 
			"OPEN_PARENTHESES", "CLOSE_PARENTHESES", "TRUE", "FALSE", "NULL", "VISIBILITY", 
			"INTEGER", "BINARY_INTEGER", "OCTAL_INTEGER", "HEXADECIMAL_INTEGER", 
			"WS", "IDENT", "CHAR_LITER", "STR_LITER", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "WaccParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	  SyntaxErrorListener syntaxErr;

	  public WaccParser(TokenStream input, SyntaxErrorListener syntaxErr) {
	    this(input);
	    this.syntaxErr = syntaxErr;
	  }

	  public void checkOverflowError(boolean isMinus, long val) {
	    if (isMinus && -val < Integer.MIN_VALUE) {
	      syntaxErr.intError(this._ctx.start.getLine(), false);
	    } else if (!isMinus && val > Integer.MAX_VALUE) {
	      syntaxErr.intError(this._ctx.start.getLine(), true);
	    }
	  }

	public WaccParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(WaccParser.BEGIN, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(WaccParser.END, 0); }
		public TerminalNode EOF() { return getToken(WaccParser.EOF, 0); }
		public List<ClassesContext> classes() {
			return getRuleContexts(ClassesContext.class);
		}
		public ClassesContext classes(int i) {
			return getRuleContext(ClassesContext.class,i);
		}
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(BEGIN);
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CLASS) {
				{
				{
				setState(59);
				classes();
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(68);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(65);
					func();
					}
					} 
				}
				setState(70);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(71);
			stat(0);
			setState(72);
			match(END);
			setState(73);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassesContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(WaccParser.CLASS, 0); }
		public TerminalNode IDENT() { return getToken(WaccParser.IDENT, 0); }
		public TerminalNode IS() { return getToken(WaccParser.IS, 0); }
		public TerminalNode END() { return getToken(WaccParser.END, 0); }
		public AttributeListContext attributeList() {
			return getRuleContext(AttributeListContext.class,0);
		}
		public List<ClassFuncContext> classFunc() {
			return getRuleContexts(ClassFuncContext.class);
		}
		public ClassFuncContext classFunc(int i) {
			return getRuleContext(ClassFuncContext.class,i);
		}
		public ClassesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classes; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitClasses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassesContext classes() throws RecognitionException {
		ClassesContext _localctx = new ClassesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(CLASS);
			setState(76);
			match(IDENT);
			setState(77);
			match(IS);
			setState(79);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(78);
				attributeList();
				}
				break;
			}
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VISIBILITY) {
				{
				{
				setState(81);
				classFunc();
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(87);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public TerminalNode VISIBILITY() { return getToken(WaccParser.VISIBILITY, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(WaccParser.IDENT, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(VISIBILITY);
			setState(90);
			type();
			setState(91);
			match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeListContext extends ParserRuleContext {
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public List<TerminalNode> SEMI_COLON() { return getTokens(WaccParser.SEMI_COLON); }
		public TerminalNode SEMI_COLON(int i) {
			return getToken(WaccParser.SEMI_COLON, i);
		}
		public AttributeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitAttributeList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeListContext attributeList() throws RecognitionException {
		AttributeListContext _localctx = new AttributeListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_attributeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			attribute();
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMI_COLON) {
				{
				{
				setState(94);
				match(SEMI_COLON);
				setState(95);
				attribute();
				}
				}
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassFuncContext extends ParserRuleContext {
		public TerminalNode VISIBILITY() { return getToken(WaccParser.VISIBILITY, 0); }
		public FuncContext func() {
			return getRuleContext(FuncContext.class,0);
		}
		public ClassFuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classFunc; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitClassFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassFuncContext classFunc() throws RecognitionException {
		ClassFuncContext _localctx = new ClassFuncContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_classFunc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(VISIBILITY);
			setState(102);
			func();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassInstantContext extends ParserRuleContext {
		public TerminalNode NEW() { return getToken(WaccParser.NEW, 0); }
		public TerminalNode IDENT() { return getToken(WaccParser.IDENT, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(WaccParser.OPEN_PARENTHESES, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WaccParser.CLOSE_PARENTHESES, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public ClassInstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classInstant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitClassInstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassInstantContext classInstant() throws RecognitionException {
		ClassInstantContext _localctx = new ClassInstantContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_classInstant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(NEW);
			setState(105);
			match(IDENT);
			setState(106);
			match(OPEN_PARENTHESES);
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & ((1L << (PLUS - 39)) | (1L << (MINUS - 39)) | (1L << (NOT - 39)) | (1L << (LEN - 39)) | (1L << (ORD - 39)) | (1L << (CHR - 39)) | (1L << (BIT_NOT - 39)) | (1L << (OPEN_PARENTHESES - 39)) | (1L << (TRUE - 39)) | (1L << (FALSE - 39)) | (1L << (NULL - 39)) | (1L << (INTEGER - 39)) | (1L << (BINARY_INTEGER - 39)) | (1L << (OCTAL_INTEGER - 39)) | (1L << (HEXADECIMAL_INTEGER - 39)) | (1L << (IDENT - 39)) | (1L << (CHAR_LITER - 39)) | (1L << (STR_LITER - 39)))) != 0)) {
				{
				setState(107);
				argList();
				}
			}

			setState(110);
			match(CLOSE_PARENTHESES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallClassFuncContext extends ParserRuleContext {
		public TerminalNode CALL() { return getToken(WaccParser.CALL, 0); }
		public List<TerminalNode> IDENT() { return getTokens(WaccParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(WaccParser.IDENT, i);
		}
		public TerminalNode FULL_STOP() { return getToken(WaccParser.FULL_STOP, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(WaccParser.OPEN_PARENTHESES, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WaccParser.CLOSE_PARENTHESES, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public CallClassFuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callClassFunc; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitCallClassFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallClassFuncContext callClassFunc() throws RecognitionException {
		CallClassFuncContext _localctx = new CallClassFuncContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_callClassFunc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(CALL);
			setState(113);
			match(IDENT);
			setState(114);
			match(FULL_STOP);
			setState(115);
			match(IDENT);
			setState(116);
			match(OPEN_PARENTHESES);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & ((1L << (PLUS - 39)) | (1L << (MINUS - 39)) | (1L << (NOT - 39)) | (1L << (LEN - 39)) | (1L << (ORD - 39)) | (1L << (CHR - 39)) | (1L << (BIT_NOT - 39)) | (1L << (OPEN_PARENTHESES - 39)) | (1L << (TRUE - 39)) | (1L << (FALSE - 39)) | (1L << (NULL - 39)) | (1L << (INTEGER - 39)) | (1L << (BINARY_INTEGER - 39)) | (1L << (OCTAL_INTEGER - 39)) | (1L << (HEXADECIMAL_INTEGER - 39)) | (1L << (IDENT - 39)) | (1L << (CHAR_LITER - 39)) | (1L << (STR_LITER - 39)))) != 0)) {
				{
				setState(117);
				argList();
				}
			}

			setState(120);
			match(CLOSE_PARENTHESES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassAttrContext extends ParserRuleContext {
		public List<TerminalNode> IDENT() { return getTokens(WaccParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(WaccParser.IDENT, i);
		}
		public TerminalNode FULL_STOP() { return getToken(WaccParser.FULL_STOP, 0); }
		public ClassAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classAttr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitClassAttr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassAttrContext classAttr() throws RecognitionException {
		ClassAttrContext _localctx = new ClassAttrContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_classAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(IDENT);
			setState(123);
			match(FULL_STOP);
			setState(124);
			match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(WaccParser.IDENT, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(WaccParser.OPEN_PARENTHESES, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WaccParser.CLOSE_PARENTHESES, 0); }
		public TerminalNode IS() { return getToken(WaccParser.IS, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(WaccParser.END, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(126);
			type();
			setState(127);
			match(IDENT);
			setState(128);
			match(OPEN_PARENTHESES);
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAIR) | (1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING))) != 0)) {
				{
				setState(129);
				paramList();
				}
			}

			setState(132);
			match(CLOSE_PARENTHESES);
			setState(133);
			match(IS);
			setState(134);
			stat(0);
			setState(135);
			match(END);
			syntaxErr.returnCheck(this._ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(WaccParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(WaccParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			param();
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(139);
				match(COMMA);
				setState(140);
				param();
				}
				}
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(WaccParser.IDENT, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			type();
			setState(147);
			match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Print_statContext extends StatContext {
		public TerminalNode PRINT() { return getToken(WaccParser.PRINT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Print_statContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitPrint_stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Begin_statContext extends StatContext {
		public TerminalNode BEGIN() { return getToken(WaccParser.BEGIN, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(WaccParser.END, 0); }
		public Begin_statContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBegin_stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Assign_statContext extends StatContext {
		public AssignLHSContext assignLHS() {
			return getRuleContext(AssignLHSContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(WaccParser.ASSIGN, 0); }
		public AssignRHSContext assignRHS() {
			return getRuleContext(AssignRHSContext.class,0);
		}
		public Assign_statContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitAssign_stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Println_statContext extends StatContext {
		public TerminalNode PRINTLN() { return getToken(WaccParser.PRINTLN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Println_statContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitPrintln_stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Return_statContext extends StatContext {
		public TerminalNode RETURN() { return getToken(WaccParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Return_statContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitReturn_stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Exit_statContext extends StatContext {
		public TerminalNode EXIT() { return getToken(WaccParser.EXIT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Exit_statContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitExit_stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Skip_statContext extends StatContext {
		public TerminalNode SKIP_LITER() { return getToken(WaccParser.SKIP_LITER, 0); }
		public Skip_statContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitSkip_stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Free_statContext extends StatContext {
		public TerminalNode FREE() { return getToken(WaccParser.FREE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Free_statContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitFree_stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class For_statContext extends StatContext {
		public TerminalNode FOR() { return getToken(WaccParser.FOR, 0); }
		public TerminalNode IDENT() { return getToken(WaccParser.IDENT, 0); }
		public TerminalNode IN() { return getToken(WaccParser.IN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode UNTIL() { return getToken(WaccParser.UNTIL, 0); }
		public TerminalNode DO() { return getToken(WaccParser.DO, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode DONE() { return getToken(WaccParser.DONE, 0); }
		public For_statContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitFor_stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Var_decl_statContext extends StatContext {
		public TerminalNode IDENT() { return getToken(WaccParser.IDENT, 0); }
		public TerminalNode ASSIGN() { return getToken(WaccParser.ASSIGN, 0); }
		public AssignRHSContext assignRHS() {
			return getRuleContext(AssignRHSContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode VAR() { return getToken(WaccParser.VAR, 0); }
		public Var_decl_statContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitVar_decl_stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class While_statContext extends StatContext {
		public TerminalNode WHILE() { return getToken(WaccParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DO() { return getToken(WaccParser.DO, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode DONE() { return getToken(WaccParser.DONE, 0); }
		public While_statContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitWhile_stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class If_statContext extends StatContext {
		public TerminalNode IF() { return getToken(WaccParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode THEN() { return getToken(WaccParser.THEN, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(WaccParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(WaccParser.FI, 0); }
		public If_statContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitIf_stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Sequence_statContext extends StatContext {
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode SEMI_COLON() { return getToken(WaccParser.SEMI_COLON, 0); }
		public Sequence_statContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitSequence_stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Read_statContext extends StatContext {
		public TerminalNode READ() { return getToken(WaccParser.READ, 0); }
		public AssignLHSContext assignLHS() {
			return getRuleContext(AssignLHSContext.class,0);
		}
		public Read_statContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitRead_stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Do_while_statContext extends StatContext {
		public TerminalNode DO() { return getToken(WaccParser.DO, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(WaccParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DONE() { return getToken(WaccParser.DONE, 0); }
		public Do_while_statContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitDo_while_stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Class_var_decl_statContext extends StatContext {
		public List<TerminalNode> IDENT() { return getTokens(WaccParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(WaccParser.IDENT, i);
		}
		public TerminalNode ASSIGN() { return getToken(WaccParser.ASSIGN, 0); }
		public AssignRHSContext assignRHS() {
			return getRuleContext(AssignRHSContext.class,0);
		}
		public Class_var_decl_statContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitClass_var_decl_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		return stat(0);
	}

	private StatContext stat(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatContext _localctx = new StatContext(_ctx, _parentState);
		StatContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_stat, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				_localctx = new Skip_statContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(150);
				match(SKIP_LITER);
				}
				break;
			case 2:
				{
				_localctx = new Var_decl_statContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(153);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PAIR:
				case INT:
				case BOOL:
				case CHAR:
				case STRING:
					{
					setState(151);
					type();
					}
					break;
				case VAR:
					{
					setState(152);
					match(VAR);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(155);
				match(IDENT);
				setState(156);
				match(ASSIGN);
				setState(157);
				assignRHS();
				}
				break;
			case 3:
				{
				_localctx = new Class_var_decl_statContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(158);
				match(IDENT);
				setState(159);
				match(IDENT);
				setState(160);
				match(ASSIGN);
				setState(161);
				assignRHS();
				}
				break;
			case 4:
				{
				_localctx = new Assign_statContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(162);
				assignLHS();
				setState(163);
				match(ASSIGN);
				setState(164);
				assignRHS();
				}
				break;
			case 5:
				{
				_localctx = new Read_statContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(166);
				match(READ);
				setState(167);
				assignLHS();
				}
				break;
			case 6:
				{
				_localctx = new Free_statContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(168);
				match(FREE);
				setState(169);
				expr(0);
				}
				break;
			case 7:
				{
				_localctx = new Return_statContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(170);
				match(RETURN);
				setState(171);
				expr(0);
				}
				break;
			case 8:
				{
				_localctx = new Exit_statContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(172);
				match(EXIT);
				setState(173);
				expr(0);
				}
				break;
			case 9:
				{
				_localctx = new Print_statContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(174);
				match(PRINT);
				setState(175);
				expr(0);
				}
				break;
			case 10:
				{
				_localctx = new Println_statContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(176);
				match(PRINTLN);
				setState(177);
				expr(0);
				}
				break;
			case 11:
				{
				_localctx = new If_statContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(178);
				match(IF);
				setState(179);
				expr(0);
				setState(180);
				match(THEN);
				setState(181);
				stat(0);
				setState(182);
				match(ELSE);
				setState(183);
				stat(0);
				setState(184);
				match(FI);
				}
				break;
			case 12:
				{
				_localctx = new While_statContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(186);
				match(WHILE);
				setState(187);
				expr(0);
				setState(188);
				match(DO);
				setState(189);
				stat(0);
				setState(190);
				match(DONE);
				}
				break;
			case 13:
				{
				_localctx = new Do_while_statContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(192);
				match(DO);
				setState(193);
				stat(0);
				setState(194);
				match(WHILE);
				setState(195);
				expr(0);
				setState(196);
				match(DONE);
				}
				break;
			case 14:
				{
				_localctx = new For_statContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(198);
				match(FOR);
				setState(199);
				match(IDENT);
				setState(200);
				match(IN);
				setState(201);
				expr(0);
				setState(202);
				match(UNTIL);
				setState(203);
				expr(0);
				setState(204);
				match(DO);
				setState(205);
				stat(0);
				setState(206);
				match(DONE);
				}
				break;
			case 15:
				{
				_localctx = new Begin_statContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(208);
				match(BEGIN);
				setState(209);
				stat(0);
				setState(210);
				match(END);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(219);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Sequence_statContext(new StatContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_stat);
					setState(214);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(215);
					match(SEMI_COLON);
					setState(216);
					stat(3);
					}
					} 
				}
				setState(221);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AssignLHSContext extends ParserRuleContext {
		public AssignLHSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignLHS; }
	 
		public AssignLHSContext() { }
		public void copyFrom(AssignLHSContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Classattr_assignLHSContext extends AssignLHSContext {
		public ClassAttrContext classAttr() {
			return getRuleContext(ClassAttrContext.class,0);
		}
		public Classattr_assignLHSContext(AssignLHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitClassattr_assignLHS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayElem_assignLHSContext extends AssignLHSContext {
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public ArrayElem_assignLHSContext(AssignLHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitArrayElem_assignLHS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Ident_assignLHSContext extends AssignLHSContext {
		public TerminalNode IDENT() { return getToken(WaccParser.IDENT, 0); }
		public Ident_assignLHSContext(AssignLHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitIdent_assignLHS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PairElem_assignLHSContext extends AssignLHSContext {
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public PairElem_assignLHSContext(AssignLHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitPairElem_assignLHS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignLHSContext assignLHS() throws RecognitionException {
		AssignLHSContext _localctx = new AssignLHSContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_assignLHS);
		try {
			setState(226);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new Ident_assignLHSContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(222);
				match(IDENT);
				}
				break;
			case 2:
				_localctx = new ArrayElem_assignLHSContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(223);
				arrayElem();
				}
				break;
			case 3:
				_localctx = new PairElem_assignLHSContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(224);
				pairElem();
				}
				break;
			case 4:
				_localctx = new Classattr_assignLHSContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(225);
				classAttr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignRHSContext extends ParserRuleContext {
		public AssignRHSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignRHS; }
	 
		public AssignRHSContext() { }
		public void copyFrom(AssignRHSContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PairElem_assignRHSContext extends AssignRHSContext {
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public PairElem_assignRHSContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitPairElem_assignRHS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewPair_assignRHSContext extends AssignRHSContext {
		public TerminalNode NEWPAIR() { return getToken(WaccParser.NEWPAIR, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(WaccParser.OPEN_PARENTHESES, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(WaccParser.COMMA, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WaccParser.CLOSE_PARENTHESES, 0); }
		public NewPair_assignRHSContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitNewPair_assignRHS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CallClassFunc_assignRHSContext extends AssignRHSContext {
		public CallClassFuncContext callClassFunc() {
			return getRuleContext(CallClassFuncContext.class,0);
		}
		public CallClassFunc_assignRHSContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitCallClassFunc_assignRHS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayLiter_assignRHSContext extends AssignRHSContext {
		public ArrayLiterContext arrayLiter() {
			return getRuleContext(ArrayLiterContext.class,0);
		}
		public ArrayLiter_assignRHSContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitArrayLiter_assignRHS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Call_assignRHSContext extends AssignRHSContext {
		public TerminalNode CALL() { return getToken(WaccParser.CALL, 0); }
		public TerminalNode IDENT() { return getToken(WaccParser.IDENT, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(WaccParser.OPEN_PARENTHESES, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WaccParser.CLOSE_PARENTHESES, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public Call_assignRHSContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitCall_assignRHS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_assignRHSContext extends AssignRHSContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_assignRHSContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitExpr_assignRHS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Instantiate_assignRHSContext extends AssignRHSContext {
		public ClassInstantContext classInstant() {
			return getRuleContext(ClassInstantContext.class,0);
		}
		public Instantiate_assignRHSContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitInstantiate_assignRHS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignRHSContext assignRHS() throws RecognitionException {
		AssignRHSContext _localctx = new AssignRHSContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_assignRHS);
		int _la;
		try {
			setState(247);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new Expr_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				expr(0);
				}
				break;
			case 2:
				_localctx = new ArrayLiter_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(229);
				arrayLiter();
				}
				break;
			case 3:
				_localctx = new NewPair_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(230);
				match(NEWPAIR);
				setState(231);
				match(OPEN_PARENTHESES);
				setState(232);
				expr(0);
				setState(233);
				match(COMMA);
				setState(234);
				expr(0);
				setState(235);
				match(CLOSE_PARENTHESES);
				}
				break;
			case 4:
				_localctx = new PairElem_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(237);
				pairElem();
				}
				break;
			case 5:
				_localctx = new Call_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(238);
				match(CALL);
				setState(239);
				match(IDENT);
				setState(240);
				match(OPEN_PARENTHESES);
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & ((1L << (PLUS - 39)) | (1L << (MINUS - 39)) | (1L << (NOT - 39)) | (1L << (LEN - 39)) | (1L << (ORD - 39)) | (1L << (CHR - 39)) | (1L << (BIT_NOT - 39)) | (1L << (OPEN_PARENTHESES - 39)) | (1L << (TRUE - 39)) | (1L << (FALSE - 39)) | (1L << (NULL - 39)) | (1L << (INTEGER - 39)) | (1L << (BINARY_INTEGER - 39)) | (1L << (OCTAL_INTEGER - 39)) | (1L << (HEXADECIMAL_INTEGER - 39)) | (1L << (IDENT - 39)) | (1L << (CHAR_LITER - 39)) | (1L << (STR_LITER - 39)))) != 0)) {
					{
					setState(241);
					argList();
					}
				}

				setState(244);
				match(CLOSE_PARENTHESES);
				}
				break;
			case 6:
				_localctx = new CallClassFunc_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(245);
				callClassFunc();
				}
				break;
			case 7:
				_localctx = new Instantiate_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(246);
				classInstant();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(WaccParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(WaccParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			expr(0);
			setState(254);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(250);
				match(COMMA);
				setState(251);
				expr(0);
				}
				}
				setState(256);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemContext extends ParserRuleContext {
		public TerminalNode FST() { return getToken(WaccParser.FST, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SND() { return getToken(WaccParser.SND, 0); }
		public PairElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitPairElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemContext pairElem() throws RecognitionException {
		PairElemContext _localctx = new PairElemContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_pairElem);
		try {
			setState(261);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FST:
				enterOuterAlt(_localctx, 1);
				{
				setState(257);
				match(FST);
				setState(258);
				expr(0);
				}
				break;
			case SND:
				enterOuterAlt(_localctx, 2);
				{
				setState(259);
				match(SND);
				setState(260);
				expr(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_type);
		try {
			setState(266);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(263);
				baseType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(264);
				arrayType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(265);
				pairType();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BaseTypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(WaccParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(WaccParser.BOOL, 0); }
		public TerminalNode CHAR() { return getToken(WaccParser.CHAR, 0); }
		public TerminalNode STRING() { return getToken(WaccParser.STRING, 0); }
		public BaseTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBaseType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayTypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public List<TerminalNode> OPEN_SQUARE_BRACKETS() { return getTokens(WaccParser.OPEN_SQUARE_BRACKETS); }
		public TerminalNode OPEN_SQUARE_BRACKETS(int i) {
			return getToken(WaccParser.OPEN_SQUARE_BRACKETS, i);
		}
		public List<TerminalNode> CLOSE_SQUARE_BRACKETS() { return getTokens(WaccParser.CLOSE_SQUARE_BRACKETS); }
		public TerminalNode CLOSE_SQUARE_BRACKETS(int i) {
			return getToken(WaccParser.CLOSE_SQUARE_BRACKETS, i);
		}
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_arrayType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
				{
				setState(270);
				baseType();
				}
				break;
			case PAIR:
				{
				setState(271);
				pairType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(276); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(274);
				match(OPEN_SQUARE_BRACKETS);
				setState(275);
				match(CLOSE_SQUARE_BRACKETS);
				}
				}
				setState(278); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OPEN_SQUARE_BRACKETS );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairTypeContext extends ParserRuleContext {
		public TerminalNode PAIR() { return getToken(WaccParser.PAIR, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(WaccParser.OPEN_PARENTHESES, 0); }
		public List<PairElemTypeContext> pairElemType() {
			return getRuleContexts(PairElemTypeContext.class);
		}
		public PairElemTypeContext pairElemType(int i) {
			return getRuleContext(PairElemTypeContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(WaccParser.COMMA, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WaccParser.CLOSE_PARENTHESES, 0); }
		public PairTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitPairType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairTypeContext pairType() throws RecognitionException {
		PairTypeContext _localctx = new PairTypeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_pairType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(PAIR);
			setState(281);
			match(OPEN_PARENTHESES);
			setState(282);
			pairElemType();
			setState(283);
			match(COMMA);
			setState(284);
			pairElemType();
			setState(285);
			match(CLOSE_PARENTHESES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemTypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public TerminalNode PAIR() { return getToken(WaccParser.PAIR, 0); }
		public PairElemTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElemType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitPairElemType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemTypeContext pairElemType() throws RecognitionException {
		PairElemTypeContext _localctx = new PairElemTypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_pairElemType);
		try {
			setState(290);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(287);
				baseType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(288);
				arrayType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(289);
				match(PAIR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrElemExprContext extends ExprContext {
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public ArrElemExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitArrElemExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassAttrExprContext extends ExprContext {
		public ClassAttrContext classAttr() {
			return getRuleContext(ClassAttrContext.class,0);
		}
		public ClassAttrExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitClassAttrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinOpExpr_2Context extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BinaryOper2Context binaryOper2() {
			return getRuleContext(BinaryOper2Context.class,0);
		}
		public BinOpExpr_2Context(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBinOpExpr_2(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR() { return getToken(WaccParser.OR, 0); }
		public OrExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntLiterContext extends ExprContext {
		public Token INTEGER;
		public TerminalNode INTEGER() { return getToken(WaccParser.INTEGER, 0); }
		public TerminalNode PLUS() { return getToken(WaccParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(WaccParser.MINUS, 0); }
		public IntLiterContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitIntLiter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolLiterContext extends ExprContext {
		public TerminalNode TRUE() { return getToken(WaccParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(WaccParser.FALSE, 0); }
		public BoolLiterContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBoolLiter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentExprContext extends ExprContext {
		public TerminalNode IDENT() { return getToken(WaccParser.IDENT, 0); }
		public IdentExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitIdentExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StrLiterContext extends ExprContext {
		public TerminalNode STR_LITER() { return getToken(WaccParser.STR_LITER, 0); }
		public StrLiterContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitStrLiter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitOrExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode BIT_OR() { return getToken(WaccParser.BIT_OR, 0); }
		public BitOrExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArithOpExpr_2Context extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArithmeticOper2Context arithmeticOper2() {
			return getRuleContext(ArithmeticOper2Context.class,0);
		}
		public ArithOpExpr_2Context(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitArithOpExpr_2(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PairLiterContext extends ExprContext {
		public TerminalNode NULL() { return getToken(WaccParser.NULL, 0); }
		public PairLiterContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitPairLiter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArithOpExpr_1Context extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArithmeticOper1Context arithmeticOper1() {
			return getRuleContext(ArithmeticOper1Context.class,0);
		}
		public ArithOpExpr_1Context(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitArithOpExpr_1(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinIntLiterContext extends ExprContext {
		public Token BINARY_INTEGER;
		public TerminalNode BINARY_INTEGER() { return getToken(WaccParser.BINARY_INTEGER, 0); }
		public TerminalNode PLUS() { return getToken(WaccParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(WaccParser.MINUS, 0); }
		public BinIntLiterContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBinIntLiter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinOpExpr_1Context extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BinaryOper1Context binaryOper1() {
			return getRuleContext(BinaryOper1Context.class,0);
		}
		public BinOpExpr_1Context(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBinOpExpr_1(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CharLiterContext extends ExprContext {
		public TerminalNode CHAR_LITER() { return getToken(WaccParser.CHAR_LITER, 0); }
		public CharLiterContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitCharLiter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class HexIntLiterContext extends ExprContext {
		public Token HEXADECIMAL_INTEGER;
		public TerminalNode HEXADECIMAL_INTEGER() { return getToken(WaccParser.HEXADECIMAL_INTEGER, 0); }
		public TerminalNode PLUS() { return getToken(WaccParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(WaccParser.MINUS, 0); }
		public HexIntLiterContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitHexIntLiter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitAndExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode BIT_AND() { return getToken(WaccParser.BIT_AND, 0); }
		public BitAndExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OctIntLiterContext extends ExprContext {
		public Token OCTAL_INTEGER;
		public TerminalNode OCTAL_INTEGER() { return getToken(WaccParser.OCTAL_INTEGER, 0); }
		public TerminalNode PLUS() { return getToken(WaccParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(WaccParser.MINUS, 0); }
		public OctIntLiterContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitOctIntLiter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParanExprContext extends ExprContext {
		public TerminalNode OPEN_PARENTHESES() { return getToken(WaccParser.OPEN_PARENTHESES, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WaccParser.CLOSE_PARENTHESES, 0); }
		public ParanExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitParanExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnOpExprContext extends ExprContext {
		public UnaryOperContext unaryOper() {
			return getRuleContext(UnaryOperContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnOpExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitUnOpExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode AND() { return getToken(WaccParser.AND, 0); }
		public AndExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 42;
		enterRecursionRule(_localctx, 42, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				_localctx = new IntLiterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(294);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(293);
					_la = _input.LA(1);
					if ( !(_la==PLUS || _la==MINUS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				{
				setState(296);
				((IntLiterContext)_localctx).INTEGER = match(INTEGER);

				    boolean isMinus = ((IntLiterContext) this._ctx).MINUS() != null;
				    checkOverflowError(isMinus, Long.valueOf((((IntLiterContext)_localctx).INTEGER!=null?((IntLiterContext)_localctx).INTEGER.getText():null)));
				  
				}
				}
				break;
			case 2:
				{
				_localctx = new BinIntLiterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(299);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(298);
					_la = _input.LA(1);
					if ( !(_la==PLUS || _la==MINUS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				{
				setState(301);
				((BinIntLiterContext)_localctx).BINARY_INTEGER = match(BINARY_INTEGER);

				    String token = (((BinIntLiterContext)_localctx).BINARY_INTEGER!=null?((BinIntLiterContext)_localctx).BINARY_INTEGER.getText():null).substring(2);
				    boolean isMinus = ((BinIntLiterContext) this._ctx).MINUS() != null;
				    long val = Long.parseLong(token, 2);
				    checkOverflowError(isMinus, val);
				  
				}
				}
				break;
			case 3:
				{
				_localctx = new OctIntLiterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(304);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(303);
					_la = _input.LA(1);
					if ( !(_la==PLUS || _la==MINUS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				{
				setState(306);
				((OctIntLiterContext)_localctx).OCTAL_INTEGER = match(OCTAL_INTEGER);

				    String token = (((OctIntLiterContext)_localctx).OCTAL_INTEGER!=null?((OctIntLiterContext)_localctx).OCTAL_INTEGER.getText():null).substring(2);
				    boolean isMinus = ((OctIntLiterContext) this._ctx).MINUS() != null;
				    long val = Long.parseLong(token, 8);
				    checkOverflowError(isMinus, val);
				  
				}
				}
				break;
			case 4:
				{
				_localctx = new HexIntLiterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(309);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(308);
					_la = _input.LA(1);
					if ( !(_la==PLUS || _la==MINUS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				{
				setState(311);
				((HexIntLiterContext)_localctx).HEXADECIMAL_INTEGER = match(HEXADECIMAL_INTEGER);

				    String token = (((HexIntLiterContext)_localctx).HEXADECIMAL_INTEGER!=null?((HexIntLiterContext)_localctx).HEXADECIMAL_INTEGER.getText():null).substring(2);
				    boolean isMinus = ((HexIntLiterContext) this._ctx).MINUS() != null;
				    long val = Long.parseLong(token, 16);
				    checkOverflowError(isMinus, val);
				  
				}
				}
				break;
			case 5:
				{
				_localctx = new BoolLiterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(313);
				_la = _input.LA(1);
				if ( !(_la==TRUE || _la==FALSE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 6:
				{
				_localctx = new CharLiterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(314);
				match(CHAR_LITER);
				}
				break;
			case 7:
				{
				_localctx = new StrLiterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(315);
				match(STR_LITER);
				}
				break;
			case 8:
				{
				_localctx = new PairLiterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(316);
				match(NULL);
				}
				break;
			case 9:
				{
				_localctx = new IdentExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(317);
				match(IDENT);
				}
				break;
			case 10:
				{
				_localctx = new ArrElemExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(318);
				arrayElem();
				}
				break;
			case 11:
				{
				_localctx = new ParanExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(319);
				match(OPEN_PARENTHESES);
				setState(320);
				expr(0);
				setState(321);
				match(CLOSE_PARENTHESES);
				}
				break;
			case 12:
				{
				_localctx = new UnOpExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(323);
				unaryOper();
				setState(324);
				expr(10);
				}
				break;
			case 13:
				{
				_localctx = new ClassAttrExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(326);
				classAttr();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(359);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(357);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
					case 1:
						{
						_localctx = new BitAndExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(329);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(330);
						match(BIT_AND);
						setState(331);
						expr(10);
						}
						break;
					case 2:
						{
						_localctx = new BitOrExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(332);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(333);
						match(BIT_OR);
						setState(334);
						expr(9);
						}
						break;
					case 3:
						{
						_localctx = new ArithOpExpr_1Context(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(335);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(336);
						arithmeticOper1();
						setState(337);
						expr(8);
						}
						break;
					case 4:
						{
						_localctx = new ArithOpExpr_2Context(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(339);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(340);
						arithmeticOper2();
						setState(341);
						expr(7);
						}
						break;
					case 5:
						{
						_localctx = new BinOpExpr_1Context(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(343);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(344);
						binaryOper1();
						setState(345);
						expr(6);
						}
						break;
					case 6:
						{
						_localctx = new BinOpExpr_2Context(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(347);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(348);
						binaryOper2();
						setState(349);
						expr(5);
						}
						break;
					case 7:
						{
						_localctx = new AndExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(351);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(352);
						match(AND);
						setState(353);
						expr(4);
						}
						break;
					case 8:
						{
						_localctx = new OrExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(354);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(355);
						match(OR);
						setState(356);
						expr(3);
						}
						break;
					}
					} 
				}
				setState(361);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class UnaryOperContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(WaccParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(WaccParser.MINUS, 0); }
		public TerminalNode LEN() { return getToken(WaccParser.LEN, 0); }
		public TerminalNode ORD() { return getToken(WaccParser.ORD, 0); }
		public TerminalNode CHR() { return getToken(WaccParser.CHR, 0); }
		public TerminalNode BIT_NOT() { return getToken(WaccParser.BIT_NOT, 0); }
		public UnaryOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitUnaryOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOperContext unaryOper() throws RecognitionException {
		UnaryOperContext _localctx = new UnaryOperContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_unaryOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << BIT_NOT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArithmeticOper1Context extends ParserRuleContext {
		public TerminalNode MULT() { return getToken(WaccParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(WaccParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(WaccParser.MOD, 0); }
		public ArithmeticOper1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticOper1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitArithmeticOper1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticOper1Context arithmeticOper1() throws RecognitionException {
		ArithmeticOper1Context _localctx = new ArithmeticOper1Context(_ctx, getState());
		enterRule(_localctx, 46, RULE_arithmeticOper1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULT) | (1L << DIV) | (1L << MOD))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArithmeticOper2Context extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(WaccParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(WaccParser.MINUS, 0); }
		public ArithmeticOper2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticOper2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitArithmeticOper2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticOper2Context arithmeticOper2() throws RecognitionException {
		ArithmeticOper2Context _localctx = new ArithmeticOper2Context(_ctx, getState());
		enterRule(_localctx, 48, RULE_arithmeticOper2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryOper1Context extends ParserRuleContext {
		public TerminalNode GT() { return getToken(WaccParser.GT, 0); }
		public TerminalNode GTE() { return getToken(WaccParser.GTE, 0); }
		public TerminalNode LT() { return getToken(WaccParser.LT, 0); }
		public TerminalNode LTE() { return getToken(WaccParser.LTE, 0); }
		public BinaryOper1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOper1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBinaryOper1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOper1Context binaryOper1() throws RecognitionException {
		BinaryOper1Context _localctx = new BinaryOper1Context(_ctx, getState());
		enterRule(_localctx, 50, RULE_binaryOper1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << GTE) | (1L << LT) | (1L << LTE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryOper2Context extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(WaccParser.EQ, 0); }
		public TerminalNode NE() { return getToken(WaccParser.NE, 0); }
		public BinaryOper2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOper2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitBinaryOper2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOper2Context binaryOper2() throws RecognitionException {
		BinaryOper2Context _localctx = new BinaryOper2Context(_ctx, getState());
		enterRule(_localctx, 52, RULE_binaryOper2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			_la = _input.LA(1);
			if ( !(_la==EQ || _la==NE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayElemContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(WaccParser.IDENT, 0); }
		public List<TerminalNode> OPEN_SQUARE_BRACKETS() { return getTokens(WaccParser.OPEN_SQUARE_BRACKETS); }
		public TerminalNode OPEN_SQUARE_BRACKETS(int i) {
			return getToken(WaccParser.OPEN_SQUARE_BRACKETS, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> CLOSE_SQUARE_BRACKETS() { return getTokens(WaccParser.CLOSE_SQUARE_BRACKETS); }
		public TerminalNode CLOSE_SQUARE_BRACKETS(int i) {
			return getToken(WaccParser.CLOSE_SQUARE_BRACKETS, i);
		}
		public ArrayElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitArrayElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayElemContext arrayElem() throws RecognitionException {
		ArrayElemContext _localctx = new ArrayElemContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			match(IDENT);
			setState(377); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(373);
					match(OPEN_SQUARE_BRACKETS);
					setState(374);
					expr(0);
					setState(375);
					match(CLOSE_SQUARE_BRACKETS);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(379); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayLiterContext extends ParserRuleContext {
		public TerminalNode OPEN_SQUARE_BRACKETS() { return getToken(WaccParser.OPEN_SQUARE_BRACKETS, 0); }
		public TerminalNode CLOSE_SQUARE_BRACKETS() { return getToken(WaccParser.CLOSE_SQUARE_BRACKETS, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(WaccParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(WaccParser.COMMA, i);
		}
		public ArrayLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WaccParserVisitor ) return ((WaccParserVisitor<? extends T>)visitor).visitArrayLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiterContext arrayLiter() throws RecognitionException {
		ArrayLiterContext _localctx = new ArrayLiterContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_arrayLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			match(OPEN_SQUARE_BRACKETS);
			setState(390);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & ((1L << (PLUS - 39)) | (1L << (MINUS - 39)) | (1L << (NOT - 39)) | (1L << (LEN - 39)) | (1L << (ORD - 39)) | (1L << (CHR - 39)) | (1L << (BIT_NOT - 39)) | (1L << (OPEN_PARENTHESES - 39)) | (1L << (TRUE - 39)) | (1L << (FALSE - 39)) | (1L << (NULL - 39)) | (1L << (INTEGER - 39)) | (1L << (BINARY_INTEGER - 39)) | (1L << (OCTAL_INTEGER - 39)) | (1L << (HEXADECIMAL_INTEGER - 39)) | (1L << (IDENT - 39)) | (1L << (CHAR_LITER - 39)) | (1L << (STR_LITER - 39)))) != 0)) {
				{
				setState(382);
				expr(0);
				setState(387);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(383);
					match(COMMA);
					setState(384);
					expr(0);
					}
					}
					setState(389);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(392);
			match(CLOSE_SQUARE_BRACKETS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return stat_sempred((StatContext)_localctx, predIndex);
		case 21:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean stat_sempred(StatContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 9);
		case 2:
			return precpred(_ctx, 8);
		case 3:
			return precpred(_ctx, 7);
		case 4:
			return precpred(_ctx, 6);
		case 5:
			return precpred(_ctx, 5);
		case 6:
			return precpred(_ctx, 4);
		case 7:
			return precpred(_ctx, 3);
		case 8:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3K\u018d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\7\2?\n\2\f"+
		"\2\16\2B\13\2\3\2\7\2E\n\2\f\2\16\2H\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\5\3R\n\3\3\3\7\3U\n\3\f\3\16\3X\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\7\5c\n\5\f\5\16\5f\13\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7o\n\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\5\by\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\5\n\u0085\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\7\13"+
		"\u0090\n\13\f\13\16\13\u0093\13\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\5\r\u009c"+
		"\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\5\r\u00d7\n\r\3\r\3\r\3\r\7\r\u00dc\n\r\f\r\16"+
		"\r\u00df\13\r\3\16\3\16\3\16\3\16\5\16\u00e5\n\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00f5\n\17\3\17"+
		"\3\17\3\17\5\17\u00fa\n\17\3\20\3\20\3\20\7\20\u00ff\n\20\f\20\16\20\u0102"+
		"\13\20\3\21\3\21\3\21\3\21\5\21\u0108\n\21\3\22\3\22\3\22\5\22\u010d\n"+
		"\22\3\23\3\23\3\24\3\24\5\24\u0113\n\24\3\24\3\24\6\24\u0117\n\24\r\24"+
		"\16\24\u0118\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\5\26\u0125"+
		"\n\26\3\27\3\27\5\27\u0129\n\27\3\27\3\27\3\27\5\27\u012e\n\27\3\27\3"+
		"\27\3\27\5\27\u0133\n\27\3\27\3\27\3\27\5\27\u0138\n\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27"+
		"\u014a\n\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\7\27\u0168\n\27\f\27\16\27\u016b\13\27\3\30\3\30\3\31\3\31"+
		"\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\35\3\35\6\35\u017c\n\35"+
		"\r\35\16\35\u017d\3\36\3\36\3\36\3\36\7\36\u0184\n\36\f\36\16\36\u0187"+
		"\13\36\5\36\u0189\n\36\3\36\3\36\3\36\2\4\30,\37\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:\2\t\3\2%(\3\2)*\3\2?@\4\2**"+
		"8<\3\2+-\3\2.\61\3\2\62\63\2\u01b5\2<\3\2\2\2\4M\3\2\2\2\6[\3\2\2\2\b"+
		"_\3\2\2\2\ng\3\2\2\2\fj\3\2\2\2\16r\3\2\2\2\20|\3\2\2\2\22\u0080\3\2\2"+
		"\2\24\u008c\3\2\2\2\26\u0094\3\2\2\2\30\u00d6\3\2\2\2\32\u00e4\3\2\2\2"+
		"\34\u00f9\3\2\2\2\36\u00fb\3\2\2\2 \u0107\3\2\2\2\"\u010c\3\2\2\2$\u010e"+
		"\3\2\2\2&\u0112\3\2\2\2(\u011a\3\2\2\2*\u0124\3\2\2\2,\u0149\3\2\2\2."+
		"\u016c\3\2\2\2\60\u016e\3\2\2\2\62\u0170\3\2\2\2\64\u0172\3\2\2\2\66\u0174"+
		"\3\2\2\28\u0176\3\2\2\2:\u017f\3\2\2\2<@\7\4\2\2=?\5\4\3\2>=\3\2\2\2?"+
		"B\3\2\2\2@>\3\2\2\2@A\3\2\2\2AF\3\2\2\2B@\3\2\2\2CE\5\22\n\2DC\3\2\2\2"+
		"EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3\2\2\2IJ\5\30\r\2JK\7\3\2"+
		"\2KL\7\2\2\3L\3\3\2\2\2MN\7\t\2\2NO\7H\2\2OQ\7\5\2\2PR\5\b\5\2QP\3\2\2"+
		"\2QR\3\2\2\2RV\3\2\2\2SU\5\n\6\2TS\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2"+
		"\2WY\3\2\2\2XV\3\2\2\2YZ\7\3\2\2Z\5\3\2\2\2[\\\7B\2\2\\]\5\"\22\2]^\7"+
		"H\2\2^\7\3\2\2\2_d\5\6\4\2`a\7\31\2\2ac\5\6\4\2b`\3\2\2\2cf\3\2\2\2db"+
		"\3\2\2\2de\3\2\2\2e\t\3\2\2\2fd\3\2\2\2gh\7B\2\2hi\5\22\n\2i\13\3\2\2"+
		"\2jk\7\n\2\2kl\7H\2\2ln\7=\2\2mo\5\36\20\2nm\3\2\2\2no\3\2\2\2op\3\2\2"+
		"\2pq\7>\2\2q\r\3\2\2\2rs\7\33\2\2st\7H\2\2tu\7\7\2\2uv\7H\2\2vx\7=\2\2"+
		"wy\5\36\20\2xw\3\2\2\2xy\3\2\2\2yz\3\2\2\2z{\7>\2\2{\17\3\2\2\2|}\7H\2"+
		"\2}~\7\7\2\2~\177\7H\2\2\177\21\3\2\2\2\u0080\u0081\5\"\22\2\u0081\u0082"+
		"\7H\2\2\u0082\u0084\7=\2\2\u0083\u0085\5\24\13\2\u0084\u0083\3\2\2\2\u0084"+
		"\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\7>\2\2\u0087\u0088\7\5"+
		"\2\2\u0088\u0089\5\30\r\2\u0089\u008a\7\3\2\2\u008a\u008b\b\n\1\2\u008b"+
		"\23\3\2\2\2\u008c\u0091\5\26\f\2\u008d\u008e\7\6\2\2\u008e\u0090\5\26"+
		"\f\2\u008f\u008d\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\25\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u0095\5\"\22"+
		"\2\u0095\u0096\7H\2\2\u0096\27\3\2\2\2\u0097\u0098\b\r\1\2\u0098\u00d7"+
		"\7\13\2\2\u0099\u009c\5\"\22\2\u009a\u009c\7$\2\2\u009b\u0099\3\2\2\2"+
		"\u009b\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e\7H\2\2\u009e\u009f"+
		"\7\b\2\2\u009f\u00d7\5\34\17\2\u00a0\u00a1\7H\2\2\u00a1\u00a2\7H\2\2\u00a2"+
		"\u00a3\7\b\2\2\u00a3\u00d7\5\34\17\2\u00a4\u00a5\5\32\16\2\u00a5\u00a6"+
		"\7\b\2\2\u00a6\u00a7\5\34\17\2\u00a7\u00d7\3\2\2\2\u00a8\u00a9\7\f\2\2"+
		"\u00a9\u00d7\5\32\16\2\u00aa\u00ab\7\r\2\2\u00ab\u00d7\5,\27\2\u00ac\u00ad"+
		"\7\16\2\2\u00ad\u00d7\5,\27\2\u00ae\u00af\7\17\2\2\u00af\u00d7\5,\27\2"+
		"\u00b0\u00b1\7\20\2\2\u00b1\u00d7\5,\27\2\u00b2\u00b3\7\21\2\2\u00b3\u00d7"+
		"\5,\27\2\u00b4\u00b5\7\22\2\2\u00b5\u00b6\5,\27\2\u00b6\u00b7\7\23\2\2"+
		"\u00b7\u00b8\5\30\r\2\u00b8\u00b9\7\24\2\2\u00b9\u00ba\5\30\r\2\u00ba"+
		"\u00bb\7\25\2\2\u00bb\u00d7\3\2\2\2\u00bc\u00bd\7\26\2\2\u00bd\u00be\5"+
		",\27\2\u00be\u00bf\7\27\2\2\u00bf\u00c0\5\30\r\2\u00c0\u00c1\7\30\2\2"+
		"\u00c1\u00d7\3\2\2\2\u00c2\u00c3\7\27\2\2\u00c3\u00c4\5\30\r\2\u00c4\u00c5"+
		"\7\26\2\2\u00c5\u00c6\5,\27\2\u00c6\u00c7\7\30\2\2\u00c7\u00d7\3\2\2\2"+
		"\u00c8\u00c9\7!\2\2\u00c9\u00ca\7H\2\2\u00ca\u00cb\7\"\2\2\u00cb\u00cc"+
		"\5,\27\2\u00cc\u00cd\7#\2\2\u00cd\u00ce\5,\27\2\u00ce\u00cf\7\27\2\2\u00cf"+
		"\u00d0\5\30\r\2\u00d0\u00d1\7\30\2\2\u00d1\u00d7\3\2\2\2\u00d2\u00d3\7"+
		"\4\2\2\u00d3\u00d4\5\30\r\2\u00d4\u00d5\7\3\2\2\u00d5\u00d7\3\2\2\2\u00d6"+
		"\u0097\3\2\2\2\u00d6\u009b\3\2\2\2\u00d6\u00a0\3\2\2\2\u00d6\u00a4\3\2"+
		"\2\2\u00d6\u00a8\3\2\2\2\u00d6\u00aa\3\2\2\2\u00d6\u00ac\3\2\2\2\u00d6"+
		"\u00ae\3\2\2\2\u00d6\u00b0\3\2\2\2\u00d6\u00b2\3\2\2\2\u00d6\u00b4\3\2"+
		"\2\2\u00d6\u00bc\3\2\2\2\u00d6\u00c2\3\2\2\2\u00d6\u00c8\3\2\2\2\u00d6"+
		"\u00d2\3\2\2\2\u00d7\u00dd\3\2\2\2\u00d8\u00d9\f\4\2\2\u00d9\u00da\7\31"+
		"\2\2\u00da\u00dc\5\30\r\5\u00db\u00d8\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd"+
		"\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\31\3\2\2\2\u00df\u00dd\3\2\2"+
		"\2\u00e0\u00e5\7H\2\2\u00e1\u00e5\58\35\2\u00e2\u00e5\5 \21\2\u00e3\u00e5"+
		"\5\20\t\2\u00e4\u00e0\3\2\2\2\u00e4\u00e1\3\2\2\2\u00e4\u00e2\3\2\2\2"+
		"\u00e4\u00e3\3\2\2\2\u00e5\33\3\2\2\2\u00e6\u00fa\5,\27\2\u00e7\u00fa"+
		"\5:\36\2\u00e8\u00e9\7\32\2\2\u00e9\u00ea\7=\2\2\u00ea\u00eb\5,\27\2\u00eb"+
		"\u00ec\7\6\2\2\u00ec\u00ed\5,\27\2\u00ed\u00ee\7>\2\2\u00ee\u00fa\3\2"+
		"\2\2\u00ef\u00fa\5 \21\2\u00f0\u00f1\7\33\2\2\u00f1\u00f2\7H\2\2\u00f2"+
		"\u00f4\7=\2\2\u00f3\u00f5\5\36\20\2\u00f4\u00f3\3\2\2\2\u00f4\u00f5\3"+
		"\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00fa\7>\2\2\u00f7\u00fa\5\16\b\2\u00f8"+
		"\u00fa\5\f\7\2\u00f9\u00e6\3\2\2\2\u00f9\u00e7\3\2\2\2\u00f9\u00e8\3\2"+
		"\2\2\u00f9\u00ef\3\2\2\2\u00f9\u00f0\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9"+
		"\u00f8\3\2\2\2\u00fa\35\3\2\2\2\u00fb\u0100\5,\27\2\u00fc\u00fd\7\6\2"+
		"\2\u00fd\u00ff\5,\27\2\u00fe\u00fc\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u00fe"+
		"\3\2\2\2\u0100\u0101\3\2\2\2\u0101\37\3\2\2\2\u0102\u0100\3\2\2\2\u0103"+
		"\u0104\7\37\2\2\u0104\u0108\5,\27\2\u0105\u0106\7 \2\2\u0106\u0108\5,"+
		"\27\2\u0107\u0103\3\2\2\2\u0107\u0105\3\2\2\2\u0108!\3\2\2\2\u0109\u010d"+
		"\5$\23\2\u010a\u010d\5&\24\2\u010b\u010d\5(\25\2\u010c\u0109\3\2\2\2\u010c"+
		"\u010a\3\2\2\2\u010c\u010b\3\2\2\2\u010d#\3\2\2\2\u010e\u010f\t\2\2\2"+
		"\u010f%\3\2\2\2\u0110\u0113\5$\23\2\u0111\u0113\5(\25\2\u0112\u0110\3"+
		"\2\2\2\u0112\u0111\3\2\2\2\u0113\u0116\3\2\2\2\u0114\u0115\7\34\2\2\u0115"+
		"\u0117\7\35\2\2\u0116\u0114\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0116\3"+
		"\2\2\2\u0118\u0119\3\2\2\2\u0119\'\3\2\2\2\u011a\u011b\7\36\2\2\u011b"+
		"\u011c\7=\2\2\u011c\u011d\5*\26\2\u011d\u011e\7\6\2\2\u011e\u011f\5*\26"+
		"\2\u011f\u0120\7>\2\2\u0120)\3\2\2\2\u0121\u0125\5$\23\2\u0122\u0125\5"+
		"&\24\2\u0123\u0125\7\36\2\2\u0124\u0121\3\2\2\2\u0124\u0122\3\2\2\2\u0124"+
		"\u0123\3\2\2\2\u0125+\3\2\2\2\u0126\u0128\b\27\1\2\u0127\u0129\t\3\2\2"+
		"\u0128\u0127\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012b"+
		"\7C\2\2\u012b\u014a\b\27\1\2\u012c\u012e\t\3\2\2\u012d\u012c\3\2\2\2\u012d"+
		"\u012e\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0130\7D\2\2\u0130\u014a\b\27"+
		"\1\2\u0131\u0133\t\3\2\2\u0132\u0131\3\2\2\2\u0132\u0133\3\2\2\2\u0133"+
		"\u0134\3\2\2\2\u0134\u0135\7E\2\2\u0135\u014a\b\27\1\2\u0136\u0138\t\3"+
		"\2\2\u0137\u0136\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u0139\3\2\2\2\u0139"+
		"\u013a\7F\2\2\u013a\u014a\b\27\1\2\u013b\u014a\t\4\2\2\u013c\u014a\7I"+
		"\2\2\u013d\u014a\7J\2\2\u013e\u014a\7A\2\2\u013f\u014a\7H\2\2\u0140\u014a"+
		"\58\35\2\u0141\u0142\7=\2\2\u0142\u0143\5,\27\2\u0143\u0144\7>\2\2\u0144"+
		"\u014a\3\2\2\2\u0145\u0146\5.\30\2\u0146\u0147\5,\27\f\u0147\u014a\3\2"+
		"\2\2\u0148\u014a\5\20\t\2\u0149\u0126\3\2\2\2\u0149\u012d\3\2\2\2\u0149"+
		"\u0132\3\2\2\2\u0149\u0137\3\2\2\2\u0149\u013b\3\2\2\2\u0149\u013c\3\2"+
		"\2\2\u0149\u013d\3\2\2\2\u0149\u013e\3\2\2\2\u0149\u013f\3\2\2\2\u0149"+
		"\u0140\3\2\2\2\u0149\u0141\3\2\2\2\u0149\u0145\3\2\2\2\u0149\u0148\3\2"+
		"\2\2\u014a\u0169\3\2\2\2\u014b\u014c\f\13\2\2\u014c\u014d\7\66\2\2\u014d"+
		"\u0168\5,\27\f\u014e\u014f\f\n\2\2\u014f\u0150\7\67\2\2\u0150\u0168\5"+
		",\27\13\u0151\u0152\f\t\2\2\u0152\u0153\5\60\31\2\u0153\u0154\5,\27\n"+
		"\u0154\u0168\3\2\2\2\u0155\u0156\f\b\2\2\u0156\u0157\5\62\32\2\u0157\u0158"+
		"\5,\27\t\u0158\u0168\3\2\2\2\u0159\u015a\f\7\2\2\u015a\u015b\5\64\33\2"+
		"\u015b\u015c\5,\27\b\u015c\u0168\3\2\2\2\u015d\u015e\f\6\2\2\u015e\u015f"+
		"\5\66\34\2\u015f\u0160\5,\27\7\u0160\u0168\3\2\2\2\u0161\u0162\f\5\2\2"+
		"\u0162\u0163\7\64\2\2\u0163\u0168\5,\27\6\u0164\u0165\f\4\2\2\u0165\u0166"+
		"\7\65\2\2\u0166\u0168\5,\27\5\u0167\u014b\3\2\2\2\u0167\u014e\3\2\2\2"+
		"\u0167\u0151\3\2\2\2\u0167\u0155\3\2\2\2\u0167\u0159\3\2\2\2\u0167\u015d"+
		"\3\2\2\2\u0167\u0161\3\2\2\2\u0167\u0164\3\2\2\2\u0168\u016b\3\2\2\2\u0169"+
		"\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a-\3\2\2\2\u016b\u0169\3\2\2\2"+
		"\u016c\u016d\t\5\2\2\u016d/\3\2\2\2\u016e\u016f\t\6\2\2\u016f\61\3\2\2"+
		"\2\u0170\u0171\t\3\2\2\u0171\63\3\2\2\2\u0172\u0173\t\7\2\2\u0173\65\3"+
		"\2\2\2\u0174\u0175\t\b\2\2\u0175\67\3\2\2\2\u0176\u017b\7H\2\2\u0177\u0178"+
		"\7\34\2\2\u0178\u0179\5,\27\2\u0179\u017a\7\35\2\2\u017a\u017c\3\2\2\2"+
		"\u017b\u0177\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017b\3\2\2\2\u017d\u017e"+
		"\3\2\2\2\u017e9\3\2\2\2\u017f\u0188\7\34\2\2\u0180\u0185\5,\27\2\u0181"+
		"\u0182\7\6\2\2\u0182\u0184\5,\27\2\u0183\u0181\3\2\2\2\u0184\u0187\3\2"+
		"\2\2\u0185\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0189\3\2\2\2\u0187"+
		"\u0185\3\2\2\2\u0188\u0180\3\2\2\2\u0188\u0189\3\2\2\2\u0189\u018a\3\2"+
		"\2\2\u018a\u018b\7\35\2\2\u018b;\3\2\2\2!@FQVdnx\u0084\u0091\u009b\u00d6"+
		"\u00dd\u00e4\u00f4\u00f9\u0100\u0107\u010c\u0112\u0118\u0124\u0128\u012d"+
		"\u0132\u0137\u0149\u0167\u0169\u017d\u0185\u0188";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}