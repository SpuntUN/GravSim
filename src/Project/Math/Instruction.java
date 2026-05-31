package Project.Math;

public class Instruction {
    private double thrust;
    public double direction;
    private double wait;
    private double duration;

    public Instruction(double thrust, double direction, double wait, double duration) {
        this.thrust = thrust;
        this.direction = direction;
        this.wait = wait;
        this.duration = duration;
    }

    public boolean isActive(){
        return !waiting() && !done();
    }

    public void update(double time){
        if (isActive()){
            duration -= time;
        }else if (waiting()){
            wait -= time;
        }
    }

    public boolean done(){
        return duration <= 0;
    }

    public boolean waiting(){
        return wait > 0;
    }

    public double getWait() {
        return wait;
    }

    public void setWait(double wait) {
        this.wait = wait;
    }

    public double getThrust() {
        return thrust;
    }

    public void setThrust(double thrust) {
        this.thrust = thrust;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }
}
