package com.google.code.datastrut.sort;

public interface Sortable<Type> {

    /**
     * Sorts in O(n^2) using memory to keep track of the currently verifying element..
     * @param comparator
     */
    void selectionSort(Comparator<Type> comparator);
    /**
     * Sorts in O(n^2) in the average and worst case scenario without using memory as it uses swap values.
     * Smaller values are known as turtle as they move slowly to initial area, whereas the larger values are known as
     * the rabbits as they move faster towards the end of the array. Improvements can be made if the list is 
     * already sorted, just verify if any swap was made.
     * @param comparator
     */
    void bubbleSort(Comparator<Type> comparator);
    /**
     * Also known as the shaker sort, it is an improvement to the Bubble sort where the turtle values are moved
     * faster as the rabbits. This is done by reverting the bubble sort from one side to another (like shaking) once
     * it reaches the extremes. It still sorts in O(n^2).
     * @param comparator
     */
    void cocktailSort(Comparator<Type> comparator);
    /**
     * 
     * @param comparator
     */
    void insertionSort(Comparator<Type> comparator);
    /**
     * Sorts a sortable object using the an insertion sort algorithm, but using buckets.
     * @param comparator
     */
    void bucketSort(Comparator<Type> comparator);
    void quickSort(Comparator<Type> comparator);
    void mergeSort(Comparator<Type> comparator);
}
