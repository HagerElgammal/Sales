package com.frame.view;

import com.frame.control.MyListener;
import com.frame.tables.Invoice;
import com.frame.tables.InvoiceItem;
import com.frame.tables.InvoiceItemsTable;
import com.frame.tables.InvoicesTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class MyFrame extends JFrame  {


    private JButton createNewInvoice;
    private JButton deleteInvoice;
    private JButton createItem;
    private JButton deleteItem;
    private JMenuBar mb;
    private JMenu fileMenu;
    private JMenuItem loadFile;
    private JMenuItem saveFile;
    private JTable invoiceTable;
    private JTable itemsTable;
    private JLabel invoiceNo;
    private JLabel invoiceDate;
    private JLabel customerName;
    private JLabel invoiceTotal;
    private JLabel invDate;
    private JLabel cusName;
    private JLabel no;
    private JLabel invTotal;


    private  NewCustomer newInvCustomer = new NewCustomer(this);
    private NewPurshase newItemPurshase= new NewPurshase(this);



    private MyListener myListener = new MyListener(this);
    private ArrayList<InvoiceItem>items;
    private ArrayList<Invoice> invoiceList;
    private InvoicesTable invoicesTable = new InvoicesTable();
    private InvoiceItemsTable invoiceItemsTable = new InvoiceItemsTable();


    Container container;
    private JPanel invoiceHeaderPanel;
    private JPanel invoicesTablePanel;
    private JPanel invoiceItemsPanel;
    private JPanel Panel2;
    private JPanel Panel4;
    private JPanel buttonPanel;
    private JPanel buttonPanel1;
    private JPanel buttonPanel2;
    private JPanel newPanel;



    public MyFrame() {
        super("Sales Invoice Generator");

        setSize(1265, 580);
        setLocation(400, 200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mb = new JMenuBar();
        fileMenu = new JMenu("File");
        loadFile = new JMenuItem("Load File", 'l');
        loadFile.setAccelerator(KeyStroke.getKeyStroke('L', KeyEvent.CTRL_DOWN_MASK));
        loadFile.addActionListener( myListener);
        loadFile.setActionCommand("L");
        saveFile = new JMenuItem("Save File", 's');
        saveFile.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));
        saveFile.addActionListener( myListener);
        saveFile.setActionCommand("S");

        setJMenuBar(mb);
        mb.add(fileMenu);
        fileMenu.add(loadFile);
        fileMenu.add(saveFile);
        container = getContentPane();


        Panel2 = new JPanel();
        Panel2.setLayout(new BoxLayout(Panel2, BoxLayout.Y_AXIS));
        invoicesTablePanel = new JPanel();
        invoicesTablePanel.setLayout(new GridLayout());
        invoiceHeaderPanel = new JPanel(new FlowLayout());
        invoiceItemsPanel = new JPanel(new GridLayout());
        Panel4 = new JPanel();
        Panel4.setLayout(new BoxLayout(Panel4, BoxLayout.Y_AXIS));
        newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        buttonPanel = new JPanel();
        buttonPanel1 = new JPanel();
        buttonPanel2 = new JPanel();

        invoicesTablePanel.setBorder(BorderFactory.createTitledBorder(" Invoice Table"));
        invoiceItemsPanel.setBorder(BorderFactory.createTitledBorder(" Invoice Items"));

        createNewInvoice = new JButton("Create New Invoice");
        createNewInvoice.setActionCommand("newInv");
        createNewInvoice.addActionListener(myListener);

        deleteInvoice = new JButton("Delete Invoice");
        deleteInvoice.setActionCommand("delete");
        deleteInvoice.addActionListener(myListener);

        createItem = new JButton("Create Item");
        createItem.setActionCommand("newItem");
        createItem.addActionListener(myListener);


        deleteItem = new JButton("Delete Item");
        deleteItem.setActionCommand("deleteItem");
        deleteItem.addActionListener(myListener);



        invoiceTable = new JTable();
        invoiceTable.getSelectionModel().addListSelectionListener(myListener);
        invoiceTable.setCellSelectionEnabled(true);
        invoiceTable.setModel(new DefaultTableModel(
                new Object [][] {},
                new String [] {"No.", "Date", "Customer", "Total"}));


        itemsTable = new JTable();
        itemsTable.getSelectionModel().addListSelectionListener(myListener);
        itemsTable.setCellSelectionEnabled(true);
        itemsTable.setModel(new DefaultTableModel(
                new Object [][] {},
                new String [] {"No.", "Item Name", "Item Price", "Count", "Item Total"} ));


        invoiceNo = new JLabel("Invoice Number");
        no = new JLabel(":          ");

        invoiceDate = new JLabel("Invoice Date: ");
        invDate = new JLabel("         ");

        customerName = new JLabel("Customer Name :");
        cusName = new JLabel("                 ");

        invoiceTotal = new JLabel("Invoice Total :");
        invTotal = new JLabel("      ");


        invoicesTablePanel.add(new JScrollPane(invoiceTable));
        invoiceItemsPanel.add(new JScrollPane(itemsTable));

        invoiceHeaderPanel.add(invoiceNo);
        invoiceHeaderPanel.add(no);
        invoiceHeaderPanel.add(invoiceDate);
        invoiceHeaderPanel.add(invDate);
        invoiceHeaderPanel.add(customerName);
        invoiceHeaderPanel.add(cusName);
        invoiceHeaderPanel.add(invoiceTotal);
        invoiceHeaderPanel.add(invTotal);
        invoiceHeaderPanel.setLayout(new GridLayout());
        Panel4.add(invoiceHeaderPanel);
        Panel2.add(Panel4);
        Panel2.add(new JScrollPane(invoiceItemsPanel));
        newPanel.add(Panel2);

        invoicesTablePanel.setBorder(BorderFactory.createTitledBorder(" Invoice Table"));
        invoiceItemsPanel.setBorder(BorderFactory.createTitledBorder(" Invoice Items"));

        buttonPanel.setLayout(new GridLayout());
        buttonPanel1.add(createNewInvoice);
        buttonPanel1.add(deleteInvoice);
        buttonPanel2.add(createItem);
        buttonPanel2.add(deleteItem);
        buttonPanel.add(buttonPanel1);
        buttonPanel.add(buttonPanel2);
        container.add(invoicesTablePanel, BorderLayout.WEST);
        container.add(newPanel, BorderLayout.EAST);
        container.add(buttonPanel, BorderLayout.PAGE_END);

    }
    public MyListener getMyListener() {
        return myListener;
    }

    public void setMyListener(MyListener myListener) {
        this.myListener = myListener;
    }
    public JButton getCreateNewInvoice() {
        return createNewInvoice;
    }

    public void setCreateNewInvoice(JButton createNewInvoice) {
        this.createNewInvoice = createNewInvoice;
    }

    public JButton getDeleteInvoice() {
        return deleteInvoice;
    }

    public void setDeleteInvoice(JButton deleteInvoice) {
        this.deleteInvoice = deleteInvoice;
    }

    public JButton getCreateItem() {
        return createItem;
    }

    public void setCreateItem(JButton createItem) {
        this.createItem = createItem;
    }


    public Invoice getNum(int number){
        for (Invoice invoice : invoiceList){
            if (invoice.getInvNu() == number) {
                return invoice;
            }
        }
        return null;
    }

    public JLabel getInvDate() {
        return invDate;
    }

    public void setInvDate(JLabel invDate) {
        this.invDate = invDate;
    }

    public JLabel getCusName() {
        return cusName;
    }

    public void setCusName(JLabel cusName) {
        this.cusName = cusName;
    }

    public JLabel getNo() {
        return no;
    }

    public void setNo(JLabel no) {
        this.no = no;
    }

    public JLabel getInvTotal() {
        return invTotal;
    }

    public void setInvTotal(JLabel invTotal) {
        this.invTotal = invTotal;
    }

    public ArrayList<InvoiceItem>getItems(){return items;}

    public void setItems(ArrayList<InvoiceItem> itemArrayList){this.items = itemArrayList;}

    public void setInvoiceList(ArrayList<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public ArrayList<Invoice> getInvoiceList() {
        return invoiceList;
    }
    public void setInvTable(InvoicesTable invTable) {this.invoicesTable = invTable;}
    public InvoicesTable getInvoicesTable(){ return invoicesTable;}
    public InvoiceItemsTable getInvoiceItemsTable(){return invoiceItemsTable;}


    public JTable getInvoiceData() {return invoiceTable;}

    public void setInvoiceTable(JTable invoiceTable) {
        this.invoiceTable = invoiceTable;
    }



    public JTable getItemsJTable() {
        return itemsTable;
    }

    public void setItemsJTable(JTable itemsJTable) {
        this.itemsTable = itemsTable;
    }



    public NewCustomer getNewInvCustomer(){return newInvCustomer;}
    public void setNewInvCustomer(NewCustomer newInvCustomer) {
        this.newInvCustomer = newInvCustomer;
    }

    public NewPurshase getNewItemPurshase(){return newItemPurshase;}
    public void setNewItemPurshase(NewPurshase newItemPurshase) {
        this.newItemPurshase = newItemPurshase;
    }


    public void setInvoicesTable() {this.invoicesTable = invoicesTable;
    }
}
