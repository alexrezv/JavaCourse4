package Week1.FilteringData;

import Week1.provided.QuakeEntry;

/**
 * Write a description of interface Filter here.
 *
 * @author AlexRezv
 * @version 2 April 2017
 */
public interface Filter {
    boolean satisfies(QuakeEntry qe);
}
