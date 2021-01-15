import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.LinkedList;

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
        //oui.createUser("a","a","a","2");

        //oui.getOrientationLeaderboard();

        //System.out.println("\n-----");
        //oui.getDrink("Boxer");


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

        JList jList = new JList(leaderBoard);
        panelLB.add(jList);

        frame.getContentPane().add(BorderLayout.WEST , panelLB);
        frame.setVisible(true);

    }

    public String[] getLeaderBoard() {
        LinkedList<String> list = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();

        try {
            ResultSet resultSet = idbAccess.getLeaderboard();


            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = resultSet.getString(i);
                    //System.out.print(columnValue + " " + rsmd.getColumnName(i));
                    stringBuilder.append(columnValue);
                    stringBuilder.append(" ");

;                }
                list.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list.toArray(new String[list.size()]);
    }
}