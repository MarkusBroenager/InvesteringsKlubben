package Services.ServicesCSV;

import Repository.Interfaces.CurrencyRepository;
import Models.Model.User;
import Models.Model.Transaction;
import Models.Model.Currency;
import Repository.Interfaces.TransactionRepository;
import Repository.Interfaces.UserRepository;
import Repository.RepositoriesCSV.TransactionRepositoryCSV;
import Repository.Interfaces.CurrencyRepository;
import Repository.RepositoriesCSV.CurrencyRepositoryCSV;
import Repository.RepositoriesCSV.UserRepositoryCSV;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import Repository.RepositoriesCSV.TransactionRepositoryCSV;
import Services.Interfaces.TransactionServices;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionServiceTest {
class TransactionServiceTest {


    private TransactionService transactionService;
    @BeforeEach
    void setUp() {
        TransactionRepository transactionRepository = new TransactionRepositoryCSV("transactions.csv");

    @Test
    void returnsCorrectPriceInEUR() {
        CurrencyRepository currencyRepository = new CurrencyRepositoryCSV("currency.csv");
        transactionService = new TransactionService(transactionRepository, currencyRepository);

        TransactionRepository transactionRepository = new TransactionRepositoryCSV("transactions.csv");
        TransactionService transactionService = new TransactionService(transactionRepository, currencyRepository);

        assertEquals(26.6,transactionService.getPriceInQuoteCurrency(198.0, "EUR"));
    }

    @Test
    void returnsCorrectPriceInUSD() {
        TransactionService transactionService = new TransactionService();
        assertEquals(28.7,transactionService.getPriceInQuoteCurrency(198.0, "USD"));
    }

    @Test
    void returnsCorrectPriceInSEK() {
        TransactionService transactionService = new TransactionService();
        assertEquals(304.6,transactionService.getPriceInQuoteCurrency(198.0, "SEK"));
    }

    @Test
    void addNewTransactionTest() {
        transactionService.addNewTransaction(1, LocalDate.of(2021, 3, 12), "NOVO", 20.0, "DKK", "AA", 5);

        /*Transaction newTransaction = createNewTransaction(userID, dateOfTransaction, ticker,
                getPriceInQuoteCurrency(price, currency),
                currencyRepository.getCurrencyFromBaseCurrency(currency).getQuoteCurrency(), orderType, quantity);

        //TO_DO check if price varies too much from price on the stock market
        if (newTransaction.getQuantity() > 0) {
            transactionRepository.addTransaction(newTransaction);
            return true;
        }
        return false;*/

    void returnsCorrectPriceInNOK() {
        TransactionService transactionService = new TransactionService();
        assertEquals(291.2,transactionService.getPriceInQuoteCurrency(198.0, "NOK"));
    }

    @Test
    void returnsCorrectPriceInGBP() {
        TransactionService transactionService = new TransactionService();
        assertEquals(22.9,transactionService.getPriceInQuoteCurrency(198.0, "GBP"));
    void getTransactionsForUserTest() {
        transactionService.getTransactionsForUser(1);

        List<Transaction> transactions = transactionService.getTransactionsForUser(1);

        Assertions.assertEquals(transactionService.getTransactionsForUser(1), transactions);
    }

    @Test
    void returnsCorrectPriceInJPY() {
        TransactionService transactionService = new TransactionService();
        assertEquals(4212.8,transactionService.getPriceInQuoteCurrency(198.0, "JPY"));
    void createNewTransactionTest() {
        Transaction transaction = new Transaction(1,1,LocalDate.of(2025, 03, 01), "NOVO-B", 710.5, "DKK", "buy",20);

        Assertions.assertEquals(transaction, transaction);
    }

    @Test
    void returnsCorrectPriceInCHF() {
        TransactionService transactionService = new TransactionService();
        assertEquals(25.4,transactionService.getPriceInQuoteCurrency(198.0, "CHF"));
    void getPriceInQuoteCurrencyTest() {
        Currency currency = new Currency("DKK","DKK",1,LocalDate.of(2025, 4, 22));
        CurrencyRepository currencyRepository = new CurrencyRepositoryCSV("currency.csv");
        Currency quoteCurrency = currencyRepository.getCurrencyFromBaseCurrency("DKK");
        double rate = quoteCurrency.getRate();

        Assertions.assertEquals(rate, 1);
    }

    @Test
    void returnsCorrectPriceInAUD() {
        TransactionService transactionService = new TransactionService();
        assertEquals(44.5,transactionService.getPriceInQuoteCurrency(198.0, "AUD"));
    }
    void getUniqueIDTest() {
        TransactionRepository transactionRepository = new TransactionRepositoryCSV("transactions.csv");
        List<Transaction> transactions = transactionRepository.getAllTransactions();
        Collections.sort(transactions);
        int transactionID = transactions.get(transactions.size()-1).getTransactionID() + 1;

        Assertions.assertEquals(transactionID, 1);

    @Test
    void returnsCorrectPriceInCAD() {
        TransactionService transactionService = new TransactionService();
        assertEquals(38.8,transactionService.getPriceInQuoteCurrency(198.0, "CAD"));
    }




}
