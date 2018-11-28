package it.eparlato.salestaxesproblem;

public class SalesTaxesProblemMain {
    public static void main(String[] args) {
        CashRegister cashRegister = new CashRegister();

        Receipt receipt = cashRegister.process("");

        System.out.println(receipt.buildAndReturn());
    }
}
