package com.google.code.datastrut.sort.algorithm;

import com.google.code.datastrut.sort.AbstractSortStrategy;
import com.google.code.datastrut.sort.Comparator;
import com.google.common.base.Preconditions;

/**
 * Sorts the given arrayList with the given comparator.
 * The description of the algorithm can be seen at http://www.youtube.com/watch?v=JiJXnEpDk5o
 *
 * The Cocktail Sort, or Shaker Algorithm, is an improvement to the Bubble sort in which the turtle values
 * (the small numbers) are sorted at the same rate as the rabbits.
 * 
 * More information at http://en.wikipedia.org/wiki/Cocktail_sort
 *
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public final class CocktailSortStrategy extends AbstractSortStrategy {

    public static final CocktailSortStrategy singleton = new CocktailSortStrategy();

    private CocktailSortStrategy() {

    }

    /**
     * @return The singleton instance of this sort strategy.
     */
    public static CocktailSortStrategy getInstance() {
        return singleton;
    }

    /**
     * Sorts the given arrayList with the given comparator.
     * The description of the algorithm can be seen at http://www.youtube.com/watch?JiJXnEpDk5o
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
        boolean swapped = false;
        do {
            swapped = false;
            for (int i = 0; i < arrayList.length-1; i++) {
                if (arrayList[i] == null || arrayList[i + 1] == null) {
                    break;
                }
                if (comparator.compare(arrayList[i], arrayList[i + 1]) > 0) {
                    swap(arrayList, i, i + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
            swapped = false;
            for (int i = arrayList.length-1; i > 0; i--) {
                if (arrayList[i] == null || arrayList[i - 1] == null) {
                    break;
                }
                if (comparator.compare(arrayList[i - 1], arrayList[i]) > 0) {
                    swap(arrayList, i - 1, i);
                    swapped = true;
                }
            }

        } while (swapped);
    }

}
