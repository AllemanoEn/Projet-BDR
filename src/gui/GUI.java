package gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import db.*;

public class GUI {
    IDBAccess idbAccess;

    public void show() {

        try {
            idbAccess = new DB();

            idbAccess.startDB();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }


        JFrame frame = new JFrame("ChillOut");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Creating the MenuBar and adding components
        /*JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);
         */

/*
        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);




        // Text Area at the Center
        JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        //frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(frame.setVisible(true);.CENTER, ta);
        frame.setVisible(true);

 */

        JPanel panelLB = new JPanel();

        String[] leaderBoard = new String[3];

        JList jList = new JList(getLeaderBoard());
        JList jList2 = new JList(getLeaderBoard());
        panelLB.add(jList);

        frame.getContentPane().add(BorderLayout.WEST , panelLB);
        frame.setVisible(true);

    }

    private JPanel genLeaderBoard() {
        JPanel lb = new JPanel();

        try {
            ResultSet resultSet = idbAccess.getLeaderboard();


            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                
                //lb.add(new J)
                //list.add(String.format("%-40s:%2s", resultSet.getString(1), resultSet.getString(2)));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return lb;
    }

    public String[] getLeaderBoard() {
        LinkedList<String> list = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();

        try {
            ResultSet resultSet = idbAccess.getLeaderboard();


            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                list.add(String.format("%-40s:%2s", resultSet.getString(1), resultSet.getString(2)));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list.toArray(new String[list.size()]);
    }
}