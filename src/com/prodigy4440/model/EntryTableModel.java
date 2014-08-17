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
public class EntryTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -8141742212545373856L;
    private final List<Entry> entryList = new LinkedList<>();
    private final String[] columns = {"S/N", "Vehicle Tag", "Plate Number", "Entry Type", "Registration No", "Time In", "Time Out"};

    public EntryTableModel() {
    }

    public EntryTableModel(LinkedList<Entry> list) {
        list.stream().forEach((entry) -> {
            this.entryList.add(entry);
        });
        fireTableDataChanged();
    }

    public Entry addEntry(Entry entry) {
        this.entryList.add(entry);
        fireTableDataChanged();
        return entry;
    }

    public Entry getEntry(int row) {
        return this.entryList.get(row);
    }

    public Entry removeEntry(int row) {
        Entry remove = this.entryList.remove(row);
        fireTableDataChanged();
        return remove;
    }

    public void clearEntry() {
        this.entryList.clear();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.entryList.size();
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
        }
        return String.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Entry entry = this.entryList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return (rowIndex+1);
            case 1:
                if (!entry.getTag().isEmpty()) {
                    return entry.getTag();
                } else {
                    return "";
                }
            case 2:
                if (entry.getPlateNumber().isEmpty()) {
                    return entry.getPlateNumber();
                } else {
                    return "";
                }
            case 3:
                if (entry.getEntryType() == 'V') {
                    return "Visitor";
                } else if (entry.getEntryType() == 'R') {
                    return "Registered";
                } else {
                    return "";
                }
            case 4:
                if (!entry.getRegistrationNo().isEmpty()) {
                    return entry.getRegistrationNo();
                } else {
                    return "";
                }
            case 5:
                if (entry.getTimeIn() != null) {
                    return entry.getTimeIn();
                } else {
                    return "";
                }
            case 6:
                if (entry.getTimeOut() != null) {
                    return entry.getTimeOut();
                } else {
                    return "";
                }
            default:
                System.err.println("Logic Error");
        }

        return "";
    }

}
