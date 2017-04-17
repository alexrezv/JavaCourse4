package Week3.GeneratingRandomText.Refactored;

import java.util.List;
import java.util.Random;

/**
 * Created by alex on 16.04.17.
 * <p>
 * Create the class MarkovFour to use four characters to predict the next character. Copy and paste in MarkovOne and
 * then modify it. You can watch the video “Implementing Order-Two” on how to create MarkovTwo from MarkovOne.
 */
public class MarkovFour extends AbstractMarkovModel {

    public MarkovFour() {
        myRandom = new Random();
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 4);
        String key = this.myText.substring(index, index + 4);
        sb.append(key);

        for (int i = 0; i < numChars - 4; i++) {
            //System.out.println("\'"+sb+"\'\tnew key \'"+sb.substring(i,i+4)+"\'");
            List follows = this.getFollows(sb.substring(i, i + 4));
            //System.out.println(follows);
            if (follows.size() == 0) {
                break;
            }

            index = myRandom.nextInt(follows.size());
            sb.append(follows.get(index));
        }

        return sb.toString();
    }

    public String toString() {
        return "MarkovModel of order 4";
    }
}
