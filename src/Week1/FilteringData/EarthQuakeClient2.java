package Week1.FilteringData;

import Week1.FilteringData.Filters.DepthFilter;
import Week1.FilteringData.Filters.DistanceFilter;
import Week1.FilteringData.Filters.MagnitudeFilter;
import Week1.FilteringData.Filters.PhraseFilter;
import Week1.provided.EarthQuakeParser;
import Week1.provided.Location;
import Week1.provided.QuakeEntry;

import java.util.ArrayList;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (f.satisfies(qe)) {
                answer.add(qe);
            }
        }

        return answer;
    }

    /*
    * Modify the code in the quakesWithFilter method in the EarthQuakeClient2 class to filter earthquakes using two
    * criteria, those with magnitude between 4.0 and 5.0 and depth between -35000.0 and -12000.0. You’ll need to use
    * both the MagnitudeFilter and the DepthFilter.
    *
    * Comment out the previous code in quakesWithFilter in the EarthQuakeClient2 class and add additional code to the
    * quakesWithFilter method in the EarthQuakeClient2 class to filter earthquakes using two criteria, those that are
    * 10,000,000 meters (10,000 km) from Tokyo, Japan whose location is (35.42, 139.43), and that are in Japan (this
    * means "Japan" should be the last word in the title).
    */
    public void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/Week1/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        Filter f1 = new MagnitudeFilter("magF", 3.5, 4.5);
        Filter f2 = new DepthFilter("depF", -55000.0, -20000.0);
        //Location denver = new Location(39.7392, -104.9903);
        //Filter f1 = new DistanceFilter("distF", denver, 1000000);
        //Filter f2 = new PhraseFilter("phraseF","a", "end");
        ArrayList<QuakeEntry> m7;
        m7 = this.filter(list, f1);
        m7 = this.filter(m7, f2);
        for (QuakeEntry qe : m7) {
            System.out.println(qe);
        }
        System.out.println(m7.size());
    }

    /*
    * Write a new void method named testMatchAllFilter in the EarthQuakeClient2 class. This method reads in earthquake
    * data from a source and stores them into an ArrayList of type QuakeEntry. Then it prints all the earthquakes and
    * how many earthquakes that were from the source. You should read in earthquakes from the small file
    * nov20quakedatasmall.atom, print all the earthquakes and also print how many there are. After this works you should
    * comment out the printing of all the earthquakes, but continue to print out the total number of earthquakes read
    * in. Then create a MatchAllFilter named maf and use the addFilter method to add three Filters to test the magnitude
    * between 0.0 and 2.0, to test the depth between -100000.0 and -10000.0, and if the letter “a” is in the title.
    * Then use filter(list, maf) to use all three filters and print out the resulting list of earthquakes.
    */
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/Week1/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter("magF", 1.0, 4.0));
        maf.addFilter(new DepthFilter("depthF", -180000.0, -30000.0));
        maf.addFilter(new PhraseFilter("phrF", "o", "any"));
        ArrayList<QuakeEntry> filtered = this.filter(list, maf);
        filtered.forEach(System.out::println);
        System.out.println(filtered.size());

    }

    /*
    * Write a new void method named testMatchAllFilter2 in the EarthQuakeClient2 class. This method should be identical
    * to the method testMatchAllFilter, but will create different Filters. You should read in earthquakes from the small
    * file nov20quakedatasmall.atom.Then create a MatchAllFilter named maf, and use the addFilter method to add three
    * Filters to test the magnitude between 0.0 and 3.0, to test for the distance from Tulsa, Oklahoma at location
    * (36.1314, -95.9372) is less than 10000000 meters (10000 km), and if the substring “Ca” is in the title. Then use
    * filter(list, maf) to use all three filters and print out the resulting list of earthquakes.
    */
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/Week1/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter("magF", 0.0, 5.0));
        Location billund = new Location(55.7308, 9.1153);
        maf.addFilter(new DistanceFilter("distF", billund, 3000000));
        maf.addFilter(new PhraseFilter("phrF", "e", "any"));
        ArrayList<QuakeEntry> filtered = this.filter(list, maf);
        filtered.forEach(System.out::println);
        System.out.println(filtered.size());
        System.out.println("Used filters: " + maf.getName());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
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

}
