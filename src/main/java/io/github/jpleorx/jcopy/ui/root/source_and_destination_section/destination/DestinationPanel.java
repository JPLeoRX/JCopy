package io.github.jpleorx.jcopy.ui.root.source_and_destination_section.destination;

import io.github.jpleorx.jcopy.core.Cache;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * The destination panel will contain info about destination folder and a select button
 *
 * @author Leo Ertuna
 * @since 23.05.2018 22:46
 */
public class DestinationPanel extends JPanel {
    private JButton selectButton;
    private DestinationInfoPanel infoPanel;

    /**
     * Constructor
     */
    public DestinationPanel() {
        // Initialize
        this.init();
    }

    /**
     * Initialization
     */
    private void init() {
        // Set layout and border across this panel
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Destination folder"));

        // Create select button
        selectButton = new JButton("Select folder");
        selectButton.addActionListener(new SelectButtonActionListener());

        // Create info panel
        infoPanel = new DestinationInfoPanel();

        // Add components to this view
        this.add(selectButton, BorderLayout.NORTH);
        this.add(infoPanel, BorderLayout.CENTER);
    }

    /**
     * Select button listener
     */
    private class SelectButtonActionListener implements ActionListener {
        private final JFileChooser fileChooser = new JFileChooser();

        /**
         * Constructor
         */
        public SelectButtonActionListener() {
            // Make the file chooser work in directories mode
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }

        /**
         * On click
         * @param e action
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // Open the filechooser and Wait till it returns
            int returnValue = fileChooser.showOpenDialog(DestinationPanel.this);

            // If the user selected a file
            if (returnValue  == JFileChooser.APPROVE_OPTION) {
                // Extract the file
                File file = fileChooser.getSelectedFile();

                // Update info panel
                infoPanel.update(file);

                // Store the contents in the cache
                Cache.getInstance().setDestinationFolder(file);
            }

            // If the user canceled the dialog
            else {
                // Do nothing
            }
        }
    }
}