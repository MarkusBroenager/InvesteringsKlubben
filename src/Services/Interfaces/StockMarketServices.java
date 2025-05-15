package Services.Interfaces;

import Models.Interfaces.Asset;
import Models.Model.Bond;
import Models.Model.Currency;
import Models.Model.Stock;

import java.util.List;

public interface StockMarketServices {

    public Asset getAsset(String ticker);

    List<Stock> getStocks();

    List<Bond> getBonds();

    List<Stock> getStocksInDKK();

    Stock getStock(String ticker);

    Stock getStockInDKK(String ticker);

    Bond getBond(String ticker);

    List<Currency> getCurrencyList();

    Currency getCurrency(String currency);
}
