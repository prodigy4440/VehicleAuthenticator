/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prodigy4440.view;

import com.prodigy4440.model.Entry;
import com.prodigy4440.model.GeneralUtil;
import com.prodigy4440.model.HibernateUtil;
import com.prodigy4440.model.TakeSnapshot;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import org.hibernate.Session;

/**
 *
 * @author prodigy4440
 */
public class NewEntryJDialog extends JDialog {

    private BufferedImage driverBufferedImage = null;
    private BufferedImage plateBufferedImage = null;

    private JPanel southJPanel;
    private JPanel northJPanel;

    private JPanel driverImageJPanel;
    private JPanel plateNumberJPanel;

    private JButton driverImageCaptureJButton;
    private JButton plateNumberImageCaptureJButton;

    private JSplitPane splitPane;

    private JLabel driverImageJLabel;
    private JLabel plateImageJLabel;

    private JLabel tagJLabel;
    private JLabel plateNumberJLabel;
    private JLabel entryTypeJLabel;
    private JLabel registrationNumberJLabel;
    private JLabel timeInJLabel;
    private JLabel timeOutJLabel;

    private JTextField tagJTextField;
    private JTextField plateNumberJTextField;
    private JComboBox entryTypeJComboBox;
    private JTextField registrationNumberJTextField;
    private JTextField timeInJTextField;
    private JTextField timeOutJTextField;

    private JPanel buttonJPanel;

    private JButton saveJButton;
    private JButton cancelJButton;

    public NewEntryJDialog() {
        this.setTitle("New Vehicle Entry");
        initComponents();
        setLocationRelativeTo(null);
    }

    public NewEntryJDialog(String title, JFrame frame) {
        this.setTitle(title);
        initComponents();
        this.setLocationRelativeTo(frame);
    }

