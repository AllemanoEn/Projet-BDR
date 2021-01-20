package gui;

import db.IDBAccess;
import db.Utilisateur;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainView extends JFrame {
    private JPanel mainPanel;
    private JPanel selectionPanel;
    private JPanel InfoPanel;
    private JButton seConnecterButton;
    private JPasswordField mdpPasswordField;
    private JTextField utilisateurTextField;
    private JLabel label_status;
    private JCheckBox adminCheckBox;
    private JTabbedPane tabbedPane1;
    private JPanel soft_tab;
    private JPanel biere_tab;
    private JPanel event_tab;
    private JPanel orientation_tab;
    private JScrollPane soft_scroll;
    private JScrollPane biere_scroll;
    private JScrollPane event_scroll;
    private JScrollPane orientation_scroll;
    private JButton ajouterButton;
    private JList beerLB;
    private JPanel beerInfo;
    private JLabel beerName;
    private JLabel beerPercentage;
    private JLabel beerQuantity;
    private JLabel beerRegion;
    private JLabel beerCountry;
    private JLabel Biere;
    private JLabel Note;
    private AjouterPopUp displayAddPopUp;

    private IDBAccess idbAccess;

    public MainView(IDBAccess DBprojet) throws SQLException {
        setSize(800, 600);
        setContentPane(mainPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        idbAccess = DBprojet;

        DBprojet.startDB();

        seConnecterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Utilisateur u = new Utilisateur(utilisateurTextField.getText(), mdpPasswordField.getText(),adminCheckBox.isSelected());

                try {
                    if(DBprojet.login(u)){
                        label_status.setForeground( new Color(0,255,0));
                        label_status.setText(u.getPseudo() + " est connecté !");
                        DBprojet.setUser(u);
                        /*if (u.isAdmin()){
                            ajouterButton.setEnabled(true);
                        }else {
                            ajouterButton.setEnabled(false);
                        }*/
                    }
                    else {
                        label_status.setForeground( new Color(255,0,0));
                        label_status.setText("offline");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                AjouterPopUp ajouterPopUp = new AjouterPopUp(MainView.this);


            }
        });


        beerLB.setListData(genLeaderBoard());

        beerLB.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if (!listSelectionEvent.getValueIsAdjusting()) {

                    if (beerLB.getSelectedIndex() == -1) {


                    } else {
                        // show beer info
                        beerName.setText((String) beerLB.getSelectedValue());
                    }
                }
            }
        });
    }

    private String[] genLeaderBoard() {

        ArrayList<String> arrayList = new ArrayList<>();
        int i = 1;
        try {
            ResultSet resultSet = idbAccess.getLeaderboard();

            while(resultSet.next()) {
                String str = String.format("%2d) %-25s %.1f", i++, resultSet.getString(1), resultSet.getDouble(2));
                System.out.println(str);
                arrayList.add(str);
            }

        } catch (SQLException e) {

        }

        return arrayList.toArray(new String[0]);
    }
}