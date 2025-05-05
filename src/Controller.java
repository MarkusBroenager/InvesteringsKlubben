import Comparators.profitAndLossInDKKComparator;
import Comparators.profitAndLossInPercentageComparator;
import Models.Interfaces.Portfolios;
import Models.Model.Currency;
import Models.Model.Stock;
import Models.Model.Transaction;
import Services.Interfaces.PortfolioServices;
import Services.Interfaces.StockMarketServices;
import Services.Interfaces.TransactionServices;
import Services.Interfaces.UserServices;
import Services.ServicesCSV.DataServices;

import java.time.LocalDate;
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
            int userChoice = getUserChoice(2, 0);
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
        int memberID = getUserChoice(1000, 0);
        if (memberID == 0) {
            return;
        }
        while (isRunning) {
            System.out.println("0 - Exit, 1 - View stock market, 2 - View forex market, 3 - Enter new transaction, " +
                    "4 - View your portfolio, 5 - View transaction history, 6 - View stats for all members");
            int userChoice = getUserChoice(6, 0);
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
                    break;
                case 6:
                    break;
                default:
                    isRunning = false;
                    break;
            }
        }
    }

    private void leaderUI() {

    }

    private void viewStockMarket() {
        for (Stock stock : stockMarketService.getStocks()) {
            System.out.println(stock);
        }
        return;
    }

    private void viewForexMarket() {
        for (Currency currency : stockMarketService.getCurrencyList()) {
            System.out.println(currency);
        }
        return;
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
        int quantity = getUserChoice(1000000000, 0);
        if (transactionService.addNewTransaction(memberID, dateOfTransaction, ticker, price, currency,
                orderType, quantity)) {
            return true;
        }
        return false;
    }

    private void viewPortfolio(int memberID) {
        Portfolios portfolio = portfolioService.getPortfolio(memberID);
        for (String s : portfolio.getPortfolioInformation()) {
            System.out.println(s);
        }
    }

    private void viewTransactions(int memberID){
       List<Transaction> transactions = transactionService.getTransactionsForUser(memberID);
       for(Transaction t : transactions){
           System.out.println(t);
       }
    }

    private int getUserChoice(int choiceUpperBoundary, int choiceLowerBoundary) {
        int userInput;

        do {
            //While loop skips every token (non-number input) until there is a number,
            //then the loop ends and that number is saved in userInput
            while (!this.scanner.hasNextInt()) {

                this.scanner.next();

            }
            userInput = this.scanner.nextInt();
            this.scanner.nextLine();
        } while (userInput > choiceUpperBoundary || userInput < choiceLowerBoundary);

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
            if (DataServices.getLocalDate(input).isAfter(LocalDate.of(2025, 01, 01))) {
                return DataServices.getLocalDate(input);
            }
        }
    }

}
