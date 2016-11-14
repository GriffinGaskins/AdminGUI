package adminGui;

import java.io.FileReader;

import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.ScrollPaneConstants;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import adminGui_TermFrequency.*;
import GutenSearchGUI.*;
import Book.Book;
import Book.BookList;

/**
 * The Resources class.
 * 
 * @author Griffin Gaskins
 * @version 2
 * 
 *          This work complies with the JMU Honor Code.
 */
public class resources extends javax.swing.JFrame
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    private BookList mainList;

    /** The file name. */
    public static File fileName;

    /** The return value. */
    public static int returnVal;

    /** The temp string. */
    public String temp;

    /**
     * Creates new form resources.
     *
     * @param file
     *            the passed file
     */
    public resources(File file)
    {
        fileName = file;
        initComponents();

    }

    /**
     * Instantiates a new resources.
     */
    private resources()
    {
        throw new UnsupportedOperationException(
                "Feature Not Currently Implemented.");
    }

    @SuppressWarnings("unchecked")
    private void initComponents()
    {

        jMenuItem1 = new javax.swing.JMenuItem();
        fileChooser = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        textarea = new javax.swing.JTextArea();
        computeFile = new javax.swing.JButton();
        cancelUpload = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Open = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        UserMode = new javax.swing.JMenuItem();
        mainList = new BookList();

        jMenuItem1.setText("jMenuItem1");

        fileChooser.setDialogTitle("Choose a file for upload");
        fileChooser.setFileFilter(new MyCustomFilter());
        fileChooser.setMinimumSize(new java.awt.Dimension(300, 200));
        fileChooser.setPreferredSize(new java.awt.Dimension(500, 300));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textarea.setColumns(20);
        textarea.setRows(5);
        textarea.setEditable(true);
        jScrollPane1.setViewportView(textarea);
        jScrollPane1.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        computeFile.setText("Upload");
        computeFile.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                computeFileActionPerformed(evt);
            }
        });

        cancelUpload.setText("Cancel");

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenu1ActionPerformed(evt);
            }
        });

        Open.setAccelerator(
                javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F,
                        java.awt.event.InputEvent.CTRL_MASK));
        Open.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/adminGui/folder-open-add2.png")));
        Open.setText("Open");
        Open.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                OpenActionPerformed(evt);
            }
        });
        jMenu1.add(Open);

        Exit.setAccelerator(
                javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X,
                        java.awt.event.InputEvent.CTRL_MASK));
        Exit.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/adminGui/application_exit.png")));
        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ExitActionPerformed(evt);
            }
        });
        jMenu1.add(Exit);

        jMenuBar1.add(jMenu1);

        UserMode.setText("User Mode");
        UserMode.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                GutenSearchGUI gui = new GutenSearchGUI(mainList.list);
                gui.display();
            }
        });

        jMenu2.setText("Tools");
        jMenu2.add(UserMode);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
                getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                        .addComponent(jScrollPane1).addContainerGap())
                .addGroup(layout.createSequentialGroup().addGap(191, 191, 191)
                        .addComponent(computeFile).addGap(18, 18, 18)
                        .addComponent(cancelUpload)
                        .addContainerGap(272, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                        .addComponent(jScrollPane1,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 345,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(
                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout
                                .createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(computeFile)
                                .addComponent(cancelUpload))
                        .addContainerGap(29, Short.MAX_VALUE)));

        pack();
    }

    /**
     * Open action performed.
     *
     * @param evt
     *            the evt
     */
    private void OpenActionPerformed(java.awt.event.ActionEvent evt)
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        returnVal = fileChooser.showOpenDialog(null);

        System.out.println("You can edit the text before you upload it.");
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            {
                fileName = fileChooser.getSelectedFile();
                FileReader reader = null;
                try
                {
                    reader = new FileReader(fileName);
                    textarea.read(reader, null);
                    reader.close();
                } catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        } else
        {
            System.out.println("File access cancelled by user.");
        }

    }

    /**
     * Exit action performed.
     *
     * @param evt
     *            the evt
     */
    private void ExitActionPerformed(java.awt.event.ActionEvent evt)
    {
        System.exit(0);
    }

    /**
     * tools action performed.
     *
     * @param evt
     *            button pressed
     */
    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    /**
     * Upload/Compute file action performed.
     *
     * @param evt
     *            button pressed
     */
    private void computeFileActionPerformed(java.awt.event.ActionEvent evt)
    {
        try
        {
            Book book = new Book(fileName);
            // when a new book is added.
            if (fileName == null)
            {
                System.err.println("fileName is null");
            } else if (mainList.list == null)
            {
                System.err.println("mainBookList is null");
            }
            mainList.add(book);
            // save over the old mainBookList with newer version. Now,
            // mainBookList can be accessed from
            // search GUI on the same user session and can search newly uploaded
            // texts.
        } catch (NullPointerException e)
        {
            System.err.println("Loaded text was null");
        }

    }

    /**
     * The main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                    .getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }

            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(resources.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(resources.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(resources.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(resources.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() ->
        {
            new resources(fileName).setVisible(true);

        });

    }

    /** The Exit. */
    // Variables declaration
    private javax.swing.JMenuItem Exit;

    /** The Open. */
    private javax.swing.JMenuItem Open;

    /** The cancel upload. */
    private javax.swing.JButton cancelUpload;

    /** The compute file. */
    private javax.swing.JButton computeFile;

    /** The file chooser. */
    private javax.swing.JFileChooser fileChooser;

    /** The j menu1. */
    private javax.swing.JMenu jMenu1;

    /** The j menu2. */
    private javax.swing.JMenu jMenu2;

    /** The j menu bar1. */
    private javax.swing.JMenuBar jMenuBar1;

    /** The j menu item1. */
    private javax.swing.JMenuItem jMenuItem1;

    /** The j scroll pane1. */
    private javax.swing.JScrollPane jScrollPane1;

    /** The text area. */
    private javax.swing.JTextArea textarea;

    private javax.swing.JMenuItem UserMode;
    // End of variables declaration

}
