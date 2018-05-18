package io.github.jpleorx.jcopy.helpers;

import java.io.File;

/**
 * A helper class that extracts file extension
 *
 * @author Leo Ertuna
 * @since 18.05.2018 23:56
 */
public class FileExtensionHelper {
    private File file;
    private String extension;

    /**
     * Constructor
     * @param file file
     */
    public FileExtensionHelper(File file) {
        this.file = file;
        this.extension = extract();
    }

    /**
     * Internal extraction method
     */
    private String extract() {
        String extension = "";
        String fileName = file.getName();
        int i = fileName.lastIndexOf('.');
        if (i >= 0)
            extension = fileName.substring(i+1);
        return extension;
    }

    /**
     * Getter
     * @return file
     */
    public File getFile() {
        return file;
    }

    /**
     * Getter
     * @return extension
     */
    public String getExtension() {
        return extension;
    }
}