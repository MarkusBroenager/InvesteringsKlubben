package Services.ServicesCSV;

import Models.Model.*;
import Models.Model.PortfolioDKK;
import Repository.Interfaces.CurrencyRepository;
import Repository.Interfaces.StockMarketRepository;
import Repository.Interfaces.TransactionRepository;
import Repository.Interfaces.UserRepository;
import Services.Interfaces.PortfolioServices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PortfolioService implements PortfolioServices {

    private CurrencyRepository currencyRepository;
    private StockMarketRepository stockMarketRepository;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    public PortfolioService(CurrencyRepository currencyRepository, StockMarketRepository stockMarketRepository,
                            TransactionRepository transactionRepository, UserRepository userRepository) {
        this.currencyRepository = currencyRepository;
        this.stockMarketRepository = stockMarketRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PortfolioDKK getPortfolio(int userID) {
        double initialCash = userRepository.getUserFromUserID(userID).getInitialCash();
        return createPortfolio(initialCash, transactionRepository.getAllTransactionsFromUserID(userID));
    }

    @Override
    public PortfolioDKK getCombinedUserPortfolio() {
        List<User> users = userRepository.getUsers();
        double combinedCash = 0;
        for (User u : users) {
            combinedCash += u.getInitialCash();
        }
        return createPortfolio(combinedCash, transactionRepository.getAllTransactions());
    }

    @Override
    public List<String> getCombinedInvestmentPerSector(PortfolioDKK portfolio) {
        return portfolio.getSectorDistribution();
    }

    @Override
    public List<String> getCombinedInvestmentPerStock() {
        return List.of();
    }

    @Override
    public List<PortfolioDKK> getAllPortfolios() {
        List<PortfolioDKK> allPortfolios = new ArrayList<>();
        for (User user : userRepository.getUsers()) {
            allPortfolios.add(getPortfolio(user.getUserID()));
        }
        return allPortfolios;
    }

    private PortfolioDKK createPortfolio(double initialCash, List<Transaction> transactions) {
        List<String> tickerAndQuantity = getTickerAndQuantity(transactions);
        List<Holding> holdings = new ArrayList<>();
        for (String line : tickerAndQuantity) {
            String[] lineSplit = line.split(";");
            Stock stock = stockMarketRepository.getStockFromTicker(lineSplit[0]);
            Currency currency = currencyRepository.getCurrencyFromBaseCurrency(stock.getCurrency());
            holdings.add(new Holding(stock, currency, Integer.parseInt(lineSplit[1])));
        }
        Collections.sort(holdings);
        return new PortfolioDKK(holdings, initialCash, getLiquidCash(transactions, initialCash));
    }

    //Kan getTickerAndQuantity() gøres effektivt.
    private List<String> getTickerAndQuantity(List<Transaction> transactions) {
        List<String> listOfTickerAndQuantity = new ArrayList<>();
        HashMap<String, Integer> tickerAndQuantity = new HashMap<>();
        for (Transaction t : transactions) {
            tickerAndQuantity.putIfAbsent(t.getTicker(), 0);
            if (t.getOrderType().equalsIgnoreCase("buy")) {
                tickerAndQuantity.replace(t.getTicker(), tickerAndQuantity.get(t.getTicker()) + t.getQuantity());
            } else if (t.getOrderType().equalsIgnoreCase("sell")) {
                tickerAndQuantity.replace(t.getTicker(), tickerAndQuantity.get(t.getTicker()) - t.getQuantity());
            }
        }
        tickerAndQuantity.forEach((k, v) -> {
            if (tickerAndQuantity.get(k) > 0) {
                listOfTickerAndQuantity.add(k + ";" + v);
            }
        });

        return listOfTickerAndQuantity;
    }

    private double getLiquidCash(List<Transaction> transactions, double initialCash) {
        double liquidCash = initialCash;
        for (Transaction t : transactions) {
            if (t.getOrderType().equalsIgnoreCase("buy")) {
                liquidCash -= t.getTotalTransactionPrice();
            } else if (t.getOrderType().equalsIgnoreCase("sell")) {
                liquidCash += t.getTotalTransactionPrice();
            }
        }
        return liquidCash;
    }

}
