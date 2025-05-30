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

    private final CurrencyRepository currencyRepository;
    private final StockMarketRepository stockMarketRepository;
    private final BondRepository bondRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private HoldingSortBySector holdingSortBySector = new HoldingSortBySector();
    private sortSectorByTotalValueOfPortfolio sortSectorByTotalValueOfPortfolio = new sortSectorByTotalValueOfPortfolio();

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
        String fullName = userRepository.getUserFromUserID(userID).getFullName();
        return createPortfolio(initialCash, transactionRepository.getAllTransactionsFromUserID(userID), fullName);
    }

    @Override
    public PortfolioDKK getCombinedUserPortfolio() {
        double combinedCash = 0;
        for (User u : userRepository.getUsers()) {
            combinedCash += u.getInitialCash();
        }
        String fullName = "Combined portfolio ";
        return createPortfolio(combinedCash, transactionRepository.getAllTransactions(), fullName);
    }

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

    private PortfolioDKK createPortfolio(double initialCash, List<Transaction> transactions, String fullName) {
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
                }
            }
        });

        Collections.reverse(holdings);
        return new PortfolioDKK(holdings, initialCash, getLiquidCash(transactions, initialCash), fullName);
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
