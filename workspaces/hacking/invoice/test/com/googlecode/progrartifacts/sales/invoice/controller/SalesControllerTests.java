package com.googlecode.progrartifacts.sales.invoice.controller;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.google.code.progrartifacts.sales.invoice.controller.SalesController;
import com.google.code.progrartifacts.sales.invoice.view.InvoiceView;
import com.google.code.progrartifacts.sales.invoice.view.ViewStrategy;
import com.googlecode.progrartifacts.util.TextFileReaderTests.TEST_INPUT;

/**
 * The sales controller test verifies the controller tests for different 
 * views.
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public class SalesControllerTests {

    /**
     * The controller reference.
     */
    SalesController controller = SalesController.INSTANCE;

    @Test
    public void testPrintSaleInvoiceToConsoleStrategy() throws IOException {
        Map<TEST_INPUT, String> receiptStringByInputIndex = new LinkedHashMap<TEST_INPUT, String>();
        for (TEST_INPUT testInput : EnumSet.of(TEST_INPUT.INPUT_1, TEST_INPUT.INPUT_2, TEST_INPUT.INPUT_3)) {
            // get the test file.
            File invoiceFile = testInput.file();

            ViewStrategy view = ViewStrategy.BUFFER;
            // print the shopping basket to the buffer.
            controller.printSaleInvoice(invoiceFile, view);

            InvoiceView selectedView = view.getRender();

            Assert.assertNotNull("The selected view should not be null.", selectedView);
            Assert.assertNotNull("The selected view's output should not be null.", selectedView.getOutput());
            Assert.assertTrue("The buffer should have contents.", selectedView.getOutput().length() > 0);

            // add the invoice text to the index.
            receiptStringByInputIndex.put(testInput, view.getRender().getOutput());
        }
        Assert.assertTrue(receiptStringByInputIndex.size() == 3);
        // print the sales invoices per test file.
        for (TEST_INPUT inputTest : receiptStringByInputIndex.keySet()) {
            Assert.assertNotNull("The buffer must have elements.", receiptStringByInputIndex.get(inputTest));
            Assert.assertTrue("The buffer string must have contents.", receiptStringByInputIndex.get(inputTest).length() > 0);
            System.out.println(inputTest.getTitle() + ":");
            System.out.println(receiptStringByInputIndex.get(inputTest));
            System.out.println("\n");
        }
    }

    @Test
    public void testPrintReceiptInvoice() throws IOException {
        Map<TEST_INPUT, String> receiptStringByInputIndex = new LinkedHashMap<TEST_INPUT, String>();
        for (TEST_INPUT testInput : EnumSet.of(TEST_INPUT.INPUT_1, TEST_INPUT.INPUT_2, TEST_INPUT.INPUT_3)) {
            // get the test file.
            File invoiceFile = testInput.file();

            ViewStrategy view = ViewStrategy.BUFFER;
            // print the shopping basket to the buffer.
            controller.printReceiptInvoice(invoiceFile, view);

            InvoiceView selectedView = view.getRender();

            Assert.assertNotNull("The selected view should not be null.", selectedView);
            Assert.assertNotNull("The selected view's output should not be null.", selectedView.getOutput());
            Assert.assertTrue("The buffer should have contents.", selectedView.getOutput().length() > 0);

            // add the invoice text to the index.
            receiptStringByInputIndex.put(testInput, view.getRender().getOutput());
        }
        // print the sales invoices per test file.
        for (TEST_INPUT inputTest : receiptStringByInputIndex.keySet()) {
            Assert.assertNotNull("The buffer must have elements.", receiptStringByInputIndex.get(inputTest));
            Assert.assertTrue("The buffer string must have contents.", receiptStringByInputIndex.get(inputTest).length() > 0);
            System.out.println(inputTest.getTitle() + ":");
            System.out.println(receiptStringByInputIndex.get(inputTest));
            System.out.println("\n");
        }
    }

}
