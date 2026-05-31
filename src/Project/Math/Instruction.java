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

    public String getThrustString() {
        double abs = Math.abs(thrust);

        if (abs >= 1_000_000) {
            return String.format("%.2f MN", thrust / 1_000_000.0);
        } else if (abs >= 1_000) {
            return String.format("%.2f kN", thrust / 1_000.0);
        } else {
            return String.format("%.2f N", thrust);
        }
    }

    public String getDirectionString() {
        double degrees = direction;

        degrees = degrees % 360;

        return String.format("%.2f°", degrees);
    }

    public String getWaitString() {
        return formatTime(wait);
    }

    public String getDurationString() {
        return formatTime(duration);
    }

    private String formatTime(double seconds) {

        double abs = Math.abs(seconds);

        if (abs >= 365 * 24 * 3600) {
            return String.format("%.2f years", seconds / (365 * 24 * 3600));
        } else if (abs >= 24 * 3600) {
            return String.format("%.2f days", seconds / (24 * 3600));
        } else if (abs >= 3600) {
            return String.format("%.2f hours", seconds / 3600);
        } else if (abs >= 60) {
            return String.format("%.2f min", seconds / 60);
        } else {
            return String.format("%.2f s", seconds);
        }
    }
}
