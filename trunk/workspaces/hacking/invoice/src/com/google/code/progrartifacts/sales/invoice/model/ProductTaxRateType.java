package com.google.code.progrartifacts.sales.invoice.model;

/**
 * The tax rate values.
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com).
 *
 */
public enum ProductTaxRateType {

    /**
     * All types of products.
     */
    REGULAR(.10f),
    /**
     * books, food, and medical products are not added regular taxes.
     */
    TAX_EXEMPT(.0f),
    /**
     * For imported products.
     */
    IMPORTED(.05f);

    private float rate;

    private ProductTaxRateType(float rate) {
        this.rate = rate;
    }

    public float getSalesTaxRate() {
        return rate;
    }
}
