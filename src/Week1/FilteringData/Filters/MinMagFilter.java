package Week1.FilteringData.Filters;

import Week1.FilteringData.Filter;
import Week1.provided.QuakeEntry;

/**
 * Write a description of class MinMaxFilter here.
 *
 * @author AlexRezv
 * @version 2 April 2017
 */
public class MinMagFilter implements Filter {
    private double minMag;
    private String name;

    public MinMagFilter(String filterName, double min) {
        this.minMag = min;
        this.name = filterName;
    }

    public String getName () {
        return this.name;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= minMag;
    }

}
