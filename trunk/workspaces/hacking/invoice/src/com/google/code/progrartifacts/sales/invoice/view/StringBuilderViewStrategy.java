package com.google.code.progrartifacts.sales.invoice.view;

import java.text.NumberFormat;

import com.google.code.progrartifacts.sales.invoice.model.BasketItems;
import com.google.code.progrartifacts.sales.invoice.model.ShoppingBasket;
import com.google.code.progrartifacts.sales.invoice.service.BasketItemsDecorator;
import com.google.code.progrartifacts.sales.invoice.service.SalesCalculatorService;

/**
 * This is a console view of the invoice.
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public enum StringBuilderViewStrategy implements InvoiceView {

    /**
     * The singleton instance.
     */
    INSTANCE;
    /**
     * Current formatter.
     */
    private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance();
    /**
     * The sales taxes term to be print.
     */
    private static final String TAXES_TERM = "Sales Taxes: ";
    /**
     * The total value term to be printed.
     */
    private static final String TOTAL_TERM = "Total: ";
    /**
     * The print stream of this view.
     */
    private StringBuilder builderView = new StringBuilder();
    /**
     * The instance of the calculator service.
     */
    private SalesCalculatorService calculatorService = SalesCalculatorService.INSTANCE;

    @Override
    public void render(ShoppingBasket<BasketItems> basket, BasketItemsDecorator decorator) {
        for (BasketItems item : basket) {
            builderView.append(decorator.decorate(item, calculatorService));
            builderView.append("\n");
        }
        if (decorator == BasketItemsDecorator.RECEIPT_ITEM_STRATEGY) {
            builderView.append(TAXES_TERM);
            builderView.append(CURRENCY_FORMATTER.format(calculatorService.calculateTotalTaxes(basket)).replace("$", ""));

            builderView.append("\n");

            builderView.append(TOTAL_TERM);
            builderView.append(calculatorService.calculateTotalCost(basket));
        }
    }

    @Override
    public String getOutput() {
        return this.builderView.toString();
    }
}
