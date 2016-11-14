package GutenSearchGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.html.HTMLDocument.Iterator;

import Book.Book;
import adminGui_TermFrequency.TermFrequency;

/**
 * User GUI.
 * 
 * @author Scott Woodgate
 */
public class GutenSearchGUI
{
    private JFrame frame;
    private JButton btnQuery;
    private JButton btnClear;
    private JTextField txtKeyword;
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtPublication;
    JScrollPane scroll;
    private ArrayList<Book> myList;

    /**
     * GutenSearch gui Constructor.
     * 
     * @param list
     *            arrayList of books
     */
    public GutenSearchGUI(ArrayList<Book> list)
    {
        myList = list;
        JLabel lblKeyword = new JLabel("Keyword(s):");
        JLabel lblTitle = new JLabel("Title:");
        JLabel lblAuthor = new JLabel("Author:");
        JLabel lblPublication = new JLabel("Publication:");

        JPanel queryPanel = new JPanel();
        JPanel keywordPanel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel authorPanel = new JPanel();
        JPanel publicationPanel = new JPanel();
        JPanel resultPanel = new JPanel();

        scroll = new JScrollPane();
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setViewportBorder(new LineBorder(Color.RED));

        frame = new JFrame("Guten Search");
        frame.setMinimumSize(new Dimension(447, 515));
        frame.setLocation(500, 100);

        btnQuery = new JButton("Query");
        btnClear = new JButton("Clear Query");

        txtKeyword = new JTextField();
        txtTitle = new JTextField();
        txtAuthor = new JTextField();
        txtPublication = new JTextField();

        scroll.setPreferredSize(new Dimension(447, 300));

        txtKeyword.setPreferredSize(new Dimension(350, 27));
        txtTitle.setPreferredSize(new Dimension(390, 27));
        txtAuthor.setPreferredSize(new Dimension(375, 27));
        txtPublication.setPreferredSize(new Dimension(350, 27));

        queryPanel.setLayout(new BoxLayout(queryPanel, BoxLayout.PAGE_AXIS));

        keywordPanel.setLayout(new FlowLayout());
        titlePanel.setLayout(new FlowLayout());
        authorPanel.setLayout(new FlowLayout());
        publicationPanel.setLayout(new FlowLayout());

        keywordPanel.add(lblKeyword);
        keywordPanel.add(txtKeyword);

        titlePanel.add(lblTitle);
        titlePanel.add(txtTitle);

        authorPanel.add(lblAuthor);
        authorPanel.add(txtAuthor);

        publicationPanel.add(lblPublication);
        publicationPanel.add(txtPublication);

        resultPanel.add(btnClear);
        resultPanel.add(btnQuery);

        queryPanel.add(keywordPanel);
        queryPanel.add(titlePanel);
        queryPanel.add(authorPanel);
        queryPanel.add(publicationPanel);
        queryPanel.add(resultPanel);

        frame.add(queryPanel, BorderLayout.CENTER);
        frame.add(scroll, BorderLayout.SOUTH);

        btnQuery.addActionListener(new ButtonPress());
        btnClear.addActionListener(new ButtonPress());
    }

