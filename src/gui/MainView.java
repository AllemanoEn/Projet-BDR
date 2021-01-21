package gui;

import db.Biere;
import db.IDBAccess;
import db.Utilisateur;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainView extends JFrame {
    private JPanel mainPanel;
    private JPanel selectionPanel;
    private JPanel InfoPanel;
    private JButton seConnecterButton;
    private JPasswordField mdpPasswordField;
    private JTextField utilisateurTextField;
    private JLabel label_status;
    private JCheckBox adminCheckBox;
    private JTabbedPane tabbedPane1;
    private JPanel soft_tab;
    private JPanel biere_tab;
    private JPanel event_tab;
    private JPanel orientation_tab;
    private JScrollPane soft_scroll;
    private JScrollPane event_scroll;
    private JScrollPane orientation_scroll;
    private JButton ajouterButton;
    private JList beerLB;
    private JPanel beerInfo;
    private JLabel beerName;
    private JLabel beerPercentage;
    private JLabel beerQuantity;
    private JLabel beerRegion;
    private JLabel beerCountry;
    private JLabel Biere;
    private JList listSoft;
    private JList listOrientation;
    private JButton addEventBT;
    private JList listNote;
    private JList listComment;
    private JTextArea commentArea;
    private JButton btnAddComment;
    private JComboBox cbNote;
    private JList nbBiereBuesList;
    private JLabel labelNbStock;
    private AdminPanel displayAddPopUp;

    private Utilisateur u;

    IDBAccess idbAccess;

    public MainView(IDBAccess DBprojet) throws SQLException {
        setSize(1024, 800);
        setContentPane(mainPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        idbAccess = DBprojet;

        idbAccess.startDB();

        seConnecterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                u = new Utilisateur(utilisateurTextField.getText(), mdpPasswordField.getText());

                try {

                    if(idbAccess.login(u)){
                        label_status.setForeground( new Color(0,255,0));
                        label_status.setText(u.getPseudo() + " est connecté !");
                        idbAccess.setUser(u);
                        if (u.isAdmin()){
                            ajouterButton.setEnabled(true);
                        }else {
                            ajouterButton.setEnabled(false);
                        }


                        // Enable buttons if logged in
                        addEventBT.setEnabled(true);
                        btnAddComment.setEnabled(true);
                    }
                    else {
                        label_status.setForeground( new Color(255,0,0));
                        label_status.setText("offline");
                        u = null;
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                AdminPanel ajouterPopUp = new AdminPanel(MainView.this);

            }
        });

        // List boisson soft
        listSoft.setListData(idbAccess.getSoftDrinks());


        String[] res = idbAccess.getOrientationLeaderboard();
        String[] orientation = new String[res.length];
        String[] nbBieresBues = new String[res.length];
        int i = 0;
        for(String str : res) {
            String[] vals = str.split(",");
            orientation[i] = vals[0];
            nbBieresBues[i] = vals[1];
            i++;
        }
        // Classement des orientations
        listOrientation.setListData(orientation);
        nbBiereBuesList.setListData(nbBieresBues);

        beerLB.setCellRenderer(new BiereListRenderer());


        updateNoteBiere();

        beerLB.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if (!listSelectionEvent.getValueIsAdjusting()) {

                    if (beerLB.getSelectedIndex() == -1) {

                    } else {
                        // show beer info
                        Biere biere = (db.Biere)beerLB.getSelectedValue();

                        beerName.setText(biere.getName());
                        beerCountry.setText(biere.getPays());
                        beerPercentage.setText("" + biere.getPourcentage());
                        beerQuantity.setText("" + biere.getContenance());
                        beerRegion.setText(biere.getRegion());
                        labelNbStock.setText("" + biere.getQuantiteStock());

                        try {
                            listComment.setCellRenderer(new CommentListRenderer());
                            listComment.setListData(idbAccess.getComments(biere.getName()));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
            }
        });

        addEventBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                EventPanel eventPanel = new EventPanel(MainView.this);
            }
        });

        btnAddComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


                if(beerLB.getSelectedIndex() == -1) {
                    return;
                }

                String comment = commentArea.getText();
                String name = ((db.Biere)beerLB.getSelectedValue()).getName();
                int note = Integer.decode(String.valueOf(cbNote.getSelectedItem()));

                try {
                    idbAccess.addComment(note, comment, u, name);
                    listComment.setListData(idbAccess.getComments(name));   // maj comment

                    commentArea.setText("");
                    cbNote.setSelectedIndex(0);

                    updateNoteBiere();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        utilisateurTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                utilisateurTextField.setText("");
            }
        });
        mdpPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                mdpPasswordField.setText("");
            }
        });
    }


    // MAJ du classement des bières
    public void updateNoteBiere() throws SQLException{
        beerLB.setCellRenderer(new BiereListRenderer());

        Biere[] beers = idbAccess.getBeers();
        beerLB.setListData(beers);

        ArrayList<String> arrayList = new ArrayList<>();
        for(Biere biere : beers) {
            arrayList.add(String.format("%.1f", biere.getNoteMoyenne()));
        }

        listNote.setCellRenderer(new NoteListRenderer());
        listNote.setListData(arrayList.toArray(new String[0]));
    }
}