package pingball.parser;

import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import physics.Vect;
import pingball.board.Absorber;
import pingball.board.Ball;
import pingball.board.Board;
import pingball.board.CircularBumper;
import pingball.board.Flipper.FlipperOrientation;
import pingball.board.Gadget;
import pingball.board.LeftFlipper;
import pingball.board.RightFlipper;
import pingball.board.SquareBumper;
import pingball.board.TriangularBumper;
import pingball.board.TriangularBumper.TriangularBumperOrientation;
//import pingball.board.TriangularBumper.TriangularBumperOrientation;

public class BoardFactory {
    /**
     * @param input string representing a board
     * @return Board corresponding to the input
     */
    public static Board parse(String input) {
        // Create a stream of tokens using the lexer.
        CharStream stream = new ANTLRInputStream(input);
        BoardLexer lexer = new BoardLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        
        //System.out.println(lexer.getAllTokens());
        
        // Feed the tokens into the parser.
        BoardParser parser = new BoardParser(tokens);
        parser.reportErrorsAsExceptions();
        
        // Generate the parse tree using the starter rule.
        ParseTree tree = parser.board(); // "expression" is the starter rule
        
        // debugging option #1: print the tree to the console
//        System.err.println(tree.toStringTree(parser));

        // debugging option #2: show the tree in a window
 //       ((RuleContext)tree).inspect(parser);

        // Finally, construct an Expression value by walking over the parse tree.
        ParseTreeWalker walker = new ParseTreeWalker();
        BoardCreatorListener listener = new BoardCreatorListener();
        walker.walk(listener, tree);
        
        // return the Expression value that the listener created
        return listener.getBoard();
    }
    
    private static class BoardCreatorListener extends BoardBaseListener {
       // private Stack<Expression> stack = new Stack<>();
        Board board;
        HashMap<String, String> attributes = new HashMap<String, String>();

        // board
        
        @Override
        public void enterBoardObjectLine(BoardParser.BoardObjectLineContext ctx) {
            attributes.clear();
            attributes.put("name", "invalid");
            attributes.put("gravity", "25.0");
            attributes.put("friction1", "0.025");
            attributes.put("friction2", "0.025");
        }
        @Override
        public void exitBoardAttributes(BoardParser.BoardAttributesContext ctx) {
            attributes.put(ctx.getStart().getText(), ctx.getStop().getText());
        }
        @Override
        public void exitBoardObjectLine(BoardParser.BoardObjectLineContext ctx) {
            board = new Board(
                    attributes.get("name"),
                    new ArrayList<Ball>(),
                    new ArrayList<Gadget>(),
                    Double.parseDouble(attributes.get("gravity")), 
                    Double.parseDouble(attributes.get("friction1")),
                    Double.parseDouble(attributes.get("friction2")));
            // The following code is not required since the Board constructor does this for us
           /* board.addGadget(new OuterWallsGadget(new Vect(0, 0), OuterWallsOrientation.HORIZONTAL));
            board.addGadget(new OuterWallsGadget(new Vect(0, 0), OuterWallsOrientation.VERTICAL));
            board.addGadget(new OuterWallsGadget(new Vect(0, 21), OuterWallsOrientation.HORIZONTAL));
            board.addGadget(new OuterWallsGadget(new Vect(21, 0), OuterWallsOrientation.VERTICAL));*/
            attributes.clear();
        }
        
        // ball
        
        @Override
        public void enterBallObjectLine(BoardParser.BallObjectLineContext ctx) {
            attributes.clear();
            attributes.put("name", "invalid");
        }
        @Override
        public void exitBallAttributes(BoardParser.BallAttributesContext ctx) {
            attributes.put(ctx.getStart().getText(), ctx.getStop().getText());
        }
        @Override
        public void exitBallObjectLine(BoardParser.BallObjectLineContext ctx) {
            board.addBall(new Ball(
                    attributes.get("name"),
                    new Vect(Double.parseDouble(attributes.get("x")), Double.parseDouble(attributes.get("y"))), 
                    new Vect(Double.parseDouble(attributes.get("xVelocity")), Double.parseDouble(attributes.get("yVelocity")))));
            attributes.clear();
        }
        
        // squareBumper
        
        @Override
        public void enterSquareBumperObjectLine(BoardParser.SquareBumperObjectLineContext ctx) {
            attributes.clear();
            attributes.put("name", "invalid");
        }
        @Override
        public void exitSquareBumperAttributes(BoardParser.SquareBumperAttributesContext ctx) {
            attributes.put(ctx.getStart().getText(), ctx.getStop().getText());
        }
        @Override
        public void exitSquareBumperObjectLine(BoardParser.SquareBumperObjectLineContext ctx) {
            board.addGadget(new SquareBumper(
                    new Vect(Integer.parseInt(attributes.get("x")), Integer.parseInt(attributes.get("y"))), 
                    attributes.get("name")));
            attributes.clear();
        }
        
        // triangleBumper
        
        @Override
        public void enterTriangleBumperObjectLine(BoardParser.TriangleBumperObjectLineContext ctx) {
            attributes.clear();
            attributes.put("name", "invalid");
            attributes.put("orientation", "0");
        }
        @Override
        public void exitTriangleBumperAttributes(BoardParser.TriangleBumperAttributesContext ctx) {
            attributes.put(ctx.getStart().getText(), ctx.getStop().getText());
        }
        @Override
        public void exitTriangleBumperObjectLine(BoardParser.TriangleBumperObjectLineContext ctx) {
            TriangularBumperOrientation orientation = TriangularBumperOrientation.TOP_LEFT;
            switch (Integer.parseInt(attributes.get("orientation"))) {
            case 0:
                orientation = TriangularBumperOrientation.TOP_LEFT; break;
            case 90:
                orientation = TriangularBumperOrientation.TOP_RIGHT; break;
            case 180:
                orientation = TriangularBumperOrientation.BOTTOM_RIGHT; break;
            case 270:
                orientation = TriangularBumperOrientation.BOTTOM_LEFT; break;
            }
            board.addGadget(new TriangularBumper(
                    new Vect(Integer.parseInt(attributes.get("x")), Integer.parseInt(attributes.get("y"))),
                    orientation,
                    attributes.get("name")));
            attributes.clear();
        }
        
