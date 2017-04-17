package Week3.GeneratingRandomText.Original;

/**
 * Write a description of class MarkovRunner here.
 *
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.FileResource;

public class MarkovRunner {
    /*
    * Modify the runMarkovZero method to call the setRandom method with the seed 42. Run this method at least twice.
    * What do you observe? Now change to seed to 101. Run it at least twice.
    * You should get different text than you got with the seed 42, but every time you run it you get the same text.
    */
    public void runMarkovZero() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovZero markov = new MarkovZero();
        markov.setTraining(st);
        markov.setRandom(1024);
        for (int k = 0; k < 1; k++) {
            String text = markov.getRandomText(300);
            printOut(text);
        }
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

    /*
    * In the class MarkovRunner, make a copy of the method runMarkovZero, and name this method runMarkovOne.
    * Then change the line MarkovZero markov = new MarkovZero(); to MarkovOne markov = new MarkovOne();.
    */
    public void runMarkovOne() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        markov.setRandom(365);
        for (int k = 0; k < 1; k++) {
            String text = markov.getRandomText(100);
            printOut(text);
        }
    }

    /*
    * In the MarkovRunner class, create the method runMarkovFour to generate random text using the MarkovFour class.*/
    public void runMarkovFour() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "this is a test yes this is a test";

        MarkovFour markov = new MarkovFour();
        markov.setTraining(st);
        markov.setRandom(715);

        for (int k = 0; k < 1; k++) {
            String text = markov.getRandomText(100);
            printOut(text);
        }
    }

    /*
    * In the MarkovRunner class, create the method runMarkovModel to generate random text using the MarkovModel class.*/
    public void runMarkovModel() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "this is a test yes this is a test";

        MarkovModel markov = new MarkovModel(7);
        markov.setTraining(st);
        markov.setRandom(953);

        for (int k = 0; k < 1; k++) {
            String text = markov.getRandomText(100);
            printOut(text);
        }
    }

}
