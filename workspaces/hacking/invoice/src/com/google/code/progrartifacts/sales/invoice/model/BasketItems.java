package com.google.code.progrartifacts.sales.invoice.model;

import java.util.Set;

/**
 * The Basket Items represents a given amount of the same item described by the
 * description. The quantity and unit price of items is also given.
 *
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public interface BasketItems {

    /**
     * @return The description of a unit of the items.
     */
    String getDescription();
    /**
     * @return The unit price of an item.
     */
    Money getUnitPrice();
    /**
     * @return The number similar items.
     */
    int getQuantity();
    /**
     * @return the list of applicable taxes to the type of item.
     * @see ProductTaxRateType
     */
    Set<ProductTaxRateType> getApplicableTaxes();
}
