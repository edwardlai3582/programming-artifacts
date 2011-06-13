package com.google.code.progrartifacts.sales;

import java.io.File;
import java.io.IOException;

import com.google.code.progrartifacts.sales.invoice.controller.SalesController;
import com.google.code.progrartifacts.sales.invoice.view.ViewStrategy;

/**
 * 
 * This app is used to open invoice files like the following:
 * <pre>
 * ----------------------------------------
 * 1 imported bottle of perfume at 27.99
 * 1 bottle of perfume at 18.99
 * 1 packet of headache pills at 9.75
 * 1 box of imported chocolates at 11.25
 * ----------------------------------------
 * </pre>
 * 
 * The output is a formatted invoice like the following:
 * <pre>
 * ----------------------------------------
 * 1 imported bottle of perfume: 32.19
 * 1 bottle of perfume: 20.89
 * 1 packet of headache pills: 9.75
 * 1 box of imported chocolates: 11.85
 * Sales Taxes: 6.70
 * Total: 74.68
 * ----------------------------------------
 * </pre>
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public class InvoiceApp {

    /**
     * Errors related to the view to be used by documentation.
     * 
     * @author Marcello de Sales (marcello.desales@gmail.com)
     *
     */
    private static enum Error {

        FILE_NOT_PROVIDED(0xC01D, "The path to the invoice file must be provided."),

        PATH_NOT_FILE(0x1EAF, "The path provided '%s' must be a file."),

        FILE_IS_EMPTY(0xDEADC0DE, "The invoice file provided '%s' must contain entries."),

        WHILE_READING_FILE(0xDEADFA11, "An error occurred while reading the invoice file provided '%s': ");

        /**
         * The code of the error as integer.
         */
        private int code;
        /**
         * The description of the error.
         */
        private String description;

        private Error(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String toString() {
            return "Error " + this.code + ": " + this.description;
        }
    }

    /**
     * Prints the usage of this program.
     */
    private static void printUsage() {
        System.err.println();
        System.err.println("Usage: " + InvoiceApp.class.getCanonicalName() + " INVOICE_FILE_PATH");
        System.err.println(" INVOICE_FILE_PATH = The path to the invoice file.");
    }

    /**
     * Verifies if the given arguments contain the path to the invoice file.
     * If any validation fails, the program will exit with the related error message.
     * 
     * @param args the arguments to be verified.
     */
    public static File verifyArgumentsForInvoice(String ... args) {
        if (args.length == 0) {
            Error error = Error.FILE_NOT_PROVIDED;
            System.err.println(error);
            printUsage();
            System.exit(error.code);
        }
        File invoiceFile = new File(args[0]);
        if (!invoiceFile.isFile()) {
            Error error = Error.PATH_NOT_FILE;
            System.err.format(error.toString(), error.code);
            printUsage();
            System.exit(error.code);
        }
        if (invoiceFile.length() == 0) {
            Error error = Error.FILE_IS_EMPTY;
            System.err.format(error.toString(), error.code);
            printUsage();
            System.exit(error.code);
        }
        return invoiceFile;
    }

    /**
     * Console view that expects 
     * @param args
     */
    public static void main(String[] args) {
        // get the invoice file from the argument.
        File invoiceFile = verifyArgumentsForInvoice(args);

        // get the controller if the file is valid.
        SalesController controller = SalesController.INSTANCE;

        // select the view to print.
        ViewStrategy view = ViewStrategy.CONSOLE;

        // print the receipt invoice.
        try {
            controller.printReceiptInvoice(invoiceFile, view);

        } catch (IOException errorWhileReading) {
            Error error = Error.WHILE_READING_FILE;
            System.err.println(error);
            System.exit(error.code);
        }
    }
}
