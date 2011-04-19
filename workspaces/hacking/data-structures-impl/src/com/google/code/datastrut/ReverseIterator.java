package com.google.code.datastrut;

public interface ReverseIterator<Type> extends Iterator<Type> {

    boolean hasPrevious();

    Type getPrevious();
}
