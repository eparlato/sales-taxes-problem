package it.eparlato.salestaxesproblem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private StringBuilder receiptAsString = new StringBuilder();
    private List<Purchase> purchases = new ArrayList<>();

    public void add(Purchase purchase) {
        purchases.add(purchase);
    }

    public String print() {

        addPurchaseRow();

        addSalesTaxesRow();
        addTotalRow();

        return receiptAsString.toString();
    }

    private void addSalesTaxesRow() {
        receiptAsString.append("Sales Taxes: 0.00\n");
    }

    private void addTotalRow() {
        BigDecimal total = new BigDecimal(0);

        for(Purchase purchase : purchases) {
            total = total.add(purchase.getTotal());
        }

        receiptAsString.append(String.format("Total: %.2f", total.doubleValue()));
    }

    private void addPurchaseRow() {

        for (Purchase purchase : purchases) {
            if (purchase != null && !purchase.isEmpty()) {
                receiptAsString.append(String.format("%d %s: %.2f\n",
                        purchase.getQuantity(),
                        purchase.getProductName(),
                        purchase.getTotal().doubleValue()));
            }

        }

    }

}
