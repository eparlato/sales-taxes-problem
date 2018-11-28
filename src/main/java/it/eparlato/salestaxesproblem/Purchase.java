package it.eparlato.salestaxesproblem;

import java.math.BigDecimal;

public class Purchase {
    private int quantity;
    private String productName;
    private BigDecimal price;

    public Purchase(int quantity, String productName, BigDecimal price) {
        this.quantity = quantity;
        this.productName = productName;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Purchase)) {
            return false;
        }

        Purchase that = (Purchase) obj;

        if (this.quantity != that.quantity) {
            return false;
        }

        if (!this.productName.equals(that.productName)) {
            return false;
        }

        if (!(this.price.compareTo(that.price) == 0)) {
            return false;
        }

        return true;
    }
}
