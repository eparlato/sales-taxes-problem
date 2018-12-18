package it.eparlato.salestaxesproblem;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

@RunWith(Enclosed.class)
public class PrintingReceiptForMultiplePurchasesTest {

    public static class A_receipt_for_multiple_purchases_should_print {

        String expected;
        Receipt receipt;

        @Before
        public void setup() {
            receipt = new Receipt();
        }

        @Test
        public void two_product_rows_with_sales_taxes_row_and_total_row_for_a_purchase_of_two_products() {
            expected =
                    "1 book: 5.60\n" +
                    "1 chocolate bar: 2.40\n" +
                    "Sales Taxes: 0.00\n" +
                    "Total: 8.00";

            receipt.add(buildPurchaseWithoutTaxes(1, "book", 5.60));
            receipt.add(buildPurchaseWithoutTaxes(1, "chocolate bar", 2.40));

            assertEquals(expected, receipt.print());
        }

        private static Purchase buildPurchaseWithoutTaxes(int quantity, String productName, double price) {
            return new Purchase(quantity, productName, new BigDecimal(price), new BigDecimal(0.00));
        }

    }

}
