package Models.Interfaces;

import Models.Model.Currency;

public interface Holdings {

    double getValueOfHoldingInDKK();

    String getTicker();

    int getQuantity();

    String getSector();

    Currency getCurrency();
}
