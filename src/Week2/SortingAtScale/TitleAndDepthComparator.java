package Week2.SortingAtScale;

import provided.QuakeEntry;

import java.util.Comparator;

/**
 * Created by alex on 10.04.17.
 */
public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    /*
    * Write the TitleAndDepthComparator class that implements a Comparator of type QuakeEntry.
    * In this class you should write the compare method that has two parameters,
    * a QuakeEntry named q1 and a QuakeEntry named q2. This method should compare the title of q1 and q2.
    * If q1’s title comes before q2’s title in alphabetical order, then this method should return a negative integer.
    * If q1’s title comes after q2’s title, then this method should return a positive integer.
    * If q1’s title is the same as q2’s title, then this method should compare the depth of the two earthquakes.
    * If q1’s depth is less than q2’s depth, then this method should return a negative number.
    * If q1’s depth is greater than q2’s depth, then this method should return a positive integer.
    * Otherwise, this method should return 0.
    */
    public int compare (QuakeEntry q1, QuakeEntry q2) {
        if (q1.getInfo().compareTo(q2.getInfo()) == 0) {
            return Double.compare(q1.getDepth(), q2.getDepth());
        }
            return q1.getInfo().compareTo(q2.getInfo());
    }
}
