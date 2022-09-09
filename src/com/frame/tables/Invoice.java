package com.frame.tables;
import com.frame.control.MyListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Invoice {
   private final int invNu;
   private final Date invD;
   private final String invCus;
   private double invT;
   private ArrayList <InvoiceItem> items;
   private final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
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

    public Date getInvD() {
        return invD;
    }

    public String getInvCus() {
        return invCus;
    }

    public double getInvT() {
        double invT = 0.0;
        for (int i = 0 ; i < getInvItems().size(); i++){
            invT += getInvItems().get(i).getItemTotal();
        }
        return invT;
    }

    public ArrayList<InvoiceItem> getInvItems(){if (items == null){items = new ArrayList<>();}
         return items;
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


    public void setInvT(double invT) {
        this.invT = invT;
    }
}

