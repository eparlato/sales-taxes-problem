package it.eparlato.salestaxesproblem.acceptance;

import it.eparlato.salestaxesproblem.CashRegister;
import it.eparlato.salestaxesproblem.RegexPurchaseBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MultiplePurchaseAcceptanceTest {
    String input;
    String output;
    String expected;

    RegexPurchaseBuilder purchaseBuilder = new RegexPurchaseBuilder();
    CashRegister cashRegister;

    @Before
    public void setup() {
        purchaseBuilder = new RegexPurchaseBuilder();
        cashRegister = new CashRegister(purchaseBuilder);
    }

    @Test
    public void a_receipt_for_a_few_purchases_of_one_unit_of_tax_free_goods() {
        input =
                "1 chocolate bar at 0.85\n" +
                "1 book at 10.25";

        expected =
                "1 chocolate bar: 0.85\n" +
                "1 book: 10.25\n" +
                "Sales Taxes: 0.00\n" +
                "Total: 11.10"
        ;

        output = cashRegister.process(input);

        assertEquals(expected, output);
    }
}
