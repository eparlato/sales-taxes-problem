package it.eparlato.salestaxesproblem;

import java.math.BigDecimal;

public interface TaxCalculator {
    BigDecimal getTaxValue(BigDecimal price, BigDecimal taxRate);
}
