package VIEW;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Model.Poste;
import Model.Role;
import java.awt.*;

public class EmployeesView extends JPanel{
    private JPanel formPanel, buttonPanel;
    private JLabel labelFirstName, labelLastName, labelPhone, labelEmail, labelSalary, labelRole, labelPoste;
    private JTextField firstName, lastName, phone, email, salary;
    private JComboBox<Role> roles;
    private JComboBox<Poste> postes;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JButton addButton, updateButton, deleteButton, displayButton;

    private static final Color BUTTON_COLOR = new Color(0, 123, 255);
    private static final Color BUTTON_HOVER_COLOR = new Color(0, 102, 204);
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 14);
    private static final Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 14);

    public EmployeesView() {
        initializeComponents();
        assembleComponents();
        setVisible(false);
    }

  

    private void initializeComponents() {
        // Panels
       this.setLayout(new BorderLayout());
        formPanel = createPanel(new GridLayout(7, 2, 10, 10), 10);
        buttonPanel = createPanel(new FlowLayout(FlowLayout.CENTER, 10, 10), 0);

        labelFirstName = createLabel("Nom:");
        labelLastName = createLabel("Prenom:");
        labelPhone = createLabel("Telephone:");
        labelEmail = createLabel("Email:");
        labelSalary = createLabel("Salaire:");
        labelRole = createLabel("Roles:");
        labelPoste = createLabel("Postes:");

        // Text fields
        firstName = new JTextField(10);
        lastName = new JTextField(10);
        phone = new JTextField(10);
        email = new JTextField(10);
        salary = new JTextField(10);

        // Combo boxes
        roles = new JComboBox<>(Role.values());
        postes = new JComboBox<>(Poste.values());

        // Table
        String[] columnNames = {"ID", "Nom", "Prenom", "Telephone", "Email", "Salaire", "Role", "Poste"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null); // Disable editing
        scrollPane = new JScrollPane(table);

        // create Buttons
        addButton = createButton("ADD");
        updateButton = createButton("UPDATE");
        deleteButton = createButton("REMOVE");
        displayButton = createButton("DISPLAY");
    }

    private void assembleComponents() {
        // Add components to formPanel
        formPanel.add(labelFirstName);
        formPanel.add(firstName);
        formPanel.add(labelLastName);
        formPanel.add(lastName);
        formPanel.add(labelPhone);
        formPanel.add(phone);
        formPanel.add(labelEmail);
        formPanel.add(email);
        formPanel.add(labelSalary);
        formPanel.add(salary);
        formPanel.add(labelRole);
        formPanel.add(roles);
        formPanel.add(labelPoste);
        formPanel.add(postes);

        // add buttons to buttonPanel
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(displayButton);
        //add subPanels to mainPanel
        this.add(formPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

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

    // Getters 
    public JTextField getFirstName() { return firstName; }
    public JTextField getLastName() { return lastName; }
    public JTextField getPhone() { return phone; }
    public JTextField getEmail() { return email; }
    public JTextField getSalary() { return salary; }
    public JTable getTable(){return table;}
	public JComboBox<Role> getRoles() { return roles; }
    public JComboBox<Poste> getPostes() { return postes; }
    public DefaultTableModel getModel() { return model; }
    public JButton getAddButton() { return addButton; }
    public JButton getUpdateButton() { return updateButton; }
    public JButton getDeleteButton() { return deleteButton; }
    public JButton getDisplayButton() { return displayButton; }
}
