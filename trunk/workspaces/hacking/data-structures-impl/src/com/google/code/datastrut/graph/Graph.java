package com.google.code.datastrut.graph;

import com.google.code.datastrut.Iterator;

public interface Graph<Type> {

    Iterator<Type> depthFirstSearchIterator();

    Iterator<Type> breadthFirstSearchIterator();
}
