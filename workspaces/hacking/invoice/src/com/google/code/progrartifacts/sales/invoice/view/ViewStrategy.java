package com.google.code.progrartifacts.sales.invoice.view;

/**
 * View strategy selector.
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com).
 *
 */
public enum ViewStrategy {

    CONSOLE, BUFFER;

    public InvoiceView getRender() {
        switch(this) {
        case CONSOLE: 
            return ConsoleViewStrategy.INSTANCE;

        case BUFFER: 
            return StringBuilderViewStrategy.INSTANCE;

        default: return ConsoleViewStrategy.INSTANCE;
        }
    }
}
