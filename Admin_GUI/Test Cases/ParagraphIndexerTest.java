package Indexing;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class ParagraphIndexerTest
{

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
