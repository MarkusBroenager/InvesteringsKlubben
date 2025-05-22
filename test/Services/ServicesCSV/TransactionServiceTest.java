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

import static org.junit.jupiter.api.Assertions.*;

public class TransactionServiceTest {

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        TransactionRepository transactionRepository = new TransactionRepositoryCSV("test\\TestResources\\testTransactions.csv");
        CurrencyRepository currencyRepository = new CurrencyRepositoryCSV("test\\TestResources\\testCurrency.csv");
        transactionService = new TransactionService(transactionRepository, currencyRepository);
    }

    @Test
    void returnsCorrectPriceInEUR() {
        Assertions.assertEquals(1475.1000000000001, transactionService.getPriceInQuoteCurrency(198.0, "EUR"));
    }

    @Test
    void returnsCorrectPriceInUSD() {
        Assertions.assertEquals(1366.2, transactionService.getPriceInQuoteCurrency(198.0, "USD"));
    }

    @Test
    void returnsCorrectPriceInSEK() {
        Assertions.assertEquals(128.70000000000002, transactionService.getPriceInQuoteCurrency(198.0, "SEK"));
    }

    @Test
    void returnsCorrectPriceInNOK() {
        assertEquals(134.64000000000001, transactionService.getPriceInQuoteCurrency(198.0, "NOK"));
    }

    @Test
    void returnsCorrectPriceInGBP() {
        assertEquals(1712.7, transactionService.getPriceInQuoteCurrency(198.0, "GBP"));
    }

    @Test
    void returnsCorrectPriceInJPY() {
        assertEquals(9.306, transactionService.getPriceInQuoteCurrency(198.0, "JPY"));
    }

    @Test
    void returnsCorrectPriceInCHF() {
        assertEquals(1544.3999999999999, transactionService.getPriceInQuoteCurrency(198.0, "CHF"));
    }

    @Test
    void returnsCorrectPriceInAUD() {
        assertEquals(881.1, transactionService.getPriceInQuoteCurrency(198.0, "AUD"));
    }

    @Test
    void returnsCorrectPriceInCAD() {
        assertEquals(1009.8, transactionService.getPriceInQuoteCurrency(198.0, "CAD"));
    }

    @Test
    void addNewTransactionTest() {
        boolean newTransaction = transactionService.addNewTransaction(1, LocalDate.of(2021, 3, 12), "NOVO-B", 20.0, "DKK", "buy", 5);

        Assertions.assertEquals(newTransaction, true);
    }

    @Test
    void createNewTransactionTest() {
        Transaction transaction = new Transaction(1, 1, LocalDate.of(2025, 03, 01), "NOVO-B", 710.5, "DKK", "buy", 20);

        Assertions.assertEquals(transaction, transaction);
    }

    @Test
    void getPriceInQuoteCurrencyTest() {
        CurrencyRepository currencyRepository = new CurrencyRepositoryCSV("test\\TestResources\\testCurrency.csv");
        Currency quoteCurrency = currencyRepository.getCurrencyFromBaseCurrency("DKK");
        double rate = quoteCurrency.getRate();

        Assertions.assertEquals(rate, 1);
    }
}
