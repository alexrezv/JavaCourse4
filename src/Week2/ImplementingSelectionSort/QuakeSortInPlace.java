package Week2.ImplementingSelectionSort;

/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author AlexRezv
 * @version 4 April 2017
 *
 * Assignment 1: Sort by Depth
 * In this assignment, you will add methods in the QuakeSortInPlace class to sort the quakes by depth, from largest
 * depth to smallest depth. This will mean any positive depth values will be first, followed by depths with increasingly
 * negative values, e.g., 200.00, 0.00, -20000.00, -100000.00
 */

import java.util.*;
import edu.duke.*;
import provided.EarthQuakeParser;
import provided.QuakeEntry;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");

        /*
        * Modify the testSort method to comment out the line sortByMagnitude and add below this line a call to
        * sortByLargestDepth. Run your program on any data file and you should see the earthquakes in sorted order from
        * largest depth to smallest depth.
        */
        //sortByMagnitude(list);
        sortByLargestDepth(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
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

	public int getLargestDepth (ArrayList<QuakeEntry> quakeData, int idx) {
	    ArrayList<QuakeEntry> copy = new ArrayList<>(quakeData);
	    copy.subList(0, idx).clear();

	    double largestDepth = copy.stream()
                                    .map(a -> a.getDepth())
                                    .max(Double::compareTo)
                                    .orElse(null);

	    QuakeEntry largestDepthQE = copy.stream()
                        .filter(a -> a.getDepth() == largestDepth)
                        .findFirst()
                        .get();

	    return copy.indexOf(largestDepthQE) + idx;
    }

    /*
    * Write the void method sortByLargestDepth that has one parameter, an ArrayList of type QuakeEntry named in.
    * This method sorts the QuakeEntry’s in the ArrayList by depth using the selection sort algorithm, but in reverse
    * order from largest depth to smallest depth (the QuakeEntry with the largest depth should be in the 0th position in
    * the ArrayList). This method should call the method getLargestDepth repeatedly until the ArrayList is sorted.
    */

    public void sortByLargestDepth (ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size(); ++i) {
            int largestDepth = this.getLargestDepth(in, i);
            QuakeEntry buf = in.get(i);
            in.set(i, in.get(largestDepth));
            in.set(largestDepth, buf);
        }
    }
}
