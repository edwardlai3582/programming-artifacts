package com.google.code.datastrut.stack;

import java.util.Iterator;

import com.google.code.datastrut.Element;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

public class StackImpl<Type> implements Stack<Type> {

    private Element<Type> top;
    private int size;

    public StackImpl() {

    }

    public StackImpl(Type initialTop) {
        Preconditions.checkArgument(initialTop != null, "The initial value must be provided.");
        this.push(initialTop);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(Type newElement) {
        Preconditions.checkArgument(newElement != null, "The element to be pushed must be provided.");

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
        Preconditions.checkState(!this.isEmpty(), "Stack underflow: There is no elements to be popped.");

        Type topValue = this.top.getValue();
        this.top = this.top.getNext();
        this.size--;
        return topValue;
    }

    @Override
    public Type peek() {
        Preconditions.checkState(!this.isEmpty(), "There is no elements in the top of the stack.");
        return this.top.getValue();
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
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
    public Iterator<Type> iterator() {
        Iterator<Type> it = new Iterator<Type>() {

            private Element<Type> current = top;

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
        StackImpl<Integer> plates = new StackImpl<Integer>();
        for (int i = 1; i <= 10; i++) {
            plates.push(i);
            System.out.println("Current State: " + Joiner.on(", ").join(plates));
        }

        System.out.println("Elements joined by Google Guava: " + Joiner.on(" <- ").join(plates));
        System.out.print("foreach... ");
        for (Integer plate : plates) {
            System.out.print(plate + ", ");
        }

        System.out.println("");
        System.out.println("Removing elements... ");
        while(plates.size() > 0) {
            System.out.println("Current State: " + Joiner.on(", ").join(plates));
            Integer removedVal = plates.pop();
            System.out.println("Removed Val = " + removedVal);
        }
    }

}
