package Project.Math;

import java.awt.*;
import java.util.LinkedList;

public class Orbit {
    private LinkedList<Vector> positions;
    private int maxTrail;
    private int counter = 0;

    private SpaceObject relativeTo;


    public Orbit(int maxTrail) {
        positions = new LinkedList<>();
        this.maxTrail = maxTrail;
    }

    public Orbit(Orbit orbit){
        this.positions = orbit.getPositions();
        this.maxTrail = orbit.getMaxTrail();
        this.counter = orbit.getCounter();
        this.relativeTo = orbit.getRelativeTo();
    }


    public void addPosition(Vector pos){
        counter++;
        if (counter < 50) return;
        counter = 0;

        if (positions.size()+1 > maxTrail){
            positions.removeFirst();
        }
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


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public SpaceObject getRelativeTo() {
        return relativeTo;
    }

    public void setRelativeTo(SpaceObject relativeTo) {
        this.relativeTo = relativeTo;
    }
}
