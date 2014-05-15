package pingball.ui.board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import pingball.board.LeftFlipper;

public class LeftFlipperPainter implements GadgetPainter {
    private final Color leftFlipperColor = Color.black;//new Color(0x23, 0x85, 0xe6);
    private LeftFlipper gadget;
    
    public LeftFlipperPainter(LeftFlipper gadget) {
        this.gadget = gadget;
    }


    @Override
    public void paint(Graphics2D g) {
        g.setColor(leftFlipperColor);
        g.setStroke(new BasicStroke(GraphicsConstants.STROKE_WIDTH));
        List<LineSegment> segments = gadget.getLineSegments();
        List<Circle> circles = gadget.getCircles();
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
        
        g.drawOval(
                GraphicsConstants.convertX(circles.get(0).getCenter().x() - 0.25),
                GraphicsConstants.convertY(circles.get(0).getCenter().y() - 0.25),
                (int)(GraphicsConstants.CELL_SIZE * 0.5),
                (int)(GraphicsConstants.CELL_SIZE * 0.5));
        g.drawOval(
                GraphicsConstants.convertX(circles.get(1).getCenter().x() - 0.25),
                GraphicsConstants.convertY(circles.get(1).getCenter().y() - 0.25),
                (int)(GraphicsConstants.CELL_SIZE * 0.5),
                (int)(GraphicsConstants.CELL_SIZE * 0.5));
        
        g.fillOval(GraphicsConstants.convertX(circles.get(0).getCenter().x())-2, GraphicsConstants.convertY(circles.get(0).getCenter().y())-2,
                3, 3);
        g.fillOval(GraphicsConstants.convertX(circles.get(1).getCenter().x())-2, GraphicsConstants.convertY(circles.get(1).getCenter().y())-2,
                3, 3);
        g.drawLine(GraphicsConstants.convertX(circles.get(0).getCenter().x()), GraphicsConstants.convertY(circles.get(0).getCenter().y()), GraphicsConstants.convertX(circles.get(1).getCenter().x()), GraphicsConstants.convertY(circles.get(1).getCenter().y()));
    }

}
