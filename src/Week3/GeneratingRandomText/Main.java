package Week3.GeneratingRandomText;


import Week3.GeneratingRandomText.Original.MarkovRunner;
import Week3.GeneratingRandomText.Refactored.MarkovRunnerWithInterface;

/**
 * Created by alex on 13.04.17.
 */
public class Main {
    public static void main(String[] args) {
        /*
        * Create MarkovZero generated texts by running the method runMarkovZero in MarkovRunner.
        * Run the program twice and note that the output is different each time you run it.
        */


        //mr.runMarkovModel();
        /*Try running the runMarkovOne method. It should compile and do exactly what runMarkovZero did.*/
        //mr.runMarkovModel();

        //Tester.testGetFollowsWithFile();

        /*MarkovRunnerWithInterface mrwi = new MarkovRunnerWithInterface();
        mrwi.runMarkov();*/

       /* MarkovRunner mr = new MarkovRunner();
        mr.runMarkovWordTwo();*/

        //final quiz
        MarkovRunner mr = new MarkovRunner();
        //mr.runMarkovZero();

        //Tester t1 = new Tester();
        //t1.testGetFollowsWithFile();

        //mr.runMarkovOne();
        //mr.runMarkovFour();
        //mr.runMarkovModel();

        MarkovRunnerWithInterface mrwi = new MarkovRunnerWithInterface();
        mrwi.runMarkov();


    }
}
