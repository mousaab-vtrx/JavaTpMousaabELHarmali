package DAO;

import Model.Employees;
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOImportExportEmployeeImplement implements DataImportExportI<Employees> {
    private static final Logger LOGGER = Logger.getLogger(DAOImportExportEmployeeImplement.class.getName());
    private static final String TABLE_NAME = "employees";
    private static final String[] COLUMNS = {
        "nom", "prenom", "email", "salaire", "role", "poste", "telephone"
    };

    @Override
    public int importData(String fileName) throws IOException {
        int count = 0;
        String query = String.format(
            "INSERT INTO %s (%s) VALUES (?, ?, ?, ?::numeric, ?, ?, ?)",
            TABLE_NAME,
            String.join(", ", COLUMNS)
        );

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             PreparedStatement stmt = Connect.getConnection().prepareStatement(query)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == COLUMNS.length) {
                    setPreparedStatement(stmt, data);
                    count += stmt.executeUpdate();
                } else {
                    LOGGER.warning("Skipping invalid data row: " + line);
                }
            }
        } catch (IOException | SQLException e) {
            LOGGER.log(Level.SEVERE, "Error importing data from file: " + fileName, e);
            throw new IOException("Error importing data", e);
        }
        return count;
    }

    @Override
    public void exportData(String fileName) throws IOException {
        String query = "SELECT * FROM " + TABLE_NAME;

        try (PreparedStatement stmt = Connect.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery();
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            while (rs.next()) {
                writer.write(formatEmployeeData(rs));
                writer.newLine();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error during export", e);
            throw new IOException("Database error during export", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "File I/O error during export to: " + fileName, e);
            throw new IOException("File I/O error during export", e);
        }
    }

    private void setPreparedStatement(PreparedStatement stmt, String[] data) throws SQLException {
        for (int i = 0; i < COLUMNS.length; i++) {
            stmt.setString(i + 1, data[i].trim());
        }
    }

    private String formatEmployeeData(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();
        for (String column : COLUMNS) {
            sb.append(rs.getString(column)).append(",");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
