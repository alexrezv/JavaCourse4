package Week3.GeneratingRandomText.Original;

import java.util.ArrayList;
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
public class MarkovOne {

    private String myText;
    private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
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

    /*
    * In the class MarkovOne, write the method getFollows that has one String parameter named key.
    * This method should find all the characters from the private variable myText in MarkovOne that follow key and put
    * all these characters into an ArrayList and then return this ArrayList. This algorithm for this method was
    * described in “Finding Follow Sets.” For example, if myText were “this is a test yes this is a test.”,
    * then the call getFollows(“t”) should return an ArrayList with the Strings “h”, “e”, “ “, “h”, “e”, “.” as “t”
    * appears 6 times. The call getFollows(“e”) should return an ArrayList with the Strings “s”, “s”, “s”.
    * Your method should work even if key is a word. Thus, getFollows(“es”) should return an ArrayList with the
    * Strings “t”, “ “, “t”. Next we will write a method to test this method.
    */

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
