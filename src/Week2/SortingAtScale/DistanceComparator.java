package Week2.SortingAtScale;

/**
 * Write a description of class DistanceComparator here.
 *
 * @author AlexRezv
 * @version 10 april 2017
 */

import provided.Location;
import provided.QuakeEntry;

import java.util.Comparator;

public class DistanceComparator implements Comparator<QuakeEntry> {
    Location fromWhere;

    public DistanceComparator(Location where) {
        fromWhere = where;
    }

    public int compare(QuakeEntry q1, QuakeEntry q2) {
        double dist1 = q1.getLocation().distanceTo(fromWhere);
        double dist2 = q2.getLocation().distanceTo(fromWhere);
        return Double.compare(dist1, dist2);
    }

}
 