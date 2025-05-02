package Models.Model;

import Models.Interfaces.Transactions;

import java.time.LocalDate;

public class Transaction implements Transactions {

    private String ticker;
    private double price;
    private int quantity;
    private LocalDate dateOfTransaction;
    private String orderType;
    private int transactionID;
    private int userID;
    private String currency;


    public Transaction(int transactionID, int userID, LocalDate dateOfTransaction, String ticker,
                       double price, String currency, String orderType, int quantity) {
        this.ticker = ticker;
        this.price = price;
        this.quantity = quantity;
        this.dateOfTransaction = dateOfTransaction;
        this.orderType = orderType;
        this.transactionID = transactionID;
        this.userID = userID;
        this.currency = currency;
    }

    public String getTicker() {
        return ticker;
    }

    public int getUserID() {
        return userID;
    }

    public String getOrderType() {
        return orderType;
    }

    public double getTotalTransactionPrice() {
        return price * quantity;
    }


    public LocalDate getDateOfTransaction() {
        return dateOfTransaction;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCurrency(){
        return currency;
    }

    public String addTransactionToCSVFile() {
        return transactionID + ";" + userID + ";" + dateOfTransaction + ";" + ticker + ";" + price +
                ";" + currency + ";" + orderType + ";" + quantity;
    }

    public String toString() {
        return orderType + " " + quantity + " " + ticker + " For " + getTotalTransactionPrice() +
                " " + currency + " date: " + dateOfTransaction;
    }

}
