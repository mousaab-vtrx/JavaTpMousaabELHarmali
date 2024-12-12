package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Model.Employees;
import Model.Poste;
import Model.Role;

public class EmployeeDAOimplement implements EmployeeDAOI {
    private static Connect c;

    public EmployeeDAOimplement() {
        c = new Connect();
    }

    @Override
    public void addEmployee(Employees emp) {
        String sql = "INSERT INTO Employees (nom, prenom, email, salaire, role, poste, telephone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = c.getConnect().prepareStatement(sql)) {
            st.setString(1, emp.getNom());
            st.setString(2, emp.getPrenom());
            st.setString(3, emp.getEmail());
            st.setDouble(4, emp.getSalaire());
            st.setString(5, emp.getRole());
            st.setString(6, emp.getPoste());
            st.setString(7, emp.getTelephone());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Employee added successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error adding employee: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void modifyEmployee(Employees emp) {
        String sql = "UPDATE Employees SET nom = ? , prenom = ? , email = ?, salaire = ?, role = ?, poste = ?, telephone = ? WHERE id = ?";
        try (PreparedStatement st = c.getConnect().prepareStatement(sql)) {
            st.setString(1, emp.getNom());
            st.setString(2, emp.getPrenom());
            st.setString(3, emp.getEmail());
            st.setDouble(4, emp.getSalaire());
            st.setString(5, emp.getRole());
            st.setString(6, emp.getPoste());
            st.setString(7, emp.getTelephone());
            st.setInt(8, emp.getId());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
               System.out.println("Employee updated successfully!");
               } else {
                System.err.println("No rows were affected.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error updating employee: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM Employees WHERE id = ?";
        try (PreparedStatement st = c.getConnect().prepareStatement(sql)) {
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee deleted successfully!");
                } else {
                    System.err.println("No rows were affected.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error deleting employee: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public ArrayList<Employees> getAllEmployees() {
        ArrayList<Employees> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employees";
        try (PreparedStatement st = c.getConnect().prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                employees.add(new Employees(
                		rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getDouble("salaire"),
                        Role.valueOf(rs.getString("role")),
                        Poste.valueOf(rs.getString("poste"))
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error retrieving employees: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return employees;
    }
}
