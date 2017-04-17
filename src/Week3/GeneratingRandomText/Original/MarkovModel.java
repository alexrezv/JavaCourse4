package Week3.GeneratingRandomText.Original;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by alex on 16.04.17.
 * <p>
 * Create the class MarkovModel to use N characters to predict the next character. An integer should be passed in with
 * the constructor to specify the number of characters to use to predict the next character. Copy and paste in
 * MarkovFour and then modify it.
 */
public class MarkovModel {
    private int markovOrder;
    private String myText;
    private Random myRandom;

    public MarkovModel() {
        this.markovOrder = 0;
        this.myRandom = new Random();
    }

    public MarkovModel(int order) {
        this.markovOrder = order;
        this.myRandom = new Random();
    }

    public void setRandom(int seed) {
        this.myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        this.myText = s.trim();
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
            List follows = this.getFollows(sb.substring(i, i + this.markovOrder));
            //System.out.println(follows);
            if (follows.size() == 0) {
                break;
            }

            index = myRandom.nextInt(follows.size());
            sb.append(follows.get(index));
        }

        return sb.toString();
    }

    public List getFollows(String key) {
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
}
