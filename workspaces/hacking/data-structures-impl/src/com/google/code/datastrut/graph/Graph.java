package com.google.code.datastrut.graph;

import com.google.code.datastrut.Iterator;

public interface Graph<Type> {

    /**
     * @return the DFS iterator of the graph as described at http://www.youtube.com/watch?v=or9xlA3YYzo
     */
    Iterator<Type> depthFirstSearchIterator();

    /**
     * @return the BFS iterator of the graph as described at the second half http://www.youtube.com/watch?v=or9xlA3YYzo
     */
    Iterator<Type> breadthFirstSearchIterator();
}
