package it.eparlato.salestaxesproblem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPurchaseBuilder implements PurchaseBuilder {

    @Override
    public List<Purchase> buildPurchasesFromInput(String input) {
        List<Purchase> purchases = new ArrayList<>();

        String regex = "(\\d) (.*) at (\\d+.\\d{2})";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        int quantity;
        String productName;
        BigDecimal price;

        while (matcher.find()) {
            quantity = Integer.valueOf(matcher.group(1));
            productName = matcher.group(2);
            price = new BigDecimal(Double.valueOf(matcher.group(3)));

            purchases.add(new Purchase(quantity, productName, price));
        }


        return purchases;
    }
}
