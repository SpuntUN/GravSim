package Project.Math;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class SpaceCraft extends SpaceObject{
    private double fuel;
    private double thrust;
    private final double fuelEfficiency = 0.01;
    private double thrustDirection;
    private double maxThrust;

    private ArrayList<Instruction> instructions;


    public SpaceCraft(String name, double mass, Vector position, Vector velocity, Color color, double radius, double fuel, double maxThrust) {
        super(name, mass, position, velocity, color, radius);
        this.massless = true;
        this.fuel = fuel;
        this.maxThrust = maxThrust;
        instructions = new ArrayList<>();
    }

    public void burn(double time){
        if (thrust > maxThrust) thrust = maxThrust;

        consumeFuel(thrust * time);

        addForce(Vector.polarToCartesianConversion(thrust, thrustDirection));
    }

    private void consumeFuel(double thrustTime){
        double consumedFuel = thrustTime * fuelEfficiency;
        fuel -= consumedFuel;
    }

    public boolean outOfFuel(){
        return fuel <= 0;
    }


    @Override
    public void update(double time){
        System.out.println(fuel);

        if (outOfFuel()) instructions = new ArrayList<>();

        Iterator<Instruction> iterator = instructions.iterator();
        while (iterator.hasNext()) {
            Instruction i = iterator.next();

            if (i.done()) {
                iterator.remove();
                continue;
            }

            if (i.isActive()) {
                thrust = i.getThrust();
                thrustDirection = i.getDirection();
            } else {
                thrust = 0;
            }
            burn(time);
            i.update(time);
        }

        super.update(time);
    }


    public void addInstruction(Instruction instruction){
        instructions.add(instruction);
    }

}
