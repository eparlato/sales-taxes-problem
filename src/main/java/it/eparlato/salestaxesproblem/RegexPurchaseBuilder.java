package it.eparlato.salestaxesproblem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPurchaseBuilder implements PurchaseBuilder {

    public static final BigDecimal IMPORTED_TAX_RATE = new BigDecimal(5);
    private static final BigDecimal BASE_SALES_TAX_RATE = new BigDecimal(10);

    private final List<String> taxedProducts = Arrays.asList(
            "bottle of perfume", "music CD");

    private TaxCalculator taxCalculator;

    public RegexPurchaseBuilder(TaxCalculator taxcalculator) {
        this.taxCalculator = taxcalculator;
    }

    public List<Purchase> buildPurchasesFromInput(String input) {
        List<Purchase> purchases = new ArrayList<Purchase>();

        String regex = "(\\d) (.*) at (\\d+.\\d{2})";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        int quantity;
        String productName;
        BigDecimal price;
        BigDecimal taxValue;

        while (matcher.find()) {
            quantity = Integer.valueOf(matcher.group(1));
            productName = matcher.group(2);
            price = new BigDecimal(Double.valueOf(matcher.group(3)));
            taxValue = calculateTax(quantity, productName, price);

            purchases.add(new Purchase(quantity, productName, price, taxValue));
        }

        return purchases;
    }

    private BigDecimal calculateTax(int quantity, String productName, BigDecimal price) {
        BigDecimal taxValue = new BigDecimal(0);

        if (isProductImported(productName)) {
            taxValue = taxValue.add(taxCalculator.getTaxValue(price, IMPORTED_TAX_RATE));
        }

        if (hasProductBaseSalesTaxApplied(productName)) {
            taxValue = taxValue.add(taxCalculator.getTaxValue(price, BASE_SALES_TAX_RATE));
        }

        return taxValue.multiply(new BigDecimal( quantity));
    }

    private boolean isProductImported(String productName) {
        return productName.contains("imported");
    }

    private boolean hasProductBaseSalesTaxApplied(String productName) {
        // Need to remove imported string, if present, in order to search for product name
        // in taxedProducts
        productName = productName.replace("imported", "").trim();
        return taxedProducts.contains(productName);
    }
}
