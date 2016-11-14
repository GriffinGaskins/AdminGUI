package GutenSearchGUI;

import static org.junit.Assert.*;	

import org.junit.Test;

import GutenSearchGUI.*;
import Book.*;

public class GutenSearchTest
{

    @Test
    public void search_keywords() throws IllegalSearchException
    {
        Search search = new Search("cat, hat, in the", null, null, null);
        assertEquals("cat", search.getKeywords()[0]);
        assertEquals("hat", search.getKeywords()[1]);
        assertEquals("in", search.getKeywords()[2]);
        assertEquals("the", search.getKeywords()[3]);
    }
    
    @Test(expected = IllegalSearchException.class)
    public void search_no_keywords() throws IllegalSearchException
    {
        new Search(null, null, null, null);
        new Search("", null, null, null);
        new Search("    ", null, null, null);
        new Search(null, "Cat in the Hat", "Dr. Seuss", "1952");
    }

    @Test
    public void search_intialization() throws IllegalSearchException
    {
        Search search = new Search("cat, hat, in the", "Cat in the Hat", "Dr. Seuss", "1952");
        assertEquals("Cat in the Hat", search.getTitle());
        assertEquals("Dr. Seuss", search.getAuthor());
        assertEquals("1952", search.getPublication());
    }
}
