package com.frame.control;

import com.frame.tables.Invoice;
import com.frame.tables.InvoiceItem;
import com.frame.tables.InvoiceItemsTable;
import com.frame.tables.InvoicesTable;
import com.frame.view.MyFrame;
import com.frame.view.NewCustomer;
import com.frame.view.NewPurshase;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
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
    private NewCustomer nCustomer;
    private NewPurshase newPurshase;


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
        JOptionPane.showMessageDialog(frame, "Choose location for headers file...","", JOptionPane.INFORMATION_MESSAGE);
        JFileChooser chooser = new JFileChooser();
        try {
            int saV = chooser.showSaveDialog(frame);
            if (saV == JFileChooser.APPROVE_OPTION){
                File invF = chooser.getSelectedFile();
                FileWriter fileHW = new FileWriter(invF);
                ArrayList<Invoice> headersList = frame.getInvoiceList();


                String headerWords = "", records = "";

                for (Invoice header: headersList){
                    headerWords += header.toString();
                    headerWords += "\n";
                    for (InvoiceItem item: header.getInvItems()){
                        records += item.toString();
                        records += "\n";
                    }
                }

                JOptionPane.showMessageDialog(frame, "Now choose location for invoices file...","", JOptionPane.INFORMATION_MESSAGE);
                saV = chooser.showSaveDialog(frame);
                File fI = chooser.getSelectedFile();

                FileWriter FI = new FileWriter(fI);

                headerWords = headerWords.substring(0, headerWords.length()-1);
                fileHW.write(headerWords);
                fileHW.close();

                records = records.substring(0, records.length()-1);
                FI.write(records);
                FI.close();


                JOptionPane.showMessageDialog(frame, "Save Done","", JOptionPane.INFORMATION_MESSAGE);
                if (headersList == null) {
                    throw new Exception("Invoices Missing");
                }
            }
        }

        catch (Exception exp){
            JOptionPane.showMessageDialog(frame, "Saving Failed.", "", JOptionPane.ERROR_MESSAGE);
        }

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
             List<String> invoicesData;
             invoicesData = Files.readAllLines(pathInvoiceHeader);
             for (String invoiceData : invoicesData ){
                 String[] data = invoiceData.split(",");
                 String firstNo = data[0];
                 String secDate = data[1];
                 String thirdCustomer = data[2];

                 int number = Integer.parseInt(firstNo);
                 Date dateInHeader = df.parse(secDate);
                 String customerName = thirdCustomer;
                 Invoice invoHeader = new Invoice(number, dateInHeader, customerName);
                 invoiceList.add(invoHeader);

             }
             frame.setInvoiceList(invoiceList);
             JOptionPane.showMessageDialog(frame, "Now select Items file ", "", JOptionPane.WARNING_MESSAGE);
             l = fc.showOpenDialog(frame);
             if (l == JFileChooser.CANCEL_OPTION){
                 JOptionPane.showMessageDialog(frame, "Please Select (.CSV) File.", "", JOptionPane.ERROR_MESSAGE);
             }
             else if (l == JFileChooser.APPROVE_OPTION){
                 File fileOfLine = fc.getSelectedFile();
                 Path pathForLine = Paths.get(fileOfLine.getAbsolutePath());

                 ArrayList<InvoiceItem> product = new ArrayList<>();
                 List<String> itemsList = Files.readAllLines(pathForLine);

                 for (String item : itemsList){
                     String[] itemData = item.split(",");
                     String frstNo = itemData[0];
                     String secItnam = itemData[1];
                     String thrdItPri = itemData[2];
                     String frthItCon = itemData[3];

                     int numVal;
                     numVal = Integer.parseInt(frstNo);
                     String itemName = secItnam;
                     double itemPrice = Double.parseDouble(thrdItPri);
                     int itemCount = Integer.parseInt(frthItCon);
                     Invoice invoHeader = frame.getNum(numVal);

                     InvoiceItem invLine = new InvoiceItem(invoHeader, itemName, itemPrice,itemCount);
                     invoHeader.getInvItems().add(invLine);
                 }
                frame.setItems(product);

             }
             InvoicesTable inTable;
             inTable = new InvoicesTable( invoiceList);
             frame.setInvTable(inTable);
             frame.getInvoiceData().setModel(inTable);
         }
        } catch (HeadlessException | IOException | NumberFormatException | ParseException exp) {
            JOptionPane.showMessageDialog(frame, "Failed Loading CSV File.\n Please Select file in (.CSV) .", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteInvoice() {
        int invID = frame.getInvoiceData().getSelectedRow();
        if (invID != -1){
            frame.getInvoiceList().remove(invID);
            frame.getInvoicesTable().fireTableDataChanged();
            frame.getItemsJTable().setModel(new InvoiceItemsTable(new ArrayList<>()));
            frame.getNo().setText("");
            frame.getCusName().setText("");
            frame.getInvDate().setText("");
            frame.getInvTotal().setText("");
        }
    }

    private void newInv() {
        nCustomer = new NewCustomer(frame);
        nCustomer.setVisible(true);
        try {
            int invNo = 0;
            for (Invoice invoice : frame.getInvoiceList()) {
                if (invoice.getInvNu()> invNo)
                    invNo = invoice.getInvNu();
            }
            invNo++;
            nCustomer.getInvNumberLabel2().setText("" + invNo);
        }
        catch(Exception ep){
            JOptionPane.showMessageDialog(frame,"Fill all the customer data first.", "", JOptionPane.ERROR_MESSAGE);
            nCustomer.setVisible(false);
        }


    }

    private void createItem() {
    }


    private void save() {
    }

    private void cancel() {

    }
    private void create() {
        int invNo = 0;
        for (Invoice invoice : frame.getInvoiceList()){
            if (invoice.getInvNu() > invNo)
                invNo = invoice.getInvNu();
        }
        invNo++;
        String cutstomerName = nCustomer.getCreateNCField().getText();
        String date = nCustomer.getInvDateField().getText();
        Date invDate = new Date();
        try {
            invDate = df.parse(date);
        }
        catch (ParseException exception) {
            JOptionPane.showMessageDialog(frame, "Date entry should adhere to format: dd-MM-yyyy", "", JOptionPane.ERROR_MESSAGE);
        }
        if (cutstomerName.length()==0){
            JOptionPane.showMessageDialog(frame, "Name cannot be empty", "", JOptionPane.ERROR_MESSAGE);
        }
        else{
            Invoice invHeader = new Invoice(invNo, invDate, cutstomerName);
            frame.getInvoiceList().add(invHeader);
            frame.getInvoicesTable().fireTableDataChanged();
            nCustomer.dispose();
            nCustomer = null;
        }

    }
    private void cancelCustomer() {
        nCustomer.dispose();
        nCustomer = null;
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
