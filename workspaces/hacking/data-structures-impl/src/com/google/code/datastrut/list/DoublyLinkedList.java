package com.google.code.datastrut.list;

import java.util.Iterator;

import com.google.code.datastrut.Navigable;
import com.google.code.datastrut.NavigatableElement;
import com.google.code.datastrut.ReverseIterator;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;


public class DoublyLinkedList<Type> implements List<Type>, Navigable<Type> {

    private NavigatableElement<Type> head, tail;

    private int size;

    @Override
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void insertHead(Type newValue) {
        Preconditions.checkArgument(newValue != null, "The initial value must be provided.");

        NavigatableElement<Type> newElement = new NavigatableElement<Type>(newValue);
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
        Preconditions.checkArgument(newValue != null, "The new tail value must be provided.");

        NavigatableElement<Type> newElement = new NavigatableElement<Type>(newValue);
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
        Preconditions.checkArgument(newValue != null, "The new value must be provided.");
        this.insertTail(newValue);
    }

    @Override
    public boolean remove(Type element) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Iterator<Type> iterator() {
        Iterator<Type> it = new Iterator<Type>() {

            NavigatableElement<Type> current = head;

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

    @Override
    public ReverseIterator<Type> getReverseIterator() {
        ReverseIterator<Type> it = new ReverseIterator<Type>() {

            NavigatableElement<Type> current = tail;

            @Override
            public boolean hasPrevious() {
                return current != null;
            }

            @Override
            public Type previous() {
                Type val = this.current.getValue();
                this.current = this.current.getPrevious();
                return val;
            }

        };
        return it;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DoublyLinkedList: [");
        Joiner.on(", ").appendTo(builder, this);
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> line = new DoublyLinkedList<Integer>();
        for (int i = 1; i <= 10; i++) {
            line.add(i);
            System.out.println(line);
        }

//        for (int i = 1; i <= 10; i++) {
//            System.out.println(line);
//            boolean removed = line.remove(i);
//            System.out.println("Removed " + i + "? " + removed);
//            System.out.println();
//        }

        // Iterate with Iterators
        for(Integer value : line) {
            System.out.print(value + ", ");
        }

        System.out.println("");
        System.out.println("All the elements joined by Google Guava");
        System.out.println(Joiner.on(", ").join(line));

        // Iterate with Iterators
        System.out.println("");
        System.out.println("Reverting...");
        ReverseIterator<Integer> rit = line.getReverseIterator();
        while(rit.hasPrevious()) {
            System.out.print(rit.previous() + ", ");
        }
    }

}
