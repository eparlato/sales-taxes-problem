package it.eparlato.salestaxesproblem.acceptance;

import it.eparlato.salestaxesproblem.CashRegister;
import it.eparlato.salestaxesproblem.RegexPurchaseBuilder;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SinglePurchaseAcceptanceTest {
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
    public void should_return_an_empty_receipt_if_the_input_is_empty() {
        input = "";

        expected =
                "Sales Taxes: 0.00\n" +
                "Total: 0.00";

        output = cashRegister.process(input);

        assertEquals(expected, output);
    }

    @Test
    public void should_return_a_receipt_for_one_unit_of_a_tax_free_product() {
        input =
                "1 box of headache pills at 34.40\n";

        expected =
                "1 box of headache pills: 34.40\n" +
                "Sales Taxes: 0.00\n" +
                "Total: 34.40";

        output = cashRegister.process(input);

        assertEquals(expected, output);
    }

    @Test
    public void should_return_a_receipt_for_a_few_units_of_a_tax_free_product() {
        input =
                "3 books at 15.20\n";

        expected =
                "3 books: 45.60\n" +
                "Sales Taxes: 0.00\n" +
                "Total: 45.60"
                ;

        output = cashRegister.process(input);

        assertEquals(expected, output);
    }
}
