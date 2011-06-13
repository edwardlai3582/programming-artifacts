package com.googlecode.progrartifacts;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.googlecode.progrartifacts.sales.invoice.controller.SalesControllerTests;
import com.googlecode.progrartifacts.sales.invoice.model.BasketItemFactoryTests;
import com.googlecode.progrartifacts.sales.invoice.model.ShoppingBasketTests;
import com.googlecode.progrartifacts.util.TextFileReaderTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    SalesControllerTests.class,
    BasketItemFactoryTests.class,
    ShoppingBasketTests.class,
    TextFileReaderTests.class
})

/**
 * Runs all the test cases from the Invoice tests.
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com)
 */
public class AllSalesTests {
}
