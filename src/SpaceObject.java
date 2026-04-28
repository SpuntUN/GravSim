import java.awt.*;

public class SpaceObject {
    private String name;
    private boolean massless;
    private double mass;
    private Vector position;
    private Vector velocity;
    private Vector force;

    private Color color;
    private double radius;

    public SpaceObject(String name, boolean massless, double mass, Vector position, Vector velocity, double radius) {
        this.name = name;
        this.massless = massless;
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
        this.force = new Vector(0, 0);
        color = Color.WHITE;

    }

    public void update(double time){
        Vector acceleration = Vector.divide(force, mass);

        velocity = Vector.add(velocity, Vector.multiply(acceleration, time));
        position = Vector.add(position, Vector.multiply(velocity, time));
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMassless() {
        return massless;
    }

    public void setMassless(boolean massless) {
        this.massless = massless;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public Vector getForce() {
        return force;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setForce(Vector force) {
        this.force = force;
    }

    public void addForce(Vector force) {
        this.force = Vector.add(this.force, force);
    }
}
