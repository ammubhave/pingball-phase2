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
		COMMENT=1, WHITESPACE=2, STRING_BOARD=3, STRING_BALL=4, STRING_SQUAREBUMPER=5, 
		STRING_TRIANGLEBUMPER=6, STRING_CIRCLEBUMPER=7, STRING_LEFTFLIPPER=8, 
		STRING_RIGHTFLIPPER=9, STRING_ABSORBER=10, STRING_FIRE=11, STRING_NAME=12, 
		STRING_GRAVITY=13, STRING_FRICTION1=14, STRING_FRICTION2=15, STRING_WIDTH=16, 
		STRING_HEIGHT=17, STRING_TRIGGER=18, STRING_ACTION=19, STRING_ORIENTATION=20, 
		STRING_XVELOCITY=21, STRING_YVELOCITY=22, STRING_X=23, STRING_Y=24, EQUALS=25, 
		NUMBER=26, NAME=27;
	public static final String[] tokenNames = {
		"<INVALID>", "COMMENT", "WHITESPACE", "'board'", "'ball'", "'squareBumper'", 
		"'triangleBumper'", "'circleBumper'", "'leftFlipper'", "'rightFlipper'", 
		"'absorber'", "'fire'", "'name'", "'gravity'", "'friction1'", "'friction2'", 
		"'width'", "'height'", "'trigger'", "'action'", "'orientation'", "'xVelocity'", 
		"'yVelocity'", "'x'", "'y'", "'='", "NUMBER", "NAME"
	};
	public static final int
		RULE_board = 0, RULE_objectLine = 1, RULE_boardObjectLine = 2, RULE_ballObjectLine = 3, 
		RULE_squareBumperObjectLine = 4, RULE_triangleBumperObjectLine = 5, RULE_circleBumperObjectLine = 6, 
		RULE_leftFlipperObjectLine = 7, RULE_rightFlipperObjectLine = 8, RULE_absorberObjectLine = 9, 
		RULE_fireObjectLine = 10, RULE_boardAttributes = 11, RULE_ballAttributes = 12, 
		RULE_squareBumperAttributes = 13, RULE_triangleBumperAttributes = 14, 
		RULE_circleBumperAttributes = 15, RULE_leftFlipperAttributes = 16, RULE_rightFlipperAttributes = 17, 
		RULE_absorberAttributes = 18, RULE_fireAttributes = 19, RULE_attributeName = 20, 
		RULE_attributeGravity = 21, RULE_attributeFriction1 = 22, RULE_attributeFriction2 = 23, 
		RULE_attributeX = 24, RULE_attributeY = 25, RULE_attributeXVelocity = 26, 
		RULE_attributeYVelocity = 27, RULE_attributeOrientation = 28, RULE_attributeTrigger = 29, 
		RULE_attributeAction = 30, RULE_attributeWidth = 31, RULE_attributeHeight = 32;
	public static final String[] ruleNames = {
		"board", "objectLine", "boardObjectLine", "ballObjectLine", "squareBumperObjectLine", 
		"triangleBumperObjectLine", "circleBumperObjectLine", "leftFlipperObjectLine", 
		"rightFlipperObjectLine", "absorberObjectLine", "fireObjectLine", "boardAttributes", 
		"ballAttributes", "squareBumperAttributes", "triangleBumperAttributes", 
		"circleBumperAttributes", "leftFlipperAttributes", "rightFlipperAttributes", 
		"absorberAttributes", "fireAttributes", "attributeName", "attributeGravity", 
		"attributeFriction1", "attributeFriction2", "attributeX", "attributeY", 
		"attributeXVelocity", "attributeYVelocity", "attributeOrientation", "attributeTrigger", 
		"attributeAction", "attributeWidth", "attributeHeight"
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
			setState(67); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(66); objectLine();
				}
				}
				setState(69); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING_BOARD) | (1L << STRING_BALL) | (1L << STRING_SQUAREBUMPER) | (1L << STRING_TRIANGLEBUMPER) | (1L << STRING_CIRCLEBUMPER) | (1L << STRING_LEFTFLIPPER) | (1L << STRING_RIGHTFLIPPER) | (1L << STRING_ABSORBER) | (1L << STRING_FIRE))) != 0) );
			setState(71); match(EOF);
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
		public BallObjectLineContext ballObjectLine() {
			return getRuleContext(BallObjectLineContext.class,0);
		}
		public BoardObjectLineContext boardObjectLine() {
			return getRuleContext(BoardObjectLineContext.class,0);
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
			setState(82);
			switch (_input.LA(1)) {
			case STRING_BOARD:
				enterOuterAlt(_localctx, 1);
				{
				setState(73); boardObjectLine();
				}
				break;
			case STRING_BALL:
				enterOuterAlt(_localctx, 2);
				{
				setState(74); ballObjectLine();
				}
				break;
			case STRING_SQUAREBUMPER:
				enterOuterAlt(_localctx, 3);
				{
				setState(75); squareBumperObjectLine();
				}
				break;
			case STRING_TRIANGLEBUMPER:
				enterOuterAlt(_localctx, 4);
				{
				setState(76); triangleBumperObjectLine();
				}
				break;
			case STRING_CIRCLEBUMPER:
				enterOuterAlt(_localctx, 5);
				{
				setState(77); circleBumperObjectLine();
				}
				break;
			case STRING_LEFTFLIPPER:
				enterOuterAlt(_localctx, 6);
				{
				setState(78); leftFlipperObjectLine();
				}
				break;
			case STRING_RIGHTFLIPPER:
				enterOuterAlt(_localctx, 7);
				{
				setState(79); rightFlipperObjectLine();
				}
				break;
			case STRING_ABSORBER:
				enterOuterAlt(_localctx, 8);
				{
				setState(80); absorberObjectLine();
				}
				break;
			case STRING_FIRE:
				enterOuterAlt(_localctx, 9);
				{
				setState(81); fireObjectLine();
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
			setState(84); match(STRING_BOARD);
			setState(86); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(85); boardAttributes();
				}
				}
				setState(88); 
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
			setState(90); match(STRING_BALL);
			setState(92); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(91); ballAttributes();
				}
				}
				setState(94); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING_NAME) | (1L << STRING_XVELOCITY) | (1L << STRING_YVELOCITY) | (1L << STRING_X) | (1L << STRING_Y))) != 0) );
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
			setState(96); match(STRING_SQUAREBUMPER);
			setState(98); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(97); squareBumperAttributes();
				}
				}
				setState(100); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING_NAME) | (1L << STRING_X) | (1L << STRING_Y))) != 0) );
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
			setState(102); match(STRING_TRIANGLEBUMPER);
			setState(104); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(103); triangleBumperAttributes();
				}
				}
				setState(106); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING_NAME) | (1L << STRING_ORIENTATION) | (1L << STRING_X) | (1L << STRING_Y))) != 0) );
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
			setState(108); match(STRING_CIRCLEBUMPER);
			setState(110); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(109); circleBumperAttributes();
				}
				}
				setState(112); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING_NAME) | (1L << STRING_X) | (1L << STRING_Y))) != 0) );
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
			setState(114); match(STRING_LEFTFLIPPER);
			setState(116); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(115); leftFlipperAttributes();
				}
				}
				setState(118); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING_NAME) | (1L << STRING_ORIENTATION) | (1L << STRING_X) | (1L << STRING_Y))) != 0) );
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
			setState(120); match(STRING_RIGHTFLIPPER);
			setState(122); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(121); rightFlipperAttributes();
				}
				}
				setState(124); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING_NAME) | (1L << STRING_ORIENTATION) | (1L << STRING_X) | (1L << STRING_Y))) != 0) );
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
			setState(126); match(STRING_ABSORBER);
			setState(128); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(127); absorberAttributes();
				}
				}
				setState(130); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING_NAME) | (1L << STRING_WIDTH) | (1L << STRING_HEIGHT) | (1L << STRING_X) | (1L << STRING_Y))) != 0) );
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
			setState(132); match(STRING_FIRE);
			setState(134); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(133); fireAttributes();
				}
				}
				setState(136); 
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
		enterRule(_localctx, 22, RULE_boardAttributes);
		try {
			setState(142);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(138); attributeName();
				}
				break;
			case STRING_GRAVITY:
				enterOuterAlt(_localctx, 2);
				{
				setState(139); attributeGravity();
				}
				break;
			case STRING_FRICTION1:
				enterOuterAlt(_localctx, 3);
				{
				setState(140); attributeFriction1();
				}
				break;
			case STRING_FRICTION2:
				enterOuterAlt(_localctx, 4);
				{
				setState(141); attributeFriction2();
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
		enterRule(_localctx, 24, RULE_ballAttributes);
		try {
			setState(149);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(144); attributeName();
				}
				break;
			case STRING_X:
				enterOuterAlt(_localctx, 2);
				{
				setState(145); attributeX();
				}
				break;
			case STRING_Y:
				enterOuterAlt(_localctx, 3);
				{
				setState(146); attributeY();
				}
				break;
			case STRING_XVELOCITY:
				enterOuterAlt(_localctx, 4);
				{
				setState(147); attributeXVelocity();
				}
				break;
			case STRING_YVELOCITY:
				enterOuterAlt(_localctx, 5);
				{
				setState(148); attributeYVelocity();
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
		enterRule(_localctx, 26, RULE_squareBumperAttributes);
		try {
			setState(154);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(151); attributeName();
				}
				break;
			case STRING_X:
				enterOuterAlt(_localctx, 2);
				{
				setState(152); attributeX();
				}
				break;
			case STRING_Y:
				enterOuterAlt(_localctx, 3);
				{
				setState(153); attributeY();
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
		enterRule(_localctx, 28, RULE_triangleBumperAttributes);
		try {
			setState(160);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(156); attributeName();
				}
				break;
			case STRING_X:
				enterOuterAlt(_localctx, 2);
				{
				setState(157); attributeX();
				}
				break;
			case STRING_Y:
				enterOuterAlt(_localctx, 3);
				{
				setState(158); attributeY();
				}
				break;
			case STRING_ORIENTATION:
				enterOuterAlt(_localctx, 4);
				{
				setState(159); attributeOrientation();
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
		enterRule(_localctx, 30, RULE_circleBumperAttributes);
		try {
			setState(165);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(162); attributeName();
				}
				break;
			case STRING_X:
				enterOuterAlt(_localctx, 2);
				{
				setState(163); attributeX();
				}
				break;
			case STRING_Y:
				enterOuterAlt(_localctx, 3);
				{
				setState(164); attributeY();
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
		enterRule(_localctx, 32, RULE_leftFlipperAttributes);
		try {
			setState(171);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(167); attributeName();
				}
				break;
			case STRING_X:
				enterOuterAlt(_localctx, 2);
				{
				setState(168); attributeX();
				}
				break;
			case STRING_Y:
				enterOuterAlt(_localctx, 3);
				{
				setState(169); attributeY();
				}
				break;
			case STRING_ORIENTATION:
				enterOuterAlt(_localctx, 4);
				{
				setState(170); attributeOrientation();
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
		enterRule(_localctx, 34, RULE_rightFlipperAttributes);
		try {
			setState(177);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(173); attributeName();
				}
				break;
			case STRING_X:
				enterOuterAlt(_localctx, 2);
				{
				setState(174); attributeX();
				}
				break;
			case STRING_Y:
				enterOuterAlt(_localctx, 3);
				{
				setState(175); attributeY();
				}
				break;
			case STRING_ORIENTATION:
				enterOuterAlt(_localctx, 4);
				{
				setState(176); attributeOrientation();
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
		enterRule(_localctx, 36, RULE_absorberAttributes);
		try {
			setState(184);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(179); attributeName();
				}
				break;
			case STRING_X:
				enterOuterAlt(_localctx, 2);
				{
				setState(180); attributeX();
				}
				break;
			case STRING_Y:
				enterOuterAlt(_localctx, 3);
				{
				setState(181); attributeY();
				}
				break;
			case STRING_WIDTH:
				enterOuterAlt(_localctx, 4);
				{
				setState(182); attributeWidth();
				}
				break;
			case STRING_HEIGHT:
				enterOuterAlt(_localctx, 5);
				{
				setState(183); attributeHeight();
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
		enterRule(_localctx, 38, RULE_fireAttributes);
		try {
			setState(189);
			switch (_input.LA(1)) {
			case STRING_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(186); attributeName();
				}
				break;
			case STRING_TRIGGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(187); attributeTrigger();
				}
				break;
			case STRING_ACTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(188); attributeAction();
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
		enterRule(_localctx, 40, RULE_attributeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191); match(STRING_NAME);
			setState(192); match(EQUALS);
			setState(193); match(NAME);
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
		enterRule(_localctx, 42, RULE_attributeGravity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195); match(STRING_GRAVITY);
			setState(196); match(EQUALS);
			setState(197); match(NUMBER);
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
		enterRule(_localctx, 44, RULE_attributeFriction1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199); match(STRING_FRICTION1);
			setState(200); match(EQUALS);
			setState(201); match(NUMBER);
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
		enterRule(_localctx, 46, RULE_attributeFriction2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203); match(STRING_FRICTION2);
			setState(204); match(EQUALS);
			setState(205); match(NUMBER);
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
		public TerminalNode STRING_X() { return getToken(BoardParser.STRING_X, 0); }
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
		enterRule(_localctx, 48, RULE_attributeX);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207); match(STRING_X);
			setState(208); match(EQUALS);
			setState(209); match(NUMBER);
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
		public TerminalNode STRING_Y() { return getToken(BoardParser.STRING_Y, 0); }
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
		enterRule(_localctx, 50, RULE_attributeY);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211); match(STRING_Y);
			setState(212); match(EQUALS);
			setState(213); match(NUMBER);
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
		enterRule(_localctx, 52, RULE_attributeXVelocity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215); match(STRING_XVELOCITY);
			setState(216); match(EQUALS);
			setState(217); match(NUMBER);
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
		enterRule(_localctx, 54, RULE_attributeYVelocity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219); match(STRING_YVELOCITY);
			setState(220); match(EQUALS);
			setState(221); match(NUMBER);
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
		enterRule(_localctx, 56, RULE_attributeOrientation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223); match(STRING_ORIENTATION);
			setState(224); match(EQUALS);
			setState(225); match(NUMBER);
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
		enterRule(_localctx, 58, RULE_attributeTrigger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227); match(STRING_TRIGGER);
			setState(228); match(EQUALS);
			setState(229); match(NAME);
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
		enterRule(_localctx, 60, RULE_attributeAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231); match(STRING_ACTION);
			setState(232); match(EQUALS);
			setState(233); match(NAME);
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
		enterRule(_localctx, 62, RULE_attributeWidth);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235); match(STRING_WIDTH);
			setState(236); match(EQUALS);
			setState(237); match(NUMBER);
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
		enterRule(_localctx, 64, RULE_attributeHeight);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239); match(STRING_HEIGHT);
			setState(240); match(EQUALS);
			setState(241); match(NUMBER);
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
		"\2\3\35\u00f6\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t"+
		"\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t"+
		"\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t"+
		"\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\3\2\6\2F\n\2\r\2\16\2G\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3U\n\3\3\4\3\4\6\4Y\n\4\r\4\16\4Z\3\5"+
		"\3\5\6\5_\n\5\r\5\16\5`\3\6\3\6\6\6e\n\6\r\6\16\6f\3\7\3\7\6\7k\n\7\r"+
		"\7\16\7l\3\b\3\b\6\bq\n\b\r\b\16\br\3\t\3\t\6\tw\n\t\r\t\16\tx\3\n\3\n"+
		"\6\n}\n\n\r\n\16\n~\3\13\3\13\6\13\u0083\n\13\r\13\16\13\u0084\3\f\3\f"+
		"\6\f\u0089\n\f\r\f\16\f\u008a\3\r\3\r\3\r\3\r\5\r\u0091\n\r\3\16\3\16"+
		"\3\16\3\16\3\16\5\16\u0098\n\16\3\17\3\17\3\17\5\17\u009d\n\17\3\20\3"+
		"\20\3\20\3\20\5\20\u00a3\n\20\3\21\3\21\3\21\5\21\u00a8\n\21\3\22\3\22"+
		"\3\22\3\22\5\22\u00ae\n\22\3\23\3\23\3\23\3\23\5\23\u00b4\n\23\3\24\3"+
		"\24\3\24\3\24\3\24\5\24\u00bb\n\24\3\25\3\25\3\25\5\25\u00c0\n\25\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3"+
		"!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\2#\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\62\64\668:<>@B\2\2\u0100\2E\3\2\2\2\4T\3\2\2\2\6V\3\2"+
		"\2\2\b\\\3\2\2\2\nb\3\2\2\2\fh\3\2\2\2\16n\3\2\2\2\20t\3\2\2\2\22z\3\2"+
		"\2\2\24\u0080\3\2\2\2\26\u0086\3\2\2\2\30\u0090\3\2\2\2\32\u0097\3\2\2"+
		"\2\34\u009c\3\2\2\2\36\u00a2\3\2\2\2 \u00a7\3\2\2\2\"\u00ad\3\2\2\2$\u00b3"+
		"\3\2\2\2&\u00ba\3\2\2\2(\u00bf\3\2\2\2*\u00c1\3\2\2\2,\u00c5\3\2\2\2."+
		"\u00c9\3\2\2\2\60\u00cd\3\2\2\2\62\u00d1\3\2\2\2\64\u00d5\3\2\2\2\66\u00d9"+
		"\3\2\2\28\u00dd\3\2\2\2:\u00e1\3\2\2\2<\u00e5\3\2\2\2>\u00e9\3\2\2\2@"+
		"\u00ed\3\2\2\2B\u00f1\3\2\2\2DF\5\4\3\2ED\3\2\2\2FG\3\2\2\2GE\3\2\2\2"+
		"GH\3\2\2\2HI\3\2\2\2IJ\7\1\2\2J\3\3\2\2\2KU\5\6\4\2LU\5\b\5\2MU\5\n\6"+
		"\2NU\5\f\7\2OU\5\16\b\2PU\5\20\t\2QU\5\22\n\2RU\5\24\13\2SU\5\26\f\2T"+
		"K\3\2\2\2TL\3\2\2\2TM\3\2\2\2TN\3\2\2\2TO\3\2\2\2TP\3\2\2\2TQ\3\2\2\2"+
		"TR\3\2\2\2TS\3\2\2\2U\5\3\2\2\2VX\7\5\2\2WY\5\30\r\2XW\3\2\2\2YZ\3\2\2"+
		"\2ZX\3\2\2\2Z[\3\2\2\2[\7\3\2\2\2\\^\7\6\2\2]_\5\32\16\2^]\3\2\2\2_`\3"+
		"\2\2\2`^\3\2\2\2`a\3\2\2\2a\t\3\2\2\2bd\7\7\2\2ce\5\34\17\2dc\3\2\2\2"+
		"ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2g\13\3\2\2\2hj\7\b\2\2ik\5\36\20\2ji\3\2"+
		"\2\2kl\3\2\2\2lj\3\2\2\2lm\3\2\2\2m\r\3\2\2\2np\7\t\2\2oq\5 \21\2po\3"+
		"\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2s\17\3\2\2\2tv\7\n\2\2uw\5\"\22\2"+
		"vu\3\2\2\2wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\21\3\2\2\2z|\7\13\2\2{}\5$\23"+
		"\2|{\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\23\3\2\2\2\u0080\u0082"+
		"\7\f\2\2\u0081\u0083\5&\24\2\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\25\3\2\2\2\u0086\u0088\7\r\2"+
		"\2\u0087\u0089\5(\25\2\u0088\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0088"+
		"\3\2\2\2\u008a\u008b\3\2\2\2\u008b\27\3\2\2\2\u008c\u0091\5*\26\2\u008d"+
		"\u0091\5,\27\2\u008e\u0091\5.\30\2\u008f\u0091\5\60\31\2\u0090\u008c\3"+
		"\2\2\2\u0090\u008d\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u008f\3\2\2\2\u0091"+
		"\31\3\2\2\2\u0092\u0098\5*\26\2\u0093\u0098\5\62\32\2\u0094\u0098\5\64"+
		"\33\2\u0095\u0098\5\66\34\2\u0096\u0098\58\35\2\u0097\u0092\3\2\2\2\u0097"+
		"\u0093\3\2\2\2\u0097\u0094\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0096\3\2"+
		"\2\2\u0098\33\3\2\2\2\u0099\u009d\5*\26\2\u009a\u009d\5\62\32\2\u009b"+
		"\u009d\5\64\33\2\u009c\u0099\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009b\3"+
		"\2\2\2\u009d\35\3\2\2\2\u009e\u00a3\5*\26\2\u009f\u00a3\5\62\32\2\u00a0"+
		"\u00a3\5\64\33\2\u00a1\u00a3\5:\36\2\u00a2\u009e\3\2\2\2\u00a2\u009f\3"+
		"\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a1\3\2\2\2\u00a3\37\3\2\2\2\u00a4"+
		"\u00a8\5*\26\2\u00a5\u00a8\5\62\32\2\u00a6\u00a8\5\64\33\2\u00a7\u00a4"+
		"\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a6\3\2\2\2\u00a8!\3\2\2\2\u00a9"+
		"\u00ae\5*\26\2\u00aa\u00ae\5\62\32\2\u00ab\u00ae\5\64\33\2\u00ac\u00ae"+
		"\5:\36\2\u00ad\u00a9\3\2\2\2\u00ad\u00aa\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad"+
		"\u00ac\3\2\2\2\u00ae#\3\2\2\2\u00af\u00b4\5*\26\2\u00b0\u00b4\5\62\32"+
		"\2\u00b1\u00b4\5\64\33\2\u00b2\u00b4\5:\36\2\u00b3\u00af\3\2\2\2\u00b3"+
		"\u00b0\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b2\3\2\2\2\u00b4%\3\2\2\2"+
		"\u00b5\u00bb\5*\26\2\u00b6\u00bb\5\62\32\2\u00b7\u00bb\5\64\33\2\u00b8"+
		"\u00bb\5@!\2\u00b9\u00bb\5B\"\2\u00ba\u00b5\3\2\2\2\u00ba\u00b6\3\2\2"+
		"\2\u00ba\u00b7\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00b9\3\2\2\2\u00bb\'"+
		"\3\2\2\2\u00bc\u00c0\5*\26\2\u00bd\u00c0\5<\37\2\u00be\u00c0\5> \2\u00bf"+
		"\u00bc\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00be\3\2\2\2\u00c0)\3\2\2\2"+
		"\u00c1\u00c2\7\16\2\2\u00c2\u00c3\7\33\2\2\u00c3\u00c4\7\35\2\2\u00c4"+
		"+\3\2\2\2\u00c5\u00c6\7\17\2\2\u00c6\u00c7\7\33\2\2\u00c7\u00c8\7\34\2"+
		"\2\u00c8-\3\2\2\2\u00c9\u00ca\7\20\2\2\u00ca\u00cb\7\33\2\2\u00cb\u00cc"+
		"\7\34\2\2\u00cc/\3\2\2\2\u00cd\u00ce\7\21\2\2\u00ce\u00cf\7\33\2\2\u00cf"+
		"\u00d0\7\34\2\2\u00d0\61\3\2\2\2\u00d1\u00d2\7\31\2\2\u00d2\u00d3\7\33"+
		"\2\2\u00d3\u00d4\7\34\2\2\u00d4\63\3\2\2\2\u00d5\u00d6\7\32\2\2\u00d6"+
		"\u00d7\7\33\2\2\u00d7\u00d8\7\34\2\2\u00d8\65\3\2\2\2\u00d9\u00da\7\27"+
		"\2\2\u00da\u00db\7\33\2\2\u00db\u00dc\7\34\2\2\u00dc\67\3\2\2\2\u00dd"+
		"\u00de\7\30\2\2\u00de\u00df\7\33\2\2\u00df\u00e0\7\34\2\2\u00e09\3\2\2"+
		"\2\u00e1\u00e2\7\26\2\2\u00e2\u00e3\7\33\2\2\u00e3\u00e4\7\34\2\2\u00e4"+
		";\3\2\2\2\u00e5\u00e6\7\24\2\2\u00e6\u00e7\7\33\2\2\u00e7\u00e8\7\35\2"+
		"\2\u00e8=\3\2\2\2\u00e9\u00ea\7\25\2\2\u00ea\u00eb\7\33\2\2\u00eb\u00ec"+
		"\7\35\2\2\u00ec?\3\2\2\2\u00ed\u00ee\7\22\2\2\u00ee\u00ef\7\33\2\2\u00ef"+
		"\u00f0\7\34\2\2\u00f0A\3\2\2\2\u00f1\u00f2\7\23\2\2\u00f2\u00f3\7\33\2"+
		"\2\u00f3\u00f4\7\34\2\2\u00f4C\3\2\2\2\26GTZ`flrx~\u0084\u008a\u0090\u0097"+
		"\u009c\u00a2\u00a7\u00ad\u00b3\u00ba\u00bf";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}