package gui;

import db.Biere;

import javax.swing.*;
import java.awt.*;

public class BiereListRenderer extends JLabel implements ListCellRenderer<Biere> {

    public BiereListRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Biere> jList, Biere biere,
                                                  int index, boolean isSelected, boolean cellHasFocus) {

        setText(String.format("%d) %-25s", index+1, biere.getName()));

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
