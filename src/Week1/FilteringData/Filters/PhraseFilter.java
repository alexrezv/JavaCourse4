package Week1.FilteringData.Filters;

import Week1.FilteringData.Filter;
import Week1.provided.QuakeEntry;

/**
 * Created by alex on 02.04.17.
 * Write the class PhraseFilter that implements Filter. This class should include two private instance variables for
 * 1) a String representing the type of request that indicates where to search in the title and has one of three values:
 * (“start”, ”end”, or “any”), and
 * 2) a String indicating the phrase to search for in the title of the earthquake (Note the title of the earthquake can
 * be obtained through the getInfo method).
 * This class also has a constructor to initialize those variables, and a satisfies method that returns true if the
 * phrase is found at the requested location in the title. If the phrase is not found, this method should return false.
 */
public class PhraseFilter implements Filter {
    private String phraseToSearch, requestType;
    private String name;

    public PhraseFilter(String filterName, String phraseToSearch, String requestType) {
        this.phraseToSearch = phraseToSearch;
        this.requestType = requestType;
        this.name = filterName;
    }

    public String getName() {
        return this.name;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getInfo().startsWith(this.phraseToSearch) && this.requestType.equals("start") ||
                qe.getInfo().endsWith(this.phraseToSearch) && this.requestType.equals("end") ||
                qe.getInfo().contains(this.phraseToSearch) && this.requestType.equals("any");
    }
}
