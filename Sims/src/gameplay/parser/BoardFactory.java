package parser;

import gameplay.Absorber;
import gameplay.Ball;
import gameplay.Board;
import gameplay.Bumper;
import gameplay.Flipper;
import gameplay.Gadget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import physics.Vect;
import server.PingballClient;
import utilities.Coords;

/**
 * This class uses antlr's parsing and Board.g4 to generate a board
 * 
 * @author Julia
 * 
 */
public class BoardFactory {

    /**
     * Returns a board with the client as a parameter, and the list of gadgets
     * generated from the file.
     * 
     * @param client
     *            PingballClient corresponding to the boarda
     * @param input
     *            string representing a conjunctive boolean expression
     * @return Board corresponding to the input
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static Board parse(PingballClient client, File input)
            throws FileNotFoundException, IOException {
        // Create a stream of tokens using the lexer.
        CharStream stream = new ANTLRInputStream(new FileReader(input));
        BoardLexer lexer = new BoardLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);

        // Feed the tokens into the parser.
        BoardParser parser = new BoardParser(tokens);
        parser.reportErrorsAsExceptions();

        // Generate the parse tree using the starter rule.
        ParseTree tree = parser.board(); // "expression" is the starter rule

        // debugging option #1: print the tree to the console
        // System.err.println(tree.toStringTree(parser));

        // debugging option #2: show the tree in a window
        // ((RuleContext)tree).inspect(parser);

        // debugging option #3: walk the tree with a listener
        // new ParseTreeWalker().walk(new PrintEverythingListener(), tree);

        // Finally, construct an Board value by walking over the parse tree.
        ParseTreeWalker walker = new ParseTreeWalker();
        BoardCreatorListener listener = new BoardCreatorListener(client);
        walker.walk(listener, tree);

        // return the Board value that the listener created
        return listener.getBoard();
    }

    /**
     * Creates objects as exitTriggers are entered, depending on which trigger.
     * 
     * @author Julia
     * 
     */
    private static class BoardCreatorListener extends BoardBaseListener {
        private ArrayList<Gadget> gadgets = new ArrayList<Gadget>();
        private ArrayList<Ball> balls = new ArrayList<Ball>();
        private HashMap<String, Gadget> refs = new HashMap<String, Gadget>();
        private Board board = null;
        private PingballClient client;
        private double gravity, friction1, friction2;

        /**
         * Constructor including the client to be passed to the board
         * 
         * @param client
         *            client to be passed to the board
         */
        public BoardCreatorListener(PingballClient client) {
            super();
            this.client = client;
            gravity = 25.0;
            friction1 = 0.020;
            friction2 = 0.020;
        }

        /**
         * Tells the first object which object is triggered when a ball boucnes
         * off of it.
         */
        @Override
        public void exitTrigger(BoardParser.TriggerContext ctx) {
            Gadget trigger = refs.get(ctx.NAME(0).getText().trim());
            Gadget action = refs.get(ctx.NAME(1).getText().trim());
            trigger.addTrigger(action);
        }

        /**
         * Creates a square bumper with the given parameters and adds it to
         * gadgets.
         */
        @Override
        public void exitSBumper(BoardParser.SBumperContext ctx) {
            float[] args = new float[ctx.NUMBER().size()];
            for (int i = 0; i < args.length; i++)
                args[i] = Integer.parseInt(ctx.NUMBER(i).getText().trim());
            Bumper bumper = new Bumper(new Coords(args[0], args[1]), "SQUARE");
            gadgets.add(bumper);
            refs.put(ctx.NAME().getText().trim(), bumper);
        }

        /**
         * Creates a circular bumper with the given parameters and adds it to
         * gadgets.
         */
        @Override
        public void exitCBumper(BoardParser.CBumperContext ctx) {
            float[] args = new float[ctx.NUMBER().size()];
            for (int i = 0; i < args.length; i++)
                args[i] = Integer.parseInt(ctx.NUMBER(i).getText().trim());
            Bumper bumper = new Bumper(new Coords(args[0], args[1]), "CIRCLE");
            gadgets.add(bumper);
            refs.put(ctx.NAME().getText().trim(), bumper);
        }

        /**
         * Creates a triangular bumper with the given parameters and adds it to
         * gadgets.
         */
        @Override
        public void exitTBumper(BoardParser.TBumperContext ctx) {
            float[] args = new float[ctx.NUMBER().size()];
            for (int i = 0; i < args.length; i++)
                args[i] = Integer.parseInt(ctx.NUMBER(i).getText().trim());
            Bumper bumper = new Bumper(new Coords(args[0], args[1]),
                    "TRIANGLE", (int) args[2]);
            gadgets.add(bumper);
            refs.put(ctx.NAME().getText().trim(), bumper);
        }

