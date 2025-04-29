package Services;

import Models.Stock;
import Repository.ReadOnlyRepository;

import java.util.ArrayList;
import java.util.List;

public class StockMarketService {

    private ReadOnlyRepository repo;

    public StockMarketService(ReadOnlyRepository repo) {
        this.repo = repo;
    }

    public List<Stock> getStocks() {
        List<Stock> stocks = new ArrayList<>();
        List<String> list = repo.readFile();
        list.remove(0);
        for (String line : list) {
            String[] lineSplit = line.split(";");
            double price = Double.parseDouble(lineSplit[3].replace(",","."));
            double dividend = Double.parseDouble(lineSplit[6].replace(",","."));
            stocks.add(new Stock(lineSplit[0],lineSplit[1],lineSplit[2],price,
                    lineSplit[4],lineSplit[5],dividend, lineSplit[7],LocalDateCreator.create(lineSplit[8])));
        }
        return stocks;
    }
}
