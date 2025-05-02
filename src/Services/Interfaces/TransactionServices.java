package Services.Interfaces;

import Models.Model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionServices {
    public boolean addNewTransaction(int userID, LocalDate dateOfTransaction, String ticker,
                                     double price, String currency, String orderType, int quantity);

    public List<Transaction> getTransactionsForUser(int userID);
}
