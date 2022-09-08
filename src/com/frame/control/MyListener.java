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
    private final MyFrame frame;
    public static DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
      private NewCustomer nCustomer;
    private NewPurshase nPurshase;


    public MyListener(MyFrame frame) {
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "L" -> loadFile();
            case "S" -> saveFile();
            case "New Inv" -> newInv();
            case "Delete" -> deleteInvoice();
            case "Delete Item" -> DeleteItem();
            case "New Item" -> createItem();
            case "CreateNewItem" -> createNewItem();
            case " CancelItem" -> cancelItem();
            case "create" -> create();
            case "cancelCustomer" -> cancelCustomer();
        }


    }


    private void saveFile() {
        JOptionPane.showMessageDialog(frame, "Choose location for Invoice file...","", JOptionPane.INFORMATION_MESSAGE);
        JFileChooser chooser = new JFileChooser();
        try {
            int saV = chooser.showSaveDialog(frame);
            if (saV == JFileChooser.APPROVE_OPTION){
                File invF = chooser.getSelectedFile();
                FileWriter fileSA = new FileWriter(invF);
                ArrayList<Invoice> headersList = frame.getInvoiceList();


                StringBuilder invData = new StringBuilder();
                StringBuilder records = new StringBuilder();

                for (Invoice header: headersList){
                    invData.append(header.toString());
                    invData.append("\n");
                    for (InvoiceItem item: header.getInvItems()){
                        records.append(item.toString());
                        records.append("\n");
                    }
                }

                JOptionPane.showMessageDialog(frame, "Now choose location for Items file...","", JOptionPane.INFORMATION_MESSAGE);
                chooser.showSaveDialog(frame);
                File fI = chooser.getSelectedFile();
                FileWriter FI = new FileWriter(fI);
                invData = new StringBuilder(invData.substring(0, invData.length() - 1));
                fileSA.write(invData.toString());
                fileSA.close();
                records = new StringBuilder(records.substring(0, records.length() - 1));
                FI.write(records.toString());
                FI.close();
                JOptionPane.showMessageDialog(frame, "Save Done","", JOptionPane.INFORMATION_MESSAGE);
            }

    } catch (IOException e)

    {
        JOptionPane.showMessageDialog(frame, "Failed Save CSV File.\n Please Save file in (.CSV) .", "", JOptionPane.ERROR_MESSAGE);
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
                 Invoice invoHeader = new Invoice(number, dateInHeader, thirdCustomer);
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
                     double itemPrice = Double.parseDouble(thrdItPri);
                     int itemCount = Integer.parseInt(frthItCon);
                     Invoice invoHeader = frame.getNum(numVal);

                     InvoiceItem invLine = new InvoiceItem(invoHeader, secItnam, itemPrice,itemCount);
                     invoHeader.getInvItems().add(invLine);
                 }
                frame.setItems(product);

             }
             InvoicesTable inTable;
             inTable = new InvoicesTable( invoiceList);
             frame.setInvTable(inTable);
             frame.getInvoiceData().setModel(inTable);
         }

        } catch (NumberFormatException | ParseException | IOException e) {
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
        nPurshase = new NewPurshase(frame);
        nPurshase.setVisible(true);
    }


    private void DeleteItem() {
        int selectedInvoice = frame.getInvoiceData().getSelectedRow();
        int selectedItemIndex = frame.getItemsJTable().getSelectedRow();
        if (selectedItemIndex != -1){
            frame.getItems().remove(selectedItemIndex);
            frame.getInvoicesTable().fireTableDataChanged();
            frame.getInvoiceData().setRowSelectionInterval(selectedInvoice, selectedInvoice);
            frame.getInvoiceItemsTable().fireTableDataChanged();
        }
    }
    private void create() {
        int invNo = 0;
        for (Invoice invoice : frame.getInvoiceList()) {
            if (invoice.getInvNu() > invNo)
                invNo = invoice.getInvNu();
        }
        invNo++;
        String cutstomerName = nCustomer.getCreateNCField().getText();
        String date = nCustomer.getInvDateField().getText();
        Date invDate = null;
        try {
            invDate = df.parse(date);
         
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                df.setLenient(false);
                df.parse(date);
            } catch (ParseException exception) {
                JOptionPane.showMessageDialog(frame, "Date format should be: dd-MM-yyyy", "", JOptionPane.ERROR_MESSAGE);
            }

            if (cutstomerName.length() == 0) {
                JOptionPane.showMessageDialog(frame, "Name cannot be empty", "", JOptionPane.ERROR_MESSAGE);
            } else {
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
        nPurshase.dispose();
        nPurshase = null;
    }

    private void createNewItem() {

        int currInvoice;
        currInvoice = frame.getInvoiceData().getSelectedRow();
        String itemName = nPurshase.getItemNameField().getText();
        String itemPrAsString = nPurshase.getItemPriceField().getText();
        String countString = nPurshase.getCountField().getText();
        double itemPrice = 0;
        int itemCount = 0;
        try {
            itemPrice = Double.parseDouble(itemPrAsString);
        }
        catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(frame, "Error parsing price.", "", JOptionPane.ERROR_MESSAGE);
        }
        try {
            itemCount = Integer.parseInt(countString);
        }
        catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(frame, "Error parsing count", "", JOptionPane.ERROR_MESSAGE);
        }

        if (currInvoice != -1 && nPurshase != null){
            Invoice invoiceNum = frame.getInvoiceList().get(currInvoice);
            InvoiceItem invLine = new InvoiceItem(invoiceNum, itemName, itemPrice, itemCount);
            frame.getItems().add(invLine);
            frame.getInvoicesTable().fireTableDataChanged();
            frame.getInvoiceData().setRowSelectionInterval(currInvoice, currInvoice);
            frame.getInvoiceItemsTable().fireTableDataChanged();
            nPurshase.dispose();
            nPurshase = null;
        }



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
