package PersistenceLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class User {

    private int userID;
    private String fullName;
    private String email;
    private String birthDate;
    private double initialCashDKK;
    private String createdAt;
    private String lastUpdated;

    public User(int userID, String fullName, String email, String birthDate, double initialCashDKK,
                 String createdAt, String lastUpdated) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
        this.initialCashDKK = initialCashDKK;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;

    }

    ArrayList<User> users = new ArrayList<>();

    public User(String[] tokens) {
        try{
            this.userID = Integer.parseInt(tokens[0]);
            this.fullName = tokens[1];
            this.email = tokens[2];
            this.birthDate = tokens[3];
            this.initialCashDKK = Double.parseDouble(tokens[4]);
            this.createdAt = tokens[5];
            this.lastUpdated = tokens[6];
        }catch (NumberFormatException e){
            System.out.println("Could not parse number in users.csv");
        }
    }

    public void transferToArrayList() {

        try {
            File usersFile = new File("Resources/users.csv");
            Scanner scanner = new Scanner(usersFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(";");

                int userID = Integer.parseInt(lineScanner.next());
                String fullName = lineScanner.next();
                String email = lineScanner.next();
                String birthDate = lineScanner.next();
                double initialCashDKK = Double.parseDouble(lineScanner.next());
                String createdAt = lineScanner.next();
                String lastUpdated = lineScanner.next();
                users.add(new User(userID, fullName, email, birthDate, initialCashDKK, createdAt, lastUpdated));
                lineScanner.close();
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Reading of file failed. File could not be found.");
        }
    }
}