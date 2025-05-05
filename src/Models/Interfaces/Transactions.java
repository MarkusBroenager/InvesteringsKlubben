package Models.Interfaces;

import java.time.LocalDate;

public interface Transactions {

    String getTicker();

    int getUserID();

    String getOrderType();

    double getTotalTransactionPrice();

    LocalDate getDateOfTransaction();

    int getTransactionID();

    int getQuantity();

    String getCurrency();

    String addTransactionToCSVFile();

    String toString();

}
