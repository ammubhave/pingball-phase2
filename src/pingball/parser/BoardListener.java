// Generated from src/pingball/parser/Board.g4 by ANTLR 4.0

package pingball.parser;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface BoardListener extends ParseTreeListener {
	void enterObjectLine(BoardParser.ObjectLineContext ctx);
	void exitObjectLine(BoardParser.ObjectLineContext ctx);

	void enterLeftFlipperObjectLine(BoardParser.LeftFlipperObjectLineContext ctx);
	void exitLeftFlipperObjectLine(BoardParser.LeftFlipperObjectLineContext ctx);

	void enterRightFlipperObjectLine(BoardParser.RightFlipperObjectLineContext ctx);
	void exitRightFlipperObjectLine(BoardParser.RightFlipperObjectLineContext ctx);

	void enterAbsorberObjectLine(BoardParser.AbsorberObjectLineContext ctx);
	void exitAbsorberObjectLine(BoardParser.AbsorberObjectLineContext ctx);

	void enterAttribute(BoardParser.AttributeContext ctx);
	void exitAttribute(BoardParser.AttributeContext ctx);

	void enterFireObjectLine(BoardParser.FireObjectLineContext ctx);
	void exitFireObjectLine(BoardParser.FireObjectLineContext ctx);

	void enterBallObjectLine(BoardParser.BallObjectLineContext ctx);
	void exitBallObjectLine(BoardParser.BallObjectLineContext ctx);

	void enterBoard(BoardParser.BoardContext ctx);
	void exitBoard(BoardParser.BoardContext ctx);

	void enterBoardObjectLine(BoardParser.BoardObjectLineContext ctx);
	void exitBoardObjectLine(BoardParser.BoardObjectLineContext ctx);

	void enterPortalObjectLine(BoardParser.PortalObjectLineContext ctx);
	void exitPortalObjectLine(BoardParser.PortalObjectLineContext ctx);

	void enterTriangleBumperObjectLine(BoardParser.TriangleBumperObjectLineContext ctx);
	void exitTriangleBumperObjectLine(BoardParser.TriangleBumperObjectLineContext ctx);

	void enterSquareBumperObjectLine(BoardParser.SquareBumperObjectLineContext ctx);
	void exitSquareBumperObjectLine(BoardParser.SquareBumperObjectLineContext ctx);

	void enterCircleBumperObjectLine(BoardParser.CircleBumperObjectLineContext ctx);
	void exitCircleBumperObjectLine(BoardParser.CircleBumperObjectLineContext ctx);

	void enterKeyupObjectLine(BoardParser.KeyupObjectLineContext ctx);
	void exitKeyupObjectLine(BoardParser.KeyupObjectLineContext ctx);

	void enterKeydownObjectLine(BoardParser.KeydownObjectLineContext ctx);
	void exitKeydownObjectLine(BoardParser.KeydownObjectLineContext ctx);
}