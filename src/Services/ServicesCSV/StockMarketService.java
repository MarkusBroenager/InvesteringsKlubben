package Services.ServicesCSV;

import Models.Model.Currency;
import Models.Model.Stock;
import Repository.Interfaces.CurrencyRepository;
import Repository.Interfaces.StockMarketRepository;
import Services.Interfaces.StockMarketServices;

import java.util.List;

public class StockMarketService implements StockMarketServices {

    private StockMarketRepository stockMarketRepository;
    private CurrencyRepository currencyRepository;

    public StockMarketService(StockMarketRepository stockMarketRepository, CurrencyRepository currencyRepository) {
        this.stockMarketRepository = stockMarketRepository;
        this.currencyRepository = currencyRepository;
    }

    public List<Stock> getStocks() {
        return stockMarketRepository.getStockList();
    }

    @Override
    public List<Stock> getStocksInDKK() {
        List<Stock> stocks = getStocks();
        for(Stock s : stocks){
            s.setCurrency(getCurrency(s.getCurrency()));
        }
        return stocks;
    }

    public Stock getStock(String ticker){
        return stockMarketRepository.getStockFromTicker(ticker);
    }

    public List<Currency> getCurrencyList() {
        return currencyRepository.getListOfCurrencies();
    }

    public Currency getCurrency(String currency) {
        return currencyRepository.getCurrencyFromBaseCurrency(currency);
    }




}
