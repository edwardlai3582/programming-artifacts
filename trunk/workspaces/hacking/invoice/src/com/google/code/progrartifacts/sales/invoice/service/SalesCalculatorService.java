package com.google.code.progrartifacts.sales.invoice.service;

import com.google.code.progrartifacts.sales.invoice.model.BasketItems;
import com.google.code.progrartifacts.sales.invoice.model.ProductTaxRateType;
import com.google.code.progrartifacts.sales.invoice.model.ShoppingBasket;

public enum SalesCalculatorService {

    INSTANCE;

    /**
     * @return The total price of the basket basket item before taxes.
     */
    public float calculateTotalPrice(BasketItems item) {
        return item.getQuantity() * item.getUnitPrice().getValue();
    }

    /**
     * @param item is a given basket item.
     * @return the sale tax for this basket item.
     */
    public float calculateSaleTax(BasketItems item) {
        float taxes = 0;
        float totalPrice = calculateTotalPrice(item);
        for (ProductTaxRateType taxRate : item.getApplicableTaxes()) {
            taxes += totalPrice * taxRate.getSalesTaxRate();
        }
        return (float)Math.ceil(taxes / .05f) * .05f;
    }

    /**
     * @param item is a given basket item.
     * @return the total sale cost for this given item after taxes.
     */
    public float calculateTotalCost(BasketItems item) {
        return calculateTotalPrice(item) + calculateSaleTax(item);
    }

    /**
     * @param item is a given basket item.
     * @return the total sale cost for this given item after taxes.
     */
    public float calculateTotalSaleTax(BasketItems item) {
        return calculateTotalPrice(item) + calculateSaleTax(item);
    }

    /**
     * @param item is a given basket item.
     * @return the total sale cost for this given item after taxes.
     */
    public float calculateTotalCost(ShoppingBasket<BasketItems> shoppingBasket) {
        float totalCost = 0;
        for (BasketItems basketItem : shoppingBasket) {
            totalCost += calculateTotalCost(basketItem);
        }
        return totalCost;
    }

    /**
     * @param item is a given basket item.
     * @return the total sale cost for this given item after taxes.
     */
    public float calculateTotalTaxes(ShoppingBasket<BasketItems> shoppingBasket) {
        float totalTax = 0;
        for (BasketItems basketItem : shoppingBasket) {
            totalTax += calculateSaleTax(basketItem);
        }
        return totalTax;
    }
}
