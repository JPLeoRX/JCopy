package io.github.jpleorx.jcopy.helpers;

import java.io.File;

/**
 * A helper class that extracts file extension
 *
 * @author Leo Ertuna
 * @since 18.05.2018 23:56
 */
public class FileHelper {
    private File file;
    private String name;
    private String path;
    private String extension;
    private String size;

    /**
     * Constructor
     * @param file file
     */
    public FileHelper(File file) {
        this.file = file;
        this.name = extractName(file);
        this.path = extractPath(file);
        this.extension = extractExtension(file);
        this.size = extractSize(file);
    }

    /**
     * Internal extraction method, name
     */
    private static String extractName(File file) {
        return file.getName();
    }

    /**
     * Internal extraction method, path
     */
    private static String extractPath(File file) {
        return file.getAbsolutePath();
    }

    /**
     * Internal extraction method, extension
     */
    private static String extractExtension(File file) {
        String extension = "";

        if (!file.isDirectory()) {
            String fileName = file.getName();
            int i = fileName.lastIndexOf('.');
            if (i >= 0)
                extension = fileName.substring(i+1);
        }

        return extension;
    }

    /**
     * Internal extraction method, size
     */
    private static String extractSize(File file) {
        double bytes = file.length();
        double kilobytes = (bytes / 1024);
        double megabytes = (kilobytes / 1024);
        double gigabytes = (megabytes / 1024);

        if (gigabytes > 1)
            return gigabytes + " GB";
        else if (megabytes > 1)
            return megabytes + " MB";
        else if (kilobytes > 1)
            return kilobytes + " KB";
        else
            return bytes + " bytes";
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * Getter
     * @return extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Getter
     * @return size
     */
    public String getSize() {
        return size;
    }

    /**
     * Getter
     * @return size as a number
     */
    public double getSizeNumeric() {
        return Double.parseDouble(getSize().split(" ")[0]);
    }

    /**
     * Getter
     * @return size unit
     */
    public String getSizeUnit() {
        return getSize().split(" ")[1];
    }
}