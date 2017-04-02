package Week1;

import Week1.FilteringData.EarthQuakeClient2;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hi");
        /*SearchingEarthquakesDAta*/
        //EarthQuakeClient eQC = new EarthQuakeClient();
        //eQC.quakesOfDepth(-10000.0, -8000.0);
        //eQC.quakesByPhrase("start", "Explosion");
        //eQC.quakesByPhrase("end", "California");
        //eQC.quakesByPhrase("any", "Creek");

        //eQC.bigQuakes(5.0);
        //eQC.closeToMe(1000);
        //ClosestQuakes cQ = new ClosestQuakes();
        //cQ.findClosestQuakes();
        //LargestQuakes lQ = new LargestQuakes();
        //lQ.findLargestQuakes(3);
        //lQ.findLargestQuakes(5);

        /*FilteringData*/
        EarthQuakeClient2 eQC2 = new EarthQuakeClient2();
        //eQC2.quakesWithFilter();
        //eQC2.testMatchAllFilter();
        eQC2.testMatchAllFilter2();

    }
}