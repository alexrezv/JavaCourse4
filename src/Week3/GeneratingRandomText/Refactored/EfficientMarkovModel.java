package Week3.GeneratingRandomText.Refactored;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by alex on 17.04.17.
 * <p>
 * Write a new class named EfficientMarkovModel (make a copy of MarkovModel to start with) that extends
 * AbstractMarkovModel and that builds a HashMap to calculate the follows ArrayList for each possible substring only
 * once, and then uses the HashMap to look at the list of characters following when it is needed.
 * <p>
 * This class should include:
 * - a toString method to print that this is the EfficientMarkovModel class of a specific number
 * - a method named buildMap to build the HashMap (Be sure to handle the case where there may not be a follow character.
 * If that key is not in the HashMap yet, then it should be put in mapped to an empty ArrayList.) Think carefully
 * about where to call this method, considering that you will want to build a map for each new training text.
 * - a getFollows method, but this getFollows method should be much shorter, as it can look up the ArrayList of Strings,
 * instead of computing it each time.
 */

public class EfficientMarkovModel extends AbstractMarkovModel {

    private int markovOrder;
    private HashMap<String, List> substrToFollows;

    public EfficientMarkovModel() {
        this.markovOrder = 0;
        this.myRandom = new Random();
        this.substrToFollows = new HashMap<>();
    }

    public EfficientMarkovModel(int order) {
        this.markovOrder = order;
        this.myRandom = new Random();
        this.substrToFollows = new HashMap<>();
    }

    public String toString() {
        return "EfficientMarkovModel of order " + this.markovOrder;
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - this.markovOrder);
        String key = this.myText.substring(index, index + this.markovOrder);
        sb.append(key);

        for (int i = 0; i < numChars - this.markovOrder; i++) {
            //System.out.println("\'"+sb+"\'\tnew key \'"+sb.substring(i,i+4)+"\'");
            key = sb.substring(i, i + this.markovOrder);
            this.buildMap(key);
            //List follows = this.getFollows(sb.substring(i,i+this.markovOrder));
            //System.out.println(follows);
            if (this.substrToFollows.get(key).size() == 0) {
                break;
            }

            index = myRandom.nextInt(this.substrToFollows.get(key).size());
            sb.append(this.substrToFollows.get(key).get(index));
        }

        return sb.toString();
    }

    private void buildMap(String key) {
        this.substrToFollows.putIfAbsent(key, this.getFollows(key));
    }

    private void buildFullMap() {
        HashMap<String, List> fullMap = new HashMap<>();
        int order = this.markovOrder;
        for (int i = 0; i < this.myText.length() - this.markovOrder + 1; ++i) {
            String key = this.myText.substring(i, i + this.markovOrder);
            fullMap.putIfAbsent(key, this.getFollows(key));
        }
        System.out.println("Number of keys: " + fullMap.size());
        int maxSize = fullMap.entrySet().stream()
                .map(a -> a.getValue().size())
                .max(Integer::compareTo)
                .orElse(-1);
        System.out.println("Size of the largest list: " + maxSize);
        fullMap.entrySet().stream()
                .filter(a -> a.getValue().size() >= maxSize)
                .map(a -> a.getKey())
                .forEach(System.out::println);
    }

   /* protected List getFollows(String key) {
        ArrayList<String> follows = new ArrayList<>();
        int keyPosition = this.myText.indexOf(key);
        while (keyPosition >= 0) {
            //if (keyPosition == this.myText.length()-1) break;
            if (keyPosition+key.length() == this.myText.length()) break;
            follows.add(this.myText.substring(keyPosition+key.length(), keyPosition+key.length()+1));
            keyPosition = this.myText.indexOf(key, keyPosition+1);
        }
        return follows;
    }*/

    /*
    * To test your HashMap to make sure it is built correctly, write the void method printHashMapInfo in the
    * EfficientMarkovModel class. Make sure to call this method immediately after building the map. This method should
    * print out the following information about the HashMap:
    * - Print the HashMap (all the keys and their corresponding values). Only do this if the HashMap is small.
    * - Print the number of keys in the HashMap
    * - Print the size of the largest value in the HashMap—that is, the size of the largest ArrayList of characters
    * - Print the keys that have the maximum size value.
    */
    public void printHashMapInfo() {
        System.out.println("********** NECESSARY **********");
        if (this.substrToFollows.size() <= 10) {
            System.out.println(this.substrToFollows);
        }
        System.out.println("Number of keys: " + this.substrToFollows.size());
        int maxSize = this.substrToFollows.entrySet().stream()
                .map(a -> a.getValue().size())
                .max(Integer::compareTo)
                .orElse(-1);
        System.out.println("Size of the largest list: " + maxSize);
        this.substrToFollows.entrySet().stream()
                .filter(a -> a.getValue().size() >= maxSize)
                .map(a -> a.getKey())
                .forEach(System.out::println);
        // zaebali pizdec
        System.out.println("********** FULL **********");
        this.buildFullMap();
    }
}
