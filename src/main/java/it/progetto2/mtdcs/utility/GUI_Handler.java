package it.progetto2.mtdcs.utility;

import javax.swing.*;
import java.awt.*;

public class GUI_Handler {

    public static JLabel addLabel(String text, int positionX, int positionY) {
        JLabel label = new JLabel(text, positionX);
        label.setVerticalAlignment(positionY);
        return label;
    }

    public static JPanel createPanel(Color color, int x, int y, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBounds(x, y, width, height);
        panel.setBackground(color);
        return panel;
    }

}
