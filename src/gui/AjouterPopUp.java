package gui;

import javax.swing.*;

public class AjouterPopUp extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel addBiereTab;
    private JPanel addOrientationTab;
    private JPanel addSoftTab;
    private JPanel addEventTab;

    public AjouterPopUp(MainView mainView) {
        setSize(800, 600);
        setContentPane(panel1);
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mainView.setVisible(true);
            }
        });
    }
}
