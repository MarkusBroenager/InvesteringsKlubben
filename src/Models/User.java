package Models;

import java.time.LocalDate;

public class User {

    private int userID;
    private String fullName;
    private String email;
    private double initialCash;
    private LocalDate birthday;
    private LocalDate createdAt;
    private LocalDate lastUpdated;

    public User(int userID, String fullName, String email, LocalDate birthday, double initialCash,
                LocalDate createdAt, LocalDate lastUpdated) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.initialCash = initialCash;
        this.birthday = birthday;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }

    public int getUserID() {
        return userID;
    }

    public String addToCSVFile() {
        return userID + ";" + fullName + ";" + email + ";" + birthday +
                ";" + initialCash + ";" + createdAt + ";" + lastUpdated;
    }

    @Override
    public String toString() {
        return "Name: " + fullName + " userID: " + userID + " Born: " + birthday + " email: " +
                email + " Started investing: " + createdAt + " Initial investment : " +
                String.format("%.2f",initialCash) +" DKK Last update:" + lastUpdated;
    }

}
