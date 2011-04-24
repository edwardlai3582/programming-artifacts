package com.google.code.datastrut.graph;

import java.util.Random;

import com.google.code.datastrut.Iterator;
import com.google.code.datastrut.list.List;
import com.google.code.datastrut.list.SinglyLinkedList;
import com.google.code.datastrut.stack.Stack;
import com.google.code.datastrut.stack.StackImpl;

public class GraphImpl<Type> implements Graph<Type> {

    /**
     * The set of vertexes of the graph.
     */
    private Vertex<Type>[] vertexes;
    /**
     * The root index of the vertexes.
     */
    private int rootIndex;

    private GraphImpl() {
        
    }

    /**
     * of the vertex values.
     * @param vertexValues is a set of vertex values.
     * @param rootIndex is the root index of the vertex values considered the root.
     * @param adjacencyMatrix is the adjacency matrix.
     * @return A new graph with the vertex values, a chosen root index of the list and an adjacency matrix of the 
     * connections.
     */
    public static GraphImpl<String> makeNewGraph(String[] vertexValues, int rootIndex, boolean[][] adjacencyMatrix) {
        GraphImpl<String> newGraph = new GraphImpl<String>();
        newGraph.rootIndex = rootIndex;
        newGraph.vertexes = new Vertex[vertexValues.length];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            List<String> connections = makeConnections(vertexValues, adjacencyMatrix, i);
            newGraph.vertexes[i] = new Vertex<String>(vertexValues[i], connections);
        }
        return newGraph;
    }

    /**
     * @param vertexValues is the list of vertex values.
     * @param adjacencyMatrix is the adjacency matrix with the connections from the graph.
     * @param vertexIndex is the index of the vertex value to build the connection list.
     * @return a list of vertex values based on the connectivity of the given index on the adjacency matrix.
     */
    public static List<String> makeConnections(String[] vertexValues, boolean[][] adjacencyMatrix, int vertexIndex) {
        List<String> connections = new SinglyLinkedList<String>();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[vertexIndex][i]) {
                connections.add(vertexValues[i]);
            }
        }
        return connections;
    }

    /**
     * @param vertexValues the vertexes.
     * @return a random adjacency matrix of connections for the vertexes without circular connections.
     */
    public static boolean[][] makeRandomAdjacencyMatrix(String[] vertexValues) {
        final int numberOfVertexes = vertexValues.length;
        boolean[][] adjacencyMatrix = new boolean[numberOfVertexes][numberOfVertexes];
        for (int i = 0; i < numberOfVertexes; i++) {
            for (int j = 0; j < numberOfVertexes; j++) {
                if (i == j) continue;
                adjacencyMatrix[i][j] = new Random().nextBoolean();
            }
        }
        return adjacencyMatrix;
    }

    /**
     * Prints an adjacency matrix.
     * @param vertexes the vertexes values.
     * @param adjacencyMatrix the adjacency matrix.
     */
    public static void printAdjacencyMatrix(String[] vertexes, boolean[][] adjacencyMatrix) {
        StringBuilder builder = new StringBuilder();
        builder.append("\t");
        for (int i = 0; i < vertexes.length; i++) {
            builder.append(vertexes[i]);
            builder.append("\t");
        }
        builder.append("\n");
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (j == 0) {
                    builder.append(vertexes[i]);
                    builder.append("\t");
                    builder.append(adjacencyMatrix[i][j]);
                    builder.append("\t");
                } else {
                    builder.append(adjacencyMatrix[i][j]);
                    builder.append("\t");
                }
            }
            builder.append("\n");
        }
        System.out.println(builder.toString());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < vertexes.length; i++) {
            if (i == rootIndex) {
                builder.append("-> ");
            }
            builder.append(vertexes[i].getValue());
            builder.append(": ");
            List<Type> connections = vertexes[i].getConnections();
            Iterator<Type> it = connections.getIterator();
            while(it.hasNext()) {
                Type value = it.getNext();
                builder.append(value);
                if (it.hasNext()) {
                    builder.append(", ");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public Vertex<Type> getRootVertex() {
        return this.vertexes[this.rootIndex];
    }

    /**
     * @param value is a value.
     * @return the vertex instance with the associated value.
     */
    public Vertex<Type> getVertexInstance(Type value) {
        for(Vertex<Type> vertex: vertexes) {
            if (vertex.getValue().equals(value)) {
                return vertex;
            }
        }
        throw new IllegalArgumentException("There is no vertex with the value '" + value + "'");
    }

    /**
     * @param value is a value.
     * @return the vertex index of the associated value.
     */
    public int getVertexIndex(Type value) {
        for(int i = 0; i < vertexes.length; i++) {
            if (vertexes[i].getValue().equals(value)) {
                return i;
            }
        }
        throw new IllegalArgumentException("There is no vertex with the value '" + value + "'");
    }

    @Override
    public Iterator<Type> depthFirstSearchIterator() {
        Iterator<Type> it = new Iterator<Type>() {

            Stack<Vertex<Type>> stack = new StackImpl<Vertex<Type>>(getRootVertex());

            @Override
            public boolean hasNext() {
                if (!stack.isEmpty()) {
                    Vertex<Type> currentVertex = stack.peek();
                    while (!stack.isEmpty() && currentVertex != null) {
                        if (currentVertex.hasBeenVisited()) {
                            Iterator<Type> it = currentVertex.getConnections().getIterator();
                            // put the next connection on the stack.
                            while (it.hasNext()) {
                                Type nextConnectionValue = it.getNext();
                                Vertex<Type> connectionVertex = getVertexInstance(nextConnectionValue);
                                if (!connectionVertex.hasBeenVisited()) {
                                    return true;
                                }
                            }
                            if (!stack.isEmpty()) {
                                currentVertex = stack.pop();
                            }
                        } else return true;
                    }
                    return false;

                } else return false;
            }

            @Override
            public Type getNext() {
                Type value = null;
                if (!stack.isEmpty()) {
                    Vertex<Type> currentVertex = stack.peek();
                    while (!stack.isEmpty() && currentVertex != null) {
                        if (!currentVertex.hasBeenVisited()) {
                            value = currentVertex.getValue();
                            // mark as visited.
                            currentVertex.setAsVisited();
                        } else {
                            Iterator<Type> it = currentVertex.getConnections().getIterator();
                            // put the next connection on the stack.
                            while (it.hasNext()) {
                                Type nextConnectionValue = it.getNext();
                                Vertex<Type> connectionVertex = getVertexInstance(nextConnectionValue);
                                if (!connectionVertex.hasBeenVisited()) {
                                    value = connectionVertex.getValue();
                                    connectionVertex.setAsVisited();
                                    stack.push(connectionVertex);
                                    break;
                                }
                            }
                        }
                        if (value == null && !stack.isEmpty()) {
                            // keep searching for a value...
                            currentVertex = stack.pop();
                        } else {
                            // we can break the search as we have a value.
                            break;
                        }
                    }
                }
                return value;
            }
        };
        return it;
    }

    @Override
    public Iterator<Type> breadthFirstSearchIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(String[] args) {
        String[] graphVertexes = new String[]{"A", "B", "C", "D", "E"};
        boolean[][] adjacencyMatrix = makeRandomAdjacencyMatrix(graphVertexes);
        printAdjacencyMatrix(graphVertexes, adjacencyMatrix);

        System.out.println("Vertex Set with Connections based on the above connections matrix (-> = root)");
        GraphImpl<String> letterGraph = makeNewGraph(graphVertexes, 2, adjacencyMatrix);
        System.out.println(letterGraph);

        System.out.println("The Depth First Search (DFS) elements");
        Iterator<String> dfsIterator =  letterGraph.depthFirstSearchIterator();
        while (dfsIterator.hasNext()) {
            String value = dfsIterator.getNext();
            if (dfsIterator.hasNext()) {
                System.out.print(value + " -> ");
            } else {
                System.out.print(value);
            }
        }
    }
}