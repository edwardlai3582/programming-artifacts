package com.google.code.progrartifacts.sales.invoice.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.google.common.base.Preconditions;

/**
 * The Abstract Factory to build new Basket Items from the textual representation from the input invoice.
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com).
 */
public enum BasketItemAbstractFactory {

    /**
     * The singleton instance of this factory.
     */
    INSTANCE;
    /**
     * The token to identify the imported items.
     */
    private static final String IMPORTED_TOKEN = "imported";
    /**
     * The list of tokens that represents the tax-exempt items.
     */
    private static final List<String> EXEMPT_ITEMS_TOKENS = new ArrayList<String>();
    /**
     * Initializing the exempt tokens before the class is instantiated.
     */
    static {
        EXEMPT_ITEMS_TOKENS.add("book");
        EXEMPT_ITEMS_TOKENS.add("chocolate");
        EXEMPT_ITEMS_TOKENS.add("pills");
    }

    /**
     * Abstract Factory method that makes an item instance.
     * @param itemLine is a line item like "1 chocolate bar at 0.85"
     * @return an instance of Item.
     */
    public BasketItems create(String itemLine) {
        Preconditions.checkArgument(itemLine != null, "The item line must be provided.");

        Scanner itemScanner = new Scanner(itemLine);
        // scan the tokens for the quantity of an item line.
        int quantity = -1;
        if (itemScanner.hasNextInt()) {
            quantity = itemScanner.nextInt();

        } else {
            throw new IllegalStateException("The given itemLine '" + itemLine + "' must start with an amount.");
        }

        // scan the tokens for the description of an item line, excluding the token "at".
        StringBuilder builder = new StringBuilder();
        while (itemScanner.hasNext()) {
            String descriptionToken = itemScanner.next();
            if (descriptionToken.equals("at")) {
                break;
            }
            builder.append(descriptionToken);
            builder.append(" ");
        }
        String itemDescription = builder.toString().trim();

        // scan the price token an item line.
        float price = -1f;
        if (itemScanner.hasNextFloat()) {
            price = itemScanner.nextFloat();
        }

        // Add the tax rate for all items first.
        Set<ProductTaxRateType> multiTaxValue = new HashSet<ProductTaxRateType>();
        multiTaxValue.add(ProductTaxRateType.REGULAR);

        // If this is an tax-exempt item, remove the tax.
        for (String exemptToken : EXEMPT_ITEMS_TOKENS) {
            if (itemDescription.toLowerCase().contains(exemptToken)) {
                multiTaxValue.remove(ProductTaxRateType.REGULAR);
                multiTaxValue.add(ProductTaxRateType.TAX_EXEMPT);
                break;
            }
        }

        // verify if the item is imported.
        if (itemDescription.toLowerCase().contains(IMPORTED_TOKEN)) {
            multiTaxValue.add(ProductTaxRateType.IMPORTED);
        }
        return new BasketItemImpl(quantity, itemDescription, Money.newInstance(price), multiTaxValue);
    }
}