    /**
     * Displays User GUI.
     */
    public void display()
    {
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Button Action.
     * 
     * @author Scott Woodgate
     */
    private class ButtonPress implements ActionListener
    {

        /**
         * Preform button action.
         * 
         * @param e
         *            button action event
         */
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == btnQuery)
            {
                // create new array list to pass to search function.
                ArrayList<String> searchValues = new ArrayList<String>();
                ArrayList<Book> results = new ArrayList<Book>();

                // create new search object
                Search search = null;
                try
                {
                    search = new Search(txtKeyword.getText(),
                            txtTitle.getText(), txtAuthor.getText(),
                            txtPublication.getText());
                } catch (IllegalSearchException e1)
                {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(frame,
                            "Error: No keyword entered!", "Keyword Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                // split keywords
                String[] words = txtKeyword.getText().split("[,\\s]+");

                // create new searchValues ArrayList
                searchValues = new ArrayList<String>(Arrays.asList(words));

                // search for valuesadd
                results = search.search(myList, searchValues);

                // sort result in descending order
                Collections.sort(results, new CustomComparator(words[0]));

                // here we have a list of sorted values.

                // go through the list one by one and if the title of the book
                // equals
                // the title of the title search, move that book to the front of
                // the list.

                /*ArrayList<Book> finalList = new ArrayList<Book>();
                if (txtTitle.getText() != null || txtAuthor.getText() != null
                        || txtPublication.getText() != null)
                {
                    for (int i = 0; i < results.size(); i++)
                    {
                        if (txtTitle.getText() != null
                                && txtAuthor.getText() != null
                                && txtPublication.getText() != null)
                        {
                            if (results.get(i).title
                                    .equalsIgnoreCase(txtTitle.getText())
                                    && results.get(i).author.equalsIgnoreCase(
                                            txtAuthor.getText())
                                    && results.get(i).publisher
                                            .equalsIgnoreCase(
                                                    txtPublication.getText()))
                            {
                                finalList.add(results.get(i));
                            }
                        } else if (txtTitle.getText() != null
                                && txtAuthor.getText() != null)
                        {
                            if (results.get(i).title
                                    .equalsIgnoreCase(txtTitle.getText())
                                    && results.get(i).author.equalsIgnoreCase(
                                            txtAuthor.getText()))
                            {
                                finalList.add(results.get(i));
                            }
                        } else if (txtAuthor.getText() != null
                                && txtPublication.getText() != null)
                        {
                            if (results.get(i).author
                                    .equalsIgnoreCase(txtAuthor.getText())
                                    && results.get(i).publisher
                                            .equalsIgnoreCase(
                                                    txtPublication.getText()))
                            {
                                finalList.add(results.get(i));
                            }
                        } else if (txtTitle.getText() != null
                                && txtPublication.getText() != null)
                        {
                            if (results.get(i).title
                                    .equalsIgnoreCase(txtTitle.getText())
                                    && results.get(i).publisher
                                            .equalsIgnoreCase(
                                                    txtPublication.getText()))
                            {
                                finalList.add(results.get(i));
                            }
                        } else if (txtTitle.getText() != null)
                        {
                            if (results.get(i).title
                                    .equalsIgnoreCase(txtTitle.getText()))
                            {
                                finalList.add(results.get(i));
                            }
                        } else if (txtAuthor.getText() != null)
                        {
                            if (results.get(i).author
                                    .equalsIgnoreCase(txtTitle.getText()))
                            {
                                finalList.add(results.get(i));
                            }
                        } else if (txtPublication.getText() != null)
                        {
                            if (results.get(i).publisher
                                    .equalsIgnoreCase(txtTitle.getText()))
                            {
                                finalList.add(results.get(i));
                            }
                        }
                    }
                }*/

                // when this ends, you will have a list sorted first by, word,
                // then by title.

                // for each result value
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                for (int i = 0; i < results.size(); ++i)
                {
                    String paragraphToDisplay = new String();
                    int currentLargest = 0;
                    int currentIndex = 0;
                    // go through the results indexed paragraph TreeMap
                    for (Map.Entry<Integer, String> entry : results
                            .get(i).myIndex.entrySet())
                    {
                        Integer key = entry.getKey();
                        String value = entry.getValue();

                        currentIndex = key;

                        // Create a new termFrequency object passing in the
                        // paragraph (aka. value)
                        TermFrequency freak = new TermFrequency(value);

                        // Get the produced hashmap
                        HashMap<String, Integer> processMap = freak
                                .getHashMap();

                        // counter total number of equivalent words from each
                        // paragraph.
                        // Keep a running count of the max paragraph, outside of
                        // the for loop.
                        int counter = 0;
                        for (int x = 0; x < searchValues.size(); ++x)
                        {
                            if (processMap.containsKey(searchValues.get(x)))
                            {
                                counter += processMap.get(searchValues.get(x));
                            }
                        }

                        // Running max counter of largest likeness. Saves
                        // paragraph, paragraph id (key)
                        if (counter > currentLargest)
                        {
                            paragraphToDisplay = value;
                            key = currentIndex;
                            currentLargest = counter;
                        }
                    }

                    // print out the best map for this book.
                    System.out.println(results.get(i).toString());
                    System.out.println(
                            currentIndex + " => " + paragraphToDisplay);

                    // THIS IS THE AREA WHERE YOU SHOULD DO THE FOLLOWING

                    // CREATE A NEW GUI COMPONENT TO ADD TO SCROLLPANE (scroll)

                    // ADD IN ALL VALUES TITLES/AUTHOR/SHORT-FORM PARAGRAPH

                    // ADD IT TO THE SCROLLPANE

                    // VALIDATE/REPAINT/DOLAYOUT <- one of these functions
                    // basically tells the gui to update what it should be
                    // drawing out, too tired to rememmber.
                    // AFTER YOU CALL THIS, THE COMPONENTS SHOULD POP UP IN THE
                    // SEARCH RESULTS FEILD UNDER THE TEXT BOXES
                    JTextArea text = new JTextArea(results.get(i).toString()
                            + "\n" + paragraphToDisplay.substring(0,
                                    paragraphToDisplay.length() / 2));
                    text.setPreferredSize(new Dimension(336, 69));
                    text.setLineWrap(true);
                    text.setEditable(false);
                    JButton button = new JButton("Expand");

                    final String finalbook = paragraphToDisplay;
                    final String finaltitle = String.format("%s [%d]",
                            results.get(i).title, currentIndex);

                    class ExpandButton implements ActionListener
                    {

                        public void actionPerformed(ActionEvent e)
                        {

                            JFrame book = new JFrame(finaltitle);
                            book.setPreferredSize(new Dimension(500, 500));
                            JTextArea full = new JTextArea(finalbook);
                            full.setLineWrap(true);
                            full.setEditable(false);
                            book.add(full);
                            book.pack();
                            book.setVisible(true);
                        }

                    }

                    button.addActionListener(new ExpandButton());
                    JPanel textandbutton = new JPanel();
                    textandbutton.setLayout(new FlowLayout());
                    textandbutton.add(text);
                    textandbutton.add(button);
                    panel.add(textandbutton);
                    scroll.setViewportView(panel);
                }

            } else
            {
                txtKeyword.setText(null);
                txtTitle.setText(null);
                txtAuthor.setText(null);
                txtPublication.setText(null);
                scroll.setViewportView(null);
            }

        }

    }
}
