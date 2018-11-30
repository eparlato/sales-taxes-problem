package it.eparlato.salestaxesproblem;

import java.math.BigDecimal;

public class Receipt {

    private Purchase purchase;
    private StringBuilder receiptAsString = new StringBuilder();

    public void add(Purchase purchase) {
        this.purchase = purchase;
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

        if (purchase != null) {
            total = purchase.getTotal();
        }

        receiptAsString.append(String.format("Total: %.2f", total.doubleValue()));
    }

    private void addPurchaseRow() {
        if (purchase != null && !purchase.isEmpty()) {
            receiptAsString.append(String.format("%d %s: %.2f\n",
                    purchase.getQuantity(),
                    purchase.getProductName(),
                    purchase.getTotal().doubleValue()));
        }
    }

}
