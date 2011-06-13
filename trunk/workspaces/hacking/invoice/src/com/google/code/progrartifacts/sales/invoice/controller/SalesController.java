package com.google.code.progrartifacts.sales.invoice.controller;

import java.io.File;
import java.io.IOException;

import com.google.code.progrartifacts.sales.invoice.model.BasketItems;
import com.google.code.progrartifacts.sales.invoice.model.ShoppingBasket;
import com.google.code.progrartifacts.sales.invoice.service.BasketItemsDecorator;
import com.google.code.progrartifacts.sales.invoice.service.InvoiceReaderService;
import com.google.code.progrartifacts.sales.invoice.view.ViewStrategy;

/**
 * This is the sales controller with the main user stories.
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public enum SalesController {

    /**
     * The singleton instance.
     */
    INSTANCE;
    /**
     * The sales invoice reader service.
     */
    private InvoiceReaderService invoiceReaderService = InvoiceReaderService.INSTANCE;

    /**
     * Prints the invoice as shown in the Sale as it was given in the file.
     * @param basket is the shopping basket.
     * @param printStreamView is the print stream to print to.
     * @throws IOException in case there is a problem reading the file.
     */
    public void printSaleInvoice(File invoiceFile, ViewStrategy view) throws IOException {
        ShoppingBasket<BasketItems> basket = invoiceReaderService.loadShoppingBasket(invoiceFile);
        BasketItemsDecorator decorator = BasketItemsDecorator.SALE_ITEM_STRATEGY;
        view.getRender().render(basket, decorator);
    }

    /**
     * Prints the invoice as a Receipt of the shopping basket.
     * @param basket is the shopping basket instance.
     * @param view is the selected view to print to.
     * @throws IOException in case there is a problem reading the file.
     */
    public void printReceiptInvoice(File invoiceFile, ViewStrategy view) throws IOException {
        ShoppingBasket<BasketItems> basket = invoiceReaderService.loadShoppingBasket(invoiceFile);
        BasketItemsDecorator decorator = BasketItemsDecorator.RECEIPT_ITEM_STRATEGY;
        view.getRender().render(basket, decorator);
    }
}
