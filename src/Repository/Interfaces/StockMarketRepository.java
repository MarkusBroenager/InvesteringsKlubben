package Repository.Interfaces;

import Models.Model.Stock;

import java.util.List;

public interface StockMarketRepository {

    List<Stock> getStockList();

    Stock getStockFromTicker(String ticker);
}
