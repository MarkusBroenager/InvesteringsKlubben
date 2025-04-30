package Repository.Interfaces;

import Models.Transaction;

import java.util.List;

public interface TransactionRepository {

    public List<Transaction> getAllTransactions();
    public List<Transaction> getAllTransactionsFromUserID(int userID);
    public void addTransaction(Transaction transaction);
}
