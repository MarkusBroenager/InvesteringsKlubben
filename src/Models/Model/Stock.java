package Models.Model;

import Models.Interfaces.Stocks;

import java.time.LocalDate;

public class Stock implements Stocks {

    private String ticker;
    private String name;
    private String sector;
    private double price;
    private String currency;
    private String rating;
    private double dividend;
    private String market;
    private LocalDate lastUpdated;


    public Stock(String ticker, String name, String sector,double price,String currency,
                 String rating,double dividend,String market,LocalDate lastUpdated) {
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

    public String getTicker() {
        return ticker;
    }

    public String getName() {
        return name;
    }

    public String getSector() {
        return sector;
    }

    public String getMarket() {
        return market;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return name + " (" + ticker + ") Sector: " + sector + " Price: " + price + " " +
                currency + " Dividend: " + dividend +"% Exchange: " + market +
                " Rating: " + rating + " Last update: " + lastUpdated;
    }
}
