public class TimeManager {
    private double simulatedTime;
    private double timeSinceStart;

    private double dt = 1.0/1.0;
    private double speed = 86400;
    private double accumulator = 0;
    private double frameTime;
    private long lastTime;

    public TimeManager(double dt, double speed) {
        this.dt = dt;
        this.speed = speed;
        accumulator = 0;
    }

    public TimeManager(){

    }

    public void accumulate(long currentTime){
        frameTime = (currentTime-lastTime)/1_000_000_000.0;
        lastTime = currentTime;

        accumulator += frameTime*speed;
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

}
