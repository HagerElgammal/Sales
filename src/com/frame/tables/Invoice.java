package com.frame.tables;

import com.frame.control.MyListener;

import java.util.ArrayList;
import java.util.Date;

public class Invoice {
   private  int invNu;
   private Date invD;
   private String invCus;
   private double invT;
   private ArrayList <InvoiceItem> items;

// Constructor
    public Invoice(int invNu, Date invD, String invCus){
        this.invNu = invNu;
        this.invD = invD;
        this.invCus = invCus;


    }



    // getter and setter


    public int getInvNu() {
        return invNu;
    }

    public void setInvNu(int invNu) {
        this.invNu = invNu;
    }

    public Date getInvD() {
        return invD;
    }

    public void setInvD(Date invD) {
        this.invD = invD;
    }

    public String getInvCus() {
        return invCus;
    }

    public void setInvCus(String invCus) {
        this.invCus = invCus;
    }

    public double getInvT() {
        double invT = 0.0;
        for (int i = 0 ; i < getInvItems().size(); i++){
            invT += getInvItems().get(i).getItemTotal();
        }
        return invT;
    }

    public void setInvT(double invTotal) {
        this.invT = invT;
    }

    public ArrayList<InvoiceItem> getInvItems(){if (items == null){items = new ArrayList<>();}
         return items;
    }

    public void setItemsTables(ArrayList<InvoiceItemsTable> itemsTables) {
        this.items = items;
    }

    @Override
    public String toString() {
        return
                 invNu + ","
                 + MyListener.df.format(invD) +","
                + invCus +","
                + invT
                ;
    }


}

