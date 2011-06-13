package com.google.code.progrartifacts.sales.invoice.view;

import java.io.PrintStream;
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
public enum ConsoleViewStrategy implements InvoiceView {

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
    private static final PrintStream printStream = System.out;
    /**
     * The output produced.
     */
    private StringBuilder outputBuffer;
    /**
     * The instance of the calculator service.
     */
    private SalesCalculatorService calculatorService = SalesCalculatorService.INSTANCE;

    @Override
    public String getOutput() {
        return outputBuffer.toString();
    }

    @Override
    public void render(ShoppingBasket<BasketItems> basket, BasketItemsDecorator decorator) {
        outputBuffer = new StringBuilder();
        for (BasketItems item : basket) {
            printStream.println(decorator.decorate(item, calculatorService));
            outputBuffer.append(decorator.decorate(item, calculatorService));
            outputBuffer.append("\n");
        }
        if (decorator == BasketItemsDecorator.RECEIPT_ITEM_STRATEGY) {
            printStream.print(TAXES_TERM);
            outputBuffer.append(TAXES_TERM);

            String taxes = CURRENCY_FORMATTER.format(calculatorService.calculateTotalTaxes(basket)).replace("$", "");
            printStream.print(taxes);
            outputBuffer.append(taxes);

            printStream.println("");
            outputBuffer.append("\n");

            printStream.print(TOTAL_TERM);
            outputBuffer.append(TOTAL_TERM);
            printStream.print(calculatorService.calculateTotalCost(basket));
            outputBuffer.append(calculatorService.calculateTotalCost(basket));
        }
    }
}
