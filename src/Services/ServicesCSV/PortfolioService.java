package Services.ServicesCSV;

import Models.Interfaces.Portfolios;
import Models.Model.Holding;
import Models.Model.*;
import Repository.Interfaces.CurrencyRepository;
import Repository.Interfaces.StockMarketRepository;
import Repository.Interfaces.TransactionRepository;
import Repository.Interfaces.UserRepository;
import Services.Interfaces.PortfolioServices;
import Services.Interfaces.TransactionServices;

import java.util.ArrayList;
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
    public Portfolios getPortfolio(int userID) {
        double initialCash = userRepository.getUserFromUserID(userID).getInitialCash();
        return createPortfolio(initialCash, transactionRepository.getAllTransactionsFromUserID(userID));
    }

    @Override
    public Portfolios getCombinedUserPortfolio() {
        List<User> users = userRepository.getUsers();
        double combinedCash = 0;
        for (User u : users) {
            combinedCash += u.getInitialCash();
        }
        return createPortfolio(combinedCash, transactionRepository.getAllTransactions());
    }

    private Portfolio createPortfolio(double initialCash, List<Transaction> transactions) {
        List<String> tickerAndQuantity = getTickerAndQuantity(transactions);
        List<Holding> holdings = new ArrayList<>();
        for (String line : tickerAndQuantity) {
            String[] lineSplit = line.split(";");
            Stock stock = stockMarketRepository.getStockFromTicker(lineSplit[0]);
            Currency currency = currencyRepository.getCurrencyFromBaseCurrency(stock.getCurrency());
            holdings.add(new Holding(stock, currency, Integer.parseInt(lineSplit[1])));
        }
        Portfolio portfolio = new Portfolio(holdings, initialCash, getLiquidCash(transactions,initialCash));
        return portfolio;
    }

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
        List<Stock> stocks = stockMarketRepository.getStockList();
        for (Stock s : stocks) {
            if (tickerAndQuantity.get(s.getTicker()) != null) {
                listOfTickerAndQuantity.add(s.getTicker() + ";" +
                        tickerAndQuantity.get(s.getTicker()));
            }
        }
        return listOfTickerAndQuantity;
    }

    private double getLiquidCash(List<Transaction> transactions,double initialCash) {
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
