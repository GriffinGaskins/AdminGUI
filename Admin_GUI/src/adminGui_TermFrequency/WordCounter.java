package adminGui_TermFrequency;

import java.util.HashMap;
import java.lang.Integer;
import java.util.Iterator;

/**
 * The WordCounter class.
 * 
 * @author Griffin Gaskins
 * @version 1
 * 
 *          This work complies with the JMU Honor Code.
 */

public class WordCounter implements Iterable<String>, java.io.Serializable
{

    /** The word count. */
    private HashMap<String, Integer> wordCount;

    /**
     * 
     * WordCounter constructor.
     * 
     */
    public WordCounter()
    {
        wordCount = new HashMap<String, Integer>();
    }

    /**
     * Determines if a string has been added, if so, increments count if not,
     * adds a new word.
     * 
     * @param word
     *            A string representing the parsed word.
     */
    public void add(String word)
    {
        Integer count = 0;

        if (wordCount.containsKey(word))
        {
            count = wordCount.get(word);
        }
        wordCount.put(word, count + 1);
    }

    /**
     * Finds a given word and returns the mapped Integer value. If there is no
     * word, returns 0
     * 
     * @param word
     *            A string representing the parsed word.
     * @return returns the Integer mapped value of the word, or 0.
     */
    public int count(String word)
    {
        int ret = 0;
        if (wordCount.containsKey(word))
        {
            ret = wordCount.get(word);
        }
        return ret;

    }

    /**
     * Creates and returns a string iterator that prints the values and keys of
     * the map.
     *
     * @return the iterator
     * @returns An iterator that will print the string and mapped count value
     */
    public Iterator<String> iterator()
    {

        return wordCount.keySet().iterator();
    }

    /**
     * Returns the number of strings in the map.
     * 
     * @return returns number of strings.
     */
    public int size()
    {
        return wordCount.size();
    }

    /**
     * Gets the map.
     *
     * @return the map
     */
    public HashMap<String, Integer> getMap()
    {
        return this.wordCount;
    }

}
