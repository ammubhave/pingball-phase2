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
import pingball.board.OuterWall;
import pingball.board.OuterWall.OuterWallsOrientation;
import pingball.board.Portal;
import pingball.board.RightFlipper;
import pingball.board.SquareBumper;
import pingball.board.TriangularBumper;
import pingball.board.TriangularBumper.TriangularBumperOrientation;
import pingball.ui.board.AbsorberPainter;
import pingball.ui.board.BallPainter;
import pingball.ui.board.CircularBumperPainter;
import pingball.ui.board.LeftFlipperPainter;
import pingball.ui.board.OuterWallPainter;
import pingball.ui.board.PortalPainter;
import pingball.ui.board.RightFlipperPainter;
import pingball.ui.board.SquareBumperPainter;
import pingball.ui.board.TriangularBumperPainter;
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
        HashMap<String, ArrayList<Gadget>> keyUMap = new HashMap<String, ArrayList<Gadget>>();
        HashMap<String, ArrayList<Gadget>> keyDMap = new HashMap<String, ArrayList<Gadget>>();

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
                    Double.parseDouble(attributes.get("friction2")), keyUMap, keyDMap);
            
            OuterWall w1 = new OuterWall(new Vect(0, 0), OuterWallsOrientation.HORIZONTAL, "w1");         
            board.addGadget(w1);
            board.addGadgetPainter(new OuterWallPainter(w1));
            
            OuterWall w2 = new OuterWall(new Vect(0, 0), OuterWallsOrientation.VERTICAL, "w2");         
            board.addGadget(w2);
            board.addGadgetPainter(new OuterWallPainter(w2));
            
            OuterWall w3 = new OuterWall(new Vect(0, 21), OuterWallsOrientation.HORIZONTAL, "w3");         
            board.addGadget(w3);
            board.addGadgetPainter(new OuterWallPainter(w3));
            
            OuterWall w4 = new OuterWall(new Vect(21, 0), OuterWallsOrientation.VERTICAL, "w4");         
            board.addGadget(w4);
            board.addGadgetPainter(new OuterWallPainter(w4));
            
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
            Ball ball = new Ball(
                    attributes.get("name"),
                    new Vect(Double.parseDouble(attributes.get("x")), Double.parseDouble(attributes.get("y"))), 
                    new Vect(Double.parseDouble(attributes.get("xVelocity")), Double.parseDouble(attributes.get("yVelocity"))));
            board.addBall(ball);
            board.addGadgetPainter(new BallPainter(ball));
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
            SquareBumper gadget = new SquareBumper(
                    new Vect(Integer.parseInt(attributes.get("x")), Integer.parseInt(attributes.get("y"))), 
                    attributes.get("name"));
            board.addGadget(gadget);
            board.addGadgetPainter(new SquareBumperPainter(gadget));
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
            TriangularBumper gadget = new TriangularBumper(
                    new Vect(Integer.parseInt(attributes.get("x")), Integer.parseInt(attributes.get("y"))),
                    orientation,
                    attributes.get("name")); 
            board.addGadget(gadget);
            board.addGadgetPainter(new TriangularBumperPainter(gadget));
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
            LeftFlipper gadget = new LeftFlipper(
                    new Vect(Integer.parseInt(attributes.get("x")), Integer.parseInt(attributes.get("y"))),
                    orientation, 
                    attributes.get("name")); 
            board.addGadget(gadget);
            board.addGadgetPainter(new LeftFlipperPainter(gadget));
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
                orientation = FlipperOrientation.TOP; break;
            case 90:
                orientation = FlipperOrientation.RIGHT; break;
            case 180:
                orientation = FlipperOrientation.BOTTOM; break;
            case 270:
                orientation = FlipperOrientation.LEFT; break;
            }
            RightFlipper gadget = new RightFlipper(
                    new Vect(Integer.parseInt(attributes.get("x")), Integer.parseInt(attributes.get("y"))),
                    orientation, 
                    attributes.get("name")); 
            board.addGadget(gadget);
            board.addGadgetPainter(new RightFlipperPainter(gadget));
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
            CircularBumper gadget = new CircularBumper(
                    new Vect(Integer.parseInt(attributes.get("x")), Integer.parseInt(attributes.get("y"))), 
                    attributes.get("name")); 
            board.addGadget(gadget);
            board.addGadgetPainter(new CircularBumperPainter(gadget));
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
            Absorber gadget = new Absorber(
                    new Vect(Integer.parseInt(attributes.get("x")), Integer.parseInt(attributes.get("y"))),
                    Integer.parseInt(attributes.get("width")), Integer.parseInt(attributes.get("height")),
                    attributes.get("name"));
            board.addGadget(gadget);
            board.addGadgetPainter(new AbsorberPainter(gadget));
            attributes.clear();
        }

        
        // portal
        
        @Override
        public void enterPortalObjectLine(BoardParser.PortalObjectLineContext ctx) {
            attributes.clear();
            attributes.put("otherBoard", board.getName());
        }
        @Override
        public void exitPortalAttributes(BoardParser.PortalAttributesContext ctx) {
            attributes.put(ctx.getStart().getText(), ctx.getStop().getText());
        }
        @Override
        public void exitPortalObjectLine(BoardParser.PortalObjectLineContext ctx) {
            Portal gadget = new Portal(
                    new Vect(Integer.parseInt(attributes.get("x")), Integer.parseInt(attributes.get("y"))),
                    attributes.get("name"));
            gadget.setTargetBoard(attributes.get("targetBoard"));
            gadget.setTargetPortal(attributes.get("targetPortal"));
            board.addGadget(gadget);
            board.addGadgetPainter(new PortalPainter(gadget));
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
        
        // keydownAttributes : attributeKey | attributeAction ;
        @Override
        public void enterKeydownObjectLine(BoardParser.KeydownObjectLineContext ctx) {
            attributes.clear();
        }
        @Override
        public void exitKeydownAttributes(BoardParser.KeydownAttributesContext ctx) {
            attributes.put(ctx.getStart().getText(), ctx.getStop().getText());
        }
        @Override
        public void exitKeydownObjectLine(BoardParser.KeydownObjectLineContext ctx) {
            board.addKeyDownBinding(attributes.get("key"), board.getGadgetFromName(attributes.get("action")));
        }
        
        // keyupAttributes : attributeKey | attributeAction ;
        @Override
        public void enterKeyupObjectLine(BoardParser.KeyupObjectLineContext ctx) {
            attributes.clear();
        }
        @Override
        public void exitKeyupAttributes(BoardParser.KeyupAttributesContext ctx) {
            attributes.put(ctx.getStart().getText(), ctx.getStop().getText());
        }
        @Override
        public void exitKeyupObjectLine(BoardParser.KeyupObjectLineContext ctx) {
            board.addKeyUpBinding(attributes.get("key"), board.getGadgetFromName(attributes.get("action")));
        }
    }
}
