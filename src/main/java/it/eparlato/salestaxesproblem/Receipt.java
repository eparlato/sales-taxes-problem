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
        BigDecimal totalTaxAmount = new BigDecimal(0);

        for (Purchase purchase : purchases) {
            totalTaxAmount = totalTaxAmount.add(purchase.getTaxValue());
        }

        receiptAsString.append(String.format("Sales Taxes: %.2f\n", totalTaxAmount.doubleValue()));
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
            String productName;


            if (purchase != null && !purchase.isEmpty()) {
                productName = purchase.getProductName();

                // TODO: refactor
                if (purchase.isProductImported()) {
                    String [] splitProductName = productName.split("imported");

                    productName = splitProductName[0] + splitProductName[1].trim();
                    productName = "imported " + productName;
                }

                receiptAsString.append(String.format("%d %s: %.2f\n",
                        purchase.getQuantity(),
                        productName,
                        purchase.getTotal()));
            }
        }
    }

}
