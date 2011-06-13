package com.googlecode.progrartifacts.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

import com.google.code.progrartifacts.util.TextFileReader;
import com.google.code.progrartifacts.util.TextLineListener;

public class TextFileReaderTests {

    private static File dataDir;
    static {
        try {
            dataDir = new File(new File(".").getCanonicalFile(), "data");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static enum TEST_INPUT {
        INPUT_1, INPUT_2, INPUT_3, EMPTY, NON_EXISTENT;

        public String toString() {
            switch (this) {
            case INPUT_1: return "input1";
            case INPUT_2: return "input2";
            case INPUT_3: return "input3";
            case EMPTY: return "empty";
            case NON_EXISTENT: return "XYZ";
            default: return null;
            }
        };

        public String getTitle() {
            switch (this) {
            case INPUT_1: return "Input 1";
            case INPUT_2: return "Input 2";
            case INPUT_3: return "Input 3";
            case EMPTY: return "empty";
            case NON_EXISTENT: return "XYZ";
            default: return null;
            }
        };

        /**
         * @return the Test file object.
         */
        public File file() {
            return new File(dataDir, this.toString());
        }

        /**
         * The sales cost per item for each of the test cases.
         */
        private static final float[][] saleCostPerItem = new float[][]{
            {12.49f, 16.49f, 0.85f}, 
            {10.50f, 54.65f}, 
            {32.19f, 20.89f, 9.75f}
        };

        /**
         * The total sale taxes for the given test.
         */
        private static final float[] totalSaleTax = new float[]{1.50f, 7.65f, 6.70f};

        /**
         * The total sale cost for each of the test cases.
         */
        private static final float[] totalSaleCost = new float[]{29.83f, 65.15f, 74.68f};

        /**
         * @return The sales cost per item for a given test case.
         */
        public float[] getSalesCostPerItem() {
            switch (this) {
            case INPUT_1: return saleCostPerItem[0];
            case INPUT_2: return saleCostPerItem[1];
            case INPUT_3: return saleCostPerItem[2];
            default: return null;
            }
        }

        public float getTotalSaleTaxes() {
            switch (this) {
            case INPUT_1: return totalSaleTax[0];
            case INPUT_2: return totalSaleTax[1];
            case INPUT_3: return totalSaleTax[2];
            default: return 0;
            }
        }

        public float getTotalSaleCost() {
            switch (this) {
            case INPUT_1: return totalSaleCost[0];
            case INPUT_2: return totalSaleCost[1];
            case INPUT_3: return totalSaleCost[2];
            default: return 0;
            }
        }
    }

    @Test
    public void readRegularInput() throws IOException {
        // use a blocking queue to see the contents...
        final BlockingQueue<String> lines = new LinkedBlockingQueue<String>();
        File input = TEST_INPUT.INPUT_1.file();
        TextFileReader.INSTANCE.from(input).to(new TextLineListener() {

            @Override
            public void receiveTextLine(String line) {
                lines.add(line);
            }

        }).read();

        // just verify the 3 existing entries
        int numberItems = 3;
        while (numberItems > 0) {
            try {
                String line = lines.take();
                switch (numberItems) {
                case 3:
                    assertEquals("1 book at 12.49", line);
                    break;
                case 2:
                    assertEquals("1 music CD at 14.99", line);
                    break;
                case 1:
                    assertEquals("1 chocolate bar at 0.85", line);
                    break;
                }
                numberItems--;

            } catch (InterruptedException e) {
            }
        }
        assertTrue("There should be not more than 3 elements in the input1 file", lines.size() == 0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void tryOppeningNullFile() {
        File input = null;
        TextFileReader.INSTANCE.from(input);
    }

    @Test(expected=IllegalArgumentException.class)
    public void tryOppeningAnEmptyFile() {
        File input = TEST_INPUT.EMPTY.file();
        TextFileReader.INSTANCE.from(input);
    }

    @Test(expected=IllegalArgumentException.class)
    public void tryOppeningANonExistingFile() {
        File input = TEST_INPUT.NON_EXISTENT.file();
        TextFileReader.INSTANCE.from(input);
    }

    @Test(expected=IllegalArgumentException.class)
    public void tryOppeningExistingFileButWithoutListener() {
        File input = TEST_INPUT.INPUT_1.file();
        Set<String> container = null;
        TextFileReader.INSTANCE.from(input).to(container);
    }

    @Test(expected=IllegalStateException.class)
    public void tryOppeningFileWithoutProvidingAFile() {
        try {
            TextFileReader.INSTANCE.read();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            TextFileReader.INSTANCE.to(new TextLineListener() {

                @Override
                public void receiveTextLine(String line) {
                    // don't do anything.
                }
            }).read();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
