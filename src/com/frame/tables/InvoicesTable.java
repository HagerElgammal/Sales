package com.frame.tables;
import com.frame.control.MyListener;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


public class InvoicesTable extends AbstractTableModel {


   private ArrayList<Invoice> invoiceList ;

   public InvoicesTable(ArrayList<Invoice> invoiceList){
        this.invoiceList = invoiceList;
    }

    public List<Invoice> getInvoiceList(){
        return invoiceList;
    }
public InvoicesTable(){}
    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0 -> {
                return "No.";
            }
            case 1 -> {
                return "Date";
            }
            case 2 -> {
                return "Customer";
            }
            case 3 -> {
                return "Total";
            }
        }
        return "";
    }
    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int data, int col) {
        Invoice invoice;
        invoice = invoiceList.get(data);
        switch (col) {
            case 0 -> {
                return invoice.getInvNu();
            }
            case 1 -> {
                return MyListener.df.format(invoice.getInvD());
            }
            case 2 -> {
                return invoice.getInvCus();
            }
            case 3 -> {
                return invoice.getInvT();
            }

        }
        return "";
    }
    @Override
    public int getRowCount() {
        return invoiceList.size();
    }


}