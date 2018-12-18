package it.eparlato.salestaxesproblem;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

@RunWith(Enclosed.class)
public class PrintingReceiptForSinglePurchaseTest {

    public static class A_receipt_for_a_single_purchase_should_print {
        String expected;
        Receipt receipt;

        @Before
        public void setup() {
            receipt = new Receipt();
        }

        @Test
        public void only_sales_taxes_row_and_total_row_if_no_purchases_are_provided() {
            expected =
                    "Sales Taxes: 0.00\n" +
                    "Total: 0.00";

            assertEquals(expected, receipt.print());
        }

        @Test
        public void only_sales_taxes_row_and_total_row_if_an_empty_purchase_is_provided() {
            expected =
                    "Sales Taxes: 0.00\n" +
                    "Total: 0.00";

            receipt.add(new Purchase(0,"", new BigDecimal(0), new BigDecimal(0.00)));

            assertEquals(expected, receipt.print());
        }

        @Test
        public void one_product_row_with_sales_taxes_row_and_total_row_for_a_single_unit_purchase() {
            expected =
                    "1 book: 7.80\n" +
                    "Sales Taxes: 0.00\n" +
                    "Total: 7.80";

            receipt.add(new Purchase(1, "book", new BigDecimal(7.80), new BigDecimal(0.00)));

            assertEquals(expected, receipt.print());
        }

        @Test
        public void one_product_row_with_sales_taxes_row_and_total_row_for_a_multiple_units_purchase() {
            expected =
                    "3 chocolates bar: 9.90\n" +
                    "Sales Taxes: 0.00\n" +
                    "Total: 9.90";

            receipt.add(new Purchase(3, "chocolates bar", new BigDecimal(3.30), new BigDecimal(0.00)));

            assertEquals(expected, receipt.print());
        }


    }
}
