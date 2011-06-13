package com.google.code.progrartifacts.util;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Collection;

import com.google.common.base.Preconditions;

/**
 * Reads a text file passing the output to a listener.
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com)
 * 
 */
public enum TextFileReader {

    INSTANCE;

    /**
     * The file to be read.
     */
    private File file;
    /**
     * The listener to receive the text file lines.
     */
    private TextLineListener listener;
    
    private Collection<String> container;

    /**
     * Adds a file to the reader.
     * @param textFile is the file to be read.
     * @return the current instance.
     */
    public TextFileReader from(File textFile) {
        Preconditions.checkArgument(textFile != null, "The path must be provided.");
        Preconditions.checkArgument(textFile.isFile(), "The given path '%s' is not a file.", textFile);
        Preconditions.checkArgument(textFile.length() > 0, "The given file '%s' is empty.", textFile);

        this.file = textFile;
        return this;
    }

    /**
     * Adds the unique listener to this text file reader that will receive each text file line.
     * 
     * @param listener is the listener.
     * @return the current instance.
     */
    public TextFileReader to(TextLineListener listener) {
        Preconditions.checkArgument(listener != null, "The listener must be provided.");
        this.listener = listener;
        return this;
    }

    /**
     * Adds the unique listener to this text file reader that will receive each text file line.
     * 
     * @param listener is the listener.
     * @return the current instance.
     */
    public TextFileReader to(Collection<String> container) {
        Preconditions.checkArgument(container != null, "The collection must be provided.");
        this.container = container;
        return this;
    }

    /**
     * Reads the entire file.
     * 
     * @throws IOException in case an error occur while reading the current file.
     */
    public void read() throws IOException {
        Preconditions.checkState(this.file != null, "You need to provide a file to read.");
        Preconditions.checkState(this.listener != null || this.container != null, "You need to provide a listener " +
            "or a container to receive the file lines.");

        FileInputStream inFile = new FileInputStream(this.file);
        FileChannel inChannel = inFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate((int) inChannel.size());
        final Charset cs = Charset.forName("ASCII");
        int rd;
        while ((rd = inChannel.read(buf)) != -1) {
            buf.flip();
            CharBuffer chBuf = cs.decode(buf);
            BufferedReader br = new BufferedReader(new CharArrayReader(chBuf.array()));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (this.listener != null) {
                    this.listener.receiveTextLine(line);
                }
                if (this.container != null) {
                    this.container.add(line);
                }
            }
            buf.clear();
        }
        inFile.close();
        this.file = null;
        this.listener = null;
    }
}
