package it.eparlato.salestaxesproblem;

public class Receipt {

    private Purchase purchase;
    private StringBuilder receiptAsString = new StringBuilder();

    public void add(Purchase purchase) {
        this.purchase = purchase;
    }

    public Receipt build() {

        buildPurchaseRow();

        buildSalesTaxesRow();

        buildTotalRow();

        return this;
    }

    public String getAsString() {
        return receiptAsString.toString();
    }

    private void buildSalesTaxesRow() {
        receiptAsString.append("Sales Taxes: 0.00\n");
    }

    private void buildTotalRow() {
        if (purchase != null) {
            receiptAsString.append(String.format("Total: %.2f", purchase.getTotal().doubleValue()));
        } else {
            receiptAsString.append("Total: 0.00");
        }
    }

    private void buildPurchaseRow() {
        if (purchase != null && !purchase.isEmpty()) {
            receiptAsString.append(String.format("%d %s: %.2f\n",
                    purchase.getQuantity(),
                    purchase.getProductName(),
                    purchase.getTotal().doubleValue()));
        }
    }

}
