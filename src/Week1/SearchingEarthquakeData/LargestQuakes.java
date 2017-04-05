package Week1.SearchingEarthquakeData;

import provided.EarthQuakeParser;
import provided.QuakeEntry;

import java.util.ArrayList;

/**
 * Created by alex on 01.04.17.
 * <p>
 * Assignment 5: Finding the Largest Magnitude Earthquakes
 * In this assignment you will write a new class and methods to determine the N biggest earthquakes,
 * those with largest magnitude.
 *
 * @author AlexRezv
 */
public class LargestQuakes {
    /*
    * Write a void method named findLargestQuakes that reads in earthquake data from a source and storing them into
    * an ArrayList of type QuakeEntry. Then it prints all the earthquakes and how many earthquakes that were from the
    * source. You should read in earthquakes from the small file nov20quakedatasmall.atom, print all the earthquakes
    * and also print how many there are. After this works you should comment out the printing of all the earthquakes,
    * but continue to print out the total number of earthquakes read in.
    */
    public void findLargestQuakes(int howMany) {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/Week1/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        //list.forEach(System.out::println);
        System.out.println("read data for " + list.size() + " quakes");
        //System.out.println(this.indexOfLargest(list) + "\t" + list.get(this.indexOfLargest(list)).getMagnitude());
        ArrayList<QuakeEntry> largest;
        largest = this.getLargest(list, howMany);
        largest.forEach(System.out::println);
        System.out.println(largest.size());
    }

    /*
    * Write a method named indexOfLargest that has one parameter, an ArrayList of type QuakeEntry named data.
    * This method returns an integer representing the index location in data of the earthquake with the largest
    * magnitude. You should test out this method by adding code to the method findLargestQuakes to print the index
    * location of the largest magnitude earthquake in the file nov20quakedatasmall.atom and the earthquake at that
    * location. You will see that the largest such earthquake is at location 3 and has magnitude 5.50. Once this works
    * you may want to comment this out.
    */
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int indexOfLargest = 0;
        double maxMagnitude = data.stream().map(a -> a.getMagnitude()).max(Double::compareTo).orElse(null);

        for (int i = 0; i < data.size(); ++i) {
            if (data.get(i).getMagnitude() == maxMagnitude) {
                indexOfLargest = i;
            }
        }
        return indexOfLargest;
    }

    /*
    * Write a method named getLargest that has two parameters, an ArrayList of type QuakeEntry named quakeData and
    * an integer named howMany. This method returns an ArrayList of type QuakeEntry of the top howMany largest magnitude
    * earthquakes from quakeData. The quakes returned should be in the ArrayList in order by their magnitude, with
    * the largest magnitude earthquake in index position 0. If quakeData has fewer than howMany earthquakes, then
    * the number of earthquakes returned in the ArrayList is equal to the number of earthquakes in quakeData.
    * This method should call the method indexOfLargest.
    */
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> quakeDataCopy = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> largestQuakes = new ArrayList<>();
        if (howMany > quakeDataCopy.size()) {
            howMany = quakeDataCopy.size();
        }
        for (int i = 0; i < howMany; ++i) {
            int largestQuakeIndex = this.indexOfLargest(quakeDataCopy);
            largestQuakes.add(quakeDataCopy.get(largestQuakeIndex));
            quakeDataCopy.remove(largestQuakeIndex);
        }
        return largestQuakes;
    }

}
