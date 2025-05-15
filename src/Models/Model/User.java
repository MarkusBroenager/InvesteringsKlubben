package Models.Model;

import Models.Interfaces.Users;

import java.time.LocalDate;

public class User implements Users, Comparable<User> {

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

    @Override
    public int getUserID() {
        return userID;
    }

    public String getFullName(){
        return this.fullName;
    }

    @Override
    public double getInitialCash() {
        return initialCash;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String addToCSVFile() {
        return userID + ";" + fullName + ";" + email + ";" + birthday +
                ";" + initialCash + ";" + createdAt + ";" + lastUpdated;
    }

    @Override
    public String toString() {
        return fullName + ";" + userID + ";" + birthday + ";" +
                email + ";" + createdAt + ";" +
                String.format("%.2f", initialCash) + " DKK;" + lastUpdated;
    }

    @Override
    public int compareTo(User o) {
        return this.userID - o.getUserID();
    }
}
