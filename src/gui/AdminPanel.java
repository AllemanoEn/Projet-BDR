package gui;

import db.Biere;
import db.Boisson;
import db.Utilisateur;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminPanel extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JPanel addBiereTab;
    private JPanel addSoftTab;
    private JTextField tfNomBiere;
    private JTextField tfPourcentageBiere;
    private JTextField tfPrixAchatBiere;
    private JTextField tfQuantiteBiere;
    private JTextField tfPrixVenteBiere;
    private JButton supprimerBiereButton;
    private JButton majBiereButton;
    private JButton ajouterBiereButton;
    private JTextField tfContenanceBiere;
    private JButton buttonajouter;
    private JButton buttonmaj;
    private JButton buttonsupprimer;
    private JList beerList;
    private JTextField tfPaysBiere;
    private JTextField tfStyleBiere;
    private JList softList;
    private JTextField tfNameSoft;
    private JTextField tfQuantitySoft;
    private JTextField tfPrixVenteSoft;
    private JTextField tfPrixAchatSoft;
    private JTextField tfRegion;
    private JTextField tfBrasserie;
    private JPanel addUserTab;
    private JComboBox cbOrientation;
    private JRadioButton rbIsAdmin;
    private JScrollPane UserList;
    private JPanel addTransactionTab;
    private JTextField quantiteTF;
    private JComboBox boissonCB;
    private JButton ajouterTransaction;
    private JList utilisateurList;
    private JTextField tfPseudo;
    private JTextField tfEmail;
    private JTextField tfPassword;

    public AdminPanel(MainView mainView) {
        super("Admin Panel");
        setSize(800, 600);
        setContentPane(mainPanel);
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mainView.setVisible(true);
            }
        });

        try {
            beerList.setListData(mainView.idbAccess.getBeers());
            softList.setListData(mainView.idbAccess.getSoftDrinks());
            utilisateurList.setListData(mainView.idbAccess.getUsers());

            cbOrientation.setModel(new DefaultComboBoxModel<String>(mainView.idbAccess.getOrientation()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        beerList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if (!listSelectionEvent.getValueIsAdjusting()) {

                    if (beerList.getSelectedIndex() == -1) {

                    } else {

                        // show beer info
                        Biere biere = (db.Biere)beerList.getSelectedValue();

                        tfNomBiere.setText(biere.getName());

                        tfPourcentageBiere.setText("" + biere.getPourcentage());
                        tfContenanceBiere.setText("" + biere.getContenance());
                        tfPrixAchatBiere.setText("" + biere.getPrixAchat());
                        tfPrixVenteBiere.setText("" + biere.getPrixVente());
                        tfQuantiteBiere.setText("" + biere.getQuantiteStock());

                        tfPaysBiere.setText(biere.getPays());
                        tfRegion.setText(biere.getRegion());
                        tfBrasserie.setText(biere.getBrasserie());
                        tfStyleBiere.setText(biere.getTypeBiere());
                    }
                }
            }
        });



        softList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if (!listSelectionEvent.getValueIsAdjusting()) {

                    if (softList.getSelectedIndex() == -1) {

                    } else {

                        // show drink info
                        Boisson boisson = (Boisson) softList.getSelectedValue();

                        tfNameSoft.setText(boisson.getName());

                        tfPrixAchatSoft.setText("" + boisson.getPrixAchat());
                        tfPrixVenteSoft.setText("" + boisson.getPrixVente());
                        tfQuantitySoft.setText("" + boisson.getQuantiteStock());

                    }
                }
            }
        });

        ajouterBiereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    if(mainView.idbAccess.getDrink(tabbedPane1.getSelectedComponent() == addBiereTab ? tfNomBiere.getText() : tfNameSoft.getText()) == null) {
                        if(tabbedPane1.getSelectedComponent() == addBiereTab) {
                            //mainView.idbAccess.addDrink(tfNomBiere);
                            // TODO addBiere in DB.java
                        }

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        utilisateurList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if (!listSelectionEvent.getValueIsAdjusting()) {

                    if (utilisateurList.getSelectedIndex() == -1) {

                    } else {

                        // show user info
                        Utilisateur u = (Utilisateur) utilisateurList.getSelectedValue();

                        tfPseudo.setText(u.getPseudo());
                        tfEmail.setText(u.getEmail());
                        tfPassword.setText(u.getPassword());
                        cbOrientation.setSelectedItem(u.getOrientation());
                        rbIsAdmin.setSelected(u.isAdmin());

                    }
                }
            }
        });
    }


    private void disableTextFields() {
        tfBrasserie.setEnabled(false);
        tfNameSoft.setEnabled(false);
        tfPaysBiere.setEnabled(false);
        tfStyleBiere.setEnabled(false);
        tfContenanceBiere.setEnabled(false);
        tfPourcentageBiere.setEnabled(false);
        tfRegion.setEnabled(false);
        tfNomBiere.setEnabled(false);
    }

    private void enableTextFields() {
        tfBrasserie.setEnabled(true);
        tfNameSoft.setEnabled(true);
        tfPaysBiere.setEnabled(true);
        tfStyleBiere.setEnabled(true);
        tfContenanceBiere.setEnabled(true);
        tfPourcentageBiere.setEnabled(true);
        tfRegion.setEnabled(true);
        tfNomBiere.setEnabled(true);
    }
}
