package Week3.Word_NGrams;

/**
 * Write a description of class MarkovWordOne here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - 1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for (int k = 0; k < numWords - 1; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }

        return sb.toString().trim();
    }

    /*
    * Complete the method named getFollows that has been started for you with String parameter key.
    * This method should return an ArrayList of all the single words that immediately follow the key in the training
    * text. This method should call the indexOf method.
    */
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<>();
        int keyPosition = this.indexOf(this.myText, key, 0);
        while (keyPosition >= 0) {
            if (keyPosition + 1 == this.myText.length) break;
            follows.add(this.myText[keyPosition + 1]);
            keyPosition = this.indexOf(this.myText, key, ++keyPosition);
        }
        return follows;
    }

    /*
    * In the MarkovWordOne class, write the private method indexOf that has three parameters, a String array named
    * words, a String named target, and an integer named start. This method starts looking at the start position and
    * returns the first index location in words that matches target. If no word is found, then this method returns -1.
    */
    private int indexOf(String[] words, String target, int start) {
        for (int i = start; i < words.length; ++i) {
            if (words[i].equals(target)) return i;
        }
        return -1;
    }

    /*
    * In the MarkovWordOne class, write a public void method named testIndexOf that has no parameters.
    * This method is only for testing the indexOf method. This method should create a simple String array with the
    * words “this is just a test yes this is a simple test” then look for the words: “this” starting at 0, “this”
    * starting at 3, “frog” starting at 0, “frog” starting at 5, “simple” starting at 2 and “test” starting at 5.
    */
    public void testIndexOf() {
        String[] words = "this is just a test yes this is a simple test".split("\\s+");
        System.out.println(this.indexOf(words, "this", 0));
        System.out.println(this.indexOf(words, "this", 3));
        System.out.println(this.indexOf(words, "frog", 0));
        System.out.println(this.indexOf(words, "frog", 5));
        System.out.println(this.indexOf(words, "simple", 2));
        System.out.println(this.indexOf(words, "test", 5));

        /*System.out.println(this.getFollows("test"));
        System.out.println(this.getFollows("just"));
        System.out.println(this.getFollows("is"));*/
    }

}