        /**
         * Creates a flipper given the parameters and adding it to gadgets.
         */
        @Override
        public void exitFlipper(BoardParser.FlipperContext ctx) {
            String type = ctx.NAME(0).getText();
            type = type.substring(0, type.length() - "Flipper".length());
            type = type.toUpperCase();

            float[] args = new float[ctx.NUMBER().size()];
            for (int i = 0; i < args.length; i++)
                args[i] = Integer.parseInt(ctx.NUMBER(i).getText().trim());
            Flipper flipper = new Flipper(new Coords(args[0], args[1]), type,
                    (int) args[2]);
            gadgets.add(flipper);
            refs.put(ctx.NAME(1).getText().trim(), flipper);
        }

        /**
         * Creates the absorber with the given parameters, and adding it to
         * gadgets
         */
        @Override
        public void exitAbsorber(BoardParser.AbsorberContext ctx) {
            int[] args = new int[ctx.NUMBER().size()];
            for (int i = 0; i < args.length; i++)
                args[i] = Integer.parseInt(ctx.NUMBER(i).getText().trim());

            Absorber absorber = new Absorber(args[3], args[2], new Coords(
                    args[0], args[1]));
            gadgets.add(absorber);
            refs.put(ctx.NAME().getText().trim(), absorber);
        }

        /**
         * Creates the ball with the given parameters
         */
        @Override
        public void exitBall(BoardParser.BallContext ctx) {
            float[] args = new float[ctx.NUMBER().size()];
            for (int i = 0; i < args.length; i++)
                args[i] = Float.parseFloat(ctx.NUMBER(i).getText());
            Ball ball = new Ball(new Coords(args[0], args[1]), new Vect(
                    args[2], args[3]));
            balls.add(ball);
        }

        /**
         * Updates gravity.
         */
        @Override
        public void exitGravity(BoardParser.GravityContext ctx) {
            gravity = Double.parseDouble(ctx.NUMBER().getText().trim());
        }

        /**
         * Updates friction1.
         */
        @Override
        public void exitFriction1(BoardParser.Friction1Context ctx) {
            friction1 = Double.parseDouble(ctx.NUMBER().getText().trim());
        }

        /**
         * Updates friction2.
         */
        @Override
        public void exitFriction2(BoardParser.Friction2Context ctx) {
            friction2 = Double.parseDouble(ctx.NUMBER().getText().trim());
        }

        /**
         * Creates the board with the client, name, balls, and gadgets
         */
        @Override
        public void exitBoard(BoardParser.BoardContext ctx) {
            // do nothing, because the top of the stack should have the node
            // already in it
            String name = ctx.NAME() == null ? "" : ctx.NAME().getText();
            board = new Board(client, name, balls, gadgets, gravity, friction1,
                    friction2);
            assert board != null;
        }

        /**
         * @return the board
         */
        public Board getBoard() {
            return board;
        }
    }

    static class PrintEverythingListener extends BoardBaseListener {
        public void enterBoard(BoardParser.BoardContext ctx) {
            System.err.println("entering board " + ctx.getText());
        }

        public void exitBoard(BoardParser.BoardContext ctx) {
            System.err.println("exiting board " + ctx.getText());
        }

        public void enterBall(BoardParser.BallContext ctx) {
            System.err.println("entering ball " + ctx.getText());
        }

        public void exitBall(BoardParser.BallContext ctx) {
            System.err.println("exiting ball " + ctx.getText());
        }

        public void enterFlipper(BoardParser.FlipperContext ctx) {
            System.err.println("entering flipper " + ctx.getText());
        }

        public void exitFlipper(BoardParser.FlipperContext ctx) {
            System.err.println("exiting flipper " + ctx.getText());
        }

        public void enterBumper(BoardParser.BumperContext ctx) {
            System.err.println("entering bumper " + ctx.getText());
        }

        public void exitBumper(BoardParser.BumperContext ctx) {
            System.err.println("exiting bumper " + ctx.getText());
        }

        public void enterAbsorber(BoardParser.AbsorberContext ctx) {
            System.err.println("entering absorber " + ctx.getText());
        }

        public void exitAbsorber(BoardParser.AbsorberContext ctx) {
            System.err.println("exiting absorber " + ctx.getText());
        }
    }

}