    public final void initComponents() {
//        this.setResizable(false);
        this.setModal(true);
        this.setModalityType(ModalityType.DOCUMENT_MODAL);
        this.setSize(670, 630);
        this.setLayout(new BorderLayout(20, 20));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //Action Handler for this form
        ActionHandler actionHandler = new ActionHandler(this);

        //Setting up the button panel and it buttons
        buttonJPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveJButton = new JButton(" Save ");
        cancelJButton = new JButton("Cancel");
        saveJButton.addActionListener(actionHandler);
        cancelJButton.addActionListener(actionHandler);
        buttonJPanel.add(cancelJButton);
        buttonJPanel.add(saveJButton);

        //Instantiate each object created
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(1.0);

        //Creating the section panels
        northJPanel = new JPanel();
        northJPanel.setBorder(BorderFactory.createTitledBorder("Pictures"));
        southJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southJPanel.setBorder(BorderFactory.createTitledBorder("Vehicle Details"));

        //Setting the bufferedImages up
        try {
            driverBufferedImage = ImageIO.read(getClass().getResource("/com/prodigy4440/resources/image.png"));
            plateBufferedImage = ImageIO.read(getClass().getResource("/com/prodigy4440/resources/image.png"));
        } catch (IOException ex) {
            Logger.getLogger(NewEntryJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

        //instantaite the labels
        driverImageJLabel = new JLabel(new ImageIcon(driverBufferedImage));
        plateImageJLabel = new JLabel(new ImageIcon(plateBufferedImage));

        //Add components to the splitpane
        splitPane.setDividerSize(10);
        splitPane.setTopComponent(northJPanel);
        splitPane.setBottomComponent(southJPanel);

        driverImageJPanel = new JPanel(new BorderLayout());
        plateNumberJPanel = new JPanel(new BorderLayout());

        //create the buttons for the captures
        driverImageCaptureJButton = new JButton("Capture Driver");
        plateNumberImageCaptureJButton = new JButton("Capture Plate");
        driverImageCaptureJButton.addActionListener(actionHandler);
        plateNumberImageCaptureJButton.addActionListener(actionHandler);

        //Initializing the core variables
        tagJLabel = new JLabel("Vehicle Tag:      ");
        tagJTextField = new JTextField(40);
        tagJTextField.setEditable(false);
        plateNumberJLabel = new JLabel("Plate Number:  ");
        plateNumberJTextField = new JTextField(40);
        plateNumberJTextField.setEditable(false);
        String types[] = {"Registered                                                                                                 ", "Visitor"};
        entryTypeJLabel = new JLabel("Entry Type:        ");
        entryTypeJComboBox = new JComboBox(new DefaultComboBoxModel(types));
        entryTypeJComboBox.addActionListener(actionHandler);
        registrationNumberJLabel = new JLabel("Reg No:             ");
        registrationNumberJTextField = new JTextField(40);
        registrationNumberJTextField.setEditable(false);
        timeInJLabel = new JLabel("Time In:             ");
        timeInJTextField = new JTextField(40);
        timeInJTextField.setEditable(false);
        timeInJTextField.setText(new Date().toString());
        timeOutJLabel = new JLabel("Time Out:          ");
        timeOutJTextField = new JTextField(40);
        timeOutJTextField.setEditable(false);

        //Setting label for components
        tagJLabel.setLabelFor(tagJTextField);
        plateNumberJLabel.setLabelFor(plateNumberJTextField);
        entryTypeJLabel.setLabelFor(entryTypeJComboBox);
        registrationNumberJLabel.setLabelFor(registrationNumberJTextField);
        timeInJLabel.setLabelFor(timeInJTextField);
        timeOutJLabel.setLabelFor(timeOutJTextField);

        //Add contents to the driver and plate panel
        driverImageJPanel.add(driverImageJLabel, BorderLayout.CENTER);
        plateNumberJPanel.add(plateImageJLabel, BorderLayout.CENTER);
        driverImageJPanel.add(driverImageCaptureJButton, BorderLayout.SOUTH);
        plateNumberJPanel.add(plateNumberImageCaptureJButton, BorderLayout.SOUTH);

        //Main Contents JPanel
        JPanel mainJPanel = new JPanel(new GridLayout(6, 1));
        mainJPanel.setSize(mainJPanel.getWidth(), mainJPanel.getHeight());
        JPanel panel = new JPanel();
        panel.add(tagJLabel);
        panel.add(tagJTextField);
        JPanel panel1 = new JPanel();
        panel1.add(plateNumberJLabel);
        panel1.add(plateNumberJTextField);
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel2.add(entryTypeJLabel);
        panel2.add(entryTypeJComboBox);
        JPanel panel3 = new JPanel();
        panel3.add(registrationNumberJLabel);
        panel3.add(registrationNumberJTextField);
        JPanel panel4 = new JPanel();
        panel4.add(timeInJLabel);
        panel4.add(timeInJTextField);
        JPanel panel5 = new JPanel();
        panel5.add(timeOutJLabel);
        panel5.add(timeOutJTextField);

        //Joining the panels together
        mainJPanel.add(panel);
        mainJPanel.add(panel1);
        mainJPanel.add(panel2);
        mainJPanel.add(panel3);
        mainJPanel.add(panel4);
        mainJPanel.add(panel5);
        southJPanel.add(mainJPanel);

        //Add component to the north panel
        northJPanel.add(new JScrollPane(driverImageJPanel));
        northJPanel.add(new JScrollPane(plateNumberJPanel));

        //Add components to the dialog
        this.add(splitPane, BorderLayout.CENTER);
        this.add(buttonJPanel, BorderLayout.SOUTH);

    }

    public class ActionHandler implements ActionListener {

        NewEntryJDialog newEntryJDialog;

        public ActionHandler(NewEntryJDialog newEntryJDialog) {
            this.newEntryJDialog = newEntryJDialog;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            if (actionEvent.getSource() == newEntryJDialog.saveJButton) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Entry entry = new Entry();
                entry.setDriverImage(GeneralUtil.imageToByteArray(driverBufferedImage));
                String type = (String) entryTypeJComboBox.getSelectedItem();

                if (type.trim().equalsIgnoreCase("Registered")) {
                    entry.setEntryType('R');
                } else {
                    entry.setEntryType('V');
                }

                entry.setPlateNumber(plateNumberJTextField.getText());
                entry.setRegistrationNo(registrationNumberJTextField.getText());
                entry.setTag(tagJTextField.getText());
                entry.setTimeIn(new Date());
                session.save(entry);
            } else if (actionEvent.getSource() == newEntryJDialog.cancelJButton) {
                newEntryJDialog.dispose();
            } else if (actionEvent.getSource() == newEntryJDialog.entryTypeJComboBox) {
                System.out.println(actionEvent.getActionCommand());
            } else if (actionEvent.getSource() == newEntryJDialog.driverImageCaptureJButton) {
                TakeSnapshot takeSnapshot = new TakeSnapshot(newEntryJDialog);
                takeSnapshot.setVisible(true);
                newEntryJDialog.driverBufferedImage = takeSnapshot.getSnapShot();
                newEntryJDialog.driverImageJLabel.setIcon(new ImageIcon(takeSnapshot.getSnapShot()));
                newEntryJDialog.repaint();
            } else if (actionEvent.getSource() == newEntryJDialog.plateNumberImageCaptureJButton) {
                TakeSnapshot takeSnapshot = new TakeSnapshot(newEntryJDialog);
                takeSnapshot.setVisible(true);
                newEntryJDialog.plateBufferedImage = takeSnapshot.getSnapShot();
                newEntryJDialog.plateImageJLabel.setIcon(new ImageIcon(takeSnapshot.getSnapShot()));
                newEntryJDialog.repaint();
            }
        }
    }

    public static void main(String args[]) {
        new NewEntryJDialog().setVisible(true);
    }
}
