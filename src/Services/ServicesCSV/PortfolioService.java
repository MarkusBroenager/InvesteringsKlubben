package Services.ServicesCSV;

import Comparators.HoldingSortBySector;
import Comparators.sortSectorByTotalValueOfPortfolio;
import Models.Interfaces.Asset;
import Models.Model.*;
import Models.Model.Currency;
import Models.Model.PortfolioDKK;
import Repository.Interfaces.*;
import Services.Interfaces.PortfolioServices;

import java.util.*;

public class PortfolioService implements PortfolioServices {

    private CurrencyRepository currencyRepository;
    private StockMarketRepository stockMarketRepository;
    private BondRepository bondRepository;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;
    private HoldingSortBySector holdingSortBySector = new HoldingSortBySector();
    private sortSectorByTotalValueOfPortfolio sortSectorByTotalValueOfPortfolio = new sortSectorByTotalValueOfPortfolio();
    //TODO : Unused variables
    private final static String blue = "\u001B[34m";
    private final static String standard = "\u001B[0m";
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

    //TODO How can we sort by sector if we use bonds and stocks as the interface asset, but bonds lack a sector
    @Override
    public List<String> getCombinedInvestmentPerSector() {
        List<String> sectorList = new ArrayList<>();
        PortfolioDKK portfolio = getCombinedUserPortfolio();
        List<Holding> holdingsSortedBySector = portfolio.getHoldings();
        holdingsSortedBySector.sort(holdingSortBySector);
        double liquidCash = portfolio.getLiquidCash();
        String sector = holdingsSortedBySector.get(0).getSector();
        double sectorInvestment = 0;
        for (Holding holding : holdingsSortedBySector) {
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
        sectorList.sort(sortSectorByTotalValueOfPortfolio);
        return sectorList;
    }

    @Override
    public List<PortfolioDKK> getAllPortfolios() {
        List<PortfolioDKK> allPortfolios = new ArrayList<>();
        for (User user : userRepository.getUsers()) {
            allPortfolios.add(getPortfolio(user.getUserID()));
        }
        return allPortfolios;
    }

    public List<PortfolioDKK> viewProfitAndLossSortedPortfolios(Comparator<PortfolioDKK> comparator) {
        List<PortfolioDKK> portfolios = getAllPortfolios();
        portfolios.sort(comparator);
        return portfolios;
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
                if (asset == null) {
                    asset = bondRepository.getBondFromTicker(k);
                }
                if (asset != null) {
                    Currency currency = currencyRepository.getCurrencyFromBaseCurrency(asset.getCurrency());
                    holdings.add(new Holding(asset, currency, v));
                } else {
                    //TODO
                    // - add holding for asset removed from stockmarket
                }
            }
        });

        Collections.reverse(holdings);
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
        sectorList.add(sector + ": " + String.format("%.2f", sectorInvestment) + " DKK" + ";" +
                String.format("%.2f", portfolio.getPercentageOfPortfolio(sectorInvestment)) + "%");
    }

}
