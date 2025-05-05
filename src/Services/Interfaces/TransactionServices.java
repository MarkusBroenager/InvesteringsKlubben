package Services.Interfaces;

import Models.Model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionServices {
    boolean addNewTransaction(int userID, LocalDate dateOfTransaction, String ticker,
                              double price, String currency, String orderType, int quantity);

    List<Transaction> getTransactionsForUser(int userID);
}
