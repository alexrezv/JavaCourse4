package Week1.FilteringData.Filters;

import Week1.FilteringData.Filter;
import Week1.provided.QuakeEntry;

/**
 * Created by alex on 02.04.17.
 * <p>
 * Write the class DepthFilter that implements Filter. This class should include private instance variables for
 * a minimum and maximum depth, a constructor to initialize those variables, and a satisfies method that returns true
 * if a QuakeEntry’s depth is between the minimum and maximum depths, or equal to one of them. Otherwise it should
 * return false.
 */
public class DepthFilter implements Filter {
    private String name;
    private double minDepth, maxDepth;

    public DepthFilter(String filterName, double minDepth, double maxDepth) {
        this.name = filterName;
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
    }

    public String getName () {
        return this.name;
    }

    public boolean satisfies(QuakeEntry qe) {
        return this.minDepth <= qe.getDepth() && qe.getDepth() <= this.maxDepth;
    }
}
