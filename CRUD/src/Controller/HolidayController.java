package Controller;

import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import Model.HolidayModel;
import Model.TypeConge;
import VIEW.UserInterface;

public class HolidayController {
    private UserInterface view;
    private HolidayModel model;

    public HolidayController(UserInterface view, HolidayModel model) {
        this.view = view;
        this.model = model;

        // Add ActionListener for the "Add" button
        this.view.getAdd().addActionListener(e -> {
            try {
                if (view.getStartDateField().getText().isEmpty() || view.getEndDateField().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String selectedEmployee = (String) view.getIdComboBox().getSelectedItem();
                int idEmployee = Integer.parseInt(selectedEmployee.replace("Employee ", "").trim());
                LocalDate startDate = LocalDate.parse(view.getStartDateField().getText().trim());
                LocalDate endDate = LocalDate.parse(view.getEndDateField().getText().trim());
                TypeConge conge = (TypeConge) view.getCongeField().getSelectedItem();

                if (model.demandHoliday(idEmployee, startDate, endDate, conge)) {
                    JOptionPane.showMessageDialog(null, "Holiday request was added successfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    refreshDisplay();
                } else {
                    JOptionPane.showMessageDialog(null, "You have exceeded the number of allowed days. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add ActionListener for the "Update" button
        this.view.getUpdate().addActionListener(e -> {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow > -1) {
                try {
                    int id = (int) view.getTable().getValueAt(selectedRow, 0);
                    String selectedEmployee = (String) view.getIdComboBox().getSelectedItem();
                    int idEmployee = Integer.parseInt(selectedEmployee.replace("Employee ", "").trim());
                    LocalDate startDate = LocalDate.parse(view.getStartDateField().getText().trim());
                    LocalDate endDate = LocalDate.parse(view.getEndDateField().getText().trim());
                    TypeConge conge = (TypeConge) view.getCongeField().getSelectedItem();

                    if (model.updateHoliday(id, idEmployee, startDate, endDate, conge)) {
                        JOptionPane.showMessageDialog(null, "Holiday request was updated successfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        refreshDisplay();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update the holiday request", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add ActionListener for the "Delete" button
        this.view.getDelete().addActionListener(e -> {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow > -1) {
                try {
                    int id = (int) view.getTable().getValueAt(selectedRow, 0);
                    if (model.deleteHoliday(id)) {
                        JOptionPane.showMessageDialog(null, "Holiday request was deleted successfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        refreshDisplay();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete holiday request", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


            this.view.getTable().getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow > -1) {
                try {
                    int idEmployee = (int) view.getTable().getValueAt(selectedRow, 1);
                    String startDate = (String) view.getTable().getValueAt(selectedRow, 2);
                    String endDate = (String) view.getTable().getValueAt(selectedRow, 3);
                    String congeString = (String) view.getTable().getValueAt(selectedRow, 4);
                    TypeConge conge = TypeConge.valueOf(congeString);

                    view.getIdComboBox().setSelectedItem("Employee " + idEmployee);
                    view.getStartDateField().setText(startDate);
                    view.getEndDateField().setText(endDate);
                    view.getCongeField().setSelectedItem(conge);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        refreshDisplay();
        this.view.getRefresh().addActionListener(e ->  updateIdComboBox());
       
    }

    private void refreshDisplay() {
        List<Object[]> holidays = model.displayHoliday();
        view.getTableModel().setRowCount(0);
        for (Object[] holiday : holidays) {
            view.getTableModel().addRow(holiday);
        }
    }

    private void updateIdComboBox() {
        List<String> employeeNames = model.getAllIds();
        view.getIdComboBox().removeAllItems();
        for (String element : employeeNames) {
            view.getIdComboBox().addItem("Employee " +element);
        }
    }
}
