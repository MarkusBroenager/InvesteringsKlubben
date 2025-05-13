package Models.Interfaces;

import Models.Model.Currency;

public interface Holdings {

    double getValueOfHoldingInDKK();

    double getPriceInQuoteCurrency();

    String getTicker();

    int getQuantity();

    String getSector();

    Currency getCurrency();
}
