package it.eparlato.salestaxesproblem;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class BuildingPurchaseTest {
    @Test
    public void a_purchase_should_be_built_from_an_input() {
        String input = "1 chocolate bar at 6.20";

        Purchase expected = new Purchase(1, "chocolate bar", new BigDecimal(6.20));

        PurchaseBuilder purchaseBuilder = new RegexPurchaseBuilder();

        assertEquals(expected, purchaseBuilder.buildPurchasesFromInput(input).get(0));
    }

}
