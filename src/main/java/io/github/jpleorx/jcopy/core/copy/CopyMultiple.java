package io.github.jpleorx.jcopy.core.copy;

import java.io.File;
import java.util.List;

/**
 * Copy operation distributed to multiple destination files
 *
 * @author Leo Ertuna
 * @since 19.05.2018 00:10
 */
public class CopyMultiple {
    private File source;
    private List<File> destinations;
    private CopyMultipleStatistics statistics;
    private boolean stop;

    /**
     * Constructor
     * @param source source file
     * @param destinations list of destination files
     */
    public CopyMultiple(File source, List<File> destinations) {
        this.source = source;
        this.destinations = destinations;
        this.statistics = new CopyMultipleStatistics(destinations);
        this.stop = false;
    }

    /**
     * External control method to copying procedure
     */
    public void copy() {
        // Run for each destination file through parallel stream
        destinations.parallelStream().forEach(destination -> {
            // If it wasn't stopped
            if (!stop) {
                // Create new copy operation
                CopyOperation copyOperation = new CopyOperation(source, destination);

                // Mark as started
                statistics.started(destination, copyOperation);

                // Run it
                copyOperation.copy();

                // Mark as finished
                statistics.finished(destination, copyOperation);
            }
        });
    }

    /**
     * External control method to stop copying
     */
    public void stop() {
        stop = true;
    }

    /**
     * Getter
     * @return source file
     */
    public File getSource() {
        return source;
    }

    /**
     * Getter
     * @return list of destination files
     */
    public List<File> getDestinations() {
        return destinations;
    }

    /**
     * Getter
     * @return statistics
     */
    public CopyMultipleStatistics getStatistics() {
        return statistics;
    }
}