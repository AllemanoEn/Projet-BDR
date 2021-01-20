package gui;

import db.Boisson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;



public class EventPanel extends JFrame{
    private JButton buttonajouter;
    private JPanel panel1main;
    private JTextField nom;
    private JTextField date;
    private JTabbedPane tabbedPane1;
    private JList listBoisson;
    private JTextField textField1;
    private JList listTable;
    private JButton Verify;
    Timestamp oui;

    public EventPanel(MainView mainView) {
        super("Event Panel");
        setSize(800, 600);
        setContentPane(panel1main);
        setVisible(true);


        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mainView.setVisible(true);
            }
        });

        buttonajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventDate = date.getText();
                String eventName = nom.getText();

                oui = Timestamp.valueOf(eventDate);

                try {
                    mainView.idbAccess.createEvent(eventName,oui,null, 0, 0);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        try {
            Boisson[] first = mainView.idbAccess.getSoftDrinks();
            Boisson[] second = mainView.idbAccess.getBeers();
            int i = first.length;
            int j = second.length;
            int datalength = i+ j;
            Boisson[] data = new Boisson[datalength];

            System.arraycopy(first,0,data,0,i);
            System.arraycopy(second,0,data,i,j);
            listBoisson.setListData(data);


            listTable.setListData(mainView.idbAccess.getTables(oui));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Verify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventDate = date.getText();
                oui = Timestamp.valueOf(eventDate);
                try {
                    listTable.setListData(mainView.idbAccess.getTables(oui));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

}


