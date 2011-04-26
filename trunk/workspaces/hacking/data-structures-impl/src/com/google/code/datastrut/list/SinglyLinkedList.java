package com.google.code.datastrut.list;

import java.util.Iterator;

import com.google.code.datastrut.Element;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

public class SinglyLinkedList<Type> implements List<Type> {

    private Element<Type> head;

    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(Type newValue) {
        Element<Type> newElement = new Element<Type>(newValue);
        if (this.isEmpty()) {
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
        Preconditions.checkState(!this.isEmpty(), "The list is empty!");

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
            if (current != null) {
                builder.append(val + ", ");
            } else {
                builder.append(val);
            }
        }
        return builder.toString();
    }

    @Override
    public synchronized Iterator<Type> iterator() {
        // singly-linked list iterator
        Iterator<Type> it = new Iterator<Type>() {

            private Element<Type> current = head;

            @Override
            public boolean hasNext() {
                return this.current != null;
            }

            @Override
            public Type next() {
                Type val = this.current.getValue();
                this.current = this.current.getNext();
                return val;
            }

            @Override
            public void remove() {
                // TODO Auto-generated method stub
                
            }
            
        };
        return it;
    }

    public static void main(String[] args) {

        SinglyLinkedList<Integer> line = new SinglyLinkedList<Integer>();
        for (int i = 1; i <= 10; i++) {
            line.add(i);
            System.out.println(line);
        }

        // Iterate with Iterators
        System.out.println("All the elements joined by Google Guava");
        System.out.println(Joiner.on(", ").join(line));
    }

}
