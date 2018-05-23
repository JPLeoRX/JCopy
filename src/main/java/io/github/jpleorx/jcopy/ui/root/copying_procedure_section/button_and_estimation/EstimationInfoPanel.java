package io.github.jpleorx.jcopy.ui.root.copying_procedure_section.button_and_estimation;

import io.github.jpleorx.jcopy.core.Cache;
import io.github.jpleorx.jcopy.core.listeners.CacheListener;
import io.github.jpleorx.jcopy.helpers.FileHelper;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * This panel contains estimation of resulting copies
 *
 * @author Leo Ertuna
 * @since 23.05.2018 23:25
 */
public class EstimationInfoPanel extends JPanel {
    private JLabel countLabel;
    private JTextField countTextField;
    private JScrollPane countScrollPane;

    private JLabel sizeLabel;
    private JTextField sizeTextField;
    private JScrollPane sizeScrollPane;

    /**
     * Constructor
     */
    public EstimationInfoPanel() {
        // Initialize
        this.init();
    }

    /**
     * Initialization
     */
    private void init() {
        // Set layout
        this.setLayout(new GridLayout(4, 1));

        // Count
        countLabel = new JLabel("Estimated number of files: ");
        countTextField = new JTextField();
        countTextField.setEditable(false);
        countScrollPane = new JScrollPane(countTextField);

        // Size
        sizeLabel = new JLabel("Estimated size of files: ");
        sizeTextField = new JTextField();
        sizeTextField.setEditable(false);
        sizeScrollPane = new JScrollPane(sizeTextField);

        // Add components to this view
        this.add(countLabel);
        this.add(countScrollPane);
        this.add(sizeLabel);
        this.add(sizeScrollPane);

        // Set cache listener
        Cache.getInstance().addListener(new EstimationCacheListener());
    }

    /**
     * Update
     * @param count count
     * @param size size
     */
    private void update(String count, String size) {
        countTextField.setText(count);
        sizeTextField.setText(size);
    }

    /**
     * Cache listener - it will update this panel as soon as there are some changes in the cache
     */
    private class EstimationCacheListener implements CacheListener {
        @Override
        public void action(Cache cache) {
            // If cache contains the data we need for estimation
            if (cache.getSourceFile() != null && cache.getDestinationFilenames() != null) {
                // Get source file from cache and create a file helper
                File sourceFile = cache.getSourceFile();
                FileHelper fileHelper = new FileHelper(sourceFile);

                // Extract source file size and unit
                double sourceFileSize = fileHelper.getSizeNumeric();
                String sourceFileUnit = fileHelper.getSizeUnit();

                // Compute destination folder count and size
                int destinationFolderFileCount = cache.getDestinationFilenames().size();
                double destinationFolderSize = sourceFileSize * destinationFolderFileCount;

                // Update the panel
                update(String.valueOf(destinationFolderFileCount), destinationFolderSize + " " + sourceFileUnit);
            }

            // If there's null data
            else {
                // Reset the panel
                update("", "");
            }
        }
    }
}