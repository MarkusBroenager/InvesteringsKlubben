package Services.ServicesCSV;

import Models.Model.Bond;
import Models.Model.Currency;
import Models.Model.Stock;
import Repository.Interfaces.BondRepository;
import Repository.Interfaces.CurrencyRepository;
import Repository.Interfaces.StockMarketRepository;
import Services.Interfaces.StockMarketServices;

import java.util.Collections;
import java.util.List;

public class StockMarketService implements StockMarketServices {

    private StockMarketRepository stockMarketRepository;
    private BondRepository bondRepository;
    private CurrencyRepository currencyRepository;

    public StockMarketService(StockMarketRepository stockMarketRepository, BondRepository bondRepository, CurrencyRepository currencyRepository) {
        this.stockMarketRepository = stockMarketRepository;
        this.bondRepository = bondRepository;
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

        Collections.sort(stocks);

        return stocks;
    }

    public List<Bond> getBonds() {
        List<Bond> bonds = bondRepository.getBondList();
        Collections.sort(bonds);
        return bonds;
    }

    public Stock getStock(String ticker){
        return stockMarketRepository.getStockFromTicker(ticker);
    }
    public Stock getStockInDKK(String ticker){
        Stock stock = stockMarketRepository.getStockFromTicker(ticker);
        if(stock != null) {
            stock.setCurrency(getCurrency(stock.getCurrency()));
        }
        return stock;
    }
    public Bond getBond(String ticker){
        return bondRepository.getBondFromTicker(ticker);
    }

    public List<Currency> getCurrencyList() {
        return currencyRepository.getListOfCurrencies();
    }

    public Currency getCurrency(String currency) {
        return currencyRepository.getCurrencyFromBaseCurrency(currency);
    }

}
