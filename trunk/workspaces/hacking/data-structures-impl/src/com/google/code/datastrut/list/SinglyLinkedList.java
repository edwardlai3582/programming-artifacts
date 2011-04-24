package com.google.code.datastrut.list;

import com.google.code.datastrut.Element;
import com.google.code.datastrut.Iterator;

public class SinglyLinkedList<Type> implements List<Type> {

    private Element<Type> head;

    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(Type newValue) {
        Element<Type> newElement = new Element<Type>(newValue);
        if (this.size == 0) {
            this.head = newElement;
        } else {
            Element<Type> current = this.head;
            while(current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newElement);
        }
        this.size++;
    }

    @Override
    public boolean remove(Type element) {
        if (this.size == 0) {
            throw new IllegalStateException("The list is empty!");
        }
        Element<Type> current = this.head, previous = null;
        boolean found = false;
        while(!current.getValue().equals(element) && current.getNext() != null) {
            previous = current;
            current = current.getNext();
        }
        if (current != null) { // found on current.
            if (previous == null) { // previous was the head
                this.head = current.getNext();
            } else {
                previous.setNext(current.getNext());
            }
            found = true;
            this.size--;
        }
        return found;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Element<Type> current = this.head;
        while(current != null) {
            Type val = current.getValue();
            current = current.getNext();
            builder.append(val + ", ");
        }
        return builder.substring(0, builder.length()-2);
    }

    @Override
    public synchronized Iterator<Type> getIterator() {
        // singly-linked list iterator
        Iterator<Type> it = new Iterator<Type>() {

            private Element<Type> current = head;

            @Override
            public boolean hasNext() {
                return this.current != null;
            }

            @Override
            public Type getNext() {
                Type val = this.current.getValue();
                this.current = this.current.getNext();
                return val;
            }
            
        };
        return it;
    }

    public static void main(String[] args) {

        SinglyLinkedList<Integer> line = new SinglyLinkedList<Integer>();
        for (int i = 1; i <= 10; i++) {
            line.add(i);
            System.out.println(line);
            System.out.println();
        }

//        for (int i = 1; i <= 10; i++) {
//            System.out.println(line);
//            boolean removed = line.remove(i);
//            System.out.println("Removed " + i + "? " + removed);
//            System.out.println();
//        }

        // Iterate with Iterators
        Iterator<Integer> it = line.getIterator();
        while(it.hasNext()) {
            System.out.println(it.getNext() + ", ");
        }
        
    }
}
