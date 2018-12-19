package it.eparlato.salestaxesproblem;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class CalculatingTaxValuesTest {

    public static class The_tax_value {
        BigDecimal price;
        BigDecimal taxRate;
        TaxCalculator taxCalculator = new TaxCalculatorImpl();

        @Test
        public void should_be_zero_if_tax_rate_is_zero() {
            price = new BigDecimal(13.00);
            taxRate = new BigDecimal(0);

            assertEquals(0.0, taxCalculator.getTaxValue(price, taxRate).doubleValue(), 0);
        }

        @Test
        public void should_be_1_50_if_price_is_14_99_and_tax_rate_is_10() {
            price = new BigDecimal(14.99);
            taxRate = new BigDecimal(10);

            assertEquals(1.50, taxCalculator.getTaxValue(price, taxRate).doubleValue(), 0);
        }

        @Test
        public void should_be_0_50_if_price_is_10_and_tax_rate_is_5() {
            price = new BigDecimal(10.00);
            taxRate = new BigDecimal( 5);

            assertEquals(0.50, taxCalculator.getTaxValue(price, taxRate).doubleValue(), 0);
        }

        @Test
        public void should_be_7_15_if_price_is_47_50_and_tax_rate_is_15() {
            price = new BigDecimal(47.50);
            taxRate = new BigDecimal( 15);

            assertEquals(7.15, taxCalculator.getTaxValue(price, taxRate).doubleValue(), 0);
        }
    }
}
