package it.eparlato.salestaxesproblem;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(Enclosed.class)
public class BuildingPurchaseTest {
    public static abstract class CommonSetup {
        String input;
        PurchaseBuilder purchaseBuilder;

        @Before
        public void init() {
            purchaseBuilder = new RegexPurchaseBuilder();
        }
    }

    public static class Given_an_input_string extends CommonSetup {

        @Test
        public void a_single_purchase_should_be_built_if_the_input_is_one_row() {
            input = "1 chocolate bar at 6.20";

            Purchase expected = buildPurchaseWithoutTaxes(1, "chocolate bar", 6.20);

            assertEquals(expected, purchaseBuilder.buildPurchasesFromInput(input).get(0));
        }

        @Test
        public void a_list_of_purchases_should_be_built_if_the_input_is_multiple_rows() {
            input =
                    "3 books at 6.70\n" +
                    "4 chocolate bar at 4.10\n" +
                    "3 box of headache pills at 20.50";

            List<Purchase> expectedPurchases =
                    Arrays.asList(
                            buildPurchaseWithoutTaxes(3, "books", 6.70),
                            buildPurchaseWithoutTaxes(4, "chocolate bar", 4.10),
                            buildPurchaseWithoutTaxes(3, "box of headache pills", 20.50)
                    );


            assertEquals(expectedPurchases, purchaseBuilder.buildPurchasesFromInput(input));
        }

        @Test
        public void a_purchase_with_zero_tax_should_be_built_if_product_is_tax_free() {
            input = "1 chocolate bar at 4.50";

            Purchase purchase = purchaseBuilder.buildPurchasesFromInput(input).get(0);

            assertEquals(new BigDecimal(0.00), purchase.getTaxValue());
        }

        @Test
        @Ignore
        public void a_purchase_with_a_tax_should_be_built_if_product_is_taxed() {
            input = "1 bottle of perfume at 25.00";

            Purchase purchase = purchaseBuilder.buildPurchasesFromInput(input).get(0);

            assertEquals(new BigDecimal(2.50), purchase.getTaxValue());
        }

        private static Purchase buildPurchaseWithoutTaxes(int quantity, String productName, double price) {
            return new Purchase(quantity, productName, new BigDecimal(price), new BigDecimal(0.00));
        }

    }

    public static class Given_a_product_name extends CommonSetup {
        @Test
        public void if_there_are_no_spaces_a_purchase_with_that_name_should_be_built() {
            input = "1 book at 5.60";

            Purchase purchase = purchaseBuilder.buildPurchasesFromInput(input).get(0);

            assertEquals("book", purchase.getProductName());
        }

        @Test
        public void if_there_are_spaces_a_purchase_with_that_name_should_be_print() {
            input = "1 music CD at 15.30";

            Purchase purchase = purchaseBuilder.buildPurchasesFromInput(input).get(0);

            assertEquals("music CD", purchase.getProductName());
        }

    }

}
