package Services.ServicesCSV;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DataServicesTest {

    @Test
    void returnsCorrectDate() {
        DataServices services = new DataServices();
        assertEquals(LocalDate.of(2025, 05, 13), services.getLocalDate("2025-05-13"));
    }

    @Test
    void correctsDate() {
        DataServices services = new DataServices();
        assertEquals(LocalDate.of(2025, 05, 13), services.getLocalDate("13-05-2025"));
    }

    @Test
    void returnsWrongInputDate() {
        DataServices services = new DataServices();
        assertEquals(LocalDate.of(0, 1, 1), services.getLocalDate("2025-05"));
    }
    @Test
    void returnsAnotherWrongInputDate() {
        DataServices services = new DataServices();
        assertEquals(LocalDate.of(0, 1, 1), services.getLocalDate("-05-13"));
    }
}