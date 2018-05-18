package io.github.jpleorx.jcopy.copy;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Simple copy operation
 *
 * We copy contents of source file to the destination file
 *
 * @author Leo Ertuna
 * @since 18.05.2018 23:49
 */
public class CopyOperation {
    private File source;
    private File destination;
    private boolean started;
    private boolean finished;

    /**
     * Constructor
     * @param source source file
     * @param destination destination file
     */
    public CopyOperation(File source, File destination) {
        this.source = source;
        this.destination = destination;
        this.started = false;
        this.finished = false;
    }

    /**
     * Internal copying method
     * @throws IOException
     */
    private void copyInternal() throws IOException {
        // Mark this operation as started
        this.started = true;

        // Create channels
        FileChannel sourceChannel = new FileInputStream(source).getChannel();
        FileChannel destChannel = new FileOutputStream(destination).getChannel();

        // Copy the data
        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

        // Close channels
        sourceChannel.close();
        destChannel.close();

        // Mark this operation as finished
        this.finished = true;
    }

    /**
     * External control method to copying procedure
     * @return true if the copy was successful
     */
    public boolean copy() {
        try {
            // Call internal method
            this.copyInternal();

            // Return finished flag
            return finished;
        }

        catch (IOException e) {
            throw new CopyOperationException(e);
        }
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
     * @return destination file
     */
    public File getDestination() {
        return destination;
    }

    /**
     * Getter
     * @return is this operation started?
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Getter
     * @return is this operation finished?
     */
    public boolean isFinished() {
        return finished;
    }
}