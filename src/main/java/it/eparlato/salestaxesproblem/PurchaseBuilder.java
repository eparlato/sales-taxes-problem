package it.eparlato.salestaxesproblem;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PurchaseBuilder {
    public Purchase buildFromInput(String input) {
        String regex = "(\\d) (.*) at (\\d+.\\d{2})";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        int quantity = 0;
        String productName = "";
        BigDecimal price = new BigDecimal(0);

        if (matcher.find()) {
            quantity = Integer.valueOf(matcher.group(1));
            productName = matcher.group(2);
            price = new BigDecimal(Double.valueOf(matcher.group(3)));

        }


        return new Purchase(quantity, productName, price);
    }
}
