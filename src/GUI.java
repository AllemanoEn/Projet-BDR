import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GUI {
    public static void main(String ... args) throws SQLException {
        
        IDBAccess oui = new DB();
        oui.startDB();
        //oui.createUser("a","a","a","2");

        oui.getOrientationLeaderboard();

        System.out.println("-----");
        oui.getDrink("Boxer");

        System.out.println("-----");
        if (oui.login("Paul", "mypassword")){
            System.out.println("Paul est log");
        }
        else{
            System.out.println("Paul est pas log");
        }

        System.out.println("-----");
        if (oui.login("Marc", "dutrou")){
            System.out.println("Marc n'est log");
        }
        else{
            System.out.println("Marc est pas log");
        }

        try{
            oui.addDrink("pipi",1,300,10,5);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        /*
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);

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
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
        
     
         */
    }
}