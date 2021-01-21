package gui;

import db.Commentaire;

import javax.swing.*;
import java.awt.*;

public class CommentListRenderer extends JLabel implements ListCellRenderer<Commentaire> {

    public CommentListRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Commentaire> jList, Commentaire note,
                                                  int index, boolean isSelected, boolean cellHasFocus) {



        if (isSelected) {
            setBackground(jList.getSelectionBackground());
            setForeground(jList.getSelectionForeground());
        } else {
            setBackground(jList.getBackground());
            setForeground(jList.getForeground());
        }

        return this;
    }
}