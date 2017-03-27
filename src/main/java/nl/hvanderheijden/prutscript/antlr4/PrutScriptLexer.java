package nl.hvanderheijden.prutscript.antlr4;// Generated from /Users/heikovanderheijden/Desktop/HeikoScript/src/main/antlr4/PrutScript.g4 by ANTLR 4.6
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrutScriptLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, LINE_COMMENT=7, Identifier=8, 
		NUMBER=9, INT=10, EXPONENT=11, MULTIPLY=12, DIVIDE=13, SUBTRACT=14, ADD=15, 
		LPAREN=16, RPAREN=17, String=18, COMMENT=19, WS=20, NL=21;
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
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\27\u0093\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\7\b@\n\b\f\b\16\b"+
		"C\13\b\3\b\3\b\3\t\3\t\7\tI\n\t\f\t\16\tL\13\t\3\n\3\n\3\n\3\n\5\nR\n"+
		"\n\3\13\3\13\3\13\3\13\5\13X\n\13\3\f\3\f\3\f\5\f]\n\f\3\r\6\r`\n\r\r"+
		"\r\16\ra\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3"+
		"\24\3\24\3\25\3\25\7\25t\n\25\f\25\16\25w\13\25\3\25\3\25\3\26\3\26\3"+
		"\26\3\26\7\26\177\n\26\f\26\16\26\u0082\13\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\30\5\30\u008e\n\30\3\30\3\30\3\30\3\30\3\u0080"+
		"\2\31\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\2\25\2\27\13\31\f\33\r\35"+
		"\16\37\17!\20#\21%\22\'\23)\24+\25-\26/\27\3\2\n\4\2\f\f\17\17\6\2&&C"+
		"\\aac|\4\2\2\u0081\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001\7\2&&\62"+
		";C\\aac|\4\2$$^^\4\2\13\13\"\"\u009b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\3\61\3\2\2\2\5\63\3\2\2\2\7\65\3\2\2\2\t\67\3\2\2\2\139\3"+
		"\2\2\2\r;\3\2\2\2\17=\3\2\2\2\21F\3\2\2\2\23Q\3\2\2\2\25W\3\2\2\2\27Y"+
		"\3\2\2\2\31_\3\2\2\2\33c\3\2\2\2\35e\3\2\2\2\37g\3\2\2\2!i\3\2\2\2#k\3"+
		"\2\2\2%m\3\2\2\2\'o\3\2\2\2)q\3\2\2\2+z\3\2\2\2-\u0088\3\2\2\2/\u008d"+
		"\3\2\2\2\61\62\7?\2\2\62\4\3\2\2\2\63\64\7=\2\2\64\6\3\2\2\2\65\66\7\17"+
		"\2\2\66\b\3\2\2\2\678\7\f\2\28\n\3\2\2\29:\7}\2\2:\f\3\2\2\2;<\7\177\2"+
		"\2<\16\3\2\2\2=A\7%\2\2>@\n\2\2\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2"+
		"\2\2BD\3\2\2\2CA\3\2\2\2DE\b\b\2\2E\20\3\2\2\2FJ\5\23\n\2GI\5\25\13\2"+
		"HG\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\22\3\2\2\2LJ\3\2\2\2MR\t\3\2"+
		"\2NR\n\4\2\2OP\t\5\2\2PR\t\6\2\2QM\3\2\2\2QN\3\2\2\2QO\3\2\2\2R\24\3\2"+
		"\2\2SX\t\7\2\2TX\n\4\2\2UV\t\5\2\2VX\t\6\2\2WS\3\2\2\2WT\3\2\2\2WU\3\2"+
		"\2\2X\26\3\2\2\2Y\\\5\31\r\2Z[\7\60\2\2[]\5\31\r\2\\Z\3\2\2\2\\]\3\2\2"+
		"\2]\30\3\2\2\2^`\4\62;\2_^\3\2\2\2`a\3\2\2\2a_\3\2\2\2ab\3\2\2\2b\32\3"+
		"\2\2\2cd\7`\2\2d\34\3\2\2\2ef\7,\2\2f\36\3\2\2\2gh\7\61\2\2h \3\2\2\2"+
		"ij\7/\2\2j\"\3\2\2\2kl\7-\2\2l$\3\2\2\2mn\7*\2\2n&\3\2\2\2op\7+\2\2p("+
		"\3\2\2\2qu\7$\2\2rt\n\b\2\2sr\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2vx"+
		"\3\2\2\2wu\3\2\2\2xy\7$\2\2y*\3\2\2\2z{\7\61\2\2{|\7,\2\2|\u0080\3\2\2"+
		"\2}\177\13\2\2\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080\u0081\3\2\2\2\u0080"+
		"~\3\2\2\2\u0081\u0083\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0084\7,\2\2\u0084"+
		"\u0085\7\61\2\2\u0085\u0086\3\2\2\2\u0086\u0087\b\26\2\2\u0087,\3\2\2"+
		"\2\u0088\u0089\t\t\2\2\u0089\u008a\3\2\2\2\u008a\u008b\b\27\2\2\u008b"+
		".\3\2\2\2\u008c\u008e\7\17\2\2\u008d\u008c\3\2\2\2\u008d\u008e\3\2\2\2"+
		"\u008e\u008f\3\2\2\2\u008f\u0090\7\f\2\2\u0090\u0091\3\2\2\2\u0091\u0092"+
		"\b\30\2\2\u0092\60\3\2\2\2\f\2AJQW\\au\u0080\u008d\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}