        // add other gadget lines too!
        
        // LeftFlipper
        @Override
        public void enterLeftFlipperObjectLine(BoardParser.LeftFlipperObjectLineContext ctx) {
            attributes.clear();
            attributes.put("name", "invalid");
            attributes.put("orientation", "0");
        }
        @Override
        public void exitLeftFlipperAttributes(BoardParser.LeftFlipperAttributesContext ctx) {
            attributes.put(ctx.getStart().getText(), ctx.getStop().getText());
        }
        @Override
        public void exitLeftFlipperObjectLine(BoardParser.LeftFlipperObjectLineContext ctx) {
            FlipperOrientation orientation = FlipperOrientation.LEFT;
            switch (Integer.parseInt(attributes.get("orientation"))) {
            // CHECK ORIENTATIONS FOR EACH ANGLE
            case 0:
                orientation = FlipperOrientation.LEFT; break;
            case 90:
                orientation = FlipperOrientation.TOP; break;
            case 180:
                orientation = FlipperOrientation.RIGHT; break;
            case 270:
                orientation = FlipperOrientation.BOTTOM; break;
            }
            board.addGadget(new LeftFlipper(
                    new Vect(Integer.parseInt(attributes.get("x")), Integer.parseInt(attributes.get("y"))),
                    orientation, 
                    attributes.get("name")));
            attributes.clear();
        }
        
     // RightFlipper
        @Override
        public void enterRightFlipperObjectLine(BoardParser.RightFlipperObjectLineContext ctx) {
            attributes.clear();
            attributes.put("name", "invalid");
            attributes.put("orientation", "0");
        }
        @Override
        public void exitRightFlipperAttributes(BoardParser.RightFlipperAttributesContext ctx) {
            attributes.put(ctx.getStart().getText(), ctx.getStop().getText());
        }
        @Override
        public void exitRightFlipperObjectLine(BoardParser.RightFlipperObjectLineContext ctx) {
            FlipperOrientation orientation = FlipperOrientation.LEFT;
            switch (Integer.parseInt(attributes.get("orientation"))) {
            // CHECK ORIENTATIONS FOR EACH ANGLE
            case 0:
                orientation = FlipperOrientation.LEFT; break;
            case 90:
                orientation = FlipperOrientation.TOP; break;
            case 180:
                orientation = FlipperOrientation.RIGHT; break;
            case 270:
                orientation = FlipperOrientation.BOTTOM; break;
            }
            board.addGadget(new RightFlipper(
                    new Vect(Integer.parseInt(attributes.get("x")), Integer.parseInt(attributes.get("y"))),
                    orientation, 
                    attributes.get("name")));
            attributes.clear();
        }
        
        // circleBumper
        
        @Override
        public void enterCircleBumperObjectLine(BoardParser.CircleBumperObjectLineContext ctx) {
            attributes.clear();
            attributes.put("name", "invalid");
        }
        @Override
        public void exitCircleBumperAttributes(BoardParser.CircleBumperAttributesContext ctx) {
            attributes.put(ctx.getStart().getText(), ctx.getStop().getText());
        }
        @Override
        public void exitCircleBumperObjectLine(BoardParser.CircleBumperObjectLineContext ctx) {
            board.addGadget(new CircularBumper(
                    new Vect(Integer.parseInt(attributes.get("x")), Integer.parseInt(attributes.get("y"))), 
                    attributes.get("name")));
            attributes.clear();
        }
        
        
        // absorber
        
        @Override
        public void enterAbsorberObjectLine(BoardParser.AbsorberObjectLineContext ctx) {
            attributes.clear();
            attributes.put("name", "invalid");
        }
        @Override
        public void exitAbsorberAttributes(BoardParser.AbsorberAttributesContext ctx) {
            attributes.put(ctx.getStart().getText(), ctx.getStop().getText());
        }
        @Override
        public void exitAbsorberObjectLine(BoardParser.AbsorberObjectLineContext ctx) {
            board.addGadget(new Absorber(
                    new Vect(Integer.parseInt(attributes.get("x")), Integer.parseInt(attributes.get("y"))),
                            Integer.parseInt(attributes.get("width")), Integer.parseInt(attributes.get("height")),
                            attributes.get("name")));
            attributes.clear();
        }
        
        public Board getBoard() {
            return board;
        }
        
        // fireAttributes : attributeName | attributeTrigger | attributeAction ;
        @Override
        public void enterFireObjectLine(BoardParser.FireObjectLineContext ctx) {
            attributes.clear();
            attributes.put("name", "invalid");
        }
        @Override
        public void exitFireAttributes(BoardParser.FireAttributesContext ctx) {
            attributes.put(ctx.getStart().getText(), ctx.getStop().getText());
        }
        @Override
        public void exitFireObjectLine(BoardParser.FireObjectLineContext ctx) {
            Gadget gadget = board.getGadgetFromName(attributes.get("trigger"));
            gadget.hookActionToTrigger(board.getGadgetFromName(attributes.get("action")));
        }
    }
}
