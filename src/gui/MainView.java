package gui;

import javax.swing.*;

public class MainView extends JFrame {
    private JPanel mainPanel;
    private JPanel lbPanel;
    private JPanel drinkInfoPanel;

    public MainView() {
        setSize(800, 600);
        setContentPane(mainPanel);
        setVisible(true);
    }
}
