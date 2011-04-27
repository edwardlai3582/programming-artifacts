package com.google.code.datastrut.sort;

import java.util.Arrays;
import java.util.Random;

import com.google.code.datastrut.list.ArrayList;
import com.google.code.datastrut.sort.algorithm.BubbleSortStrategy;
import com.google.code.datastrut.sort.algorithm.InsertionSortStrategy;
import com.google.code.datastrut.sort.algorithm.SelectionSortStrategy;
import com.google.common.base.Preconditions;

public abstract class AbstractSortStrategy {

    /**
     * The different sorting algorithms to be used.
     * 
     * @author Marcello de Sales (marcello.desales@gmail.com)
     *
     */
    public static enum Algorithm {

        SELECTION_SORT(SelectionSortStrategy.getInstance()),

        BUBBLE_SORT(BubbleSortStrategy.getInstance()),

        INSERTION_SORT(InsertionSortStrategy.getInstance()),

        QUICK_SORT(null),

        MERGE_SORT(null);

        /**
         * The selected strategy.
         */
        private AbstractSortStrategy strategy;

        /**
         * Constructs one of the enumerated sorting algorithms with a given strategy.
         * @param algorithmStrategy is the algorithm strategy implementation.
         */
        private Algorithm(AbstractSortStrategy algorithmStrategy) {
            this.strategy = algorithmStrategy;
        }

        /**
         * Sorts the given array list with the given comparator using associated 
         * implementation.
         * @param <Type>
         * @param arrayList
         * @param comparator
         */
        public <Type> void sort(Type[] arrayList, Comparator<Type> comparator) {
            this.strategy.sort(arrayList, comparator);
        }
    }

    public abstract <Type> void sort(Type[] arrayList, Comparator<Type> comparator);

    /**
     * Swaps the elements of index 1 and index 2 two elements from the given array list.
     * @param <Type>
     * @param arrayList is the array list.
     * @param index1 is the index 1
     * @param index2 is the index 2
     */
    public static <Type> void swap(Type[] arrayList, int index1, int index2) {
        Preconditions.checkArgument(arrayList != null, "The array list must be provided to swap.");
        Preconditions.checkElementIndex(index1, arrayList.length, "The index 1 is out of the array bounds.");
        Preconditions.checkElementIndex(index2, arrayList.length, "The index 2 is out of the array bounds.");

        Type aux = arrayList[index2];
        arrayList[index2] = arrayList[index1];
        arrayList[index1] = aux;
    }

    public static void main(String[] args) {
        int numbers = 10;
        System.out.println(" -> Sorting... ");
        // verify the sorting
        Random rand = new Random();
        Integer[] arrayIntegers = new Integer[numbers];
        for (int i = 0; i < numbers; i++) {
            arrayIntegers[i] = rand.nextInt(100);
        }
        ArrayList<Integer> randomList = ArrayList.makeNewArrayList(arrayIntegers);
        System.out.println("Bubble sorting the array " + randomList);
        randomList.bubbleSort(ComparatorFactory.makeIntegerComparator());
        System.out.println("Bubble Sorted array: " + randomList);

        for (int i = 0; i < numbers; i++) {
            arrayIntegers[i] = rand.nextInt(100);
        }
        System.out.println("------------------------------");
        System.out.println("Selection sorting the array " + Arrays.toString(arrayIntegers));

        AbstractSortStrategy.Algorithm.SELECTION_SORT.sort(arrayIntegers, ComparatorFactory.makeIntegerComparator());
        System.out.println("Selection Sorted array: " + Arrays.toString(arrayIntegers));

        for (int i = 0; i < numbers; i++) {
            arrayIntegers[i] = rand.nextInt(100);
        }
        System.out.println("------------------------------");
        System.out.println("Bubble sorting the array " + Arrays.toString(arrayIntegers));

        AbstractSortStrategy.Algorithm.BUBBLE_SORT.sort(arrayIntegers, ComparatorFactory.makeIntegerComparator());
        System.out.println("Bubble Sorted array: " + Arrays.toString(arrayIntegers));

        for (int i = 0; i < numbers; i++) {
            arrayIntegers[i] = rand.nextInt(100);
        }
        System.out.println("------------------------------");
        System.out.println("Insertion sorting the array " + Arrays.toString(arrayIntegers));

        AbstractSortStrategy.Algorithm.INSERTION_SORT.sort(arrayIntegers, ComparatorFactory.makeIntegerComparator());
        System.out.println("Insertion Sorted array: " + Arrays.toString(arrayIntegers));
    }
}
