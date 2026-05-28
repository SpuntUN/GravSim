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

    public SpaceObject(String name, boolean massless, double mass, Vector position, Vector velocity, double radius, Color color) {
        this.name = name;
        this.massless = massless;
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
        this.force = new Vector(0, 0);
        this.color = color;

    }

    public SpaceObject(SpaceObject spaceObject) {
        this.name = spaceObject.name;
        this.massless = spaceObject.massless;
        this.mass = spaceObject.mass;
        this.radius = spaceObject.radius;
        this.color = spaceObject.color;

        this.position = new Vector(spaceObject.position);
        this.velocity = new Vector(spaceObject.velocity);

        this.force = new Vector(spaceObject.force);
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

    public String getMassString() {
        double mass = getMass();

        final double EARTH_MASS = 5.972e24;
        final double SOLAR_MASS = 1.98847e30;

        if (mass >= SOLAR_MASS/10) {
            double solar = mass / SOLAR_MASS;
            return String.format("%.4f Msun", solar);

        } else if (mass >= EARTH_MASS/10) {
            double earth = mass / EARTH_MASS;
            return String.format("%.4f Mearth", earth);

        } else {
            return String.format("%.4f kg", mass);
        }
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Vector getPosition() {
        return position;
    }

    public String getPositionString() {
        double distance = getDistance();

        double x = position.getX();
        double y = position.getY();


        final double KM_THRESHOLD = 1e3;
        final double AU_THRESHOLD = 1.496e11;

        if (distance >= AU_THRESHOLD/10) {
            return String.format("[%.4f, %.4f] AU", x / AU_THRESHOLD, y / AU_THRESHOLD);

        } else if (distance >= KM_THRESHOLD) {
            return String.format("[%.4f, %.4f] km", x / 1000.0, y / 1000.0);

        } else {
            return String.format("[%.4f, %.4f] m", x, y);
        }
    }


    public double getDistance(){
        return Vector.normalize(position);
    }

    public String getDistanceString(){
        double distance = getDistance();

        final double KM_THRESHOLD = 1e3;
        final double AU_THRESHOLD = 1.496e11;

        if (distance >= AU_THRESHOLD/10) {
            return String.format("%.4f AU", distance/AU_THRESHOLD);

        } else if (distance >= KM_THRESHOLD) {
            return String.format("%.4f km", distance/KM_THRESHOLD);

        } else {
            return String.format("%.4f m", distance);
        }
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public String getVelocityString() {
        double speed = getSpeed();

        double x = velocity.getX();
        double y = velocity.getY();

        if (speed >= 1000.0) {
            double xKm = x / 1000.0;
            double yKm = y / 1000.0;
            return String.format("[%.4f, %.4f] km/s", xKm, yKm);
        } else {
            return String.format("[%.4f, %.4f] m/s", x, y);
        }
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public double getSpeed(){
        return Vector.normalize(velocity);
    }

    public String getSpeedString() {
        double speed = getSpeed();

        if (speed >= 1000.0) {
            return String.format("%.4f km/s", speed / 1000.0);
        } else {
            return String.format("%.4f m/s", speed);
        }
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
