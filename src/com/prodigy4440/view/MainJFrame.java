/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prodigy4440.view;

import com.prodigy4440.model.Account;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToolBar;

/**
 *
 * @author prodigy4440
 */
public class MainJFrame extends JFrame {

    private JMenuBar menuBar;

    private JMenu fileJMenu;
    private JMenu vehicleJMenu;
    private JMenu viewJMenu;
    private JMenu preferenceJMenu;
    private JMenu helpJMenu;

    private JMenuItem newEntryJMenuItem;
    private JMenuItem vehicleExitJMenuItem;
    private JMenuItem saveAsJMenuItem;
    private JMenuItem printJMenuItem;
    private JMenuItem exitJMenuItem;

    private JMenuItem registerVehicleJMenuItem;
    private JMenuItem updateVehicleJMenuItem;
    private JMenuItem deleteVehicleJMenuItem;

    private JMenuItem entriesViewJMenuItem;
    private JMenuItem registrationViewJMenuItem;

    private JMenuItem newUserJMenuItem;
    private JMenuItem updateUserJMenuItem;
    private JMenuItem deleteUserJMenuItem;
    private JMenuItem optionsJMenuItem;

    private JMenuItem contentsJMenuItem;
    private JMenuItem updateJMenuItem;
    private JMenuItem feedbackJMenuItem;
    private JMenuItem licenseJMenuItem;
    private JMenuItem aboutJMenuItem;

    private JPanel toolBarJPanel;
    private JPanel statusBarJPanel;
    private JPanel mainJPanel;

    private JToolBar toolBar;
    private JTable table;
    
    private Account account;

    public MainJFrame(Account a) {
        initComponents();
        this.account = a;
//        this.setLocationRelativeTo(null);
    }

    public final void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Vehicle Authenticator");
        table = new JTable();

        ActionHandler actionHandler = new ActionHandler(this);

        toolBarJPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        statusBarJPanel = new JPanel();
        mainJPanel = new JPanel(new BorderLayout(10, 10));

        toolBar = new JToolBar("General Tools", JToolBar.HORIZONTAL);

        menuBar = new JMenuBar();

        fileJMenu = new JMenu("File");
        vehicleJMenu = new JMenu("Vehicles");
        viewJMenu = new JMenu("View");
        preferenceJMenu = new JMenu("Preferences");
        helpJMenu = new JMenu("Help");

        newEntryJMenuItem = new JMenuItem("New Entry");
        newEntryJMenuItem.addActionListener(actionHandler);
        vehicleExitJMenuItem = new JMenuItem("Vehicle Exit");
        vehicleExitJMenuItem.addActionListener(actionHandler);
        saveAsJMenuItem = new JMenuItem("Save As");
        saveAsJMenuItem.addActionListener(actionHandler);
        printJMenuItem = new JMenuItem("Print");
        printJMenuItem.addActionListener(actionHandler);
        exitJMenuItem = new JMenuItem("Exit");
        exitJMenuItem.addActionListener(actionHandler);

        registerVehicleJMenuItem = new JMenuItem("Register Vehicle");
        registerVehicleJMenuItem.addActionListener(actionHandler);
        updateVehicleJMenuItem = new JMenuItem("Update Vehicle");
        updateVehicleJMenuItem.addActionListener(actionHandler);
        deleteVehicleJMenuItem = new JMenuItem("Delete Vehicle");
        deleteVehicleJMenuItem.addActionListener(actionHandler);

        entriesViewJMenuItem = new JMenuItem("Entries");
        entriesViewJMenuItem.addActionListener(actionHandler);
        registrationViewJMenuItem = new JMenuItem("Registrations");
        registrationViewJMenuItem.addActionListener(actionHandler);

        newUserJMenuItem = new JMenuItem("New User");
        newUserJMenuItem.addActionListener(actionHandler);
        updateUserJMenuItem = new JMenuItem("Update User");
        updateUserJMenuItem.addActionListener(actionHandler);
        deleteUserJMenuItem = new JMenuItem("Delete User");
        deleteUserJMenuItem.addActionListener(actionHandler);
        optionsJMenuItem = new JMenuItem("Options");
        optionsJMenuItem.addActionListener(actionHandler);

        contentsJMenuItem = new JMenuItem("Contents");
        contentsJMenuItem.addActionListener(actionHandler);
        updateJMenuItem = new JMenuItem("Update");
        updateJMenuItem.addActionListener(actionHandler);
        feedbackJMenuItem = new JMenuItem("Feedback");
        feedbackJMenuItem.addActionListener(actionHandler);
        licenseJMenuItem = new JMenuItem("License");
        licenseJMenuItem.addActionListener(actionHandler);
        aboutJMenuItem = new JMenuItem("About");
        aboutJMenuItem.addActionListener(actionHandler);

        //Add menuitems to filemenu
        fileJMenu.add(newEntryJMenuItem);
        fileJMenu.add(vehicleExitJMenuItem);
        fileJMenu.addSeparator();
        fileJMenu.add(saveAsJMenuItem);
        fileJMenu.addSeparator();
        fileJMenu.add(printJMenuItem);
        fileJMenu.addSeparator();
        fileJMenu.add(exitJMenuItem);

