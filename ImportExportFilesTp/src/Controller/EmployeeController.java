package Controller;

import Model.*;
import VIEW.EmployeesView;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EmployeeController {
    private final EmployeeModel model;
    private final EmployeesView view;
    private final DataImportExportEmployeeModel dataModel;

    public EmployeeController(EmployeeModel model, EmployeesView view, DataImportExportEmployeeModel dataModel) {
        this.model = model;
        this.view = view;
        this.dataModel = dataModel;
        initListeners();
    }

    private void initListeners() {
        view.getAddButton().addActionListener(e -> addEmployee());
        view.getDeleteButton().addActionListener(e -> deleteEmployee());
        view.getUpdateButton().addActionListener(e -> updateEmployee());
        view.getDisplayButton().addActionListener(e -> display());
        view.getImportButton().addActionListener(e -> importData());
        view.getExportButton().addActionListener(e -> exportData());
        view.getTable().getSelectionModel().addListSelectionListener(e -> fillOutFields());
    }

    private void addEmployee() {
        if (view.getLastName().getText().isEmpty() || view.getFirstName().getText().isEmpty()
                || view.getEmail().getText().isEmpty() || view.getPhone().getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "All fields must be filled out", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String nom = view.getLastName().getText();
            String prenom = view.getFirstName().getText();
            String email = view.getEmail().getText().toLowerCase();
            String telephone = view.getPhone().getText().trim();
            Poste poste = (Poste) view.getPostes().getSelectedItem();
            Role role = (Role) view.getRoles().getSelectedItem();
            double salaire = Double.parseDouble(view.getSalary().getText());

            String result = model.addEmployee(nom, prenom, email, telephone, salaire, role, poste);
            if (result.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Employee added successfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                display();
            } else {
                JOptionPane.showMessageDialog(view, result, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Invalid salary format", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteEmployee() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow > -1) {
            int id = (int) view.getTable().getValueAt(selectedRow, 0);
            if (model.deleteEmployee(id)) {
                JOptionPane.showMessageDialog(view, "Employee deleted successfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                display();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to delete the employee", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select an employee", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateEmployee() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow > -1) {
            try {
                int id = (int) view.getTable().getValueAt(selectedRow, 0);
                String nom = view.getLastName().getText();
                String prenom = view.getFirstName().getText();
                String email = view.getEmail().getText().toLowerCase();
                String telephone = view.getPhone().getText().trim();
                Poste poste = (Poste) view.getPostes().getSelectedItem();
                Role role = (Role) view.getRoles().getSelectedItem();
                double salaire = Double.parseDouble(view.getSalary().getText());

                String result = model.modifyEmployee(id, nom, prenom, email, telephone, salaire, role, poste);
                if (result.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Employee updated successfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    display();
                } else {
                    JOptionPane.showMessageDialog(view, result, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid salary format", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select an employee", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void display() {
        List<Object[]> data = model.getAllElements();
        view.getModel().setRowCount(0);
        for (Object[] row : data) {
            view.getModel().addRow(row);
        }
    }

    private void importData() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a file to import");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        if (fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                int count = dataModel.importData(file);
                JOptionPane.showMessageDialog(view, count + " records imported successfully.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                display();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(view, "Failed to import data", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void exportData() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a file to export");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        if (fileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                dataModel.exportData(file);
                JOptionPane.showMessageDialog(view, "Data exported successfully.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(view, "Failed to export data", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void fillOutFields() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow > -1) {
            view.getLastName().setText(view.getTable().getValueAt(selectedRow, 1).toString());
            view.getFirstName().setText(view.getTable().getValueAt(selectedRow, 2).toString());
            view.getPhone().setText(view.getTable().getValueAt(selectedRow, 3).toString());
            view.getEmail().setText(view.getTable().getValueAt(selectedRow, 4).toString());
            view.getSalary().setText(view.getTable().getValueAt(selectedRow, 5).toString());
            view.getRoles().setSelectedItem(view.getTable().getValueAt(selectedRow, 6));
            view.getPostes().setSelectedItem(view.getTable().getValueAt(selectedRow, 7));
        }
    }
}
