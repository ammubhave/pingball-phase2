// Generated from src/pingball/parser/Board.g4 by ANTLR 4.0

package pingball.parser;

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
		T__11=1, T__10=2, T__9=3, T__8=4, T__7=5, T__6=6, T__5=7, T__4=8, T__3=9, 
		T__2=10, T__1=11, T__0=12, COMMENT=13, WHITESPACE=14, EQUALS=15, NUMBER=16, 
		NAME=17;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'triangleBumper'", "'fire'", "'keyup'", "'board'", "'ball'", "'absorber'", 
		"'portal'", "'squareBumper'", "'leftFlipper'", "'circleBumper'", "'keydown'", 
		"'rightFlipper'", "COMMENT", "WHITESPACE", "'='", "NUMBER", "NAME"
	};
	public static final String[] ruleNames = {
		"T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", 
		"T__2", "T__1", "T__0", "COMMENT", "WHITESPACE", "EQUALS", "NUMBER", "NAME"
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
		case 12: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 13: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WHITESPACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4\23\u00c7\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\16\3\16\7\16\u0098\n\16\f\16\16\16\u009b\13\16\3\16\3\16\3\17\6\17\u00a0"+
		"\n\17\r\17\16\17\u00a1\3\17\3\17\3\20\3\20\3\21\5\21\u00a9\n\21\3\21\6"+
		"\21\u00ac\n\21\r\21\16\21\u00ad\3\21\3\21\7\21\u00b2\n\21\f\21\16\21\u00b5"+
		"\13\21\3\21\5\21\u00b8\n\21\3\21\6\21\u00bb\n\21\r\21\16\21\u00bc\5\21"+
		"\u00bf\n\21\3\22\3\22\7\22\u00c3\n\22\f\22\16\22\u00c6\13\22\2\23\3\3"+
		"\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1"+
		"\31\16\1\33\17\2\35\20\3\37\21\1!\22\1#\23\1\3\2\t\3\f\f\5\13\f\17\17"+
		"\"\"\3\62;\3\62;\3\62;\5C\\aac|\6\62;C\\aac|\u00cf\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2\2\5\64\3"+
		"\2\2\2\79\3\2\2\2\t?\3\2\2\2\13E\3\2\2\2\rJ\3\2\2\2\17S\3\2\2\2\21Z\3"+
		"\2\2\2\23g\3\2\2\2\25s\3\2\2\2\27\u0080\3\2\2\2\31\u0088\3\2\2\2\33\u0095"+
		"\3\2\2\2\35\u009f\3\2\2\2\37\u00a5\3\2\2\2!\u00a8\3\2\2\2#\u00c0\3\2\2"+
		"\2%&\7v\2\2&\'\7t\2\2\'(\7k\2\2()\7c\2\2)*\7p\2\2*+\7i\2\2+,\7n\2\2,-"+
		"\7g\2\2-.\7D\2\2./\7w\2\2/\60\7o\2\2\60\61\7r\2\2\61\62\7g\2\2\62\63\7"+
		"t\2\2\63\4\3\2\2\2\64\65\7h\2\2\65\66\7k\2\2\66\67\7t\2\2\678\7g\2\28"+
		"\6\3\2\2\29:\7m\2\2:;\7g\2\2;<\7{\2\2<=\7w\2\2=>\7r\2\2>\b\3\2\2\2?@\7"+
		"d\2\2@A\7q\2\2AB\7c\2\2BC\7t\2\2CD\7f\2\2D\n\3\2\2\2EF\7d\2\2FG\7c\2\2"+
		"GH\7n\2\2HI\7n\2\2I\f\3\2\2\2JK\7c\2\2KL\7d\2\2LM\7u\2\2MN\7q\2\2NO\7"+
		"t\2\2OP\7d\2\2PQ\7g\2\2QR\7t\2\2R\16\3\2\2\2ST\7r\2\2TU\7q\2\2UV\7t\2"+
		"\2VW\7v\2\2WX\7c\2\2XY\7n\2\2Y\20\3\2\2\2Z[\7u\2\2[\\\7s\2\2\\]\7w\2\2"+
		"]^\7c\2\2^_\7t\2\2_`\7g\2\2`a\7D\2\2ab\7w\2\2bc\7o\2\2cd\7r\2\2de\7g\2"+
		"\2ef\7t\2\2f\22\3\2\2\2gh\7n\2\2hi\7g\2\2ij\7h\2\2jk\7v\2\2kl\7H\2\2l"+
		"m\7n\2\2mn\7k\2\2no\7r\2\2op\7r\2\2pq\7g\2\2qr\7t\2\2r\24\3\2\2\2st\7"+
		"e\2\2tu\7k\2\2uv\7t\2\2vw\7e\2\2wx\7n\2\2xy\7g\2\2yz\7D\2\2z{\7w\2\2{"+
		"|\7o\2\2|}\7r\2\2}~\7g\2\2~\177\7t\2\2\177\26\3\2\2\2\u0080\u0081\7m\2"+
		"\2\u0081\u0082\7g\2\2\u0082\u0083\7{\2\2\u0083\u0084\7f\2\2\u0084\u0085"+
		"\7q\2\2\u0085\u0086\7y\2\2\u0086\u0087\7p\2\2\u0087\30\3\2\2\2\u0088\u0089"+
		"\7t\2\2\u0089\u008a\7k\2\2\u008a\u008b\7i\2\2\u008b\u008c\7j\2\2\u008c"+
		"\u008d\7v\2\2\u008d\u008e\7H\2\2\u008e\u008f\7n\2\2\u008f\u0090\7k\2\2"+
		"\u0090\u0091\7r\2\2\u0091\u0092\7r\2\2\u0092\u0093\7g\2\2\u0093\u0094"+
		"\7t\2\2\u0094\32\3\2\2\2\u0095\u0099\7%\2\2\u0096\u0098\n\2\2\2\u0097"+
		"\u0096\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2"+
		"\2\2\u009a\u009c\3\2\2\2\u009b\u0099\3\2\2\2\u009c\u009d\b\16\2\2\u009d"+
		"\34\3\2\2\2\u009e\u00a0\t\3\2\2\u009f\u009e\3\2\2\2\u00a0\u00a1\3\2\2"+
		"\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4"+
		"\b\17\3\2\u00a4\36\3\2\2\2\u00a5\u00a6\7?\2\2\u00a6 \3\2\2\2\u00a7\u00a9"+
		"\7/\2\2\u00a8\u00a7\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00be\3\2\2\2\u00aa"+
		"\u00ac\t\4\2\2\u00ab\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ab\3\2"+
		"\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b3\7\60\2\2\u00b0"+
		"\u00b2\t\5\2\2\u00b1\u00b0\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2"+
		"\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00bf\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6"+
		"\u00b8\7\60\2\2\u00b7\u00b6\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\3"+
		"\2\2\2\u00b9\u00bb\t\6\2\2\u00ba\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc"+
		"\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bf\3\2\2\2\u00be\u00ab\3\2"+
		"\2\2\u00be\u00b7\3\2\2\2\u00bf\"\3\2\2\2\u00c0\u00c4\t\7\2\2\u00c1\u00c3"+
		"\t\b\2\2\u00c2\u00c1\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5$\3\2\2\2\u00c6\u00c4\3\2\2\2\f\2\u0099\u00a1\u00a8"+
		"\u00ad\u00b3\u00b7\u00bc\u00be\u00c4";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}