package Week3.Word_NGrams;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alex on 17.04.17.
 * <p>
 * Copy the code from MarkovWordOne into MarkovWordTwo, and then modify the code to work for two consecutive words.
 * You should have two keys: key1 and key2 that are consecutive words in the text, and then getFollows returns the list
 * of single words that follow these two words. For example if the text was “this is just a test yes this is a simple
 * test”, then getFollows of “this” “is” would return an ArrayList with “just” and “a”, and getFollows of “just” “a”
 * returns an ArrayList with “test”. Specifically, we will do it slightly differently than in the videos:
 * - getFollows should have two String parameters, key1 and key2
 * - indexOf should have four parameters: a String array words, a String target1, a String target2, and an integer
 * start. It returns the first location of target1 such that target2 immediately follows it, and the search starts
 * looking at index start.
 * - Be sure to test these methods.
 */
public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
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
        int index = myRandom.nextInt(myText.length - 2);  // random word to start with
        String key1 = myText[index];
        sb.append(key1);
        sb.append(" ");
        String key2 = myText[index + 1];
        sb.append(key2);
        sb.append(" ");

        for (int k = 0; k < numWords - 2; k++) {
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }

        return sb.toString().trim();
    }

    /*
    * Complete the method named getFollows that has been started for you with String parameter key.
    * This method should return an ArrayList of all the single words that immediately follow the key in the training
    * text. This method should call the indexOf method.
    */
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<>();
        int keyPosition = this.indexOf(this.myText, key1, key2, 0);
        while (keyPosition >= 0) {
            if (keyPosition + 1 == this.myText.length) break;
            follows.add(this.myText[keyPosition + 2]);
            keyPosition = this.indexOf(this.myText, key1, key2, ++keyPosition);
        }
        return follows;
    }

    /*
    * In the MarkovWordOne class, write the private method indexOf that has three parameters, a String array named
    * words, a String named target, and an integer named start. This method starts looking at the start position and
    * returns the first index location in words that matches target. If no word is found, then this method returns -1.
    */
    private int indexOf(String[] words, String target1, String target2, int start) {
        for (int i = start; i < words.length - 1; ++i) {
            if (words[i].equals(target1) && words[i + 1].equals(target2)) return i;
        }
        return -1;
    }
}
