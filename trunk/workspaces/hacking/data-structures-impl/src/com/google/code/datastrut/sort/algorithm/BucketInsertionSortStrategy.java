package com.google.code.datastrut.sort.algorithm;

import com.google.code.datastrut.list.ArrayList;
import com.google.code.datastrut.sort.AbstractSortStrategy;
import com.google.code.datastrut.sort.Comparator;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

/**
 * Sorts the given arrayList with the given comparator.
 * The description of the algorithm can be seen at http://www.youtube.com/watch?v=Iiuqrns0Gwk
 *
 * http://en.wikipedia.org/wiki/Bucket_sort
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public final class BucketInsertionSortStrategy extends AbstractSortStrategy {

    public static final BucketInsertionSortStrategy singleton = new BucketInsertionSortStrategy();

    private static final int BUCKET_INDEX_START_STRING = Character.getNumericValue('a') - 1;
    private static final int BUCKET_INDEX_END_STRING = Character.getNumericValue('z') - 1;

    private static final int NUMBER_OF_BUCKETS_STRING = BUCKET_INDEX_END_STRING - BUCKET_INDEX_START_STRING;

    //TODO: STILL NEEDS THE IMPLEMENTATION OF BUCKETS FOR NUMBERS
    private static final int NUMBER_OF_BUCKETS_NUMBER = 3;

    private BucketInsertionSortStrategy() {

    }

    /**
     * @return The singleton instance of this sort strategy.
     */
    public static BucketInsertionSortStrategy getInstance() {
        return singleton;
    }

    /**
     * Sorts the given arrayList with the given comparator.
     * The description of the algorithm can be seen at http://www.youtube.com/watch?v=Iiuqrns0Gwk
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
        // setup the buckets list
        ArrayList<ArrayList<Type>> buckets = null;
        if(arrayList instanceof String[]) {
            buckets = new ArrayList<ArrayList<Type>>(NUMBER_OF_BUCKETS_STRING);
            for (int i = 0; i < NUMBER_OF_BUCKETS_STRING; i++) {
                // initialize the array list of the given bucket with max possible number
                buckets.add(new ArrayList<Type>(arrayList.length));
            }
        }

        // Scatter: distribute the arrayList elements to the correct buckets.
        if(arrayList instanceof String[]) {
            for (int i = 0; i < arrayList.length; i++) {
                // get the index based on the first character int value (a - z lowercase)
                int firstCharNumber = Character.getNumericValue(arrayList[i].toString().toLowerCase().charAt(0));
                buckets.getElementAt(firstCharNumber - BUCKET_INDEX_START_STRING - 1).add(arrayList[i]);
            }
        } else if (arrayList instanceof Integer[]) {
            //TODO: STILL NEEDS THE IMPLEMENTATION OF BUCKETS FOR NUMBERS
            
        } else {
            //TODO: STILL NEEDS A MORE GENERAL VERSION FOR ANY TYPE.
        }

        System.out.println("Buckets");

        // Sort each non-empty bucket list.
        for (int i = 0; i < buckets.size(); i++) {
            ArrayList<Type> bucketList = buckets.getElementAt(i);
            System.out.println("Bucket (" + i + "): " + Joiner.on(", ").join(bucketList));
            if (bucketList.size() > 0) {
                bucketList.insertionSort(comparator);
            }
        }

        // Gather: visit each bucket list and place the elements into the array.
        int generalIndex = -1;
        for (int i = 0; i < buckets.size(); i++) {
            // each element of the bucket is an Iterable
            for (Type bucketListElement : buckets.getElementAt(i)) {
                arrayList[++generalIndex] = bucketListElement;
            }
        }
    }

}
