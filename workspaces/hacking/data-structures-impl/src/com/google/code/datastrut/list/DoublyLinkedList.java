package com.google.code.datastrut.list;

import com.google.code.datastrut.Iterator;
import com.google.code.datastrut.Navigable;
import com.google.code.datastrut.ReverseIterator;


public class DoublyLinkedList<Type> implements List<Type>, Navigable<Type> {

    private ListElement<Type> head, tail;

    private int size;

    @Override
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void insertHead(Type newValue) {
        ListElement<Type> newElement = new ListElement<Type>(newValue);
        if (this.isEmpty()) {
            this.tail = newElement;
        } else {
            this.head.setPrevious(newElement);
        }
        newElement.setNext(this.head);
        this.head = newElement;
        this.size++;
    }

    public void insertTail(Type newValue) {
        ListElement<Type> newElement = new ListElement<Type>(newValue);
        if (this.isEmpty()) {
            this.head = newElement;
        } else {
            this.tail.setNext(newElement);
            newElement.setPrevious(this.tail);
        }
        this.tail = newElement;
        this.size++;
    }

    @Override
    public void add(Type newValue) {
        this.insertTail(newValue);
    }

    @Override
    public boolean remove(Type element) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Iterator<Type> getIterator() {
        Iterator<Type> it = new Iterator<Type>() {

            ListElement<Type> current = head;

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

    @Override
    public ReverseIterator<Type> getReverseIterator() {
        ReverseIterator<Type> it = new ReverseIterator<Type>() {

            ListElement<Type> current = tail;

            ListElement<Type> reverse = head;

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

            @Override
            public boolean hasPrevious() {
                return reverse != null;
            }

            @Override
            public Type getPrevious() {
                Type val = this.reverse.getValue();
                this.reverse = this.reverse.getPrevious();
                return val;
            }
        };
        return it;
    }

    @Override
    public String toString() {
        Iterator<Type> it = this.getIterator();
        StringBuilder builder = new StringBuilder();
        builder.append("DoublyLinkedList: [ ");
        while (it.hasNext()) {
            builder.append(it.getNext() + ", ");
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> line = new DoublyLinkedList<Integer>();
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

        // Iterate with Iterators
        ReverseIterator<Integer> rit = line.getReverseIterator();
        while(rit.hasPrevious()) {
            System.out.println(rit.getPrevious() + ", ");
        }
    }

}
