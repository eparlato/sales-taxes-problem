package it.eparlato.salestaxesproblem;

import java.util.List;

public class CashRegister {
    private PurchaseBuilder purchaseBuilder;

    public CashRegister(PurchaseBuilder purchaseBuilder) {
        this.purchaseBuilder = purchaseBuilder;
    }

    public String process(String input) {

        List<Purchase> purchases = purchaseBuilder.buildPurchasesFromInput(input);

        Receipt receipt = new Receipt();

        for (Purchase purchase : purchases) {
            receipt.add(purchase);
        }

        return receipt.print();
    }
}
