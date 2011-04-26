package com.google.code.datastrut.sort;

public interface Sortable<Type> {

    void bubbleSort(Comparator<Type> comparator);
    void insertionSort(Comparator<Type> comparator);
    void quickSort(Comparator<Type> comparator);
    void mergeSort(Comparator<Type> comparator);
}
