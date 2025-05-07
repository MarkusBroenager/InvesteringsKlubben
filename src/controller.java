import Service.viewCurrency;
import Service.viewStockMarket;
import Service.Transactions;
import Service.Users;

import java.util.ArrayList;
import java.util.Scanner;

import static Service.viewCurrency.loadCurrency;
import static Service.viewCurrency.showCurrency;
import static Service.viewStockMarket.loadStockMarketData;
import static Service.viewStockMarket.showStockMarket;
import static Service.Transactions.loadTransactions;
import static Service.Transactions.showTransactions;

public class controller {

    public static void startProgramme() {

        ArrayList<Users> listOfUsers = new ArrayList<>();
        ArrayList<viewStockMarket> listOfStockMarket = new ArrayList<>();
        ArrayList<Transactions> listOfTransactions = new ArrayList<>();
        ArrayList<viewCurrency> listOfCurrency = new ArrayList<>();

        Users.loadUsersData(listOfUsers);
        loadStockMarketData(listOfStockMarket);
        loadTransactions(listOfTransactions);
        loadCurrency(listOfCurrency);

        boolean programmeIsRunning = true;

        System.out.println("Tast 1 hvis du er leder, tast 2 hvis du er medlem");
        Scanner scanner = new Scanner(System.in);

        int input1 = scanner.nextInt();

        if (input1 == 1) {
            while (programmeIsRunning) {
                System.out.println("Tast:");
                System.out.println("1. Opret bruger");
                System.out.println("2. Se liste over brugere");
                System.out.println("3. Gem ændringer");
                System.out.println("4. Se aktiemarkedet");
                System.out.println("5. Se transaktioner");
                System.out.println("6. Se valuta");
                System.out.println("7. Afslut programmet");

                int input2 = scanner.nextInt();

                if (input2 == 1) {
                    Users.createUser(listOfUsers);

                } else if (input2 == 2) {
                    Users.showListOfUsers(listOfUsers);

                } else if (input2 == 3) {

                    Users.saveUsersData(listOfUsers);

                } else if (input2 == 4) {

                    showStockMarket(listOfStockMarket);

                } else if (input2 == 5) {

                   showTransactions(listOfTransactions);

                } else if (input2 == 6) {

                    showCurrency(listOfCurrency);
                }

                else if (input2 == 7) {
                    programmeIsRunning = false;
                }
            }
        }
    }
}