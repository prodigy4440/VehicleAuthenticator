/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prodigy4440.model;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamLock;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author prodigy4440
 */
public class TakeSnapshot extends JDialog{

    private int state = 0;

    private BufferedImage bufferedImage;

    private JButton snapJButton;
    private JButton cancelJButton;
    private JPanel southJPanel;

    public Webcam webcam;
    private WebcamPanel imageJPanel;

    public TakeSnapshot(JDialog frame) {
        initComponents();
        setLocationRelativeTo(frame);
    }

    public final void initComponents() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
        this.setLayout(new BorderLayout(30, 30));
        this.setSize(360, 340);
        webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(WebcamResolution.QVGA.getSize()));
        boolean open = webcam.open();

        if (!open) {
            JOptionPane.showMessageDialog(this, "Webcam not present", "Webcam Status", JOptionPane.ERROR_MESSAGE);
        }

        imageJPanel = new WebcamPanel(webcam);
        imageJPanel.setFPSDisplayed(true);
        imageJPanel.setFillArea(true);

        cancelJButton = new JButton("Cancel");
        snapJButton = new JButton("Snap");

        ActionHandler actionHandler = new ActionHandler(this);
        snapJButton.addActionListener(actionHandler);
        cancelJButton.addActionListener(actionHandler);

        southJPanel = new JPanel();
        southJPanel.add(snapJButton);
        southJPanel.add(cancelJButton);

        this.add(imageJPanel, BorderLayout.CENTER);
        this.add(southJPanel, BorderLayout.SOUTH);
    }

    public class ActionHandler implements ActionListener {

        private final TakeSnapshot takeSnapshot;

        public ActionHandler(TakeSnapshot takeSnapshot) {
            this.takeSnapshot = takeSnapshot;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == takeSnapshot.cancelJButton) {

                boolean close = webcam.close();
                WebcamLock lock = webcam.getLock();
                lock.unlock();
                if (!close) {
                    JOptionPane.showMessageDialog(takeSnapshot, "Unable to stop webcam", "Webcam Status", JOptionPane.ERROR_MESSAGE);
                }
                takeSnapshot.dispose();
            } else if (e.getSource() == takeSnapshot.snapJButton) {
                state = 1;
                bufferedImage = webcam.getImage();
                try {
                    ImageIO.write(bufferedImage, "jpg", new File("image.jpg"));
                } catch (IOException ex) {
                    Logger.getLogger(TakeSnapshot.class.getName()).log(Level.SEVERE, null, ex);
                }
                boolean close = webcam.close();
                WebcamLock lock = webcam.getLock();
                lock.unlock();
                if (!close) {
                    JOptionPane.showMessageDialog(takeSnapshot, "Unable to stop webcam", "Webcam Status", JOptionPane.ERROR_MESSAGE);
                }
                takeSnapshot.dispose();
            }
        }

    }

    public BufferedImage getSnapShot() {
        return bufferedImage;
    }

}
