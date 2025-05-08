package Controller;

import Comparators.*;
import Models.Interfaces.*;
import Models.Model.*;
import Models.Model.PortfolioDKK;
import Services.Interfaces.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Controller {

    private StockMarketServices stockMarketService;
    private TransactionServices transactionService;
    private UserServices userService;
    private PortfolioServices portfolioService;
    private ProfitAndLossInPercentageComparator percentageComparator = new ProfitAndLossInPercentageComparator();
    private ProfitAndLossInDKKComparator dkkComparator = new ProfitAndLossInDKKComparator();

    public Controller(StockMarketServices stockMarketService, TransactionServices transactionService,
                      UserServices userService, PortfolioServices portfolioService) {
        this.stockMarketService = stockMarketService;
        this.transactionService = transactionService;
        this.userService = userService;
        this.portfolioService = portfolioService;
    }


public void viewStocksInDKK(){
    for (Stocks stock : stockMarketService.getStocksInDKK()) {
        System.out.println(stock);
    }
}

public void viewStocks(){
    for (Stocks stock : stockMarketService.getStocks()) {
        System.out.println(stock);
    }
}

    public void viewForexMarket() {
        for (Currencies currency : stockMarketService.getCurrencyList()) {
            System.out.println(currency);
        }
    }

    public boolean addNewTransaction(int memberID,LocalDate dateOfTransaction,String ticker,double price,String currency,
                                    String orderType,int quantity) {
        return transactionService.addNewTransaction(memberID, dateOfTransaction, ticker, price, currency,
                orderType, quantity);
    }

    public void viewPortfolio(int memberID) {
        PortfolioDKK portfolio = portfolioService.getPortfolio(memberID);
        printPortfolio(portfolio);
    }

    public void viewTransactions(int memberID) {
        List<Transaction> transactions = transactionService.getTransactionsForUser(memberID);
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public void viewPersonalInformation(int memberID) {
        System.out.println(userService.getUser(memberID));
    }

    public void viewCombinedPortfolio() {
        PortfolioDKK portfolio = portfolioService.getCombinedUserPortfolio();
        printPortfolio(portfolio);
    }

    public void printPortfolio(Portfolios portfolio) {
        System.out.println(portfolio);
        for (String s : portfolio.getPortfolioInformation()) {
            System.out.println(s);
        }
    }

    public void viewPortfoliosSortedByPercentage(){
        viewProfitAndLossSortedPortfolios(percentageComparator);
    }

    public void viewPortfoliosSortedByDKK(){
        viewProfitAndLossSortedPortfolios(dkkComparator);
    }

    public void viewProfitAndLossSortedPortfolios(Comparator<PortfolioDKK> comparator) {
        List<PortfolioDKK> portfolios = portfolioService.getAllPortfolios();
        portfolios.sort(comparator);
        for (Portfolios p : portfolios) {
            System.out.println(p);
            for (String s : p.getPortfolioInformation()) {
                System.out.println(s);
            }
            System.out.println();
        }
    }

    public void viewSectorDistribution() {
        for (String s : portfolioService.getCombinedInvestmentPerSector(portfolioService.getCombinedUserPortfolio())) {
            System.out.println(s);
        }
    }

    public void viewAllUsers(){
        for(User u : userService.getUsers()){
            System.out.println(u);
        }
    }

    public boolean addNewUser(String fullName, String email, LocalDate birthday, double initialCash){
        return(userService.addNewUser(fullName,email,birthday,initialCash));
    }
}
