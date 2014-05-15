package pingball.ui.board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import pingball.board.CircularBumper;

public class CircularBumperPainter implements GadgetPainter {
    private final Color circularBumperColor = new Color(0, 0x89, 0x30);;//. new Color(0x23, 0x85, 0xe6);
    
    private CircularBumper gadget;
    
    public CircularBumperPainter(CircularBumper gadget) {
        this.gadget = gadget;
    }

    @Override
    public void paint(Graphics2D g) {       
        g.setColor(circularBumperColor);
        g.setStroke(new BasicStroke(GraphicsConstants.STROKE_WIDTH));
        
        g.drawOval(GraphicsConstants.convertX(this.gadget.getX()),
                   GraphicsConstants.convertY(this.gadget.getY()),
                   GraphicsConstants.CELL_SIZE,
                   GraphicsConstants.CELL_SIZE);
        
        g.drawLine(GraphicsConstants.convertX(this.gadget.getX() + 0.5),
                GraphicsConstants.convertY(this.gadget.getY() + 0.25), 
                GraphicsConstants.convertX(this.gadget.getX() + 0.5),
                GraphicsConstants.convertY(this.gadget.getY() + 0.75));
        
        g.drawLine(GraphicsConstants.convertX(this.gadget.getX() + 0.25),
                GraphicsConstants.convertY(this.gadget.getY() + 0.5), 
                GraphicsConstants.convertX(this.gadget.getX() + 0.75),
                GraphicsConstants.convertY(this.gadget.getY() + 0.5));
    }

}
