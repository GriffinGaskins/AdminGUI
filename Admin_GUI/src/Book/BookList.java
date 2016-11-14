package Book;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Indexing.ParagraphIndexer;
import adminGui.resources;

public class BookList implements java.io.Serializable
{

    public ArrayList<Book> list = new ArrayList<Book>();
    public String name = "";

    public BookList()
    {
        loadResources();
    }

    public void add(Book b)
    {
        list.add(b);
        saveResources();
    }

    // Loads the mainBookList. This list keeps all books loaded into the
    // program.
    @SuppressWarnings("unchecked")
    private void loadResources()
    {
        File yourFile = new File("resources/mainList.ser");
        if (!yourFile.exists())
        {
            try
            {
                yourFile.createNewFile();
                File tempFile = new File("default.txt");
                Book tempBook = new Book(tempFile);
                add(tempBook);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream("resources/mainList.ser");
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        ObjectInputStream ois = null;
        try
        {
            ois = new ObjectInputStream(fis);
        } catch (IOException e1)
        {
            System.err.println("Error");
        }
        try
        {
            list = (ArrayList<Book>) ois.readObject();
        } catch (ClassNotFoundException | IOException e)
        {
            File file = new File("resources/mainList.ser");
            file.delete();
        }
    }

    private void saveResources()
    {
        // serialize data object to file
        ObjectOutputStream out = null;
        try
        {
            out = new ObjectOutputStream(
                    new FileOutputStream("resources/mainList.ser"));
            out.writeObject(list);
            out.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}