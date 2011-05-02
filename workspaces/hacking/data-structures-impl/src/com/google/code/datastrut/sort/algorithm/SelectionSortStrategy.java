package com.google.code.datastrut.sort.algorithm;

import com.google.code.datastrut.sort.AbstractSortStrategy;
import com.google.code.datastrut.sort.Comparator;
import com.google.common.base.Preconditions;

/**
 * Sorts the given arrayList with the given comparator.
 * The description of the algorithm can be seen at http://www.youtube.com/watch?v=o1KPDllRV94 and 
 * http://www.youtube.com/watch?v=P00xJgWzz2c
 *
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public final class SelectionSortStrategy extends AbstractSortStrategy {

    public static final SelectionSortStrategy singleton = new SelectionSortStrategy();

    private SelectionSortStrategy() {

    }

    /**
     * @return The singleton instance of this sort strategy.
     */
    public static SelectionSortStrategy getInstance() {
        return singleton;
    }

    /**
     * Sorts the given arrayList with the given comparator.
     * The description of the algorithm can be seen at http://www.youtube.com/watch?v=o1KPDllRV94
     * The algorithm sorts in O(n^2) average and worse cases
     * @param <Type> any type.
     * @param arrayList the array list of the given type.
     * @param comparator is the comparator that compares elements of the given type.
     */
    public <Type> void sort(Type[] arrayList, Comparator<Type> comparator) {
        Preconditions.checkArgument(arrayList != null, "The array list must be provided to swap.");
        Preconditions.checkArgument(comparator != null, "The comparator must be provided.");

        if (arrayList.length == 0 || arrayList.length == 1) {
            return;
        }
        for (int i = 0; i < arrayList.length; i++) {
            int currentSmallestIndex = i;
            for (int j = i; j < arrayList.length; j++) {
                if (comparator.compare(arrayList[j], arrayList[currentSmallestIndex]) < 0) {
                    currentSmallestIndex = j;
                }
            }
            swap(arrayList, i, currentSmallestIndex);
        }
    }

}
