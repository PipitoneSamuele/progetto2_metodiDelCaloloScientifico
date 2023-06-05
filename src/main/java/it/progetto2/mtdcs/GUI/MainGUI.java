package it.progetto2.mtdcs.GUI;

import it.progetto2.mtdcs.utility.GUI_Handler;
import it.progetto2.mtdcs.utility.MatrixUtility;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainGUI {

    private String imagePath = "";
    private JFrame frame;
    private JPanel mplCenter;
    private boolean uploadedImage = false;
    private int ampiezza;
    private int taglio;
    private JTextField intAmpiezzaTextField;
    private JTextField intTaglioFreqTextField;

    public MainGUI() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                /*
                 * Finestra principale
                 */
                frame = GUI_Handler.initMainFrame();

                /*
                 * Pannello superiore
                 */
                JPanel topPanel = GUI_Handler.createPanel(Color.white, 0,25,1200,75);

                JLabel title = new JLabel("Progetto 2 Metodi del calcolo scientifico");
                title.setFont(new Font("Roboto", Font.PLAIN, 20));

                topPanel.add(title);

                /*
                 * Pannello centro sinistra
                 */
                JLayeredPane midPanelLeft = new JLayeredPane();
                midPanelLeft.setBounds(0,100,600,600);

                JPanel mplTop1 = GUI_Handler.createPanel(Color.white, 0, 0, 600, 25);
                JPanel mplTop2 = GUI_Handler.createPanel(Color.white, 0, 25, 600, 50);

                mplCenter = GUI_Handler.createPanel(Color.white, 0, 75, 600, 425);

                JPanel mplBottom1 = GUI_Handler.createPanel(Color.white, 0, 500, 300, 35);
                JPanel mplBottom11 = GUI_Handler.createPanel(Color.white, 0, 525, 300, 35);
                JPanel mplBottom2 = GUI_Handler.createPanel(Color.white, 300, 500, 300, 35);
                JPanel mplBottom21 = GUI_Handler.createPanel(Color.white, 300, 525, 300, 35);
                JPanel mplBottom = GUI_Handler.createPanel(Color.white, 0, 560, 600, 100);

                JLabel mplLabel = new JLabel("Usa il bottone per selezionare l'immagine da caricare:");
                mplLabel.setBounds(0,0,350,10);

                JButton mplButton = new JButton("Scegli foto");
                mplButton.addActionListener(e -> {
                    try {
                        uploadImage();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                mplButton.setLayout(null);
                mplButton.setBounds(10,20,100,100);

                JLabel intAmpiezzaLabel = new JLabel("Inserisci l'ampiezza delle finestre F:");
                JLabel intTaglioFreqLabel = new JLabel("Inserisci il taglio delle frequenze:");
                intAmpiezzaTextField = new JTextField(20);
                intTaglioFreqTextField = new JTextField(20);
                JButton submitButton = new JButton("Submit");
                submitButton.addActionListener(e -> submit());

                mplTop1.add(mplLabel);
                mplTop2.add(mplButton);

                mplBottom1.add(intAmpiezzaLabel);
                mplBottom11.add(intAmpiezzaTextField);
                mplBottom2.add(intTaglioFreqLabel);
                mplBottom21.add(intTaglioFreqTextField);
                mplBottom.add(submitButton);

                midPanelLeft.add(mplTop1);
                midPanelLeft.add(mplTop2);
                midPanelLeft.add(mplCenter);
                midPanelLeft.add(mplBottom1);
                midPanelLeft.add(mplBottom11);
                midPanelLeft.add(mplBottom2);
                midPanelLeft.add(mplBottom21);
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

            }
        });
    }

    private void uploadImage() throws Exception {
        if(!uploadedImage) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("./src/main/resources/images"));

            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "bmp");
            fileChooser.addChoosableFileFilter(filter);

            int choice = fileChooser.showOpenDialog(null);

            if (choice == JFileChooser.APPROVE_OPTION) {
                imagePath = fileChooser.getSelectedFile().getAbsolutePath();
                BufferedImage buffImage = ImageIO.read(new File(imagePath));
                Image image = buffImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                JLabel Jimage = new JLabel(new ImageIcon(image));
                Jimage.setVisible(true);
                uploadedImage = true;
                mplCenter.add(Jimage);
                reload();
                MatrixUtility.test(8, 8);
            } else {
                System.out.println("No image selected");
            }
        }
    }

    private void submit() {
        try {
            ampiezza = Integer.parseInt(intAmpiezzaTextField.getText());
            taglio = Integer.parseInt(intTaglioFreqTextField.getText());
        } catch(Exception e) {
            //TODO: gestisci caso in cui input non valido
            System.out.println("Non valido");
        }
    }

    private void reload() {
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

}
