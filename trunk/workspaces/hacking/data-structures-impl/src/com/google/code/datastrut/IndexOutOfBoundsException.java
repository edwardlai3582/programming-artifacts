package com.google.code.datastrut;

/**
 * When an attempt to access an index at a list does not exist.
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public class IndexOutOfBoundsException extends RuntimeException {

    private int index;
    
    public IndexOutOfBoundsException(int index, String message) {
        super(message);
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
