package Models.Model;

import Models.Interfaces.Asset;

import java.time.LocalDate;

public class Bond implements Asset, Comparable<Asset> {

    private String ticker;
    private String name;
    private double price;
    private String currency;
    private double couponRate;
    private LocalDate issueDate;
    private LocalDate maturityDate;
    private String rating;
    private String market;
    private LocalDate lastUpdated;

    public Bond(String ticker, String name, double price, String currency, double couponRate, LocalDate issueDate, LocalDate maturityDate, String rating, String market, LocalDate lastUpdated) {
        this.ticker = ticker;
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.couponRate = couponRate;
        this.issueDate = issueDate;
        this.maturityDate = maturityDate;
        this.rating = rating;
        this.market = market;
        this.lastUpdated = lastUpdated;
    }

    public String getTicker() {
        return ticker;
    }
    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getCurrency() {
        return this.currency;
    }

    @Override
    public String getSector(){
        return "Government Bonds";
    }
    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return name + " (" + ticker + ')' + "; " + String.format("%.2f", price) + " " +
                currency + "; " + String.format("%.2f", couponRate) + "% maturity date is the ;" + maturityDate +  "; " + market +
                "; " + rating + "; " + lastUpdated;
    }

    @Override
    public int compareTo(Asset otherAsset) {
        return Asset.super.compareTo(otherAsset);
    }


}
