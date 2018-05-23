package io.github.jpleorx.jcopy.helpers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This is a small helper that will build a list of destination files
 *
 * @author Leo Ertuna
 * @since 19.05.2018 00:02
 */
public class DestinationFilesHelper {
    private File destinationFolder;
    private List<String> filenames;
    private String sourceFileExtension;
    private List<File> destinationFiles;

    /**
     * Constructor
     * @param destinationFolder the destination folder
     * @param filenames list of filenames
     * @param sourceFileExtension extension of the source file
     */
    public DestinationFilesHelper(File destinationFolder, List<String> filenames, String sourceFileExtension) {
        this.destinationFolder = destinationFolder;
        this.filenames = filenames;
        this.sourceFileExtension = sourceFileExtension;
        this.destinationFiles = new CopyOnWriteArrayList<>(new ArrayList<>(filenames.size()));
    }

    /**
     * Internal building procedure
     */
    private void buildInternal() {
        // For each filename in a parallel stream
        filenames.parallelStream().forEach(name -> {
            // Get the folder path
            String folderPath = destinationFolder.getAbsolutePath();

            // Add file name and extension to the folder path - we will get filepath
            String filePath = folderPath + "/" + trimFilename(name) + "." + sourceFileExtension;

            // Create new file and add it to the list
            destinationFiles.add(new File(filePath));
        });
    }

    /**
     * External control method for building procedure
     */
    public void build() {
        this.buildInternal();
    }

    /**
     * Internal trimer of the filename, it removes all illegal characters from the name
     * @param s filename
     * @return trimed filename
     */
    private static String trimFilename(String s) {
        return s.trim().
                replace("/", "").replace("\\", "").replace("\"", "").
                replace(":", "").replace("*", "").replace("?", "").
                replace("<", "").replace(">", "").replace("|", "");
    }

    /**
     * Getter
     * @return destination folder
     */
    public File getDestinationFolder() {
        return destinationFolder;
    }

    /**
     * Getter
     * @return list of filenames
     */
    public List<String> getFilenames() {
        return filenames;
    }

    /**
     * Getter
     * @return extension of the source file
     */
    public String getSourceFileExtension() {
        return sourceFileExtension;
    }

    /**
     * Getter
     * @return list of destination files
     */
    public List<File> getDestinationFiles() {
        return destinationFiles;
    }
}