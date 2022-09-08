package com.frame.view;
import com.frame.control.MyListener;
import javax.swing.*;
import java.awt.*;




public class NewCustomer extends JDialog {

    private JLabel invNumberLabel;
    private JLabel invNumberLabel2;
    private JLabel createNCLabel;
    private JTextField createNCField;
    private JLabel invDateLabel;
    private JTextField invDateField;

    private JButton createBtn;
    private JButton cancelBtn;

private MyListener myListener ;

    public NewCustomer (MyFrame frame) {



        setLayout(new FlowLayout());

        invNumberLabel = new JLabel("NO");
        add(invNumberLabel);
        invNumberLabel2 = new JLabel(".       ");
        add(invNumberLabel2);



        createNCLabel = new JLabel("Customer Name");
        add(createNCLabel);


        createNCField = new JTextField(15);
        add(createNCField);

        invDateLabel = new JLabel("Date        ");
        add(invDateLabel);

        invDateField = new JTextField(15);
        add(invDateField);


        createBtn = new JButton("Create New Customer");
        createBtn.setActionCommand("create");

        createBtn.addActionListener(frame.getMyListener());
        add(createBtn);

        cancelBtn = new JButton("Cancel Customer");
        cancelBtn.setActionCommand("cancelCustomer");
        cancelBtn.addActionListener(frame.getMyListener());
        add(cancelBtn);



        setTitle("Add New Customer");
        setSize(610, 110);
        setLocation(400, 250);
        setResizable(false);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

   //getter and setter

    public JLabel getInvNumberLabel2() {
        return invNumberLabel2;
    }

    public void setInvNumberLabel2(JLabel invNumberLabel2) {
        this.invNumberLabel2 = invNumberLabel2;
    }


    public JTextField getCreateNCField() {

        return createNCField;
    }

    public void setCreateNCField(JTextField createNCField) {
        this.createNCField = createNCField;
    }


    public JTextField getInvDateField() {
        return invDateField;
    }

    public void setInvDateField(JTextField invDateField) {
        this.invDateField = invDateField;
    }



}
