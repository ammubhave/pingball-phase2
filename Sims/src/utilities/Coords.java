package utilities;

public class Coords {

    private double x;
    private double y;
    
    /**
     * Creates an instance of a coordinate object.
     * 
     * @param x 
     *          the x-coordinate of the pair. 
     *          Should be >= 0.
     * @param y the y-coordinate of the pair.
     *          Should be >= 0.
     */
    public Coords(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Returns the x-coordinate of the current coordinate object.
     * @return a double representing the x-coordinate of the current coordinate object.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of the current coordinate object.
     * @return a double representing the y-coordinate of the current coordinate object.
     */    
    public double getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object other) {
        if (other.getClass().equals(Coords.class)) {
            Coords otherCoord = (Coords) other;
            return getX() == otherCoord.getX() && getY() == otherCoord.getY();
        }
        return false;
    }

    /**
     * Gets the distance between this and other point.
     * 
     * @param other
     *            the other coords object.
     * @return distance between the this and other.
     */
    public double getDistance(Coords other) {
        double x0 = this.getX();
        double x1 = other.getX();

        double y0 = this.getY();
        double y1 = other.getY();

        double distance = (double) Math.sqrt(Math.pow((x1 - x0), 2)
                + Math.pow((y1 - y0), 2));
        return distance;
    }

    /**
     * Gets the angle made by a straight line joining the current point and
     * other with the horizontal in the range 0 to 2pi.
     * 
     * 
     * @param other
     *            The other coords object.
     * @return angle made by a straight line joining the current point and other
     *         with the horizontal in the range 0 to 2pi.
     */
    public double getAngleToHorizontal(Coords other) {
        double x0 = this.getX();
        double x1 = other.getX();

        double y0 = this.getY();
        double y1 = other.getY();

        double angle = Math.atan2(y1 - y0, x1 - x0);
        if (angle < 0)
            angle = 2 * Math.PI + angle;

        return angle;
    }

    @Override
    public String toString() {
        return this.x + ", " + this.y;
    }
    
    /**
     * Gets a new coordinate object identical to the current coordinate object
     * except the new one will have rounded values for x and y coordinates.
     * 
     * @return a new coordinate object whose x and y coordinates are round values
     *          for x and y coordinate.
     */
    public Coords round() {
        double x = Math.round(this.x);
        double y = Math.round(this.y);
        return new Coords(x, y);
    }
    
    /**
     * Gets a new coordinate object identical to the current coordinate object
     * except the new one will have floor values for x and y coordinates.
     * 
     * @return a new coordinate object whose x and y coordinates are floor values
     *          for x and y coordinate.
     */
    public Coords floor() {
        double x = Math.floor(this.x);
        double y = Math.floor(this.y);
        return new Coords(x, y);
    }
    
    /**
     * Gets a new coordinate object identical to the current coordinate object
     * except the new one will have ceil values for x and y coordinates.
     * 
     * @return a new coordinate object whose x and y coordinates are ceil values
     *          for x and y coordinate.
     */
    public Coords ceil() {
        double x = Math.ceil(this.x);
        double y = Math.ceil(this.y);
        return new Coords(x, y);
    }

}
