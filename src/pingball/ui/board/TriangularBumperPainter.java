package pingball.ui.board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import physics.LineSegment;
import pingball.board.TriangularBumper;

public class TriangularBumperPainter implements GadgetPainter {
    private final Color triangularBumperColor = Color.GREEN;
    private TriangularBumper gadget;
    
    public TriangularBumperPainter(TriangularBumper gadget) {
        this.gadget = gadget;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(triangularBumperColor);
        g.setStroke(new BasicStroke(GraphicsConstants.STROKE_WIDTH));
        List<LineSegment> segments = gadget.getLineSegments();
        g.drawLine(
                GraphicsConstants.convertX(segments.get(0).p1().x()),
                GraphicsConstants.convertY(segments.get(0).p1().y()),
                GraphicsConstants.convertX(segments.get(0).p2().x()),
                GraphicsConstants.convertY(segments.get(0).p2().y()));
        g.drawLine(
                GraphicsConstants.convertX(segments.get(1).p1().x()),
                GraphicsConstants.convertY(segments.get(1).p1().y()),
                GraphicsConstants.convertX(segments.get(1).p2().x()),
                GraphicsConstants.convertY(segments.get(1).p2().y()));
        g.drawLine(
                GraphicsConstants.convertX(segments.get(2).p1().x()),
                GraphicsConstants.convertY(segments.get(2).p1().y()),
                GraphicsConstants.convertX(segments.get(2).p2().x()),
                GraphicsConstants.convertY(segments.get(2).p2().y()));
       /*         new int[] { 
                        GraphicsConstants.convertX(segments.get(0).p1().x()),
                        GraphicsConstants.convertX(segments.get(1).p1().x()),
                        GraphicsConstants.convertX(segments.get(2).p1().x())
                        },
                new int[] { 
                        GraphicsConstants.convertY(segments.get(0).p1().y()),
                        GraphicsConstants.convertY(segments.get(1).p1().y()),
                        GraphicsConstants.convertY(segments.get(2).p1().y())
                        },
                   2);*/
    }
}
