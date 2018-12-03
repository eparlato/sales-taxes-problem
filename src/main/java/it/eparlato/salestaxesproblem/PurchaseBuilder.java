package it.eparlato.salestaxesproblem;

import java.util.List;

public interface PurchaseBuilder {
    Purchase buildFromInput(String input);

    List<Purchase> buildPurchasesFromInput(String input);
}
