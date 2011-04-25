package com.google.code.datastrut.list;

import com.google.code.datastrut.IndexOutOfBoundsException;
import com.google.code.datastrut.Iterator;
import com.google.code.datastrut.sort.Sortable;

public class ArrayList<Type> implements List<Type>, Indexable<Type> {

    private Type[] arrayList;
    private int capacity;
    private int currentSize;

    public ArrayList(int capacity) {
        this.capacity = capacity;
        this.arrayList = makeNewArray(capacity);
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
    public Iterator<Type> getIterator() {
        Iterator<Type> it = new Iterator<Type>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && arrayList[currentIndex] != null;
            }

            @Override
            public Type getNext() {
                return arrayList[currentIndex++];
            }
        };
        return it;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 3, 4, 5};
        List<Integer> list = new ArrayList<Integer>(numbers.length);
        for(int num : numbers) {
            list.add(num);
        }
        System.out.print("All 5 elements...");
        Iterator<Integer> allNumbers = list.getIterator();
        while (allNumbers.hasNext()) {
            Integer value = allNumbers.getNext();
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
        System.out.println("All elements after deleting 2...");
        allNumbers = list.getIterator();
        while (allNumbers.hasNext()) {
            Integer value = allNumbers.getNext();
            if (allNumbers.hasNext()) {
                System.out.print(value + ", ");
            } else {
                System.out.print(value);
            }
        }
        System.out.println("");
        System.out.println("Removing all elements...");
        ArrayList<Integer> arrayListRef = (ArrayList<Integer>)list;
        while (!list.isEmpty()) {
            allNumbers = list.getIterator();
            System.out.print("Current Elements: ");
            while (allNumbers.hasNext()) {
                Integer value = allNumbers.getNext();
                if (allNumbers.hasNext()) {
                    System.out.print(value + ", ");
                } else {
                    System.out.print(value);
                }
            }
            Integer value = arrayListRef.getElementAt(0);
            System.out.println(" -> Removing " + value);
            list.remove(value);
        }
    }

}
