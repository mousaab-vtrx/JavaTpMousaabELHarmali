package VIEWholiday;

import VIEW.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import MODELholiday.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UserInterface extends JFrame {
    private JLabel label1, label2, label3, label4;
    private JTextField startDateField, endDateField;
    private JSpinner idSpinner;
    private JComboBox<TypeConge> congeField;
    private JButton add, delete, update;
    private DefaultTableModel tableModel;
    private JPanel mainPanel, subPanelButtons, subPanelInput, employeePanel;
    private JTable table;
    private JScrollPane tableContainer;
    private JTabbedPane switchPanels;

    private static final Color BUTTON_COLOR = new Color(0, 123, 255);
    private static final Color BUTTON_HOVER_COLOR = new Color(0, 102, 204);
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 14);
    private static final Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 14);

    private final String[] columns = {"ID Employee", "Start Date", "End Date", "Type of Holiday"};

    public UserInterface() {
        setSize(700, 600);
        setTitle("Holiday Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout(5, 5));
        subPanelButtons = createPanel(new FlowLayout(), 0);
        subPanelInput = createPanel(new GridLayout(4, 2, 10, 10), 10);

        label1 = createLabel("ID Employee:");
        label2 = createLabel("Start Date:");
        label3 = createLabel("End Date:");
        label4 = createLabel("Type of Holiday:");

        startDateField = customTextField();
        endDateField = customTextField();
        idSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
        congeField = new JComboBox<>(TypeConge.values());

        add = createButton("ADD");
        delete = createButton("DELETE");
        update = createButton("UPDATE");

        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        tableContainer = new JScrollPane(table);

        subPanelInput.add(label1);
        subPanelInput.add(idSpinner);
        subPanelInput.add(label2);
        subPanelInput.add(startDateField);
        subPanelInput.add(label3);
        subPanelInput.add(endDateField);
        subPanelInput.add(label4);
        subPanelInput.add(congeField);

        subPanelButtons.add(add);
        subPanelButtons.add(delete);
        subPanelButtons.add(update);

        mainPanel.add(subPanelInput, BorderLayout.NORTH);
        mainPanel.add(tableContainer, BorderLayout.CENTER);
        mainPanel.add(subPanelButtons, BorderLayout.SOUTH);

        employeePanel = new EmployeesView();
        switchPanels = new JTabbedPane();
        switchPanels.add("Employees", employeePanel);
        switchPanels.add("Holidays", mainPanel);
        
        this.add(switchPanels);
        setVisible(true);
    }

    private JPanel createPanel(LayoutManager layout, int padding) {
        JPanel panel = new JPanel(layout);
        if (padding > 0) {
            panel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        }
        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(LABEL_FONT);
        label.setForeground(new Color(50, 50, 150));
        return label;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(BUTTON_COLOR));
        button.setPreferredSize(new Dimension(120, 35));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER_COLOR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_COLOR);
            }
        });
        return button;
    }

    public void setIdSpinner(JSpinner idSpinner) {
		this.idSpinner = idSpinner;
	}

	private JTextField customTextField() {
        JTextField textfield = new JTextField(8);
        textfield.setBorder(new EmptyBorder(10, 3, 5, 10));
        return textfield;
    }

    public JLabel getLabel1() {
        return label1;
    }

    public JLabel getLabel2() {
        return label2;
    }

    public JLabel getLabel3() {
        return label3;
    }

    public JLabel getLabel4() {
        return label4;
    }

    public JTextField getStartDateField() {
        return startDateField;
    }

    public JTextField getEndDateField() {
        return endDateField;
    }

    public JSpinner getIdSpinner() {
        return idSpinner;
    }

    public JComboBox<TypeConge> getCongeField() {
        return congeField;
    }

    public JButton getAdd() {
        return add;
    }

    public JButton getDelete() {
        return delete;
    }

    public JButton getUpdate() {
        return update;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getSubPanelButtons() {
        return subPanelButtons;
    }

    public JPanel getSubPanelInput() {
        return subPanelInput;
    }

    public JPanel getEmployeePanel() {
        return employeePanel;
    }

    public JTable getTable() {
        return table;
    }

    public JScrollPane getTableContainer() {
        return tableContainer;
    }

    public JTabbedPane getSwitchPanels() {
        return switchPanels;
    }
}
