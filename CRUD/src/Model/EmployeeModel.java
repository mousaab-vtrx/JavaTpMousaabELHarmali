package Model;

import DAO.EmployeeDAOimplement;

import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    private EmployeeDAOimplement dao;

    public EmployeeModel(EmployeeDAOimplement dao) {
        this.dao = dao;
    }

    public String addEmployee(String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste) {
        StringBuilder errors = new StringBuilder();

        if (salaire < 4000) {
            errors.append("Salary must be greater than the minimum wage.\n");
        }
        if (telephone.length() != 10) {
            errors.append("The phone number must contain exactly 10 digits.\n");
        }
        if (errors.length() > 0) {
            return errors.toString();
        }

        Employees emp = new Employees(0, nom, prenom, email, telephone, salaire, role, poste);
        dao.addElement(emp);
        return "";
    }

    public String modifyEmployee(int id, String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste) {
        StringBuilder errors = new StringBuilder();

        if (salaire < 4000) {
            errors.append("Salary must be greater than the minimum wage.\n");
        }
        if (telephone.length() != 10) {
            errors.append("The phone number must contain exactly 10 digits.\n");
        }
        if (errors.length() > 0) {
            return errors.toString();
        }

        Employees emp = new Employees(id, nom, prenom, email, telephone, salaire, role, poste);
        dao.updateElement(emp);
        return "";
    }

    public boolean deleteEmployee(int id) {
        dao.deleteElement(id);
        return true;
    }

    public List<Object[]> getAllElements() {
        List<Employees> employees = dao.getAllElements();
        List<Object[]> data = new ArrayList<>();
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
