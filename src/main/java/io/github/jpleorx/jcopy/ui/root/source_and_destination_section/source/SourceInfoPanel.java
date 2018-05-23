package io.github.jpleorx.jcopy.ui.root.source_and_destination_section.source;

import io.github.jpleorx.jcopy.helpers.FileHelper;
import io.github.jpleorx.jcopy.ui.Updatable;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * This panel will hold information about source file
 *
 * It must be updatable
 *
 * @author Leo Ertuna
 * @since 23.05.2018 22:46
 */
public class SourceInfoPanel extends JPanel implements Updatable {
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JScrollPane nameScrollPane;

    private JLabel pathLabel;
    private JTextField pathTextField;
    private JScrollPane pathScrollPane;

    private JLabel sizeLabel;
    private JTextField sizeTextField;
    private JScrollPane sizeScrollPane;


    /**
     * Constructor
     */
    public SourceInfoPanel() {
        // Initialize
        this.init();
    }

    /**
     * Initialization
     */
    private void init() {
        // Set layout
        this.setLayout(new GridLayout(6, 1));

        // Name
        nameLabel = new JLabel("File name: ");
        nameTextField = new JTextField();
        nameTextField.setEditable(false);
        nameScrollPane = new JScrollPane(nameTextField, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Path
        pathLabel = new JLabel("Path: ");
        pathTextField = new JTextField();
        pathTextField.setEditable(false);
        pathScrollPane = new JScrollPane(pathTextField, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Size
        sizeLabel = new JLabel("File size: ");
        sizeTextField = new JTextField();
        sizeTextField.setEditable(false);
        sizeScrollPane = new JScrollPane(sizeTextField, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add components to this view
        this.add(nameLabel);
        this.add(nameScrollPane);
        this.add(pathLabel);
        this.add(pathScrollPane);
        this.add(sizeLabel);
        this.add(sizeScrollPane);
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
        nameTextField.setText(fileHelper.getName());

        // Set path
        pathTextField.setText(fileHelper.getPath());

        // Set size
        sizeTextField.setText(fileHelper.getSize());
    }
}