package com.frame.control;

import com.frame.tables.Invoice;
import com.frame.tables.InvoiceItem;
import com.frame.tables.InvoiceItemsTable;
import com.frame.tables.InvoicesTable;
import com.frame.view.MyFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MyListener implements ActionListener, ListSelectionListener {
    private MyFrame frame;
    public static DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    private InvoicesTable invoicestable;
    private InvoiceItemsTable itemsTable;

    public MyListener(MyFrame frame) {
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "L":
                loadFile();
                break;

            case "S":
                saveFile();
                break;

            case "Create New Invoice":
                newInv();
                break;

            case "Delete Invoice":
                deleteInvoice();
                break;

            case "Cancel":
                cancel();
                break;

            case "Save":
                save();
                break;

            case"Create Item":
                createItem();
                break;

            case "Create New Item":
                createNewItem();
                break;

            case "Cancel Item":
                cancelItem();
                break;

            case "Create New Customer":
                create();
                break;

            case "Cancel Customer":
                cancelCustomer();
                break;

        }


    }



    private void saveFile() {

    }

    private void loadFile() {
        JFileChooser fc = new JFileChooser();
        try{ int l;JOptionPane.showMessageDialog(frame,"Select Invoice Header ","", JOptionPane.WARNING_MESSAGE);
         l = fc.showOpenDialog(frame);
         if (l == JFileChooser.APPROVE_OPTION){
             File invoiceHeader;
             invoiceHeader = fc.getSelectedFile();
             Path pathInvoiceHeader = Paths.get(invoiceHeader.getAbsolutePath());
             ArrayList<Invoice>invoiceList ;
             invoiceList = new ArrayList<>();
             List<String> invoicesText;
             invoicesText = Files.readAllLines(pathInvoiceHeader);
             for (String invoiceText : invoicesText ){
                 String[] data = invoiceText.split(",");
                 String firstNo = data[0];
                 String secDate = data[1];
                 String thirdCustomer = data[2];

                 int number = Integer.parseInt(firstNo);
                 Date dateInHeader = df.parse(secDate);
                 String customerName = thirdCustomer;
                 Invoice invoHeader = new Invoice(number, dateInHeader, customerName);
                 invoiceList.add(invoHeader);

             }
             frame.setInvoicesTable();
             JOptionPane.showMessageDialog(frame, "Now select Items file ", "", JOptionPane.WARNING_MESSAGE);
             l = fc.showOpenDialog(frame);
             if (l == JFileChooser.CANCEL_OPTION){
                 JOptionPane.showMessageDialog(frame, "Please Select (.CSV) File.", "", JOptionPane.ERROR_MESSAGE);
             }
             else if (l == JFileChooser.APPROVE_OPTION){
                 File fileOfLine = fc.getSelectedFile();
                 Path pathForLine = Paths.get(fileOfLine.getAbsolutePath());

                 ArrayList<InvoiceItem> content = new ArrayList<>();
                 List<String> linesList = Files.readAllLines(pathForLine);

                 for (String line : linesList){
                     String[] lineWords = line.split(",");
                     String frstNo = lineWords[0];
                     String secItnam = lineWords[1];
                     String thrdItPri = lineWords[2];
                     String frthItCon = lineWords[3];

                     int numVal;
                     numVal = Integer.parseInt(frstNo);
                     String itemName = secItnam;
                     double itemPrice = Double.parseDouble(thrdItPri);
                     int itemCount = Integer.parseInt(frthItCon);
                     Invoice invoHeader = frame.getNum(numVal);

                     InvoiceItem invLine = new InvoiceItem(invoHeader, itemName, itemPrice,itemCount);
                     invoHeader.getInvItems().add(invLine);
                 }
                frame.setItems(content);

             }
             InvoicesTable inTable;
             inTable = new InvoicesTable( invoiceList);
             frame.setInvTable(inTable);
             frame.getInvoiceData().setModel(inTable);
         }
        } catch (HeadlessException | IOException | NumberFormatException | ParseException exp) {
            JOptionPane.showMessageDialog(frame, "Failed Loading The File.\n Make sure to selected file in (.CSV) .", "", JOptionPane.ERROR_MESSAGE);
        }
    }

        private void deleteInvoice() {
    }

    private void newInv() {

    }

    private void createItem() {
    }


    private void save() {
    }

    private void cancel() {

    }
    private void create() {

    }
    private void cancelCustomer() {
    }

    private void cancelItem() {
    }

    private void createNewItem() {
    }
    public void valueChanged(ListSelectionEvent ev) {
        int currInv = frame.getInvoiceData().getSelectedRow();
        if (currInv != -1) {
            Invoice currSelectedInv;
            currSelectedInv = frame.getInvoiceList().get(currInv);
            ArrayList<InvoiceItem> items = currSelectedInv.getInvItems();
            InvoiceItemsTable invoiceItemsTable = new InvoiceItemsTable(items);
            frame.getItemsJTable().setModel(invoiceItemsTable);
            frame.setItems(items);
            frame.getCusName().setText(currSelectedInv.getInvCus());
            frame.getNo().setText("" + currSelectedInv.getInvNu());
            frame.getInvDate().setText(df.format(currSelectedInv.getInvD()));
            frame.getInvTotal().setText("" + currSelectedInv.getInvT());
        }
    }
}
