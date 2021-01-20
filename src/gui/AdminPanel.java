package gui;

import javax.swing.*;

public class AdminPanel extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JPanel addBiereTab;
    private JPanel addSoftTab;
    private JTextField textField1Nom;
    private JTextField textField2Pourcentage;
    private JTextField textField3PrixAchat;
    private JTextField textField4Quantite;
    private JTextField textField5PrixVente;
    private JButton supprimerButton;
    private JButton mettreÀJourButton;
    private JButton ajouterButton;
    private JRadioButton estUnAlcoolRadioButton;
    private JTextField textField6Contenance;
    private JComboBox comboBox1Provenance;
    private JComboBox comboBox2Style;
    private JButton buttonajouter;
    private JButton buttonmaj;
    private JButton buttonsupprimer;

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
    }
}
