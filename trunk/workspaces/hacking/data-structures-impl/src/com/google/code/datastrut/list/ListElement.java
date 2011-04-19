package com.google.code.datastrut.list;

public class ListElement<Type> {

    private ListElement<Type> previous;
    private Type value;
    private ListElement<Type> next;

    public ListElement(Type newValue) {
        this.value = newValue;
    }

    public ListElement<Type> getPrevious() {
        return previous;
    }

    public void setPrevious(ListElement<Type> previous) {
        this.previous = previous;
    }

    public Type getValue() {
        return this.value;
    }

    public ListElement<Type> getNext() {
        return this.next;
    }

    public void setNext(ListElement<Type> next) {
        this.next = next;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ListElement<Type> other = (ListElement) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ListElement [previous=" + previous + ", value=" + value + ", next=" + next + "]";
    }

}
