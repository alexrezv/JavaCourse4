package Week3.WordGram;

/**
 * Write a description of class MarkovRunner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

public class MarkovRunner {
    private void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    private void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for (int k = 0; k < 1; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovWord markovWord = new MarkovWord(5);
        runModel(markovWord, st, 500, 844);
    }

    public void runEfficientMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //String st = "this is a test yes this is really a test yes a test this is wow";
        EfficientMarkovWord markovWord = new EfficientMarkovWord(2);
        runModel(markovWord, st, 50, 65);
    }

    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');

        int order = 2;
        int size = 1000;
        int seed = 42;

        MarkovWord mw = new MarkovWord(order);
        EfficientMarkovWord emw = new EfficientMarkovWord(order);

        long start = System.nanoTime();
        this.runModel(mw, st, size, seed);
        System.out.println(System.nanoTime() - start);
        //8571995173
        start = System.nanoTime();
        this.runModel(emw, st, size, seed);
        System.out.println(System.nanoTime() - start);
        //157110905858
    }

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

}
