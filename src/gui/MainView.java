package gui;

import db.IDBAccess;
import db.Utilisateur;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private AjouterPopUp displayAddPopUp;

    public MainView(IDBAccess DBprojet) throws SQLException {
        setSize(800, 600);
        setContentPane(mainPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        beerLB.setListData(new String[]{"BFM", "Cuvee"});

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
}
