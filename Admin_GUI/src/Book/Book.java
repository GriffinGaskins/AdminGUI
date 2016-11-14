/**
 * The Book class.
 * 
 * @author Griffin Gaskins
 * @version 2
 * 
 *          This work complies with the JMU Honor Code.
 */
package Book;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import Indexing.ParagraphIndexer;
import adminGui_TermFrequency.TermFrequency;

// TODO: Auto-generated Javadoc
/**
 * The Class Book.
 */
public class Book implements java.io.Serializable
{

    /** The my freq map. */
    // using hashmap instead of tree for amortized constant access time
    private HashMap<String, Integer> myFreqMap;

    public File myFile;

    public TreeMap<Integer, String> myIndex;

    /** The release date. */
    public String title, publisher, author, releaseDate;

    public int weight;

    /**
     * Instantiates a new book.
     */
    public Book()
    {
        System.err.println("Must initialize new Book class with file.");
        this.weight = 0;
    }

    /**
     * Instantiates a new book.
     *
     * @param file
     *            the file
     */
    public Book(File file)
    {
        this.myFile = file;
        this.title = setTitle();
        this.author = setAuthor();
        TermFrequency termFreak = new TermFrequency(myFile);
        this.myFreqMap = termFreak.getHashMap();
        ParagraphIndexer index = new ParagraphIndexer(myFile.getAbsolutePath());
        this.myIndex = index.getPara();
    }

    /**
     * Sets the title.
     *
     * @param book
     *            the book
     * @return true, if successful
     */
    String setTitle()
    {
        try
        {
            Scanner scanner = new Scanner(myFile);

            // now read the file line by line...
            int lineNum = 0;
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                lineNum++;
                if (line.contains("Title"))
                {
                    String temp = line.substring(6, line.length());
                    return temp;
                }
            }
        } catch (FileNotFoundException e)
        {
            // handle this
        }
        return null;

    }

    /**
     * Sets the release date.
     *
     * @param book
     *            the book
     * @return true, if successful
     */
    boolean setReleaseDate(Book book)
    {
        // change true to asses book title retrieval;
        // if(scrpaer.releaseDateFound())
        // {
        // this.releaseDate = scraper.getReleaseDate();
        // return true;
        // } else
        return false;

    }

    /**
     * Sets the pub.
     *
     * @param book
     *            the book
     * @return true, if successful
     */
    boolean setPub(Book book)
    {
        // change true to asses book title retrieval;
        // if(scrpaer.publisherFound())
        // {
        // this.publisher = scraper.getPublisher();
        // return true;
        // } else
        return false;

    }

    /**
     * Sets the author.
     *
     * @param book
     *            the book
     */
    String setAuthor()
    {
        try
        {
            Scanner scanner = new Scanner(myFile);

            // now read the file line by line...
            int lineNum = 0;
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                lineNum++;
                if (line.contains("Author"))
                {
                    String newLine = line.substring(8, line.length());
                    return newLine;
                }
            }
        } catch (FileNotFoundException e)
        {
            // handle this
        }
        return null;
    }

    public HashMap<String, Integer> getMap()
    {
        return this.myFreqMap;
    }

    public TreeMap<Integer, String> getTree()
    {
        return myIndex;
        
    }
    
    public String toString()
    {
        String ret = new String();
        ret = this.title + "  By: "
                + this.author;
        return ret;
    }

}
