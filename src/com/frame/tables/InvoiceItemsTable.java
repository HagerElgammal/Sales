package com.frame.tables;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;



public class InvoiceItemsTable extends AbstractTableModel {

    private ArrayList<InvoiceItem> items;

    public InvoiceItemsTable(ArrayList<InvoiceItem> it) {
        this.items =  it;
    }
public InvoiceItemsTable(){}


    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    @Override
    public String getColumnName(int  columnIndex) {
        switch ( columnIndex) {
            case 0 : {
                return "No.";
            }
            case 1 : {
                return "Item Name";
            }
            case 2 : {
                return "Item Price";
            }
            case 3 : {
                return "Count";
            }
            case 4 : {
                return "Item Total";
            }
        }
        return "";
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceItem invoiceItem = items.get(rowIndex);
        switch (columnIndex) {

            case 0:

                return rowIndex + 1;

            case 1:

                return invoiceItem.getItemName();
            case 2:

                return invoiceItem.getItemPrice();

            case 3:
                return invoiceItem.getCount();

            case 4:
                return invoiceItem.getItemTotal();

            default:

                return "";
        }
    }
}



