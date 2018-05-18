package io.github.jpleorx.jcopy.helpers;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple text file parser
 *
 * It will read a text file line by line and save them in a list
 *
 * It will also save this text file in a string
 *
 * @author Leo Ertuna
 * @since 19.05.2018 00:01
 */
public class TextFileParser {
    private File file;
    private List<String> lines;
    private String text;

    /**
     * Constructor
     * @param file file to parse
     */
    public TextFileParser(File file) {
        this.file = file;
        this.lines = new LinkedList<>();
        this.text = "";
    }

    /**
     * Internal reading procedure
     * @throws IOException
     */
    private void readInternal() throws IOException {
        // Create input streams
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        // Read the lines
        String line = bufferedReader.readLine();;
        while (line != null) {
            lines.add(line);
            line = bufferedReader.readLine();
        }

        // Join lines in one text
        for (String tempLine : lines)
            text += tempLine + "\n";
    }

    /**
     * External control for reading
     */
    public void read() {
        try {
            this.readInternal();
        } catch (IOException e) {
            throw new TextFileParserException(e);
        }
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
     * @return list of lines
     */
    public List<String> getLines() {
        return lines;
    }

    /**
     * Getter
     * @return whole text
     */
    public String getText() {
        return text;
    }
}