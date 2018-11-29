package it.eparlato.salestaxesproblem.acceptance;

import it.eparlato.salestaxesproblem.CashRegister;
import it.eparlato.salestaxesproblem.PurchaseBuilder;
import it.eparlato.salestaxesproblem.Receipt;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SinglePurchaseAcceptanceTest {
    PurchaseBuilder purchaseBuilder = new PurchaseBuilder();

    @Test
    @Ignore
    public void should_return_an_empty_receipt_if_the_input_is_empty() {
        String input = "";

        String expected =
                "Sales Taxes: 0.00\n" +
                "Total: 0.00";

        CashRegister cashRegister = new CashRegister(purchaseBuilder);

        Receipt receipt = cashRegister.process(input);

        assertEquals(expected, receipt.buildAndReturn());
    }

    @Test
    @Ignore
    public void should_return_a_receipt_for_one_unit_of_a_tax_free_product() {
        String input =
                "1 box of headache pills at 34.40\n";

        String expected =
                "1 box of headache pills: 34.40\n" +
                "Sales Taxes: 0.0\n" +
                "Total: 34.40";

        CashRegister cashRegister = new CashRegister(purchaseBuilder);

        Receipt receipt = cashRegister.process(input);

        assertEquals(expected, receipt.buildAndReturn());
    }
}
