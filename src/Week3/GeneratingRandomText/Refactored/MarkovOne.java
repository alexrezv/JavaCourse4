package Week3.GeneratingRandomText.Refactored;

import java.util.List;
import java.util.Random;

/**
 * Created by alex on 13.04.17.
 * <p>
 * Create a new class called MarkovOne. Copy the body of MarkovZero into MarkovOne.
 * You’ll only need to change the name of the constructor to MarkovOne and add the same import that MarkovZero had,
 * and then it should compile. Right now, MarkovOne is only doing what MarkovZero did, since it is a copy of it.
 * We will fix it shortly to use one character to predict text.
 */
public class MarkovOne extends AbstractMarkovModel {

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    /*
    * In the class MarkovOne modify the method getRandomText so that it works for the way it should for MarkovOne.
    * It should predict the next character, by finding all the characters that follow the current character in the
    * training text, and then randomly picking one of them as the next character.
    */
    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 1);
        String key = this.myText.substring(index, index + 1);
        sb.append(key);

        for (int i = 0; i < numChars - 1; i++) {
            List follows = this.getFollows(sb.substring(i, i + 1));
            index = myRandom.nextInt(follows.size());
            sb.append(follows.get(index));
        }

        return sb.toString();
    }

    public String toString() {
        return "MarkovModel of order 1";
    }
}
