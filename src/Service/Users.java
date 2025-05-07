package Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Users {

    private int userID;
    private String fullName;
    private String email;
    private String birthDate;
    private double initialCashDKK;
    private String createdAt;
    private String lastUpdated;
    private ArrayList<Users> arrayList;

    public Users(int userID, String fullName, String email, String birthDate, double initialCashDKK,
                 String createdAt, String lastUpdated) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
        this.initialCashDKK = initialCashDKK;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;

    }

    public Users() {
        arrayList = new ArrayList<>();
    }

    public double getInitialCashDKK() {
        return this.initialCashDKK;
    }

    public void setInitialCashDKK(double initialCashDKK) {
        this.initialCashDKK = initialCashDKK;
    }

    public static void loadUsersData(ArrayList<Users> list) {


        try {
            File usersFile = new File("Resources/users.csv");
            Scanner scanner = new Scanner(usersFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("user_id")) {
                    System.out.println();
                } else {
                    Scanner lineScanner = new Scanner(line);
                    lineScanner.useDelimiter("  ");

                    int userID = Integer.parseInt(lineScanner.next());
                    String fullName = lineScanner.next();
                    String email = lineScanner.next();
                    String birthDate = lineScanner.next();
                    double initialCashDKK = Double.parseDouble(lineScanner.next());
                    String createdAt = lineScanner.next();
                    String lastUpdated = lineScanner.next();
                    list.add(new Users(userID, fullName, email, birthDate, initialCashDKK, createdAt, lastUpdated));
                    lineScanner.close();
                }

            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Reading of file failed. File could not be found.");
        }
    }

    public static void showListOfUsers(ArrayList<Users> list) {

        int i;

        for (i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }

    }

    public String toString() {
        return userID + "  " + fullName + "  " + email + "  " + birthDate + "  " + initialCashDKK + "  " +
                createdAt + "  " + lastUpdated;
    }

    public static void createUser(ArrayList<Users> list) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Indtast userid (helt tal) :");
        int userID = scanner.nextInt();
        System.out.println("Indtast fullname:");
        scanner.nextLine();
        String fullName = scanner.nextLine();
        System.out.println("Indtast email");
        String email = scanner.nextLine();
        System.out.println("Indtast fødselsdag:");
        String birthDate = scanner.nextLine();
        System.out.println("Indtast startbeløb:");
        double initialCashDKK = scanner.nextDouble();
        System.out.println("Indtast dato for oprettelse:");
        scanner.nextLine();
        String createdAt = scanner.nextLine();
        System.out.println("Indtast dato for sidste opdatering:");
        String lastUpdated = scanner.nextLine();

        Users bruger = new Users(userID, fullName, email, birthDate, initialCashDKK, createdAt, lastUpdated);
        list.add(bruger);
    }

    public static void saveUsersData(ArrayList<Users> list) {

        String firstLine = "user_id full_name email birth_date initial_cash_DKK created_at last_updated";

                try {
                    PrintStream delete = new PrintStream("Resources/users.csv");
                    delete.println(firstLine);
                    delete.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

        int i;

        for (i = 0; i < list.size(); i++) {
            String convertion = String.valueOf(list.get(i));

            try {
            PrintStream save = new PrintStream(new FileOutputStream("Resources/users.csv",
                    true));
            save.println(convertion);
            save.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        }

        }
    }

