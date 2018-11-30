package it.eparlato.salestaxesproblem;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class PrintReceiptForSinglePurchaseTest {
    String expected;
    Receipt receipt;

    @Before
    public void setup() {
        receipt = new Receipt();
    }

    @Test
    public void empty_receipt_if_no_purchases_are_provided() {
        expected =
                "Sales Taxes: 0.00\n" +
                "Total: 0.00";

        assertEquals(expected, receipt.print());
    }

    @Test
    public void empty_receipt_if_an_empty_purchase_is_provided() {
        expected =
                "Sales Taxes: 0.00\n" +
                "Total: 0.00";

        receipt.add(new Purchase(0,"", new BigDecimal(0)));

        assertEquals(expected, receipt.print());
    }

    @Test
    public void receipt_for_one_unit_of_a_tax_free_product() {
        expected =
                "1 book: 7.80\n" +
                "Sales Taxes: 0.00\n" +
                "Total: 7.80";

        receipt.add(new Purchase(1, "book", new BigDecimal(7.80)));

        assertEquals(expected, receipt.print());
    }

    @Test
    public void receipt_for_few_units_of_a_tax_free_product() {
        expected =
                "3 chocolates bar: 9.90\n" +
                "Sales Taxes: 0.00\n" +
                "Total: 9.90";

        receipt.add(new Purchase(3, "chocolates bar", new BigDecimal(3.30)));

        assertEquals(expected, receipt.print());
    }

}
