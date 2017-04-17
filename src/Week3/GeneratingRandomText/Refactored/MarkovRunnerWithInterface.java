package Week3.GeneratingRandomText.Refactored;

import edu.duke.FileResource;

/**
 * Write a description of class MarkovRunner here.
 *
 * @author Duke Software
 * @version 1.0
 */

public class MarkovRunnerWithInterface {
    /*
    * A void method named runModel that has three parameters: an IMarkovModel variable named markov, a String named text
    * and an int named size. This method will work with any markov object that implements IMarkovModel.
    */
    private void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    /*
    * A void method named runMarkov. This method creates one of the types of Markov models, and calls runModel with it
    * to generate random text.
    */
    public void runMarkov() {
        /*FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;

        /*MarkovZero mz = new MarkovZero();
        runModel(mz, st, size,1);

        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size,1);

        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size,1);

        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size,1);*/

        this.testHashMap();
        //this.compareMethods();
    }

    //A void method named printOut that formats and prints the randomly generated text.
    private void printOut(String s) {
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for (int k = 0; k < words.length; k++) {
            System.out.print(words[k] + " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

    /*
    * Write a new method named testHashMap in the MarkovRunnerWithInterface class. This method should create an order-2
    * EfficientMarkovModel with
    * - seed 42
    * - the training text is “yes-this-is-a-thin-pretty-pink-thistle”
    * - the size of the text generated is 50
    * - Note that “le” occurs only once at the end of the training text
    */
    private void testHashMap() {
        FileResource fr = new FileResource();
        String text = fr.asString();
        text = text.replace('\n', ' ');
        EfficientMarkovModel emm = new EfficientMarkovModel(5);
        //text = "yes-this-is-a-thin-pretty-pink-thistle";
        this.runModel(emm, text, 50, 531);
        emm.printHashMapInfo();
    }

    /*
    * In the MarkovRunnerWithInterface class, create a void method named compareMethods that runs a MarkovModel and an
    * EfficientMarkovModel. In particular,
    * - Make both order-2 Markov models
    * - Use seed 42 and set the length of text to generate to be 1000
    * - Both should call runModel that generates random text three times for each.
    * Run the MarkovModel first and then the EfficientMarkovModel with the file “hawthorne.txt” as the training text.
    * One of them should be noticeably faster. You can calculate the time each takes by using System.nanoTime() for the
    * current time.
    */
    private void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');

        int order = 2;
        int size = 1000;
        int seed = 42;

        MarkovModel mm = new MarkovModel(order);
        EfficientMarkovModel emm = new EfficientMarkovModel(order);

        long start = System.nanoTime();
        this.runModel(mm, st, size, seed);
        System.out.println(System.nanoTime() - start);
        //3449264850
        start = System.nanoTime();
        this.runModel(emm, st, size, seed);
        System.out.println(System.nanoTime() - start);
        //3312369584

    }
}
