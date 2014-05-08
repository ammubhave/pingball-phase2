// Generated from Board.g4 by ANTLR 4.0

package parser;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface BoardListener extends ParseTreeListener {
	void enterFriction1(BoardParser.Friction1Context ctx);
	void exitFriction1(BoardParser.Friction1Context ctx);

	void enterTrigger(BoardParser.TriggerContext ctx);
	void exitTrigger(BoardParser.TriggerContext ctx);

	void enterAbsorber(BoardParser.AbsorberContext ctx);
	void exitAbsorber(BoardParser.AbsorberContext ctx);

	void enterBall(BoardParser.BallContext ctx);
	void exitBall(BoardParser.BallContext ctx);

	void enterGadget(BoardParser.GadgetContext ctx);
	void exitGadget(BoardParser.GadgetContext ctx);

	void enterTBumper(BoardParser.TBumperContext ctx);
	void exitTBumper(BoardParser.TBumperContext ctx);

	void enterCBumper(BoardParser.CBumperContext ctx);
	void exitCBumper(BoardParser.CBumperContext ctx);

	void enterFlipper(BoardParser.FlipperContext ctx);
	void exitFlipper(BoardParser.FlipperContext ctx);

	void enterBoard(BoardParser.BoardContext ctx);
	void exitBoard(BoardParser.BoardContext ctx);

	void enterFriction2(BoardParser.Friction2Context ctx);
	void exitFriction2(BoardParser.Friction2Context ctx);

	void enterSBumper(BoardParser.SBumperContext ctx);
	void exitSBumper(BoardParser.SBumperContext ctx);

	void enterBumper(BoardParser.BumperContext ctx);
	void exitBumper(BoardParser.BumperContext ctx);

	void enterGravity(BoardParser.GravityContext ctx);
	void exitGravity(BoardParser.GravityContext ctx);
}