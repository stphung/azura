package experiment.physics.debug.engine;

public class Timer {
    private double time;
    public Timer() {
        this.time = System.nanoTime();
    }
    public double start() {
        this.time = System.nanoTime();
        return this.time;
    }
    public double getTimeDifferenceSeconds() {
        return getTimeDifference()/10000000000.0;
    }
    private double getTimeDifference() {
        return System.nanoTime() - this.time;
    }
}
