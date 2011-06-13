package com.google.code.progrartifacts.sales.invoice.model;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Money representation that can be added. 
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public final class Money {

    /**
     * The currency formatter.
     */
    private static final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance();
    /**
     * The default currency object dependent on the JVM current default locale, used
     * to remove the currency symbol.
     */
    private static final Currency CURRENCY = Currency.getInstance(Locale.getDefault());
    /**
     * The value of the money instance.
     */
    private float value;

    /**
     * Creates a new money with the given value.
     * 
     * @param initialValue is the initial value.
     */
    private Money(float initialValue) {
        this.value = initialValue;
    }

    /**
     * Factory method to create a new money.
     * @param initValue the initial value.
     * @return a new instance of money.
     */
    public static Money newInstance(float initValue) {
        return new Money(initValue);
    }

    /**
     * @return the value of this money.
     */
    public float getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return FORMATTER.format(this.value);
    }

    /**
     * @return the money representation without the currency symbol.
     */
    public String toStringWithoutCurrencySymbol() {
        return FORMATTER.format(this.value).replace(CURRENCY.getSymbol(), "");
    }

    /**
     * @param sumValue is an additional sum value.
     * @return the current instance updated with the sum value.
     */
    public Money add(float sumValue) {
        this.value += sumValue;
        return this;
    }
}
