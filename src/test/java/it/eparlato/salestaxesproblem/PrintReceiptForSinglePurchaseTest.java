package it.eparlato.salestaxesproblem;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PrintReceiptForSinglePurchaseTest {
    @Test
    public void empty_receipt_if_no_purchases_are_provided() {
        String expected =
                "Sales Taxes: 0.00\n" +
                "Total: 0.00";

        Receipt receipt = new Receipt();

        assertEquals(expected, receipt.print());
    }
}
