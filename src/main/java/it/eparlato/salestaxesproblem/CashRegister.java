package it.eparlato.salestaxesproblem;

import java.util.List;

public class CashRegister {
    private PurchaseBuilder purchaseBuilder;

    public CashRegister(PurchaseBuilder purchaseBuilder) {
        this.purchaseBuilder = purchaseBuilder;
    }

    public String process(String input) {

        Purchase purchase = purchaseBuilder.buildFromInput(input);
        List<Purchase> purchases = purchaseBuilder.buildPurchasesFromInput(input);

        Receipt receipt = new Receipt();

        /*
        if (purchase != null) {
            receipt.add(purchase);
        }
        */
        for (Purchase purchase2 : purchases) {
            receipt.add(purchase2);
        }



        return receipt.print();
    }
}
