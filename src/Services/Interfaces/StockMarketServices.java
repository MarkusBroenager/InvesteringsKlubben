package Services.Interfaces;

import Models.Model.Currency;
import Models.Model.Stock;

import java.util.List;

public interface StockMarketServices {
    public List<Stock> getStocks();

    public Stock getStock(String ticker);

    public List<Currency> getCurrencyList();

    public Currency getCurrency(String currency);
}
