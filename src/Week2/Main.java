package Week2;

import Week2.ImplementingSelectionSort.QuakeSortInPlace;
import Week2.SortingAtScale.DifferentSorters;

/**
 * Created by alex on 05.04.17.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Week2");
        //QuakeSortInPlace qsip = new QuakeSortInPlace();
        //qsip.testSort();

        DifferentSorters ds = new DifferentSorters();
        //ds.sortWithCompareTo();
        //ds.sortByTitleAndDepth();
        ds.sortByLastWordInTitleThenByMagnitude();

    }
}
