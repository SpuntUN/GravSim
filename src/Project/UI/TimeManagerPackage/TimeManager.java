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

    public String getTimeString(double time){
        double adjustedTime = time;

        final double SECONDS = 60;
        final double MINUTES = 60;
        final double HOURS = 60 * MINUTES;
        final double DAYS = 24 * HOURS;
        final double MONTHS = 30 * DAYS;
        final double YEARS = 12 * MONTHS;

        if (time < SECONDS) {
            adjustedTime = time;
            return String.format("%.2f sec", adjustedTime);

        } else if (time < MINUTES) {
            adjustedTime = time/MINUTES;
            return String.format("%.2f min", adjustedTime);

        } else if (time < HOURS) {
            adjustedTime = time/HOURS;
            return String.format("%.2f hours", adjustedTime);

        } else if (time < DAYS*MONTHS) {
            adjustedTime = time/DAYS;
            return String.format("%.2f days", adjustedTime);

        } else{
            adjustedTime = time/YEARS;
            return String.format("%.2f years", adjustedTime);

        }
    }

    public void setTimeSinceStart(double timeSinceStart) {
        this.timeSinceStart = timeSinceStart;
    }

    public double getSimulatedTime() {
        System.out.println(simulatedTime);
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
