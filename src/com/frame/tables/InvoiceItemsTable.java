package com.frame.tables;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class InvoiceItemsTable extends AbstractTableModel {

    private ArrayList<InvoiceItem> items;

    public InvoiceItemsTable(ArrayList<InvoiceItem> it) {
        this.items = it;
    }
    public List<InvoiceItem> getInvoiceItem() {
        return items;
    }
    public InvoiceItemsTable() {
    }


    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return "No.";
            }
            case 1 -> {
                return "Item Name";
            }
            case 2 -> {
                return "Item Price";
            }
            case 3 -> {
                return "Count";
            }
            case 4 -> {
                return "Item Total";
            }
        }
        return "";
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

    @Override
    public Object getValueAt(int data, int col) {
        InvoiceItem invoiceItem;
        invoiceItem = items.get(data);
        fireTableCellUpdated(data, col);
        return switch (col) {
            case 0 -> data + 1;
            case 1 -> invoiceItem.getItemName();
            case 2 -> invoiceItem.getItemPrice();
            case 3 -> invoiceItem.getCount();
            case 4 -> invoiceItem.getItemTotal();
            default -> "";
        };
    }



}



