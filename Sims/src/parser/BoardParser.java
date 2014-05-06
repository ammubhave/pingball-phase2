// Generated from Board.g4 by ANTLR 4.0

package parser;

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
		T__19=1, T__18=2, T__17=3, T__16=4, T__15=5, T__14=6, T__13=7, T__12=8, 
		T__11=9, T__10=10, T__9=11, T__8=12, T__7=13, T__6=14, T__5=15, T__4=16, 
		T__3=17, T__2=18, T__1=19, T__0=20, WHITESPACE=21, NAME=22, NUMBER=23, 
		COMMENT=24;
	public static final String[] tokenNames = {
		"<INVALID>", "'yVelocity'", "'friction1'", "'name'", "'gravity'", "'board'", 
		"'xVelocity'", "'ball'", "'orientation'", "'height'", "'x'", "'y'", "'triangleBumper'", 
		"'fire'", "'action'", "'absorber'", "'squareBumper'", "'trigger'", "'circleBumper'", 
		"'friction2'", "'width'", "WHITESPACE", "NAME", "NUMBER", "COMMENT"
	};
	public static final int
		RULE_gadget = 0, RULE_board = 1, RULE_gravity = 2, RULE_friction1 = 3, 
		RULE_friction2 = 4, RULE_ball = 5, RULE_sBumper = 6, RULE_cBumper = 7, 
		RULE_tBumper = 8, RULE_bumper = 9, RULE_flipper = 10, RULE_absorber = 11, 
		RULE_trigger = 12;
	public static final String[] ruleNames = {
		"gadget", "board", "gravity", "friction1", "friction2", "ball", "sBumper", 
		"cBumper", "tBumper", "bumper", "flipper", "absorber", "trigger"
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
	public static class GadgetContext extends ParserRuleContext {
		public AbsorberContext absorber() {
			return getRuleContext(AbsorberContext.class,0);
		}
		public FlipperContext flipper() {
			return getRuleContext(FlipperContext.class,0);
		}
		public BumperContext bumper() {
			return getRuleContext(BumperContext.class,0);
		}
		public GadgetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gadget; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterGadget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitGadget(this);
		}
	}

	public final GadgetContext gadget() throws RecognitionException {
		GadgetContext _localctx = new GadgetContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_gadget);
		try {
			setState(29);
			switch (_input.LA(1)) {
			case 12:
			case 16:
			case 18:
				enterOuterAlt(_localctx, 1);
				{
				setState(26); bumper();
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(27); flipper();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 3);
				{
				setState(28); absorber();
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

	public static class BoardContext extends ParserRuleContext {
		public Friction1Context friction1() {
			return getRuleContext(Friction1Context.class,0);
		}
		public List<TriggerContext> trigger() {
			return getRuleContexts(TriggerContext.class);
		}
		public List<BallContext> ball() {
			return getRuleContexts(BallContext.class);
		}
		public TriggerContext trigger(int i) {
			return getRuleContext(TriggerContext.class,i);
		}
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public List<GadgetContext> gadget() {
			return getRuleContexts(GadgetContext.class);
		}
		public TerminalNode EOF() { return getToken(BoardParser.EOF, 0); }
		public BallContext ball(int i) {
			return getRuleContext(BallContext.class,i);
		}
		public GadgetContext gadget(int i) {
			return getRuleContext(GadgetContext.class,i);
		}
		public Friction2Context friction2() {
			return getRuleContext(Friction2Context.class,0);
		}
		public GravityContext gravity() {
			return getRuleContext(GravityContext.class,0);
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
		enterRule(_localctx, 2, RULE_board);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31); match(5);
			setState(34);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(32); match(3);
				setState(33); match(NAME);
				}
			}

			setState(37);
			_la = _input.LA(1);
			if (_la==4) {
				{
				setState(36); gravity();
				}
			}

			setState(40);
			_la = _input.LA(1);
			if (_la==2) {
				{
				setState(39); friction1();
				}
			}

			setState(43);
			_la = _input.LA(1);
			if (_la==19) {
				{
				setState(42); friction2();
				}
			}

			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==7) {
				{
				{
				setState(45); ball();
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 12) | (1L << 15) | (1L << 16) | (1L << 18) | (1L << NAME))) != 0)) {
				{
				{
				setState(51); gadget();
				}
				}
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==13) {
				{
				{
				setState(57); trigger();
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63); match(EOF);
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

	public static class GravityContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(BoardParser.NUMBER, 0); }
		public GravityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gravity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterGravity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitGravity(this);
		}
	}

	public final GravityContext gravity() throws RecognitionException {
		GravityContext _localctx = new GravityContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_gravity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65); match(4);
			setState(66); match(NUMBER);
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

	public static class Friction1Context extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(BoardParser.NUMBER, 0); }
		public Friction1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_friction1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterFriction1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitFriction1(this);
		}
	}

	public final Friction1Context friction1() throws RecognitionException {
		Friction1Context _localctx = new Friction1Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_friction1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68); match(2);
			setState(69); match(NUMBER);
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

	public static class Friction2Context extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(BoardParser.NUMBER, 0); }
		public Friction2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_friction2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterFriction2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitFriction2(this);
		}
	}

	public final Friction2Context friction2() throws RecognitionException {
		Friction2Context _localctx = new Friction2Context(_ctx, getState());
		enterRule(_localctx, 8, RULE_friction2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71); match(19);
			setState(72); match(NUMBER);
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

	public static class BallContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode NUMBER(int i) {
			return getToken(BoardParser.NUMBER, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(BoardParser.NUMBER); }
		public BallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ball; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterBall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitBall(this);
		}
	}

	public final BallContext ball() throws RecognitionException {
		BallContext _localctx = new BallContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_ball);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74); match(7);
			setState(75); match(3);
			setState(76); match(NAME);
			setState(77); match(10);
			setState(78); match(NUMBER);
			setState(79); match(11);
			setState(80); match(NUMBER);
			setState(81); match(6);
			setState(82); match(NUMBER);
			setState(83); match(1);
			setState(84); match(NUMBER);
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

	public static class SBumperContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode NUMBER(int i) {
			return getToken(BoardParser.NUMBER, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(BoardParser.NUMBER); }
		public SBumperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sBumper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterSBumper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitSBumper(this);
		}
	}

	public final SBumperContext sBumper() throws RecognitionException {
		SBumperContext _localctx = new SBumperContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_sBumper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86); match(16);
			setState(87); match(3);
			setState(88); match(NAME);
			setState(89); match(10);
			setState(90); match(NUMBER);
			setState(91); match(11);
			setState(92); match(NUMBER);
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

	public static class CBumperContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode NUMBER(int i) {
			return getToken(BoardParser.NUMBER, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(BoardParser.NUMBER); }
		public CBumperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cBumper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterCBumper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitCBumper(this);
		}
	}

	public final CBumperContext cBumper() throws RecognitionException {
		CBumperContext _localctx = new CBumperContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cBumper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94); match(18);
			setState(95); match(3);
			setState(96); match(NAME);
			setState(97); match(10);
			setState(98); match(NUMBER);
			setState(99); match(11);
			setState(100); match(NUMBER);
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

	public static class TBumperContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode NUMBER(int i) {
			return getToken(BoardParser.NUMBER, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(BoardParser.NUMBER); }
		public TBumperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tBumper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterTBumper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitTBumper(this);
		}
	}

	public final TBumperContext tBumper() throws RecognitionException {
		TBumperContext _localctx = new TBumperContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_tBumper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102); match(12);
			setState(103); match(3);
			setState(104); match(NAME);
			setState(105); match(10);
			setState(106); match(NUMBER);
			setState(107); match(11);
			setState(108); match(NUMBER);
			setState(109); match(8);
			setState(110); match(NUMBER);
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

	public static class BumperContext extends ParserRuleContext {
		public TBumperContext tBumper() {
			return getRuleContext(TBumperContext.class,0);
		}
		public CBumperContext cBumper() {
			return getRuleContext(CBumperContext.class,0);
		}
		public SBumperContext sBumper() {
			return getRuleContext(SBumperContext.class,0);
		}
		public BumperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bumper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterBumper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitBumper(this);
		}
	}

	public final BumperContext bumper() throws RecognitionException {
		BumperContext _localctx = new BumperContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_bumper);
		try {
			setState(115);
			switch (_input.LA(1)) {
			case 16:
				enterOuterAlt(_localctx, 1);
				{
				setState(112); sBumper();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 2);
				{
				setState(113); cBumper();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 3);
				{
				setState(114); tBumper();
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

	public static class FlipperContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(BoardParser.NAME); }
		public TerminalNode NUMBER(int i) {
			return getToken(BoardParser.NUMBER, i);
		}
		public TerminalNode NAME(int i) {
			return getToken(BoardParser.NAME, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(BoardParser.NUMBER); }
		public FlipperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flipper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterFlipper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitFlipper(this);
		}
	}

	public final FlipperContext flipper() throws RecognitionException {
		FlipperContext _localctx = new FlipperContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_flipper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117); match(NAME);
			setState(118); match(3);
			setState(119); match(NAME);
			setState(120); match(10);
			setState(121); match(NUMBER);
			setState(122); match(11);
			setState(123); match(NUMBER);
			setState(124); match(8);
			setState(125); match(NUMBER);
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

	public static class AbsorberContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode NUMBER(int i) {
			return getToken(BoardParser.NUMBER, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(BoardParser.NUMBER); }
		public AbsorberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_absorber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAbsorber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAbsorber(this);
		}
	}

	public final AbsorberContext absorber() throws RecognitionException {
		AbsorberContext _localctx = new AbsorberContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_absorber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127); match(15);
			setState(128); match(3);
			setState(129); match(NAME);
			setState(130); match(10);
			setState(131); match(NUMBER);
			setState(132); match(11);
			setState(133); match(NUMBER);
			setState(134); match(20);
			setState(135); match(NUMBER);
			setState(136); match(9);
			setState(137); match(NUMBER);
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

	public static class TriggerContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(BoardParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(BoardParser.NAME, i);
		}
		public TriggerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trigger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterTrigger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitTrigger(this);
		}
	}

	public final TriggerContext trigger() throws RecognitionException {
		TriggerContext _localctx = new TriggerContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_trigger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139); match(13);
			setState(140); match(17);
			setState(141); match(NAME);
			setState(142); match(14);
			setState(143); match(NAME);
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
		"\2\3\32\u0094\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\5\2 "+
		"\n\2\3\3\3\3\3\3\5\3%\n\3\3\3\5\3(\n\3\3\3\5\3+\n\3\3\3\5\3.\n\3\3\3\7"+
		"\3\61\n\3\f\3\16\3\64\13\3\3\3\7\3\67\n\3\f\3\16\3:\13\3\3\3\7\3=\n\3"+
		"\f\3\16\3@\13\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\5\13v\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\2\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\2\u0091\2\37\3\2\2"+
		"\2\4!\3\2\2\2\6C\3\2\2\2\bF\3\2\2\2\nI\3\2\2\2\fL\3\2\2\2\16X\3\2\2\2"+
		"\20`\3\2\2\2\22h\3\2\2\2\24u\3\2\2\2\26w\3\2\2\2\30\u0081\3\2\2\2\32\u008d"+
		"\3\2\2\2\34 \5\24\13\2\35 \5\26\f\2\36 \5\30\r\2\37\34\3\2\2\2\37\35\3"+
		"\2\2\2\37\36\3\2\2\2 \3\3\2\2\2!$\7\7\2\2\"#\7\5\2\2#%\7\30\2\2$\"\3\2"+
		"\2\2$%\3\2\2\2%\'\3\2\2\2&(\5\6\4\2\'&\3\2\2\2\'(\3\2\2\2(*\3\2\2\2)+"+
		"\5\b\5\2*)\3\2\2\2*+\3\2\2\2+-\3\2\2\2,.\5\n\6\2-,\3\2\2\2-.\3\2\2\2."+
		"\62\3\2\2\2/\61\5\f\7\2\60/\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63"+
		"\3\2\2\2\638\3\2\2\2\64\62\3\2\2\2\65\67\5\2\2\2\66\65\3\2\2\2\67:\3\2"+
		"\2\28\66\3\2\2\289\3\2\2\29>\3\2\2\2:8\3\2\2\2;=\5\32\16\2<;\3\2\2\2="+
		"@\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2\2\2@>\3\2\2\2AB\7\1\2\2B\5\3\2\2\2"+
		"CD\7\6\2\2DE\7\31\2\2E\7\3\2\2\2FG\7\4\2\2GH\7\31\2\2H\t\3\2\2\2IJ\7\25"+
		"\2\2JK\7\31\2\2K\13\3\2\2\2LM\7\t\2\2MN\7\5\2\2NO\7\30\2\2OP\7\f\2\2P"+
		"Q\7\31\2\2QR\7\r\2\2RS\7\31\2\2ST\7\b\2\2TU\7\31\2\2UV\7\3\2\2VW\7\31"+
		"\2\2W\r\3\2\2\2XY\7\22\2\2YZ\7\5\2\2Z[\7\30\2\2[\\\7\f\2\2\\]\7\31\2\2"+
		"]^\7\r\2\2^_\7\31\2\2_\17\3\2\2\2`a\7\24\2\2ab\7\5\2\2bc\7\30\2\2cd\7"+
		"\f\2\2de\7\31\2\2ef\7\r\2\2fg\7\31\2\2g\21\3\2\2\2hi\7\16\2\2ij\7\5\2"+
		"\2jk\7\30\2\2kl\7\f\2\2lm\7\31\2\2mn\7\r\2\2no\7\31\2\2op\7\n\2\2pq\7"+
		"\31\2\2q\23\3\2\2\2rv\5\16\b\2sv\5\20\t\2tv\5\22\n\2ur\3\2\2\2us\3\2\2"+
		"\2ut\3\2\2\2v\25\3\2\2\2wx\7\30\2\2xy\7\5\2\2yz\7\30\2\2z{\7\f\2\2{|\7"+
		"\31\2\2|}\7\r\2\2}~\7\31\2\2~\177\7\n\2\2\177\u0080\7\31\2\2\u0080\27"+
		"\3\2\2\2\u0081\u0082\7\21\2\2\u0082\u0083\7\5\2\2\u0083\u0084\7\30\2\2"+
		"\u0084\u0085\7\f\2\2\u0085\u0086\7\31\2\2\u0086\u0087\7\r\2\2\u0087\u0088"+
		"\7\31\2\2\u0088\u0089\7\26\2\2\u0089\u008a\7\31\2\2\u008a\u008b\7\13\2"+
		"\2\u008b\u008c\7\31\2\2\u008c\31\3\2\2\2\u008d\u008e\7\17\2\2\u008e\u008f"+
		"\7\23\2\2\u008f\u0090\7\30\2\2\u0090\u0091\7\20\2\2\u0091\u0092\7\30\2"+
		"\2\u0092\33\3\2\2\2\13\37$\'*-\628>u";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}