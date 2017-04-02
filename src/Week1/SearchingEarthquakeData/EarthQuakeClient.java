package Week1.SearchingEarthquakeData;

import Week1.provided.EarthQuakeParser;
import Week1.provided.Location;
import Week1.provided.QuakeEntry;

import java.util.ArrayList;

/**
 * Assignment 1: Filtering by Magnitude and Distance
 * In this assignment you will complete the program to filter earthquake data by magnitude and distance,
 * which was described in this lesson in the videos “Coding a Magnitude Filter” and “Coding a Distance Filter.”
 * Specifically, for this assignment, you will only modify the EarthQuakeClient class.
 * <p>
 * Assignment 2: Filtering by Depth
 * In this assignment you will filter earthquakes by their depth, finding those earthquakes whose depth is between
 * a minimum and maximum value. For more information on what the "depth" of an earthquake means, see the information
 * here: http://earthquake.usgs.gov/learn/topics/seismology/determining_depth.php
 * Specifically, for this assignment, you will add new methods to the EarthQuakeClient class
 * <p>
 * Assignment 3: Filtering by Phrase in Title
 * In this assignment you will filter earthquakes by a phrase in the title given for the earthquake in three ways,
 * finding those earthquakes whose title starts with a phrase, ends with a phrase, or just has a phrase somewhere
 * in the title.
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
        quakeData.forEach(qe -> {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        });

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
        quakeData.forEach(qe -> {
            if (distMax > from.distanceTo(qe.getLocation()) / 1000) {
                answer.add(qe);
            }
        });
        return answer;
    }

    /*
    * Write the method filterByDepth that has three parameters, an ArrayList of type QuakeEntry named quakeData,
    * a double named minDepth and a double named maxDepth. This method should return an ArrayList of type QuakeEntry
    * of all the earthquakes from quakeData whose depth is between minDepth and maxDepth, exclusive.
    * (Do not include quakes with depth exactly minDepth or maxDepth.)
    */
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        quakeData.forEach(qe -> {
            if (qe.getDepth() < maxDepth && qe.getDepth() > minDepth) {
                answer.add(qe);
            }
        });

        return answer;
    }

    /*
    * Write the method filterByPhrase that has three parameters, an ArrayList of type QuakeEntry named quakeData,
    * a String named where that indicates where to search in the title and has one of three values: (“start”, ”end”,
    * or “any”), and a String named phrase, indicating the phrase to search for in the title of the earthquake.
    * The title of the earthquake can be obtained through the getInfo() method. The filterByPhrase method should return
    * an ArrayList of type QuakeEntry of all the earthquakes from quakeData whose titles have the given phrase found at
    * location where (“start” means the phrase must start the title, “end” means the phrase must end the title and “any”
    * means the phrase is a substring anywhere in the title.)
    */
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        quakeData.forEach(qe -> {
            if (where.equals("start") && qe.getInfo().startsWith(phrase) ||
                    where.equals("end") && qe.getInfo().endsWith(phrase) ||
                    where.equals("any") && qe.getInfo().contains(phrase)) {
                answer.add(qe);
            }
        });
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
    public void bigQuakes(double magMin) {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/Week1/SearchingEarthquakeData/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        ArrayList<QuakeEntry> bigQuakes = this.filterByMagnitude(list, magMin);
        bigQuakes.forEach(System.out::println);
        System.out.println("Total quakes:" + bigQuakes.size());

    }

    /*
    * Modify the method closeToMe that has no parameters to call filterByDistance to print out the earthquakes within
    * 1000 Kilometers to a specified city (such as Durham, NC). For each earthquake found, print the distance from the
    * earthquake to the specified city, followed by the information about the city (use getInfo()).
    * Currently this method reads data on earthquakes from a URL, stores a QuakeEntry for each earthquake read in the
    * ArrayList named list, and prints out the number of earthquakes read in. It also gives the location for two cities,
    * Durham, NC (35.988, -78.907) and Bridgeport, CA (38.17, -118.82).*/
    public void closeToMe(double distMax) {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/Week1/SearchingEarthquakeData/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        // This location is Bridgeport, CA
        Location bridgeport = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> closeQuakes = this.filterByDistanceFrom(list, distMax, bridgeport);
        closeQuakes.forEach(a -> System.out.println(bridgeport.distanceTo(a.getLocation()) / 1000 + "\t" + a.getInfo()));
        System.out.println("Total quakes:" + closeQuakes.size());
    }

    /*
    * Write the void method quakesOfDepth that has no parameters to use filterByDepth and print all the earthquakes
    * from a data source whose depth is between a given minimum and maximum value. You should also print out the number
    * of earthquakes found.
    */
    public void quakesOfDepth(double minDepth, double maxDepth) {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/Week1/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        System.out.println("Find quakes with depths between " + minDepth + " and " + maxDepth);
        ArrayList<QuakeEntry> deepQuakes = this.filterByDepth(list, minDepth, maxDepth);
        deepQuakes.forEach(System.out::println);
        System.out.println("Found " + deepQuakes.size() + " quakes that match that criteria");
    }

    /*
    * Write the void method quakesByPhrase to use filterByPhrase and print all the earthquakes from a data source that
    * have phrase in their title in a given position in the title. You should also print out the number of earthquakes
    * found.
    */
    public void quakesByPhrase(String where, String phrase) {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/Week1/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        ArrayList<QuakeEntry> deepQuakes = this.filterByPhrase(list, where, phrase);
        deepQuakes.forEach(a -> System.out.println(a));
        System.out.println("Found " + deepQuakes.size() + " quakes that match " + phrase + " at " + where);
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
