package Models.Model;

import Models.Interfaces.Asset;
import Models.Interfaces.Holdings;

public class Holding implements Holdings,Comparable<Holding> {

    private Asset asset;
    private Currency currency;
    private int quantity;

    public Holding(Asset asset, Currency currency, int quantity) {
        this.asset = asset;
        this.currency = currency;
        this.quantity = quantity;
    }

    @Override
    public double getValueOfHoldingInDKK() {
        return asset.getPrice() * currency.getRate() * quantity;
    }

    @Override
    public double getPriceInQuoteCurrency() {
        return asset.getPrice() * currency.getRate();
    }

    @Override
    public String getTicker() {
        return asset.getTicker();
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getSector() {
        return asset.getSector();
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }


    public String toString() {
        return asset.getTicker() + " " + asset.getName() + " " + String.format("%.2f", this.getPriceInQuoteCurrency()) +
                " DKK Amount: " + quantity + " Total value: " +
                String.format("%.2f", getValueOfHoldingInDKK()) + " DKK";
    }

    @Override
    public int compareTo(Holding o) {
        if(((this.getValueOfHoldingInDKK() * 100) == (o.getValueOfHoldingInDKK() * 100))){
            return o.getTicker().compareTo(this.getTicker());
        }
        return (int) ((this.getValueOfHoldingInDKK() * 100) - (o.getValueOfHoldingInDKK() * 100));
    }
}
