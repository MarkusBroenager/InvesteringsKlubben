package Repository.Interfaces;

import Models.Model.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> getAllTransactions();

    List<Transaction> getAllTransactionsFromUserID(int userID);

    void addTransaction(Transaction transaction);
}
