package Week3.GeneratingRandomText.Refactored;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by alex on 17.04.17.
 */
public abstract class AbstractMarkovModel implements IMarkovModel {
    // Two protected fields myText, a String, and myRandom, of type Random.
    protected String myText;
    protected Random myRandom;

    // A constructor that creates myRandom.
    public AbstractMarkovModel() {
        this.myRandom = new Random();
    }

    // A setTraining method that is public.
    // This method sets the the private String variable myText to the parameter text.
    public void setTraining(String text) {
        this.myText = text.trim();
    }

    /*
    * A signature for the abstract method getRandomText that has one integer parameter named numChars indicating the
    * length of the randomly generated text.
    */
    public abstract String getRandomText(int numChars);

    /*
    * The method getFollows you wrote is the same everywhere.
    * You should move it into the AbstractMarkovModel class and change this method from public to protected.
    */
    protected List getFollows(String key) {
        ArrayList<String> follows = new ArrayList<>();
        int keyPosition = this.myText.indexOf(key);
        while (keyPosition >= 0) {
            //if (keyPosition == this.myText.length()-1) break;
            if (keyPosition + key.length() == this.myText.length()) break;
            follows.add(this.myText.substring(keyPosition + key.length(), keyPosition + key.length() + 1));
            keyPosition = this.myText.indexOf(key, keyPosition + 1);
        }
        return follows;
    }

    public void setRandom(int seed) {
        this.myRandom = new Random(seed);
    }

}
