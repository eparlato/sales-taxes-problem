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

        addPurchasesToReceipt(purchases, receipt);

        return receipt.print();
    }

    private void addPurchasesToReceipt(List<Purchase> purchases, Receipt receipt) {
        for (Purchase purchase : purchases) {
            receipt.add(purchase);
        }
    }
}
