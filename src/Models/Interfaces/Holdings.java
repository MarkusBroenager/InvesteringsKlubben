package Models.Interfaces;

import Models.Model.Currency;

public interface Holdings {

    public double getValueOfHoldingInDKK();

    public String getTicker();

    public int getQuantity();

    public double getAmountSpent();

    public Currency getCurrency();
}
