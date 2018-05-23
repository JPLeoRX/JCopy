package io.github.jpleorx.jcopy.core;

import io.github.jpleorx.jcopy.core.copy.CopyMultiple;
import io.github.jpleorx.jcopy.core.listeners.CacheListener;

import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class will be a simple cache
 *
 * All needed files and contents from he UI will be saved here and passed to the core
 *
 * It will be a singleton
 *
 * This class should be more-or-less thread safe
 *
 * @author Leo Ertuna
 * @since 23.05.2018 21:23
 */
public class Cache {
    private File sourceFile;
    private File destinationFolder;
    private List<String> destinationFilenames;
    private CopyMultiple copyMultiple;

    private List<CacheListener> listeners;

    /**
     * Private constructor
     */
    private Cache() {
        // Initialize the listeners
        listeners = new CopyOnWriteArrayList<>();
    }

    /**
     * Cache getter
     * @return source file
     */
    public File getSourceFile() {
        return sourceFile;
    }

    /**
     * Cache getter
     * @return destination folder
     */
    public File getDestinationFolder() {
        return destinationFolder;
    }

    /**
     * Cache getter
     * @return destination filenames
     */
    public List<String> getDestinationFilenames() {
        return destinationFilenames;
    }

    /**
     * Cache getter
     * @return copy operation
     */
    public CopyMultiple getCopyMultiple() {
        return copyMultiple;
    }

    /**
     * Cache setter
     * @param sourceFile source file
     */
    public void setSourceFile(File sourceFile) {
        this.sourceFile = sourceFile;
        this.callListeners();
    }

    /**
     * Cache setter
     * @param destinationFolder destination folder
     */
    public void setDestinationFolder(File destinationFolder) {
        this.destinationFolder = destinationFolder;
        this.callListeners();
    }

    /**
     * Cache setter
     * @param destinationFilenames destination filenames
     */
    public void setDestinationFilenames(List<String> destinationFilenames) {
        this.destinationFilenames = destinationFilenames;
        this.callListeners();
    }

    /**
     * Cache setter
     * @param copyMultiple copy operation
     */
    public void setCopyMultiple(CopyMultiple copyMultiple) {
        this.copyMultiple = copyMultiple;
        this.callListeners();
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
    public void addListener(CacheListener listener) {
        listeners.add(listener);
    }

    /**
     * Singleton instance
     */
    private static final Cache INSTANCE = new Cache();

    /**
     * Singleton getter
     * @return instance
     */
    public static Cache getInstance() {
        return INSTANCE;
    }
}