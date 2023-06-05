package it.progetto2.mtdcs.GUI;

import it.progetto2.mtdcs.utility.GUI_Handler;
import it.progetto2.mtdcs.compression.Compression;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainGUI {

    private String imagePath = "";
    private JFrame frame;
    private JPanel mplCenter;
    private JPanel mplRight;
    private boolean uploadedImage = false;
    private boolean compressedImage = false;
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
                JPanel topPanel = GUI_Handler.createPanel(Color.decode("#edebeb"), 0,25,1200,75);

                JLabel title = new JLabel("Progetto 2 Metodi del calcolo scientifico");
                title.setFont(new Font("Roboto", Font.PLAIN, 20));

                topPanel.add(title);

                /*
                 * Pannello centro sinistra
                 */
                JLayeredPane midPanelLeft = new JLayeredPane();
                midPanelLeft.setBounds(0,100,600,600);

                JPanel mplTop1 = GUI_Handler.createPanel(Color.decode("#edebeb"), 0, 0, 600, 25);
                JPanel mplTop2 = GUI_Handler.createPanel(Color.decode("#edebeb"), 0, 25, 600, 50);

                mplCenter = GUI_Handler.createPanel(Color.decode("#edebeb"), 0, 75, 600, 425);

                JPanel mplBottom1 = GUI_Handler.createPanel(Color.decode("#edebeb"), 0, 500, 300, 35);
                JPanel mplBottom11 = GUI_Handler.createPanel(Color.decode("#edebeb"), 0, 525, 300, 35);
                JPanel mplBottom2 = GUI_Handler.createPanel(Color.decode("#edebeb"), 300, 500, 300, 35);
                JPanel mplBottom21 = GUI_Handler.createPanel(Color.decode("#edebeb"), 300, 525, 300, 35);
                JPanel mplBottom = GUI_Handler.createPanel(Color.decode("#edebeb"), 0, 560, 600, 100);

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

                JLayeredPane midPanelRight = new JLayeredPane();
                midPanelRight.setBounds(600,100,600,600);

                JPanel mprTop = GUI_Handler.createPanel(Color.decode("#edebeb"), 0, 0, 600, 75);
                mplRight = GUI_Handler.createPanel(Color.decode("#edebeb"), 0, 75, 600, 425);

                JPanel mprBottom = GUI_Handler.createPanel(Color.decode("#edebeb"), 0, 500, 600, 100);

                midPanelRight.add(mprTop);
                midPanelRight.add(mplRight);
                midPanelRight.add(mprBottom);

                /*
                 * Pannello inferiore
                 */
                JPanel bottomPanel = GUI_Handler.createPanel(Color.decode("#edebeb"), 0,700,1200,100);
                /*
                JButton reloadButton = new JButton("Reload");

                reloadButton.setLayout(null);
                reloadButton.setBounds(0,20,100,100);
                reloadButton.addActionListener(e -> {
                    WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
                    frame.setVisible(true);
                });

                bottomPanel.add(reloadButton);*/

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
            } else {
                System.out.println("No image selected");
            }
        }
    }

    private void submit() {
        if(!compressedImage) {
            try {
                int ampiezza = Integer.parseInt(intAmpiezzaTextField.getText());
                int taglio = Integer.parseInt(intTaglioFreqTextField.getText());
                
                BufferedImage buffImageCompressed = Compression.compress(ampiezza, taglio, imagePath);
                
                //Salvo il file compresso sul file system in formato jpg (nomefile_compressed.jpg)
                //in modo da poter confrontare le dimensioni del file originale con quello compresso
                //PS: so che non era richiesto ma era facile da fare e secondo me é interessante :)
                File file = new File(imagePath);
                System.out.println("DIMENSIONE FILE ORIGINALE: " + file.length() + "Bytes");
                String imagePathWithoutExtension = imagePath.replaceAll("\\.\\w+","");
                File outputfile = new File(imagePathWithoutExtension + "_compressed.jpg");
                ImageIO.write(buffImageCompressed, "jpg", outputfile);
                System.out.println("DIMENSIONE FILE COMPRESSO: " + outputfile.length() + "Bytes");
                System.out.println("IL FILE COMPRESSO OCCUPA IL " + (outputfile.length() / (float)file.length()) * 100 + "% DEL FILE ORIGINALE");
                
                Image imageCompressed = buffImageCompressed.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                JLabel JimageCompressed = new JLabel(new ImageIcon(imageCompressed));
                JimageCompressed.setVisible(true);
                uploadedImage = true;
                mplRight.add(JimageCompressed);
                compressedImage = true;
                reload();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void reload() {
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

}
