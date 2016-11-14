package Indexing;

import java.io.*;
import java.util.*;

/*
ParagraphIndexer reads in a file and indexes each paragraph
(defined by new line character or a tab character). Contains
a constructor, two get methods, a method that returns an 
iterator over the Map, and one other useful method.
*/

/**
 * Paragraph Indexer.
 * 
 * @author Kyle Foster & Scott Woodgate
 */
public class ParagraphIndexer
{
    private FileReader fr;
    private BufferedReader br;
    private TreeMap<Integer, String> para;

    /**
     * A ParagraphIndexer Constructor.
     * 
     * @param fileName
     *            string path of the file
     */
    public ParagraphIndexer(String fileName)
    {
        String line, paragraph;
        int key = 0;
        try
        {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            para = new TreeMap<Integer, String>();
            paragraph = null;

            while ((line = br.readLine()) != null)
            {
                if (!line.equals(""))
                {
                    if (paragraph != null)
                    {
                        paragraph += " " + line;
                    } else
                    {
                        paragraph = line;
                    }

                    para.put(key, paragraph);
                } else
                {
                    if (para.get(key) != null)
                    {
                        key++;
                    }
                    paragraph = null;
                }
            }
            br.close();
        } catch (FileNotFoundException fnfe)
        {
            System.out.println("No file found.");
        } catch (IOException ioe)
        {
            System.out.println("Error while reading file '" + fileName + "'");
        }
    }

    /**
     * Get the paragraph.
     * 
     * @return TreeMap of the paragraph
     */
    public TreeMap<Integer, String> getPara()
    {
        return para;
    }

    /**
     * Get paragraph size.
     * 
     * @return size of the TreeMap
     */
    public int getSize()
    {
        return para.size();
    }
}