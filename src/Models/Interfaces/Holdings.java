package Models.Interfaces;

import Models.Model.Currency;

public interface Holdings {

    double getValueOfHoldingInQuoteCurrency();

    String getTicker();

    int getQuantity();

    String getSector();

    Currency getCurrency();
}
