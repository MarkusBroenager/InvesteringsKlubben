package Models;

import java.time.LocalDate;

public class Transaction {

    private Stock stock;
    private double price;
    private int quantity;
    private LocalDate dateOfTransaction;
    private String orderType;
    private int transactionID;
    private int userID;


    public Transaction(Stock stock, double price, int quantity, LocalDate dateOfTransaction,
                       String orderType, int transactionID, int userID) {
        this.stock = stock;
        this.price = price;
        this.quantity = quantity;
        this.dateOfTransaction = dateOfTransaction;
        this.orderType = orderType;
        this.transactionID = transactionID;
        this.userID = userID;
    }

    public Stock getStock() {
        return stock;
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

    public String addTransactionToCSVFile() {
        return transactionID + ";"+userID+";"+dateOfTransaction+";"+stock.getTicker()+";"+price+
                ";"+stock.getCurrency().getBaseCurrency()+";"+orderType+";"+ quantity;
    }

}
