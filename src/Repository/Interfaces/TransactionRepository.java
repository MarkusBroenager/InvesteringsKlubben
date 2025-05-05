package Repository.Interfaces;

import Models.Interfaces.Transactions;
import Models.Model.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> getAllTransactions();

    List<Transaction> getAllTransactionsFromUserID(int userID);

    void addTransaction(Transactions transaction);
}
