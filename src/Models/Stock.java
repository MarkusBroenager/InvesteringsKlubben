package Models;

import java.time.LocalDate;

public class Stock {

    private String ticker;
    private String name;
    private String sector;
    private double price;
    private Currency currency;
    private String rating;
    private double dividend;
    private String market;
    private LocalDate lastUpdated;


    public Stock(String ticker, String name, String sector,double price,Currency currency,
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

    @Override
    public String toString() {
        return name + " (" + ticker + ") Sector: " + sector + " Price: " + price + " " +
                currency.getBaseCurrency() + " Dividend: " + dividend +"% Exchange: " + market +
                " Rating: " + rating + " Last update: " + lastUpdated;
    }
}
