package it.eparlato.salestaxesproblem;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculatorImpl implements TaxCalculator {
    @Override
    public BigDecimal getTaxValue(BigDecimal price, BigDecimal taxRate) {
        if (taxRate.intValue() == 0) {
            return new BigDecimal(0.0);
        }

        BigDecimal taxValue = price.multiply(taxRate).divide(new BigDecimal(100));

        return taxValue.divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP)
                .multiply(BigDecimal.valueOf(0.05));
    }
}
