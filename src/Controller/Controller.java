package Controller;

import Comparators.ProfitAndLossInDKKComparator;
import Comparators.ProfitAndLossInPercentageComparator;
import Models.Interfaces.*;
import Models.Model.*;
import Services.Interfaces.*;
import Services.ServicesCSV.DataServices;
import Services.ServicesCSV.ColorService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    //TODO :
    // - Check all comments
    private final StockMarketServices stockMarketService;
    private final TransactionServices transactionService;
    private final UserServices userService;
    private final PortfolioServices portfolioService;
    private final ProfitAndLossInPercentageComparator percentageComparator = new ProfitAndLossInPercentageComparator();
    private final ProfitAndLossInDKKComparator dkkComparator = new ProfitAndLossInDKKComparator();
    private final Scanner SCANNER = new Scanner(System.in);
//TODO improve string names
// - Show profit and losses in red if negativ and green otherwise

    private final String BLUE;
    private final String STANDARD;
    private final String RED_BACKGROUND;
    private final String TABLE_SEPERATOR;

    public Controller(StockMarketServices stockMarketService, TransactionServices transactionService,
                      UserServices userService, PortfolioServices portfolioService) {
        this.stockMarketService = stockMarketService;
        this.transactionService = transactionService;
        this.userService = userService;
        this.portfolioService = portfolioService;
        //setting colors
        this.BLUE = ColorService.getBlueColor();
        this.STANDARD = ColorService.getStandardColor();
        this.RED_BACKGROUND = ColorService.getRedBackgroundColor();
        this.TABLE_SEPERATOR = BLUE + "|" + STANDARD;
    }

    public void start() {
        boolean isRunning = true;
        System.out.println();
        while (isRunning) {
            //Clear messaging
            printMenu(new String[]{"1 - Login as Member", "2 - Login as Leader", "0 - Exit program"});


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

    private int calculateLongestString(String[] strings){
        int lengthOfString = 0;
        for (int index = 0; index < strings.length; index++) {
            if(strings[index].length() > lengthOfString){
                lengthOfString = strings[index].length();
            }
        }
        return lengthOfString;
    }
    private void printMenu(String[] menuPoints) {
        //calculate length
        int lengthOfMenu = calculateLongestString(menuPoints);
        printLine(lengthOfMenu + 4);
        System.out.print('\n');
        //print entries
        for (int index = 0; index < menuPoints.length - 1; index++) {
            System.out.printf(TABLE_SEPERATOR + " %-" + lengthOfMenu + "s " + TABLE_SEPERATOR + '\n',menuPoints[index]);
        }
        //print exit/back option
        System.out.print(TABLE_SEPERATOR + ' ');
        printLine(lengthOfMenu, '-');
        System.out.print(' ' + TABLE_SEPERATOR + '\n');
        System.out.printf(TABLE_SEPERATOR + " %-" + lengthOfMenu + "s " + TABLE_SEPERATOR + '\n',menuPoints[menuPoints.length - 1]);
        printLine(lengthOfMenu + 4);
        System.out.print('\n');
    }

    private void memberUI() { //
        boolean isRunning = true;
        System.out.print("Enter you member ID to login:");
        int memberID = getUserChoice(userService.getHighestUserId(),true);
        if (memberID == 0) {
            return;
        }
        System.out.println("Hey " + userService.getUser(memberID).getFullName() + "! Welcome back");
        while (isRunning) {

            printMenu(new String[]{"1 - View stock market", "2 - View bond market", "3 - View forex market",
                    "4 - View your portfolio", "5 - Enter new transaction", "6 - View transaction history",
                    "7 - View personal information", "0 - Exit"});
            int userChoice = getUserChoice(7);
            switch (userChoice) {
                case 1:
                    viewStockMarket();
                    break;
                case 2:
                    viewBondMarket();
                    break;
                case 3:
                    viewForexMarket();
                    break;
                case 4:
                    viewPortfolio(memberID);
                    break;
                case 5:
                    if (addNewTransaction(memberID)) {
                        System.out.println("Transaction added");
                    } else {
                        System.out.println("Transaction could not be added");
                    }
                    break;
                case 6:
                    viewTransactions(memberID);
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
            printMenu(new String[]{"1 - View P&L for all members", "2 - View stock distribution",
                    "3 - View sector distribution", "4 - Add new member", "5 - View all members", "0 - Exit"});
            int userChoice = getUserChoice(5);
            switch (userChoice) {
                case 1:
                    printMenu(new String[]{"1 - Sort by percentage", "2 - Sort by DKK", "0 - Exit"});
                    int sortChoice = getUserChoice(2);
                    if (sortChoice == 1) {
                        printSortedPortfolios(portfolioService.viewProfitAndLossSortedPortfolios(percentageComparator));
                    } else if (sortChoice == 2) {
                        printSortedPortfolios(portfolioService.viewProfitAndLossSortedPortfolios(dkkComparator));
                    }
                    break;
                case 2:
                    viewCombinedPortfolio();
                    break;
                case 3:
                    viewSectorDistribution();
                    break;
                case 4:
                    addNewUser();
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

    private void addNewUser(){
        double initialCash;
        System.out.print("Enter full name: ");
        String fullName = getValidName();
        System.out.print("Enter email: ");
        String email = getValidEmail();
        System.out.print("Enter birthday(year-month-day): ");
        LocalDate birthday = getValidBirthday();
        System.out.print("Enter initial cash: ");
        do {
            initialCash = getUserInputAsDouble();
            if(initialCash < 10000) {
                System.out.print(ColorService.colorText("---Your input of (" + initialCash + ") is to low---", RED_BACKGROUND) + "\nPlease type a different starting balance above 10,000 DKK:");
            }
        }while(initialCash < 10000);
        if (userService.addNewUser(fullName, email, birthday, initialCash)) {
            System.out.println("Member added");

        } else {
            System.out.println("Could not add member");
        }
    }

    private void printSortedPortfolios(List<PortfolioDKK> portfolios) {
        for (PortfolioDKK p : portfolios) {
            printPortfolio(p);
        }
    }

    private void viewStockMarket() {
        boolean isRunning = true;
        while (isRunning) {
            printMenu(new String[] {"1 - View all prices in DKK", "2 - View in native currency", "0 - Exit"});
            int userChoice = getUserChoice(2);
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

    //Hvad gør metoden anderledes end viewStocks?
    private void viewStocksInDKK() {
        List<String> stockLines = new ArrayList<>();
        for (Stock stock : stockMarketService.getStocksInDKK()) {
            stockLines.add(stock.toString());
        }
        printTable(stockLines, "Name,Sector,Price,Rating,Dividend,Market,Last updated");
    }

    private void viewBondMarket() {
        List<String> bondList = new ArrayList<>();
        for (Bond bond : stockMarketService.getBonds()) {
            bondList.add(bond.toString());
        }
        printTable(bondList, "Name, Price, Coupon Rate, Maturity Date, Exchange, Rating, Last Updated ");
    }

    private void viewStocks() {
        List<String> stockLines = new ArrayList<>();
        for (Stock stock : stockMarketService.getStocks()) {
            stockLines.add(stock.toString());
        }
        printTable(stockLines, "Name,Sector,Price,Rating,Dividend,Market,Last updated");
    }

    private void viewForexMarket() {
        List<String> forexMarket = new ArrayList<>();
        for (Currency currency : stockMarketService.getCurrencyList()) {
            forexMarket.add(currency.toString());
        }
        printTable(forexMarket, "Currency, Last updated");
    }


    private void viewPortfolio(int memberID) {
        //Bedre UI?
        PortfolioDKK portfolio = portfolioService.getPortfolio(memberID);
        printPortfolio(portfolio);
    }

    private void viewTransactions(int memberID) {
        List<String> transactionsList = new ArrayList<>();
        List<Transaction> transactions = transactionService.getTransactionsForUser(memberID);
        for (Transaction t : transactions) {
            transactionsList.add(t.toString());
        }
        printTable(transactionsList, "Order type, quantity, ticker, currency, dateOfTransactions");
    }

    private void viewPersonalInformation(int memberID) {
        System.out.println();
        System.out.println(userService.getUser(memberID));
        System.out.println();
    }

    private void viewCombinedPortfolio() {
        PortfolioDKK portfolio = portfolioService.getCombinedUserPortfolio();
        printPortfolio(portfolio);
    }

    private void printPortfolio(PortfolioDKK portfolio) {
        System.out.println(portfolio);
        if(!portfolio.getPortfolioInformation().isEmpty()) {
            printTable(portfolio.getPortfolioInformation(), "Holding,Price,quantity,value,Percent of total value");
        }
        System.out.print("\n\n");
    }

    private void viewSectorDistribution() {
        //Bør vi visse liquid cash
        PortfolioDKK combinedPortfolio = portfolioService.getCombinedUserPortfolio();
        System.out.println(combinedPortfolio);
        List<String> distributionList = new ArrayList<>();
        distributionList.addAll(portfolioService.getCombinedInvestmentPerSector());
        printTable(distributionList, "Total sector investment, percentage of total investment");
    }

    private void viewAllUsers() {
        List<String> userLines = new ArrayList<>();
        for (User u : userService.getUsers()) {
            userLines.add(u.otherToString() + ";" +
                    String.format("%.2f", portfolioService.getPortfolio(u.getUserID()).getPortfolioValueInDKK()) + " DKK");
        }
        printTable(userLines, "Full name,User ID, Birthday, Email, Started investing in,Initial investment,Last update,Current portfolio value");
    }
    private int getUserChoice(int choiceUpperBoundary) {
        return getUserChoice(choiceUpperBoundary,false);
    }

    private int getUserChoice(int choiceUpperBoundary, boolean hidden) {
        int userInput;
        boolean isValidChoice;
        String input;
        do {
            //While loop skips every token (non-number input) until there is a number,
            //then the loop ends and that number is saved in userInput
            while (!this.SCANNER.hasNextInt()) {

                input = this.SCANNER.nextLine();
                //Printing error message
                if(!input.matches("[0-" + choiceUpperBoundary + "]+$")){
                    String message;
                    if(hidden){
                        message = ColorService.colorText("---Your input of (" + input + ") cannot be accepted---", RED_BACKGROUND) + "\nPlease type your user ID:";
                    }else {
                        message = ColorService.colorText("---You can choose between 0 and " + choiceUpperBoundary + " Your choice of (" +
                                input + ") is therefore not valid---", RED_BACKGROUND) + "\nPlease type a number between 0 and " +
                                choiceUpperBoundary + ":";
                    }
                    System.out.println(message);
                }

            }
            userInput = this.SCANNER.nextInt();
            this.SCANNER.nextLine();
            isValidChoice = userInput <= choiceUpperBoundary && userInput >= 0;
            if(!isValidChoice){
                String message;
                if(hidden){
                    message = ColorService.colorText("---Your input of (" + userInput + ") cannot be accepted", RED_BACKGROUND) + "\nPlease type your user ID---";
                }else {
                    message = ColorService.colorText("---You can choose between 0 and " + choiceUpperBoundary + ". Your choice of (" +
                            userInput + ") is therefore not valid---", RED_BACKGROUND) + "\nPlease type a number between 0 and " +
                            choiceUpperBoundary + ":";
                }
                System.out.println(message);
            }
        } while (userInput > choiceUpperBoundary || userInput < 0);

        return userInput;
    }

    private double getUserInputAsDouble() {
        double userInput;
        String input;
        do {
            //While loop skips every token (non-number input) until there is a number,
            //then the loop ends and that number is saved in userInput
            while (!this.SCANNER.hasNextDouble()) {

                input = this.SCANNER.next();
                //Printing error message
                System.out.print(ColorService.colorText("---Your input of (" + input + ") cannot be accepted---", RED_BACKGROUND) + "\nPlease type a different decimal number:");


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
            LocalDate date = DataServices.getLocalDate(input);
            //TODO: is this an interresting check
            if (!date.isAfter(LocalDate.now()) && date.isAfter(LocalDate.now().minusYears(120))) {
                return DataServices.getLocalDate(input);
            }else{
                System.out.print(ColorService.colorText("--This birthdate (" + input + ") is to old, in the future, or otherwise not" +
                        " valid---", RED_BACKGROUND) +"\nPlease type a different birthDate:");
            }
        }
    }
    //TODO Burde vi overhoved bruge matches metoden
    private String getValidName() {
        String input;
        boolean isValidName;
        do {
            input = getNonEmptyString();
            isValidName = input.matches("[a-zA-ZæøåÆØÅ ]+$");
            if(!isValidName){
                System.out.print(ColorService.colorText("---You may only use characters from the danish alphabet, therefore ("  + input +
                        ") is not accepted---", RED_BACKGROUND) + "\nPlease type a different name:");
            }
        } while (!isValidName);
        return input;
    }

    private String getValidEmail() {
        String input;
        String acceptedCharacters = "[0-9a-zA-ZæøåÆØÅ._%+-]";
        boolean isValidEmail;
        do {
            input = getNonEmptyString();
            isValidEmail = input.matches(acceptedCharacters + "+@" + acceptedCharacters + "+\\." + acceptedCharacters + "+$");
            if(!isValidEmail){
                System.out.print(ColorService.colorText("---You may only use characters from the danish alphabet in the form (example@domain.host), therefore ("  + input +
                        ") is not accepted---", RED_BACKGROUND) + "\nPlease type a different email address:");
            }
        } while (!isValidEmail);
        return input;
    }

    private LocalDate getValidBirthday() {
        LocalDate input;
        boolean isValid;
        do {
            input = getLocalDate();
            isValid = input.isBefore(LocalDate.now().minusYears(18));
            if(!isValid){
                System.out.print(ColorService.colorText("---This Person is not over 18---", RED_BACKGROUND) +
                        "\nPlease type a different birthdate:");
            }
        } while (!isValid);
        return input;
    }

    private boolean addNewTransaction(int memberID) {
        //TODO methods for only getting acceptable inputs (Enums?)
        System.out.print("Enter order type (buy/sell):");
        String orderType = getTransactionType();
        System.out.print("Enter ticker:");
        String ticker = getValidTicker();
        System.out.print("Enter quantity:");
        int quantity = getUserChoice(1000000000);
        double price;
        Asset asset = stockMarketService.getAsset(ticker);
        price = asset.getPrice();
        PortfolioDKK portfolio = portfolioService.getPortfolio(memberID);
        Holding holding = portfolio.getHoldingFromTicker(ticker);
        if (orderType.equalsIgnoreCase("buy") && (price * quantity > portfolio.getLiquidCash())) {
            System.out.println(ColorService.colorText("---You cannot afford " + quantity + " stocks for " + price + " each for a total of " +
                    price * quantity + ", when your stated liquid cash is " + portfolio.getLiquidCash() + "---", RED_BACKGROUND));
            return false;
        } else if (orderType.equalsIgnoreCase("sell") &&
                (holding == null)) {
            System.out.println(ColorService.colorText("---you don't hold any " + ticker + "---", RED_BACKGROUND));
            return false;
        } else if (orderType.equalsIgnoreCase("sell") &&
                (quantity > holding.getQuantity())) {
            System.out.println(ColorService.colorText("---You cannot sell " + quantity + " stocks from " + ticker +
                    ", when your stated holding is " + holding.getQuantity() + "---", RED_BACKGROUND));
            return false;
        }
        return transactionService.addNewTransaction(memberID, LocalDate.now(), ticker, price, asset.getCurrency(),
                orderType, quantity);
    }

    private String getTransactionType() {
        String input;
        boolean isValid;
        do {
            input = getNonEmptyString();
            isValid = input.equalsIgnoreCase("buy") || input.equalsIgnoreCase("sell");
            if(!isValid){
                System.out.println(ColorService.colorText("---The given input (" + input + ") does not equal \"buy\" or \"sell\"---", RED_BACKGROUND));
            }
        } while (!isValid);
        return input;
    }

    private String getValidTicker() {
        String input;
        boolean isValid;
        do {
            input = getNonEmptyString().toUpperCase();
            isValid = stockMarketService.getAsset(input) != null;
            if(!isValid){
                System.out.println(ColorService.colorText("---The given ticker (" + input + ") could not be found---", RED_BACKGROUND)
                        + "\nPlease try again:");
            }
        } while (!isValid);
        return input;
    }

    private void printTable(List<String> entries, String titles){
        String[] splitTitles = titles.split(",");
        int[] columLengths = new int[splitTitles.length];



        for(int k = 0; k < columLengths.length; k++) {
            //System.out.println("coulum " + k);
            for (int i = 0; i < entries.size(); i++) {

                String[] entry = entries.get(i).split(";");
                //checking title length
                if (splitTitles[k].length() > columLengths[k]) {
                    columLengths[k] = splitTitles[k].length();
                }

                if (entry[k].length() > columLengths[k]) {
                    columLengths[k] = entry[k].length();
                }

            }

        }
        int totalLength = 0;
        for (int i = 0; i < columLengths.length; i++) {
            totalLength += columLengths[i] + 3;
        }
        totalLength += 1;

        //Top line of table

        printLine(totalLength);
        System.out.print('\n');
        //Print titles
        for (int i = 0; i < splitTitles.length; i++) {
            System.out.printf(TABLE_SEPERATOR + " %-" + columLengths[i] + "s ", splitTitles[i]);
        }
        System.out.print(TABLE_SEPERATOR + '\n');
        //midle line of table
        printLine(totalLength);
        System.out.print('\n');
        //print entries


        for (int i = 0; i < entries.size(); i++) {

            for (int k = 0; k < columLengths.length; k++) {

                String[] entry = entries.get(i).split(";");
                System.out.printf(TABLE_SEPERATOR + " %-" + columLengths[k] + "s ", entry[k]);

            }
            System.out.print(TABLE_SEPERATOR + '\n');

        }

        printLine(totalLength);
        System.out.println();

    }

    private void printLine(int length) {
        printLine(length, '_');
    }
    private void printLine(int length,char block) {
        System.out.print(BLUE);
        for(int i = 0; i < length; i++) {
            System.out.print(block);
        }
        System.out.print(STANDARD);
    }
}
