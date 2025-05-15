package Services.ServicesCSV;

import Repository.Interfaces.CurrencyRepository;
import Models.Model.Transaction;
import Models.Model.Currency;
import Repository.Interfaces.TransactionRepository;
import Repository.RepositoriesCSV.TransactionRepositoryCSV;
import Repository.RepositoriesCSV.CurrencyRepositoryCSV;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionServiceTest {

    private TransactionService transactionService;
    @BeforeEach
    void setUp() {
        TransactionRepository transactionRepository = new TransactionRepositoryCSV("transactions.csv");
        CurrencyRepository currencyRepository = new CurrencyRepositoryCSV("currency.csv");
        transactionService = new TransactionService(transactionRepository, currencyRepository);
    }

    @Test
    void returnsCorrectPriceInEUR() {
        Assertions.assertEquals(26.6,transactionService.getPriceInQuoteCurrency(198.0, "EUR"));
    }

    @Test
    void returnsCorrectPriceInUSD() {
        Assertions.assertEquals(28.7,transactionService.getPriceInQuoteCurrency(198.0, "USD"));
    }

    @Test
    void returnsCorrectPriceInSEK() {
        Assertions.assertEquals(304.6,transactionService.getPriceInQuoteCurrency(198.0, "SEK"));
    }

    @Test
    void addNewTransactionTest() {
        transactionService.addNewTransaction(1, LocalDate.of(2021, 3, 12), "NOVO", 20.0, "DKK", "AA", 5);
    }

    @Test
    void returnsCorrectPriceInNOK() {
        assertEquals(291.2,transactionService.getPriceInQuoteCurrency(198.0, "NOK"));
    }

    @Test
    void returnsCorrectPriceInGBP() {
        assertEquals(22.9, transactionService.getPriceInQuoteCurrency(198.0, "GBP"));
    }

    @Test
    void getTransactionsForUserTest() {
        transactionService.getTransactionsForUser(1);

        List<Transaction> transactions = transactionService.getTransactionsForUser(1);

        Assertions.assertEquals(transactionService.getTransactionsForUser(1), transactions);
    }

    @Test
    void returnsCorrectPriceInJPY() {
        assertEquals(4212.8, transactionService.getPriceInQuoteCurrency(198.0, "JPY"));
    }

    @Test
    void createNewTransactionTest() {
        Transaction transaction = new Transaction(1,1,LocalDate.of(2025, 03, 01), "NOVO-B", 710.5, "DKK", "buy",20);

        Assertions.assertEquals(transaction, transaction);
    }

    @Test
    void returnsCorrectPriceInCHF() {
        assertEquals(25.4, transactionService.getPriceInQuoteCurrency(198.0, "CHF"));
    }

    @Test
    void getPriceInQuoteCurrencyTest() {
        Currency currency = new Currency("DKK","DKK",1,LocalDate.of(2025, 4, 22));
        CurrencyRepository currencyRepository = new CurrencyRepositoryCSV("currency.csv");
        Currency quoteCurrency = currencyRepository.getCurrencyFromBaseCurrency("DKK");
        double rate = quoteCurrency.getRate();

        Assertions.assertEquals(rate, 1);
    }

    @Test
    void returnsCorrectPriceInAUD() {
        assertEquals(44.5,transactionService.getPriceInQuoteCurrency(198.0, "AUD"));
    }

    @Test
    void getUniqueIDTest() {
        TransactionRepository transactionRepository = new TransactionRepositoryCSV("transactions.csv");
        List<Transaction> transactions = transactionRepository.getAllTransactions();
        Collections.sort(transactions);
        int transactionID = transactions.get(transactions.size() - 1).getTransactionID() + 1;

        Assertions.assertEquals(transactionID, 1);
    }

    @Test
    void returnsCorrectPriceInCAD() {
        assertEquals(38.8,transactionService.getPriceInQuoteCurrency(198.0, "CAD"));
    }

}
