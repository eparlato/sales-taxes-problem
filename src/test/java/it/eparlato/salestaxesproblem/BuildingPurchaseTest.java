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
        final BigDecimal NO_TAX_APPLIED = new BigDecimal(0);

        String input;
        PurchaseBuilder purchaseBuilder;

        @Before
        public void init() {
            purchaseBuilder = new RegexPurchaseBuilder(new TaxCalculatorImpl());
        }
    }

    public static class Given_an_input_string extends CommonSetup {

        @Test
        public void a_single_purchase_should_be_built_if_the_input_is_one_row() {
            input = "1 chocolate bar at 6.20";

            Purchase expected = new Purchase(1, "chocolate bar", new BigDecimal(6.20), NO_TAX_APPLIED);

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
                            new Purchase(3, "books", new BigDecimal(6.70), NO_TAX_APPLIED),
                            new Purchase(4, "chocolate bar", new BigDecimal(4.10), NO_TAX_APPLIED),
                            new Purchase(3, "box of headache pills", new BigDecimal(20.50), NO_TAX_APPLIED)
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
        public void a_single_unit_purchase_with_a_10_percent_tax_should_be_built_if_product_is_base_taxed() {
            input = "1 bottle of perfume at 25.00";

            Purchase purchase = purchaseBuilder.buildPurchasesFromInput(input).get(0);

            assertEquals(2.50, purchase.getTaxValue().doubleValue());
        }

        @Test
        public void a_single_unit_purchase_with_a_15_percent_tax_should_be_built_if_product_is_imported_and_base_taxed() {
            input = "1 imported bottle of perfume at 27.99";

            Purchase purchase = purchaseBuilder.buildPurchasesFromInput(input).get(0);

            assertEquals(4.20, purchase.getTaxValue().doubleValue());
        }

        @Test
        public void a_multiple_units_purchase_with_a_10_percent_tax_should_be_built_if_product_is_base_taxed() {
            input = "3 bottle of perfume at 21.00";

            Purchase purchase = purchaseBuilder.buildPurchasesFromInput(input).get(0);

            assertEquals(6.30, purchase.getTaxValue().doubleValue());
        }

        @Test
        public void a_multiple_units_purchase_with_a_5_percent_tax_should_be_built_if_product_is_imported() {
            input = "4 imported box of chocolates at 10.00";

            Purchase purchase = purchaseBuilder.buildPurchasesFromInput(input).get(0);

            assertEquals(2.00, purchase.getTaxValue().doubleValue());
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
