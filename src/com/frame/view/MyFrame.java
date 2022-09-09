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


    private final JTable invoiceTable;
    private final JTable itemsTable;
    private final JLabel invDate;
    private final JLabel cusName;
    private final JLabel no;
    private final JLabel invTotal;


    public MyListener myListener = new MyListener(this);
    private ArrayList<InvoiceItem>items;
    private ArrayList<Invoice> invoiceList;
    private InvoicesTable invoicesTable = new InvoicesTable();
    private final InvoiceItemsTable invoiceItemsTable = new InvoiceItemsTable();


    Container container;


    public MyFrame() {
        super("Sales Invoice Generator");

        setSize(1280, 580);
        setLocation(400, 200);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadFile = new JMenuItem("Load File", 'l');
        loadFile.setAccelerator(KeyStroke.getKeyStroke('L', KeyEvent.CTRL_DOWN_MASK));
        loadFile.addActionListener( myListener);
        loadFile.setActionCommand("L");
        JMenuItem saveFile = new JMenuItem("Save File", 's');
        saveFile.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));
        saveFile.addActionListener( myListener);
        saveFile.setActionCommand("S");

        setJMenuBar(mb);
        mb.add(fileMenu);
        fileMenu.add(loadFile);
        fileMenu.add(saveFile);
        container = getContentPane();


        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        JPanel invoicesTablePanel = new JPanel();
        invoicesTablePanel.setLayout(new GridLayout());
        JPanel invoiceHeaderPanel = new JPanel(new FlowLayout());
        JPanel invoiceItemsPanel = new JPanel(new GridLayout());
        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel();
        JPanel buttonPanel1 = new JPanel();
        JPanel buttonPanel2 = new JPanel();

        invoicesTablePanel.setBorder(BorderFactory.createTitledBorder(" Invoice Table"));
        invoiceItemsPanel.setBorder(BorderFactory.createTitledBorder(" Invoice Items"));

        JButton createNewInvoice = new JButton("Create New Invoice");
        createNewInvoice.setActionCommand("New Inv");
        createNewInvoice.addActionListener(myListener);

        JButton deleteInvoice = new JButton("Delete Invoice");
        deleteInvoice.setActionCommand("Delete");
        deleteInvoice.addActionListener(myListener);

        JButton createItem = new JButton("Create Item");
        createItem.setActionCommand("New Item");
        createItem.addActionListener(myListener);


        JButton deleteItem = new JButton("Delete Item");
        deleteItem.setActionCommand("Delete Item");
        deleteItem.addActionListener(myListener);



        invoiceTable = new JTable();
        invoiceTable.getSelectionModel().addListSelectionListener(myListener);
        invoiceTable.setCellSelectionEnabled(true);
        invoiceTable.setModel(new DefaultTableModel(
                new Object [][] {},
                new String [] {"No.", "Date", "Customer", "Total"}));


        itemsTable = new JTable();
        itemsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemsTable.getSelectionModel().addListSelectionListener(myListener);
        itemsTable.setCellSelectionEnabled(true);
        itemsTable.setModel(new DefaultTableModel(
                new Object [][] {},
                new String [] {"No.", "Item Name", "Item Price", "Count", "Item Total"} ));


        JLabel invoiceNo = new JLabel("Invoice Number");
        no = new JLabel(":          ");

        JLabel invoiceDate = new JLabel("Invoice Date: ");
        invDate = new JLabel("         ");

        JLabel customerName = new JLabel("Customer Name :");
        cusName = new JLabel("                 ");

        JLabel invoiceTotal = new JLabel("Invoice Total :");
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
        panel4.add(invoiceHeaderPanel);
        panel2.add(panel4);
        panel2.add(new JScrollPane(invoiceItemsPanel));
        newPanel.add(panel2);

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

    public JLabel getCusName() {
        return cusName;
    }

    public JLabel getNo() {
        return no;
    }

    public JLabel getInvTotal() {
        return invTotal;
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


    public JTable getItemsJTable() {
        return itemsTable;
    }


}
