package it.progetto2.mtdcs.GUI;

import it.progetto2.mtdcs.utility.GUI_Handler;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {

    public JFrame initMainFrame() {
        JFrame frame = new JFrame();
        frame = new JFrame();
        frame.setTitle("Progetto 2: DCT2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }

}
