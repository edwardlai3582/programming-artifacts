package com.google.code.datastrut.list;

import com.google.code.datastrut.Sizable;

public interface List<Type> extends Sizable<Type>, Iterable<Type> {

    void add(Type newElement);

    boolean remove(Type element);
}
