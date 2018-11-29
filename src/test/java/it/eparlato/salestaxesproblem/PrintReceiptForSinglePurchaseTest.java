package it.eparlato.salestaxesproblem;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class PrintReceiptForSinglePurchaseTest {
    @Test
    public void empty_receipt_if_no_purchases_are_provided() {
        String expected =
                "Sales Taxes: 0.00\n" +
                "Total: 0.00";

        Receipt receipt = new Receipt();

        assertEquals(expected, receipt.buildAndReturn());
    }

    @Test
    public void empty_receipt_if_an_empty_purchase_is_provided() {
        String expected =
                "Sales Taxes: 0.00\n" +
                        "Total: 0.00";

        Receipt receipt = new Receipt();

        receipt.add(new Purchase(0,"", new BigDecimal(0)));

        assertEquals(expected, receipt.buildAndReturn());
    }

    @Test
    public void receipt_for_one_unit_of_a_tax_free_product() {
        String expected =
                "1 book: 7.80\n" +
                "Sales Taxes: 0.00\n" +
                "Total: 7.80";


        Receipt receipt = new Receipt();

        receipt.add(new Purchase(1, "book", new BigDecimal(7.80)));

        assertEquals(expected, receipt.buildAndReturn());
    }
}
