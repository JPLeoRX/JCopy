package io.github.jpleorx.jcopy.ui.root.copying_procedure_section;

import io.github.jpleorx.jcopy.core.Cache;
import io.github.jpleorx.jcopy.core.copy.CopyMultipleStatistics;
import io.github.jpleorx.jcopy.core.listeners.CacheListener;
import io.github.jpleorx.jcopy.core.listeners.CopyMultipleStatisticsListener;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class ProgressPanel extends JPanel {
    private List<File> printedFiles;
    private JTextArea finishedFilesTextArea;
    private JScrollPane finishedFilesScrollPane;

    private JPanel progressStatusPanel;
    private JLabel progressFileCountLabel;
    private JLabel progressPercentageLabel;

    /**
     * Constructor
     */
    public ProgressPanel() {
        // Initialize
        this.init();
    }

    /**
     * Initialization
     */
    private void init() {
        // Set layout
        this.setLayout(new BorderLayout());

        // Finished files
        finishedFilesTextArea = new JTextArea();
        finishedFilesTextArea.setEditable(false);
        finishedFilesScrollPane = new JScrollPane(finishedFilesTextArea);

        progressFileCountLabel = new JLabel("Copied: 0 of 0 files");
        progressPercentageLabel = new JLabel("Completed: 0%");

        progressStatusPanel = new JPanel(new GridLayout(2, 1));
        progressStatusPanel.add(progressFileCountLabel);
        progressStatusPanel.add(progressPercentageLabel);

        // Add components to this view
        this.add(finishedFilesScrollPane, BorderLayout.CENTER);
        this.add(progressStatusPanel, BorderLayout.SOUTH);

        // Initialize list of printed files
        printedFiles = new CopyOnWriteArrayList<>();

        // Set cache listener
        Cache.getInstance().addListener(new ProgressCacheListener());
    }

    /**
     * Cache listener - it listens for started copying procedure
     */
    private class ProgressCacheListener implements CacheListener {
        @Override
        public void action(Cache cache) {
            // If copy was started, but there's still no listener attached to it
            if (cache.getCopyMultiple() != null && cache.getCopyMultiple().getStatistics().getNumberOfListeners() == 0) {
                // Add new statistics listener
                cache.getCopyMultiple().getStatistics().addListener(new ProgressCopyMultipleStatisticsListener());
            }
        }
    }

    /**
     * Statistics listener - it listens for new finished files and prints them to the user
     */
    private class ProgressCopyMultipleStatisticsListener implements CopyMultipleStatisticsListener {
        @Override
        synchronized public void action(CopyMultipleStatistics copyMultipleStatistics) {
            new ProgressSwingWorker(copyMultipleStatistics).execute();
        }
    }

    private class Transfer {
        String newLines;
        String count;
        String percentage;

        public Transfer(String newLines, String count, String percentage) {
            this.newLines = newLines;
            this.count = count;
            this.percentage = percentage;
        }
    }

    private class ProgressSwingWorker extends SwingWorker<Transfer, Void> {
        private CopyMultipleStatistics copyMultipleStatistics;

        public ProgressSwingWorker(CopyMultipleStatistics copyMultipleStatistics) {
            this.copyMultipleStatistics = copyMultipleStatistics;
        }

        @Override
        protected Transfer doInBackground() throws Exception {
            // Get a list of finished files from the statistics
            List<File> finishedFiles = copyMultipleStatistics.getFinishedFiles();

            // If there are any new files that haven't been printed yet
            if (finishedFiles.size() > printedFiles.size()) {
                // Find only these new files
                List<File> newFiles = finishedFiles.parallelStream().filter(file -> !printedFiles.contains(file)).collect(Collectors.toList());

                // Extract their names
                List<String> newFilesNames = newFiles.parallelStream().map(file -> file.getName()).collect(Collectors.toList());

                // Join the string
                String joined = "";
                for (String filename : newFilesNames)
                    joined += "Copied: " + filename + "\n";

                System.out.println(joined);

                // Save those newly printed files
                printedFiles.addAll(newFiles);

                return new Transfer(
                        joined,
                        "Copied: " + copyMultipleStatistics.getFinishedCount() + " of " + copyMultipleStatistics.getTotalCount() + " files",
                        "Completed " + (int) Math.round((1 * copyMultipleStatistics.getFinishedCount()) / (1.0 * copyMultipleStatistics.getTotalCount()) * 100) + "%"
                );
            }

            return new Transfer("", progressFileCountLabel.getText(), progressPercentageLabel.getText());
        }

        @Override
        protected void done() {
            try {
                // Retrieve the return value of doInBackground.
                Transfer transfer = get();

                // Update the text area
                finishedFilesTextArea.append(transfer.newLines);

                // Update progress status
                progressFileCountLabel.setText(transfer.count);
                progressPercentageLabel.setText(transfer.percentage);

            }

            catch (InterruptedException e) {
                e.printStackTrace();
                // This is thrown if the thread's interrupted.
            }

            catch (ExecutionException e) {
                e.printStackTrace();
                // This is thrown if we throw an exception
                // from doInBackground.
            }
        }
    }
}
