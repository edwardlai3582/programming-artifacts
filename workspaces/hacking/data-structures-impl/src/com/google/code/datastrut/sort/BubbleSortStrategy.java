package com.google.code.datastrut.sort;

/**
 * Sorts the given arrayList with the given comparator.
 * The description of the algorithm can be seen at http://www.youtube.com/watch?v=P00xJgWzz2c
 *
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public final class BubbleSortStrategy extends AbstractSortStrategy {

    public static final BubbleSortStrategy singleton = new BubbleSortStrategy();

    private BubbleSortStrategy() {

    }

    /**
     * @return The singleton instance of this sort strategy.
     */
    public static BubbleSortStrategy getInstance() {
        return singleton;
    }

    /**
     * Sorts the given arrayList with the given comparator.
     * The description of the algorithm can be seen at http://www.youtube.com/watch?v=P00xJgWzz2c
     * @param <Type> any type.
     * @param arrayList the array list of the given type.
     * @param comparator is the comparator that compares elements of the given type.
     */
    public <Type> void sort(Type[] arrayList, Comparator<Type> comparator) {
        for (int i = 0; i < arrayList.length; i++) {
            for (int j = 0; j < arrayList.length-1; j++) {
                if (arrayList[j] == null || arrayList[j + 1] == null) {
                    break;
                }
                if (comparator.compare(arrayList[j], arrayList[j + 1]) > 0) {
                    Type aux = arrayList[j + 1];
                    arrayList[j + 1] = arrayList[j];
                    arrayList[j] = aux;
                }
            }
        }
    }

}
