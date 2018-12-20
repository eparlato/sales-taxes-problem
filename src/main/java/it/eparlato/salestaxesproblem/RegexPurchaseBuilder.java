package it.eparlato.salestaxesproblem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPurchaseBuilder implements PurchaseBuilder {

    private final List<String> taxedProducts = Arrays.asList(
            "bottle of perfume", "music CD");
    private TaxCalculator taxCalculator;

    public RegexPurchaseBuilder(TaxCalculator taxcalculator) {
        this.taxCalculator = taxcalculator;
    }

    @Override
    public List<Purchase> buildPurchasesFromInput(String input) {
        List<Purchase> purchases = new ArrayList<>();

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

        if (productName.contains("imported")) {
            productName = productName.replace("imported", "").trim();

            taxValue = taxValue.add(taxCalculator.getTaxValue(price, new BigDecimal(5)));
        }

        if (taxedProducts.contains(productName)) {
            taxValue = taxValue.add(taxCalculator.getTaxValue(price, new BigDecimal(10)));

        }


        return taxValue.multiply(new BigDecimal( quantity));

    }
}
