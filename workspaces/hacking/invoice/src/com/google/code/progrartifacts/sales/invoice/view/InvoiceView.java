package com.google.code.progrartifacts.sales.invoice.view;

import com.google.code.progrartifacts.sales.invoice.model.BasketItems;
import com.google.code.progrartifacts.sales.invoice.model.ShoppingBasket;
import com.google.code.progrartifacts.sales.invoice.service.BasketItemsDecorator;

/**
 * Renders the Shopping Basket.
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public interface InvoiceView {

    /**
     * Render a given shopping basket.
     * @param basket is an instance of a basket.
     * @param decorator is the decorator to help render the basket items.
     */
    public void render(ShoppingBasket<BasketItems> basket, BasketItemsDecorator decorator);
    /**
     * @return the output produced for the view.
     */
    public String getOutput();
}
