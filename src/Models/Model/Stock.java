package Models.Model;

import Models.Interfaces.Asset;

import java.time.LocalDate;

public class Stock implements  Asset, Comparable<Asset> {

    private String ticker;
    private String name;
    private String sector;
    private double price;
    private String currency;
    private String rating;
    private double dividend;
    private String market;
    private LocalDate lastUpdated;


    public Stock(String ticker, String name, String sector, double price, String currency,
                 String rating, double dividend, String market, LocalDate lastUpdated) {
        this.name = name;
        this.ticker = ticker;
        this.sector = sector;
        this.price = price;
        this.currency = currency;
        this.rating = rating;
        this.dividend = dividend;
        this.market = market;
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String getTicker() {
        return ticker;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSector() {
        return sector;
    }

    public String getMarket() {
        return market;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public LocalDate getLastUpdated(){
        return this.lastUpdated;
    }

    @Override
    public int compareTo(Asset otherAsset) {
        return Asset.super.compareTo(otherAsset);
    }

    public void setCurrency(Currency currency) {
        if (currency.getBaseCurrency().equalsIgnoreCase(this.currency)) {
            this.price = price * currency.getRate();
            this.currency = currency.getQuoteCurrency();
        }
    }

    @Override
    public String toString() {
        return name + " (" + ticker + ");" + sector + ';' + String.format("%.2f", price) + ' ' +
                currency + ';' + rating + ";" + String.format("%.2f", dividend) + "%;" + market +
                ";" + lastUpdated;
    }
}
