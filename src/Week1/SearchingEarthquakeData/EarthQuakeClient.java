package Week1.SearchingEarthquakeData;

import java.util.*;
import edu.duke.*;
import Week1.SearchingEarthquakeData.provided.*;

/**
 * Assignment 1: Filtering by Magnitude and Distance
 * In this assignment you will complete the program to filter earthquake data by magnitude and distance,
 * which was described in this lesson in the videos “Coding a Magnitude Filter” and “Coding a Distance Filter.”
 * <p>
 * Specifically, for this assignment, you will only modify one class, the EarthQuakeClient class:
 */

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    /*
    * Write the method filterByMagnitude that has already been started for you.
    * This method has two parameters, an ArrayList of type QuakeEntry named quakeData, and a double named magMin.
    * This method should return an ArrayList of type QuakeEntry of all the earthquakes from quakeData that have
    * a magnitude larger than magMin. Notice that we have already created an ArrayList named answer for you to store
    * those earthquakes that satisfy this requirement.
    */
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (int i = 0; i < quakeData.size(); ++i) {
            if (quakeData.get(i).getMagnitude() > magMin) {
                answer.add(quakeData.get(i));
            }
        }
        return answer;
    }


    /*
    * Write the method filterByDistanceFrom that has already been started for you. This method has three parameters,
    * an ArrayList of type QuakeEntry named quakeData, a double named distMax, and a Location named from.
    * This method should return an ArrayList of type QuakeEntry of all the earthquakes from quakeData that are less
    * than distMax from the location from. Notice that we have already created an ArrayList named answer for you
    * to store those earthquakes that satisfy this requirement.
    */
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int i = 0; i < quakeData.size(); ++i) {
            if (distMax > from.distanceTo(quakeData.get(i).getLocation())/1000) {
                answer.add(quakeData.get(i));
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }

    }

    /*
    * Modify the method bigQuakes that has no parameters to use filterByMagnitude and print earthquakes above a certain
    * magnitude, and also print the number of such earthquakes. Currently this method reads data on earthquakes from
    * a file, stores a QuakeEntry for each earthquake read in the ArrayList named list, and prints out the number of
    * earthquakes read in.
    */
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/Week1/SearchingEarthquakeData/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        ArrayList<QuakeEntry> bigQuakes = this.filterByMagnitude(list, 5.0);
        bigQuakes.forEach(a -> System.out.println(a));
        System.out.println("Total quakes:" + bigQuakes.size());

    }

    /*
    * Modify the method closeToMe that has no parameters to call filterByDistance to print out the earthquakes within
    * 1000 Kilometers to a specified city (such as Durham, NC). For each earthquake found, print the distance from the
    * earthquake to the specified city, followed by the information about the city (use getInfo()).
    * Currently this method reads data on earthquakes from a URL, stores a QuakeEntry for each earthquake read in the
    * ArrayList named list, and prints out the number of earthquakes read in. It also gives the location for two cities,
    * Durham, NC (35.988, -78.907) and Bridgeport, CA (38.17, -118.82).*/
    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/Week1/SearchingEarthquakeData/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        // This location is Durham, NC
        /*Location durham = new Location(35.988, -78.907);
        ArrayList<QuakeEntry> closeQuakes = this.filterByDistanceFrom(list, 1000, durham);
        closeQuakes.forEach(a -> System.out.println(a.getInfo()));
        System.out.println("Total quakes:" + closeQuakes.size());*/

        // This location is Bridgeport, CA
        Location bridgeport =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> closeQuakes = this.filterByDistanceFrom(list, 1000, bridgeport);
        closeQuakes.forEach(a -> System.out.println(bridgeport.distanceTo(a.getLocation())/1000 + "\t" + a.getInfo()));
        System.out.println("Total quakes:" + closeQuakes.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

}
