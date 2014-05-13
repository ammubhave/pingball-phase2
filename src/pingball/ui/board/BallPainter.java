package pingball.ui.board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import pingball.board.Ball;

public class BallPainter implements GadgetPainter {
    private final Color ballColor = Color.RED;
    private final int ballDiameter = GraphicsConstants.CELL_SIZE/2; // in Pixels
    
    private Ball ball;
    
    public BallPainter(Ball ball) {
        this.ball = ball;
    }
    
    public Ball getBall() {
        return ball;
    }

    @Override
    public void paint(final Graphics2D g) {
        final Point ballpt = new Point((int)((ball.getCircle().getCenter().x()-0.25+1)*GraphicsConstants.CELL_SIZE), (int)((ball.getCircle().getCenter().y()-0.25+1)*GraphicsConstants.CELL_SIZE));
        
        g.setColor(ballColor);
        g.setStroke(new BasicStroke(GraphicsConstants.STROKE_WIDTH));
        
        g.fillOval(ballpt.x, ballpt.y,
                       ballDiameter,
                       ballDiameter);
    }
}
