package Week1.SearchingEarthquakeData;

/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @author AlexRezv
 * @version 2.0, April 2017
 *
 * Assignment 4: Finding the Closest Earthquakes to a Location
 * In this assignment you will complete the program to determine the N closests earthquakes to a specified location that
 * was described in this lesson in the video.
 * Specifically, for this assignment, you will only modify the ClosestQuakes class
 */

import Week1.SearchingEarthquakeData.provided.*;

import java.util.*;

public class ClosestQuakes {
    /*
    * The first method you need has already been written for you. The method findClosestQuakes reads in data on
    * earthquakes storing them in the ArrayList list and prints how many quakes there are. It sets a location variable
    * named jakarta to the location of the city Jakarta. It then calls the method getClosest to determine the ten
    * closest earthquakes in list and prints information about those quakes and how close they are to Jakarta.
    * This method has already been written for you, but doesn’t work yet since the method getClosest is not complete.
    *
    * Complete the method getClosest that has already been started for you. This method has three parameters,
    * an ArrayList of type QuakeEntry named quakeData, a Location named current, and an int named howMany.
    * This method should find the closest number of howMany earthquakes to the current Location and return them in
    * an ArrayList of type QuakeEntry. The earthquakes should be in the ArrayList in order with the closest earthquake
    * in index position 0. If there are fewer then howMany earthquakes in quakeData, then the ArrayList returned would
    * be the same size as quakeData.
    */
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<>();
        HashMap<Double, QuakeEntry> quakeToDistance = new HashMap<>();
        quakeData.forEach(a -> quakeToDistance.put(Double.valueOf(current.distanceTo(a.getLocation())/1000), a ));
        if (howMany > quakeData.size()) howMany = quakeData.size();
        for (int i = 0; i < howMany; ++i) {
            double minKey = quakeToDistance.keySet().stream().min(Double::compareTo).orElse(null);
            ret.add(quakeToDistance.get(minKey));
            quakeToDistance.remove(minKey);
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/Week1/SearchingEarthquakeData/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
