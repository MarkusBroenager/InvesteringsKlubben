package Services.ServicesCSV;

import Models.Interfaces.Asset;
import Models.Interfaces.Portfolios;
import Models.Model.*;
import Models.Model.PortfolioDKK;
import Repository.Interfaces.*;
import Services.Interfaces.PortfolioServices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PortfolioService implements PortfolioServices {

    private CurrencyRepository currencyRepository;
    private StockMarketRepository stockMarketRepository;
    private BondRepository bondRepository;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;
    //TODO: Skal portfolioServicen intereager med repositories eller service klaserne

    public PortfolioService(CurrencyRepository currencyRepository, StockMarketRepository stockMarketRepository,
                            BondRepository bondRepository, TransactionRepository transactionRepository,
                            UserRepository userRepository) {
        this.currencyRepository = currencyRepository;
        this.stockMarketRepository = stockMarketRepository;
        this.bondRepository = bondRepository;
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
        double combinedCash = 0;
        for (User u : userRepository.getUsers()) {
            combinedCash += u.getInitialCash();
        }
        return createPortfolio(combinedCash, transactionRepository.getAllTransactions());
    }

    //TODO Method is pointless
    @Override
    public List<String> getCombinedInvestmentPerSector() {
        List<String> sectorList = new ArrayList<>();
        PortfolioDKK portfolio = getCombinedUserPortfolio();
        List<Holding> holdings = portfolio.getHoldings();
        double liquidCash = portfolio.getLiquidCash();
        String sector = holdings.get(0).getSector();
        double sectorInvestment = 0;
        sectorList.add("Total cash: " + String.format("%.2f", liquidCash) + " DKK Percentage of portfolio: " +
                String.format("%.2f", portfolio.getPercentageOfPortfolio(liquidCash)) + "%");
        for (Holding holding : holdings) {
            if (sector.equalsIgnoreCase(holding.getSector())) {
                sectorInvestment += holding.getValueOfHoldingInDKK();
            } else if (!sector.equalsIgnoreCase(holding.getSector())) {
                if (sectorInvestment > 0) {
                    addToSectorList(sectorList, sector, sectorInvestment, portfolio);
                }
                sector = holding.getSector();
                sectorInvestment = holding.getValueOfHoldingInDKK();
            }
        }
        if (!sectorList.contains(sector)) {
            addToSectorList(sectorList, sector, sectorInvestment, portfolio);
        }
        return sectorList;
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


    //TODO
    // - move creation on holding objects from createPortfolio() and into the PortfolioDKK model.
    // - test TO DO function
    // - remove empty entries in hashmap? (is currently never used)
    private PortfolioDKK createPortfolio(double initialCash, List<Transaction> transactions) {
        HashMap<String, Integer> tickerAndQuantity = getTickerAndQuantity(transactions);
        List<Holding> holdings = new ArrayList<>();
        tickerAndQuantity.forEach((k, v) -> {
            if (tickerAndQuantity.get(k) > 0) {
                Asset asset = stockMarketRepository.getStockFromTicker(k);
                if(asset == null){
                    asset = bondRepository.getBondFromTicker(k);
                }
                Currency currency = currencyRepository.getCurrencyFromBaseCurrency(asset.getCurrency());
                holdings.add(new Holding(asset, currency, v));
            }
            /*else{
                tickerAndQuantity.remove(k);
            }*/
        });

        Collections.sort(holdings);
        return new PortfolioDKK(holdings, initialCash, getLiquidCash(transactions, initialCash));
    }

    private HashMap<String, Integer> getTickerAndQuantity(List<Transaction> transactions) {
        HashMap<String, Integer> tickerAndQuantity = new HashMap<>();
        for (Transaction t : transactions) {
            tickerAndQuantity.putIfAbsent(t.getTicker(), 0);
            if (t.getOrderType().equalsIgnoreCase("buy")) {
                tickerAndQuantity.replace(t.getTicker(), tickerAndQuantity.get(t.getTicker()) + t.getQuantity());
            } else if (t.getOrderType().equalsIgnoreCase("sell")) {
                tickerAndQuantity.replace(t.getTicker(), tickerAndQuantity.get(t.getTicker()) - t.getQuantity());
            }
        }


        return tickerAndQuantity;
    }

    //TODO Kan den rykkes i PortfolioDKK klassen?
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

    private void addToSectorList(List<String> sectorList, String sector, double sectorInvestment, PortfolioDKK portfolio) {
        sectorList.add("Total invested in: " + sector + " is: " + String.format("%.2f", sectorInvestment) +
                " DKK Percentage of total investment: " +
                String.format("%.2f", portfolio.getPercentageOfPortfolio(sectorInvestment)) + "%");
    }

}
