package com.google.code.datastrut.list;

import com.google.code.datastrut.Iterable;
import com.google.code.datastrut.Sizable;

public interface Queue<Type> extends Sizable<Type>, Iterable<Type> {

    void enqueue(Type newElement);

    Type dequeue();

    Type front();

    Type back();
}
