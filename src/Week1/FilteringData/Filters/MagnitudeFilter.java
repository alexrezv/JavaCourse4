package Week1.FilteringData.Filters;

import Week1.FilteringData.Filter;
import Week1.provided.QuakeEntry;

/**
 * Created by alex on 02.04.17.
 * <p>
 * Write the class MagnitudeFilter that implements Filter. This class should include private instance variables for
 * a minimum and maximum magnitude, a constructor to initialize those variables, and a satisfies method that returns
 * true if a QuakeEntry’s magnitude is between the minimum and maximum magnitudes, or equal to one of them.
 * Otherwise it should return false.
 */
public class MagnitudeFilter implements Filter {
    private double minMagnitude, maxMagnitude;
    private String name;

    public MagnitudeFilter(String filterName, double minMagnitude, double maxMagnitude) {
        this.minMagnitude = minMagnitude;
        this.maxMagnitude = maxMagnitude;
        this.name = filterName;
    }

    public String getName() {
        return this.name;
    }

    public boolean satisfies(QuakeEntry qe) {
        return this.minMagnitude <= qe.getMagnitude() && qe.getMagnitude() <= this.maxMagnitude;
    }
}
