package Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Transactions {

    private int ID;
    private int userID;
    private String date;
    private String ticker;
    private double price;
    private String currency;
    private String orderType;
    private int quantity;
    private ArrayList<Transactions> arraylist;

    public Transactions(int ID, int userID, String date, String ticker, double price, String currency,
                        String orderType, int quantity) {
        this.ID = ID;
        this.userID = userID;
        this.date = date;
        this.ticker = ticker;
        this.price = price;
        this.currency = currency;
        this.orderType = orderType;
        this.quantity = quantity;
    }

    public int getUserID() {
        return this.userID;
    }

    public int getID() {
        return this.ID;
    }

    public Transactions() {
        this.arraylist = new ArrayList<>();
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public static void loadTransactions(ArrayList<Transactions> list) {

        File transactionsFile = new File("Resources/transactions.csv");
        try {
            Scanner scanner = new Scanner(transactionsFile);

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                if (line.contains("user_ID")) {
                    System.out.println();
                } else {
                    Scanner lineScanner = new Scanner(line);
                    lineScanner.useDelimiter("  ");

                    int ID = Integer.parseInt(lineScanner.next());
                    int user_ID = Integer.parseInt(lineScanner.next());
                    String date = lineScanner.next();
                    String ticker = lineScanner.next();
                    double price = Double.parseDouble(lineScanner.next());
                    String currency = lineScanner.next();
                    String orderType = lineScanner.next();
                    int quantity = lineScanner.nextInt();

                    list.add(new Transactions(ID, user_ID, date, ticker, price, currency, orderType,
                            quantity));


                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public static void showTransactions(ArrayList<Transactions> list) {

        int i;

        for (i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }

    }

    public String toString() {
        return ID + "  " + userID + "  " + date + "  " + ticker + "  " + price + "  " + currency + "  " +
                orderType + "  " + quantity;
    }

    public static void createTransactions(ArrayList<Transactions> list) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Indtast ID:");
        int ID = scanner.nextInt();
        System.out.println("Indtast userID:");
        int userID = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Indtast dato:");
        String date = scanner.nextLine();
        System.out.println("Indtast ticker:");
        String ticker = scanner.nextLine();
        System.out.println("Indtast pris:");
        double price = scanner.nextDouble();

        scanner.nextLine();

        System.out.println("Indtast currency:");
        String currency = scanner.nextLine();
        System.out.println("Indtast orderType:");
        String orderType = scanner.nextLine();
        System.out.println("Indtast antallet af aktier");
        int quantity = scanner.nextInt();

        Transactions transaction = new Transactions(ID, userID, date, ticker, price, currency, orderType,
                quantity);
        list.add(transaction);

    }

    public static void removeTransaction(ArrayList<Transactions> list) {

        System.out.println("Indtast dit user_ID");

        Scanner scanner = new Scanner(System.in);

        int inputtedUser_ID = scanner.nextInt();

        int i;

        for (i = 0; i < list.size(); i++) {
            if (inputtedUser_ID == list.get(i).getUserID()) {

                System.out.println(list.get(i).toString());

            }

            }

                System.out.println("Indtast ID på den transaktion du gerne vil fjerne");

                int inputtedID = scanner.nextInt();

                for (i = 0; i < list.size(); i++) {
                    if (inputtedID == list.get(i).getID()) {
                        list.remove(i);
                    }
                }


    }

    public static void saveTransactions(ArrayList<Transactions> list) {

        String firstLine = "ID user_ID date ticker price currency order_type quantity";

        try {
            PrintStream delete = new PrintStream("Resources/transactions.csv");
            delete.println(firstLine);
            delete.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int i;

        for (i = 0; i < list.size(); i++) {
            String convertion = String.valueOf(list.get(i));

            try {
                PrintStream save = new PrintStream(new FileOutputStream("Resources/transactions.csv",
                        true));
                save.println(convertion);
                save.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void searchForTransactions(ArrayList<Transactions> list) {

        System.out.println("Indtast dit user_ID");

        Scanner scanner = new Scanner(System.in);

        int inputtedUser_ID = scanner.nextInt();

        int i ;

        for (i = 0; i < list.size(); i++) {
            if (inputtedUser_ID == list.get(i).getUserID()) {

                System.out.println(list.get(i).toString());
            }
        }


    }


}
