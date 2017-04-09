package Week2.SortingAtScale;

/**
 * Write a description of class DifferentSorters here.
 *
 * @author AlexRezv
 * @version 10 april 2017
 */

import provided.EarthQuakeParser;
import provided.Location;
import provided.QuakeEntry;

import java.util.ArrayList;
import java.util.Collections;

public class DifferentSorters {

    /*
    * Test the compareTo method by running the sortWithCompareTo in the DifferentSorters class with any data file.
    * The sort used is Collections.sort. You should be able to see that the earthquakes are sorted by magnitude,
    * and those with the same magnitude are sorted by depth. Modify this method to print out the QuakeEntry in
    * the ArrayList in position 10 (which is actually the 11th element in the ArrayList) by adding the following code
    * at the end of this method, after sorting and printing out all the elements.
    */

    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list);
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

        int quakeNumber = 10;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));

    }

    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

    }

    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

    }
}
