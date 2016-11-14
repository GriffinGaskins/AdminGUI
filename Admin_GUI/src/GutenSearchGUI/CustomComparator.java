package GutenSearchGUI;

import java.util.Comparator;
import Book.Book;
/**
 * Custom Comparator Class
 * 
 * @author Griffin Gaskins
 */

public class CustomComparator implements Comparator<Book> 
{
    private String keyword;
    
    public CustomComparator(String keyword)
    {
        this.keyword = keyword;
    }
    public int compare(Book b1, Book b2) 
    {
    	if(b1.getMap().get(keyword) == null){
    		b1.getMap().put(keyword, 0);
    	}
    	if(b2.getMap().get(keyword) == null){
    		b2.getMap().put(keyword, 0);
    	}
        return b1.getMap().get(keyword).compareTo(b2.getMap().get(keyword));
    }
}
