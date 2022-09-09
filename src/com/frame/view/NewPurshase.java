package com.frame.view;
import javax.swing.*;
import java.awt.*;


public class NewPurshase extends JDialog  {
    private JLabel itemNameLabel;
    private JTextField itemNameField;
    private JLabel itemPriceLabel;
    private JTextField itemPriceField;
    private JLabel countLabel;
    private JTextField countField;
    private JButton createItemBtn;
    private JButton cancelItemBtn;


    public NewPurshase(MyFrame frame){


        setLayout(new FlowLayout());
        itemNameLabel = new JLabel("Item Name");
        add(itemNameLabel);
        itemNameField = new JTextField(15);
        add(itemNameField);
        itemPriceLabel = new JLabel("Item Price");
        add(itemPriceLabel);
        itemPriceField = new JTextField(15);
        add(itemPriceField);
        countLabel = new JLabel("Count");
        add(countLabel);
        countField = new JTextField(15);
        add(countField);

        createItemBtn = new JButton("Create New Item");
        createItemBtn.setActionCommand("CreateNewItem");
        createItemBtn.addActionListener(frame.myListener);
        add(createItemBtn);

        cancelItemBtn = new JButton("Cancel Item");
        cancelItemBtn.setActionCommand("Cancel Item");
        cancelItemBtn.addActionListener(frame.myListener);
        add(cancelItemBtn);



        setTitle("Add New Item");
        setSize(710, 105);
        setLocation(300, 250);
        setResizable(false);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }



    public JTextField getItemNameField() {
        return itemNameField;
    }

    public void setItemNameField(JTextField itemNameField) {
        this.itemNameField = itemNameField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }

    public void setItemPriceField(JTextField itemPriceField) {
        this.itemPriceField = itemPriceField;
    }

    public JTextField getCountField() {
        return countField;
    }

    public void setCountField(JTextField countField) {
        this.countField = countField;
    }

}
