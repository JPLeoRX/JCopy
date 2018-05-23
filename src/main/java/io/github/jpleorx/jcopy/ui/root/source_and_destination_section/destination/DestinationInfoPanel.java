package io.github.jpleorx.jcopy.ui.root.source_and_destination_section.destination;

import io.github.jpleorx.jcopy.helpers.FileHelper;
import io.github.jpleorx.jcopy.ui.Updatable;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * This panel will hold information about destination folder
 *
 * It must be updatable
 *
 * @author Leo Ertuna
 */
public class DestinationInfoPanel extends JPanel implements Updatable {
    private JLabel folderNameLabel;
    private JTextField folderNameTextField;
    private JScrollPane folderNameScrollPane;

    private JLabel folderPathLabel;
    private JTextField folderPathTextField;
    private JScrollPane folderPathScrollPane;

    /**
     * Constructor
     */
    public DestinationInfoPanel() {
        // Initialize
        this.init();
    }

    /**
     * Initialization
     */
    private void init() {
        // Set layout
        this.setLayout(new GridLayout(6, 1));

        // Folder name
        folderNameLabel = new JLabel("Folder name: ");
        folderNameTextField = new JTextField();
        folderNameTextField.setEditable(false);
        folderNameScrollPane = new JScrollPane(folderNameTextField, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Folder path
        folderPathLabel = new JLabel("Folder path: ");
        folderPathTextField = new JTextField();
        folderPathTextField.setEditable(false);
        folderPathScrollPane = new JScrollPane(folderPathTextField, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add components to this view
        this.add(folderNameLabel);
        this.add(folderNameScrollPane);
        this.add(folderPathLabel);
        this.add(folderPathScrollPane);
        this.add(new JLabel());
        this.add(new JLabel());
    }

    /**
     * Update
     * @param file new file
     */
    @Override
    public void update(File file) {
        // Create a file helper to parse all the data
        FileHelper fileHelper = new FileHelper(file);

        // Set name
        folderNameTextField.setText(fileHelper.getName());

        // Set path
        folderPathTextField.setText(fileHelper.getPath());
    }
}