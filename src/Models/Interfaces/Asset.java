package Models.Interfaces;

import java.time.LocalDate;

public interface Asset {

    String getTicker();
    String getName();
    double getPrice();
    String getCurrency();
    String getSector();
    LocalDate getLastUpdated();
}
