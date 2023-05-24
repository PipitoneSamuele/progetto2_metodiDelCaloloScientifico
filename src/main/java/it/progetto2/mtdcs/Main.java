package it.progetto2.mtdcs;

import it.progetto2.mtdcs.utility.GUI_Handler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        /*
          Finestra principale
         */
        JFrame frame = GUI_Handler.initMainFrame();

        /*
         * Pannello superiore
        */
        JPanel topPanel = GUI_Handler.createPanel(Color.green, 0,0,1200,100);

        /*
         * Pannello centro sinistra
        */
        JLayeredPane midPanelLeft = new JLayeredPane();
        midPanelLeft.setBounds(0,100,600,600);

        JPanel mplTop1 = GUI_Handler.createPanel(Color.cyan, 0, 0, 600, 25);
        JPanel mplTop2 = GUI_Handler.createPanel(Color.yellow, 0, 25, 600, 75);
        JPanel mplCenter = GUI_Handler.createPanel(Color.pink, 0, 100, 600, 400);
        JPanel mplBottom = GUI_Handler.createPanel(Color.MAGENTA, 0, 500, 600, 100);

        JLabel mplLabel = new JLabel("Usa il bottone per selezionare l'immagine da caricare:");
        mplLabel.setBounds(0,0,350,10);

        JButton mplButton = new JButton("Scegli foto");
        mplButton.addActionListener(e -> uploadImage());
        mplButton.setLayout(null);
        mplButton.setBounds(10,20,100,100);

        BufferedImage buffImage = ImageIO.read(new File("C:\\Users\\samue\\OneDrive\\Desktop\\universita\\MAGISTRALE_1_anno\\Metodi_del_calcolo_scientifico\\progetto 2\\progetto2_metodiDelCaloloScientifico\\src\\main\\resources\\images\\20x20.bmp"));
        Image image = buffImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel Jimage = new JLabel(new ImageIcon(image));

        mplTop1.add(mplLabel);
        mplTop2.add(mplButton);
        mplCenter.add(Jimage);

        midPanelLeft.add(mplTop1);
        midPanelLeft.add(mplTop2);
        midPanelLeft.add(mplCenter);
        midPanelLeft.add(mplBottom);

        /*
         * Pannello centro destra
        */
        JPanel midPanelRight = GUI_Handler.createPanel(Color.gray, 600,100,600,600);

        /*
         * Pannello inferiore
        */
        JPanel bottomPanel = GUI_Handler.createPanel(Color.red, 0,700,1200,100);

        /*
         * Aggiunta elementi al frame principale
        */
        frame.add(topPanel);
        frame.add(midPanelLeft);
        frame.add(midPanelRight);
        frame.add(bottomPanel);

        frame.revalidate();
    }

    private static void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./src/main/resources/images"));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "bmp");
        fileChooser.addChoosableFileFilter(filter);

        int choice = fileChooser.showOpenDialog(null);

        if(choice == JFileChooser.APPROVE_OPTION) {
            String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
            System.out.println(imagePath);
        } else {
            System.out.println("No image selected");
        }
    }

}
