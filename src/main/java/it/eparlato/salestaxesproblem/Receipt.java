package it.eparlato.salestaxesproblem;

public class Receipt {

    private Purchase purchase;
    private StringBuilder result = new StringBuilder();

    public void add(Purchase purchase) {
        this.purchase = purchase;
    }

    public String buildAndReturn() {

        buildPurchaseRow();

        buildSalesTaxesRow();

        buildTotalRow();

        return result.toString();
    }

    private void buildSalesTaxesRow() {
        result.append("Sales Taxes: 0.00\n");
    }

    private void buildTotalRow() {
        if (purchase != null) {
            result.append(String.format("Total: %.2f", purchase.getPrice().doubleValue()));
        } else {
            result.append("Total: 0.00");
        }
    }

    private void buildPurchaseRow() {
        if (purchase != null && !purchase.isEmpty()) {
            result.append(String.format("%d %s: %.2f\n", purchase.getQuantity(), purchase.getProductName(), purchase.getPrice().doubleValue()));
        }
    }

}
