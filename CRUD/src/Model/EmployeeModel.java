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
            System.err.println("The phone number must contain exactly 10 digits("+telephone.length()+")");
            return false;
        }
        Employees emp = new Employees(0,nom, prenom, email, telephone, salaire, role, poste);
        dao.addElement(emp);
        return true;
    }

    public boolean modifyEmployee(int id,String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste) {
        if (salaire < 4000) {
            System.err.println("Salary must be greater than the minimum wage.");
            return false;
        }
        if (telephone.length() != 10) {
            System.err.println("The phone number must contain exactly 10 digits("+telephone.length()+")");
            return false;
        }
        Employees emp = new Employees(id,nom, prenom, email, telephone, salaire, role, poste);
        dao.updateElement(emp);
        return true;
    }

    public boolean deleteEmployee(int id){
    	dao.deleteElement(id);
    	return true;
    }
    public List<Object[]> getAllElements() {
        List<Employees> employees = dao.getAllElements();
        List<Object[]>  data = new ArrayList<Object[]>();
        for (Employees element : employees) {
        	Object[] row = {
        		element.getId(),
        		element.getNom(),
        		element.getPrenom(),
        		element.getTelephone(),
        		element.getEmail(),
        		element.getSalaire(),
        		element.getRole(),
        		element.getPoste()
        	};
        	data.add(row);
        }
        return data;
     }

    
}
