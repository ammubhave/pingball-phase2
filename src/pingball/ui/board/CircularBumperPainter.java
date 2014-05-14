package pingball.ui.board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import pingball.board.CircularBumper;

public class CircularBumperPainter implements GadgetPainter {
    private final Color circularBumperColor = Color.YELLOW;
    
    private CircularBumper gadget;
    
    public CircularBumperPainter(CircularBumper gadget) {
        this.gadget = gadget;
    }

    @Override
    public void paint(Graphics2D g) {       
        g.setColor(circularBumperColor);
        g.setStroke(new BasicStroke(GraphicsConstants.STROKE_WIDTH));
        
        g.fillOval(GraphicsConstants.convertX(this.gadget.getX()-0.5),
                   GraphicsConstants.convertY(this.gadget.getY()-0.5),
                   GraphicsConstants.CELL_SIZE,
                   GraphicsConstants.CELL_SIZE);
    }

}
