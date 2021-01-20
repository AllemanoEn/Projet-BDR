package gui;

import javax.swing.*;

public class EventPanel extends JFrame{
    private JButton buttonmaj;
    private JButton buttonsupprimer;
    private JButton buttonajouter;
    private JPanel panel1main;

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
    }

}


