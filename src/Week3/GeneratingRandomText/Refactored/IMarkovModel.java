package Week3.GeneratingRandomText.Refactored;

/**
 * Created by alex on 17.04.17.
 * The IMarkovModel interface. It has two signatures: the void method setTraining that has one String parameter named
 * text, and the method getRandomText that has one int parameter named numChars and returns a String.
 */
public interface IMarkovModel {
    void setTraining(String text);

    void setRandom(int seed);

    String getRandomText(int numChars);
}
