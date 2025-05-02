package Repository.Interfaces;

import Models.Model.Stock;

import java.util.List;

public interface StockMarketRepository {

    public List<Stock> getStockList();
    public Stock getStockFromTicker(String ticker);
}
