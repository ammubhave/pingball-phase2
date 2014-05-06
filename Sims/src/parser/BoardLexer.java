// Generated from Board.g4 by ANTLR 4.0

package parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BoardLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__19=1, T__18=2, T__17=3, T__16=4, T__15=5, T__14=6, T__13=7, T__12=8, 
		T__11=9, T__10=10, T__9=11, T__8=12, T__7=13, T__6=14, T__5=15, T__4=16, 
		T__3=17, T__2=18, T__1=19, T__0=20, WHITESPACE=21, NAME=22, NUMBER=23, 
		COMMENT=24;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'yVelocity'", "'friction1'", "'name'", "'gravity'", "'board'", "'xVelocity'", 
		"'ball'", "'orientation'", "'height'", "'x'", "'y'", "'triangleBumper'", 
		"'fire'", "'action'", "'absorber'", "'squareBumper'", "'trigger'", "'circleBumper'", 
		"'friction2'", "'width'", "WHITESPACE", "NAME", "NUMBER", "COMMENT"
	};
	public static final String[] ruleNames = {
		"T__19", "T__18", "T__17", "T__16", "T__15", "T__14", "T__13", "T__12", 
		"T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", 
		"T__2", "T__1", "T__0", "WHITESPACE", "NAME", "NUMBER", "COMMENT"
	};


	    /**
	     * Call this method to have the lexer or parser throw a RuntimeException if
	     * it encounters an error.
	     */
	    public void reportErrorsAsExceptions() {
	        addErrorListener(new ExceptionThrowingErrorListener());
	    }
	    
	    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
	        @Override
	        public void syntaxError(Recognizer<?, ?> recognizer,
	                Object offendingSymbol, int line, int charPositionInLine,
	                String msg, RecognitionException e) {
	            throw new RuntimeException(msg);
	        }
	    }


	public BoardLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Board.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 20: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;

		case 23: COMMENT_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WHITESPACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4\32\u0106\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\6\26\u00d8\n\26"+
		"\r\26\16\26\u00d9\3\26\3\26\3\27\3\27\7\27\u00e0\n\27\f\27\16\27\u00e3"+
		"\13\27\3\30\5\30\u00e6\n\30\3\30\6\30\u00e9\n\30\r\30\16\30\u00ea\3\30"+
		"\3\30\7\30\u00ef\n\30\f\30\16\30\u00f2\13\30\3\30\5\30\u00f5\n\30\3\30"+
		"\6\30\u00f8\n\30\r\30\16\30\u00f9\5\30\u00fc\n\30\3\31\3\31\7\31\u0100"+
		"\n\31\f\31\16\31\u0103\13\31\3\31\3\31\2\32\3\3\1\5\4\1\7\5\1\t\6\1\13"+
		"\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1"+
		"\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\2-\30\1/\31\1\61\32\3\3\2"+
		"\t\7\13\f\17\17\"\"))??\5C\\aac|\6\62;C\\aac|\3\62;\3\62;\3\62;\4\f\f"+
		"\17\17\u010e\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\5=\3\2\2\2\7G\3\2\2\2\tL\3\2\2"+
		"\2\13T\3\2\2\2\rZ\3\2\2\2\17d\3\2\2\2\21i\3\2\2\2\23u\3\2\2\2\25|\3\2"+
		"\2\2\27~\3\2\2\2\31\u0080\3\2\2\2\33\u008f\3\2\2\2\35\u0094\3\2\2\2\37"+
		"\u009b\3\2\2\2!\u00a4\3\2\2\2#\u00b1\3\2\2\2%\u00b9\3\2\2\2\'\u00c6\3"+
		"\2\2\2)\u00d0\3\2\2\2+\u00d7\3\2\2\2-\u00dd\3\2\2\2/\u00e5\3\2\2\2\61"+
		"\u00fd\3\2\2\2\63\64\7{\2\2\64\65\7X\2\2\65\66\7g\2\2\66\67\7n\2\2\67"+
		"8\7q\2\289\7e\2\29:\7k\2\2:;\7v\2\2;<\7{\2\2<\4\3\2\2\2=>\7h\2\2>?\7t"+
		"\2\2?@\7k\2\2@A\7e\2\2AB\7v\2\2BC\7k\2\2CD\7q\2\2DE\7p\2\2EF\7\63\2\2"+
		"F\6\3\2\2\2GH\7p\2\2HI\7c\2\2IJ\7o\2\2JK\7g\2\2K\b\3\2\2\2LM\7i\2\2MN"+
		"\7t\2\2NO\7c\2\2OP\7x\2\2PQ\7k\2\2QR\7v\2\2RS\7{\2\2S\n\3\2\2\2TU\7d\2"+
		"\2UV\7q\2\2VW\7c\2\2WX\7t\2\2XY\7f\2\2Y\f\3\2\2\2Z[\7z\2\2[\\\7X\2\2\\"+
		"]\7g\2\2]^\7n\2\2^_\7q\2\2_`\7e\2\2`a\7k\2\2ab\7v\2\2bc\7{\2\2c\16\3\2"+
		"\2\2de\7d\2\2ef\7c\2\2fg\7n\2\2gh\7n\2\2h\20\3\2\2\2ij\7q\2\2jk\7t\2\2"+
		"kl\7k\2\2lm\7g\2\2mn\7p\2\2no\7v\2\2op\7c\2\2pq\7v\2\2qr\7k\2\2rs\7q\2"+
		"\2st\7p\2\2t\22\3\2\2\2uv\7j\2\2vw\7g\2\2wx\7k\2\2xy\7i\2\2yz\7j\2\2z"+
		"{\7v\2\2{\24\3\2\2\2|}\7z\2\2}\26\3\2\2\2~\177\7{\2\2\177\30\3\2\2\2\u0080"+
		"\u0081\7v\2\2\u0081\u0082\7t\2\2\u0082\u0083\7k\2\2\u0083\u0084\7c\2\2"+
		"\u0084\u0085\7p\2\2\u0085\u0086\7i\2\2\u0086\u0087\7n\2\2\u0087\u0088"+
		"\7g\2\2\u0088\u0089\7D\2\2\u0089\u008a\7w\2\2\u008a\u008b\7o\2\2\u008b"+
		"\u008c\7r\2\2\u008c\u008d\7g\2\2\u008d\u008e\7t\2\2\u008e\32\3\2\2\2\u008f"+
		"\u0090\7h\2\2\u0090\u0091\7k\2\2\u0091\u0092\7t\2\2\u0092\u0093\7g\2\2"+
		"\u0093\34\3\2\2\2\u0094\u0095\7c\2\2\u0095\u0096\7e\2\2\u0096\u0097\7"+
		"v\2\2\u0097\u0098\7k\2\2\u0098\u0099\7q\2\2\u0099\u009a\7p\2\2\u009a\36"+
		"\3\2\2\2\u009b\u009c\7c\2\2\u009c\u009d\7d\2\2\u009d\u009e\7u\2\2\u009e"+
		"\u009f\7q\2\2\u009f\u00a0\7t\2\2\u00a0\u00a1\7d\2\2\u00a1\u00a2\7g\2\2"+
		"\u00a2\u00a3\7t\2\2\u00a3 \3\2\2\2\u00a4\u00a5\7u\2\2\u00a5\u00a6\7s\2"+
		"\2\u00a6\u00a7\7w\2\2\u00a7\u00a8\7c\2\2\u00a8\u00a9\7t\2\2\u00a9\u00aa"+
		"\7g\2\2\u00aa\u00ab\7D\2\2\u00ab\u00ac\7w\2\2\u00ac\u00ad\7o\2\2\u00ad"+
		"\u00ae\7r\2\2\u00ae\u00af\7g\2\2\u00af\u00b0\7t\2\2\u00b0\"\3\2\2\2\u00b1"+
		"\u00b2\7v\2\2\u00b2\u00b3\7t\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7i\2\2"+
		"\u00b5\u00b6\7i\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7t\2\2\u00b8$\3\2\2"+
		"\2\u00b9\u00ba\7e\2\2\u00ba\u00bb\7k\2\2\u00bb\u00bc\7t\2\2\u00bc\u00bd"+
		"\7e\2\2\u00bd\u00be\7n\2\2\u00be\u00bf\7g\2\2\u00bf\u00c0\7D\2\2\u00c0"+
		"\u00c1\7w\2\2\u00c1\u00c2\7o\2\2\u00c2\u00c3\7r\2\2\u00c3\u00c4\7g\2\2"+
		"\u00c4\u00c5\7t\2\2\u00c5&\3\2\2\2\u00c6\u00c7\7h\2\2\u00c7\u00c8\7t\2"+
		"\2\u00c8\u00c9\7k\2\2\u00c9\u00ca\7e\2\2\u00ca\u00cb\7v\2\2\u00cb\u00cc"+
		"\7k\2\2\u00cc\u00cd\7q\2\2\u00cd\u00ce\7p\2\2\u00ce\u00cf\7\64\2\2\u00cf"+
		"(\3\2\2\2\u00d0\u00d1\7y\2\2\u00d1\u00d2\7k\2\2\u00d2\u00d3\7f\2\2\u00d3"+
		"\u00d4\7v\2\2\u00d4\u00d5\7j\2\2\u00d5*\3\2\2\2\u00d6\u00d8\t\2\2\2\u00d7"+
		"\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2"+
		"\2\2\u00da\u00db\3\2\2\2\u00db\u00dc\b\26\2\2\u00dc,\3\2\2\2\u00dd\u00e1"+
		"\t\3\2\2\u00de\u00e0\t\4\2\2\u00df\u00de\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1"+
		"\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2.\3\2\2\2\u00e3\u00e1\3\2\2\2"+
		"\u00e4\u00e6\7/\2\2\u00e5\u00e4\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00fb"+
		"\3\2\2\2\u00e7\u00e9\t\5\2\2\u00e8\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea"+
		"\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00f0\7\60"+
		"\2\2\u00ed\u00ef\t\6\2\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0"+
		"\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00fc\3\2\2\2\u00f2\u00f0\3\2"+
		"\2\2\u00f3\u00f5\7\60\2\2\u00f4\u00f3\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5"+
		"\u00f7\3\2\2\2\u00f6\u00f8\t\7\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00f9\3\2"+
		"\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fc\3\2\2\2\u00fb"+
		"\u00e8\3\2\2\2\u00fb\u00f4\3\2\2\2\u00fc\60\3\2\2\2\u00fd\u0101\7%\2\2"+
		"\u00fe\u0100\n\b\2\2\u00ff\u00fe\3\2\2\2\u0100\u0103\3\2\2\2\u0101\u00ff"+
		"\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0104\3\2\2\2\u0103\u0101\3\2\2\2\u0104"+
		"\u0105\b\31\3\2\u0105\62\3\2\2\2\f\2\u00d9\u00e1\u00e5\u00ea\u00f0\u00f4"+
		"\u00f9\u00fb\u0101";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}