package Services.Interfaces;

import Models.Currency;
import Models.Stock;

import java.util.List;

public interface StockMarketServices {
    public List<Stock> getStocks();

    public Stock getStock(String ticker);

    public List<Currency> getCurrencyList();

    public Currency getCurrency(String currency);
}
