package Controller;

import Comparators.*;
import Models.Interfaces.*;
import Models.Model.*;
import Models.Model.PortfolioDKK;
import Services.Interfaces.*;
import Services.ServicesCSV.DataServices;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private StockMarketServices stockMarketService;
    private TransactionServices transactionService;
    private UserServices userService;
    private PortfolioServices portfolioService;
    private ProfitAndLossInPercentageComparator percentageComparator = new ProfitAndLossInPercentageComparator();
    private ProfitAndLossInDKKComparator dkkComparator = new ProfitAndLossInDKKComparator();
    private final Scanner SCANNER = new Scanner(System.in);
//TODO improve string names
    private final static String blue = "\u001B[34m";
    private final static String standard = "\u001B[0m";

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

            printMenu(new String[]{"_________", "1 - Member", "2 - Leader", "0 - Exit", "_________"});




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

    private void printMenu(String[] menuPoints){
        for(int index = 0; index < menuPoints.length; index++) {
            System.out.println(menuPoints[index]);
        }
    }

    private void memberUI() { //
        boolean isRunning = true;
        System.out.println("Enter you memberID");
        int memberID = getUserChoice(1000);
        if (memberID == 0) {
            return;
        }
        while (isRunning) {

            System.out.println("Hej " + userService.getUser(memberID).getFullName() + "! Velkommen tilbage");
            printMenu(new String[]{"1 - View stock market", "2 - View forex market", "3 - Enter new transaction",
                    "4 - View your portfolio", "5 - View transaction history", "6 - View stats for all members",
                    "7 - View personal information", "0 - Exit"});
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
                    System.out.println("Nope");
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
                    " 3 - View sector distribution, 4 - Add new member, 5 - View all members");
            int userChoice = getUserChoice(5);
            switch (userChoice) {
                case 1:
                    viewCombinedPortfolio();
                    break;
                case 2:
                    System.out.println("0 - Exit, 1 - Sort by percentage, 2 - Sort by DKK");
                    int sortChoice = getUserChoice(2);
                    if (sortChoice == 1) {
                        viewPortfoliosSortedByPercentage();
                    } else if (sortChoice == 2) {
                        viewPortfoliosSortedByDKK();
                    }
                    break;
                case 3:
                    viewSectorDistribution();
                    break;
                case 4:
                    System.out.println("Enter full name: ");
                    String fullName = getNonEmptyString();
                    System.out.println("Enter email: ");
                    String email = getNonEmptyString();
                    System.out.println("Enter birthday(year-month-day): ");
                    LocalDate birthday = DataServices.getLocalDate(getNonEmptyString());
                    System.out.println("Enter initial cash: ");
                    double initialCash = getUserInputAsDouble();
                    if (addNewUser(fullName, email, birthday, initialCash)) {
                        System.out.println("Member added");

                    } else {
                        System.out.println("Could not add member");
                    }
                    break;
                case 5:
                    viewAllUsers();
                    break;
                default:
                    isRunning = false;
                    break;
            }
        }
    }

    private void viewStockMarket() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("0 - Exit, 1 - View all prices in DKK, 2 - View in native currency");
            int userChoice = getUserChoice(3);
            switch (userChoice) {
                case 1:
                    viewStocksInDKK();
                    break;
                case 2:
                    viewStocks();
                    break;
                default:
                    isRunning = false;
                    break;
            }
        }
    }


    private void viewStocksInDKK() {
        for (Stocks stock : stockMarketService.getStocksInDKK()) {
            System.out.println(stock);
        }
    }

    private void viewStocks() {
        int test = 10;
        System.out.printf("\n" + blue + "__________________________________________\n| %-" + test + "s | %-14s | %-8s | %-7s |\n__________________________________________\n" + standard,"Ticker", "Pris per aktie", "dividend", "rating");
        for (Stocks stock : stockMarketService.getStocks()) {
            System.out.printf("| %-" + test + "s | %-14s | %-8.2f | %-3s | %-15s | %-10s\n", stock.getTicker(), stock.getPrice(), 1.60, "AA", "Health sector", "01-05-2025");
        }
    }

    private void viewForexMarket() {
        for (Currencies currency : stockMarketService.getCurrencyList()) {
            System.out.println(currency);
        }
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
        System.out.println(portfolio);
        for (String s : portfolio.getPortfolioInformation()) {
            System.out.println(s);
        }
    }

    private void viewPortfoliosSortedByPercentage(){
        viewProfitAndLossSortedPortfolios(percentageComparator);
    }

    private void viewPortfoliosSortedByDKK(){
        viewProfitAndLossSortedPortfolios(dkkComparator);
    }

    private void viewProfitAndLossSortedPortfolios(Comparator<PortfolioDKK> comparator) {
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

    private void viewSectorDistribution() {
        for (String s : portfolioService.getCombinedInvestmentPerSector()) {
            System.out.println(s);
        }
    }

    private void viewAllUsers(){
        for(User u : userService.getUsers()){
            System.out.println(u);
        }
    }

    private boolean addNewUser(String fullName, String email, LocalDate birthday, double initialCash){
        return(userService.addNewUser(fullName,email,birthday,initialCash));
    }

    private int getUserChoice(int choiceUpperBoundary) {
        int userInput;

        do {
            //While loop skips every token (non-number input) until there is a number,
            //then the loop ends and that number is saved in userInput
            while (!this.SCANNER.hasNextInt()) {

                this.SCANNER.next();

            }
            userInput = this.SCANNER.nextInt();
            this.SCANNER.nextLine();
        } while (userInput > choiceUpperBoundary || userInput < 0);

        return userInput;
    }

    private double getUserInputAsDouble() {
        double userInput;

        do {
            //While loop skips every token (non-number input) until there is a number,
            //then the loop ends and that number is saved in userInput
            while (!this.SCANNER.hasNextDouble()) {

                this.SCANNER.next();

            }
            userInput = this.SCANNER.nextDouble();
            this.SCANNER.nextLine();
        } while (userInput < 0);

        return userInput;

    }

    private String getNonEmptyString() {
        String input;
        while (true) {
            input = SCANNER.nextLine();
            if (input.isEmpty() || input.contains(";")) {
                continue;
            }
            break;
        }
        return input;
    }

    private LocalDate getLocalDate() {
        String input;
        while (true) {
            input = SCANNER.nextLine();
            if (DataServices.getLocalDate(input).isAfter(LocalDate.of(2025, 1, 1))) {
                return DataServices.getLocalDate(input);
            }
        }
    }

    private boolean addNewTransaction(int memberID) {
        //TO_DO methods for only getting acceptable inputs (Enums?)
        System.out.println("Enter date of transaction(Year-Month-Day)");
        LocalDate dateOfTransaction = getLocalDate();
        System.out.println("Enter order type (buy/sell)");
        String orderType = getNonEmptyString();
        System.out.println("Enter ticker");
        String ticker = getNonEmptyString();
        System.out.println("Enter currency");
        String currency = getNonEmptyString();
        System.out.println("Enter price (for 1 share)");
        double price = getUserInputAsDouble();
        System.out.println("Enter quantity");
        int quantity = getUserChoice(1000000000);
        return (addNewTransaction(memberID, dateOfTransaction, ticker, price, currency,
                orderType, quantity));
    }

    private boolean addNewTransaction(int memberID,LocalDate dateOfTransaction,String ticker,double price,String currency,
                                      String orderType,int quantity) {
        return transactionService.addNewTransaction(memberID, dateOfTransaction, ticker, price, currency,
                orderType, quantity);
    }

}
