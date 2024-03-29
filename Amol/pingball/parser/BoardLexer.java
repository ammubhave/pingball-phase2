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
		COMMENT=1, WHITESPACE=2, STRING_BOARD=3, STRING_BALL=4, STRING_SQUAREBUMPER=5, 
		STRING_TRIANGLEBUMPER=6, STRING_CIRCLEBUMPER=7, STRING_LEFTFLIPPER=8, 
		STRING_RIGHTFLIPPER=9, STRING_ABSORBER=10, STRING_FIRE=11, STRING_NAME=12, 
		STRING_GRAVITY=13, STRING_FRICTION1=14, STRING_FRICTION2=15, STRING_WIDTH=16, 
		STRING_HEIGHT=17, STRING_TRIGGER=18, STRING_ACTION=19, STRING_ORIENTATION=20, 
		STRING_XVELOCITY=21, STRING_YVELOCITY=22, STRING_X=23, STRING_Y=24, EQUALS=25, 
		NUMBER=26, NAME=27;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"COMMENT", "WHITESPACE", "'board'", "'ball'", "'squareBumper'", "'triangleBumper'", 
		"'circleBumper'", "'leftFlipper'", "'rightFlipper'", "'absorber'", "'fire'", 
		"'name'", "'gravity'", "'friction1'", "'friction2'", "'width'", "'height'", 
		"'trigger'", "'action'", "'orientation'", "'xVelocity'", "'yVelocity'", 
		"'x'", "'y'", "'='", "NUMBER", "NAME"
	};
	public static final String[] ruleNames = {
		"COMMENT", "WHITESPACE", "STRING_BOARD", "STRING_BALL", "STRING_SQUAREBUMPER", 
		"STRING_TRIANGLEBUMPER", "STRING_CIRCLEBUMPER", "STRING_LEFTFLIPPER", 
		"STRING_RIGHTFLIPPER", "STRING_ABSORBER", "STRING_FIRE", "STRING_NAME", 
		"STRING_GRAVITY", "STRING_FRICTION1", "STRING_FRICTION2", "STRING_WIDTH", 
		"STRING_HEIGHT", "STRING_TRIGGER", "STRING_ACTION", "STRING_ORIENTATION", 
		"STRING_XVELOCITY", "STRING_YVELOCITY", "STRING_X", "STRING_Y", "EQUALS", 
		"NUMBER", "NAME"
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
		case 0: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 1: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;
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
		"\2\4\35\u0127\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\7\2<\n"+
		"\2\f\2\16\2?\13\2\3\2\3\2\3\3\6\3D\n\3\r\3\16\3E\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3\31"+
		"\3\32\3\32\3\33\5\33\u0109\n\33\3\33\6\33\u010c\n\33\r\33\16\33\u010d"+
		"\3\33\3\33\7\33\u0112\n\33\f\33\16\33\u0115\13\33\3\33\5\33\u0118\n\33"+
		"\3\33\6\33\u011b\n\33\r\33\16\33\u011c\5\33\u011f\n\33\3\34\3\34\7\34"+
		"\u0123\n\34\f\34\16\34\u0126\13\34\2\35\3\3\2\5\4\3\7\5\1\t\6\1\13\7\1"+
		"\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37"+
		"\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1"+
		"\65\34\1\67\35\1\3\2\t\3\f\f\5\13\f\17\17\"\"\3\62;\3\62;\3\62;\5C\\a"+
		"ac|\6\62;C\\aac|\u012f\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\39\3\2\2\2\5C\3\2\2\2\7I\3\2\2\2\tO\3\2\2\2\13T\3\2\2\2\ra\3\2"+
		"\2\2\17p\3\2\2\2\21}\3\2\2\2\23\u0089\3\2\2\2\25\u0096\3\2\2\2\27\u009f"+
		"\3\2\2\2\31\u00a4\3\2\2\2\33\u00a9\3\2\2\2\35\u00b1\3\2\2\2\37\u00bb\3"+
		"\2\2\2!\u00c5\3\2\2\2#\u00cb\3\2\2\2%\u00d2\3\2\2\2\'\u00da\3\2\2\2)\u00e1"+
		"\3\2\2\2+\u00ed\3\2\2\2-\u00f7\3\2\2\2/\u0101\3\2\2\2\61\u0103\3\2\2\2"+
		"\63\u0105\3\2\2\2\65\u0108\3\2\2\2\67\u0120\3\2\2\29=\7%\2\2:<\n\2\2\2"+
		";:\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>@\3\2\2\2?=\3\2\2\2@A\b\2\2\2"+
		"A\4\3\2\2\2BD\t\3\2\2CB\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2FG\3\2\2"+
		"\2GH\b\3\3\2H\6\3\2\2\2IJ\7d\2\2JK\7q\2\2KL\7c\2\2LM\7t\2\2MN\7f\2\2N"+
		"\b\3\2\2\2OP\7d\2\2PQ\7c\2\2QR\7n\2\2RS\7n\2\2S\n\3\2\2\2TU\7u\2\2UV\7"+
		"s\2\2VW\7w\2\2WX\7c\2\2XY\7t\2\2YZ\7g\2\2Z[\7D\2\2[\\\7w\2\2\\]\7o\2\2"+
		"]^\7r\2\2^_\7g\2\2_`\7t\2\2`\f\3\2\2\2ab\7v\2\2bc\7t\2\2cd\7k\2\2de\7"+
		"c\2\2ef\7p\2\2fg\7i\2\2gh\7n\2\2hi\7g\2\2ij\7D\2\2jk\7w\2\2kl\7o\2\2l"+
		"m\7r\2\2mn\7g\2\2no\7t\2\2o\16\3\2\2\2pq\7e\2\2qr\7k\2\2rs\7t\2\2st\7"+
		"e\2\2tu\7n\2\2uv\7g\2\2vw\7D\2\2wx\7w\2\2xy\7o\2\2yz\7r\2\2z{\7g\2\2{"+
		"|\7t\2\2|\20\3\2\2\2}~\7n\2\2~\177\7g\2\2\177\u0080\7h\2\2\u0080\u0081"+
		"\7v\2\2\u0081\u0082\7H\2\2\u0082\u0083\7n\2\2\u0083\u0084\7k\2\2\u0084"+
		"\u0085\7r\2\2\u0085\u0086\7r\2\2\u0086\u0087\7g\2\2\u0087\u0088\7t\2\2"+
		"\u0088\22\3\2\2\2\u0089\u008a\7t\2\2\u008a\u008b\7k\2\2\u008b\u008c\7"+
		"i\2\2\u008c\u008d\7j\2\2\u008d\u008e\7v\2\2\u008e\u008f\7H\2\2\u008f\u0090"+
		"\7n\2\2\u0090\u0091\7k\2\2\u0091\u0092\7r\2\2\u0092\u0093\7r\2\2\u0093"+
		"\u0094\7g\2\2\u0094\u0095\7t\2\2\u0095\24\3\2\2\2\u0096\u0097\7c\2\2\u0097"+
		"\u0098\7d\2\2\u0098\u0099\7u\2\2\u0099\u009a\7q\2\2\u009a\u009b\7t\2\2"+
		"\u009b\u009c\7d\2\2\u009c\u009d\7g\2\2\u009d\u009e\7t\2\2\u009e\26\3\2"+
		"\2\2\u009f\u00a0\7h\2\2\u00a0\u00a1\7k\2\2\u00a1\u00a2\7t\2\2\u00a2\u00a3"+
		"\7g\2\2\u00a3\30\3\2\2\2\u00a4\u00a5\7p\2\2\u00a5\u00a6\7c\2\2\u00a6\u00a7"+
		"\7o\2\2\u00a7\u00a8\7g\2\2\u00a8\32\3\2\2\2\u00a9\u00aa\7i\2\2\u00aa\u00ab"+
		"\7t\2\2\u00ab\u00ac\7c\2\2\u00ac\u00ad\7x\2\2\u00ad\u00ae\7k\2\2\u00ae"+
		"\u00af\7v\2\2\u00af\u00b0\7{\2\2\u00b0\34\3\2\2\2\u00b1\u00b2\7h\2\2\u00b2"+
		"\u00b3\7t\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7e\2\2\u00b5\u00b6\7v\2\2"+
		"\u00b6\u00b7\7k\2\2\u00b7\u00b8\7q\2\2\u00b8\u00b9\7p\2\2\u00b9\u00ba"+
		"\7\63\2\2\u00ba\36\3\2\2\2\u00bb\u00bc\7h\2\2\u00bc\u00bd\7t\2\2\u00bd"+
		"\u00be\7k\2\2\u00be\u00bf\7e\2\2\u00bf\u00c0\7v\2\2\u00c0\u00c1\7k\2\2"+
		"\u00c1\u00c2\7q\2\2\u00c2\u00c3\7p\2\2\u00c3\u00c4\7\64\2\2\u00c4 \3\2"+
		"\2\2\u00c5\u00c6\7y\2\2\u00c6\u00c7\7k\2\2\u00c7\u00c8\7f\2\2\u00c8\u00c9"+
		"\7v\2\2\u00c9\u00ca\7j\2\2\u00ca\"\3\2\2\2\u00cb\u00cc\7j\2\2\u00cc\u00cd"+
		"\7g\2\2\u00cd\u00ce\7k\2\2\u00ce\u00cf\7i\2\2\u00cf\u00d0\7j\2\2\u00d0"+
		"\u00d1\7v\2\2\u00d1$\3\2\2\2\u00d2\u00d3\7v\2\2\u00d3\u00d4\7t\2\2\u00d4"+
		"\u00d5\7k\2\2\u00d5\u00d6\7i\2\2\u00d6\u00d7\7i\2\2\u00d7\u00d8\7g\2\2"+
		"\u00d8\u00d9\7t\2\2\u00d9&\3\2\2\2\u00da\u00db\7c\2\2\u00db\u00dc\7e\2"+
		"\2\u00dc\u00dd\7v\2\2\u00dd\u00de\7k\2\2\u00de\u00df\7q\2\2\u00df\u00e0"+
		"\7p\2\2\u00e0(\3\2\2\2\u00e1\u00e2\7q\2\2\u00e2\u00e3\7t\2\2\u00e3\u00e4"+
		"\7k\2\2\u00e4\u00e5\7g\2\2\u00e5\u00e6\7p\2\2\u00e6\u00e7\7v\2\2\u00e7"+
		"\u00e8\7c\2\2\u00e8\u00e9\7v\2\2\u00e9\u00ea\7k\2\2\u00ea\u00eb\7q\2\2"+
		"\u00eb\u00ec\7p\2\2\u00ec*\3\2\2\2\u00ed\u00ee\7z\2\2\u00ee\u00ef\7X\2"+
		"\2\u00ef\u00f0\7g\2\2\u00f0\u00f1\7n\2\2\u00f1\u00f2\7q\2\2\u00f2\u00f3"+
		"\7e\2\2\u00f3\u00f4\7k\2\2\u00f4\u00f5\7v\2\2\u00f5\u00f6\7{\2\2\u00f6"+
		",\3\2\2\2\u00f7\u00f8\7{\2\2\u00f8\u00f9\7X\2\2\u00f9\u00fa\7g\2\2\u00fa"+
		"\u00fb\7n\2\2\u00fb\u00fc\7q\2\2\u00fc\u00fd\7e\2\2\u00fd\u00fe\7k\2\2"+
		"\u00fe\u00ff\7v\2\2\u00ff\u0100\7{\2\2\u0100.\3\2\2\2\u0101\u0102\7z\2"+
		"\2\u0102\60\3\2\2\2\u0103\u0104\7{\2\2\u0104\62\3\2\2\2\u0105\u0106\7"+
		"?\2\2\u0106\64\3\2\2\2\u0107\u0109\7/\2\2\u0108\u0107\3\2\2\2\u0108\u0109"+
		"\3\2\2\2\u0109\u011e\3\2\2\2\u010a\u010c\t\4\2\2\u010b\u010a\3\2\2\2\u010c"+
		"\u010d\3\2\2\2\u010d\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010f\3\2"+
		"\2\2\u010f\u0113\7\60\2\2\u0110\u0112\t\5\2\2\u0111\u0110\3\2\2\2\u0112"+
		"\u0115\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u011f\3\2"+
		"\2\2\u0115\u0113\3\2\2\2\u0116\u0118\7\60\2\2\u0117\u0116\3\2\2\2\u0117"+
		"\u0118\3\2\2\2\u0118\u011a\3\2\2\2\u0119\u011b\t\6\2\2\u011a\u0119\3\2"+
		"\2\2\u011b\u011c\3\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d"+
		"\u011f\3\2\2\2\u011e\u010b\3\2\2\2\u011e\u0117\3\2\2\2\u011f\66\3\2\2"+
		"\2\u0120\u0124\t\7\2\2\u0121\u0123\t\b\2\2\u0122\u0121\3\2\2\2\u0123\u0126"+
		"\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u01258\3\2\2\2\u0126"+
		"\u0124\3\2\2\2\f\2=E\u0108\u010d\u0113\u0117\u011c\u011e\u0124";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}