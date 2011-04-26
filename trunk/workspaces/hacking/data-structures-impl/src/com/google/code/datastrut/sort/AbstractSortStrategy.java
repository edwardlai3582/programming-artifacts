package com.google.code.datastrut.sort;

import java.util.Random;

import com.google.code.datastrut.list.ArrayList;

public abstract class AbstractSortStrategy {

    public static enum Algorithm {

        BUBBLE_SORT,
        INSERTION_SORT,
        QUICK_SORT,
        MERGE_SORT;

        public <Type> void sort(Type[] arrayList, Comparator<Type> comparator) {
            switch (this) {
            case BUBBLE_SORT:
                BubbleSortStrategy.getInstance().sort(arrayList, comparator);
                break;

            case INSERTION_SORT:
            case QUICK_SORT:
            case MERGE_SORT:
            default:
                break;
            }
        }
    }

    public abstract <Type> void sort(Type[] arrayList, Comparator<Type> comparator);
    
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
        ArrayList<Integer> randomList2 = ArrayList.makeNewArrayList(arrayIntegers);
        System.out.println("Bubble sorting the array " + randomList2);

        AbstractSortStrategy.Algorithm.BUBBLE_SORT.sort(arrayIntegers, ComparatorFactory.makeIntegerComparator());
        System.out.println("Bubble Sorted array: " + randomList);
    }
}
