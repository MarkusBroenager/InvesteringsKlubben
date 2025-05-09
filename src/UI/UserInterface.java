package UI;

import Controller.Controller;
import Services.ServicesCSV.DataServices;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInterface {
    private Controller controller;
    private Scanner scanner = new Scanner(System.in);

    public UserInterface(Controller controller) {
        this.controller = controller;
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
                    controller.viewForexMarket();
                    break;
                case 3:
                    if (addNewTransaction(memberID)) {
                        System.out.println("Transaction added");
                    } else {
                        System.out.println("Transaction could not be added");
                    }
                    break;
                case 4:
                    controller.viewPortfolio(memberID);
                    break;
                case 5:
                    controller.viewTransactions(memberID);
                    break;
                case 6:
                    System.out.println("Nope");
                    break;
                case 7:
                    controller.viewPersonalInformation(memberID);
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
                    controller.viewCombinedPortfolio();
                    break;
                case 2:
                    System.out.println("0 - Exit, 1 - Sort by percentage, 2 - Sort by DKK");
                    int sortChoice = getUserChoice(2);
                    if (sortChoice == 1) {
                        controller.viewPortfoliosSortedByPercentage();
                    } else if (sortChoice == 2) {
                        controller.viewPortfoliosSortedByDKK();
                    }
                    break;
                case 3:
                    controller.viewSectorDistribution();
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
                    if (controller.addNewUser(fullName, email, birthday, initialCash)) {
                        System.out.println("Member added");

                    } else {
                        System.out.println("Could not add member");
                    }
                    break;
                case 5:
                    controller.viewAllUsers();
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
                    controller.viewStocksInDKK();
                    break;
                case 2:
                    controller.viewStocks();
                    break;
                default:
                    isRunning = false;
                    break;
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
        return (controller.addNewTransaction(memberID, dateOfTransaction, ticker, price, currency,
                orderType, quantity));
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
            input = scanner.nextLine();
            if (DataServices.getLocalDate(input).isAfter(LocalDate.of(2025, 1, 1))) {
                return DataServices.getLocalDate(input);
            }
        }
    }

}
