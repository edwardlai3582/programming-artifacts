package com.googlecode.progrartifacts.sales.invoice.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.junit.Test;

import com.google.code.progrartifacts.util.TextFileReader;
import com.googlecode.progrartifacts.util.TextFileReaderTests.TEST_INPUT;

public class ShoppingBasketTests {

    @Test
    public void testTotalSalesTaxForItemsFromInputs() throws IOException {
        List<String> receiptItems = new ArrayList<String>();
        for (TEST_INPUT testInput : EnumSet.of(TEST_INPUT.INPUT_1, TEST_INPUT.INPUT_2, TEST_INPUT.INPUT_3)) {
            // get the file associated with the test input
            File fileInput = testInput.file();

            // read the receipt items to the container/bag
            TextFileReader.INSTANCE.from(fileInput).to(receiptItems).read();

            receiptItems.clear();
        }
    }
}
