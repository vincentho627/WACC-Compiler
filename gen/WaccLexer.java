// Generated from /Users/vho001/Desktop/wacc_18/antlr_config/WaccLexer.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WaccLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"END", "BEGIN", "IS", "COMMA", "FULL_STOP", "ASSIGN", "CLASS", "NEW", 
			"SKIP_LITER", "READ", "FREE", "RETURN", "EXIT", "PRINT", "PRINTLN", "IF", 
			"THEN", "ELSE", "FI", "WHILE", "DO", "DONE", "SEMI_COLON", "NEWPAIR", 
			"CALL", "OPEN_SQUARE_BRACKETS", "CLOSE_SQUARE_BRACKETS", "PAIR", "FST", 
			"SND", "FOR", "IN", "UNTIL", "VAR", "INT", "BOOL", "CHAR", "STRING", 
			"PLUS", "MINUS", "MULT", "DIV", "MOD", "GT", "GTE", "LT", "LTE", "EQ", 
			"NE", "AND", "OR", "BIT_AND", "BIT_OR", "NOT", "LEN", "ORD", "CHR", "BIT_NOT", 
			"OPEN_PARENTHESES", "CLOSE_PARENTHESES", "TRUE", "FALSE", "NULL", "PUBLIC", 
			"PRIVATE", "VISIBILITY", "DIGIT", "BINARY_FORMATTER", "BINARY_DIGIT", 
			"OCTAL_FORMATTER", "OCTAL_DIGIT", "HEXADECIMAL_FORMATTER", "HEXADECIMAL_DIGIT", 
			"LOWERCASE", "UPPERCASE", "UNDERSCORE", "SINGLE_QUOTE", "DOUBLE_QUOTE", 
			"INTEGER", "BINARY_INTEGER", "OCTAL_INTEGER", "HEXADECIMAL_INTEGER", 
			"ESCAPED_CHARACTER", "CHARACTER", "WS", "IDENT", "CHAR_LITER", "STR_LITER", 
			"COMMENT"
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


	public WaccLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "WaccLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2K\u0226\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3"+
		"#\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'"+
		"\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3.\3/\3/\3\60\3\60"+
		"\3\60\3\61\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\63\3\64\3\64\3\64\3\65"+
		"\3\65\3\66\3\66\3\67\3\67\38\38\38\38\39\39\39\39\3:\3:\3:\3:\3;\3;\3"+
		"<\3<\3=\3=\3>\3>\3>\3>\3>\3?\3?\3?\3?\3?\3?\3@\3@\3@\3@\3@\3A\3A\3A\3"+
		"A\3A\3A\3A\3B\3B\3B\3B\3B\3B\3B\3B\3C\3C\5C\u01b9\nC\3D\3D\3E\3E\3E\3"+
		"F\3F\3G\3G\3G\3H\3H\3I\3I\3I\3J\3J\5J\u01cc\nJ\3K\3K\3L\3L\3M\3M\3N\3"+
		"N\3O\3O\3P\6P\u01d9\nP\rP\16P\u01da\3Q\3Q\6Q\u01df\nQ\rQ\16Q\u01e0\3R"+
		"\3R\6R\u01e5\nR\rR\16R\u01e6\3S\3S\6S\u01eb\nS\rS\16S\u01ec\3T\3T\3T\3"+
		"T\3T\5T\u01f4\nT\3U\3U\5U\u01f8\nU\3V\6V\u01fb\nV\rV\16V\u01fc\3V\3V\3"+
		"W\3W\3W\5W\u0204\nW\3W\3W\3W\3W\7W\u020a\nW\fW\16W\u020d\13W\3X\3X\3X"+
		"\3X\3Y\3Y\7Y\u0215\nY\fY\16Y\u0218\13Y\3Y\3Y\3Z\3Z\7Z\u021e\nZ\fZ\16Z"+
		"\u0221\13Z\3Z\3Z\3Z\3Z\3\u021f\2[\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60"+
		"_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081\2\u0083\2\u0085"+
		"B\u0087\2\u0089\2\u008b\2\u008d\2\u008f\2\u0091\2\u0093\2\u0095\2\u0097"+
		"\2\u0099\2\u009b\2\u009d\2\u009fC\u00a1D\u00a3E\u00a5F\u00a7\2\u00a9\2"+
		"\u00abG\u00adH\u00afI\u00b1J\u00b3K\3\2\6\4\2CHch\7\2\62\62ddhhpptt\5"+
		"\2$$))^^\5\2\13\f\17\17\"\"\2\u0228\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2"+
		"\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3"+
		"\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2"+
		"\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2"+
		"\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2["+
		"\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2"+
		"\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2"+
		"\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2"+
		"\2\u0085\3\2\2\2\2\u009f\3\2\2\2\2\u00a1\3\2\2\2\2\u00a3\3\2\2\2\2\u00a5"+
		"\3\2\2\2\2\u00ab\3\2\2\2\2\u00ad\3\2\2\2\2\u00af\3\2\2\2\2\u00b1\3\2\2"+
		"\2\2\u00b3\3\2\2\2\3\u00b5\3\2\2\2\5\u00b9\3\2\2\2\7\u00bf\3\2\2\2\t\u00c2"+
		"\3\2\2\2\13\u00c4\3\2\2\2\r\u00c6\3\2\2\2\17\u00c8\3\2\2\2\21\u00ce\3"+
		"\2\2\2\23\u00d2\3\2\2\2\25\u00d7\3\2\2\2\27\u00dc\3\2\2\2\31\u00e1\3\2"+
		"\2\2\33\u00e8\3\2\2\2\35\u00ed\3\2\2\2\37\u00f3\3\2\2\2!\u00fb\3\2\2\2"+
		"#\u00fe\3\2\2\2%\u0103\3\2\2\2\'\u0108\3\2\2\2)\u010b\3\2\2\2+\u0111\3"+
		"\2\2\2-\u0114\3\2\2\2/\u0119\3\2\2\2\61\u011b\3\2\2\2\63\u0123\3\2\2\2"+
		"\65\u0128\3\2\2\2\67\u012a\3\2\2\29\u012c\3\2\2\2;\u0131\3\2\2\2=\u0135"+
		"\3\2\2\2?\u0139\3\2\2\2A\u013d\3\2\2\2C\u0140\3\2\2\2E\u0146\3\2\2\2G"+
		"\u014a\3\2\2\2I\u014e\3\2\2\2K\u0153\3\2\2\2M\u0158\3\2\2\2O\u015f\3\2"+
		"\2\2Q\u0161\3\2\2\2S\u0163\3\2\2\2U\u0165\3\2\2\2W\u0167\3\2\2\2Y\u0169"+
		"\3\2\2\2[\u016b\3\2\2\2]\u016e\3\2\2\2_\u0170\3\2\2\2a\u0173\3\2\2\2c"+
		"\u0176\3\2\2\2e\u0179\3\2\2\2g\u017c\3\2\2\2i\u017f\3\2\2\2k\u0181\3\2"+
		"\2\2m\u0183\3\2\2\2o\u0185\3\2\2\2q\u0189\3\2\2\2s\u018d\3\2\2\2u\u0191"+
		"\3\2\2\2w\u0193\3\2\2\2y\u0195\3\2\2\2{\u0197\3\2\2\2}\u019c\3\2\2\2\177"+
		"\u01a2\3\2\2\2\u0081\u01a7\3\2\2\2\u0083\u01ae\3\2\2\2\u0085\u01b8\3\2"+
		"\2\2\u0087\u01ba\3\2\2\2\u0089\u01bc\3\2\2\2\u008b\u01bf\3\2\2\2\u008d"+
		"\u01c1\3\2\2\2\u008f\u01c4\3\2\2\2\u0091\u01c6\3\2\2\2\u0093\u01cb\3\2"+
		"\2\2\u0095\u01cd\3\2\2\2\u0097\u01cf\3\2\2\2\u0099\u01d1\3\2\2\2\u009b"+
		"\u01d3\3\2\2\2\u009d\u01d5\3\2\2\2\u009f\u01d8\3\2\2\2\u00a1\u01dc\3\2"+
		"\2\2\u00a3\u01e2\3\2\2\2\u00a5\u01e8\3\2\2\2\u00a7\u01ee\3\2\2\2\u00a9"+
		"\u01f7\3\2\2\2\u00ab\u01fa\3\2\2\2\u00ad\u0203\3\2\2\2\u00af\u020e\3\2"+
		"\2\2\u00b1\u0212\3\2\2\2\u00b3\u021b\3\2\2\2\u00b5\u00b6\7g\2\2\u00b6"+
		"\u00b7\7p\2\2\u00b7\u00b8\7f\2\2\u00b8\4\3\2\2\2\u00b9\u00ba\7d\2\2\u00ba"+
		"\u00bb\7g\2\2\u00bb\u00bc\7i\2\2\u00bc\u00bd\7k\2\2\u00bd\u00be\7p\2\2"+
		"\u00be\6\3\2\2\2\u00bf\u00c0\7k\2\2\u00c0\u00c1\7u\2\2\u00c1\b\3\2\2\2"+
		"\u00c2\u00c3\7.\2\2\u00c3\n\3\2\2\2\u00c4\u00c5\7\60\2\2\u00c5\f\3\2\2"+
		"\2\u00c6\u00c7\7?\2\2\u00c7\16\3\2\2\2\u00c8\u00c9\7e\2\2\u00c9\u00ca"+
		"\7n\2\2\u00ca\u00cb\7c\2\2\u00cb\u00cc\7u\2\2\u00cc\u00cd\7u\2\2\u00cd"+
		"\20\3\2\2\2\u00ce\u00cf\7p\2\2\u00cf\u00d0\7g\2\2\u00d0\u00d1\7y\2\2\u00d1"+
		"\22\3\2\2\2\u00d2\u00d3\7u\2\2\u00d3\u00d4\7m\2\2\u00d4\u00d5\7k\2\2\u00d5"+
		"\u00d6\7r\2\2\u00d6\24\3\2\2\2\u00d7\u00d8\7t\2\2\u00d8\u00d9\7g\2\2\u00d9"+
		"\u00da\7c\2\2\u00da\u00db\7f\2\2\u00db\26\3\2\2\2\u00dc\u00dd\7h\2\2\u00dd"+
		"\u00de\7t\2\2\u00de\u00df\7g\2\2\u00df\u00e0\7g\2\2\u00e0\30\3\2\2\2\u00e1"+
		"\u00e2\7t\2\2\u00e2\u00e3\7g\2\2\u00e3\u00e4\7v\2\2\u00e4\u00e5\7w\2\2"+
		"\u00e5\u00e6\7t\2\2\u00e6\u00e7\7p\2\2\u00e7\32\3\2\2\2\u00e8\u00e9\7"+
		"g\2\2\u00e9\u00ea\7z\2\2\u00ea\u00eb\7k\2\2\u00eb\u00ec\7v\2\2\u00ec\34"+
		"\3\2\2\2\u00ed\u00ee\7r\2\2\u00ee\u00ef\7t\2\2\u00ef\u00f0\7k\2\2\u00f0"+
		"\u00f1\7p\2\2\u00f1\u00f2\7v\2\2\u00f2\36\3\2\2\2\u00f3\u00f4\7r\2\2\u00f4"+
		"\u00f5\7t\2\2\u00f5\u00f6\7k\2\2\u00f6\u00f7\7p\2\2\u00f7\u00f8\7v\2\2"+
		"\u00f8\u00f9\7n\2\2\u00f9\u00fa\7p\2\2\u00fa \3\2\2\2\u00fb\u00fc\7k\2"+
		"\2\u00fc\u00fd\7h\2\2\u00fd\"\3\2\2\2\u00fe\u00ff\7v\2\2\u00ff\u0100\7"+
		"j\2\2\u0100\u0101\7g\2\2\u0101\u0102\7p\2\2\u0102$\3\2\2\2\u0103\u0104"+
		"\7g\2\2\u0104\u0105\7n\2\2\u0105\u0106\7u\2\2\u0106\u0107\7g\2\2\u0107"+
		"&\3\2\2\2\u0108\u0109\7h\2\2\u0109\u010a\7k\2\2\u010a(\3\2\2\2\u010b\u010c"+
		"\7y\2\2\u010c\u010d\7j\2\2\u010d\u010e\7k\2\2\u010e\u010f\7n\2\2\u010f"+
		"\u0110\7g\2\2\u0110*\3\2\2\2\u0111\u0112\7f\2\2\u0112\u0113\7q\2\2\u0113"+
		",\3\2\2\2\u0114\u0115\7f\2\2\u0115\u0116\7q\2\2\u0116\u0117\7p\2\2\u0117"+
		"\u0118\7g\2\2\u0118.\3\2\2\2\u0119\u011a\7=\2\2\u011a\60\3\2\2\2\u011b"+
		"\u011c\7p\2\2\u011c\u011d\7g\2\2\u011d\u011e\7y\2\2\u011e\u011f\7r\2\2"+
		"\u011f\u0120\7c\2\2\u0120\u0121\7k\2\2\u0121\u0122\7t\2\2\u0122\62\3\2"+
		"\2\2\u0123\u0124\7e\2\2\u0124\u0125\7c\2\2\u0125\u0126\7n\2\2\u0126\u0127"+
		"\7n\2\2\u0127\64\3\2\2\2\u0128\u0129\7]\2\2\u0129\66\3\2\2\2\u012a\u012b"+
		"\7_\2\2\u012b8\3\2\2\2\u012c\u012d\7r\2\2\u012d\u012e\7c\2\2\u012e\u012f"+
		"\7k\2\2\u012f\u0130\7t\2\2\u0130:\3\2\2\2\u0131\u0132\7h\2\2\u0132\u0133"+
		"\7u\2\2\u0133\u0134\7v\2\2\u0134<\3\2\2\2\u0135\u0136\7u\2\2\u0136\u0137"+
		"\7p\2\2\u0137\u0138\7f\2\2\u0138>\3\2\2\2\u0139\u013a\7h\2\2\u013a\u013b"+
		"\7q\2\2\u013b\u013c\7t\2\2\u013c@\3\2\2\2\u013d\u013e\7k\2\2\u013e\u013f"+
		"\7p\2\2\u013fB\3\2\2\2\u0140\u0141\7w\2\2\u0141\u0142\7p\2\2\u0142\u0143"+
		"\7v\2\2\u0143\u0144\7k\2\2\u0144\u0145\7n\2\2\u0145D\3\2\2\2\u0146\u0147"+
		"\7x\2\2\u0147\u0148\7c\2\2\u0148\u0149\7t\2\2\u0149F\3\2\2\2\u014a\u014b"+
		"\7k\2\2\u014b\u014c\7p\2\2\u014c\u014d\7v\2\2\u014dH\3\2\2\2\u014e\u014f"+
		"\7d\2\2\u014f\u0150\7q\2\2\u0150\u0151\7q\2\2\u0151\u0152\7n\2\2\u0152"+
		"J\3\2\2\2\u0153\u0154\7e\2\2\u0154\u0155\7j\2\2\u0155\u0156\7c\2\2\u0156"+
		"\u0157\7t\2\2\u0157L\3\2\2\2\u0158\u0159\7u\2\2\u0159\u015a\7v\2\2\u015a"+
		"\u015b\7t\2\2\u015b\u015c\7k\2\2\u015c\u015d\7p\2\2\u015d\u015e\7i\2\2"+
		"\u015eN\3\2\2\2\u015f\u0160\7-\2\2\u0160P\3\2\2\2\u0161\u0162\7/\2\2\u0162"+
		"R\3\2\2\2\u0163\u0164\7,\2\2\u0164T\3\2\2\2\u0165\u0166\7\61\2\2\u0166"+
		"V\3\2\2\2\u0167\u0168\7\'\2\2\u0168X\3\2\2\2\u0169\u016a\7@\2\2\u016a"+
		"Z\3\2\2\2\u016b\u016c\7@\2\2\u016c\u016d\7?\2\2\u016d\\\3\2\2\2\u016e"+
		"\u016f\7>\2\2\u016f^\3\2\2\2\u0170\u0171\7>\2\2\u0171\u0172\7?\2\2\u0172"+
		"`\3\2\2\2\u0173\u0174\7?\2\2\u0174\u0175\7?\2\2\u0175b\3\2\2\2\u0176\u0177"+
		"\7#\2\2\u0177\u0178\7?\2\2\u0178d\3\2\2\2\u0179\u017a\7(\2\2\u017a\u017b"+
		"\7(\2\2\u017bf\3\2\2\2\u017c\u017d\7~\2\2\u017d\u017e\7~\2\2\u017eh\3"+
		"\2\2\2\u017f\u0180\7(\2\2\u0180j\3\2\2\2\u0181\u0182\7~\2\2\u0182l\3\2"+
		"\2\2\u0183\u0184\7#\2\2\u0184n\3\2\2\2\u0185\u0186\7n\2\2\u0186\u0187"+
		"\7g\2\2\u0187\u0188\7p\2\2\u0188p\3\2\2\2\u0189\u018a\7q\2\2\u018a\u018b"+
		"\7t\2\2\u018b\u018c\7f\2\2\u018cr\3\2\2\2\u018d\u018e\7e\2\2\u018e\u018f"+
		"\7j\2\2\u018f\u0190\7t\2\2\u0190t\3\2\2\2\u0191\u0192\7\u0080\2\2\u0192"+
		"v\3\2\2\2\u0193\u0194\7*\2\2\u0194x\3\2\2\2\u0195\u0196\7+\2\2\u0196z"+
		"\3\2\2\2\u0197\u0198\7v\2\2\u0198\u0199\7t\2\2\u0199\u019a\7w\2\2\u019a"+
		"\u019b\7g\2\2\u019b|\3\2\2\2\u019c\u019d\7h\2\2\u019d\u019e\7c\2\2\u019e"+
		"\u019f\7n\2\2\u019f\u01a0\7u\2\2\u01a0\u01a1\7g\2\2\u01a1~\3\2\2\2\u01a2"+
		"\u01a3\7p\2\2\u01a3\u01a4\7w\2\2\u01a4\u01a5\7n\2\2\u01a5\u01a6\7n\2\2"+
		"\u01a6\u0080\3\2\2\2\u01a7\u01a8\7r\2\2\u01a8\u01a9\7w\2\2\u01a9\u01aa"+
		"\7d\2\2\u01aa\u01ab\7n\2\2\u01ab\u01ac\7k\2\2\u01ac\u01ad\7e\2\2\u01ad"+
		"\u0082\3\2\2\2\u01ae\u01af\7r\2\2\u01af\u01b0\7t\2\2\u01b0\u01b1\7k\2"+
		"\2\u01b1\u01b2\7x\2\2\u01b2\u01b3\7c\2\2\u01b3\u01b4\7v\2\2\u01b4\u01b5"+
		"\7g\2\2\u01b5\u0084\3\2\2\2\u01b6\u01b9\5\u0081A\2\u01b7\u01b9\5\u0083"+
		"B\2\u01b8\u01b6\3\2\2\2\u01b8\u01b7\3\2\2\2\u01b9\u0086\3\2\2\2\u01ba"+
		"\u01bb\4\62;\2\u01bb\u0088\3\2\2\2\u01bc\u01bd\7\62\2\2\u01bd\u01be\7"+
		"d\2\2\u01be\u008a\3\2\2\2\u01bf\u01c0\4\62\63\2\u01c0\u008c\3\2\2\2\u01c1"+
		"\u01c2\7\62\2\2\u01c2\u01c3\7q\2\2\u01c3\u008e\3\2\2\2\u01c4\u01c5\4\62"+
		"9\2\u01c5\u0090\3\2\2\2\u01c6\u01c7\7\62\2\2\u01c7\u01c8\7z\2\2\u01c8"+
		"\u0092\3\2\2\2\u01c9\u01cc\5\u0087D\2\u01ca\u01cc\t\2\2\2\u01cb\u01c9"+
		"\3\2\2\2\u01cb\u01ca\3\2\2\2\u01cc\u0094\3\2\2\2\u01cd\u01ce\4c|\2\u01ce"+
		"\u0096\3\2\2\2\u01cf\u01d0\4C\\\2\u01d0\u0098\3\2\2\2\u01d1\u01d2\7a\2"+
		"\2\u01d2\u009a\3\2\2\2\u01d3\u01d4\7)\2\2\u01d4\u009c\3\2\2\2\u01d5\u01d6"+
		"\7$\2\2\u01d6\u009e\3\2\2\2\u01d7\u01d9\5\u0087D\2\u01d8\u01d7\3\2\2\2"+
		"\u01d9\u01da\3\2\2\2\u01da\u01d8\3\2\2\2\u01da\u01db\3\2\2\2\u01db\u00a0"+
		"\3\2\2\2\u01dc\u01de\5\u0089E\2\u01dd\u01df\5\u008bF\2\u01de\u01dd\3\2"+
		"\2\2\u01df\u01e0\3\2\2\2\u01e0\u01de\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1"+
		"\u00a2\3\2\2\2\u01e2\u01e4\5\u008dG\2\u01e3\u01e5\5\u008fH\2\u01e4\u01e3"+
		"\3\2\2\2\u01e5\u01e6\3\2\2\2\u01e6\u01e4\3\2\2\2\u01e6\u01e7\3\2\2\2\u01e7"+
		"\u00a4\3\2\2\2\u01e8\u01ea\5\u0091I\2\u01e9\u01eb\5\u0093J\2\u01ea\u01e9"+
		"\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ea\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed"+
		"\u00a6\3\2\2\2\u01ee\u01f3\7^\2\2\u01ef\u01f4\t\3\2\2\u01f0\u01f4\5\u009d"+
		"O\2\u01f1\u01f4\5\u009bN\2\u01f2\u01f4\7^\2\2\u01f3\u01ef\3\2\2\2\u01f3"+
		"\u01f0\3\2\2\2\u01f3\u01f1\3\2\2\2\u01f3\u01f2\3\2\2\2\u01f4\u00a8\3\2"+
		"\2\2\u01f5\u01f8\n\4\2\2\u01f6\u01f8\5\u00a7T\2\u01f7\u01f5\3\2\2\2\u01f7"+
		"\u01f6\3\2\2\2\u01f8\u00aa\3\2\2\2\u01f9\u01fb\t\5\2\2\u01fa\u01f9\3\2"+
		"\2\2\u01fb\u01fc\3\2\2\2\u01fc\u01fa\3\2\2\2\u01fc\u01fd\3\2\2\2\u01fd"+
		"\u01fe\3\2\2\2\u01fe\u01ff\bV\2\2\u01ff\u00ac\3\2\2\2\u0200\u0204\5\u0099"+
		"M\2\u0201\u0204\5\u0095K\2\u0202\u0204\5\u0097L\2\u0203\u0200\3\2\2\2"+
		"\u0203\u0201\3\2\2\2\u0203\u0202\3\2\2\2\u0204\u020b\3\2\2\2\u0205\u020a"+
		"\5\u0099M\2\u0206\u020a\5\u0095K\2\u0207\u020a\5\u0097L\2\u0208\u020a"+
		"\5\u0087D\2\u0209\u0205\3\2\2\2\u0209\u0206\3\2\2\2\u0209\u0207\3\2\2"+
		"\2\u0209\u0208\3\2\2\2\u020a\u020d\3\2\2\2\u020b\u0209\3\2\2\2\u020b\u020c"+
		"\3\2\2\2\u020c\u00ae\3\2\2\2\u020d\u020b\3\2\2\2\u020e\u020f\5\u009bN"+
		"\2\u020f\u0210\5\u00a9U\2\u0210\u0211\5\u009bN\2\u0211\u00b0\3\2\2\2\u0212"+
		"\u0216\5\u009dO\2\u0213\u0215\5\u00a9U\2\u0214\u0213\3\2\2\2\u0215\u0218"+
		"\3\2\2\2\u0216\u0214\3\2\2\2\u0216\u0217\3\2\2\2\u0217\u0219\3\2\2\2\u0218"+
		"\u0216\3\2\2\2\u0219\u021a\5\u009dO\2\u021a\u00b2\3\2\2\2\u021b\u021f"+
		"\7%\2\2\u021c\u021e\13\2\2\2\u021d\u021c\3\2\2\2\u021e\u0221\3\2\2\2\u021f"+
		"\u0220\3\2\2\2\u021f\u021d\3\2\2\2\u0220\u0222\3\2\2\2\u0221\u021f\3\2"+
		"\2\2\u0222\u0223\7\f\2\2\u0223\u0224\3\2\2\2\u0224\u0225\bZ\3\2\u0225"+
		"\u00b4\3\2\2\2\21\2\u01b8\u01cb\u01da\u01e0\u01e6\u01ec\u01f3\u01f7\u01fc"+
		"\u0203\u0209\u020b\u0216\u021f\4\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}