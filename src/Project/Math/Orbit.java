package Project.Math;

import java.awt.*;
import java.util.LinkedList;

public class Orbit {
    private LinkedList<Vector> positions;
    private int maxTrail;
    private int counter = 0;
    private Color color;



    public Orbit(int maxTrail) {
        positions = new LinkedList<>();
        this.maxTrail = maxTrail;
        color = Color.GRAY;
    }

    public Orbit(Orbit orbit){
        this.positions = orbit.getPositions();
        this.maxTrail = orbit.getMaxTrail();
        this.color = orbit.getColor();
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
