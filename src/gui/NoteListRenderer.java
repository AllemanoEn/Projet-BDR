package gui;

import db.Biere;

import javax.swing.*;
import java.awt.*;

public class NoteListRenderer extends JLabel implements ListCellRenderer<String> {

    public NoteListRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends String> jList, String note,
                                                  int index, boolean isSelected, boolean cellHasFocus) {

        setText(note);

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
