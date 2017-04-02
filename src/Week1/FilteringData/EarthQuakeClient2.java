package Week1.FilteringData;

import Week1.FilteringData.Filters.DistanceFilter;
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
        String source = "src/Week1/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        //Filter f1 = new MagnitudeFilter(4.0, 5.0);
        //Filter f2 = new DepthFilter(-35000.0, -12000.0);
        Location myLocation = new Location(35.42, 139.43);
        Filter f1 = new DistanceFilter(myLocation, 10000000);
        Filter f2 = new PhraseFilter("Japan", "end");
        ArrayList<QuakeEntry> m7;
        m7 = this.filter(list, f1);
        m7 = this.filter(m7, f2);
        for (QuakeEntry qe : m7) {
            System.out.println(qe);
        }
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
