package Project.UI.TimeManagerPackage;

public class TimeManager {
    private double timeSinceStart;
    private double simulatedTime;

    private double dt = 1.0/1.0;
    private double speed = 86400;
    private double accumulator = 0;
    private double frameTime;
    private long lastTime;

    public TimeManager(double dt, double speed) {
        this.dt = dt;
        this.speed = speed;
        accumulator = 0;

        timeSinceStart = 0;
        simulatedTime = 0;
    }

    public TimeManager(){

    }

    public void accumulate(long currentTime){
        frameTime = (currentTime-lastTime)/1_000_000_000.0;
        lastTime = currentTime;

        accumulator += frameTime*speed;

        timeSinceStart += frameTime;
        simulatedTime += frameTime*speed;
    }

    public boolean hasAccumulated(){
        return accumulator >= dt;
    }

    public void reduceAccumulationByDt(){
        accumulator -= dt;
    }

    public double getDt() {
        return dt;
    }

    public void setLastTime(long lastTime){
        this.lastTime = lastTime;
    }

    public double getTimeSinceStart() {
        return timeSinceStart;
    }

    public String getTimeString(double time) {
        final double SECONDS_PER_MIN = 60;
        final double SECONDS_PER_HOUR = 60 * SECONDS_PER_MIN;
        final double SECONDS_PER_DAY = 24 * SECONDS_PER_HOUR;
        final double SECONDS_PER_YEAR = 365 * SECONDS_PER_DAY;

        if (time < SECONDS_PER_MIN) {
            return String.format("%.2f sec", time);

        } else if (time < SECONDS_PER_HOUR) {
            return String.format("%.2f min", time / SECONDS_PER_MIN);

        } else if (time < SECONDS_PER_DAY) {
            return String.format("%.2f hours", time / SECONDS_PER_HOUR);

        } else if (time < SECONDS_PER_YEAR*2) {
            return String.format("%.2f days", time / SECONDS_PER_DAY);

        } else {
            return String.format("%.2f years", time / SECONDS_PER_YEAR);
        }
    }

    public void setTimeSinceStart(double timeSinceStart) {
        this.timeSinceStart = timeSinceStart;
    }

    public double getSimulatedTime() {
        return simulatedTime;
    }


    public void setSimulatedTime(double simulatedTime) {
        this.simulatedTime = simulatedTime;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
