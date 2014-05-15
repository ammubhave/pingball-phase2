package pingball.ui.board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import pingball.board.OuterWall;
import pingball.board.OuterWall.OuterWallsOrientation;

public class OuterWallPainter implements GadgetPainter {
    private final Color wallColor = new Color(0xd8, 0xec, 0xff);
    
    private OuterWall gadget;
    
    public OuterWallPainter(OuterWall gadget) {
        this.gadget = gadget;
    }

    @Override
    public void paint(final Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(GraphicsConstants.STROKE_WIDTH));
        if (gadget.getNeighborName() != null) {
            char label[] = gadget.getNeighborName().toCharArray();
            
            int nameStartIndex = (int) (11 - (double) label.length / 2.0);
            double px = gadget.getX();
            double py = gadget.getY();

            if (gadget.getOrientation() == OuterWallsOrientation.HORIZONTAL) {
                for (int x = nameStartIndex; x < nameStartIndex + label.length; x++)
                    g.drawChars(label, x - nameStartIndex, 1, GraphicsConstants.convertX(px + x - 1), GraphicsConstants.convertY(py - 0.3));   
                
                g.setColor(wallColor);
                g.drawRect((int)(gadget.getX() * GraphicsConstants.CELL_SIZE),
                        (int)(gadget.getY() * GraphicsConstants.CELL_SIZE), 
                        GraphicsConstants.SIZE,
                        GraphicsConstants.CELL_SIZE);
                
            } else {
                for (int y = nameStartIndex; y < nameStartIndex + label.length; y++)
                    g.drawChars(label, y - nameStartIndex, 1, GraphicsConstants.convertX(px - 0.75), GraphicsConstants.convertY(py + y - 1));

                g.setColor(wallColor);
                g.drawRect((int)(gadget.getX() * GraphicsConstants.CELL_SIZE),
                        (int)(gadget.getY() * GraphicsConstants.CELL_SIZE), 
                        GraphicsConstants.CELL_SIZE,
                        GraphicsConstants.SIZE);
            }
           
        } else {                
            g.setColor(wallColor);
            if (gadget.getOrientation() == OuterWallsOrientation.HORIZONTAL)
                g.fillRect((int)(gadget.getX() * GraphicsConstants.CELL_SIZE),
                           (int)(gadget.getY() * GraphicsConstants.CELL_SIZE), 
                           GraphicsConstants.SIZE,
                           GraphicsConstants.CELL_SIZE);
            if (gadget.getOrientation() == OuterWallsOrientation.VERTICAL)
                g.fillRect((int)(gadget.getX() * GraphicsConstants.CELL_SIZE),
                           (int)(gadget.getY() * GraphicsConstants.CELL_SIZE), 
                           GraphicsConstants.CELL_SIZE,
                           GraphicsConstants.SIZE);
        }
        
    }
    
}
