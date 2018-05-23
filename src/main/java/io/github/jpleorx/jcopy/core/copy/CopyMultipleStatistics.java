package io.github.jpleorx.jcopy.core.copy;

import io.github.jpleorx.jcopy.core.listeners.CopyMultipleStatisticsListener;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

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

    private List<CopyMultipleStatisticsListener> listeners;

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

        // Initialize listeners list
        listeners = new CopyOnWriteArrayList<>();
    }

    /**
     * Call this when a new copy operation is started
     * @param destination destination file
     * @param copyOperation copy operation
     */
    public void started(File destination, CopyOperation copyOperation) {
        started.put(destination, true);
        operations.put(destination, copyOperation);
        callListeners();
    }

    /**
     * Call this when a copy operation has finished
     * @param destination destination file
     * @param copyOperation copy operation
     */
    public void finished(File destination, CopyOperation copyOperation) {
        finished.put(destination, true);
        operations.put(destination, copyOperation);
        callListeners();
    }

    /**
     * Listeners call
     */
    private void callListeners() {
        listeners.parallelStream().forEach(listener -> listener.action(this));
    }

    /**
     * Add new listener
     * @param listener listener
     */
    public void addListener(CopyMultipleStatisticsListener listener) {
        listeners.add(listener);
    }

    /**
     * Getter
     * @return number of listeners
     */
    public int getNumberOfListeners() {
        return listeners.size();
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

    /**
     * Getter
     * @return get list of finished files
     */
    public List<File> getFinishedFiles() {
        return finished.keySet().parallelStream().filter(file -> finished.get(file)).collect(Collectors.toList());
    }
}