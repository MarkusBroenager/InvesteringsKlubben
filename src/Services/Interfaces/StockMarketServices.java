package Services.Interfaces;

import Models.Model.Currency;
import Models.Model.Stock;

import java.util.List;

public interface StockMarketServices {
    List<Stock> getStocks();

    List<Stock> getStocksInDKK();

    Stock getStock(String ticker);

    List<Currency> getCurrencyList();

    Currency getCurrency(String currency);
}
