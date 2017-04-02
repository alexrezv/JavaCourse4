package Week1.FilteringData.Filters;

import Week1.FilteringData.Filter;
import Week1.provided.Location;
import Week1.provided.QuakeEntry;

/**
 * Created by alex on 02.04.17.
 * <p>
 * Write the class DistanceFilter that implements Filter. This class should include private instance variables for
 * a location and a maximum distance, a constructor to initialize those variables, and a satisfies method that returns
 * true if a QuakeEntry’s distance from the given location is less than the specified maximum distance.
 * Otherwise it should return false.
 */
public class DistanceFilter implements Filter {
    private double maxDistance;
    private Location myLocation;

    public DistanceFilter(Location myLocation, double maxDistance) {
        this.maxDistance = maxDistance;
        this.myLocation = myLocation;
    }

    public boolean satisfies(QuakeEntry qe) {
        return this.myLocation.distanceTo(qe.getLocation()) < maxDistance;
    }
}
