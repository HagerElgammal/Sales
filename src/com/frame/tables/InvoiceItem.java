package com.frame.tables;

public class InvoiceItem {
    private final Invoice invoice;
    private final String itemName;
    private final double itemPrice;
    private final int count;
    private double itemTotal;



    // Constructor
    public InvoiceItem(Invoice invoice, String itemName, double itemPrice, int count) {
        this.invoice = invoice;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getCount() {
        return count;
    }

    public double getItemTotal() {
        return itemPrice * count;
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
