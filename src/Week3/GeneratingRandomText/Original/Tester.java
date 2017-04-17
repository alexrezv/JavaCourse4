package Week3.GeneratingRandomText.Original;

import edu.duke.FileResource;

/**
 * Created by alex on 16.04.17.
 */
public class Tester {
    /*
    * Create a new class in this project named Tester and a void method in this class named testGetFollows with no
    * parameters. This method should create a MarkovOne object, set the training text as “this is a test yes this is
    * a test.”. Then have it call getFollows and print out the resulting ArrayList and also its size. Be sure to test
    * it on the three examples above and also on the Strings “.” and “t.”, which occur at the end of the String.
    */
    public static void testGetFollows() {
        MarkovOne mo = new MarkovOne();
        mo.setTraining("this is a test yes this is a test.");
        System.out.println(mo.getFollows("t"));
        System.out.println(mo.getFollows("e"));
        System.out.println(mo.getFollows("es"));
        System.out.println(mo.getFollows("."));
        System.out.println(mo.getFollows("t."));
    }

    /*
    * Now let’s test getFollows on a file. In the Tester class, write the void method testGetFollowsWithFile with no
    * parameters. This method should create a MarkovOne object, set the training text to a file the user selects
    * (similar to the methods in MarkovRunner), and then call getFollows. Run your program on confucius.txt and look for
    * the characters that follow “t”. You should get 11548.
    */
    public static void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne mo = new MarkovOne();
        mo.setTraining(st);
        System.out.println(mo.getFollows("th"));
        System.out.println(mo.getFollows("th").size());
    }
}
