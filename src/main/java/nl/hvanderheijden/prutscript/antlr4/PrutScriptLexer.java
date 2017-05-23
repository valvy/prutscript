package nl.hvanderheijden.prutscript.antlr4;// Generated from /Users/heikovanderheijden/Desktop/prutscript/src/main/antlr4/PrutScript.g4 by ANTLR 4.7
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrutScriptLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, LINE_COMMENT=7, Identifier=8, 
		NUMBER=9, INT=10, EXPONENT=11, MULTIPLY=12, DIVIDE=13, SUBTRACT=14, ADD=15, 
		LPAREN=16, RPAREN=17, String=18, COMMENT=19, WS=20, NL=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "LINE_COMMENT", "Identifier", 
		"Letter", "LetterOrDigit", "NUMBER", "INT", "EXPONENT", "MULTIPLY", "DIVIDE", 
		"SUBTRACT", "ADD", "LPAREN", "RPAREN", "String", "COMMENT", "WS", "NL"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "';'", "'\r'", "'\n'", "'{'", "'}'", null, null, null, null, 
		"'^'", "'*'", "'/'", "'-'", "'+'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "LINE_COMMENT", "Identifier", 
		"NUMBER", "INT", "EXPONENT", "MULTIPLY", "DIVIDE", "SUBTRACT", "ADD", 
		"LPAREN", "RPAREN", "String", "COMMENT", "WS", "NL"
	};
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


	public PrutScriptLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PrutScript.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u0094\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\7\b@\n\b\f\b\16\b"+
		"C\13\b\3\b\3\b\3\t\3\t\7\tI\n\t\f\t\16\tL\13\t\3\n\3\n\3\n\3\n\5\nR\n"+
		"\n\3\13\3\13\3\13\3\13\3\13\5\13Y\n\13\3\f\3\f\3\f\5\f^\n\f\3\r\6\ra\n"+
		"\r\r\r\16\rb\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3"+
		"\23\3\24\3\24\3\25\3\25\7\25u\n\25\f\25\16\25x\13\25\3\25\3\25\3\26\3"+
		"\26\3\26\3\26\7\26\u0080\n\26\f\26\16\26\u0083\13\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\27\3\27\3\27\3\27\3\30\5\30\u008f\n\30\3\30\3\30\3\30\3\30"+
		"\3\u0081\2\31\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\2\25\2\27\13\31\f"+
		"\33\r\35\16\37\17!\20#\21%\22\'\23)\24+\25-\26/\27\3\2\n\4\2\f\f\17\17"+
		"\6\2&&C\\aac|\4\2\2\u0081\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001"+
		"\7\2&&\62;C\\aac|\4\2$$^^\4\2\13\13\"\"\2\u009d\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\3\61\3\2\2\2\5\63\3\2\2\2\7\65\3\2\2\2\t\67\3"+
		"\2\2\2\139\3\2\2\2\r;\3\2\2\2\17=\3\2\2\2\21F\3\2\2\2\23Q\3\2\2\2\25X"+
		"\3\2\2\2\27Z\3\2\2\2\31`\3\2\2\2\33d\3\2\2\2\35f\3\2\2\2\37h\3\2\2\2!"+
		"j\3\2\2\2#l\3\2\2\2%n\3\2\2\2\'p\3\2\2\2)r\3\2\2\2+{\3\2\2\2-\u0089\3"+
		"\2\2\2/\u008e\3\2\2\2\61\62\7?\2\2\62\4\3\2\2\2\63\64\7=\2\2\64\6\3\2"+
		"\2\2\65\66\7\17\2\2\66\b\3\2\2\2\678\7\f\2\28\n\3\2\2\29:\7}\2\2:\f\3"+
		"\2\2\2;<\7\177\2\2<\16\3\2\2\2=A\7%\2\2>@\n\2\2\2?>\3\2\2\2@C\3\2\2\2"+
		"A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DE\b\b\2\2E\20\3\2\2\2FJ\5\23"+
		"\n\2GI\5\25\13\2HG\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\22\3\2\2\2L"+
		"J\3\2\2\2MR\t\3\2\2NR\n\4\2\2OP\t\5\2\2PR\t\6\2\2QM\3\2\2\2QN\3\2\2\2"+
		"QO\3\2\2\2R\24\3\2\2\2SY\t\7\2\2TY\n\4\2\2UV\t\5\2\2VY\t\6\2\2WY\7\60"+
		"\2\2XS\3\2\2\2XT\3\2\2\2XU\3\2\2\2XW\3\2\2\2Y\26\3\2\2\2Z]\5\31\r\2[\\"+
		"\7\60\2\2\\^\5\31\r\2][\3\2\2\2]^\3\2\2\2^\30\3\2\2\2_a\4\62;\2`_\3\2"+
		"\2\2ab\3\2\2\2b`\3\2\2\2bc\3\2\2\2c\32\3\2\2\2de\7`\2\2e\34\3\2\2\2fg"+
		"\7,\2\2g\36\3\2\2\2hi\7\61\2\2i \3\2\2\2jk\7/\2\2k\"\3\2\2\2lm\7-\2\2"+
		"m$\3\2\2\2no\7*\2\2o&\3\2\2\2pq\7+\2\2q(\3\2\2\2rv\7$\2\2su\n\b\2\2ts"+
		"\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2wy\3\2\2\2xv\3\2\2\2yz\7$\2\2z*"+
		"\3\2\2\2{|\7\61\2\2|}\7,\2\2}\u0081\3\2\2\2~\u0080\13\2\2\2\177~\3\2\2"+
		"\2\u0080\u0083\3\2\2\2\u0081\u0082\3\2\2\2\u0081\177\3\2\2\2\u0082\u0084"+
		"\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085\7,\2\2\u0085\u0086\7\61\2\2\u0086"+
		"\u0087\3\2\2\2\u0087\u0088\b\26\2\2\u0088,\3\2\2\2\u0089\u008a\t\t\2\2"+
		"\u008a\u008b\3\2\2\2\u008b\u008c\b\27\2\2\u008c.\3\2\2\2\u008d\u008f\7"+
		"\17\2\2\u008e\u008d\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\3\2\2\2\u0090"+
		"\u0091\7\f\2\2\u0091\u0092\3\2\2\2\u0092\u0093\b\30\2\2\u0093\60\3\2\2"+
		"\2\f\2AJQX]bv\u0081\u008e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}