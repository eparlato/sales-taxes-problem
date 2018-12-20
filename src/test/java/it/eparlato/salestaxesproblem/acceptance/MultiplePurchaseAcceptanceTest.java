package it.eparlato.salestaxesproblem.acceptance;

import it.eparlato.salestaxesproblem.CashRegister;
import it.eparlato.salestaxesproblem.RegexPurchaseBuilder;
import it.eparlato.salestaxesproblem.TaxCalculatorImpl;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MultiplePurchaseAcceptanceTest {
    String input = "input";
    String output = "output";
    String expected = "expected";

    RegexPurchaseBuilder purchaseBuilder;
    CashRegister cashRegister;

    @Before
    public void setup() {
        purchaseBuilder = new RegexPurchaseBuilder(new TaxCalculatorImpl());
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

    @Test
    public void a_receipt_for_a_few_purchases_of_a_few_units_of_base_taxed_products() {
        input =
                "1 bottle of perfume at 18.99\n" +
                "1 music CD at 14.99\n";

        expected =
                "1 bottle of perfume: 20.89\n" +
                "1 music CD: 16.49\n" +
                "Sales Taxes: 3.40\n" +
                "Total: 37.38";

        output = cashRegister.process(input);

        assertEquals(expected, output);
    }

    @Test
    public void shopping_basket_1() {
        input =
                "2 book at 12.49\n" +
                "1 music CD at 14.99\n" +
                "1 chocolate bar at 0.85";

        expected =
                "2 book: 24.98\n" +
                "1 music CD: 16.49\n" +
                "1 chocolate bar: 0.85\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 42.32";

        output = cashRegister.process(input);

        assertEquals(expected, output);
    }

    @Test
    public void shopping_basket_2() {
        input =
                "1 imported box of chocolates at 10.00\n" +
                "1 imported bottle of perfume at 47.50";

        expected =
                "1 imported box of chocolates: 10.50\n" +
                "1 imported bottle of perfume: 54.65\n" +
                "Sales Taxes: 7.65\n" +
                "Total: 65.15";

        output = cashRegister.process(input);

        assertEquals(expected, output);
    }

}
