package com.google.code.datastrut.list;

import java.util.Iterator;

import com.google.code.datastrut.IndexOutOfBoundsException;
import com.google.code.datastrut.sort.AbstractSortStrategy;
import com.google.code.datastrut.sort.Comparator;
import com.google.code.datastrut.sort.Sortable;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

public class ArrayList<Type> implements List<Type>, Indexable<Type>, Sortable<Type> {

    private Type[] arrayList;
    private int capacity;
    private int currentSize;

    public ArrayList(int capacity) {
        Preconditions.checkArgument(capacity > 0, "The capacity must be greater than 0.");

        this.capacity = capacity;
        this.arrayList = makeNewArray(capacity);
    }

    private ArrayList(Type[] initialArray) {
        Preconditions.checkArgument(initialArray != null, "The initial array must be provided.");

        this.capacity = initialArray.length == 0 ? 1 : initialArray.length;
        this.arrayList = makeNewArray(capacity);
        for(Type element : initialArray) {
            this.add(element);
        }
    }

    public static <Type> ArrayList<Type> makeNewArrayList(Type[] newArray) {
        Preconditions.checkArgument(newArray != null, "The array must be provided.");

        return new ArrayList<Type>(newArray);
    }

    /**
     * @param capacity is the number of initial elements.
     * @return a new instance of the array of type with the given capacity.
     */
    private Type[] makeNewArray(int capacity) {
        return (Type[])new Object[capacity];
    }

    @Override
    public void add(Type newElement) {
        Preconditions.checkArgument(newElement != null, "The new element must be provided.");

        if (this.currentSize + 1 == this.capacity) {
            this.capacity *= 2;
            Type[] expandedArray = makeNewArray(this.capacity);
            for (int i = 0; i < arrayList.length; i++) {
                expandedArray[i] = arrayList[i];
            }
            this.arrayList = expandedArray;
        }
        this.arrayList[currentSize++] = newElement;
    }

    @Override
    public boolean remove(Type element) {
        Preconditions.checkArgument(element != null, "The element to be removed must be provided.");

        boolean hasBeenRemoved = false;
        for (int i = 0; i < this.arrayList.length - 1; i++) {
            if (this.arrayList[i] != null && this.arrayList[i].equals(element)) {
                this.arrayList[i] = this.arrayList[i + 1];
                hasBeenRemoved = true;
                this.currentSize--;
                continue;
            }
            if (hasBeenRemoved) {
                this.arrayList[i] = this.arrayList[i + 1];
                this.arrayList[i + 1] = null;
            }
        }
        return hasBeenRemoved;
    }

    @Override
    public Type getElementAt(int index) {
        Preconditions.checkArgument(index >= 0, "The index must be a positive number or 0.");
        Preconditions.checkElementIndex(index, this.capacity, "The index must be smaller or equals to the the " +
                "current capacity.");

        if (this.arrayList[index] == null) {
            throw new IndexOutOfBoundsException(index, "There is no element at index '" + index + "'");
        }
        return this.arrayList[index];
    }

    @Override
    public int size() {
        return this.currentSize;
    }

    @Override
    public boolean isEmpty() {
        return this.currentSize == 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ArrayList capacity = ");
        builder.append(capacity);
        builder.append(" currentSize = ");
        builder.append(currentSize );

        builder.append(" [");
        Joiner.on(", ").skipNulls().appendTo(builder, this.arrayList);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public Iterator<Type> iterator() {
        Iterator<Type> it = new Iterator<Type>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && arrayList[currentIndex] != null;
            }

            @Override
            public Type next() {
                return arrayList[currentIndex++];
            }

            @Override
            public void remove() {
                // TODO Auto-generated method stub
            }
        };
        return it;
    }
    

    @Override
    public Type[] toArray() {
        Type[] array = (Type[])new Object[this.arrayList.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = this.getElementAt(i);
        }
        return array;
    }

    @Override
    public void selectionSort(Comparator<Type> comparator) {
        AbstractSortStrategy.Algorithm.SELECTION_SORT.sort(this.arrayList,
                Preconditions.checkNotNull(comparator , "The comparator must be provided."));
    }

    @Override
    public void bubbleSort(Comparator<Type> comparator) {
        AbstractSortStrategy.Algorithm.BUBBLE_SORT.sort(this.arrayList,
                Preconditions.checkNotNull(comparator , "The comparator must be provided."));
    }

    @Override
    public void cocktailSort(Comparator<Type> comparator) {
        AbstractSortStrategy.Algorithm.COCKTAIL_SORT.sort(this.arrayList, 
                Preconditions.checkNotNull(comparator , "The comparator must be provided."));
    }

    @Override
    public void insertionSort(Comparator<Type> comparator) {
        AbstractSortStrategy.Algorithm.INSERTION_SORT.sort(this.arrayList, 
                Preconditions.checkNotNull(comparator , "The comparator must be provided."));
    }

    @Override
    public void bucketSort(Comparator<Type> comparator) {
        AbstractSortStrategy.Algorithm.BUCKET_SORT.sort(this.arrayList, 
                Preconditions.checkNotNull(comparator , "The comparator must be provided."));
    }

    @Override
    public void mergeSort(Comparator<Type> comparator) {
        AbstractSortStrategy.Algorithm.MERGE_SORT.sort(this.arrayList, 
                Preconditions.checkNotNull(comparator , "The comparator must be provided."));
    }

    @Override
    public void quickSort(Comparator<Type> comparator) {
        AbstractSortStrategy.Algorithm.QUICK_SORT.sort(this.arrayList, 
                Preconditions.checkNotNull(comparator , "The comparator must be provided."));
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 3, 4, 5};
        List<Integer> list = new ArrayList<Integer>(numbers.length);
        for(int num : numbers) {
            list.add(num);
        }
        System.out.print("All 5 elements...");
        Iterator<Integer> allNumbers = list.iterator();
        while (allNumbers.hasNext()) {
            Integer value = allNumbers.next();
            if (allNumbers.hasNext()) {
                System.out.print(value + ", ");
            } else {
                System.out.print(value);
            }
        }
        System.out.println("");
        System.out.println("Deleting element 2");
        list.remove(2);

        System.out.println("");
        System.out.println("All elements after deleting 2... joined by Google Guava");
        System.out.println(Joiner.on(", ").join(list));

        System.out.println("");
        System.out.println("Removing all elements...");
        ArrayList<Integer> arrayListRef = (ArrayList<Integer>)list;
        while (!list.isEmpty()) {
            allNumbers = list.iterator();
            System.out.print("Current Elements: " + list);

            Integer value = arrayListRef.getElementAt(0);
            System.out.println(" -> Removing " + value);
            list.remove(value);
        }
    }

}
