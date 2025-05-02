package Models.Model;

import Models.Interfaces.Holdings;

public class Holding implements Holdings {

    private Stock stock;
    private Currency currency;
    private int quantity;
    private double amountSpent;

    public Holding(Stock stock, Currency currency, int quantity) {
        this.stock = stock;
        this.currency = currency;
        this.quantity = quantity;
        this.amountSpent = amountSpent;
    }

    @Override
    public double getValueOfHoldingInDKK() {
        return stock.getPrice() * currency.getRate() * quantity;
    }

    @Override
    public String getTicker() {
        return stock.getTicker();
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    public double getAmountSpent() {
        return amountSpent;
    }

    public String toString(){
        return stock.getTicker() + " " + stock.getName() + " " + stock.getPrice() + " " +
                currency.getBaseCurrency() + " Amount: " + quantity + " Total value: " + getValueOfHoldingInDKK();
    }
}
