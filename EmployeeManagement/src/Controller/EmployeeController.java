package Controller;

import Model.*;
import VIEW.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EmployeeController {
    private EmployeesView view;
    private EmployeeModel model;

    public EmployeeController(EmployeesView view, EmployeeModel model) {
        this.view = view;
        this.model = model;

        this.view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        this.view.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployee();
            }
        });

        this.view.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });

        this.view.getDisplayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayEmployees();
            }
        });
    }

    private void addEmployee() {
        try {
            String firstName = view.getFirstName().getText();
            String lastName = view.getLastName().getText();
            String phone = view.getPhone().getText().trim();
            String email = view.getEmail().getText().trim();
            double salary = Double.parseDouble(view.getSalary().getText().trim());
            Role role = (Role) view.getRoles().getSelectedItem();
            Poste poste = (Poste) view.getPostes().getSelectedItem();

            if (model.addEmployee(firstName, lastName, email, phone, salary, role, poste)) {
                JOptionPane.showMessageDialog(view, "Employee added successfully.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                refreshDisplay();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to add employee.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Invalid input format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateEmployee() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow > -1) {
            try {
                int id = (int) view.getTable().getValueAt(selectedRow, 0);
                String firstName = view.getFirstName().getText();
                String lastName = view.getLastName().getText();
                String phone = view.getPhone().getText().trim();
                String email = view.getEmail().getText().trim();
                double salary = Double.parseDouble(view.getSalary().getText().trim());
                Role role = (Role) view.getRoles().getSelectedItem();
                Poste poste = (Poste) view.getPostes().getSelectedItem();

                if (model.modifyEmployee(id, firstName, lastName, email, phone, salary, role, poste)) {
                    JOptionPane.showMessageDialog(view, "Employee updated successfully.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    refreshDisplay();
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to update employee.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid input format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteEmployee() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow > -1) {
            int id = (int) view.getTable().getValueAt(selectedRow, 0);
            if (model.deleteEmployee(id)) {
                JOptionPane.showMessageDialog(view, "Employee deleted successfully.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                refreshDisplay();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to delete employee.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayEmployees() {
        ArrayList<Object[]> employees = model.getAllEmployees();
        view.getModel().setRowCount(0);
        for (Object[] employee : employees) {
            view.getModel().addRow(employee);
        }
    }

    private void refreshDisplay() {
        displayEmployees();
    }
}
