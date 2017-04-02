package Week1.FilteringData;

import Week1.provided.QuakeEntry;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Created by alex on 02.04.17.
 * <p>
 * Write the class MatchAllFilter that implements Filter. This class has a private ArrayList of Filters that is
 * created in the constructor that has no parameters. This class has two methods,
 * 1) a void method named addFilter with one Filter parameter that adds the Filter to its private ArrayList, and
 * 2) a method named satisfies that has one QuakeEntry parameter and returns true if the QuakeEntry satisfies all the
 * filter conditions, otherwise it returns false.
 */
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;

    public MatchAllFilter() {
        this.filters = new ArrayList<>();
    }

    public void addFilter(Filter filter) {
        this.filters.add(filter);
    }

    public String getName() {
        ArrayList<String> filterNames = new ArrayList<>();
        filters.forEach(a -> filterNames.add(a.getName()));
        return filterNames.toString();
    }

    public boolean satisfies(QuakeEntry qe) {
        Predicate<Boolean> pb = v -> v == true;
        boolean b = this.filters.stream().map(a -> a.satisfies(qe)).allMatch(pb);
        return b;
    }
}
