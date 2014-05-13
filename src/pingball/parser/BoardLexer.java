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
		T__1=1, T__0=2, COMMENT=3, WHITESPACE=4, STRING_BOARD=5, STRING_BALL=6, 
		STRING_SQUAREBUMPER=7, STRING_TRIANGLEBUMPER=8, STRING_CIRCLEBUMPER=9, 
		STRING_LEFTFLIPPER=10, STRING_RIGHTFLIPPER=11, STRING_ABSORBER=12, STRING_PORTAL=13, 
		STRING_FIRE=14, STRING_KEYDOWN=15, STRING_KEYUP=16, STRING_NAME=17, STRING_GRAVITY=18, 
		STRING_FRICTION1=19, STRING_FRICTION2=20, STRING_WIDTH=21, STRING_HEIGHT=22, 
		STRING_TRIGGER=23, STRING_ACTION=24, STRING_ORIENTATION=25, STRING_XVELOCITY=26, 
		STRING_YVELOCITY=27, STRING_KEY=28, STRING_OTHERBOARD=29, STRING_OTHERPORTAL=30, 
		EQUALS=31, NUMBER=32, NAME=33;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'x'", "'y'", "COMMENT", "WHITESPACE", "'board'", "'ball'", "'squareBumper'", 
		"'triangleBumper'", "'circleBumper'", "'leftFlipper'", "'rightFlipper'", 
		"'absorber'", "'portal'", "'fire'", "'keydown'", "'keyup'", "'name'", 
		"'gravity'", "'friction1'", "'friction2'", "'width'", "'height'", "'trigger'", 
		"'action'", "'orientation'", "'xVelocity'", "'yVelocity'", "'key'", "'otherBoard'", 
		"'otherPortal'", "'='", "NUMBER", "NAME"
	};
	public static final String[] ruleNames = {
		"T__1", "T__0", "COMMENT", "WHITESPACE", "STRING_BOARD", "STRING_BALL", 
		"STRING_SQUAREBUMPER", "STRING_TRIANGLEBUMPER", "STRING_CIRCLEBUMPER", 
		"STRING_LEFTFLIPPER", "STRING_RIGHTFLIPPER", "STRING_ABSORBER", "STRING_PORTAL", 
		"STRING_FIRE", "STRING_KEYDOWN", "STRING_KEYUP", "STRING_NAME", "STRING_GRAVITY", 
		"STRING_FRICTION1", "STRING_FRICTION2", "STRING_WIDTH", "STRING_HEIGHT", 
		"STRING_TRIGGER", "STRING_ACTION", "STRING_ORIENTATION", "STRING_XVELOCITY", 
		"STRING_YVELOCITY", "STRING_KEY", "STRING_OTHERBOARD", "STRING_OTHERPORTAL", 
		"EQUALS", "NUMBER", "NAME"
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
		case 2: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 3: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;
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
		"\2\4#\u0163\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\3\2\3\2\3\3\3\3\3\4\3\4\7\4L\n\4\f"+
		"\4\16\4O\13\4\3\4\3\4\3\5\6\5T\n\5\r\5\16\5U\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3"+
		"\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3"+
		"\37\3\37\3\37\3\37\3 \3 \3!\5!\u0145\n!\3!\6!\u0148\n!\r!\16!\u0149\3"+
		"!\3!\7!\u014e\n!\f!\16!\u0151\13!\3!\5!\u0154\n!\3!\6!\u0157\n!\r!\16"+
		"!\u0158\5!\u015b\n!\3\"\3\"\7\"\u015f\n\"\f\"\16\"\u0162\13\"\2#\3\3\1"+
		"\5\4\1\7\5\2\t\6\3\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31"+
		"\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30"+
		"\1/\31\1\61\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1"+
		"\3\2\t\3\f\f\5\13\f\17\17\"\"\3\62;\3\62;\3\62;\5C\\aac|\6\62;C\\aac|"+
		"\u016b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2"+
		"\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3E\3\2\2\2\5"+
		"G\3\2\2\2\7I\3\2\2\2\tS\3\2\2\2\13Y\3\2\2\2\r_\3\2\2\2\17d\3\2\2\2\21"+
		"q\3\2\2\2\23\u0080\3\2\2\2\25\u008d\3\2\2\2\27\u0099\3\2\2\2\31\u00a6"+
		"\3\2\2\2\33\u00af\3\2\2\2\35\u00b6\3\2\2\2\37\u00bb\3\2\2\2!\u00c3\3\2"+
		"\2\2#\u00c9\3\2\2\2%\u00ce\3\2\2\2\'\u00d6\3\2\2\2)\u00e0\3\2\2\2+\u00ea"+
		"\3\2\2\2-\u00f0\3\2\2\2/\u00f7\3\2\2\2\61\u00ff\3\2\2\2\63\u0106\3\2\2"+
		"\2\65\u0112\3\2\2\2\67\u011c\3\2\2\29\u0126\3\2\2\2;\u012a\3\2\2\2=\u0135"+
		"\3\2\2\2?\u0141\3\2\2\2A\u0144\3\2\2\2C\u015c\3\2\2\2EF\7z\2\2F\4\3\2"+
		"\2\2GH\7{\2\2H\6\3\2\2\2IM\7%\2\2JL\n\2\2\2KJ\3\2\2\2LO\3\2\2\2MK\3\2"+
		"\2\2MN\3\2\2\2NP\3\2\2\2OM\3\2\2\2PQ\b\4\2\2Q\b\3\2\2\2RT\t\3\2\2SR\3"+
		"\2\2\2TU\3\2\2\2US\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\b\5\3\2X\n\3\2\2\2YZ"+
		"\7d\2\2Z[\7q\2\2[\\\7c\2\2\\]\7t\2\2]^\7f\2\2^\f\3\2\2\2_`\7d\2\2`a\7"+
		"c\2\2ab\7n\2\2bc\7n\2\2c\16\3\2\2\2de\7u\2\2ef\7s\2\2fg\7w\2\2gh\7c\2"+
		"\2hi\7t\2\2ij\7g\2\2jk\7D\2\2kl\7w\2\2lm\7o\2\2mn\7r\2\2no\7g\2\2op\7"+
		"t\2\2p\20\3\2\2\2qr\7v\2\2rs\7t\2\2st\7k\2\2tu\7c\2\2uv\7p\2\2vw\7i\2"+
		"\2wx\7n\2\2xy\7g\2\2yz\7D\2\2z{\7w\2\2{|\7o\2\2|}\7r\2\2}~\7g\2\2~\177"+
		"\7t\2\2\177\22\3\2\2\2\u0080\u0081\7e\2\2\u0081\u0082\7k\2\2\u0082\u0083"+
		"\7t\2\2\u0083\u0084\7e\2\2\u0084\u0085\7n\2\2\u0085\u0086\7g\2\2\u0086"+
		"\u0087\7D\2\2\u0087\u0088\7w\2\2\u0088\u0089\7o\2\2\u0089\u008a\7r\2\2"+
		"\u008a\u008b\7g\2\2\u008b\u008c\7t\2\2\u008c\24\3\2\2\2\u008d\u008e\7"+
		"n\2\2\u008e\u008f\7g\2\2\u008f\u0090\7h\2\2\u0090\u0091\7v\2\2\u0091\u0092"+
		"\7H\2\2\u0092\u0093\7n\2\2\u0093\u0094\7k\2\2\u0094\u0095\7r\2\2\u0095"+
		"\u0096\7r\2\2\u0096\u0097\7g\2\2\u0097\u0098\7t\2\2\u0098\26\3\2\2\2\u0099"+
		"\u009a\7t\2\2\u009a\u009b\7k\2\2\u009b\u009c\7i\2\2\u009c\u009d\7j\2\2"+
		"\u009d\u009e\7v\2\2\u009e\u009f\7H\2\2\u009f\u00a0\7n\2\2\u00a0\u00a1"+
		"\7k\2\2\u00a1\u00a2\7r\2\2\u00a2\u00a3\7r\2\2\u00a3\u00a4\7g\2\2\u00a4"+
		"\u00a5\7t\2\2\u00a5\30\3\2\2\2\u00a6\u00a7\7c\2\2\u00a7\u00a8\7d\2\2\u00a8"+
		"\u00a9\7u\2\2\u00a9\u00aa\7q\2\2\u00aa\u00ab\7t\2\2\u00ab\u00ac\7d\2\2"+
		"\u00ac\u00ad\7g\2\2\u00ad\u00ae\7t\2\2\u00ae\32\3\2\2\2\u00af\u00b0\7"+
		"r\2\2\u00b0\u00b1\7q\2\2\u00b1\u00b2\7t\2\2\u00b2\u00b3\7v\2\2\u00b3\u00b4"+
		"\7c\2\2\u00b4\u00b5\7n\2\2\u00b5\34\3\2\2\2\u00b6\u00b7\7h\2\2\u00b7\u00b8"+
		"\7k\2\2\u00b8\u00b9\7t\2\2\u00b9\u00ba\7g\2\2\u00ba\36\3\2\2\2\u00bb\u00bc"+
		"\7m\2\2\u00bc\u00bd\7g\2\2\u00bd\u00be\7{\2\2\u00be\u00bf\7f\2\2\u00bf"+
		"\u00c0\7q\2\2\u00c0\u00c1\7y\2\2\u00c1\u00c2\7p\2\2\u00c2 \3\2\2\2\u00c3"+
		"\u00c4\7m\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c6\7{\2\2\u00c6\u00c7\7w\2\2"+
		"\u00c7\u00c8\7r\2\2\u00c8\"\3\2\2\2\u00c9\u00ca\7p\2\2\u00ca\u00cb\7c"+
		"\2\2\u00cb\u00cc\7o\2\2\u00cc\u00cd\7g\2\2\u00cd$\3\2\2\2\u00ce\u00cf"+
		"\7i\2\2\u00cf\u00d0\7t\2\2\u00d0\u00d1\7c\2\2\u00d1\u00d2\7x\2\2\u00d2"+
		"\u00d3\7k\2\2\u00d3\u00d4\7v\2\2\u00d4\u00d5\7{\2\2\u00d5&\3\2\2\2\u00d6"+
		"\u00d7\7h\2\2\u00d7\u00d8\7t\2\2\u00d8\u00d9\7k\2\2\u00d9\u00da\7e\2\2"+
		"\u00da\u00db\7v\2\2\u00db\u00dc\7k\2\2\u00dc\u00dd\7q\2\2\u00dd\u00de"+
		"\7p\2\2\u00de\u00df\7\63\2\2\u00df(\3\2\2\2\u00e0\u00e1\7h\2\2\u00e1\u00e2"+
		"\7t\2\2\u00e2\u00e3\7k\2\2\u00e3\u00e4\7e\2\2\u00e4\u00e5\7v\2\2\u00e5"+
		"\u00e6\7k\2\2\u00e6\u00e7\7q\2\2\u00e7\u00e8\7p\2\2\u00e8\u00e9\7\64\2"+
		"\2\u00e9*\3\2\2\2\u00ea\u00eb\7y\2\2\u00eb\u00ec\7k\2\2\u00ec\u00ed\7"+
		"f\2\2\u00ed\u00ee\7v\2\2\u00ee\u00ef\7j\2\2\u00ef,\3\2\2\2\u00f0\u00f1"+
		"\7j\2\2\u00f1\u00f2\7g\2\2\u00f2\u00f3\7k\2\2\u00f3\u00f4\7i\2\2\u00f4"+
		"\u00f5\7j\2\2\u00f5\u00f6\7v\2\2\u00f6.\3\2\2\2\u00f7\u00f8\7v\2\2\u00f8"+
		"\u00f9\7t\2\2\u00f9\u00fa\7k\2\2\u00fa\u00fb\7i\2\2\u00fb\u00fc\7i\2\2"+
		"\u00fc\u00fd\7g\2\2\u00fd\u00fe\7t\2\2\u00fe\60\3\2\2\2\u00ff\u0100\7"+
		"c\2\2\u0100\u0101\7e\2\2\u0101\u0102\7v\2\2\u0102\u0103\7k\2\2\u0103\u0104"+
		"\7q\2\2\u0104\u0105\7p\2\2\u0105\62\3\2\2\2\u0106\u0107\7q\2\2\u0107\u0108"+
		"\7t\2\2\u0108\u0109\7k\2\2\u0109\u010a\7g\2\2\u010a\u010b\7p\2\2\u010b"+
		"\u010c\7v\2\2\u010c\u010d\7c\2\2\u010d\u010e\7v\2\2\u010e\u010f\7k\2\2"+
		"\u010f\u0110\7q\2\2\u0110\u0111\7p\2\2\u0111\64\3\2\2\2\u0112\u0113\7"+
		"z\2\2\u0113\u0114\7X\2\2\u0114\u0115\7g\2\2\u0115\u0116\7n\2\2\u0116\u0117"+
		"\7q\2\2\u0117\u0118\7e\2\2\u0118\u0119\7k\2\2\u0119\u011a\7v\2\2\u011a"+
		"\u011b\7{\2\2\u011b\66\3\2\2\2\u011c\u011d\7{\2\2\u011d\u011e\7X\2\2\u011e"+
		"\u011f\7g\2\2\u011f\u0120\7n\2\2\u0120\u0121\7q\2\2\u0121\u0122\7e\2\2"+
		"\u0122\u0123\7k\2\2\u0123\u0124\7v\2\2\u0124\u0125\7{\2\2\u01258\3\2\2"+
		"\2\u0126\u0127\7m\2\2\u0127\u0128\7g\2\2\u0128\u0129\7{\2\2\u0129:\3\2"+
		"\2\2\u012a\u012b\7q\2\2\u012b\u012c\7v\2\2\u012c\u012d\7j\2\2\u012d\u012e"+
		"\7g\2\2\u012e\u012f\7t\2\2\u012f\u0130\7D\2\2\u0130\u0131\7q\2\2\u0131"+
		"\u0132\7c\2\2\u0132\u0133\7t\2\2\u0133\u0134\7f\2\2\u0134<\3\2\2\2\u0135"+
		"\u0136\7q\2\2\u0136\u0137\7v\2\2\u0137\u0138\7j\2\2\u0138\u0139\7g\2\2"+
		"\u0139\u013a\7t\2\2\u013a\u013b\7R\2\2\u013b\u013c\7q\2\2\u013c\u013d"+
		"\7t\2\2\u013d\u013e\7v\2\2\u013e\u013f\7c\2\2\u013f\u0140\7n\2\2\u0140"+
		">\3\2\2\2\u0141\u0142\7?\2\2\u0142@\3\2\2\2\u0143\u0145\7/\2\2\u0144\u0143"+
		"\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u015a\3\2\2\2\u0146\u0148\t\4\2\2\u0147"+
		"\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2"+
		"\2\2\u014a\u014b\3\2\2\2\u014b\u014f\7\60\2\2\u014c\u014e\t\5\2\2\u014d"+
		"\u014c\3\2\2\2\u014e\u0151\3\2\2\2\u014f\u014d\3\2\2\2\u014f\u0150\3\2"+
		"\2\2\u0150\u015b\3\2\2\2\u0151\u014f\3\2\2\2\u0152\u0154\7\60\2\2\u0153"+
		"\u0152\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0156\3\2\2\2\u0155\u0157\t\6"+
		"\2\2\u0156\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u0156\3\2\2\2\u0158"+
		"\u0159\3\2\2\2\u0159\u015b\3\2\2\2\u015a\u0147\3\2\2\2\u015a\u0153\3\2"+
		"\2\2\u015bB\3\2\2\2\u015c\u0160\t\7\2\2\u015d\u015f\t\b\2\2\u015e\u015d"+
		"\3\2\2\2\u015f\u0162\3\2\2\2\u0160\u015e\3\2\2\2\u0160\u0161\3\2\2\2\u0161"+
		"D\3\2\2\2\u0162\u0160\3\2\2\2\f\2MU\u0144\u0149\u014f\u0153\u0158\u015a"+
		"\u0160";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}