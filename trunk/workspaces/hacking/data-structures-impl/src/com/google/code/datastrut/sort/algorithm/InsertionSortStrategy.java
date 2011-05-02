package com.google.code.datastrut.sort.algorithm;

import com.google.code.datastrut.sort.AbstractSortStrategy;
import com.google.code.datastrut.sort.Comparator;
import com.google.common.base.Preconditions;

/**
 * Sorts the given arrayList with the given comparator.
 * The description of the algorithm can be seen at http://www.youtube.com/watch?v=3orUYqcaEEQ and
 * http://www.youtube.com/watch?v=c4BRHC7kTaQ
 *
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public final class InsertionSortStrategy extends AbstractSortStrategy {

    public static final InsertionSortStrategy singleton = new InsertionSortStrategy();

    private InsertionSortStrategy() {

    }

    /**
     * @return The singleton instance of this sort strategy.
     */
    public static InsertionSortStrategy getInstance() {
        return singleton;
    }

    /**
     * Sorts the given arrayList with the given comparator.
     * The description of the algorithm can be seen at http://www.youtube.com/watch?v=3orUYqcaEEQ
     * and http://www.youtube.com/watch?v=c4BRHC7kTaQ
     * @param <Type> any type.
     * @param arrayList the array list of the given type.
     * @param comparator is the comparator that compares elements of the given type.
     */
    public <Type> void sort(Type[] arrayList, Comparator<Type> comparator) {
        Preconditions.checkArgument(arrayList != null, "The array must be provided.");
        Preconditions.checkArgument(comparator != null, "The comparator must be provided.");

        if (arrayList.length == 0 || arrayList.length == 1) {
            return;
        }

        for (int i = 1; i < arrayList.length; i++) {
            int j = i;
            while (j > 0 && arrayList[j] != null && arrayList[j - 1] != null && 
                    comparator.compare(arrayList[j-1], arrayList[j]) > 0) {

                swap(arrayList, j, j-1);
                j--;
            }
        }
    }

}
