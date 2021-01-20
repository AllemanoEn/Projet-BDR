package gui;

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

                Timestamp oui = Timestamp.valueOf(eventDate);

                try {
                    mainView.idbAccess.createEvent(eventName,oui,null, 0, 0);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        try {

            listBoisson.setListData(mainView.idbAccess.getSoftDrinks());
            //listBoisson.setListData(mainView.idbAccess.getBeers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}


