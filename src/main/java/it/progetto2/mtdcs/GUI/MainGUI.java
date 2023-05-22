package it.progetto2.mtdcs.GUI;

import javax.swing.*;

public class MainGUI extends JFrame {

    public MainGUI() {
        super("titolo");
        setSize(400, 400); //dimensione finestra
        setLocationRelativeTo(null); //finestra centro
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //chiusura processo
        setVisible(true);
    }

}
