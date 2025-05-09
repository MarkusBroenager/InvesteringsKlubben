package Services.ServicesCSV;

import Models.Model.Currency;
import Models.Model.Transaction;
import Repository.Interfaces.CurrencyRepository;
import Repository.Interfaces.TransactionRepository;
import Services.Interfaces.TransactionServices;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class TransactionService implements TransactionServices {

    private TransactionRepository transactionRepository;
    private CurrencyRepository currencyRepository;

    public TransactionService(TransactionRepository transactionRepository, CurrencyRepository currencyRepository) {
        this.transactionRepository = transactionRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public boolean addNewTransaction(int userID, LocalDate dateOfTransaction, String ticker,
                                     double price, String currency, String orderType, int quantity) {
        Transaction newTransaction = createNewTransaction(userID, dateOfTransaction, ticker,
                getPriceInQuoteCurrency(price, currency),
                currencyRepository.getCurrencyFromBaseCurrency(currency).getQuoteCurrency(), orderType, quantity);

        //TO_DO check if price varies too much from price on the stock market
        if (newTransaction.getQuantity() > 0) {
            transactionRepository.addTransaction(newTransaction);
            return true;
        }
        return false;
    }

    @Override
    public List<Transaction> getTransactionsForUser(int userID) {
        return transactionRepository.getAllTransactionsFromUserID(userID);
    }

    private Transaction createNewTransaction(int userID, LocalDate dateOfTransaction, String ticker,
                                             double price, String currency, String orderType, int quantity) {
        return new Transaction(getUniqueID(), userID, dateOfTransaction, ticker, price, currency, orderType, quantity);
    }

    private double getPriceInQuoteCurrency(double price, String currency) {
        Currency quoteCurrency = currencyRepository.getCurrencyFromBaseCurrency(currency);
        return price * quoteCurrency.getRate();
    }

    private int getUniqueID() {
        List<Transaction> transactions = transactionRepository.getAllTransactions();
        Collections.sort(transactions);
        return transactions.get(transactions.size()-1).getTransactionID() + 1;
    }
}
