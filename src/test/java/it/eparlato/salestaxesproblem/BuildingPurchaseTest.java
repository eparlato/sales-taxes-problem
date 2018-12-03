package it.eparlato.salestaxesproblem;

import org.junit.Test;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class BuildingPurchaseTest {
    @Test
    public void a_purchase_should_be_built_from_an_input() {
        String input = "1 chocolate bar at 6.20";

        Purchase expected = new Purchase(1, "chocolate bar", new BigDecimal(6.20));

        PurchaseBuilder purchaseBuilder = new RegexPurchaseBuilder();

        assertEquals(expected, purchaseBuilder.buildPurchasesFromInput(input).get(0));
    }

    @Test
    public void a_list_of_purchased_should_be_built_from_an_input() {
        String input =
                "3 books at 6.70\n" +
                "4 chocolate bar at 4.10\n" +
                "3 box of headache pills at 20.50";

        List<Purchase> expectedPurchases =
                Arrays.asList(
                        new Purchase(3, "books", new BigDecimal(6.70)),
                        new Purchase(4, "chocolate bar", new BigDecimal(4.10)),
                        new Purchase(3, "box of headache pills", new BigDecimal(20.50))
                        );

        PurchaseBuilder purchaseBuilder = new RegexPurchaseBuilder();

        assertEquals(expectedPurchases, purchaseBuilder.buildPurchasesFromInput(input));
    }
}
