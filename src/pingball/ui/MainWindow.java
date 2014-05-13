package pingball.ui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFrame;

import pingball.board.Board;
import pingball.client.ClientController;

public class MainWindow extends JFrame implements KeyListener {
    GameDisplay display;
    private Board boardForKeys;
    private ClientController clientControl;

    public MainWindow(Board board, String host, int port, ClientController clientController, File file) {
        super("Pingball");
        addKeyListener(this);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        display = new GameDisplay(board);
        boardForKeys = board;
        clientControl = clientController;
        this.add(display, BorderLayout.CENTER);
        this.add(new DisplayMenu(this, board, host, port, file), BorderLayout.NORTH);
        this.pack();
        this.setVisible(true);
    }

    public void stopWindowPrinting() {
        clientControl.stop();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String keyName = e.getKeyText(e.getKeyCode());
        keyName = keyName.replaceAll(" ", "");
        keyName = keyName.toLowerCase();
        boardForKeys.handleKeyDown(keyName);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String keyName = e.getKeyText(e.getKeyCode());
        keyName = keyName.replaceAll(" ", "");
        keyName = keyName.toLowerCase();
        boardForKeys.handleKeyDown(keyName);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
