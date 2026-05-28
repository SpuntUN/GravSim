public class Transformer {
    private double scale;
    private double scaleFactor;
    private Vector offset;

    public Transformer(double scale, Vector offset){
        this.scale = scale;
        this.offset = offset;
        scaleFactor = 1.1;
    }

    public Transformer(double scale) {
        this.scale = scale;
        this.offset = new Vector(0, 0);
        scaleFactor = 1.1;
    }

    public Transformer(){
        this.scale = 1;
        this.offset = new Vector(0, 0);
        scaleFactor = 1.1;
    }

    public SpaceObject TransformNewSpaceObject(SpaceObject spaceObject){
        SpaceObject transformedObject = new SpaceObject(spaceObject);

        Vector pos = transformedObject.getPosition();
        double rad = transformedObject.getRadius();

        pos = Vector.divide(pos, scale);
        pos = Vector.add(pos, offset);
        rad /= scale;

        transformedObject.setPosition(pos);
        transformedObject.setRadius(rad);

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

    public void scaleDown(){
        scale *= scaleFactor;
    }

    public void scaleUp(){
        scale /= scaleFactor;
    }

}
