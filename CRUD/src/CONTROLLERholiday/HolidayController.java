package CONTROLLERholiday;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import MODELholiday.*;
import VIEWholiday.*;

public class HolidayController {
    private UserInterface view;
    private HolidayModel model;

    public HolidayController(UserInterface view, HolidayModel model) {
        this.view = view;
        this.model = model;

        this.view.getAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idEmployee = (int) view.getIdSpinner().getValue(); 
                    String startDate = view.getStartDateField().getText().trim();
                    String endDate = view.getEndDateField().getText().trim();
                    TypeConge conge = (TypeConge) view.getCongeField().getSelectedItem();

                    if (startDate.isEmpty() || endDate.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "All fields must be filled",
                                "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (model.demandHoliday(idEmployee, startDate, endDate, conge)) {
                        JOptionPane.showMessageDialog(null, "Holiday request was added successfully",
                                "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        refreshDisplay();
                    } else {
                        JOptionPane.showMessageDialog(null, "you have exceeded the number of allowed days to take please try again with a shorter period of time",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number format",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.view.getUpdate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getTable().getSelectedRow();
                if (selectedRow > -1) {
                    int idEmployee = (int) view.getTable().getValueAt(selectedRow, 0);
                    try {
                        String startDate = view.getStartDateField().getText().trim();
                        String endDate = view.getEndDateField().getText().trim();
                        TypeConge conge = (TypeConge) view.getCongeField().getSelectedItem();

                        if (model.updateHoliday(idEmployee, startDate, endDate, conge)) {
                            JOptionPane.showMessageDialog(null, "Holiday request was updated successfully",
                                    "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                            refreshDisplay();
                        } else {
                            JOptionPane.showMessageDialog(null, "You could not update the holiday, please try again",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid number format",
                                "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.view.getDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getTable().getSelectedRow();
                if (selectedRow > -1) {
                    int idEmployee = (int) view.getTable().getValueAt(selectedRow, 0);
                    if (model.deleteHoliday(idEmployee)) {
                        JOptionPane.showMessageDialog(null, "Holiday request was deleted successfully",
                                "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        refreshDisplay();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete holiday request",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.view.getTable().getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow > -1) {
                int idEmployee = (int) view.getTable().getValueAt(selectedRow, 0);
                String startDate = (String) view.getTable().getValueAt(selectedRow, 1);
                String endDate = (String) view.getTable().getValueAt(selectedRow, 2);
                String congeString = (String) view.getTable().getValueAt(selectedRow, 3);
                TypeConge conge = TypeConge.valueOf(congeString);

                view.getIdSpinner().setValue(idEmployee); // Set value in JSpinner
                view.getStartDateField().setText(startDate);
                view.getEndDateField().setText(endDate);
                view.getCongeField().setSelectedItem(conge);
            }
        });

        refreshDisplay();
    }

    private void refreshDisplay() {
        ArrayList<Object[]> holidays = model.displayHoliday();
        view.getTableModel().setRowCount(0);
        for (Object[] holiday : holidays) {
            view.getTableModel().addRow(holiday);
        }
    }
}
