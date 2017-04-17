package Week3.GeneratingRandomText.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 16.04.17.
 */
public class Test {
    private String myText = "this is a test yes this is a test.";

    public List getFollows(String key) {
        ArrayList<String> follows = new ArrayList<>();
        int keyPosition = this.myText.indexOf(key);
        while (keyPosition >= 0) {
            follows.add(this.myText.substring(keyPosition + key.length(), keyPosition + key.length() + 1));
            keyPosition = this.myText.indexOf(key, keyPosition + 1);
        }
        return follows;
    }


}
