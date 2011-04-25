package com.google.code.datastrut.queue;

import com.google.code.datastrut.Element;
import com.google.code.datastrut.Iterator;

public class QueueImpl<Type> implements Queue<Type> {

    private Element<Type> head, tail;
    private int size;

    @Override
    public void enqueue(Type newElement) {
        Element<Type> newTail = new Element<Type>(newElement);
        if (this.size == 0) {
            this.head = newTail;
        } else {
            this.tail.setNext(newTail);
        }
        this.tail = newTail;
        this.size++;
    }

    @Override
    public Type dequeue() {
        if (this.size == 0) {
            throw new IllegalStateException("The queue is empty!");
        }
        Type value = this.head.getValue();
        this.head = this.head.getNext();
        this.size--;
        if (this.size == 0) {
            this.tail = null;
        }
        return value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Head - " + this.head);
        builder.append("Tail - " + this.tail);
        Element<Type> current = this.tail;
        while(current != null && current.getNext() != null) {
            builder.append(" " + current.getNext());
            current = current.getNext();
        }
        return builder.toString();
    }

    @Override
    public Iterator<Type> getIterator() {
        Iterator<Type> it = new Iterator<Type>() {

            Element<Type> current = head;

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
    public int size() {
        return this.size;
    }
    

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Type front() {
        return this.head.getValue();
    }

    @Override
    public Type back() {
        return this.tail.getValue();
    }

    public static void main(String[] args) {
        QueueImpl<Integer> line = new QueueImpl<Integer>();
        for (int i = 1; i <= 10; i++) {
            line.enqueue(i);
        }
        // printing with iterator
        Iterator<Integer> it = line.getIterator();
        while(it.hasNext()) {
            Integer value = it.getNext();
            if (it.hasNext()) {
                System.out.print(value + ", ");
            } else {
                System.out.print(value);
            }
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println("######### Current State #########");
            System.out.println(line);
            Integer retrieved = line.dequeue();
            System.out.println("Removed: " + retrieved);
            System.out.println();
        }
        
    }

}
