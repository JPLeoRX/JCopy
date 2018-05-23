package io.github.jpleorx.jcopy.ui.root.source_and_destination_section.source;

import io.github.jpleorx.jcopy.core.Cache;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * The source panel will contain info about souce file and a select button
 *
 * @author Leo Ertuna
 * @since 23.05.2018 22:46
 */
public class SourcePanel extends JPanel {
    private JButton addButton;
    private SourceInfoPanel infoPanel;

    /**
     * Constructor
     */
    public SourcePanel() {
        // Initialize
        this.init();
    }

    /**
     * Initialization
     */
    private void init() {
        // Set layout and border across this panel
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Source file"));

        // Create add button
        addButton = new JButton("Add source file");
        addButton.addActionListener(new AddButtonActionListener());

        // Create info panel
        infoPanel = new SourceInfoPanel();

        // Add components to this view
        this.add(addButton, BorderLayout.NORTH);
        this.add(infoPanel, BorderLayout.CENTER);
    }

    /**
     * Add button listener
     */
    private class AddButtonActionListener implements ActionListener {
        private final JFileChooser fileChooser = new JFileChooser();

        /**
         * On click
         * @param e action
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // Open the filechooser and Wait till it returns
            int returnValue = fileChooser.showOpenDialog(SourcePanel.this);

            // If the user selected a file
            if (returnValue  == JFileChooser.APPROVE_OPTION) {
                // Extract the file
                File file = fileChooser.getSelectedFile();

                // Update info panel
                infoPanel.update(file);

                // Store the contents in the cache
                Cache.getInstance().setSourceFile(file);
            }

            // If the user canceled the dialog
            else {
                // Do nothing
            }
        }
    }
}
