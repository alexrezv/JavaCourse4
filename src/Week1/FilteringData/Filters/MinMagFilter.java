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
    private double magMin;

    public MinMagFilter(double min) {
        magMin = min;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin;
    }

}
