package Week3.WordGram;

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt " + index);
        }
        return myWords[index];
    }

    /*
    * Write the method length that has no parameters and returns the length of the WordGram.
    * This method has been started for you.
    */
    public int length() {
        return this.myWords.length;
    }

    /*
    * The method toString that has no parameters.
    * It prints a WordGram out, showing all the words in the WordGram on one line separated by spaces.
    * This method has been started for you.
    */
    public String toString() {
        String ret = "";
        for (int i = 0; i < this.length(); ++i) {
            ret = ret + this.wordAt(i) + " ";
        }

        return ret.trim();
    }

    /*
    * Write the method equals that has one parameter of type Object named o.
    * This method returns true if two WordGrams are equal and false otherwise. This method has been started for you.
    */
    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (this.length() != other.length()) return false;
        for (int i = 0; i < this.length(); ++i) {
            if (!this.wordAt(i).equals(other.wordAt(i))) return false;
        }
        return true;

    }

    /*
    * Write the method shiftAdd that has one String parameter word.
    * This method should return a new WordGram the same size as the original, consisting of each word shifted down one
    * index (for example the word in slot 1 would move to slot 0, the word in slot 2 would move to slot 1, etc.) and
    * word added to the end of the new WordGram. Be sure to test this method. For example, if a WordGram of size 4 is
    * “this” “is” “a” “test”, and shiftAdd is called with the argument “yes”, then the method would return a new
    * WordGram ”is” “a” “test” “yes”. This method should not alter the WordGram on which it is called.
    */
    public WordGram shiftAdd(String word) {
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        for (int i = 0; i < out.length() - 1; ++i) {
            out.myWords[i] = out.myWords[i + 1];
        }
        out.myWords[out.length() - 1] = word;
        return out;
    }

}