package Models.Model;

import Models.Interfaces.Holdings;

public class Holding implements Holdings {

    private Stock stock;
    private Currency currency;
    private int quantity;

    public Holding(Stock stock, Currency currency, int quantity) {
        this.stock = stock;
        this.currency = currency;
        this.quantity = quantity;
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
    public String getSector() {
        return stock.getSector();
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }


    public String toString() {
        return stock.getTicker() + " " + stock.getName() + " " + String.format("%.2f", stock.getPrice()) + " " +
                currency.getBaseCurrency() + " Amount: " + quantity + " Total value: " +
                String.format("%.2f", getValueOfHoldingInDKK()) + " " + currency.getBaseCurrency();
    }
}
