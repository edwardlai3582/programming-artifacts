package com.google.code.datastrut.sort;

import com.google.code.datastrut.list.List;

public interface Sortable<Type> {

    List<Sortable<Type>> bubbleSort();
    List<Sortable<Type>> insertionSort();
    List<Sortable<Type>> quickSort();
    List<Sortable<Type>> mergeSort();
}
