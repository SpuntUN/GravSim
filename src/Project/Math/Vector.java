package Project.Math;

/**
 * Represents a 2D vector with Cartesian coordinates (x, y)
 * and provides static methods for vector arithmetic.
 * @author Matěj Švec
 */

public class Vector {
    private double x;
    private double y;

    /**
     * Constructs a new Vector with the specified x and y coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a default Vector with coordinates initialized to (0.0, 0.0).
     */
    public Vector() {
    }

    /**
     * Constructs a new Vector as a copy of an existing Vector.
     * @param vector the vector to copy
     */
    public Vector(Vector vector){
        this.x = vector.getX();
        this.y = vector.getY();
    }

    /**
     * Adds to Vectors.
     * @param v1 first Vector
     * @param v2 second Vector
     * @return a new Vector with a sum of values of v1 and v2
     */
    public static Vector add(Vector v1, Vector v2){
        return new Vector(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }

    /**
     * Adds a Vector and a double.
     * @param v1 Vector
     * @param scalar double
     * @return a new Vector with the scalar added to x and y
     */
    public static Vector add(Vector v1, double scalar){
        return new Vector(v1.getX() + scalar, v1.getY() + scalar);
    }

    /**
     * Subtracts to Vectors.
     * @param v1 first Vector
     * @param v2 second Vector
     * @return a new Vector with a difference of values of v1 and v2
     */
    public static Vector subtract(Vector v1, Vector v2){
        return new Vector(v1.getX() - v2.getX(), v1.getY() - v2.getY());
    }

    /**
     * Subtracts a Vector and a double.
     * @param v1 Vector
     * @param scalar double
     * @return a new Vector with the scalar subtracted from x and y
     */
    public static Vector subtract(Vector v1, double scalar){
        return new Vector(v1.getX() - scalar, v1.getY() - scalar);
    }

    /**
     * Multiplies two Vectors component-wise.
     * @param v1 first Vector
     * @param v2 second Vector
     * @return a new Vector representing the component-wise product
     */
    public static Vector multiply(Vector v1, Vector v2){
        return new Vector(v1.getX() * v2.getX(), v1.getY() * v2.getY());
    }

    /**
     * Multiplies a Vector by a scalar.
     * @param v1 Vector to scale
     * @param scalar the scaling factor
     * @return a new scaled Vector
     */
    public static Vector multiply(Vector v1, double scalar){
        return new Vector(v1.getX() * scalar, v1.getY() * scalar);
    }

    /**
     * Divides two Vectors component-wise.
     * @param v1 the dividend Vector
     * @param v2 the divisor Vector
     * @return a new Vector representing the component-wise quotient
     */
    public static Vector divide(Vector v1, Vector v2){
        return new Vector(v1.getX() / v2.getX(), v1.getY() / v2.getY());
    }

    /**
     * Divides a Vector by a scalar.
     * @param v1 the dividend Vector
     * @param scalar the divisor scalar
     * @return a new divided Vector
     */
    public static Vector divide(Vector v1, double scalar){
        return new Vector(v1.getX() / scalar, v1.getY() / scalar);
    }

    /**
     * Calculates the magnitude (length) of a Vector.
     * @param v Vector
     * @return the magnitude of the Vector as a double
     */
    public static double normalize(Vector v){
        return Math.sqrt(Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2));
    }

    /**
     * Inverses both values of the Vector
     * @param v Vector
     * @return inverted Vector
     */
    public static Vector negate(Vector v){
        return new Vector(-v.getX(), -v.getY());
    }

    /**
     * Converts polar coordinates to Cartesian coordinates using a modified custom formula
     * (offsets the angle by 45 radians and inverts the resulting X/Y roles).
     * @param magnitude the magnitude/length of the vector
     * @param angle the angle in radians
     * @return a new Vector mapped to the calculated Cartesian coordinates
     */
    public static Vector polarToCartesianConversion(double magnitude, double angle){
        double angleRadians = Math.toRadians(angle+90);
        double x = magnitude * Math.cos(angleRadians);
        double y = magnitude * Math.sin(angleRadians);

        return new Vector(y, x);
    }

    /**
     * Gets the x coordinate of the vector.
     * @return the x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x coordinate of the vector.
     * @param x the new x coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the y coordinate of the vector.
     * @return the y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y coordinate of the vector.
     * @param y the new y coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Returns a string representation of the vector in the format "[x; y]".
     * @return string representation of the vector
     */
    @Override
    public String toString() {
        return "["+x+"; "+y+"]";
    }
}