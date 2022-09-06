package com.frame.tables;

public class InvoiceItem {
    private Invoice invoice;
    private String itemName;
    private double itemPrice;
    private int count;
    private double itemTotal;



    // Constructor
    public InvoiceItem(Invoice invoice, String itemName, double itemPrice, int count) {
        this.invoice = invoice;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
        this.itemTotal = itemTotal;
    }

    //getter and setter
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getItemTotal() {
        return itemPrice * count;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }


    @Override
    public String toString() {
        return  invoice.getInvNu() +
                ","  + itemName +  ","  + itemPrice +
                "," + count  + "," +
                itemTotal
                ;
    }
}
