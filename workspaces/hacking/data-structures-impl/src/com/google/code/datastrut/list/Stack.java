package com.google.code.datastrut.list;

import com.google.code.datastrut.Iterable;
import com.google.code.datastrut.Sizable;

public interface Stack<Type> extends Sizable<Type>, Iterable<Type> {

    Type pop();

    void push(Type newElement);
}
