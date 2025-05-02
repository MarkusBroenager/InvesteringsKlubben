package Repository.Interfaces;

import Models.Model.Currency;

import java.util.List;

public interface CurrencyRepository {

    public List<Currency> getListOfCurrencies();
    public Currency getCurrencyFromBaseCurrency(String currency);
}
