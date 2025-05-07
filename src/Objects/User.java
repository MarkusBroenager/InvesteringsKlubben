package Objects;

import java.time.LocalDate;

public class User implements UserInterface {
    private int userID;
    private String fullName;
    private String email;
    private LocalDate dateOfBirth;
    private double initialCash;
    private LocalDate dateCreated;
    private LocalDate lastUpdated;

    public User(int userID, String fullName, String email, LocalDate dateOfBirth, double initialCash, LocalDate dateCreated, LocalDate dateLastUpdated) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.initialCash = initialCash;
        this.dateCreated = dateCreated;
        this.lastUpdated = dateLastUpdated;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setInitialCash(double initialCash) {
        this.initialCash = initialCash;
    }
    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setLastUpdated(LocalDate dateLastUpdated) {
        this.lastUpdated = dateLastUpdated;
    }
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public double getInitialCash() {
        return initialCash;
    }
    public LocalDate getDateCreated() {
        return dateCreated;
    }
    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public int getUserID() {
        return userID;
    }

    @Override
    public String addToCSVFile() {
        return userID + ";" + fullName + ";" + email + ";" + dateOfBirth +
                ";" + initialCash + ";" + dateCreated + ";" + lastUpdated;

    }

    @Override
    public String toString() {
        return "Name: " + fullName + " UserID: " + userID + " Date of Birth: " + dateOfBirth + " Email: " +
                email + "User Creation Date" + dateCreated + " Initial investment : " +
                initialCash + " Last User update:" + lastUpdated;
    }


}
