package Services;

import Models.Currency;
import Models.Stock;
import Repository.ReadOnlyRepository;

import java.util.ArrayList;
import java.util.List;

public class StockMarketService {

    private ReadOnlyRepository stockMarketRepository;
    private ReadOnlyRepository currencyRepository;

    public StockMarketService(ReadOnlyRepository stockMarketRepository,ReadOnlyRepository currencyRepository) {
        this.stockMarketRepository = stockMarketRepository;
        this.currencyRepository = currencyRepository;
    }

    public List<Stock> getStocks() {
        List<Stock> stocks = new ArrayList<>();
        List<String> list = stockMarketRepository.readFile();
        list.remove(0);
        for (String line : list) {
            String[] lineSplit = line.split(";");
            stocks.add(new Stock(lineSplit[0],lineSplit[1],lineSplit[2],stringToDouble(lineSplit[3]),
                   getCurrency(lineSplit[4]),lineSplit[5],stringToDouble(lineSplit[6]), lineSplit[7],LocalDateCreator.create(lineSplit[8])));
        }
        return stocks;
    }

    private Currency getCurrency(String currency){
        List<Currency> currencyList = getCurrencyList();
        for(Currency c : currencyList){
            if(c.getBaseCurrency().equalsIgnoreCase(currency)){
                return c;
            }
        }
        return null;
    }

    public List<Currency> getCurrencyList(){
        List<Currency> currencyList = new ArrayList<>();
        List<String> list = currencyRepository.readFile();
        list.remove(0);
        for(String line : list){
            String[] lineSplit = line.split(";");
            currencyList.add(new Currency(lineSplit[0],lineSplit[1],stringToDouble(lineSplit[2]),
                    LocalDateCreator.create(lineSplit[3])));
        }
        return currencyList;
    }

    private double stringToDouble(String getDouble){
        return Double.parseDouble(getDouble.replace(",","."));
    }

}
