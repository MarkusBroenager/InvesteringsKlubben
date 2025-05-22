package Models.Model;

import Models.Interfaces.Asset;
import Services.ServicesCSV.ColorService;

public class Holding implements Comparable<Holding> {

    private Asset asset;
    private Currency currency;
    private int quantity;
    private final String BLUE;
    private final String STANDARD;

    public Holding(Asset asset, Currency currency, int quantity) {
        this.asset = asset;
        this.currency = currency;
        this.quantity = quantity;
        //setting color
        this.BLUE = ColorService.getBlueColor();
        this.STANDARD = ColorService.getStandardColor();
    }

    public double getValueOfHoldingInDKK() {
        return asset.getPrice() * currency.getRate() * quantity;
    }

    public double getPriceInQuoteCurrency() {
        return asset.getPrice() * currency.getRate();
    }

    public String getTicker() {
        return asset.getTicker();
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSector() {
        return asset.getSector();
    }

    public Currency getCurrency() {
        return currency;
    }


    public String toString() {
        return asset.getTicker() + " " + asset.getName() + " " + String.format("%.2f", this.getPriceInQuoteCurrency()) +
                " DKK " + BLUE + "Amount: " + STANDARD + quantity + BLUE + " Total value: " + STANDARD +
                String.format("%.2f", getValueOfHoldingInDKK()) + " DKK";
    }
    //TODO : unused method
    public String tableToString() {
        return asset.getTicker() + " " + asset.getName() + " " + String.format("%.2f", this.getPriceInQuoteCurrency()) +
                " DKK " + ";" + getQuantity() + ";" + String.format("%.2f", getValueOfHoldingInDKK()) + " DKK";
    }

    @Override
    public int compareTo(Holding o) {
        if(((this.getValueOfHoldingInDKK() * 100) == (o.getValueOfHoldingInDKK() * 100))){
            return o.getTicker().compareTo(this.getTicker());
        }
        return (int) ((this.getValueOfHoldingInDKK() * 100) - (o.getValueOfHoldingInDKK() * 100));
    }
}
