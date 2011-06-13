package com.google.code.progrartifacts.sales.invoice.model;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A Shopping Basket represents a bag of Extensible Basket Items. This shopping
 * basket accepts any implementation of Basket Items. In addition, this basket is
 * iterable by  the Basket Items for easy navigation of the items.
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public final class ShoppingBasket<T extends BasketItems> implements Iterable<T> {

    /**
     * The set of Items.
     */
    private Set<T> items;

    /**
     * Private constructor. Use newInstance() method.
     */
    private ShoppingBasket() {
        this.items = new LinkedHashSet<T>();
    }

    /**
     * @param <T>
     * @return an instance of shopping basket without elements.
     */
    public static <T extends BasketItems> ShoppingBasket<T> newInstance() {
        return new ShoppingBasket<T>();
    }

    /**
     * Adds an element to the basket.
     * @param item is an element to be added to the basket.
     */
    public void add(T item) {
        this.items.add(item);
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private Iterator<T> proxyIterator = items.iterator();

            @Override
            public boolean hasNext() {
                return proxyIterator.hasNext();
            }

            @Override
            public T next() {
                return proxyIterator.next();
            }

            @Override
            public void remove() {
                throw new IllegalAccessError("Operation not implemented.");
            }
        };
        return it;
    }
}
