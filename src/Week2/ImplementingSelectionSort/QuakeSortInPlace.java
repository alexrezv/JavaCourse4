package Week2.ImplementingSelectionSort;

/**
 * Write a description of class QuakeSortInPlace here.
 *
 * @author AlexRezv
 * @version 4 April 2017
 * <p>
 * Assignment 1: Sort by Depth
 * In this assignment, you will add methods in the QuakeSortInPlace class to sort the quakes by depth, from largest
 * depth to smallest depth. This will mean any positive depth values will be first, followed by depths with increasingly
 * negative values, e.g., 200.00, 0.00, -20000.00, -100000.00
 */

import provided.EarthQuakeParser;
import provided.QuakeEntry;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }

    private int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    private void sortByMagnitude(ArrayList<QuakeEntry> in) {

        for (int i = 0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }

    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/earthquakeDataSampleSix1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("read data for " + list.size() + " quakes");

        /*
        * Modify the testSort method to comment out the line sortByMagnitude and add below this line a call to
        * sortByLargestDepth. Run your program on any data file and you should see the earthquakes in sorted order from
        * largest depth to smallest depth.
        */
        //sortByMagnitude(list);
        //sortByLargestDepth(list);

        /*
        * Modify the testSort method to comment out the line sortByLargestDepth, and add below this line a call to
        * sortByMagnitudeWithBubbleSort. Run your program on any data file and you should see the earthquakes in sorted
        * order from smallest to largest magnitude.
        *
        * Are you convinced your program is working correctly? Let’s add more output and test it on a small file.
        * Add code to sortByMagnitudeWithBubbleSort to print all the quakes before a pass, and then to print all the
        * quakes after each pass, identifying the pass. Since there will be a lot of data, you’ll only want to run your
        * program on a small file. Once you are sure it works, you probably want to then comment out the print
        * statements. Run your program on the file earthquakeDataSampleSix2.atom, which has data on only five
        * earthquakes. You should get the following output. Focusing on the magnitude, after pass 0 (the first pass) the
        * quake with magnitude 4.80 is last, after pass 1 the quake with magnitude 2.60 is where it belongs. Note that
        * pass 3 wasn’t needed as the quakes were already in sorted order. That might happen sometimes.
        */
        //this.sortByMagnitudeWithBubbleSort(list);

        /*
        * Modify the testSort method to call to sortByMagnitudeWithBubbleSortWithCheck.
        * Run your program on any data files earthquakeDataSampleSix1.atom (should sort after 2 passes) and
        * earthquakeDataSampleSix2.atom (should sort after 3 passes). Both of these files have five earthquakes.
        */
        //this.sortByMagnitudeWithBubbleSortWithCheck(list);

        /*
        * Modify the testSort method to call to sortByMagnitudeWithCheck.
        * Run your program on any data files earthquakeDataSampleSix1.atom (should sort after 3 passes) and
        * earthquakeDataSampleSix2.atom (should sort after 4 passes). Both of these files have five earthquakes.
        */
        this.sortByMagnitudeWithCheck(list);


        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
    * Write the method getLargestDepth that has two parameters, an ArrayList of type QuakeEntry named quakeData and an
	* int named from representing an index position in the ArrayList. This method returns an integer representing the
	* index position of the QuakeEntry with the largest depth considering only those QuakeEntry’s from position from to
	* the end of the ArrayList.
	*/

    private int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        ArrayList<QuakeEntry> copy = new ArrayList<>(quakeData);
        copy.subList(0, from).clear();

        double largestDepth = copy.stream()
                .map(a -> a.getDepth())
                .max(Double::compareTo)
                .orElse(null);

        QuakeEntry largestDepthQE = copy.stream()
                .filter(a -> a.getDepth() == largestDepth)
                .findFirst()
                .get();

        return copy.indexOf(largestDepthQE) + from;
    }

    /*
    * Write the void method sortByLargestDepth that has one parameter, an ArrayList of type QuakeEntry named in.
    * This method sorts the QuakeEntry’s in the ArrayList by depth using the selection sort algorithm, but in reverse
    * order from largest depth to smallest depth (the QuakeEntry with the largest depth should be in the 0th position in
    * the ArrayList). This method should call the method getLargestDepth repeatedly until the ArrayList is sorted.
    */

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size(); ++i) {
            int largestDepth = this.getLargestDepth(in, i);
            QuakeEntry buf = in.get(i);
            in.set(i, in.get(largestDepth));
            in.set(largestDepth, buf);
        }
    }

    /*
    * Write the void method onePassBubbleSort that has two parameters, an ArrayList of type QuakeEntry named quakeData
    * and an int named numSorted that represents the number of times this method has already been called on this
    * ArrayList and thus also represents the number of the elements that are guaranteed to already be where they belong
    * when the ArrayList is sorted by magnitude. This method makes one pass of bubble sort on the ArrayList.
    * It should take advantage of the fact that the last numSorted elements are already in sorted order.
    */

    private void onePassBubbleSort (ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 0; i < quakeData.size()-1-numSorted; ++i) {
            if (quakeData.get(i).getMagnitude() > quakeData.get(i+1).getMagnitude()) {
                QuakeEntry buf = quakeData.get(i);
                quakeData.set(i, quakeData.get(i+1));
                quakeData.set(i+1, buf);
            }
        }
    }

    /*
    * Write the void method sortByMagnitudeWithBubbleSort that has one parameter, an ArrayList of type QuakeEntry named
    * in. If the ArrayList in has N elements in it, this method should call onePassBubbleSort N – 1 times to sort the
    * elements in in.
    */

    public void sortByMagnitudeWithBubbleSort (ArrayList<QuakeEntry> in) {
        in.forEach(System.out::println);
        for (int i = 0; i < in.size()-1; ++i) {
            this.onePassBubbleSort(in, i);
            in.forEach(System.out::println);
        }
    }

    /*
    * Write the method checkInSortedOrder that has one parameter, an ArrayList of type QuakeEntry named quakes.
    * This method returns true if the earthquakes are in sorted order by magnitude from smallest to largest.
    * Otherwise this methods returns false. You’ll need to loop through the ArrayList and check adjacent earthquakes
    * to see if any are out of order.
    */

    private boolean checkInSortedOrder (ArrayList<QuakeEntry> quakes) {
        for (int i = 0; i < quakes.size()-1; ++i) {
            if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }

    /*
    * Write the void method sortByMagnitudeWithBubbleSortWithCheck that has one parameter,
    * an ArrayList of type QuakeEntry named in. If the ArrayList in has N elements in it,
    * this method should call onePassBubbleSort at most N – 1 times.
    * This method should call checkInSortedOrder and stop early if the ArrayList is already sorted.
    * This method should print how many passes were needed to sort the elements.
    */

    private void sortByMagnitudeWithBubbleSortWithCheck (ArrayList<QuakeEntry> in) {
        int passes = 0;
        for (int i = 0; i < in.size()-1; ++i) {
            this.onePassBubbleSort(in, i);
            passes+=1;
            if (this.checkInSortedOrder(in)) break;
        }
        System.out.println("Passes needed: " + passes);
    }

    /*
    * Write the void method sortByMagnitudeWithCheck that has one parameter, an ArrayList of type QuakeEntry named in.
    * This method sorts earthquakes by their magnitude from smallest to largest using selection sort similar to the
    * sortByMagnitude method. However, this method should call checkInSortedOrder and stop early if the ArrayList is
    * already sorted. This method should print how many passes were needed to sort the elements. For selection sort,
    * one pass has exactly one swap.
    */

    public void sortByMagnitudeWithCheck (ArrayList<QuakeEntry> in) {
        System.out.println("Passes needed: " + this.sortByMagnitudeWithPasses(in));
    }

    private int sortByMagnitudeWithPasses(ArrayList<QuakeEntry> in) {
        int passes = 0;
        for (int i = 0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
            passes+=1;
            if (this.checkInSortedOrder(in)) break;
        }
        return passes;
    }

}
