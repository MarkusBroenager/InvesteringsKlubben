package Models;

import Models.Model.Currency;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {

    @Test
    void getBaseCurrency() {
        Currency c = new Currency("DKK","EUR",7.54, LocalDate.now());
        assertEquals("DKK",c.getBaseCurrency());
    }

    @Test
    void getRate() {
        Currency c = new Currency("DKK","EUR",0.00033, LocalDate.now());
        assertEquals(0.00033,c.getRate());
    }
}