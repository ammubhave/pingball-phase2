package pingball.ui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;

import physics.Vect;
import pingball.board.Ball;
import pingball.board.Board;
import pingball.board.SquareBumper;
import pingball.board.TriangularBumper;
import pingball.board.TriangularBumper.TriangularBumperOrientation;
import pingball.client.ClientController;
import pingball.ui.board.BallPainter;
import pingball.ui.board.GraphicsConstants;
import pingball.ui.board.SquareBumperPainter;
import pingball.ui.board.TriangularBumperPainter;

/**
 * The MainWindow class represents the graphical user interface for the Pingball
 * game. It contains a menu bar with two menus: the game menu and the server
 * menu. The game menu has six menu items: load board, pause, resume, restart,
 * exit, and exit all. The server menu has two menu items: connect to a server
 * by specifying the host and port number and disconnecting from a server.
 * 
 * Manual Testing Strategy: Each of these menu items were manually tested in
 * different orders multiple times to ensure that the program was functioning
 * correctly. For instance, multiple boards were loaded by the user and made
 * sure that they all connected properly. Different port numbers and host names
 * were also inputed to make sure that the user could connect to the server
 * properly. The movement of the ball and the gadgets on the board was visually
 * inspected.
 */

public class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    GameDisplay display;
    private Board boardForKeys;
    private ClientController clientControl;
    private GadgetType gadgetChosen = GadgetType.BALL;

    public MainWindow(final Board board, String host, int port, ClientController clientController, File file) {
        super("Pingball");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        display = new GameDisplay(board);
        boardForKeys = board;
        clientControl = clientController;
        this.add(display, BorderLayout.CENTER);
        this.add(new DisplayMenu(this, board, host, port, file), BorderLayout.NORTH);
        this.pack();
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                clientControl.stop();
            }
        });

        class MyKeyListener implements KeyListener {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyPressed(KeyEvent e) {
                String keyName = KeyEvent.getKeyText(e.getKeyCode());
                keyName = keyName.replaceAll(" ", "");
                keyName = keyName.toLowerCase();
                boardForKeys.handleKeyDown(keyName);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String keyName = KeyEvent.getKeyText(e.getKeyCode());
                keyName = keyName.replaceAll(" ", "");
                keyName = keyName.toLowerCase();
                boardForKeys.handleKeyDown(keyName);
            }
        }

        class MyMouseListener implements MouseListener {

            @Override
            public void mouseClicked(MouseEvent e) {
                switch (gadgetChosen) {
                case BALL:
                    Ball ball = new Ball(String.valueOf(e.hashCode()), new Vect(
                            GraphicsConstants.convertFromX(e.getX()) + 0.25,
                            GraphicsConstants.convertFromY(e.getY()) + 0.25), new Vect(0, 0));
                    board.addBall(ball);
                    board.addGadgetPainter(new BallPainter(ball));
                    break;
                case SQUARE_BUMPER:
                    SquareBumper squareBumper = new SquareBumper(new Vect(
                            GraphicsConstants.convertFromX(e.getX()),
                            GraphicsConstants.convertFromY(e.getY())), String.valueOf(e.hashCode()));
                    board.addGadget(squareBumper);
                    board.addGadgetPainter(new SquareBumperPainter(squareBumper));
                    break;
                case TRIANGULAR_BUMPER_BOTTOM_LEFT:
                    TriangularBumper triangularBumperBL = new TriangularBumper(new Vect(
                            GraphicsConstants.convertFromX(e.getX()),
                            GraphicsConstants.convertFromY(e.getY())),
                            TriangularBumperOrientation.BOTTOM_LEFT,
                            String.valueOf(e.hashCode()));
                    board.addGadget(triangularBumperBL);
                    board.addGadgetPainter(new TriangularBumperPainter(triangularBumperBL));
                    break;
                case TRIANGULAR_BUMPER_BOTTOM_RIGHT:
                    TriangularBumper triangularBumperBR = new TriangularBumper(new Vect(
                            GraphicsConstants.convertFromX(e.getX()),
                            GraphicsConstants.convertFromY(e.getY())),
                            TriangularBumperOrientation.BOTTOM_RIGHT,
                            String.valueOf(e.hashCode()));
                    board.addGadget(triangularBumperBR);
                    board.addGadgetPainter(new TriangularBumperPainter(triangularBumperBR));
                    break;
                case TRIANGULAR_BUMPER_TOP_LEFT:
                    TriangularBumper triangularBumperTL = new TriangularBumper(new Vect(
                            GraphicsConstants.convertFromX(e.getX()),
                            GraphicsConstants.convertFromY(e.getY())),
                            TriangularBumperOrientation.TOP_LEFT,
                            String.valueOf(e.hashCode()));
                    board.addGadget(triangularBumperTL);
                    board.addGadgetPainter(new TriangularBumperPainter(triangularBumperTL));
                    break;
                case TRIANGULAR_BUMPER_TOP_RIGHT:
                    TriangularBumper triangularBumperTR = new TriangularBumper(new Vect(
                            GraphicsConstants.convertFromX(e.getX()),
                            GraphicsConstants.convertFromY(e.getY())),
                            TriangularBumperOrientation.TOP_RIGHT,
                            String.valueOf(e.hashCode()));
                    board.addGadget(triangularBumperTR);
                    board.addGadgetPainter(new TriangularBumperPainter(triangularBumperTR));
                    break;
                default:
                    break;                
                }                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        }

        this.addKeyListener(new MagicKeyListener(new MyKeyListener()));
        this.addMouseListener(new MyMouseListener());
    }

    public void startController() {
        clientControl.start();
    }

    public void setGadgetType(GadgetType gadgetChosen) {
        this.gadgetChosen = gadgetChosen;
    }

    public void stopController() {
        clientControl.stop();
    }
}
