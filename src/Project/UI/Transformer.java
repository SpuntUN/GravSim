package Project.UI;

import Project.Math.Orbit;
import Project.Math.SpaceObject;
import Project.Math.Vector;

import java.util.LinkedList;

/**
 * Handles camera. Scales space objects.
 */
public class Transformer {
    private double scale;
    private double scaleFactor;
    private Vector offset;
    private SpaceObject followedObject;
    private Vector startingFollowingPosition;


    public Transformer(double scale, Vector offset){
        this.scale = scale;
        this.offset = offset;
        scaleFactor = 1.1;
    }

    public Transformer(double scale) {
        this.scale = scale;
        this.offset = new Vector();
        scaleFactor = 1.1;
    }

    public Transformer(){
        this.scale = 1;
        this.offset = new Vector(0, 0);
        scaleFactor = 1.1;
    }

    /**
     * Takes world spaceobject and transfroms it into screen object.
     * @param spaceObject being transformed
     * @return
     */
    public SpaceObject TransformNewSpaceObject(SpaceObject spaceObject){
        SpaceObject transformedObject = new SpaceObject(spaceObject);

        Vector pos = transformedObject.getPosition();
        double rad = transformedObject.getRadius();

        if (followedObject != null){
            pos = Vector.subtract(pos, followedObject.getPosition());
            pos = Vector.add(pos, startingFollowingPosition);
        }

        pos = Vector.divide(pos, scale);
        pos = Vector.add(pos, offset);
        rad /= scale;

        if (rad < spaceObject.getMinimalScreenRadius()){
            rad = spaceObject.getMinimalScreenRadius();
        }

        transformedObject.setPosition(pos);
        transformedObject.setRadius(rad);


        transformedObject.setOrbit(transformOrbit(transformedObject.getOrbit()));



        return transformedObject;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Vector getOffset() {
        return offset;
    }

    public void setOffset(Vector offset) {
        this.offset = offset;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }


    public void addOffset(Vector vector){
        offset = Vector.add(offset, vector);
    }

    public void subtractOffset(Vector vector){
        offset = Vector.subtract(offset, vector);
    }

    /**
     * @param mouse screen mouse coordinates
     * @param zoomIn true means changing zooming in, reducing the scale factor.
     */
    public void zoomAt(Vector mouse, boolean zoomIn) {

        double oldScale = scale;

        if (zoomIn) {
            scale /= scaleFactor;
        } else {
            scale *= scaleFactor;
        }

        Vector world = Vector.multiply(Vector.subtract(mouse, offset), oldScale);

        offset = Vector.subtract(mouse, Vector.divide(world, scale));
    }

    private Orbit transformOrbit(Orbit orbit){
        Orbit transformed = new Orbit(orbit);
        transformed.setPositions(new LinkedList<>());
        for (Vector pos : orbit.getPositions()){

            if (orbit.getRelativeTo() != null){
                pos = Vector.add(pos, orbit.getRelativeTo().getPosition());
            }

            if (followedObject != null){
                pos = Vector.subtract(pos, followedObject.getPosition());
                pos = Vector.add(pos, startingFollowingPosition);
            }

            pos = Vector.divide(pos, scale);
            pos = Vector.add(pos, offset);

            transformed.addPosition(pos);
        }
        return transformed;
    }


    /**
     * Sets an objects so it never changes screen position.
     * @param followedObject if null sets no object.
     */
    public void setCenteredObject(SpaceObject followedObject){
        if (startingFollowingPosition != null){
            startingFollowingPosition = Vector.add(Vector.subtract(startingFollowingPosition,this.followedObject.getPosition()), followedObject.getPosition())  ;
        } else {
            startingFollowingPosition = followedObject.getPosition();
        }

        this.followedObject = followedObject;
    }

}
