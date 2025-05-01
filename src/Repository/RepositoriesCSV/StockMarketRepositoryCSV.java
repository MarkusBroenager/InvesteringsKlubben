package Repository.RepositoriesCSV;

import Models.Stock;
import Repository.Interfaces.StockMarketRepository;
import Services.DataServices;

import java.util.ArrayList;
import java.util.List;

public class StockMarketRepositoryCSV extends ReadOnlyRepository implements StockMarketRepository {
    public StockMarketRepositoryCSV(String file) {
        super(file);
    }

    @Override
    public List<Stock> getStockList() {
        List<Stock> stocks = new ArrayList<>();
        List<String> list = super.readFile();
        list.remove(0);
        for (String line : list) {
            String[] lineSplit = line.split(";");
            stocks.add(new Stock(lineSplit[0],lineSplit[1],lineSplit[2],DataServices.stringToDouble(lineSplit[3]),
                    lineSplit[4],lineSplit[5],DataServices.stringToDouble(lineSplit[6]),
                    lineSplit[7], DataServices.getLocalDate(lineSplit[8])));
        }
        return stocks;
    }

    @Override
    public Stock getStockFromTicker(String ticker) {
        List<Stock> stocks = getStockList();
        for(Stock s : stocks){
            if(s.getTicker().equalsIgnoreCase(ticker)){
                return s;
            }
        }
        return null;
    }
}
