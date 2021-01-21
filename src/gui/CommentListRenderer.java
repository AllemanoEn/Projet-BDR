package gui;

import db.Commentaire;

import javax.swing.*;
import java.awt.*;

public class CommentListRenderer extends JPanel implements ListCellRenderer<Commentaire> {

    JLabel pseudoLabel = new JLabel("");
    JTextArea comment = new JTextArea();
    JLabel note = new JLabel();


    //JLabel comment = new JLabel("");

    public CommentListRenderer() {
        //setOpaque(true);
        this.setLayout(new GridLayout());
        add(comment);
        add(note);
        add(pseudoLabel);

        //note.setSize(new Dimension(5, 10));


        comment.setSize(new Dimension(100, 50));
        comment.setLineWrap(true);
        comment.setWrapStyleWord(true);



        pseudoLabel.setFont(new Font("Arial", Font.ITALIC, 15));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Commentaire> jList, Commentaire commentaire,
                                                  int index, boolean isSelected, boolean cellHasFocus) {

        pseudoLabel.setText("by " + commentaire.getUserName());
        //textArea.setText(commentaire.getComment());
        note.setText("Note: " + commentaire.getNote());
        comment.setText(commentaire.getComment());


     /*   if (isSelected) {
            setBackground(jList.getSelectionBackground());
            setForeground(jList.getSelectionForeground());
        } else {
            setBackground(jList.getBackground());
            setForeground(jList.getForeground());
        }

      */

        return this;
    }
}