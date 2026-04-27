public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector() {
    }

    public static Vector add(Vector v1, Vector v2){
        return new Vector(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }

    public static Vector add(Vector v1, double scalar){
        return new Vector(v1.getX() + scalar, v1.getY() + scalar);
    }

    public static Vector subtract(Vector v1, Vector v2){
        return new Vector(v1.getX() - v2.getX(), v1.getY() - v2.getY());
    }

    public static Vector subtract(Vector v1, double scalar){
        return new Vector(v1.getX() - scalar, v1.getY() - scalar);
    }

    public static Vector multiply(Vector v1, Vector v2){
        return new Vector(v1.getX() * v2.getX(), v1.getY() * v2.getY());
    }

    public static Vector multiply(Vector v1, double scalar){
        return new Vector(v1.getX() * scalar, v1.getY() * scalar);
    }


    public static Vector divide(Vector v1, Vector v2){
        return new Vector(v1.getX() / v2.getX(), v1.getY() / v2.getY());
    }

    public static Vector divide(Vector v1, double scalar){
        return new Vector(v1.getX() / scalar, v1.getY() / scalar);
    }

    public static double normalize(Vector v){
        return Math.sqrt(Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2));
    }

    public static Vector negate(Vector v){
        return new Vector(-v.getX(), -v.getY());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
