package DAO;
import Model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserDAOimplement implements UserDAOI {
    @Override
    public void addUser(User Auser) {
        String sql = "INSERT INTO users(username,password) VALUES (?,?)";
        try (PreparedStatement st = Connect.getConnection().prepareStatement(sql)) {
            st.setString(1, Auser.getUsername());
            st.setString(2, Auser.getPassword());
            st.executeUpdate();
        } catch (SQLException er) {
        	er.printStackTrace();

        }
    }

    @Override
    public boolean checkUser(User Auser) {
        String sql = "SELECT username, password FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement st = Connect.getConnection().prepareStatement(sql)) {
            st.setString(1, Auser.getUsername());
            st.setString(2, Auser.getPassword());
            ResultSet rs = st.executeQuery();
            if (rs.next()) 
            	return true;
        } catch (SQLException er) {
        	er.printStackTrace();
        }
        return false;
    }
}
