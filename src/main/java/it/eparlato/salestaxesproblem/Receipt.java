package it.eparlato.salestaxesproblem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private StringBuilder receiptAsString = new StringBuilder();
    private List<Purchase> purchases = new ArrayList<Purchase>();

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

            if (purchase.isNotEmpty()) {
                receiptAsString.append(String.format("%d %s: %.2f\n",
                        purchase.getQuantity(),
                        buildProductName(purchase),
                        purchase.getTotal()));
            }
        }
    }

    private String buildProductName(Purchase purchase) {

        if (purchase.isProductImported()) {
            return importedProductName(purchase.getProductName());
        }

        return purchase.getProductName();
    }

    private String importedProductName(String productName) {
        StringBuilder importedProductName = new StringBuilder();

        String [] productNamePartsWithoutImportedString = productName.split("imported");

        importedProductName.append("imported ");
        importedProductName.append(productNamePartsWithoutImportedString[0]).append(productNamePartsWithoutImportedString[1].trim());

        return importedProductName.toString();
    }

}
