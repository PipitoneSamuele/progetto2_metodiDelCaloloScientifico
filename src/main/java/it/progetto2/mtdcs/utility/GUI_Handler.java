package it.progetto2.mtdcs.utility;

import javax.swing.*;
import java.awt.*;

public class GUI_Handler {

    public static JFrame initMainFrame() {
        JFrame frame = new JFrame();

        ImageIcon icon = new ImageIcon("C:\\Users\\samue\\OneDrive\\Desktop\\universita\\MAGISTRALE_1_anno\\Metodi_del_calcolo_scientifico\\progetto 2\\progetto2_metodiDelCaloloScientifico\\src\\main\\resources\\icons\\mainIcon.png");
        frame.setIconImage(icon.getImage());

        frame.setTitle("Progetto 2: DCT2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }

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
