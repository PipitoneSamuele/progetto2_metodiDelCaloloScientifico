package it.progetto2.mtdcs.GUI;

import it.progetto2.mtdcs.utility.GUI_Handler;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {

    private JFrame mainFrame;

    public MainGUI() {
        super("DCT2");
        init();

        JPanel firstPanel = new JPanel();
        firstPanel.setBackground(Color.green);
        firstPanel.setBounds(0, 0, 250, 250);

        mainFrame.add(firstPanel);
    }

    private void init() {
        mainFrame = new JFrame();
        mainFrame.setSize(750, 750); //dimensione finestra
        mainFrame.setLocationRelativeTo(null); //finestra centro
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //chiusura processo
        mainFrame.setVisible(true);
    }

}
