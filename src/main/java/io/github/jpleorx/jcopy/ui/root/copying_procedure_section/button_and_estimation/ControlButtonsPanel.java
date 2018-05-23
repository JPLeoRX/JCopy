package io.github.jpleorx.jcopy.ui.root.copying_procedure_section.button_and_estimation;

import io.github.jpleorx.jcopy.core.Cache;
import io.github.jpleorx.jcopy.core.copy.CopyMultiple;
import io.github.jpleorx.jcopy.core.listeners.CacheListener;
import io.github.jpleorx.jcopy.helpers.DestinationFilesHelper;
import io.github.jpleorx.jcopy.helpers.FileHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This panel contains control buttons for copying procedure
 *
 * The listeners here are a bit complicated, but they do the job well
 *
 * Copying will be started in a new SwingWorker to allow better flow of data between GUI and core
 *
 * @author Leo Ertuna
 * @since 23.05.2018 23:25
 */
public class ControlButtonsPanel extends JPanel {
    private JButton startButton;
    private JButton stopButton;
    private CopySwingWorker copySwingWorker;

    /**
     * Constructor
     */
    public ControlButtonsPanel() {
        // Initialize
        this.init();
    }

    /**
     * Initialization
     */
    private void init() {
        // Set layout
        this.setLayout(new GridLayout(1, 2));

        // Start
        startButton = new JButton("Start");
        startButton.setEnabled(false);
        startButton.addActionListener(new StartButtonListener());

        // Stop
        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new StopButtonListener());

        // Add components to this view
        this.add(startButton);
        this.add(stopButton);

        // Set cache listener
        Cache.getInstance().addListener(new StartButtonCacheListener());
    }

    /**
     * Start button cache listener - this will fire as soon as we have all needed data in the cache
     */
    private class StartButtonCacheListener implements CacheListener {
        @Override
        public void action(Cache cache) {
            // If there is enough data to start copying
            if (cache.getSourceFile() != null && cache.getDestinationFolder() != null && cache.getDestinationFilenames() != null) {
                // Enable the button
                startButton.setEnabled(true);
            }

            // If not
            else {
                // Keep it disabled
                startButton.setEnabled(false);
            }
        }
    }

    /**
     * Start button action listener - this fires when the button is clicked
     */
    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // If the button is enabled
            if (startButton.isEnabled()) {
                // Enable the stop button
                stopButton.setEnabled(true);

                // Create new swing worker
                copySwingWorker = new CopySwingWorker();

                // Run it
                copySwingWorker.execute();
            }
        }
    }

    /**
     * Stop button action listener - this fires when the button is clicked
     */
    private class StopButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // If the button is enabled
            if (stopButton.isEnabled()) {
                // Cancel the copy worker
                copySwingWorker.cancel(true);

                // Stop the copying procedure
                Cache.getInstance().getCopyMultiple().stop();

                // Disable the stop button
                stopButton.setEnabled(false);
            }
        }
    }

    /**
     * Swing worker that starts up the copying procedure
     */
    private class CopySwingWorker extends SwingWorker<Void, Void> {
        @Override
        protected Void doInBackground() throws Exception {
            // Extract source file extension
            String sourceFileExtension = new FileHelper(Cache.getInstance().getSourceFile()).getExtension();

            // Build a list of destination files
            DestinationFilesHelper destinationFilesHelper = new DestinationFilesHelper(Cache.getInstance().getDestinationFolder(), Cache.getInstance().getDestinationFilenames(), sourceFileExtension);
            destinationFilesHelper.build();

            // Create new copy procedure
            CopyMultiple copyMultiple = new CopyMultiple(Cache.getInstance().getSourceFile(), destinationFilesHelper.getDestinationFiles());

            // Store it in cache
            Cache.getInstance().setCopyMultiple(copyMultiple);

            // Start copying
            copyMultiple.copy();

            return null;
        }

        @Override
        protected void done() {
            // After copying is done - disable the stop button
            stopButton.setEnabled(false);
        }
    }
}