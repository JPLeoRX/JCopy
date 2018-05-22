package io.github.jpleorx.jcopy;

import java.io.File;
import java.util.List;

/**
 * @author Leo Ertuna
 * @since 23.05.2018
 */
public class Cache {
    private File sourceFile;
    private List<File> destinationFiles;

    private Cache() {

    }

    public File getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(File sourceFile) {
        this.sourceFile = sourceFile;
    }

    public List<File> getDestinationFiles() {
        return destinationFiles;
    }

    public void setDestinationFiles(List<File> destinationFiles) {
        this.destinationFiles = destinationFiles;
    }

    private static final Cache INSTANCE = new Cache();

    public static Cache getInstance() {
        return INSTANCE;
    }
}