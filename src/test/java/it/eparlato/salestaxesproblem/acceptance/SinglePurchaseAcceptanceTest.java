package it.eparlato.salestaxesproblem.acceptance;

import it.eparlato.salestaxesproblem.CashRegister;
import it.eparlato.salestaxesproblem.Receipt;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SinglePurchaseAcceptanceTest {
    @Test
    public void should_return_an_empty_receipt_whatever_the_input_is() {
        String input = "whatever";

        String expected =
                "Sales Taxes: 0.00\n" +
                "Total: 0.00";

        CashRegister cashRegister = new CashRegister();

        Receipt receipt = cashRegister.process(input);

        assertEquals(expected, receipt.buildAndReturn());
    }
}
