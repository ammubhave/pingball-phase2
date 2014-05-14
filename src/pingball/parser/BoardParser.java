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
		T__11=1, T__10=2, T__9=3, T__8=4, T__7=5, T__6=6, T__5=7, T__4=8, T__3=9, 
		T__2=10, T__1=11, T__0=12, COMMENT=13, WHITESPACE=14, EQUALS=15, NUMBER=16, 
		NAME=17;
	public static final String[] tokenNames = {
		"<INVALID>", "'triangleBumper'", "'fire'", "'keyup'", "'board'", "'ball'", 
		"'absorber'", "'portal'", "'squareBumper'", "'leftFlipper'", "'circleBumper'", 
		"'keydown'", "'rightFlipper'", "COMMENT", "WHITESPACE", "'='", "NUMBER", 
		"NAME"
	};
	public static final int
		RULE_board = 0, RULE_objectLine = 1, RULE_boardObjectLine = 2, RULE_ballObjectLine = 3, 
		RULE_squareBumperObjectLine = 4, RULE_triangleBumperObjectLine = 5, RULE_circleBumperObjectLine = 6, 
		RULE_leftFlipperObjectLine = 7, RULE_rightFlipperObjectLine = 8, RULE_absorberObjectLine = 9, 
		RULE_fireObjectLine = 10, RULE_keydownObjectLine = 11, RULE_keyupObjectLine = 12, 
		RULE_portalObjectLine = 13, RULE_attribute = 14;
	public static final String[] ruleNames = {
		"board", "objectLine", "boardObjectLine", "ballObjectLine", "squareBumperObjectLine", 
		"triangleBumperObjectLine", "circleBumperObjectLine", "leftFlipperObjectLine", 
		"rightFlipperObjectLine", "absorberObjectLine", "fireObjectLine", "keydownObjectLine", 
		"keyupObjectLine", "portalObjectLine", "attribute"
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
			setState(31); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(30); objectLine();
				}
				}
				setState(33); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << 3) | (1L << 4) | (1L << 5) | (1L << 6) | (1L << 7) | (1L << 8) | (1L << 9) | (1L << 10) | (1L << 11) | (1L << 12))) != 0) );
			setState(35); match(EOF);
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
			setState(49);
			switch (_input.LA(1)) {
			case 4:
				enterOuterAlt(_localctx, 1);
				{
				setState(37); boardObjectLine();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 2);
				{
				setState(38); ballObjectLine();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 3);
				{
				setState(39); squareBumperObjectLine();
				}
				break;
			case 1:
				enterOuterAlt(_localctx, 4);
				{
				setState(40); triangleBumperObjectLine();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 5);
				{
				setState(41); circleBumperObjectLine();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 6);
				{
				setState(42); leftFlipperObjectLine();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 7);
				{
				setState(43); rightFlipperObjectLine();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 8);
				{
				setState(44); absorberObjectLine();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 9);
				{
				setState(45); fireObjectLine();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 10);
				{
				setState(46); keyupObjectLine();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(47); keydownObjectLine();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 12);
				{
				setState(48); portalObjectLine();
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
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
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
			setState(51); match(4);
			setState(53); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(52); attribute();
				}
				}
				setState(55); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
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
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
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
			setState(57); match(5);
			setState(59); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(58); attribute();
				}
				}
				setState(61); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
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
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
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
			setState(63); match(8);
			setState(65); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(64); attribute();
				}
				}
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
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
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
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
			setState(69); match(1);
			setState(71); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(70); attribute();
				}
				}
				setState(73); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
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
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
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
			setState(75); match(10);
			setState(77); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(76); attribute();
				}
				}
				setState(79); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
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
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
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
			setState(81); match(9);
			setState(83); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(82); attribute();
				}
				}
				setState(85); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
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
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
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
			setState(87); match(12);
			setState(89); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(88); attribute();
				}
				}
				setState(91); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
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
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
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
			setState(93); match(6);
			setState(95); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(94); attribute();
				}
				}
				setState(97); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
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
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
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
			setState(99); match(2);
			setState(101); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(100); attribute();
				}
				}
				setState(103); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
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
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
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
			setState(105); match(11);
			setState(107); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(106); attribute();
				}
				}
				setState(109); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
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
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
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
			setState(111); match(3);
			setState(113); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(112); attribute();
				}
				}
				setState(115); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
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
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
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
			setState(117); match(7);
			setState(119); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(118); attribute();
				}
				}
				setState(121); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
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
		public List<TerminalNode> NAME() { return getTokens(BoardParser.NAME); }
		public TerminalNode EQUALS() { return getToken(BoardParser.EQUALS, 0); }
		public TerminalNode NAME(int i) {
			return getToken(BoardParser.NAME, i);
		}
		public TerminalNode NUMBER() { return getToken(BoardParser.NUMBER, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_attribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123); match(NAME);
			setState(124); match(EQUALS);
			setState(125);
			_la = _input.LA(1);
			if ( !(_la==NUMBER || _la==NAME) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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
		"\2\3\23\u0082\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t"+
		"\20\3\2\6\2\"\n\2\r\2\16\2#\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\5\3\64\n\3\3\4\3\4\6\48\n\4\r\4\16\49\3\5\3\5\6\5>\n\5"+
		"\r\5\16\5?\3\6\3\6\6\6D\n\6\r\6\16\6E\3\7\3\7\6\7J\n\7\r\7\16\7K\3\b\3"+
		"\b\6\bP\n\b\r\b\16\bQ\3\t\3\t\6\tV\n\t\r\t\16\tW\3\n\3\n\6\n\\\n\n\r\n"+
		"\16\n]\3\13\3\13\6\13b\n\13\r\13\16\13c\3\f\3\f\6\fh\n\f\r\f\16\fi\3\r"+
		"\3\r\6\rn\n\r\r\r\16\ro\3\16\3\16\6\16t\n\16\r\16\16\16u\3\17\3\17\6\17"+
		"z\n\17\r\17\16\17{\3\20\3\20\3\20\3\20\3\20\2\21\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36\2\3\3\22\23\u008a\2!\3\2\2\2\4\63\3\2\2\2\6\65\3\2"+
		"\2\2\b;\3\2\2\2\nA\3\2\2\2\fG\3\2\2\2\16M\3\2\2\2\20S\3\2\2\2\22Y\3\2"+
		"\2\2\24_\3\2\2\2\26e\3\2\2\2\30k\3\2\2\2\32q\3\2\2\2\34w\3\2\2\2\36}\3"+
		"\2\2\2 \"\5\4\3\2! \3\2\2\2\"#\3\2\2\2#!\3\2\2\2#$\3\2\2\2$%\3\2\2\2%"+
		"&\7\1\2\2&\3\3\2\2\2\'\64\5\6\4\2(\64\5\b\5\2)\64\5\n\6\2*\64\5\f\7\2"+
		"+\64\5\16\b\2,\64\5\20\t\2-\64\5\22\n\2.\64\5\24\13\2/\64\5\26\f\2\60"+
		"\64\5\32\16\2\61\64\5\30\r\2\62\64\5\34\17\2\63\'\3\2\2\2\63(\3\2\2\2"+
		"\63)\3\2\2\2\63*\3\2\2\2\63+\3\2\2\2\63,\3\2\2\2\63-\3\2\2\2\63.\3\2\2"+
		"\2\63/\3\2\2\2\63\60\3\2\2\2\63\61\3\2\2\2\63\62\3\2\2\2\64\5\3\2\2\2"+
		"\65\67\7\6\2\2\668\5\36\20\2\67\66\3\2\2\289\3\2\2\29\67\3\2\2\29:\3\2"+
		"\2\2:\7\3\2\2\2;=\7\7\2\2<>\5\36\20\2=<\3\2\2\2>?\3\2\2\2?=\3\2\2\2?@"+
		"\3\2\2\2@\t\3\2\2\2AC\7\n\2\2BD\5\36\20\2CB\3\2\2\2DE\3\2\2\2EC\3\2\2"+
		"\2EF\3\2\2\2F\13\3\2\2\2GI\7\3\2\2HJ\5\36\20\2IH\3\2\2\2JK\3\2\2\2KI\3"+
		"\2\2\2KL\3\2\2\2L\r\3\2\2\2MO\7\f\2\2NP\5\36\20\2ON\3\2\2\2PQ\3\2\2\2"+
		"QO\3\2\2\2QR\3\2\2\2R\17\3\2\2\2SU\7\13\2\2TV\5\36\20\2UT\3\2\2\2VW\3"+
		"\2\2\2WU\3\2\2\2WX\3\2\2\2X\21\3\2\2\2Y[\7\16\2\2Z\\\5\36\20\2[Z\3\2\2"+
		"\2\\]\3\2\2\2][\3\2\2\2]^\3\2\2\2^\23\3\2\2\2_a\7\b\2\2`b\5\36\20\2a`"+
		"\3\2\2\2bc\3\2\2\2ca\3\2\2\2cd\3\2\2\2d\25\3\2\2\2eg\7\4\2\2fh\5\36\20"+
		"\2gf\3\2\2\2hi\3\2\2\2ig\3\2\2\2ij\3\2\2\2j\27\3\2\2\2km\7\r\2\2ln\5\36"+
		"\20\2ml\3\2\2\2no\3\2\2\2om\3\2\2\2op\3\2\2\2p\31\3\2\2\2qs\7\5\2\2rt"+
		"\5\36\20\2sr\3\2\2\2tu\3\2\2\2us\3\2\2\2uv\3\2\2\2v\33\3\2\2\2wy\7\t\2"+
		"\2xz\5\36\20\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|\35\3\2\2\2}~\7"+
		"\23\2\2~\177\7\21\2\2\177\u0080\t\2\2\2\u0080\37\3\2\2\2\20#\639?EKQW"+
		"]ciou{";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}