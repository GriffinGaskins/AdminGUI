package adminGui;

import java.io.File;

/**
 * The Class MyCustomFilter.
 *
 * @author Griffin Gaskins
 */
class MyCustomFilter extends javax.swing.filechooser.FileFilter {
        
        /* (non-Javadoc)
         * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
         */
        @Override
        public boolean accept(File file) {
            return file.isDirectory() || file.getAbsolutePath().endsWith(".txt");
        }
        
        /* (non-Javadoc)
         * @see javax.swing.filechooser.FileFilter#getDescription()
         */
        @Override
        public String getDescription() {
            return "Text documents (*.txt)";
        }
    } 