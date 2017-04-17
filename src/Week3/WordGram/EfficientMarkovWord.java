package Week3.WordGram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by alex on 17.04.17.
 * <p>
 * Write a new class named EfficientMarkovWord (make a copy of MarkovWord to start with) that implements IMarkovModel
 * and that builds a HashMap to calculate the follows ArrayList for each possible WordGram only once, and then uses the
 * HashMap to look at the list of characters following when it is needed.
 */
public class EfficientMarkovWord implements IMarkovModel {
    private int myOrder;
    private String[] myText;
    private Random myRandom;
    private HashMap<WordGram, ArrayList<String>> myMap;

    // A constructor with one integer parameter that is the order (how many words to use in prediction).
    // This method should initialize myOrder and myRandom.
    public EfficientMarkovWord(int order) {
        this.myOrder = order;
        this.myRandom = new Random();
        this.myMap = new HashMap<>();
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
    * The getRandomText method has one integer parameter named numWords.
    * This method generates and returns random text that has numWords words. This class generates each word by randomly
    * choosing a word from the training text that follows the current word(s) in the training text. When you copied the
    * body of MarkovWordOne into the MarkovWord class, you copied this method from MarkovWordOne. Much of the code from
    * the copied method will still be correct for MarkovWord, but you will need to make a few changes so that it works
    * for any order (not just order one), and uses WordGram objects. You may want to use the shiftAdd method you wrote
    * in WordGram.
    */
    public String getRandomText(int numWords) {
        this.buildMap();
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

    /*
    * a method named buildMap to build the HashMap (Be sure to handle the case at the end where there is not a follow
    * character. If that WordGram is not in the HashMap yet, then it should be put in mapped to an empty ArrayList. If
    * that key is already in the HashMap, then do not enter anything for this case.)
    */
    private void buildMap() {
        for (int i = 0; i < this.myText.length - this.myOrder + 1; ++i) {
            WordGram keyGram = new WordGram(this.myText, i, this.myOrder);
            myMap.putIfAbsent(keyGram, this.getFollows(keyGram));
        }
        System.out.println("********************** MAP INFO **********************");
        System.out.println("Number of keys: " + myMap.size());
        int maxSize = myMap.entrySet().stream()
                .map(a -> a.getValue().size())
                .max(Integer::compareTo)
                .orElse(-1);
        System.out.println("Size of the largest list: " + maxSize);
        myMap.entrySet().stream()
                .filter(a -> a.getValue().size() >= maxSize)
                .map(a -> a.getKey())
                .forEach(System.out::println);
        System.out.println("******************************************************");
    }

    // a getFollows method, but this getFollows method should be much shorter, as it can look up the WordGram,
    // instead of computing it each time.
    /*
    * The getFollows method has one WordGram parameter named kGram.
    * This method returns an ArrayList of all the singlewords that immediately follow an instance of the WordGram
    * parameter somewhere in the training text. This method should call indexOf to find these matches.
    */
    private ArrayList<String> getFollows(WordGram keyGram) {
        ArrayList<String> follows = new ArrayList<>();
        if (this.myMap.containsKey(keyGram)) {
            return this.myMap.get(keyGram);
        } //else { }
        int keyPosition = this.indexOf(this.myText, keyGram, 0);
        while (keyPosition >= 0) {
            if (keyPosition + keyGram.length() > this.myText.length) break;
            follows.add(this.myText[keyPosition + keyGram.length()]);
            keyPosition = this.indexOf(this.myText, keyGram, ++keyPosition);
        }
        return follows;
    }
}
