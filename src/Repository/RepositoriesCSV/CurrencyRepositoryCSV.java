package Repository.RepositoriesCSV;

import Models.Model.Currency;
import Repository.Interfaces.CurrencyRepository;
import Services.ServicesCSV.DataServices;

import java.util.ArrayList;
import java.util.List;

public class CurrencyRepositoryCSV extends ReadOnlyRepository implements CurrencyRepository {
    public CurrencyRepositoryCSV(String file) {
        super(file);
    }

    @Override
    public List<Currency> getListOfCurrencies() {
        List<Currency> currencyList = new ArrayList<>();
        List<String> list = super.readFile();
        list.remove(0);
        for (String line : list) {
            String[] lineSplit = line.split(";");
            currencyList.add(new Currency(lineSplit[0], lineSplit[1], DataServices.stringToDouble(lineSplit[2]),
                    DataServices.getLocalDate(lineSplit[3])));
        }
        return currencyList;
    }

    @Override
    public Currency getCurrencyFromBaseCurrency(String currency) {
        List<Currency> currencyList = getListOfCurrencies();
        for (Currency c : currencyList) {
            if (c.getBaseCurrency().equalsIgnoreCase(currency)) {
                return c;
            }
        }
        return null;
    }
}
