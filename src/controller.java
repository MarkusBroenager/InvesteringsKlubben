import Service.viewCurrency;
import Service.viewStockMarket;
import Service.Transactions;
import Service.Users;

import java.util.ArrayList;
import java.util.Scanner;

import static Service.Transactions.*;
import static Service.Users.*;
import static Service.viewCurrency.loadCurrency;
import static Service.viewCurrency.showCurrency;
import static Service.viewStockMarket.loadStockMarketData;
import static Service.viewStockMarket.showStockMarket;

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
                System.out.println("2. Fjern bruger");
                System.out.println("3. Se liste over brugere");
                System.out.println("4. Gem ændringer");
                System.out.println("5. Se aktiemarkedet");
                System.out.println("6. Se transaktioner");
                System.out.println("7. Se valuta");
                System.out.println("8. Se rangliste");
                System.out.println("9. Se fordelingen på aktier og sektorer");
                System.out.println("10. Afslut programmet");

                int input2 = scanner.nextInt();

                if (input2 == 1) {
                    Users.createUser(listOfUsers);

                } else if (input2 == 2) {
                    removeUser(listOfUsers);
                }

                else if (input2 == 3) {
                    Users.showListOfUsers(listOfUsers);

                } else if (input2 == 4) {

                    Users.saveUsersData(listOfUsers);

                } else if (input2 == 5) {

                    showStockMarket(listOfStockMarket);

                } else if (input2 == 6) {

                   showTransactions(listOfTransactions);

                } else if (input2 == 7) {

                    showCurrency(listOfCurrency);

                } else if (input2 == 8) {

                    showRankedList(listOfUsers);

                } else if (input2 == 9) {

                }

                else if (input2 == 10) {

                    programmeIsRunning = false;
                }
            }
        }

        if (input1 == 2) {
            while (programmeIsRunning) {
                System.out.println("Tast:");
                System.out.println("1. Se aktiemarkedet");
                System.out.println("2. Opret transaktion");
                System.out.println("3. Slet transaktion");
                System.out.println("4. Gem ændringer");
                System.out.println("5. Se transaktionshistorik");
                System.out.println("6. Se personlig portefølgeværdi");
                System.out.println("7. Afslut programmet");

                int input3 = scanner.nextInt();

                if (input3 == 1) {
                    showStockMarket(listOfStockMarket);

                } if (input3 == 2) {

                    createTransactions(listOfTransactions);

                } if (input3 == 3) {

                    removeTransaction(listOfTransactions);

                }

                if (input3 == 4) {

                    saveTransactions(listOfTransactions);

                } if (input3 == 5) {

                    searchForTransactions(listOfTransactions);

                }

                if (input3 == 6) {

                    showPersonalInitialCash(listOfUsers);
                }

                if (input3 == 7) {

                    programmeIsRunning = false;

                }
            }
        }
    }
}