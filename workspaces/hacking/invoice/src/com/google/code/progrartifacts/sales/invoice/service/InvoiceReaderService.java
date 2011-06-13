package com.google.code.progrartifacts.sales.invoice.service;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.code.progrartifacts.sales.invoice.model.BasketItemAbstractFactory;
import com.google.code.progrartifacts.sales.invoice.model.BasketItems;
import com.google.code.progrartifacts.sales.invoice.model.ShoppingBasket;
import com.google.code.progrartifacts.util.TextFileReader;

/**
 * This service is responsible for loading invoices. Extensions may be added here depending
 * on the invoice location such as remote, WS, FTP, etc...
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public enum InvoiceReaderService {

    INSTANCE;

    /**
     * Loads the given invoice File as a Shopping Basket file.
     * @param invoiceFile is the invoice file.
     * @return an instance of ShoppingBasket<BasketItems> with the BasketItems.
     * @throws IOException if there is any problem while loading the invoice file.
     */
    public ShoppingBasket<BasketItems> loadShoppingBasket(File invoiceFile) throws IOException {
        ShoppingBasket<BasketItems> basket = ShoppingBasket.newInstance();

        // load the invoice items as texts.
        List<String> invoceItemsTextList = new LinkedList<String>();
        TextFileReader.INSTANCE.from(invoiceFile).to(invoceItemsTextList).read();

        for (String invoceItemText : invoceItemsTextList) {
            // build a Basket Item for each invoice text line.
            BasketItems item = BasketItemAbstractFactory.INSTANCE.create(invoceItemText);

            // add the item to the shopping basket.
            basket.add(item);
        }

        return basket;
    }
}
