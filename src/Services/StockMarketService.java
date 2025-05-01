package Services;

import Models.Currency;
import Models.Stock;
import Repository.Interfaces.CurrencyRepository;
import Repository.Interfaces.StockMarketRepository;

import java.util.List;

public class StockMarketService {

    private StockMarketRepository stockMarketRepository;
    private CurrencyRepository currencyRepository;

    public StockMarketService(StockMarketRepository stockMarketRepository, CurrencyRepository currencyRepository) {
        this.stockMarketRepository = stockMarketRepository;
        this.currencyRepository = currencyRepository;
    }

    public List<Stock> getStocks() {
        return stockMarketRepository.getStockList();
    }

    public Currency getCurrency(String currency) {
        return currencyRepository.getCurrencyFromBaseCurrency(currency);
    }

    public Stock getStock(String ticker){
        return stockMarketRepository.getStockFromTicker(ticker);
    }

    public List<Currency> getCurrencyList() {
        return currencyRepository.getListOfCurrencies();
    }


}
