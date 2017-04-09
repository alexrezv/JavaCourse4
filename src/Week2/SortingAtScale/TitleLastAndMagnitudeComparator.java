package Week2.SortingAtScale;

import provided.QuakeEntry;

import java.util.Comparator;

/**
 * Created by alex on 10.04.17.
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    /*
    * Write the TitleLastAndMagnitudeComparator class that implements a Comparator of type QuakeEntry.
    * In this class you should write the compare method that has two parameters, a QuakeEntry  q1 and a QuakeEntry  q2.
    * This method should compare the last word in the title of q1 and q2.
    * If q1’s last word comes before q2’s last word in alphabetical order, then this method should return a neg integer.
    * If q1’s last word comes after q2’s last word, then this method should return a positive integer.
    * If q1’s last word is the same as q2’s, then this method should compare the magnitude of the two earthquakes.
    * If q1’s magnitude is less than q2’s magnitude, then this method should return a negative number.
    * If q1’s magnitude is greater than q2’s magnitude, then this method should return a positive integer.
    * Otherwise, this method should return 0.
    */
    public int compare (QuakeEntry q1, QuakeEntry q2) {
        /*System.out.println(q1.getInfo().substring(q1.getInfo().lastIndexOf(" "))
                + " " + q2.getInfo().substring(q2.getInfo().lastIndexOf(" ")));*/
        if (q1.getInfo()
                .substring(q1.getInfo().lastIndexOf(" "))
                .compareTo(
                        q2.getInfo()
                                .substring(q2.getInfo().lastIndexOf(" "))
                ) == 0) {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return q1.getInfo().substring(q1.getInfo().lastIndexOf(" "))
                .compareTo(q2.getInfo().substring(q2.getInfo().lastIndexOf(" ")));
    }
}
