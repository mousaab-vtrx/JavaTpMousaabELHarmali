package DAOholiday;

import java.sql.*;
import java.util.ArrayList;

import DAO.Connect;
import MODELholiday.Holiday;

public class HolidayDAOimplement implements HolidayDAOI {
    private String sql = null;
    static Connect c;
    public HolidayDAOimplement() {
    	c = new Connect();
    }
   @Override
	public boolean updateDemamdedHoliday(Holiday holiday) {
		sql = "Update holidays set startDate = ?::Date ,endDate  = ?::Date,holidayType = ? where idEmployee = ?";
        try (PreparedStatement stm = c.getConnect().prepareStatement(sql)) {
        	stm.setString(1,holiday.getStartDate());
        	stm.setString(2, holiday.getEndDate());
        	stm.setString(3, holiday.getConge().name());
            stm.setInt(5, holiday.getIdEmployee());
            stm.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
	}
   @Override
	public ArrayList<Object[]> displayHoliday() {
		  sql = "SELECT * FROM Holidays";
	        ArrayList<Object[]> data = new ArrayList<>();
	        try (Statement st = c.getConnect().createStatement();
	             ResultSet rs = st.executeQuery(sql)) {
	            while (rs.next()) {
	                Object[] row = new Object[6];
	                row[0] = rs.getInt("idEmployee");           
	                row[1] = rs.getString("startDate");
	                row[2] = rs.getString("endDate");  
	                row[3] = rs.getString("holidayType");          
	                data.add(row);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	 }
	@Override
	public boolean deleteHoliday(int id) {
		 sql = "DELETE FROM holidays WHERE idEmployee = ?";
	        try (PreparedStatement st = c.getConnect().prepareStatement(sql)) {
	            st.setInt(1, id);
	            st.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	       return true;
	}
	@Override
	public boolean addHoliday(Holiday holiday) {
		sql = "INSERT INTO holidays (idEmployee,startDate,endDate,holidayType) VALUES (?, ?::Date, ?::Date, ?)";
        try (PreparedStatement stm = c.getConnect().prepareStatement(sql)) {
            stm.setInt(1, holiday.getIdEmployee());
            stm.setString(2,holiday.getStartDate());
            stm.setString(3, holiday.getEndDate());
            stm.setString(4, holiday.getConge().name());
            stm.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
		return true;
	}
	
}
