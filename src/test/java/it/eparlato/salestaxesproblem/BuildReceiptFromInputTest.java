package it.eparlato.salestaxesproblem;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class BuildReceiptFromInputTest {

    @Test
    public void a_receipt_should_be_built_if_an_input_is_provided() {
        String input = "";

        CashRegister cashRegister = new CashRegister();

        Receipt receipt = cashRegister.process(input);

        assertNotNull(receipt);
    }
}
