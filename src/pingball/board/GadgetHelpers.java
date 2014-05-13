package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;

public class GadgetHelpers {    
    public static double leastCollisionTime(List<LineSegment> lines, List<Circle> circles, Ball ball) {
        if (lines == null) lines = new ArrayList<LineSegment>();
        if (circles == null) circles = new ArrayList<Circle>();
        
        double smallestTime = Double.POSITIVE_INFINITY;
        for (LineSegment ls : lines) {
            double time = Geometry.timeUntilWallCollision(ls, ball.getCircle(), ball.getVelocity());
            if (time <= smallestTime) {
                smallestTime = time;
            }
        }
        for (Circle circ : circles) {
            double time = Geometry.timeUntilCircleCollision(circ, ball.getCircle(), ball.getVelocity());
            if (time <= smallestTime) {
                smallestTime = time;
            }
        }
        return smallestTime;
    }

    public static void reflectBall(List<LineSegment> lines, List<Circle> circles, Ball ball, double reflectionCoefficient) {
        if (lines == null) lines = new ArrayList<LineSegment>();
        if (circles == null) circles = new ArrayList<Circle>();
        
        double smallestTimeWall = Double.POSITIVE_INFINITY;
        LineSegment smallestWall = null;
        double timeToWall = 0;
        for (LineSegment ls : lines) {
            timeToWall = Geometry.timeUntilWallCollision(ls, ball.getCircle(), ball.getVelocity());
            if (timeToWall <= smallestTimeWall) {
                smallestTimeWall = timeToWall;
                smallestWall = ls;
            }
        }
        Circle smallestCircle = null;
        double smallestTimeCircle = Double.POSITIVE_INFINITY;
        double timeToCircle = 0;
        for (Circle circ : circles) {
            timeToCircle = Geometry.timeUntilCircleCollision(circ, ball.getCircle(), ball.getVelocity());
            if (timeToCircle <= smallestTimeCircle) {
                smallestTimeCircle = timeToCircle;
                smallestCircle = circ;
            }
        }
        if (smallestTimeWall < smallestTimeCircle) {
            ball.changeVelocity(Geometry.reflectWall(smallestWall, ball.getVelocity(), reflectionCoefficient));
        } else {
            ball.changeVelocity(Geometry.reflectCircle(smallestCircle.getCenter(), ball.getPos(), ball.getVelocity(), reflectionCoefficient));
        }
    }
    
    public static void reflectBall(List<LineSegment> lines, List<Circle> circles, Ball ball) {
        reflectBall(lines, circles, ball, 1);
    }
}
