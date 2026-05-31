package Project.Math;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class SpaceCraft extends SpaceObject{
    private double fuel;
    private double maxFuel;
    private double thrust;
    private final double fuelEfficiency = 0.01;
    private double thrustDirection;
    private double maxThrust;

    private ArrayList<Instruction> instructions;


    public SpaceCraft(String name, double mass, Vector position, Vector velocity, Color color, double radius, double fuel, double maxThrust) {
        super(name, mass, position, velocity, color, radius);
        this.massless = true;
        this.fuel = fuel;
        maxFuel = fuel;
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

    public void removeInstruction(Instruction instruction){
        instructions.remove(instruction);
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(double maxFuel) {
        this.maxFuel = maxFuel;
    }

    public double getMaxThrust() {
        return maxThrust;
    }

    public void setMaxThrust(double maxThrust) {
        this.maxThrust = maxThrust;
    }

    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }

    public String getFuelString() {
        double currentFuel = getFuel();
        final double KL_THRESHOLD = 1e3;
        final double ML_THRESHOLD = 1e6;

        if (currentFuel >= ML_THRESHOLD) {
            return String.format("%.2f ML", currentFuel / ML_THRESHOLD);
        } else if (currentFuel >= KL_THRESHOLD) {
            return String.format("%.2f kL", currentFuel / KL_THRESHOLD);
        } else {
            return String.format("%.2f L", currentFuel);
        }
    }

    public String getMaxFuelString() {
        double totalFuel = getMaxFuel();
        final double KL_THRESHOLD = 1e3;
        final double ML_THRESHOLD = 1e6;

        if (totalFuel >= ML_THRESHOLD) {
            return String.format("%.2f ML", totalFuel / ML_THRESHOLD);
        } else if (totalFuel >= KL_THRESHOLD) {
            return String.format("%.2f kL", totalFuel / KL_THRESHOLD);
        } else {
            return String.format("%.2f L", totalFuel);
        }
    }

    public String getMaxThrustString() {
        double thrustVal = getMaxThrust();
        final double KN_THRESHOLD = 1e3;
        final double MN_THRESHOLD = 1e6;

        if (thrustVal >= MN_THRESHOLD) {
            return String.format("%.2f MN", thrustVal / MN_THRESHOLD);
        } else if (thrustVal >= KN_THRESHOLD) {
            return String.format("%.2f kN", thrustVal / KN_THRESHOLD);
        } else {
            return String.format("%.2f N", thrustVal);
        }
    }

}
