package pingball.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import pingball.board.Board;
import pingball.client.ClientController;
import pingball.parser.BoardBuilder;

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

    private MainWindow mainWindow;
    private Board gameBoard;
    private String gameHost;
    private int gamePort;
    private File gameFile;

    public DisplayMenu(MainWindow window, Board board, String host, int port, File file) {
        mainWindow = window;
        gameBoard = board;
        gameFile = file;

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
                JFileChooser boardLoad = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("Pingball Board File", new String[] {"pb"});
                boardLoad.setFileFilter(filter);
                boardLoad.addChoosableFileFilter(filter);
                int returnVal = boardLoad.showOpenDialog(loadBoard);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = boardLoad.getSelectedFile();
                    try {
                        final Board board = BoardBuilder.buildBoard(file);
                        ClientController controller = new ClientController(board, gameHost, gamePort, file);
                        controller.start();
                        mainWindow.stopController();
                        mainWindow.dispose();
                    } catch (IOException i) {
                        i.printStackTrace();
                    }
                }
            }
        });

        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                mainWindow.stopController();
            }
        });

        resume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                mainWindow.startController();
            }
        });

        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    gameBoard = BoardBuilder.buildBoard(gameFile);
                    ClientController controller = new ClientController(gameBoard, gameHost, gamePort, gameFile);
                    controller.start();
                    mainWindow.stopController();
                    mainWindow.dispose();
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
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

}
