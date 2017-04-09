package Week2.SortingAtScale;

/**
 * Write a description of class MagnitudeComparator here.
 *
 * @author AlexRezv
 * @version 10 april 2017
 */

import provided.QuakeEntry;

import java.util.Comparator;

public class MagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
    }

}
