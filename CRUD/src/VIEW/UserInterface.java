package VIEW;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import Model.*;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UserInterface extends JFrame {
    private JTextField startDateField, endDateField;
    private JComboBox<String> idComboBox;
    private JComboBox<TypeConge> congeField;
    private JButton add, delete, update,refresh;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTabbedPane switchPanels;
    private JPanel employeePanel;
    public UserInterface() {
        setSize(700, 600);
        setTitle("Holiday Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = UIUtils.createPanel(new BorderLayout(5, 5), 0);
        JPanel subPanelButtons = UIUtils.createPanel(new FlowLayout(), 0);
        JPanel subPanelInput = UIUtils.createPanel(new GridLayout(4, 2, 10, 10), 10);

        subPanelInput.add(UIUtils.createLabel("ID Employee:"));
        idComboBox = new JComboBox<>();
        subPanelInput.add(idComboBox);
        subPanelInput.add(UIUtils.createLabel("Start Date:"));
        startDateField = UIUtils.createTextField(8);
        subPanelInput.add(startDateField);
        subPanelInput.add(UIUtils.createLabel("End Date:"));
        endDateField = UIUtils.createTextField(8);
        subPanelInput.add(endDateField);
        subPanelInput.add(UIUtils.createLabel("Type of Holiday:"));
        congeField = new JComboBox<>(TypeConge.values());
        subPanelInput.add(congeField);

        add = UIUtils.createButton("ADD");
        delete = UIUtils.createButton("DELETE");
        update = UIUtils.createButton("UPDATE");
        refresh = UIUtils.createButton("REFRESH");
        subPanelButtons.add(add);
        subPanelButtons.add(delete);
        subPanelButtons.add(update);
        subPanelButtons.add(refresh);

        String[] columns = {"Id", "Employee", "Start Date", "End Date", "Type of Holiday"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        mainPanel.add(subPanelInput, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.add(subPanelButtons, BorderLayout.SOUTH);
        employeePanel = new EmployeesView();
        switchPanels = new JTabbedPane();
        switchPanels.add("Employees", employeePanel);
        switchPanels.add("Holidays", mainPanel);

        this.add(switchPanels);
 }


    public JButton getRefresh() {
		return refresh;
	}



	public EmployeesView getEmployeePanel() {
    	return (EmployeesView) employeePanel;
    }
    public JTextField getStartDateField() {
        return startDateField;
    }

    public JTextField getEndDateField() {
        return endDateField;
    }
    public JComboBox<String> getIdComboBox() {
        return idComboBox;
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
    public JTable getTable() {
        return table;
    }

  
    public JTabbedPane getSwitchPanels() {
        return switchPanels;
    }
 
    
}
