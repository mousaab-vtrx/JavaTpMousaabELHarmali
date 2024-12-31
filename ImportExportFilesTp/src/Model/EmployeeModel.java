package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import DAO.*;
public class EmployeeModel {
    private EmployeeDAOimplement dao;
    public EmployeeModel(EmployeeDAOimplement dao) {
        this.dao = dao;
    }

    private String validateEmployeeData(String email, String telephone, double salaire) {
        StringBuilder errors = new StringBuilder();

        if (!isValidEmail(email)) {
            errors.append("Invalid email format.\n");
        }
        if (telephone.length() != 10 || !telephone.matches("\\d+")) {
            errors.append("The phone number must contain exactly 10 digits and be numeric.\n");
        }
        if (salaire < 4000) {
            errors.append("Salary must be greater than the minimum wage.\n");
        }
        return errors.toString();
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }

    public String addEmployee(String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste) {
        String validationErrors = validateEmployeeData(email, telephone, salaire);
        if (!validationErrors.isEmpty()) {
            return validationErrors;
        }

        Employees emp = new Employees(0, nom, prenom, email, telephone, salaire, role, poste);
        dao.addElement(emp);
        return "";
    }

    public String modifyEmployee(int id, String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste) {
        String validationErrors = validateEmployeeData(email, telephone, salaire);
        if (!validationErrors.isEmpty()) {
            return validationErrors;
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
