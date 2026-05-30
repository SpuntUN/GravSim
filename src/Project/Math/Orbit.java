package Project.Math;

import java.util.LinkedList;
import java.util.Vector;

public class Orbit {
    private LinkedList<Vector> positions;
    private int maxTrail;

    public Orbit(int maxTrail) {
        this.maxTrail = maxTrail;
    }

    public void addPosition(Vector pos){
        positions.add(pos);
    }

    public LinkedList<Vector> getPositions() {
        return positions;
    }

    public void setPositions(LinkedList<Vector> positions) {
        this.positions = positions;
    }

    public int getMaxTrail() {
        return maxTrail;
    }

    public void setMaxTrail(int maxTrail) {
        this.maxTrail = maxTrail;
    }
}
