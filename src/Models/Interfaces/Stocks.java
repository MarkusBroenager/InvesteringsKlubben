package Models.Interfaces;

import Models.Model.Currency;

public interface Stocks {

    String getTicker();

    String getName();

    String getSector();

    String getMarket();

    String getCurrency();

    double getPrice();

    void setCurrency(Currency currency);

    @Override
    String toString();

}
