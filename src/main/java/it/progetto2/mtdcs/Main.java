package it.progetto2.mtdcs;

import it.progetto2.mtdcs.GUI.MainGUI;
import it.progetto2.mtdcs.utility.GUI_Handler;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JPanel topPanel = GUI_Handler.createPanel(Color.green, 0,0,1200,100);

        JPanel midPanelLeft = GUI_Handler.createPanel(Color.yellow, 0,100,600,600);

        JPanel midPanelRight = GUI_Handler.createPanel(Color.gray, 600,100,600,600);

        JPanel bottomPanel = GUI_Handler.createPanel(Color.red, 0,700,1200,100);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1200, 800);
        frame.setVisible(true);

        frame.add(topPanel);
        frame.add(midPanelLeft);
        frame.add(midPanelRight);
        frame.add(bottomPanel);
    }

}
