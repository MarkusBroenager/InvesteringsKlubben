package Repository.Interfaces;

import Models.Model.Currency;

import java.util.List;

public interface CurrencyRepository {

    List<Currency> getListOfCurrencies();

    Currency getCurrencyFromBaseCurrency(String currency);
}
