package gui;

import javax.swing.*;

public class AdminPanel extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JPanel addBiereTab;
    private JPanel addSoftTab;
    private JPanel addEventTab;
    private JPanel bierePanel;

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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        JLabel jLabel = new JLabel();
        jLabel.setText("aa");
    }
}
