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
        Vector v3 = new Vector();
        v3.setX(v1.getX()+v2.getX());
        v3.setY(v1.getY()+v2.getY());

        return v3;
    }

    public static Vector subtract(Vector v1, Vector v2){
        Vector v3 = new Vector();
        v3.setX(v1.getX()-v2.getX());
        v3.setY(v1.getY()-v2.getY());

        return v3;
    }

    public static Vector multiply(Vector v1, Vector v2){
        Vector v3 = new Vector();
        v3.setX(v1.getX()*v2.getX());
        v3.setY(v1.getY()*v2.getY());

        return v3;
    }

    public static Vector divide(Vector v1, Vector v2){
        Vector v3 = new Vector();
        v3.setX(v1.getX()/v2.getX());
        v3.setY(v1.getY()/v2.getY());

        return v3;
    }

    public static double normalize(Vector v){
        return Math.sqrt(Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2));
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
