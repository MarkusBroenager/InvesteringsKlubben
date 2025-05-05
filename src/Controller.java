import Comparators.*;
import Models.Interfaces.*;
import Models.Model.*;
import Models.Model.PortfolioDKK;
import Services.Interfaces.*;
import Services.ServicesCSV.DataServices;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private StockMarketServices stockMarketService;
    private TransactionServices transactionService;
    private UserServices userService;
    private PortfolioServices portfolioService;
    private profitAndLossInPercentageComparator percentageComparator = new profitAndLossInPercentageComparator();
    private profitAndLossInDKKComparator dkkComparator = new profitAndLossInDKKComparator();
    private Scanner scanner = new Scanner(System.in);

    public Controller(StockMarketServices stockMarketService, TransactionServices transactionService,
                      UserServices userService, PortfolioServices portfolioService) {
        this.stockMarketService = stockMarketService;
        this.transactionService = transactionService;
        this.userService = userService;
        this.portfolioService = portfolioService;
    }

    public void start() {
        boolean isRunning = true;
        System.out.println();
        while (isRunning) {
            System.out.println("0 - Exit, 1 - Member, 2 - Leader");
            int userChoice = getUserChoice(2);
            switch (userChoice) {
                case 1:
                    memberUI();
                    break;
                case 2:
                    leaderUI();
                    break;
                default:
                    isRunning = false;
                    break;
            }
        }
    }

    private void memberUI() {
        boolean isRunning = true;
        System.out.println("Enter you memberID");
        int memberID = getUserChoice(1000);
        if (memberID == 0) {
            return;
        }
        while (isRunning) {
            System.out.println("0 - Exit, 1 - View stock market, 2 - View forex market, 3 - Enter new transaction, " +
                    "4 - View your portfolio, 5 - View transaction history, 6 - View stats for all members," +
                    " 7 - View personal information");
            int userChoice = getUserChoice(7);
            switch (userChoice) {
                case 1:
                    viewStockMarket();
                    break;
                case 2:
                    viewForexMarket();
                    break;
                case 3:
                    if (addNewTransaction(memberID)) {
                        System.out.println("Transaction added");
                    } else {
                        System.out.println("Transaction could not be added");
                    }
                    break;
                case 4:
                    viewPortfolio(memberID);
                    break;
                case 5:
                    viewTransactions(memberID);
                    break;
                case 6:
                    break;
                case 7:
                    viewPersonalInformation(memberID);
                    break;
                default:
                    isRunning = false;
                    break;
            }
        }
    }

    private void leaderUI() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("0 - Exit, 1 - View combined portfolio, 2 - View P&L for all portfolios," +
                    " 3 - View sector distribution");
            int userChoice = getUserChoice(4);
            switch (userChoice) {
                case 1:
                    viewCombinedPortfolio();
                    break;
                case 2:
                    System.out.println("0 - Exit, 1 - Sort by percentage, 2 - Sort by DKK");
                    int sortChoice = getUserChoice(2);
                    if (sortChoice == 1) {
                        viewProfitAndLossSortedPortfolios(percentageComparator);
                    } else if (sortChoice == 2) {
                        viewProfitAndLossSortedPortfolios(dkkComparator);
                    }
                    break;
                case 3:
                    viewSectorDistribution();
                    break;
                case 4:
                    System.out.println("e");
                    break;
                default:
                    isRunning = false;
                    break;
            }
        }
    }

    private void viewStockMarket() {
        for (Stocks stock : stockMarketService.getStocks()) {
            System.out.println(stock);
        }
    }

    private void viewForexMarket() {
        for (Currencies currency : stockMarketService.getCurrencyList()) {
            System.out.println(currency);
        }
    }

    private boolean addNewTransaction(int memberID) {
        //TO_DO methods for only getting acceptable inputs (Enums?)
        System.out.println("Enter date of transaction");
        LocalDate dateOfTransaction = getLocalDate();
        System.out.println("Enter ordertype (buy/sell)");
        String orderType = getNonEmptyString();
        System.out.println("Enter ticker");
        String ticker = getNonEmptyString();
        System.out.println("Enter currency");
        String currency = getNonEmptyString();
        System.out.println("Enter price (for 1 stock)");
        double price = getUserInputAsDouble();
        System.out.println("Enter quantity");
        int quantity = getUserChoice(1000000000);
        return transactionService.addNewTransaction(memberID, dateOfTransaction, ticker, price, currency,
                orderType, quantity);
    }

    private void viewPortfolio(int memberID) {
        PortfolioDKK portfolio = portfolioService.getPortfolio(memberID);
        printPortfolio(portfolio);
    }

    private void viewTransactions(int memberID) {
        List<Transaction> transactions = transactionService.getTransactionsForUser(memberID);
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    private void viewPersonalInformation(int memberID) {
        System.out.println(userService.getUser(memberID));
    }

    private void viewCombinedPortfolio() {
        PortfolioDKK portfolio = portfolioService.getCombinedUserPortfolio();
        printPortfolio(portfolio);
    }

    private void printPortfolio(Portfolios portfolio) {
        for (String s : portfolio.getPortfolioInformation()) {
            System.out.println(s);
        }
    }

    private void viewProfitAndLossSortedPortfolios(Comparator comparator) {
        List<PortfolioDKK> portfolios = portfolioService.getAllPortfolios();
        Collections.sort(portfolios, comparator);
        for (Portfolios p : portfolios) {
            for (String s : p.getPortfolioInformation()) {
                System.out.println(s);
            }
            System.out.println();
        }
    }

    private void viewSectorDistribution(){
        for(String s : portfolioService.getCombinedInvestmentPerSector(portfolioService.getCombinedUserPortfolio())){
            System.out.println(s);
        }
    }

    private int getUserChoice(int choiceUpperBoundary) {
        int userInput;

        do {
            //While loop skips every token (non-number input) until there is a number,
            //then the loop ends and that number is saved in userInput
            while (!this.scanner.hasNextInt()) {

                this.scanner.next();

            }
            userInput = this.scanner.nextInt();
            this.scanner.nextLine();
        } while (userInput > choiceUpperBoundary || userInput < 0);

        return userInput;
    }

    private double getUserInputAsDouble() {
        double userInput;

        do {
            //While loop skips every token (non-number input) until there is a number,
            //then the loop ends and that number is saved in userInput
            while (!this.scanner.hasNextDouble()) {

                this.scanner.next();

            }
            userInput = this.scanner.nextDouble();
            this.scanner.nextLine();
        } while (userInput < 0);

        return userInput;

    }

    private String getNonEmptyString() {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.isEmpty() || input.contains(":") || input.contains("/")) {
                continue;
            }
            break;
        }
        return input;
    }

    private LocalDate getLocalDate() {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (DataServices.getLocalDate(input).isAfter(LocalDate.of(2025, 1, 1))) {
                return DataServices.getLocalDate(input);
            }
        }
    }

}
