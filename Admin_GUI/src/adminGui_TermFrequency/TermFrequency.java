package adminGui_TermFrequency;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.io.PrintWriter;

/**
 * The TermFrequency class.
 * 
 * @author Griffin Gaskins
 * @version 2
 * 
 *          This work complies with the JMU Honor Code.
 */
public class TermFrequency implements java.io.Serializable
{

    /** The file. */
    private File file;
    private WordCounter counterPass;
    private HashMap<String, Integer> termMap;

    public TermFrequency(String paragraph)
    {
        WordCounter counter = new WordCounter();
        try
        {
            StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(paragraph));
            tokenizer.lowerCaseMode(true);
            tokenizer.ordinaryChar('.');
            int tokenType;
            do
            {
                tokenType = tokenizer.nextToken();
                if (tokenType == StreamTokenizer.TT_WORD
                        && 1 < tokenizer.sval.length())
                {
                    counter.add(tokenizer.sval);
                }
            }while (tokenType != StreamTokenizer.TT_EOF);
        }
        catch (IOException e)
        {
            System.err.println("Error reading text");
        }

        counterPass = counter;
    }
    /**
     * Instantiates a new term frequency.
     *
     * @param filePara
     *            the file parameter
     */
    public TermFrequency(File filePara)
    {
        // All wordcounter created files will be the original file name
        // concatenated with "_wordCounter.txt"
        file = filePara;

        if (file.getAbsolutePath() == null)
        {
            System.err.println("No input file");
            return;
        }
        WordCounter counter = new WordCounter();
        try
        {
            StreamTokenizer tokenizer = new StreamTokenizer(
                    new FileReader(file.getAbsolutePath()));
            tokenizer.lowerCaseMode(true);
            tokenizer.ordinaryChar('.');
            int tokenType;
            do
            {
                tokenType = tokenizer.nextToken();
                if (tokenType == StreamTokenizer.TT_WORD
                        && 1 < tokenizer.sval.length())
                {
                    counter.add(tokenizer.sval);
                }
            } while (tokenType != StreamTokenizer.TT_EOF);
        } catch (FileNotFoundException e)
        {
            System.err.println("No such file " + file.getAbsolutePath());
        } catch (IOException e)
        {
            System.err.println("Error reading file " + file.getAbsolutePath());
        }

        // Print out completion and serialize the uploaded file immediately.
        System.out.println(
                "File parsed and term frequency completed successfully.");
        counterPass = counter;

    }
    

    public HashMap<String, Integer> getHashMap()
    {
        return counterPass.getMap();
    }

    /**
     * Removes the extension.
     *
     * @param s
     *            the s
     * @return the string
     */
    public static String removeExtension(String s)
    {

        String temp = s;

        int dot = temp.lastIndexOf(".");
        if (dot == -1)
            return temp;

        return temp.substring(0, dot);
    }
}