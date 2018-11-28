package it.eparlato.salestaxesproblem;

public class Receipt {

    private Purchase purchase;

    public String print() {
        StringBuilder result = new StringBuilder();



        if (purchase != null) {
            result.append(String.format("%d %s: %.2f\n", purchase.getQuantity(), purchase.getProductName(), purchase.getPrice().doubleValue()));
        }

        result.append("Sales Taxes: 0.00\n");

        if (purchase != null) {
            result.append(String.format("Total: %.2f", purchase.getPrice().doubleValue()));
        } else {
            result.append("Total: 0.00");
        }

        return result.toString();
    }

    public void add(Purchase purchase) {
        this.purchase = purchase;
    }
}
