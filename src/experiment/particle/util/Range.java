package experiment.particle.util;

public class Range {
    
    private final double min;
    private final double max;
    
    public Range(double min, double max) {
        this.min = min;
        this.max = max;
    }
    public double getMin() {
        return this.min;
    }
    public double getMax() {
        return this.max;
    }
}
