package it.eparlato.salestaxesproblem.acceptance;

import it.eparlato.salestaxesproblem.CashRegister;
import it.eparlato.salestaxesproblem.RegexPurchaseBuilder;
import it.eparlato.salestaxesproblem.TaxCalculatorImpl;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SinglePurchaseAcceptanceTest {
    String input;
    String output;
    String expected;

    RegexPurchaseBuilder purchaseBuilder;
    CashRegister cashRegister;

    @Before
    public void setup() {
        purchaseBuilder = new RegexPurchaseBuilder(new TaxCalculatorImpl());
        cashRegister = new CashRegister(purchaseBuilder);
    }

    @Test
    public void a_receipt_for_an_empty_input() {
        input = "";

        expected =
                "Sales Taxes: 0.00\n" +
                "Total: 0.00";

        output = cashRegister.process(input);

        assertEquals(expected, output);
    }

    @Test
    public void a_receipt_for_one_unit_of_a_tax_free_product() {
        input = "1 box of headache pills at 34.40\n";

        expected =
                "1 box of headache pills: 34.40\n" +
                "Sales Taxes: 0.00\n" +
                "Total: 34.40";

        output = cashRegister.process(input);

        assertEquals(expected, output);
    }

    @Test
    public void a_receipt_for_a_few_units_of_a_tax_free_product() {
        input = "3 books at 15.20\n";

        expected =
                "3 books: 45.60\n" +
                "Sales Taxes: 0.00\n" +
                "Total: 45.60";

        output = cashRegister.process(input);

        assertEquals(expected, output);
    }

    @Test
    public void a_receipt_for_one_unit_of_a_base_taxed_product() {
        input = "1 music CD at 14.99";

        expected =
                "1 music CD: 16.49\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 16.49";

        output = cashRegister.process(input);

        assertEquals(expected, output);
    }

    @Test
    public void a_receipt_for_multiple_units_of_a_base_tax_product() {
        input = "3 music CD at 14.99";

        expected =
                "3 music CD: 49.47\n" +
                        "Sales Taxes: 4.50\n" +
                        "Total: 49.47";

        output = cashRegister.process(input);

        assertEquals(expected, output);
    }
}
