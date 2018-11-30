package it.eparlato.salestaxesproblem;

public class CashRegister {
    private PurchaseBuilder purchaseBuilder;

    public CashRegister(PurchaseBuilder purchaseBuilder) {
        this.purchaseBuilder = purchaseBuilder;
    }

    public String process(String input) {

        Purchase purchase = purchaseBuilder.buildFromInput(input);

        Receipt receipt = new Receipt();

        if (purchase != null) {
            receipt.add(purchase);
        }


        return receipt.print();
    }
}
