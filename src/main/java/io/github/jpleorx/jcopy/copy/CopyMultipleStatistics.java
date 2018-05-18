package io.github.jpleorx.jcopy.copy;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Stats of the multiple copying procedure
 *
 * @author Leo Ertuna
 * @since 19.05.2018 00:08
 */
public class CopyMultipleStatistics {
    private int totalCount;
    private Map<File, CopyOperation> operations;
    private Map<File, Boolean> started;
    private Map<File, Boolean> finished;

    /**
     * Constructor
     * @param destinationFiles list of destination files
     */
    public CopyMultipleStatistics(List<File> destinationFiles) {
        // Save number of destination files
        this.totalCount = destinationFiles.size();

        // Initialize the maps
        operations = new ConcurrentHashMap<>(destinationFiles.size());
        started = new ConcurrentHashMap<>(destinationFiles.size());
        finished = new ConcurrentHashMap<>(destinationFiles.size());

        // Load the maps
        destinationFiles.parallelStream().forEach(file -> started.put(file, false));
        destinationFiles.parallelStream().forEach(file -> finished.put(file, false));
    }

    /**
     * Call this when a new copy operation is started
     * @param destination destination file
     * @param copyOperation copy operation
     */
    public void started(File destination, CopyOperation copyOperation) {
        started.put(destination, true);
        operations.put(destination, copyOperation);
    }

    /**
     * Call this when a copy operation has finished
     * @param destination destination file
     * @param copyOperation copy operation
     */
    public void finished(File destination, CopyOperation copyOperation) {
        finished.put(destination, true);
        operations.put(destination, copyOperation);
    }

    /**
     * Getter
     * @return get total number of operations that should be performed
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * Getter
     * @return get number of started operations
     */
    public int getStartedCount() {
        return started.keySet().parallelStream().mapToInt(file -> started.get(file) ? 1 : 0).sum();
    }

    /**
     * Getter
     * @return get number of finished operations
     */
    public int getFinishedCount() {
        return finished.keySet().parallelStream().mapToInt(file -> finished.get(file) ? 1 : 0).sum();
    }
}