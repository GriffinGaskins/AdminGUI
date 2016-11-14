import static org.junit.Assert.*;		

import java.io.File;
import java.util.Set;

import org.junit.Test;

import Book.Book;
import GutenSearchGUI.IllegalSearchException;
import GutenSearchGUI.Search;
import Indexing.ParagraphIndexer;
import adminGui.resources;

public class GutenTest
{

    @Test
    public void resourceMain()
    {
        File file = new File("bin/Sherlock.txt");
        new resources(file);
    }
    
    @Test
    public void searcherTest()
    {
        //
    }
    
    @Test
    public void search_keywords() throws IllegalSearchException
    {
        Search search = new Search("cat, hat, in the", null, null, null);
        assertEquals("cat", search.getKeywords().get(0));
        assertEquals("hat", search.getKeywords().get(1));
        assertEquals("in", search.getKeywords().get(2));
        assertEquals("the", search.getKeywords().get(3));
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
    
    @Test
    public void createBook()
    {
        File file = new File("bin/Sherlock.txt");
        new Book();
        new Book(file);
    }
    
    @Test
    public void createIndexer()
    {
        new ParagraphIndexer("bin/Holmes.txt");
        new ParagraphIndexer("bin/Sherlock.txt");
    }
    
    @Test
    public void indexGetters()
    {
        ParagraphIndexer para = new ParagraphIndexer("bin/Sherlock.txt");
        assertEquals(para.getSize(), 2604);
        
        Set<Integer> keys = para.getPara().keySet();
        
        for(Integer key : keys)
        {
            System.out.println(key + " " + para.getPara().get(key));
        }
    }


}
