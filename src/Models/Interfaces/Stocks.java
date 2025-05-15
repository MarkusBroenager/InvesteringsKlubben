package Models.Interfaces;

import Models.Model.Currency;

import java.time.LocalDate;

public interface Stocks {

    String getTicker();

    String getName();

    String getSector();

    String getMarket();

    String getCurrency();

    double getPrice();

    void setCurrency(Currency currency);

    String getRating();

    double getDivident();

    LocalDate getLastUpdated();



    @Override
    String toString();

}
