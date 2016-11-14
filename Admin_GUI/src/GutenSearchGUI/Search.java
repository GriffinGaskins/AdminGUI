package GutenSearchGUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import Book.Book;

/**
 * Search class.
 * 
 * @author Scott Woodgate & Griffin Gaskins
 */
public class Search
{
    private ArrayList<String> keywords;
    private String title;
    private String author;
    private String publication;

    /**
     * Search constructor.
     * 
     * @param keywords
     *            input keywords
     * @param title
     *            input title
     * @param author
     *            input author
     * @param publication
     *            input publication
     * @throws IllegalSearchException
     *             if keywords is null
     */
    public Search(String keywords, String title, String author,
            String publication) throws IllegalSearchException
    {
        if (keywords == null || keywords.isEmpty())
        {
            throw new IllegalSearchException();
        } else
        {
            String[] words = keywords.split("[,\\s]+");
            this.keywords = new ArrayList<String>(Arrays.asList(words));
            this.title = title;
            this.author = author;
            this.publication = publication;
        }
    }

    // return total int number
    /**
     * Search through a book.
     * 
     * @param lis
     *            list of books
     * @param keywords
     *            keywords input
     * @return map of search
     */
    public ArrayList<Book> search(ArrayList<Book> lis,
            ArrayList<String> keywords)
    {
        if (lis == null)
        {
            System.out.println("Book's List passed is null.");
            return null;
        }

        ArrayList<Book> results = new ArrayList<Book>();
        for (int x = 0; x < lis.size(); ++x)
        {
            int count = 0;
            Book book = lis.get(x);
            HashMap<String, Integer> tempMap = new HashMap<String, Integer>(
                    book.getMap());
            for (int i = 0; i < keywords.size(); ++i)
            {
                if (tempMap.containsKey((keywords.get(i).toLowerCase())))
                {
                    count += tempMap.get(keywords.get(i).toLowerCase());
                    book.weight += count;

                    // checks for existance in the results array
                    if (!results.contains(book))
                    {
                        results.add(book);
                    }
                }
            }
        }

        return results;
    }

    /**
     * Get all keywords.
     * 
     * @return array of keywords
     */
    public ArrayList<String> getKeywords()
    {
        return keywords;
    }

    /**
     * Get Title.
     * 
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Get Author.
     * 
     * @return the author
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * Get Publication date.
     * 
     * @return the publication date
     */
    public String getPublication()
    {
        return publication;
    }
}
