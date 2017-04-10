package Week1.FilteringData;

import provided.QuakeEntry;

/**
 * Write a description of interface Filter here.
 *
 * @author AlexRezv
 * @version 2 April 2017
 */
public interface Filter {
    boolean satisfies(QuakeEntry qe);

    /*
    * Modify the Filter interface to include a new method named getName that returns the name of the filter. The line
    * added to the Filter interface should be: public String getName(); What changes need to be made to all the Filter
    * classes? The user should be able to specify what they want the name of the filter to be when they create a
    * specific filter. For the MatchAllFilter class, a getName method should return a String of all the Filter names in
    * its ArrayList.
    */
    String getName();
}
