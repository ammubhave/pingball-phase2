// Generated from src/pingball/parser/Board.g4 by ANTLR 4.0

package pingball.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BoardParser extends Parser {
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
	public static final String[] tokenNames = {
		"<INVALID>", "'x'", "'y'", "COMMENT", "WHITESPACE", "'board'", "'ball'", 
		"'squareBumper'", "'triangleBumper'", "'circleBumper'", "'leftFlipper'", 
		"'rightFlipper'", "'absorber'", "'portal'", "'fire'", "'keydown'", "'keyup'", 
		"'name'", "'gravity'", "'friction1'", "'friction2'", "'width'", "'height'", 
		"'trigger'", "'action'", "'orientation'", "'xVelocity'", "'yVelocity'", 
		"'key'", "'otherBoard'", "'otherPortal'", "'='", "NUMBER", "NAME"
	};
	public static final int
		RULE_board = 0, RULE_objectLine = 1, RULE_boardObjectLine = 2, RULE_ballObjectLine = 3, 
		RULE_squareBumperObjectLine = 4, RULE_triangleBumperObjectLine = 5, RULE_circleBumperObjectLine = 6, 
		RULE_leftFlipperObjectLine = 7, RULE_rightFlipperObjectLine = 8, RULE_absorberObjectLine = 9, 
		RULE_fireObjectLine = 10, RULE_keydownObjectLine = 11, RULE_keyupObjectLine = 12, 
		RULE_portalObjectLine = 13, RULE_boardAttributes = 14, RULE_ballAttributes = 15, 
		RULE_squareBumperAttributes = 16, RULE_triangleBumperAttributes = 17, 
		RULE_circleBumperAttributes = 18, RULE_leftFlipperAttributes = 19, RULE_rightFlipperAttributes = 20, 
		RULE_absorberAttributes = 21, RULE_fireAttributes = 22, RULE_keydownAttributes = 23, 
		RULE_keyupAttributes = 24, RULE_portalAttributes = 25, RULE_attributeName = 26, 
		RULE_attributeGravity = 27, RULE_attributeFriction1 = 28, RULE_attributeFriction2 = 29, 
		RULE_attributeX = 30, RULE_attributeY = 31, RULE_attributeXVelocity = 32, 
		RULE_attributeYVelocity = 33, RULE_attributeOrientation = 34, RULE_attributeTrigger = 35, 
		RULE_attributeAction = 36, RULE_attributeWidth = 37, RULE_attributeHeight = 38, 
		RULE_attributeKey = 39, RULE_attributeOtherboard = 40, RULE_attributeOtherportal = 41;
	public static final String[] ruleNames = {
		"board", "objectLine", "boardObjectLine", "ballObjectLine", "squareBumperObjectLine", 
		"triangleBumperObjectLine", "circleBumperObjectLine", "leftFlipperObjectLine", 
		"rightFlipperObjectLine", "absorberObjectLine", "fireObjectLine", "keydownObjectLine", 
		"keyupObjectLine", "portalObjectLine", "boardAttributes", "ballAttributes", 
		"squareBumperAttributes", "triangleBumperAttributes", "circleBumperAttributes", 
		"leftFlipperAttributes", "rightFlipperAttributes", "absorberAttributes", 
		"fireAttributes", "keydownAttributes", "keyupAttributes", "portalAttributes", 
		"attributeName", "attributeGravity", "attributeFriction1", "attributeFriction2", 
		"attributeX", "attributeY", "attributeXVelocity", "attributeYVelocity", 
		"attributeOrientation", "attributeTrigger", "attributeAction", "attributeWidth", 
		"attributeHeight", "attributeKey", "attributeOtherboard", "attributeOtherportal"
	};

	@Override
	public String getGrammarFileName() { return "Board.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public BoardParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class BoardContext extends ParserRuleContext {
		public List<ObjectLineContext> objectLine() {
			return getRuleContexts(ObjectLineContext.class);
		}
		public TerminalNode EOF() { return getToken(BoardParser.EOF, 0); }
		public ObjectLineContext objectLine(int i) {
			return getRuleContext(ObjectLineContext.class,i);
		}
		public BoardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_board; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterBoard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitBoard(this);
		}
	}

	public final BoardContext board() throws RecognitionException {
		BoardContext _localctx = new BoardContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_board);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(84); objectLine();
				}
				}
				setState(87); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING_BOARD) | (1L << STRING_BALL) | (1L << STRING_SQUAREBUMPER) | (1L << STRING_TRIANGLEBUMPER) | (1L << STRING_CIRCLEBUMPER) | (1L << STRING_LEFTFLIPPER) | (1L << STRING_RIGHTFLIPPER) | (1L << STRING_ABSORBER) | (1L << STRING_PORTAL) | (1L << STRING_FIRE) | (1L << STRING_KEYDOWN) | (1L << STRING_KEYUP))) != 0) );
			setState(89); match(EOF);
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

	public static class ObjectLineContext extends ParserRuleContext {
		public LeftFlipperObjectLineContext leftFlipperObjectLine() {
			return getRuleContext(LeftFlipperObjectLineContext.class,0);
		}
		public CircleBumperObjectLineContext circleBumperObjectLine() {
			return getRuleContext(CircleBumperObjectLineContext.class,0);
		}
		public SquareBumperObjectLineContext squareBumperObjectLine() {
			return getRuleContext(SquareBumperObjectLineContext.class,0);
		}
		public AbsorberObjectLineContext absorberObjectLine() {
			return getRuleContext(AbsorberObjectLineContext.class,0);
		}
		public RightFlipperObjectLineContext rightFlipperObjectLine() {
			return getRuleContext(RightFlipperObjectLineContext.class,0);
		}
		public FireObjectLineContext fireObjectLine() {
			return getRuleContext(FireObjectLineContext.class,0);
		}
		public KeyupObjectLineContext keyupObjectLine() {
			return getRuleContext(KeyupObjectLineContext.class,0);
		}
		public BallObjectLineContext ballObjectLine() {
			return getRuleContext(BallObjectLineContext.class,0);
		}
		public KeydownObjectLineContext keydownObjectLine() {
			return getRuleContext(KeydownObjectLineContext.class,0);
		}
		public BoardObjectLineContext boardObjectLine() {
			return getRuleContext(BoardObjectLineContext.class,0);
		}
		public PortalObjectLineContext portalObjectLine() {
			return getRuleContext(PortalObjectLineContext.class,0);
		}
		public TriangleBumperObjectLineContext triangleBumperObjectLine() {
			return getRuleContext(TriangleBumperObjectLineContext.class,0);
		}
		public ObjectLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterObjectLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitObjectLine(this);
		}
	}

	public final ObjectLineContext objectLine() throws RecognitionException {
		ObjectLineContext _localctx = new ObjectLineContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_objectLine);
		try {
			setState(103);
			switch (_input.LA(1)) {
			case STRING_BOARD:
				enterOuterAlt(_localctx, 1);
				{
				setState(91); boardObjectLine();
				}
				break;
			case STRING_BALL:
				enterOuterAlt(_localctx, 2);
				{
				setState(92); ballObjectLine();
				}
				break;
			case STRING_SQUAREBUMPER:
				enterOuterAlt(_localctx, 3);
				{
				setState(93); squareBumperObjectLine();
				}
				break;
			case STRING_TRIANGLEBUMPER:
				enterOuterAlt(_localctx, 4);
				{
				setState(94); triangleBumperObjectLine();
				}
				break;
			case STRING_CIRCLEBUMPER:
				enterOuterAlt(_localctx, 5);
				{
				setState(95); circleBumperObjectLine();
				}
				break;
			case STRING_LEFTFLIPPER:
				enterOuterAlt(_localctx, 6);
				{
				setState(96); leftFlipperObjectLine();
				}
				break;
			case STRING_RIGHTFLIPPER:
				enterOuterAlt(_localctx, 7);
				{
				setState(97); rightFlipperObjectLine();
				}
				break;
			case STRING_ABSORBER:
				enterOuterAlt(_localctx, 8);
				{
				setState(98); absorberObjectLine();
				}
				break;
			case STRING_FIRE:
				enterOuterAlt(_localctx, 9);
				{
				setState(99); fireObjectLine();
				}
				break;
			case STRING_KEYUP:
				enterOuterAlt(_localctx, 10);
				{
				setState(100); keyupObjectLine();
				}
				break;
			case STRING_KEYDOWN:
				enterOuterAlt(_localctx, 11);
				{
				setState(101); keydownObjectLine();
				}
				break;
			case STRING_PORTAL:
				enterOuterAlt(_localctx, 12);
				{
				setState(102); portalObjectLine();
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

	public static class BoardObjectLineContext extends ParserRuleContext {
		public BoardAttributesContext boardAttributes(int i) {
			return getRuleContext(BoardAttributesContext.class,i);
		}
		public List<BoardAttributesContext> boardAttributes() {
			return getRuleContexts(BoardAttributesContext.class);
		}
		public TerminalNode STRING_BOARD() { return getToken(BoardParser.STRING_BOARD, 0); }
		public BoardObjectLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boardObjectLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterBoardObjectLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitBoardObjectLine(this);
		}
	}

	public final BoardObjectLineContext boardObjectLine() throws RecognitionException {
		BoardObjectLineContext _localctx = new BoardObjectLineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_boardObjectLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105); match(STRING_BOARD);
			setState(107); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(106); boardAttributes();
				}
				}
				setState(109); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING_NAME) | (1L << STRING_GRAVITY) | (1L << STRING_FRICTION1) | (1L << STRING_FRICTION2))) != 0) );
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

	public static class BallObjectLineContext extends ParserRuleContext {
		public BallAttributesContext ballAttributes(int i) {
			return getRuleContext(BallAttributesContext.class,i);
		}
		public TerminalNode STRING_BALL() { return getToken(BoardParser.STRING_BALL, 0); }
		public List<BallAttributesContext> ballAttributes() {
			return getRuleContexts(BallAttributesContext.class);
		}
		public BallObjectLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ballObjectLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterBallObjectLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitBallObjectLine(this);
		}
	}

	public final BallObjectLineContext ballObjectLine() throws RecognitionException {
		BallObjectLineContext _localctx = new BallObjectLineContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ballObjectLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111); match(STRING_BALL);
			setState(113); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(112); ballAttributes();
				}
				}
				setState(115); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << STRING_NAME) | (1L << STRING_XVELOCITY) | (1L << STRING_YVELOCITY))) != 0) );
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

	public static class SquareBumperObjectLineContext extends ParserRuleContext {
		public SquareBumperAttributesContext squareBumperAttributes(int i) {
			return getRuleContext(SquareBumperAttributesContext.class,i);
		}
		public TerminalNode STRING_SQUAREBUMPER() { return getToken(BoardParser.STRING_SQUAREBUMPER, 0); }
		public List<SquareBumperAttributesContext> squareBumperAttributes() {
			return getRuleContexts(SquareBumperAttributesContext.class);
		}
		public SquareBumperObjectLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_squareBumperObjectLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterSquareBumperObjectLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitSquareBumperObjectLine(this);
		}
	}

	public final SquareBumperObjectLineContext squareBumperObjectLine() throws RecognitionException {
		SquareBumperObjectLineContext _localctx = new SquareBumperObjectLineContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_squareBumperObjectLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117); match(STRING_SQUAREBUMPER);
			setState(119); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(118); squareBumperAttributes();
				}
				}
				setState(121); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << STRING_NAME))) != 0) );
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

	public static class TriangleBumperObjectLineContext extends ParserRuleContext {
		public List<TriangleBumperAttributesContext> triangleBumperAttributes() {
			return getRuleContexts(TriangleBumperAttributesContext.class);
		}
		public TerminalNode STRING_TRIANGLEBUMPER() { return getToken(BoardParser.STRING_TRIANGLEBUMPER, 0); }
		public TriangleBumperAttributesContext triangleBumperAttributes(int i) {
			return getRuleContext(TriangleBumperAttributesContext.class,i);
		}
		public TriangleBumperObjectLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triangleBumperObjectLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterTriangleBumperObjectLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitTriangleBumperObjectLine(this);
		}
	}

	public final TriangleBumperObjectLineContext triangleBumperObjectLine() throws RecognitionException {
		TriangleBumperObjectLineContext _localctx = new TriangleBumperObjectLineContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_triangleBumperObjectLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123); match(STRING_TRIANGLEBUMPER);
			setState(125); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(124); triangleBumperAttributes();
				}
				}
				setState(127); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << STRING_NAME) | (1L << STRING_ORIENTATION))) != 0) );
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

	public static class CircleBumperObjectLineContext extends ParserRuleContext {
		public List<CircleBumperAttributesContext> circleBumperAttributes() {
			return getRuleContexts(CircleBumperAttributesContext.class);
		}
		public TerminalNode STRING_CIRCLEBUMPER() { return getToken(BoardParser.STRING_CIRCLEBUMPER, 0); }
		public CircleBumperAttributesContext circleBumperAttributes(int i) {
			return getRuleContext(CircleBumperAttributesContext.class,i);
		}
		public CircleBumperObjectLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_circleBumperObjectLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterCircleBumperObjectLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitCircleBumperObjectLine(this);
		}
	}

	public final CircleBumperObjectLineContext circleBumperObjectLine() throws RecognitionException {
		CircleBumperObjectLineContext _localctx = new CircleBumperObjectLineContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_circleBumperObjectLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129); match(STRING_CIRCLEBUMPER);
			setState(131); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(130); circleBumperAttributes();
				}
				}
				setState(133); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << STRING_NAME))) != 0) );
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

	public static class LeftFlipperObjectLineContext extends ParserRuleContext {
		public List<LeftFlipperAttributesContext> leftFlipperAttributes() {
			return getRuleContexts(LeftFlipperAttributesContext.class);
		}
		public LeftFlipperAttributesContext leftFlipperAttributes(int i) {
			return getRuleContext(LeftFlipperAttributesContext.class,i);
		}
		public TerminalNode STRING_LEFTFLIPPER() { return getToken(BoardParser.STRING_LEFTFLIPPER, 0); }
		public LeftFlipperObjectLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftFlipperObjectLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterLeftFlipperObjectLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitLeftFlipperObjectLine(this);
		}
	}

	public final LeftFlipperObjectLineContext leftFlipperObjectLine() throws RecognitionException {
		LeftFlipperObjectLineContext _localctx = new LeftFlipperObjectLineContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_leftFlipperObjectLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135); match(STRING_LEFTFLIPPER);
			setState(137); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(136); leftFlipperAttributes();
				}
				}
				setState(139); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << STRING_NAME) | (1L << STRING_ORIENTATION))) != 0) );
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

	public static class RightFlipperObjectLineContext extends ParserRuleContext {
		public RightFlipperAttributesContext rightFlipperAttributes(int i) {
			return getRuleContext(RightFlipperAttributesContext.class,i);
		}
		public TerminalNode STRING_RIGHTFLIPPER() { return getToken(BoardParser.STRING_RIGHTFLIPPER, 0); }
		public List<RightFlipperAttributesContext> rightFlipperAttributes() {
			return getRuleContexts(RightFlipperAttributesContext.class);
		}
		public RightFlipperObjectLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightFlipperObjectLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterRightFlipperObjectLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitRightFlipperObjectLine(this);
		}
	}

	public final RightFlipperObjectLineContext rightFlipperObjectLine() throws RecognitionException {
		RightFlipperObjectLineContext _localctx = new RightFlipperObjectLineContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_rightFlipperObjectLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141); match(STRING_RIGHTFLIPPER);
			setState(143); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(142); rightFlipperAttributes();
				}
				}
				setState(145); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << STRING_NAME) | (1L << STRING_ORIENTATION))) != 0) );
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

	public static class AbsorberObjectLineContext extends ParserRuleContext {
		public AbsorberAttributesContext absorberAttributes(int i) {
			return getRuleContext(AbsorberAttributesContext.class,i);
		}
		public TerminalNode STRING_ABSORBER() { return getToken(BoardParser.STRING_ABSORBER, 0); }
		public List<AbsorberAttributesContext> absorberAttributes() {
			return getRuleContexts(AbsorberAttributesContext.class);
		}
		public AbsorberObjectLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_absorberObjectLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAbsorberObjectLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAbsorberObjectLine(this);
		}
	}

	public final AbsorberObjectLineContext absorberObjectLine() throws RecognitionException {
		AbsorberObjectLineContext _localctx = new AbsorberObjectLineContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_absorberObjectLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147); match(STRING_ABSORBER);
			setState(149); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(148); absorberAttributes();
				}
				}
				setState(151); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << STRING_NAME) | (1L << STRING_WIDTH) | (1L << STRING_HEIGHT))) != 0) );
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

	public static class FireObjectLineContext extends ParserRuleContext {
		public TerminalNode STRING_FIRE() { return getToken(BoardParser.STRING_FIRE, 0); }
		public List<FireAttributesContext> fireAttributes() {
			return getRuleContexts(FireAttributesContext.class);
		}
		public FireAttributesContext fireAttributes(int i) {
			return getRuleContext(FireAttributesContext.class,i);
		}
		public FireObjectLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fireObjectLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterFireObjectLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitFireObjectLine(this);
		}
	}

	public final FireObjectLineContext fireObjectLine() throws RecognitionException {
		FireObjectLineContext _localctx = new FireObjectLineContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_fireObjectLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153); match(STRING_FIRE);
			setState(155); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(154); fireAttributes();
				}
				}
				setState(157); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING_NAME) | (1L << STRING_TRIGGER) | (1L << STRING_ACTION))) != 0) );
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

	public static class KeydownObjectLineContext extends ParserRuleContext {
		public TerminalNode STRING_KEYDOWN() { return getToken(BoardParser.STRING_KEYDOWN, 0); }
		public List<KeydownAttributesContext> keydownAttributes() {
			return getRuleContexts(KeydownAttributesContext.class);
		}
		public KeydownAttributesContext keydownAttributes(int i) {
			return getRuleContext(KeydownAttributesContext.class,i);
		}
		public KeydownObjectLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keydownObjectLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterKeydownObjectLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitKeydownObjectLine(this);
		}
	}

	public final KeydownObjectLineContext keydownObjectLine() throws RecognitionException {
		KeydownObjectLineContext _localctx = new KeydownObjectLineContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_keydownObjectLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159); match(STRING_KEYDOWN);
			setState(161); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(160); keydownAttributes();
				}
				}
				setState(163); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STRING_ACTION || _la==STRING_KEY );
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

	public static class KeyupObjectLineContext extends ParserRuleContext {
		public KeyupAttributesContext keyupAttributes(int i) {
			return getRuleContext(KeyupAttributesContext.class,i);
		}
		public TerminalNode STRING_KEYUP() { return getToken(BoardParser.STRING_KEYUP, 0); }
		public List<KeyupAttributesContext> keyupAttributes() {
			return getRuleContexts(KeyupAttributesContext.class);
		}
		public KeyupObjectLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyupObjectLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterKeyupObjectLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitKeyupObjectLine(this);
		}
	}

	public final KeyupObjectLineContext keyupObjectLine() throws RecognitionException {
		KeyupObjectLineContext _localctx = new KeyupObjectLineContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_keyupObjectLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165); match(STRING_KEYUP);
			setState(167); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(166); keyupAttributes();
				}
				}
				setState(169); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STRING_ACTION || _la==STRING_KEY );
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

	public static class PortalObjectLineContext extends ParserRuleContext {
		public List<PortalAttributesContext> portalAttributes() {
			return getRuleContexts(PortalAttributesContext.class);
		}
		public TerminalNode STRING_PORTAL() { return getToken(BoardParser.STRING_PORTAL, 0); }
		public PortalAttributesContext portalAttributes(int i) {
			return getRuleContext(PortalAttributesContext.class,i);
		}
		public PortalObjectLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portalObjectLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterPortalObjectLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitPortalObjectLine(this);
		}
	}

	public final PortalObjectLineContext portalObjectLine() throws RecognitionException {
		PortalObjectLineContext _localctx = new PortalObjectLineContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_portalObjectLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171); match(STRING_PORTAL);
			setState(173); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(172); portalAttributes();
				}
				}
				setState(175); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << STRING_NAME) | (1L << STRING_OTHERBOARD) | (1L << STRING_OTHERPORTAL))) != 0) );
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

	public static class BoardAttributesContext extends ParserRuleContext {
		public AttributeNameContext attributeName() {
			return getRuleContext(AttributeNameContext.class,0);
		}
		public AttributeFriction1Context attributeFriction1() {
			return getRuleContext(AttributeFriction1Context.class,0);
		}
		public AttributeFriction2Context attributeFriction2() {
			return getRuleContext(AttributeFriction2Context.class,0);
		}
		public AttributeGravityContext attributeGravity() {
			return getRuleContext(AttributeGravityContext.class,0);
		}
		public BoardAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boardAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterBoardAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitBoardAttributes(this);
		}
	}

	public final BoardAttributesContext boardAttributes() throws RecognitionException {
		BoardAttributesContext _localctx = new BoardAttributesContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_boardAttributes);
		try {
			setState(181);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(177); attributeName();
				}
				break;
			case STRING_GRAVITY:
				enterOuterAlt(_localctx, 2);
				{
				setState(178); attributeGravity();
				}
				break;
			case STRING_FRICTION1:
				enterOuterAlt(_localctx, 3);
				{
				setState(179); attributeFriction1();
				}
				break;
			case STRING_FRICTION2:
				enterOuterAlt(_localctx, 4);
				{
				setState(180); attributeFriction2();
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

	public static class BallAttributesContext extends ParserRuleContext {
		public AttributeYVelocityContext attributeYVelocity() {
			return getRuleContext(AttributeYVelocityContext.class,0);
		}
		public AttributeNameContext attributeName() {
			return getRuleContext(AttributeNameContext.class,0);
		}
		public AttributeXContext attributeX() {
			return getRuleContext(AttributeXContext.class,0);
		}
		public AttributeYContext attributeY() {
			return getRuleContext(AttributeYContext.class,0);
		}
		public AttributeXVelocityContext attributeXVelocity() {
			return getRuleContext(AttributeXVelocityContext.class,0);
		}
		public BallAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ballAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterBallAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitBallAttributes(this);
		}
	}

	public final BallAttributesContext ballAttributes() throws RecognitionException {
		BallAttributesContext _localctx = new BallAttributesContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_ballAttributes);
		try {
			setState(188);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(183); attributeName();
				}
				break;
			case 1:
				enterOuterAlt(_localctx, 2);
				{
				setState(184); attributeX();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(185); attributeY();
				}
				break;
			case STRING_XVELOCITY:
				enterOuterAlt(_localctx, 4);
				{
				setState(186); attributeXVelocity();
				}
				break;
			case STRING_YVELOCITY:
				enterOuterAlt(_localctx, 5);
				{
				setState(187); attributeYVelocity();
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

	public static class SquareBumperAttributesContext extends ParserRuleContext {
		public AttributeNameContext attributeName() {
			return getRuleContext(AttributeNameContext.class,0);
		}
		public AttributeXContext attributeX() {
			return getRuleContext(AttributeXContext.class,0);
		}
		public AttributeYContext attributeY() {
			return getRuleContext(AttributeYContext.class,0);
		}
		public SquareBumperAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_squareBumperAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterSquareBumperAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitSquareBumperAttributes(this);
		}
	}

	public final SquareBumperAttributesContext squareBumperAttributes() throws RecognitionException {
		SquareBumperAttributesContext _localctx = new SquareBumperAttributesContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_squareBumperAttributes);
		try {
			setState(193);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(190); attributeName();
				}
				break;
			case 1:
				enterOuterAlt(_localctx, 2);
				{
				setState(191); attributeX();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(192); attributeY();
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

	public static class TriangleBumperAttributesContext extends ParserRuleContext {
		public AttributeNameContext attributeName() {
			return getRuleContext(AttributeNameContext.class,0);
		}
		public AttributeXContext attributeX() {
			return getRuleContext(AttributeXContext.class,0);
		}
		public AttributeYContext attributeY() {
			return getRuleContext(AttributeYContext.class,0);
		}
		public AttributeOrientationContext attributeOrientation() {
			return getRuleContext(AttributeOrientationContext.class,0);
		}
		public TriangleBumperAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triangleBumperAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterTriangleBumperAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitTriangleBumperAttributes(this);
		}
	}

	public final TriangleBumperAttributesContext triangleBumperAttributes() throws RecognitionException {
		TriangleBumperAttributesContext _localctx = new TriangleBumperAttributesContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_triangleBumperAttributes);
		try {
			setState(199);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(195); attributeName();
				}
				break;
			case 1:
				enterOuterAlt(_localctx, 2);
				{
				setState(196); attributeX();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(197); attributeY();
				}
				break;
			case STRING_ORIENTATION:
				enterOuterAlt(_localctx, 4);
				{
				setState(198); attributeOrientation();
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

	public static class CircleBumperAttributesContext extends ParserRuleContext {
		public AttributeNameContext attributeName() {
			return getRuleContext(AttributeNameContext.class,0);
		}
		public AttributeXContext attributeX() {
			return getRuleContext(AttributeXContext.class,0);
		}
		public AttributeYContext attributeY() {
			return getRuleContext(AttributeYContext.class,0);
		}
		public CircleBumperAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_circleBumperAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterCircleBumperAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitCircleBumperAttributes(this);
		}
	}

	public final CircleBumperAttributesContext circleBumperAttributes() throws RecognitionException {
		CircleBumperAttributesContext _localctx = new CircleBumperAttributesContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_circleBumperAttributes);
		try {
			setState(204);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(201); attributeName();
				}
				break;
			case 1:
				enterOuterAlt(_localctx, 2);
				{
				setState(202); attributeX();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(203); attributeY();
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

	public static class LeftFlipperAttributesContext extends ParserRuleContext {
		public AttributeNameContext attributeName() {
			return getRuleContext(AttributeNameContext.class,0);
		}
		public AttributeXContext attributeX() {
			return getRuleContext(AttributeXContext.class,0);
		}
		public AttributeYContext attributeY() {
			return getRuleContext(AttributeYContext.class,0);
		}
		public AttributeOrientationContext attributeOrientation() {
			return getRuleContext(AttributeOrientationContext.class,0);
		}
		public LeftFlipperAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftFlipperAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterLeftFlipperAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitLeftFlipperAttributes(this);
		}
	}

	public final LeftFlipperAttributesContext leftFlipperAttributes() throws RecognitionException {
		LeftFlipperAttributesContext _localctx = new LeftFlipperAttributesContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_leftFlipperAttributes);
		try {
			setState(210);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(206); attributeName();
				}
				break;
			case 1:
				enterOuterAlt(_localctx, 2);
				{
				setState(207); attributeX();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(208); attributeY();
				}
				break;
			case STRING_ORIENTATION:
				enterOuterAlt(_localctx, 4);
				{
				setState(209); attributeOrientation();
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

	public static class RightFlipperAttributesContext extends ParserRuleContext {
		public AttributeNameContext attributeName() {
			return getRuleContext(AttributeNameContext.class,0);
		}
		public AttributeXContext attributeX() {
			return getRuleContext(AttributeXContext.class,0);
		}
		public AttributeYContext attributeY() {
			return getRuleContext(AttributeYContext.class,0);
		}
		public AttributeOrientationContext attributeOrientation() {
			return getRuleContext(AttributeOrientationContext.class,0);
		}
		public RightFlipperAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightFlipperAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterRightFlipperAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitRightFlipperAttributes(this);
		}
	}

	public final RightFlipperAttributesContext rightFlipperAttributes() throws RecognitionException {
		RightFlipperAttributesContext _localctx = new RightFlipperAttributesContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_rightFlipperAttributes);
		try {
			setState(216);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(212); attributeName();
				}
				break;
			case 1:
				enterOuterAlt(_localctx, 2);
				{
				setState(213); attributeX();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(214); attributeY();
				}
				break;
			case STRING_ORIENTATION:
				enterOuterAlt(_localctx, 4);
				{
				setState(215); attributeOrientation();
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

	public static class AbsorberAttributesContext extends ParserRuleContext {
		public AttributeNameContext attributeName() {
			return getRuleContext(AttributeNameContext.class,0);
		}
		public AttributeHeightContext attributeHeight() {
			return getRuleContext(AttributeHeightContext.class,0);
		}
		public AttributeWidthContext attributeWidth() {
			return getRuleContext(AttributeWidthContext.class,0);
		}
		public AttributeXContext attributeX() {
			return getRuleContext(AttributeXContext.class,0);
		}
		public AttributeYContext attributeY() {
			return getRuleContext(AttributeYContext.class,0);
		}
		public AbsorberAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_absorberAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAbsorberAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAbsorberAttributes(this);
		}
	}

	public final AbsorberAttributesContext absorberAttributes() throws RecognitionException {
		AbsorberAttributesContext _localctx = new AbsorberAttributesContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_absorberAttributes);
		try {
			setState(223);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(218); attributeName();
				}
				break;
			case 1:
				enterOuterAlt(_localctx, 2);
				{
				setState(219); attributeX();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(220); attributeY();
				}
				break;
			case STRING_WIDTH:
				enterOuterAlt(_localctx, 4);
				{
				setState(221); attributeWidth();
				}
				break;
			case STRING_HEIGHT:
				enterOuterAlt(_localctx, 5);
				{
				setState(222); attributeHeight();
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

	public static class FireAttributesContext extends ParserRuleContext {
		public AttributeNameContext attributeName() {
			return getRuleContext(AttributeNameContext.class,0);
		}
		public AttributeTriggerContext attributeTrigger() {
			return getRuleContext(AttributeTriggerContext.class,0);
		}
		public AttributeActionContext attributeAction() {
			return getRuleContext(AttributeActionContext.class,0);
		}
		public FireAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fireAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterFireAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitFireAttributes(this);
		}
	}

	public final FireAttributesContext fireAttributes() throws RecognitionException {
		FireAttributesContext _localctx = new FireAttributesContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_fireAttributes);
		try {
			setState(228);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(225); attributeName();
				}
				break;
			case STRING_TRIGGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(226); attributeTrigger();
				}
				break;
			case STRING_ACTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(227); attributeAction();
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

	public static class KeydownAttributesContext extends ParserRuleContext {
		public AttributeKeyContext attributeKey() {
			return getRuleContext(AttributeKeyContext.class,0);
		}
		public AttributeActionContext attributeAction() {
			return getRuleContext(AttributeActionContext.class,0);
		}
		public KeydownAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keydownAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterKeydownAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitKeydownAttributes(this);
		}
	}

	public final KeydownAttributesContext keydownAttributes() throws RecognitionException {
		KeydownAttributesContext _localctx = new KeydownAttributesContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_keydownAttributes);
		try {
			setState(232);
			switch (_input.LA(1)) {
			case STRING_KEY:
				enterOuterAlt(_localctx, 1);
				{
				setState(230); attributeKey();
				}
				break;
			case STRING_ACTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(231); attributeAction();
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

	public static class KeyupAttributesContext extends ParserRuleContext {
		public AttributeKeyContext attributeKey() {
			return getRuleContext(AttributeKeyContext.class,0);
		}
		public AttributeActionContext attributeAction() {
			return getRuleContext(AttributeActionContext.class,0);
		}
		public KeyupAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyupAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterKeyupAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitKeyupAttributes(this);
		}
	}

	public final KeyupAttributesContext keyupAttributes() throws RecognitionException {
		KeyupAttributesContext _localctx = new KeyupAttributesContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_keyupAttributes);
		try {
			setState(236);
			switch (_input.LA(1)) {
			case STRING_KEY:
				enterOuterAlt(_localctx, 1);
				{
				setState(234); attributeKey();
				}
				break;
			case STRING_ACTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(235); attributeAction();
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

	public static class PortalAttributesContext extends ParserRuleContext {
		public AttributeNameContext attributeName() {
			return getRuleContext(AttributeNameContext.class,0);
		}
		public AttributeOtherboardContext attributeOtherboard() {
			return getRuleContext(AttributeOtherboardContext.class,0);
		}
		public AttributeXContext attributeX() {
			return getRuleContext(AttributeXContext.class,0);
		}
		public AttributeYContext attributeY() {
			return getRuleContext(AttributeYContext.class,0);
		}
		public AttributeOtherportalContext attributeOtherportal() {
			return getRuleContext(AttributeOtherportalContext.class,0);
		}
		public PortalAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portalAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterPortalAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitPortalAttributes(this);
		}
	}

	public final PortalAttributesContext portalAttributes() throws RecognitionException {
		PortalAttributesContext _localctx = new PortalAttributesContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_portalAttributes);
		try {
			setState(243);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(238); attributeName();
				}
				break;
			case 1:
				enterOuterAlt(_localctx, 2);
				{
				setState(239); attributeX();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(240); attributeY();
				}
				break;
			case STRING_OTHERBOARD:
				enterOuterAlt(_localctx, 4);
				{
				setState(241); attributeOtherboard();
				}
				break;
			case STRING_OTHERPORTAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(242); attributeOtherportal();
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

	public static class AttributeNameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public TerminalNode STRING_NAME() { return getToken(BoardParser.STRING_NAME, 0); }
		public AttributeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttributeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttributeName(this);
		}
	}

	public final AttributeNameContext attributeName() throws RecognitionException {
		AttributeNameContext _localctx = new AttributeNameContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_attributeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245); match(STRING_NAME);
			setState(246); match(EQUALS);
			setState(247); match(NAME);
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

	public static class AttributeGravityContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public TerminalNode STRING_GRAVITY() { return getToken(BoardParser.STRING_GRAVITY, 0); }
		public TerminalNode NUMBER() { return getToken(BoardParser.NUMBER, 0); }
		public AttributeGravityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeGravity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttributeGravity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttributeGravity(this);
		}
	}

	public final AttributeGravityContext attributeGravity() throws RecognitionException {
		AttributeGravityContext _localctx = new AttributeGravityContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_attributeGravity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249); match(STRING_GRAVITY);
			setState(250); match(EQUALS);
			setState(251); match(NUMBER);
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

	public static class AttributeFriction1Context extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public TerminalNode STRING_FRICTION1() { return getToken(BoardParser.STRING_FRICTION1, 0); }
		public TerminalNode NUMBER() { return getToken(BoardParser.NUMBER, 0); }
		public AttributeFriction1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeFriction1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttributeFriction1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttributeFriction1(this);
		}
	}

	public final AttributeFriction1Context attributeFriction1() throws RecognitionException {
		AttributeFriction1Context _localctx = new AttributeFriction1Context(_ctx, getState());
		enterRule(_localctx, 56, RULE_attributeFriction1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253); match(STRING_FRICTION1);
			setState(254); match(EQUALS);
			setState(255); match(NUMBER);
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

	public static class AttributeFriction2Context extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public TerminalNode STRING_FRICTION2() { return getToken(BoardParser.STRING_FRICTION2, 0); }
		public TerminalNode NUMBER() { return getToken(BoardParser.NUMBER, 0); }
		public AttributeFriction2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeFriction2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttributeFriction2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttributeFriction2(this);
		}
	}

	public final AttributeFriction2Context attributeFriction2() throws RecognitionException {
		AttributeFriction2Context _localctx = new AttributeFriction2Context(_ctx, getState());
		enterRule(_localctx, 58, RULE_attributeFriction2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257); match(STRING_FRICTION2);
			setState(258); match(EQUALS);
			setState(259); match(NUMBER);
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

	public static class AttributeXContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public TerminalNode NUMBER() { return getToken(BoardParser.NUMBER, 0); }
		public AttributeXContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeX; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttributeX(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttributeX(this);
		}
	}

	public final AttributeXContext attributeX() throws RecognitionException {
		AttributeXContext _localctx = new AttributeXContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_attributeX);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261); match(1);
			setState(262); match(EQUALS);
			setState(263); match(NUMBER);
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

	public static class AttributeYContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public TerminalNode NUMBER() { return getToken(BoardParser.NUMBER, 0); }
		public AttributeYContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeY; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttributeY(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttributeY(this);
		}
	}

	public final AttributeYContext attributeY() throws RecognitionException {
		AttributeYContext _localctx = new AttributeYContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_attributeY);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265); match(2);
			setState(266); match(EQUALS);
			setState(267); match(NUMBER);
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

	public static class AttributeXVelocityContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public TerminalNode STRING_XVELOCITY() { return getToken(BoardParser.STRING_XVELOCITY, 0); }
		public TerminalNode NUMBER() { return getToken(BoardParser.NUMBER, 0); }
		public AttributeXVelocityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeXVelocity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttributeXVelocity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttributeXVelocity(this);
		}
	}

	public final AttributeXVelocityContext attributeXVelocity() throws RecognitionException {
		AttributeXVelocityContext _localctx = new AttributeXVelocityContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_attributeXVelocity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269); match(STRING_XVELOCITY);
			setState(270); match(EQUALS);
			setState(271); match(NUMBER);
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

	public static class AttributeYVelocityContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public TerminalNode STRING_YVELOCITY() { return getToken(BoardParser.STRING_YVELOCITY, 0); }
		public TerminalNode NUMBER() { return getToken(BoardParser.NUMBER, 0); }
		public AttributeYVelocityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeYVelocity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttributeYVelocity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttributeYVelocity(this);
		}
	}

	public final AttributeYVelocityContext attributeYVelocity() throws RecognitionException {
		AttributeYVelocityContext _localctx = new AttributeYVelocityContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_attributeYVelocity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273); match(STRING_YVELOCITY);
			setState(274); match(EQUALS);
			setState(275); match(NUMBER);
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

	public static class AttributeOrientationContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public TerminalNode STRING_ORIENTATION() { return getToken(BoardParser.STRING_ORIENTATION, 0); }
		public TerminalNode NUMBER() { return getToken(BoardParser.NUMBER, 0); }
		public AttributeOrientationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeOrientation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttributeOrientation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttributeOrientation(this);
		}
	}

	public final AttributeOrientationContext attributeOrientation() throws RecognitionException {
		AttributeOrientationContext _localctx = new AttributeOrientationContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_attributeOrientation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277); match(STRING_ORIENTATION);
			setState(278); match(EQUALS);
			setState(279); match(NUMBER);
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

	public static class AttributeTriggerContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public TerminalNode STRING_TRIGGER() { return getToken(BoardParser.STRING_TRIGGER, 0); }
		public AttributeTriggerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeTrigger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttributeTrigger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttributeTrigger(this);
		}
	}

	public final AttributeTriggerContext attributeTrigger() throws RecognitionException {
		AttributeTriggerContext _localctx = new AttributeTriggerContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_attributeTrigger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281); match(STRING_TRIGGER);
			setState(282); match(EQUALS);
			setState(283); match(NAME);
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

	public static class AttributeActionContext extends ParserRuleContext {
		public TerminalNode STRING_ACTION() { return getToken(BoardParser.STRING_ACTION, 0); }
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public AttributeActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttributeAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttributeAction(this);
		}
	}

	public final AttributeActionContext attributeAction() throws RecognitionException {
		AttributeActionContext _localctx = new AttributeActionContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_attributeAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285); match(STRING_ACTION);
			setState(286); match(EQUALS);
			setState(287); match(NAME);
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

	public static class AttributeWidthContext extends ParserRuleContext {
		public TerminalNode STRING_WIDTH() { return getToken(BoardParser.STRING_WIDTH, 0); }
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public TerminalNode NUMBER() { return getToken(BoardParser.NUMBER, 0); }
		public AttributeWidthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeWidth; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttributeWidth(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttributeWidth(this);
		}
	}

	public final AttributeWidthContext attributeWidth() throws RecognitionException {
		AttributeWidthContext _localctx = new AttributeWidthContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_attributeWidth);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289); match(STRING_WIDTH);
			setState(290); match(EQUALS);
			setState(291); match(NUMBER);
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

	public static class AttributeHeightContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public TerminalNode NUMBER() { return getToken(BoardParser.NUMBER, 0); }
		public TerminalNode STRING_HEIGHT() { return getToken(BoardParser.STRING_HEIGHT, 0); }
		public AttributeHeightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeHeight; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttributeHeight(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttributeHeight(this);
		}
	}

	public final AttributeHeightContext attributeHeight() throws RecognitionException {
		AttributeHeightContext _localctx = new AttributeHeightContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_attributeHeight);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293); match(STRING_HEIGHT);
			setState(294); match(EQUALS);
			setState(295); match(NUMBER);
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

	public static class AttributeKeyContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public TerminalNode STRING_KEY() { return getToken(BoardParser.STRING_KEY, 0); }
		public AttributeKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeKey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttributeKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttributeKey(this);
		}
	}

	public final AttributeKeyContext attributeKey() throws RecognitionException {
		AttributeKeyContext _localctx = new AttributeKeyContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_attributeKey);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297); match(STRING_KEY);
			setState(298); match(EQUALS);
			setState(299); match(NAME);
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

	public static class AttributeOtherboardContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public TerminalNode STRING_OTHERBOARD() { return getToken(BoardParser.STRING_OTHERBOARD, 0); }
		public AttributeOtherboardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeOtherboard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttributeOtherboard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttributeOtherboard(this);
		}
	}

	public final AttributeOtherboardContext attributeOtherboard() throws RecognitionException {
		AttributeOtherboardContext _localctx = new AttributeOtherboardContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_attributeOtherboard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301); match(STRING_OTHERBOARD);
			setState(302); match(EQUALS);
			setState(303); match(NAME);
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

	public static class AttributeOtherportalContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public TerminalNode STRING_OTHERPORTAL() { return getToken(BoardParser.STRING_OTHERPORTAL, 0); }
		public AttributeOtherportalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeOtherportal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttributeOtherportal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttributeOtherportal(this);
		}
	}

	public final AttributeOtherportalContext attributeOtherportal() throws RecognitionException {
		AttributeOtherportalContext _localctx = new AttributeOtherportalContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_attributeOtherportal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305); match(STRING_OTHERPORTAL);
			setState(306); match(EQUALS);
			setState(307); match(NAME);
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

	public static final String _serializedATN =
		"\2\3#\u0138\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20"+
		"\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27"+
		"\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36"+
		"\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4"+
		")\t)\4*\t*\4+\t+\3\2\6\2X\n\2\r\2\16\2Y\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3j\n\3\3\4\3\4\6\4n\n\4\r\4\16\4o\3\5\3\5"+
		"\6\5t\n\5\r\5\16\5u\3\6\3\6\6\6z\n\6\r\6\16\6{\3\7\3\7\6\7\u0080\n\7\r"+
		"\7\16\7\u0081\3\b\3\b\6\b\u0086\n\b\r\b\16\b\u0087\3\t\3\t\6\t\u008c\n"+
		"\t\r\t\16\t\u008d\3\n\3\n\6\n\u0092\n\n\r\n\16\n\u0093\3\13\3\13\6\13"+
		"\u0098\n\13\r\13\16\13\u0099\3\f\3\f\6\f\u009e\n\f\r\f\16\f\u009f\3\r"+
		"\3\r\6\r\u00a4\n\r\r\r\16\r\u00a5\3\16\3\16\6\16\u00aa\n\16\r\16\16\16"+
		"\u00ab\3\17\3\17\6\17\u00b0\n\17\r\17\16\17\u00b1\3\20\3\20\3\20\3\20"+
		"\5\20\u00b8\n\20\3\21\3\21\3\21\3\21\3\21\5\21\u00bf\n\21\3\22\3\22\3"+
		"\22\5\22\u00c4\n\22\3\23\3\23\3\23\3\23\5\23\u00ca\n\23\3\24\3\24\3\24"+
		"\5\24\u00cf\n\24\3\25\3\25\3\25\3\25\5\25\u00d5\n\25\3\26\3\26\3\26\3"+
		"\26\5\26\u00db\n\26\3\27\3\27\3\27\3\27\3\27\5\27\u00e2\n\27\3\30\3\30"+
		"\3\30\5\30\u00e7\n\30\3\31\3\31\5\31\u00eb\n\31\3\32\3\32\5\32\u00ef\n"+
		"\32\3\33\3\33\3\33\3\33\3\33\5\33\u00f6\n\33\3\34\3\34\3\34\3\34\3\35"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3"+
		"!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3&\3&\3"+
		"&\3&\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3)\3)\3)\3)\3*\3*\3*\3*\3+\3+\3+\3+\3"+
		"+\2,\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@"+
		"BDFHJLNPRT\2\2\u0145\2W\3\2\2\2\4i\3\2\2\2\6k\3\2\2\2\bq\3\2\2\2\nw\3"+
		"\2\2\2\f}\3\2\2\2\16\u0083\3\2\2\2\20\u0089\3\2\2\2\22\u008f\3\2\2\2\24"+
		"\u0095\3\2\2\2\26\u009b\3\2\2\2\30\u00a1\3\2\2\2\32\u00a7\3\2\2\2\34\u00ad"+
		"\3\2\2\2\36\u00b7\3\2\2\2 \u00be\3\2\2\2\"\u00c3\3\2\2\2$\u00c9\3\2\2"+
		"\2&\u00ce\3\2\2\2(\u00d4\3\2\2\2*\u00da\3\2\2\2,\u00e1\3\2\2\2.\u00e6"+
		"\3\2\2\2\60\u00ea\3\2\2\2\62\u00ee\3\2\2\2\64\u00f5\3\2\2\2\66\u00f7\3"+
		"\2\2\28\u00fb\3\2\2\2:\u00ff\3\2\2\2<\u0103\3\2\2\2>\u0107\3\2\2\2@\u010b"+
		"\3\2\2\2B\u010f\3\2\2\2D\u0113\3\2\2\2F\u0117\3\2\2\2H\u011b\3\2\2\2J"+
		"\u011f\3\2\2\2L\u0123\3\2\2\2N\u0127\3\2\2\2P\u012b\3\2\2\2R\u012f\3\2"+
		"\2\2T\u0133\3\2\2\2VX\5\4\3\2WV\3\2\2\2XY\3\2\2\2YW\3\2\2\2YZ\3\2\2\2"+
		"Z[\3\2\2\2[\\\7\1\2\2\\\3\3\2\2\2]j\5\6\4\2^j\5\b\5\2_j\5\n\6\2`j\5\f"+
		"\7\2aj\5\16\b\2bj\5\20\t\2cj\5\22\n\2dj\5\24\13\2ej\5\26\f\2fj\5\32\16"+
		"\2gj\5\30\r\2hj\5\34\17\2i]\3\2\2\2i^\3\2\2\2i_\3\2\2\2i`\3\2\2\2ia\3"+
		"\2\2\2ib\3\2\2\2ic\3\2\2\2id\3\2\2\2ie\3\2\2\2if\3\2\2\2ig\3\2\2\2ih\3"+
		"\2\2\2j\5\3\2\2\2km\7\7\2\2ln\5\36\20\2ml\3\2\2\2no\3\2\2\2om\3\2\2\2"+
		"op\3\2\2\2p\7\3\2\2\2qs\7\b\2\2rt\5 \21\2sr\3\2\2\2tu\3\2\2\2us\3\2\2"+
		"\2uv\3\2\2\2v\t\3\2\2\2wy\7\t\2\2xz\5\"\22\2yx\3\2\2\2z{\3\2\2\2{y\3\2"+
		"\2\2{|\3\2\2\2|\13\3\2\2\2}\177\7\n\2\2~\u0080\5$\23\2\177~\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\r\3\2\2\2\u0083"+
		"\u0085\7\13\2\2\u0084\u0086\5&\24\2\u0085\u0084\3\2\2\2\u0086\u0087\3"+
		"\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\17\3\2\2\2\u0089"+
		"\u008b\7\f\2\2\u008a\u008c\5(\25\2\u008b\u008a\3\2\2\2\u008c\u008d\3\2"+
		"\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\21\3\2\2\2\u008f\u0091"+
		"\7\r\2\2\u0090\u0092\5*\26\2\u0091\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093"+
		"\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\23\3\2\2\2\u0095\u0097\7\16\2"+
		"\2\u0096\u0098\5,\27\2\u0097\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u0097"+
		"\3\2\2\2\u0099\u009a\3\2\2\2\u009a\25\3\2\2\2\u009b\u009d\7\20\2\2\u009c"+
		"\u009e\5.\30\2\u009d\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u009d\3\2"+
		"\2\2\u009f\u00a0\3\2\2\2\u00a0\27\3\2\2\2\u00a1\u00a3\7\21\2\2\u00a2\u00a4"+
		"\5\60\31\2\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a3\3\2\2\2"+
		"\u00a5\u00a6\3\2\2\2\u00a6\31\3\2\2\2\u00a7\u00a9\7\22\2\2\u00a8\u00aa"+
		"\5\62\32\2\u00a9\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00a9\3\2\2\2"+
		"\u00ab\u00ac\3\2\2\2\u00ac\33\3\2\2\2\u00ad\u00af\7\17\2\2\u00ae\u00b0"+
		"\5\64\33\2\u00af\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00af\3\2\2\2"+
		"\u00b1\u00b2\3\2\2\2\u00b2\35\3\2\2\2\u00b3\u00b8\5\66\34\2\u00b4\u00b8"+
		"\58\35\2\u00b5\u00b8\5:\36\2\u00b6\u00b8\5<\37\2\u00b7\u00b3\3\2\2\2\u00b7"+
		"\u00b4\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b6\3\2\2\2\u00b8\37\3\2\2"+
		"\2\u00b9\u00bf\5\66\34\2\u00ba\u00bf\5> \2\u00bb\u00bf\5@!\2\u00bc\u00bf"+
		"\5B\"\2\u00bd\u00bf\5D#\2\u00be\u00b9\3\2\2\2\u00be\u00ba\3\2\2\2\u00be"+
		"\u00bb\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bd\3\2\2\2\u00bf!\3\2\2\2"+
		"\u00c0\u00c4\5\66\34\2\u00c1\u00c4\5> \2\u00c2\u00c4\5@!\2\u00c3\u00c0"+
		"\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c2\3\2\2\2\u00c4#\3\2\2\2\u00c5"+
		"\u00ca\5\66\34\2\u00c6\u00ca\5> \2\u00c7\u00ca\5@!\2\u00c8\u00ca\5F$\2"+
		"\u00c9\u00c5\3\2\2\2\u00c9\u00c6\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00c8"+
		"\3\2\2\2\u00ca%\3\2\2\2\u00cb\u00cf\5\66\34\2\u00cc\u00cf\5> \2\u00cd"+
		"\u00cf\5@!\2\u00ce\u00cb\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cd\3\2\2"+
		"\2\u00cf\'\3\2\2\2\u00d0\u00d5\5\66\34\2\u00d1\u00d5\5> \2\u00d2\u00d5"+
		"\5@!\2\u00d3\u00d5\5F$\2\u00d4\u00d0\3\2\2\2\u00d4\u00d1\3\2\2\2\u00d4"+
		"\u00d2\3\2\2\2\u00d4\u00d3\3\2\2\2\u00d5)\3\2\2\2\u00d6\u00db\5\66\34"+
		"\2\u00d7\u00db\5> \2\u00d8\u00db\5@!\2\u00d9\u00db\5F$\2\u00da\u00d6\3"+
		"\2\2\2\u00da\u00d7\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00d9\3\2\2\2\u00db"+
		"+\3\2\2\2\u00dc\u00e2\5\66\34\2\u00dd\u00e2\5> \2\u00de\u00e2\5@!\2\u00df"+
		"\u00e2\5L\'\2\u00e0\u00e2\5N(\2\u00e1\u00dc\3\2\2\2\u00e1\u00dd\3\2\2"+
		"\2\u00e1\u00de\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e0\3\2\2\2\u00e2-"+
		"\3\2\2\2\u00e3\u00e7\5\66\34\2\u00e4\u00e7\5H%\2\u00e5\u00e7\5J&\2\u00e6"+
		"\u00e3\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e5\3\2\2\2\u00e7/\3\2\2\2"+
		"\u00e8\u00eb\5P)\2\u00e9\u00eb\5J&\2\u00ea\u00e8\3\2\2\2\u00ea\u00e9\3"+
		"\2\2\2\u00eb\61\3\2\2\2\u00ec\u00ef\5P)\2\u00ed\u00ef\5J&\2\u00ee\u00ec"+
		"\3\2\2\2\u00ee\u00ed\3\2\2\2\u00ef\63\3\2\2\2\u00f0\u00f6\5\66\34\2\u00f1"+
		"\u00f6\5> \2\u00f2\u00f6\5@!\2\u00f3\u00f6\5R*\2\u00f4\u00f6\5T+\2\u00f5"+
		"\u00f0\3\2\2\2\u00f5\u00f1\3\2\2\2\u00f5\u00f2\3\2\2\2\u00f5\u00f3\3\2"+
		"\2\2\u00f5\u00f4\3\2\2\2\u00f6\65\3\2\2\2\u00f7\u00f8\7\23\2\2\u00f8\u00f9"+
		"\7!\2\2\u00f9\u00fa\7#\2\2\u00fa\67\3\2\2\2\u00fb\u00fc\7\24\2\2\u00fc"+
		"\u00fd\7!\2\2\u00fd\u00fe\7\"\2\2\u00fe9\3\2\2\2\u00ff\u0100\7\25\2\2"+
		"\u0100\u0101\7!\2\2\u0101\u0102\7\"\2\2\u0102;\3\2\2\2\u0103\u0104\7\26"+
		"\2\2\u0104\u0105\7!\2\2\u0105\u0106\7\"\2\2\u0106=\3\2\2\2\u0107\u0108"+
		"\7\3\2\2\u0108\u0109\7!\2\2\u0109\u010a\7\"\2\2\u010a?\3\2\2\2\u010b\u010c"+
		"\7\4\2\2\u010c\u010d\7!\2\2\u010d\u010e\7\"\2\2\u010eA\3\2\2\2\u010f\u0110"+
		"\7\34\2\2\u0110\u0111\7!\2\2\u0111\u0112\7\"\2\2\u0112C\3\2\2\2\u0113"+
		"\u0114\7\35\2\2\u0114\u0115\7!\2\2\u0115\u0116\7\"\2\2\u0116E\3\2\2\2"+
		"\u0117\u0118\7\33\2\2\u0118\u0119\7!\2\2\u0119\u011a\7\"\2\2\u011aG\3"+
		"\2\2\2\u011b\u011c\7\31\2\2\u011c\u011d\7!\2\2\u011d\u011e\7#\2\2\u011e"+
		"I\3\2\2\2\u011f\u0120\7\32\2\2\u0120\u0121\7!\2\2\u0121\u0122\7#\2\2\u0122"+
		"K\3\2\2\2\u0123\u0124\7\27\2\2\u0124\u0125\7!\2\2\u0125\u0126\7\"\2\2"+
		"\u0126M\3\2\2\2\u0127\u0128\7\30\2\2\u0128\u0129\7!\2\2\u0129\u012a\7"+
		"\"\2\2\u012aO\3\2\2\2\u012b\u012c\7\36\2\2\u012c\u012d\7!\2\2\u012d\u012e"+
		"\7#\2\2\u012eQ\3\2\2\2\u012f\u0130\7\37\2\2\u0130\u0131\7!\2\2\u0131\u0132"+
		"\7#\2\2\u0132S\3\2\2\2\u0133\u0134\7 \2\2\u0134\u0135\7!\2\2\u0135\u0136"+
		"\7#\2\2\u0136U\3\2\2\2\34Yiou{\u0081\u0087\u008d\u0093\u0099\u009f\u00a5"+
		"\u00ab\u00b1\u00b7\u00be\u00c3\u00c9\u00ce\u00d4\u00da\u00e1\u00e6\u00ea"+
		"\u00ee\u00f5";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}