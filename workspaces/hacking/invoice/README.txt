### Running the program

1. Run "ant" in the parent directory (where this file and build.xml are located).
This will execute the target "run-test-data", which does all the test cases and run 
application with the test input data.

2. To run a specific input file, add the FULL path to the file in the property
INVOICE_FILE. Then type "ant run".

### Solving the invoice problem...

Solving the problem involved starting bottom up using Test-Driven Development:
writing the invoice file reader, design and implementation of the data wrappers
of invoice objects using Object-Oriented Analysis and Design, to a solution 
designed with reusable components in Model-View-Controller along with other
design-patterns. Additional libraries used were JUnit 4 and Google Guava.

1. Copied the input and output data from the email to the data directory to
start working on initial test cases (Test-Drive Development).

2. Started with the implementation of the TextFileReader and its unit tests
at TextFileReaderTests.java. Since there is no requirement on performance, I
thought that would be useful to implement this component using NIO APIs and
expect files that are larger than the examples. Also added 2 options of who
consumes the data loaded from the text files: buffers and listeners.

3. After being able to load an invoice data into containers/listeners, the
model was designed taking into account extensibility: a ShoppingBasket is
a "container" object aggregated with BasketItems (just to make the implementation
easier, but conceptually a shopping basket is composed of BasketItems). To
supposed extensibility, different types of BasketItems can be implemented by
implementing that interface. A BasketItems object is a representation of a
single row of the invoice. In order to support Multi-Valued state of imported,
exempt and all regular products, EnumSet was used instead of Bit Maps (usual
way to represent that) in ProjectTaxRateType. An Abstract Factory was created
to be responsible to create ShoppingBasket<BasketIntems> instances from an
invoice text representation. The last decision made was to model the price as
a Money.java class so that it is easier to decrease coupling of the 
NumberFormatter.currencyFormatter. The model Unit tests were created and
refactored several times while developing the other layers, but the basic
representation of invoice objects were verified before moving to the other
layers.

4. After having the Model ready, the concept of Services was added to provide
the real implementation of requirements and allow code reuse using singleton
(Enums as best option as Effective Java 2nd suggests). In order to speed the
process of writing the solution, the controller SalesControllerTests.java was
put in place to indirectly exercise the services created and validate the 
scenarios of viewing invoice files. In order to decouple steps, the 
InvoiceReaderService reuses the TextFileReader utility to load a 
ShoppingBasket from an invoice text file with invoice objects representation. 
To decouple the the calculations from the ShoppingBasket, the SalesCalculatorService
implements the methods to read the data from the wrapper objects. The strategy
of calculating tax values is based on the ProductTaxRateTypes. Finally, in order
decouple the visualization of the objects, the BasketItemsDecorator is the service
responsible for providing the Strategies SALE_ITEM_STRATEGY (printing the same
input input text) or RECEIPT_ITEM_STRATEGY (to print the expected output). All
the scenarios are verified by the SalesControllerTests using the files in the
"data" directory.

5. The view was the last piece designed. As the standard output was the primary
view, I have added an InvoiceView interface with the contracts of for the view.
Then, different view strategies were implemented: ConsoleViewStrategy.java and
StringBuilderViewStrategy.java, where the former prints the invoice to the
System.out and the latter to a container (StringBuilder). Tests were not added
as the SalesControllerTests exercises the needed pieces.

6. The last piece added was the InvoiceApp.java, which includes the main method
and verification of the input and potential error messages and code.