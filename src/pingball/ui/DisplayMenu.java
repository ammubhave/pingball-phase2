package pingball.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * The DisplayMenu class represents menu bar for the Pingball game. It contains
 * two menus: a game menu and a help menu. The game menu contains options for
 * the user to load the board from a file, pause the game, resume the game,
 * restart the game from the initial state of the board, or exit the program.
 * The server menu contains two options: connect to a server or disconnect from
 * it.
 */

public class DisplayMenu extends JMenuBar implements MouseListener {

    private JMenu game;
    private JMenu server;
    private JMenu help;

    private JLabel loadBoard;
    private JLabel pause;
    private JLabel resume;
    private JLabel restart;
    private JLabel exit;

    private JLabel connectToServer;
    private JLabel disconnectFromServer;

    public DisplayMenu() {

    }

    /**
     * Adds the loadBoard, pause, resume, restart, and exit options to the game
     * menu. The loadBoard option requires the user to specify a board. The
     * appropriate listeners are added and their methods are defined for all
     * these options.
     */
    public void addComponentsToGameMenu() {

    }

    /**
     * Adds the connect to server and disconnect from server options to the
     * server menu. The connect to server option requires the user to specify a
     * host and port. The appropriate listeners are added and their methods are
     * defined for all these options.
     */
    public void addComponentsToServerMenu() {

    }

    /**
     * Adds the directions option to the help menu. The appropriate listeners
     * are added and their methods are defined for all these options.
     */
    public void addComponentsToHelpMenu() {

    }

    /** Adds the game menu, server menu, and help menu to the menu bar. */
    public void addMenusToMenuBar() {

    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}
