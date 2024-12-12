package Model;

import DAO.EmployeeDAOimplement;

import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    private EmployeeDAOimplement dao;

    public EmployeeModel(EmployeeDAOimplement dao) {
        this.dao = dao;
    }

    public boolean addEmployee(String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste) {
        if (salaire < 4000) {
            System.err.println("Salary must be greater than the minimum wage.");
            return false;
        }
        if (telephone.length() != 10) {
            System.err.println("The phone number must contain exactly 10 digits.");
            return false;
        }
        Employees emp = new Employees(0,nom, prenom, email, telephone, salaire, role, poste);
        dao.addEmployee(emp);
        return true;
    }

    public boolean modifyEmployee(int id,String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste) {
        if (salaire < 4000) {
            System.err.println("Salary must be greater than the minimum wage.");
            return false;
        }
        if (telephone.length() != 10) {
            System.err.println("The phone number must contain exactly 10 digits.");
            return false;
        }
        Employees emp = new Employees(id,nom, prenom, email, telephone, salaire, role, poste);
        dao.modifyEmployee(emp);
        return true;
    }

    public boolean deleteEmployee(int id){
    	dao.deleteEmployee(id);
    	return true;
    }
    public ArrayList<Object[]> getAllEmployees() {
        ArrayList<Employees> employees = dao.getAllEmployees();
        ArrayList<Object[]> employeeData = new ArrayList<>();

        for (Employees emp : employees) {
            Object[] empData = new Object[8];
            empData[0] = emp.getId();
            empData[1] = emp.getNom();
            empData[2] = emp.getPrenom();
            empData[3] = emp.getEmail();
            empData[4] = emp.getTelephone();
            empData[5] = emp.getSalaire();
            empData[6] = emp.getRole();
            empData[7] = emp.getPoste();
            employeeData.add(empData);
        }
        return employeeData;
    }

    
}