        //Add menuitems to vehicle
        vehicleJMenu.add(registerVehicleJMenuItem);
        vehicleJMenu.add(updateVehicleJMenuItem);
        vehicleJMenu.add(deleteVehicleJMenuItem);

        //Add menuitems to view
        viewJMenu.add(registrationViewJMenuItem);
        viewJMenu.add(entriesViewJMenuItem);

        //Add menuitems to preference
        preferenceJMenu.add(newUserJMenuItem);
        preferenceJMenu.add(updateUserJMenuItem);
        preferenceJMenu.add(deleteUserJMenuItem);
        preferenceJMenu.addSeparator();
        preferenceJMenu.add(optionsJMenuItem);

        //Add Menuitems to help
        helpJMenu.add(contentsJMenuItem);
        helpJMenu.add(updateJMenuItem);
        helpJMenu.addSeparator();
        helpJMenu.add(feedbackJMenuItem);
        helpJMenu.add(licenseJMenuItem);
        helpJMenu.addSeparator();
        helpJMenu.add(aboutJMenuItem);

        //Add Menus to JMenuBar
        menuBar.add(fileJMenu);
        menuBar.add(vehicleJMenu);
        menuBar.add(viewJMenu);
        menuBar.add(preferenceJMenu);
        menuBar.add(helpJMenu);

        //Create and Add ToolBar to the north panel
        toolBar =createJToolBar(false);
        toolBarJPanel.add(toolBar);
        
        //Add table to the main panel
        mainJPanel.add(table,BorderLayout.CENTER);
        
        //setting up the frame
        this.setJMenuBar(menuBar);
        this.add(toolBarJPanel, BorderLayout.NORTH);
        this.add(mainJPanel, BorderLayout.CENTER);
        this.add(statusBarJPanel, BorderLayout.SOUTH);
    }

    public JToolBar createJToolBar(boolean status) {
        JToolBar tB = new JToolBar("General Tools", JToolBar.HORIZONTAL);

        JButton newEntryJButton = new JButton(new ImageIcon(getClass().getResource("/com/prodigy4440/resources/entry.jpg")));
        newEntryJButton.setToolTipText("New Entry");
        JButton exitEntryJButton = new JButton(new ImageIcon(getClass().getResource("/com/prodigy4440/resources/exit.jpg")));
        exitEntryJButton.setToolTipText("Exit Entry");
        JButton saveAsJButton = new JButton(new ImageIcon(getClass().getResource("/com/prodigy4440/resources/save.png")));
        saveAsJButton.setToolTipText("Save");
        JButton printJButton = new JButton(new ImageIcon(getClass().getResource("/com/prodigy4440/resources/print.jpg")));
        printJButton.setToolTipText("Print");
        JButton vehicleRegisterJButton = new JButton(new ImageIcon(getClass().getResource("/com/prodigy4440/resources/registervehicle.jpg")));
        vehicleRegisterJButton.setToolTipText("New Registration");
        JButton vehicleUpdateJButton = new JButton(new ImageIcon(getClass().getResource("/com/prodigy4440/resources/updatevehicle.jpg")));
        vehicleUpdateJButton.setToolTipText("Update Registration");
        JButton vehicleDeleteJButton = new JButton(new ImageIcon(getClass().getResource("/com/prodigy4440/resources/deletevehicle.jpg")));
        vehicleDeleteJButton.setToolTipText("Delete Registration");
        JButton newUserJButton = new JButton(new ImageIcon(getClass().getResource("/com/prodigy4440/resources/newuser.jpg")));
        newUserJButton.setToolTipText("New User");
        JButton updateUserJButton = new JButton(new ImageIcon(getClass().getResource("/com/prodigy4440/resources/updateuser.png")));
        updateUserJButton.setToolTipText("Update User");
        JButton deleteUserJButton = new JButton(new ImageIcon(getClass().getResource("/com/prodigy4440/resources/deleteuser.jpg")));
        deleteUserJButton.setToolTipText("Delete User");
        
        
        if(status){
            vehicleUpdateJButton.setEnabled(false);
            vehicleDeleteJButton.setEnabled(false);
            newUserJButton.setEnabled(false);
            updateUserJButton.setEnabled(false);
            deleteUserJButton.setEnabled(false);
        }
        tB.add(newEntryJButton);
        tB.add(exitEntryJButton);
        tB.add(saveAsJButton);
        tB.add(printJButton);
        tB.addSeparator();
        tB.add(vehicleRegisterJButton);
        tB.add(vehicleUpdateJButton);
        tB.add(vehicleDeleteJButton);
        tB.addSeparator();
        tB.add(newUserJButton);
        tB.add(updateUserJButton);
        tB.add(deleteUserJButton);
        
        return tB;
    }

    public class ActionHandler implements ActionListener {

        private JFrame frame;

        public ActionHandler(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == newEntryJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == vehicleExitJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == saveAsJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == printJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == exitJMenuItem) {
                System.exit(0);
            } else if (e.getSource() == registerVehicleJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == updateVehicleJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == deleteVehicleJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == entriesViewJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == registrationViewJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == newUserJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == updateUserJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == deleteUserJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == optionsJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == contentsJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == updateJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == feedbackJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == licenseJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == aboutJMenuItem) {
                JOptionPane.showMessageDialog(frame, "Operation", e.getActionCommand() + " under construction ", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }


}
