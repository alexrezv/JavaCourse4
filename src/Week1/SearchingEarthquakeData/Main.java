package Week1.SearchingEarthquakeData;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hi");
        EarthQuakeClient eQC = new EarthQuakeClient();
        //eQC.bigQuakes();
        //eQC.closeToMe(1000);
        //eQC.quakesOfDepth(-10000.0, -5000.0);
        eQC.quakesByPhrase("any", "Can");
    }
}