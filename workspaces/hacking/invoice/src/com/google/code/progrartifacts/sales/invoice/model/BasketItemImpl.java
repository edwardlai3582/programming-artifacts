package com.google.code.progrartifacts.sales.invoice.model;

import java.util.Set;

import com.google.common.base.Objects;

/**
 * The Basket Item Impl is an immutable representation of an invoice textual line.
 * Mode details available in the contract interface {@link BasketItems}
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public final class BasketItemImpl implements BasketItems {

    /**
     * The description of the basket items.
     */
    private final String description;
    /**
     * The price value of a single unit price.
     */
    private final Money unitPrice;
    /**
     * The quantity on items.
     */
    private final int quantity;
    /**
     * The set of applicable taxes to this basket item. This resembles a Strategy, but it is used
     * to represent a multi-value type instead of Bit Maps. See Effective Java 2nd Edition.
     */
    private final Set<ProductTaxRateType> applicableTaxes;

    /**
     * Creates a new basket Item impl with the given values.
     * @param quantity is the quantity of items.
     * @param description is the description of a unit.
     * @param price is the unit price of the item.
     * @param goodRates is the tax rates for an item.
     */
    public BasketItemImpl(int quantity, String description, Money price, Set<ProductTaxRateType> goodRates) {
        this.description = description;
        this.unitPrice = price;
        this.quantity = quantity;
        this.applicableTaxes = goodRates;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }

    @Override
    public final Money getUnitPrice() {
        return this.unitPrice;
    }

    @Override
    public final int getQuantity() {
        return this.quantity;
    }

    @Override
    public final Set<ProductTaxRateType> getApplicableTaxes() {
        return this.applicableTaxes;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BasketItems)) {
            return false;
        }
        BasketItems that = (BasketItems)obj;
        return Objects.equal(this.quantity, that.getQuantity()) &&
            Objects.equal(this.description, that.getDescription()) &&
            Objects.equal(this.unitPrice, that.getUnitPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.quantity, this.description, this.unitPrice);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("quantity", this.quantity)
            .add("description", this.description)
            .add("price", this.unitPrice).toString();
    }
}
