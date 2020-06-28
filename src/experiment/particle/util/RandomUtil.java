package experiment.particle.util;

import java.util.Random;


public class RandomUtil {
    
    private static final Random RANDOM = new Random();
    
    public static double randomValueIn(Range range) {
        return randomValueBetween(range.getMin(), range.getMax());
    }
    public static double randomValueBetween(double minValue, double maxValue) {
        double values = maxValue - minValue;
        return RANDOM.nextDouble()*values + minValue;
    }
}
