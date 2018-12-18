package it.eparlato.salestaxesproblem;

import java.math.BigDecimal;
import java.util.Objects;

public class Purchase {
    private int quantity;
    private String productName;
    private BigDecimal price;
    private BigDecimal taxValue;

    public Purchase(int quantity, String productName, BigDecimal price) {
        this.quantity = quantity;
        this.productName = productName;
        this.price = price;
        this.taxValue = new BigDecimal(2.50);
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getTotal() {
        return price.multiply(new BigDecimal(quantity));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(!(obj instanceof Purchase)) {
            return false;
        }

        Purchase that = (Purchase) obj;


        return (this.quantity == that.quantity) &&
                (this.productName.equals(that.productName)) &&
                (this.price.compareTo(that.price) == 0);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + quantity;
        result = 31 * result + productName.hashCode();
        result = 31 * result + price.hashCode();

        return result;
    }

    public boolean isEmpty() {
        return quantity == 0;
    }

    public BigDecimal getTaxValue() {
        return taxValue;
    }
}
