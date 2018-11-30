package it.eparlato.salestaxesproblem;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class PrintReceiptForMultiplePurchasesTest {
    String expected;
    Receipt receipt;

    @Before
    public void setup() {
        receipt = new Receipt();
    }

    @Test
    public void receipt_for_one_unit_for_two_tax_free_products() {
        expected =
                "1 book: 5.60\n" +
                "1 chocolate bar: 2.40\n" +
                "Sales Taxes: 0.00\n" +
                "Total: 8.00";

        receipt.add(new Purchase(1, "book", new BigDecimal(5.60)));
        receipt.add(new Purchase(1, "chocolate bar", new BigDecimal(2.40)));


        assertEquals(expected, receipt.print());
    }
}
