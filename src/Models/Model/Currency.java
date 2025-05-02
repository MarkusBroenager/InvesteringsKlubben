package Models.Model;

import Models.Interfaces.Currencies;

import java.time.LocalDate;

public class Currency implements Currencies {

    private String baseCurrency;
    private String quoteCurrency;
    private double rate;
    private LocalDate lastUpdated;

    public Currency(String baseCurrency, String quoteCurrency, double rate, LocalDate lastUpdated) {
        this.baseCurrency = baseCurrency;
        this.quoteCurrency = quoteCurrency;
        this.rate = rate;
        this.lastUpdated = lastUpdated;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public double getRate() {
        return rate;
    }

    public String toString() {
        if(String.valueOf(rate).contains("0.0")){
            return "1 " + baseCurrency + " is currently worth " + rate +
                    " " + quoteCurrency + " Last update: " + lastUpdated;
        }
        return "1 " + baseCurrency + " is currently worth " + String.format("%.2f",rate) +
                " " + quoteCurrency + " Last update: " + lastUpdated;
    }

}
