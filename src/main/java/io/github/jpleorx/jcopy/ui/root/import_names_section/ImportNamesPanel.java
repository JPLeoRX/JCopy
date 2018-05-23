package io.github.jpleorx.jcopy.ui.root.import_names_section;

import io.github.jpleorx.jcopy.core.Cache;
import io.github.jpleorx.jcopy.helpers.TextFileParser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * One of the main 3 sections
 *
 * In this part of the UI we will ask the user to import a .txt with filenames
 * We will read the file, store a list of filenames and output the contents of this file to the user
 *
 * @author Leo Ertuna
 * @since 23.05.2018
 */
public class ImportNamesPanel extends JPanel {
    private JButton importButton;
    private JTextArea namesTextArea;
    private JScrollPane namesScrollPane;

    /**
     * Constructor
     */
    public ImportNamesPanel() {
        // Initialize
        this.init();
    }

    /**
     * Initialization
     */
    private void init() {
        // Set layout and border across this panel
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("List of names"));

        // Create import button
        importButton = new JButton("Import names list");
        importButton.addActionListener(new ImportButtonActionListener());

        // Create names text area
        namesTextArea = new JTextArea();
        namesTextArea.setEditable(false);

        // Make this text area scrollable
        namesScrollPane = new JScrollPane(namesTextArea);

        // Add components to this view
        this.add(importButton, BorderLayout.NORTH);
        this.add(namesScrollPane, BorderLayout.CENTER);
    }

    /**
     * Import button listener
     */
    private class ImportButtonActionListener implements ActionListener {
        private final JFileChooser fileChooser = new JFileChooser();

        /**
         * Constructor
         */
        public ImportButtonActionListener() {
            // Create new filter, so that we will allow only .txt files
            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Plain text files (.txt)", "txt");

            // Set the filter to file chooser
            fileChooser.setFileFilter(extensionFilter);
        }

        /**
         * On click
         * @param e action
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // Open the file chooser and Wait till it returns
            int returnValue = fileChooser.showOpenDialog(ImportNamesPanel.this);

            // If the user selected a file
            if (returnValue  == JFileChooser.APPROVE_OPTION) {
                // Extract the file
                File file = fileChooser.getSelectedFile();

                // Create a new text file parser and read this file
                TextFileParser textFileParser = new TextFileParser(file);
                textFileParser.read();

                // Display its contents in the text area
                namesTextArea.setText(textFileParser.getText());

                // Store the contents in the cache
                Cache.getInstance().setDestinationFilenames(textFileParser.getLines());
            }

            // If the user canceled the dialog
            else {
                // Do nothing
            }
        }
    }
}