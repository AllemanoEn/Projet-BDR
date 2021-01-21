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
    private JComboBox cbBoisson;
    private JButton ajouterTransaction;
    private JList utilisateurList;
    private JTextField tfPseudo;
    private JTextField tfEmail;
    private JTextField tfPassword;
    private JButton bAddUtilisateur;
    private JCheckBox isAdmin;
    private JComboBox cbUtilisateur;

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
            cbBoisson.setModel(new DefaultComboBoxModel<String>(mainView.idbAccess.getAllBoissons()));
            cbUtilisateur.setModel(new DefaultComboBoxModel<String>(mainView.idbAccess.getAllUsers()));

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
                            try {
                                mainView.idbAccess.addBeer(tfNomBiere.getText(),
                                        Integer.parseInt(tfQuantiteBiere.getText()),
                                        Double.parseDouble(tfPrixVenteBiere.getText()),
                                        Double.parseDouble(tfPrixAchatBiere.getText()),
                                        Double.parseDouble(tfPourcentageBiere.getText()),
                                        Double.parseDouble(tfContenanceBiere.getText()),
                                        tfPaysBiere.getText(),
                                        tfRegion.getText(),
                                        tfBrasserie.getText(),
                                        tfStyleBiere.getText()
                                        );

                                softList.setListData(mainView.idbAccess.getSoftDrinks());
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }


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
                        isAdmin.setSelected(u.isAdmin());

                    }
                }
            }
        });
        ajouterBiereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mainView.idbAccess.addDrink(tfNameSoft.getText(),
                            1,
                            Integer.parseInt(tfQuantitySoft.getText()),
                            Double.parseDouble(tfPrixVenteSoft.getText()),
                            Double.parseDouble(tfPrixAchatSoft.getText()));

                    softList.setListData(mainView.idbAccess.getSoftDrinks());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        bAddUtilisateur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mainView.idbAccess.createUser(tfPseudo.getText(),
                            tfPassword.getText(),
                            tfEmail.getText(),
                            cbOrientation.getSelectedIndex(),
                            isAdmin.isSelected());

                    softList.setListData(mainView.idbAccess.getSoftDrinks());

                    utilisateurList.setListData(mainView.idbAccess.getUsers());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        majBiereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    mainView.idbAccess.updateBeer(tfNomBiere.getText(), Integer.parseInt(tfQuantiteBiere.getText()),
                            Double.parseDouble(tfPrixVenteBiere.getText()), Double.parseDouble(tfPrixAchatBiere.getText()),
                                    Double.parseDouble(tfContenanceBiere.getText()), Double.parseDouble(tfPourcentageBiere.getText()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        buttonmaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    mainView.idbAccess.updateDrink(tfNameSoft.getText(), Integer.parseInt(tfQuantitySoft.getText()),
                            Double.parseDouble(tfPrixVenteSoft.getText()), Double.parseDouble(tfPrixAchatSoft.getText()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        ajouterTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mainView.idbAccess.addTransaction(cbUtilisateur.getSelectedItem().toString(),cbBoisson.getSelectedItem().toString(),Integer.parseInt(quantiteTF.getText()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }
}
