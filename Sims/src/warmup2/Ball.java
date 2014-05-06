package warmup2;

import physics.*;

public class Ball {
    private int[] center;
    private Vect velocity;

    public Ball(int[] center, Vect velocity) {
        this.center = center;
        this.velocity = velocity;
    }

    public void move() {
        this.center[0] += this.velocity.x();
        this.center[1] += this.velocity.y();
        correctOutOfBounds();
    }

    public void correctOutOfBounds() {
        if (getX() <= 0) {
            center[0] = center[0] * -1 + 1;
            velocity = new Vect(-velocity.x(),velocity.y());
        } else if (getX() >= 20) {
            center[0] = 38 - center[0];
            velocity = new Vect(-velocity.x(),velocity.y());
        }

        if (getY() <= 0) {
            center[1] = center[1] * -1 + 1;
            velocity = new Vect(velocity.x(),-velocity.y());
        }
        if (getY() >= 20) {
            center[1] = 38 - center[1];
            velocity = new Vect(velocity.x(),-velocity.y());
        }
    }

    public int getX() {
        return center[0];
    }

    public int getY() {
        return center[1];
    }

}