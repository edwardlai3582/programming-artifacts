package com.google.code.datastrut;

public interface ReverseIterator<Type> {

    boolean hasPrevious();

    Type previous();
}
