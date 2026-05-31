package Project.Math;

import java.util.ArrayList;

/**
 * Holds {@link SpaceObject} and handles physics interactions between them.
 */
public class Space {
    private ArrayList<SpaceObject> spaceObjects;
    private int scale;
    private final double G = 6.67430e-11;

    public Space() {
        spaceObjects = new ArrayList<>();
    }

    public void update(double time){
        ArrayList<SpaceObject> massBodies = new ArrayList<>();
        ArrayList<SpaceObject> masslessBodies = new ArrayList<>();
        for (SpaceObject o : spaceObjects){
            if (o.isMassless()){
                masslessBodies.add(o);
            } else{
                massBodies.add(o);
            }
        }

        for (int i = 0; i < massBodies.size(); i++) {
            for (int j = i + 1; j < massBodies.size(); j++) {
                Vector force = calculateForce(massBodies.get(i), massBodies.get(j));
                massBodies.get(i).addForce(force);
                massBodies.get(j).addForce(Vector.negate(force));
            }
        }

        for (SpaceObject massless : masslessBodies) {
            for (SpaceObject mass : massBodies) {
                massless.addForce(calculateForce(massless, mass));
            }
        }


        for (SpaceObject o : spaceObjects){
            o.update(time);
            o.setForce(new Vector(0, 0));
        }


    }

    /**
     * Calculates Gravitational Force between two spaceObjects
     * @param o1 first SpaceObject
     * @param o2 second SpaceObject
     * @return resulting force
     */
    private Vector calculateForce(SpaceObject o1, SpaceObject o2){
        Vector r = Vector.subtract(o1.getPosition(), o2.getPosition());
        double distance = Vector.normalize(r);
        double m1 = o1.getMass();
        double m2 = o2.getMass();

        return Vector.negate(Vector.multiply(r, G*m1*m2/Math.pow(distance, 3)));
    }

    public void addSpaceObject(SpaceObject o) {
        spaceObjects.addFirst(o);
    }

    public ArrayList<SpaceObject> getSpaceObjects() {
        return spaceObjects;
    }

    public SpaceObject getSpaceObject(String name) {
        for (SpaceObject obj : spaceObjects) {
            if (obj.name.equalsIgnoreCase(name)) return obj;
        }
        return null;
    }

}
