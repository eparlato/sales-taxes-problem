package it.eparlato.salestaxesproblem;

import java.util.List;

public interface PurchaseBuilder {
    List<Purchase> buildPurchasesFromInput(String input);
}
