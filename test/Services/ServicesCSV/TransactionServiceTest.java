package Services.ServicesCSV;

import Repository.Interfaces.CurrencyRepository;
import Repository.Interfaces.TransactionRepository;
import Repository.RepositoriesCSV.CurrencyRepositoryCSV;
import Repository.RepositoriesCSV.TransactionRepositoryCSV;
import Services.Interfaces.TransactionServices;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {



    @Test
    void returnsCorrectPriceInEUR() {
        CurrencyRepository currencyRepository = new CurrencyRepositoryCSV("currency.csv");
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
    void returnsCorrectPriceInNOK() {
        TransactionService transactionService = new TransactionService();
        assertEquals(291.2,transactionService.getPriceInQuoteCurrency(198.0, "NOK"));
    }

    @Test
    void returnsCorrectPriceInGBP() {
        TransactionService transactionService = new TransactionService();
        assertEquals(22.9,transactionService.getPriceInQuoteCurrency(198.0, "GBP"));
    }

    @Test
    void returnsCorrectPriceInJPY() {
        TransactionService transactionService = new TransactionService();
        assertEquals(4212.8,transactionService.getPriceInQuoteCurrency(198.0, "JPY"));
    }

    @Test
    void returnsCorrectPriceInCHF() {
        TransactionService transactionService = new TransactionService();
        assertEquals(25.4,transactionService.getPriceInQuoteCurrency(198.0, "CHF"));
    }

    @Test
    void returnsCorrectPriceInAUD() {
        TransactionService transactionService = new TransactionService();
        assertEquals(44.5,transactionService.getPriceInQuoteCurrency(198.0, "AUD"));
    }

    @Test
    void returnsCorrectPriceInCAD() {
        TransactionService transactionService = new TransactionService();
        assertEquals(38.8,transactionService.getPriceInQuoteCurrency(198.0, "CAD"));
    }




}