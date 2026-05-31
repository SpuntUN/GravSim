package Project.Math;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a controllable spacecraft that extends {@link SpaceObject}.
 * It tracks fuel consumption, custom instructions (maneuvers), and handles thrust application.
 * * @author Matěj Švec
 */
public class SpaceCraft extends SpaceObject {
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

    /**
     * Executes engine burn sequences, shortens requested thrust to maxThrust,
     * consumes corresponding fuel, and adds the resulting force vector to the craft.
     *
     * @param time time delta
     */
    public void burn(double time){
        if (thrust > maxThrust) thrust = maxThrust;

        consumeFuel(thrust * time);

        addForce(Vector.polarToCartesianConversion(thrust, thrustDirection));
    }

    /**
     * Decrements fuel reserves based on thrust intensity, duration, and efficiency.
     * Important to say, this is not realistic consumption of fuel for a space craft.
     * @param thrustTime total thrust accumulated during the time frame
     */
    private void consumeFuel(double thrustTime){
        double consumedFuel = thrustTime * fuelEfficiency;
        fuel -= consumedFuel;
    }

    /**
     * Checks if the vehicle has depleted its fuel reserves.
     *
     * @return true if fuel is less than or equal to 0, false otherwise
     */
    public boolean outOfFuel(){
        return fuel <= 0;
    }

    /**
     * Updates the spacecraft state over an incremental step. Processes active instructions,
     * updates engine configurations, and processes structural physics updates.
     * If the craft runs out of fuel, pending instructions are cleared.
     *
     * @param time elapsed time step delta
     */
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

    /**
     * Appends a planned instruction to the spacecraft's trajectory queue.
     *
     * @param instruction the instruction to add
     */
    public void addInstruction(Instruction instruction){
        instructions.add(instruction);
    }

    /**
     * Removes an instruction from the spacecraft's trajectory queue.
     *
     * @param instruction the instruction to remove
     */
    public void removeInstruction(Instruction instruction){
        instructions.remove(instruction);
    }

    /**
     * Returns the complete queue of execution instructions.
     *
     * @return list of instructions
     */
    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    /**
     * Gets the current amount of remaining fuel.
     *
     * @return fuel remaining
     */
    public double getFuel() {
        return fuel;
    }

    /**
     * Direct setting of the current fuel value.
     *
     * @param fuel new fuel value
     */
    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    /**
     * Gets the maximum capacity threshold of fuel.
     *
     * @return max capacity
     */
    public double getMaxFuel() {
        return maxFuel;
    }

    /**
     * Sets the maximum capacity threshold of fuel.
     *
     * @param maxFuel new max fuel value
     */
    public void setMaxFuel(double maxFuel) {
        this.maxFuel = maxFuel;
    }

    /**
     * Gets maximum structural engine capability.
     *
     * @return max thrust value
     */
    public double getMaxThrust() {
        return maxThrust;
    }

    /**
     * Sets maximum structural engine capability limit.
     *
     * @param maxThrust new max thrust limit
     */
    public void setMaxThrust(double maxThrust) {
        this.maxThrust = maxThrust;
    }

    /**
     * Assigns a fresh instruction list to the vessel, wiping previous items.
     *
     * @param instructions custom list of instructions
     */
    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }

    /**
     * Generates a readable string of the current fuel formatted using liters (L),
     * kiloliters (kL), or megaliters (ML) based on magnitude.
     *
     * @return formatted fuel string
     */
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

    /**
     * Generates a readable string of the maximum fuel capacity formatted with
     * appropriate volumetric unit prefixes (L, kL, ML).
     *
     * @return formatted max fuel string
     */
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

    /**
     * Generates a human-readable string representation of the maximum engine thrust capability,
     * scaled into Newtons (N), kilonewtons (kN), or meganewtons (MN).
     *
     * @return formatted max thrust string
     */
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