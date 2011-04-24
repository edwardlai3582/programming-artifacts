package com.google.code.datastrut.stack;

import com.google.code.datastrut.Iterator;
import com.google.code.datastrut.list.Element;

public class StackImpl<Type> implements Stack<Type> {

    private Element<Type> top;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(Type newElement) {
        Element<Type> newTop = new Element<Type>(newElement);
        if (this.size == 0) {
            this.top = newTop;
        } else {
            newTop.setNext(this.top);
            this.top = newTop;
        }
        this.size++;
    }

    @Override
    public Type pop() {
        if (this.size == 0) {
            throw new IllegalStateException("The stack is empty!");
        }
        Type topValue = this.top.getValue();
        this.top = this.top.getNext();
        this.size--;
        return topValue;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Element<Type> current = this.top;
        builder.append("Top -> " + this.top);
        while(current != null) {
            Type val = current.getValue();
            builder.append("" + val);
            current = current.getNext();
        }
        return builder.toString();
    }

    @Override
    public Iterator<Type> getIterator() {
        Iterator<Type> it = new Iterator<Type>() {

            private Element<Type> current = top;

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
        StackImpl<Integer> plates = new StackImpl<Integer>();
        for (int i = 1; i <= 10; i++) {
            plates.push(i);
            System.out.println("Current State");
            System.out.println(plates);
        }

        Iterator<Integer> it = plates.getIterator();
        System.out.print("Iterating... ");
        while(it.hasNext()) {
            System.out.print(it.getNext() + ", ");
        }
        System.out.println();

        while(plates.size() > 0) {
            System.out.println("Current State");
            System.out.println(plates);
            Integer removedVal = plates.pop();
            System.out.println("Removed Val = " + removedVal);
        }
    }

}
