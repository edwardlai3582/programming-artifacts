package com.google.code.progrartifacts.sales.invoice.service;

import com.google.code.progrartifacts.sales.invoice.model.BasketItems;

public enum BasketItemsDecorator {

    /**
     * Prints the receipt invoice with the total costs.
     */
    RECEIPT_ITEM_STRATEGY,
    /**
     * Prints the sale invoice without the totals.
     */
    SALE_ITEM_STRATEGY;

    public String decorate(BasketItems item, SalesCalculatorService calculator) {
        StringBuilder decoratedItem = new StringBuilder();

        // quantity 
        decoratedItem.append(item.getQuantity());
        decoratedItem.append(" ");

        // descriptor
        decoratedItem.append(item.getDescription());

        // price
        if (this == SALE_ITEM_STRATEGY) {
            decoratedItem.append(" at ");
            decoratedItem.append(item.getUnitPrice().toStringWithoutCurrencySymbol());

        } else {
            decoratedItem.append(": ");
            decoratedItem.append(calculator.calculateTotalCost(item));
        }
        return decoratedItem.toString();
    }
}