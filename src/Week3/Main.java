package Week3;

import Week3.Word_NGrams.MarkovRunner;

/**
 * Created by alex on 13.04.17.
 */
public class Main {
    public static void main(String[] args) {
        /*
        * Create MarkovZero generated texts by running the method runMarkovZero in MarkovRunner.
        * Run the program twice and note that the output is different each time you run it.
        */
        //MarkovRunner mr = new MarkovRunner();
        //mr.runMarkovModel();
        /*Try running the runMarkovOne method. It should compile and do exactly what runMarkovZero did.*/
        //mr.runMarkovModel();

        //Tester.testGetFollowsWithFile();

        /*MarkovRunnerWithInterface mrwi = new MarkovRunnerWithInterface();
        mrwi.runMarkov();*/

        MarkovRunner mr = new MarkovRunner();
        mr.runMarkovWordTwo();

    }
}
