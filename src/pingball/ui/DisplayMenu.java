package pingball.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

public class DisplayMenu extends JMenuBar {

    private JMenu game;
    private JMenu server;
    private JMenu addGadget;

    private JMenuItem loadBoard;
    private JMenuItem pause;
    private JMenuItem resume;
    private JMenuItem restart;
    private JMenuItem exit;
    private JMenuItem exitAll;

    private JMenuItem squareBumper;
    private JMenuItem circleBumper;
    private JMenuItem triangularBumperBottomRight;
    private JMenuItem triangularBumperBottomLeft;
    private JMenuItem triangularBumperTopRight;
    private JMenuItem triangularBumperTopLeft;
    private JMenuItem portal;

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
        addComponentsToAddGadgetsMenu();
    }

    public void addComponentsToAddGadgetsMenu() {
        squareBumper = new JMenuItem("Square Bumper");
        circleBumper = new JMenuItem("Circular Bumper");
        triangularBumperBottomRight = new JMenuItem("Triangular Bumper Bottom Right");
        triangularBumperBottomLeft = new JMenuItem("Triangular Bumper Bottom Left");
        triangularBumperTopRight = new JMenuItem("Triangular Bumper Top Right");
        triangularBumperTopLeft = new JMenuItem("Triangular Bumper Top Left");
        portal = new JMenuItem("Portal");

        addGadget.add(squareBumper);
        addGadget.add(circleBumper);
        addGadget.add(triangularBumperBottomRight);
        addGadget.add(triangularBumperBottomLeft);
        addGadget.add(triangularBumperTopRight);
        addGadget.add(triangularBumperTopLeft);
        addGadget.add(portal);
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
        exitAll = new JMenuItem("Exit All");

        game.add(loadBoard);
        game.add(pause);
        game.add(resume);
        game.add(restart);
        game.add(exit);
        game.add(exitAll);

        loadBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser boardLoad = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("Pingball Board File", new String[] { "pb" });
                boardLoad.setFileFilter(filter);
                boardLoad.addChoosableFileFilter(filter);
                int returnVal = boardLoad.showOpenDialog(loadBoard);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = boardLoad.getSelectedFile();
                    try {
                        final Board board = BoardBuilder.buildBoard(file);
                        ClientController controller = new ClientController(board, gameHost, gamePort, file);
                        controller.start();
                        // mainWindow.stopController();
                        // mainWindow.dispose();
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
                mainWindow.dispose();
            }
        });

        exitAll.addActionListener(new ActionListener() {
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
                try {
                    JPanel serverPanel = new JPanel();

                    JLabel hostRequest = new JLabel("Enter the host name: ");
                    JLabel portRequest = new JLabel("Enter the port number: ");

                    JTextField hostInput = new JTextField("localhost");
                    JTextField portInput = new JTextField("10987");

                    serverPanel.setLayout(new GridLayout(0, 2));
                    serverPanel.add(hostRequest);
                    serverPanel.add(hostInput);
                    serverPanel.add(portRequest);
                    serverPanel.add(portInput);

                    int result = JOptionPane.showConfirmDialog(connectToServer, serverPanel,
                            "Input to Connect to Server: ", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        try {
                            gamePort = Integer.parseInt(portInput.getText());
                            gameHost = hostInput.getText();
                            ClientController controller = new ClientController(gameBoard, gameHost, gamePort, gameFile);
                            controller.start();
                            mainWindow.stopController();
                            mainWindow.dispose();
                        } catch (Exception u) {

                        }
                    }

                } catch (Exception i) {
                    // i.printStackTrace();
                }
            }
        });

        disconnectFromServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientController controller = new ClientController(gameBoard, null, 0, gameFile);
                    controller.start();
                    mainWindow.stopController();
                    mainWindow.dispose();
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
        });
    }

    /** Adds the game menu, server menu, and help menu to the menu bar. */
    public void addMenusToMenuBar() {
        game = new JMenu("Game");
        server = new JMenu("Server");
        addGadget = new JMenu("Add Gadget");

        this.add(game);
        this.add(server);
        this.add(addGadget);
    }

}
