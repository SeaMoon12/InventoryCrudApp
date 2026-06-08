package com.inventory.queries;

public class Transaction {
    private int transactionID;
    private int productID;
    private int quantity;
    private String transactionType;
    private String transactionDate;

    public Transaction(int transactionID, int productID, int quantity, String transactionType, String transactionDate) {
        this.transactionID = transactionID;
        this.productID = productID;
        this.quantity = quantity;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public int getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getTransactionDate() {
        return transactionDate;
    }
}
