package Services;

import Models.Transaction;
import Repository.*;
import Repository.Interfaces.StockMarketRepository;
import Repository.Interfaces.TransactionRepository;

import java.time.LocalDate;
import java.util.List;

public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public boolean addNewTransaction(int userID, LocalDate dateOfTransaction, String ticker,
                                     double price, String currency, String orderType, int quantity) {
        Transaction newTransaction = createNewTransaction(userID,dateOfTransaction,ticker,price,currency,orderType,quantity);
        if (newTransaction != null) {
            transactionRepository.addTransaction(newTransaction);
            return true;
        }
        return false;
    }

    public List<Transaction> getTransactionsForUser(int userID){
        return transactionRepository.getAllTransactionsFromUserID(userID);
    }

    private Transaction createNewTransaction(int userID, LocalDate dateOfTransaction, String ticker,
                                             double price, String currency, String orderType, int quantity) {
        return new Transaction(getUniqueID(),userID,dateOfTransaction,ticker,price,currency,orderType,quantity);
    }

    private int getUniqueID() {
        return 0;
    }

}
