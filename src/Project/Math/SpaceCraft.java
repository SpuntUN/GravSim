package Project.Math;

import java.util.ArrayList;

public class SpaceCraft extends SpaceObject{
    private double fuel;
    private double fuelConsumption;
    private double thrust;
    private double thrustDirection;
    private double maxThrust;

    private ArrayList<Instruction> instructions;


    public void burn(double time){
        if (thrust > maxThrust) thrust = maxThrust;

        double thrustTime = thrust*time;
        consumeFuel(thrustTime);

        addForce(Vector.polarToCartesianConversion(thrustTime, thrustDirection));
    }

    private void consumeFuel(double thrust){
        double consumedFuel = thrust * mass;
        fuel -= consumedFuel;
    }

    public boolean outOfFuel(){
        return fuel <= 0;
    }


    @Override
    public void update(double time){

        if (outOfFuel()) instructions = new ArrayList<>();

        for (Instruction i : instructions){

            if (i.done()) {
                instructions.remove(i);
                continue;
            }
            if (!i.isActive()) {
                continue;
            }

            thrust = i.getThrust();
            thrustDirection = i.getDirection();

            burn(time);
            i.update(time);
        }

        super.update(time);
    }


    public void addInstruction(Instruction instruction){
        instructions.add(instruction);
    }

}
