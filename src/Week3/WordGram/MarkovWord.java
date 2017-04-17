package Week3.WordGram;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alex on 17.04.17.
 * <p>
 * Create the MarkovWord class that implements IMarkovModel.
 * This class should have three private variables, a String array named myText, a Random variable named myRandom, and
 * an integer variable named myOrder. This class should have the following methods, similar to what the MarkovWordOne
 * class had, but extended for handling a larger number of words. You should copy the body of MarkovWordOne and then
 * modify it.
 */
public class MarkovWord implements IMarkovModel {
    private int myOrder;
    private String[] myText;
    private Random myRandom;

    // A constructor with one integer parameter that is the order (how many words to use in prediction).
    // This method should initialize myOrder and myRandom.
    public MarkovWord(int order) {
        this.myOrder = order;
        this.myRandom = new Random();
    }

    /*
    * The void method setRandom has one integer parameter named seed.
    * Using this method will allow you to generate the same random text each time, which will help in testing your
    * program. This method should be the same as it was in MarkovWordOne.
    */
    public void setRandom(int seed) {
        this.myRandom = new Random(seed);
    }

    /*
    * The void method setTraining has one String parameter named text.
    * The String text is split into words and stored in myText. The words are used to initialize the training text.
    * This method should be the same as it was in MarkovWordOne.
    */
    public void setTraining(String text) {
        this.myText = text.split("\\s+");
    }

    /*
    * The indexOf method has three parameters, a String array of all the words in the training text named words,
    * a WordGram named target, and an integer named start indicating where to start looking for a WordGram match in
    * words. This method should return the first position from start that has words in the array words that match the
    * WordGram target. If there is no such match then return -1.
    */
    private int indexOf(String[] words, WordGram target, int start) {
        if (start >= words.length) return -1;
        for (int i = start; i < this.myText.length - this.myOrder; ++i) {
            WordGram candidate = new WordGram(this.myText, i, this.myOrder);
            if (candidate.equals(target)) return i;
        }
        return -1;
    }

    /*
    * The getFollows method has one WordGram parameter named kGram.
    * This method returns an ArrayList of all the singlewords that immediately follow an instance of the WordGram
    * parameter somewhere in the training text. This method should call indexOf to find these matches.
    */
    private ArrayList<String> getFollows(WordGram keyGram) {
        ArrayList<String> follows = new ArrayList<>();
        int keyPosition = this.indexOf(this.myText, keyGram, 0);
        while (keyPosition >= 0) {
            if (keyPosition + keyGram.length() > this.myText.length) break;
            follows.add(this.myText[keyPosition + keyGram.length()]);
            keyPosition = this.indexOf(this.myText, keyGram, ++keyPosition);
        }
        return follows;
    }

    /*
    * The getRandomText method has one integer parameter named numWords.
    * This method generates and returns random text that has numWords words. This class generates each word by randomly
    * choosing a word from the training text that follows the current word(s) in the training text. When you copied the
    * body of MarkovWordOne into the MarkovWord class, you copied this method from MarkovWordOne. Much of the code from
    * the copied method will still be correct for MarkovWord, but you will need to make a few changes so that it works
    * for any order (not just order one), and uses WordGram objects. You may want to use the shiftAdd method you wrote
    * in WordGram.
    */
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = this.myRandom.nextInt(myText.length - this.myOrder);  // random word to start with
        WordGram keyGram = new WordGram(this.myText, index, this.myOrder);
        sb.append(keyGram);
        sb.append(" ");

        for (int i = 0; i < numWords - keyGram.length(); ++i) {
            ArrayList<String> follows = this.getFollows(keyGram);
            if (follows.size() == 0) {
                break;
            }
            index = this.myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            keyGram = keyGram.shiftAdd(next);
        }

        return sb.toString().trim();
    }


}
