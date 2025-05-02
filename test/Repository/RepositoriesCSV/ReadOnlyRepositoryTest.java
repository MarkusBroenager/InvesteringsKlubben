package Repository.RepositoriesCSV;

import Models.Model.Currency;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadOnlyRepositoryTest {

    @Test
    void readFile() {
        ReadOnlyRepository repository = new ReadOnlyRepository("currency.csv");
        List<String> currencies = repository.readFile();
        assertEquals(currencies.get(4),"SEK;DKK;0,65;22-04-2025");
    }
}