package Models.Interfaces;

import java.time.LocalDate;

public interface Transactions {

    public String getTicker();

    public int getUserID();

    public String getOrderType();

    public double getTotalTransactionPrice();

    public LocalDate getDateOfTransaction();

    public int getTransactionID();

    public int getQuantity();

    public String getCurrency();

    public String addTransactionToCSVFile();

    public String toString();

}
