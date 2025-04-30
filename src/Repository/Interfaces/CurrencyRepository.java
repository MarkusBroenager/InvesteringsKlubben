package Repository.Interfaces;

import Models.Currency;

import java.util.List;

public interface CurrencyRepository {

    public List<Currency> getListOfCurrencies();
    public Currency getCurrencyFromBaseCurrency(String currency);
}
