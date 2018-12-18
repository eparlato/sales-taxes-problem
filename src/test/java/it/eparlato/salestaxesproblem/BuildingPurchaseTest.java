package it.eparlato.salestaxesproblem;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(Enclosed.class)
public class BuildingPurchaseTest {
    public static class Given_an_input_string {
        String input;
        PurchaseBuilder purchaseBuilder = new RegexPurchaseBuilder();

        @Test
        public void a_single_purchase_should_be_built_if_the_input_is_one_row() {
            input = "1 chocolate bar at 6.20";

            Purchase expected = buildPurchase(1, "chocolate bar", 6.20);

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
                            buildPurchase(3, "books", 6.70),
                            buildPurchase(4, "chocolate bar", 4.10),
                            buildPurchase(3, "box of headache pills", 20.50)
                    );


            assertEquals(expectedPurchases, purchaseBuilder.buildPurchasesFromInput(input));
        }



        private Purchase buildPurchase(int quantity, String productName, double price) {
            return new Purchase(quantity, productName, new BigDecimal(price));
        }

    }
}
