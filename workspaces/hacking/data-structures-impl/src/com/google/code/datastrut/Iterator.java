package com.google.code.datastrut;

public interface Iterator<Type> {

    boolean hasNext();

    Type getNext();
}
