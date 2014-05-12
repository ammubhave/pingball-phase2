package pingball.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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

    private JMenuItem loadBoard;
    private JMenuItem pause;
    private JMenuItem resume;
    private JMenuItem restart;
    private JMenuItem exit;

    private JMenuItem connectToServer;
    private JMenuItem disconnectFromServer;

    public DisplayMenu() {
        addMenusToMenuBar();
        addComponentsToGameMenu();
        addComponentsToServerMenu();
    }

    /**
     * Adds the loadBoard, pause, resume, restart, and exit options to the game
     * menu. The loadBoard option requires the user to specify a board. The
     * appropriate listeners are added and their methods are defined for all
     * these options.
     */
    public void addComponentsToGameMenu() {
        loadBoard = new JMenuItem("Load Board");
        pause = new JMenuItem("Pause");
        resume = new JMenuItem("Resume");
        restart = new JMenuItem("Restart");
        exit = new JMenuItem("Exit");

        game.add(loadBoard);
        game.add(pause);
        game.add(resume);
        game.add(restart);
        game.add(exit);

        loadBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        resume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    /**
     * Adds the connect to server and disconnect from server options to the
     * server menu. The connect to server option requires the user to specify a
     * host and port. The appropriate listeners are added and their methods are
     * defined for all these options.
     */
    public void addComponentsToServerMenu() {
        connectToServer = new JMenuItem("Connect to Server");
        disconnectFromServer = new JMenuItem("Disconnect from Server");

        server.add(connectToServer);
        server.add(disconnectFromServer);

        connectToServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        disconnectFromServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    /** Adds the game menu, server menu, and help menu to the menu bar. */
    public void addMenusToMenuBar() {
        game = new JMenu("Game");
        server = new JMenu("Server");
        this.add(game);
        this.add(server);
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

    /*
     * public static void main(String[] args) { SwingUtilities.invokeLater(new
     * Runnable() { public void run() { DisplayMenu main = new DisplayMenu();
     * main.setSize(400, 400); main.setVisible(true);
     * 
     * } }); }
     */

}
