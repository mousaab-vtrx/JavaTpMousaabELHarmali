package Controller;
import Model.*;
import VIEW.EmployeesView;
import java.awt.event.*;
import java.util.List;

import javax.swing.JOptionPane;
public class EmployeeController {
	private EmployeeModel model;
	private EmployeesView view;
	public EmployeeController(EmployeeModel model, EmployeesView view) {
		this.model = model;
		this.view = view;
		this.view.getAddButton().addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				 if (view.getLastName().getText().isEmpty() || view.getFirstName().getText().isEmpty() || view.getEmail().getText().isEmpty() || view.getPhone().getText().isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "All fields must be filled out", "Input Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
				String nom = view.getLastName().getText();
				String prenom = view.getFirstName().getText();
				String email = view.getEmail().getText().toLowerCase();
				String telephone = view.getPhone().getText().trim();
				Poste poste = (Poste) view.getPostes().getSelectedItem();
				Role role = (Role) view.getRoles().getSelectedItem();
			
				try {
					double salaire = Double.parseDouble(view.getSalary().getText());
					if(model.addEmployee(nom, prenom, email, telephone, salaire, role, poste)) {
						JOptionPane.showMessageDialog(null, "Employee was added successfully","Comfirmation",JOptionPane.INFORMATION_MESSAGE);
	                    EmployeeController.this.display();				
					}
					else JOptionPane.showMessageDialog(null, "Couldn't add the employee please revise your information","Error",JOptionPane.ERROR_MESSAGE);
 
			    } catch(NumberFormatException er) {
			    	er.printStackTrace();
			    }
			
		}});
		this.view.getDeleteButton().addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				int selectedRow = view.getTable().getSelectedRow();
				if(selectedRow > - 1) {
					int id = (int) view.getTable().getValueAt(selectedRow, 0);
					if(model.deleteEmployee(id)) {
						JOptionPane.showMessageDialog(null, "Employee was deleted successfully","Comfirmation",JOptionPane.INFORMATION_MESSAGE);
						 EmployeeController.this.display();	
					}
					else JOptionPane.showMessageDialog(null, "Couldn't delete the employee please try again","Error",JOptionPane.ERROR_MESSAGE);
				}
				else JOptionPane.showMessageDialog(null, "please select an employee","Error",JOptionPane.ERROR_MESSAGE);
				
			}
		});
		this.view.getUpdateButton().addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				int selectedRow = view.getTable().getSelectedRow();
				if(selectedRow > - 1) {
					int id = (int) view.getTable().getValueAt(selectedRow, 0);
					String nom = view.getLastName().getText();
					String prenom = view.getFirstName().getText();
					String email = view.getEmail().getText().toLowerCase();
					String telephone = view.getPhone().getText().trim();
					Poste poste = (Poste) view.getPostes().getSelectedItem();
					Role role = (Role) view.getRoles().getSelectedItem();
					double salaire = 0.0;
					try {
						 salaire = Double.parseDouble(view.getSalary().getText());
	 
				    } catch(NumberFormatException er) {
				    	er.printStackTrace();
				    }
					if(model.modifyEmployee(id, nom, prenom, email, telephone, salaire, role, poste)) {
						JOptionPane.showMessageDialog(null, "Employee was updated successfully","Comfirmation",JOptionPane.INFORMATION_MESSAGE);
						 EmployeeController.this.display();	
						}
					else JOptionPane.showMessageDialog(null, "Couldn't update the employee please try again","Error",JOptionPane.ERROR_MESSAGE);
				}
				else JOptionPane.showMessageDialog(null, "please select an employee","Error",JOptionPane.ERROR_MESSAGE);
				
			}
		});
		this.view.getTable().getSelectionModel().addListSelectionListener(e -> fillOutFields());
		this.view.getDisplayButton().addActionListener(e -> display());
		
	}
	public void display() {
		List<Object[]> data  = model.getAllElements();
		view.getModel().setRowCount(0);
		for (Object[] row : data) {
			view.getModel().addRow(row);
		}
	}
	public void fillOutFields() {
		int selectedRow = view.getTable().getSelectedRow();
		if(selectedRow > - 1) {
			view.getLastName().setText(view.getTable().getValueAt(selectedRow, 1).toString());
			view.getFirstName().setText(view.getTable().getValueAt(selectedRow, 2).toString());
			view.getPhone().setText(view.getTable().getValueAt(selectedRow, 3).toString());
			view.getEmail().setText( view.getTable().getValueAt(selectedRow, 4).toString());
		    view.getSalary().setText(view.getTable().getValueAt(selectedRow, 5).toString());
		    view.getRoles().setSelectedItem((Role)view.getTable().getValueAt(selectedRow,6));
		    view.getPostes().setSelectedItem((Poste)view.getTable().getValueAt(selectedRow,7));
	}
	}
	
	
}
