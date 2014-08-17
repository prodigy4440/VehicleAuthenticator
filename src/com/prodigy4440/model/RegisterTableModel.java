/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prodigy4440.model;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author prodigy4440
 */
public class RegisterTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -5161744212565377856L;
    private final List<Registration> registeredList = new LinkedList<>();
    private final String[] columns = {"S/N", "Surname", "firstname", "Sex", "Registration No", "License ID", "License Expiry Date", "Vehicle Make", "Vehicle Model", "Plate Number"};

    public RegisterTableModel() {
    }

    public RegisterTableModel(LinkedList<Registration> list) {
        list.stream().forEach((registration) -> {
            this.registeredList.add(registration);
        });
        fireTableDataChanged();
    }

    public Registration addRegistration(Registration registration) {
        this.registeredList.add(registration);
        fireTableDataChanged();
        return registration;
    }

    public Registration getRegistration(int row) {
        return this.registeredList.get(row);
    }

    public Registration removeRegistration(int row) {
        Registration remove = this.registeredList.remove(row);
        fireTableDataChanged();
        return remove;
    }

    public void clearRegistrations() {
        this.registeredList.clear();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.registeredList.size();
    }

    @Override
    public int getColumnCount() {
        return this.columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            case 7:
                return String.class;
            case 8:
                return String.class;
            case 9:
                return String.class;
        }
        return String.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Registration registration = this.registeredList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return (rowIndex+1);
            case 1:
                if (registration.getDriverSurname().isEmpty()) {
                    return "";
                } else {
                    return registration.getDriverSurname();
                }
            case 2:
                if (registration.getDriverLastName().isEmpty()) {
                    return "";
                } else {
                    return registration.getDriverLastName();
                }
            case 3:
                if (registration.getDriverSex() == 'M') {
                    return "Male";
                } else if (registration.getDriverSex() == 'F') {
                    return "Female";
                } else {
                    return "";
                }
            case 4:
                
                if(registration.getRegistrationNo().isEmpty()){
                    return "";
                }else{
                    return registration.getRegistrationNo();
                }
            case 5:
                if(registration.getLicenseId().isEmpty()){
                    return "";
                }else{
                    return registration.getLicenseId();
                }
            case 6:
                if(registration.getLicenseExpiryDate() == null){
                    return "";
                }else{
                    return registration.getLicenseExpiryDate();
                }
            case 7:
                if(registration.getVehicleMake().isEmpty()){
                    return "";
                }else{
                    return registration.getVehicleMake();
                }
            case 8:
                if(registration.getVehicleModel().isEmpty()){
                    return "";
                }else{
                    return registration.getVehicleModel();
                }
            case 9:
                if(registration.getVehiclePlateNumber().isEmpty()){
                    return "";
                }else{
                    return registration.getVehiclePlateNumber();
                }
            default:
                System.err.println("Logic Error");
        }

        return "";
    }
}
