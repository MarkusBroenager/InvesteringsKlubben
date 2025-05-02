package Repository.RepositoriesCSV;

import Models.Model.Currency;
import Repository.Interfaces.CurrencyRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyRepositoryCSVTest {

    @Test
    void getCurrencyFromBaseCurrency() {
        CurrencyRepository currencyRepositoryCSV = new CurrencyRepositoryCSV("currency.csv");
        assertEquals(currencyRepositoryCSV.getCurrencyFromBaseCurrency("EUR").toString(),"1 EUR is currently worth 7,45 DKK Last update: 2025-04-22");
    }
}