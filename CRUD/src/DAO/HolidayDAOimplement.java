package DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Model.Holiday;
import Model.TypeConge;

public class HolidayDAOimplement implements GenericDAOI<Holiday> {
    private String sql = null;

   @Override
	public void updateElement(Holiday holiday) {
		sql = "Update holidays set startDate = ?::Date ,endDate  = ?::Date,holidayType = ?,idEmployee = ? where id = ?";
        try (PreparedStatement stm = Connect.getConnection().prepareStatement(sql)) {
        	stm.setString(1,holiday.getStartDate().toString());
        	stm.setString(2, holiday.getEndDate().toString());
        	stm.setString(3, holiday.getConge().name());
            stm.setInt(4, holiday.getIdEmployee());
            stm.setInt(5, holiday.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
   @Override
	public List<Holiday> getAllElements() {
		  sql = "SELECT * FROM Holidays";
	      List<Holiday> holidays = null;
	      try {
	    	  Statement st = Connect.getConnection().createStatement();
	    	  ResultSet rs = st.executeQuery(sql);
	    	  holidays = new ArrayList<>();
	    	  while(rs.next()) {
	    		  holidays.add(new Holiday(
	    				  rs.getInt("id"),
	    				  rs.getInt("IdEmployee"),
	    				  LocalDate.parse(rs.getString("startDate")),
	    				  LocalDate.parse(rs.getString("endDate")),
	    				  TypeConge.valueOf(rs.getString("holidayType"))
	    				 ));
	    	  }
	      }
	      catch(SQLException e) {
	    	  e.printStackTrace();
	      }
	      return holidays;
	 }
	@Override
	public void deleteElement(int id) {
		 sql = "DELETE FROM holidays WHERE id = ?";
	        try (PreparedStatement st = Connect.getConnection().prepareStatement(sql)) {
	            st.setInt(1, id);
	            st.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	@Override
	public void addElement(Holiday holiday) {
		sql = "INSERT INTO holidays (idEmployee,startDate,endDate,holidayType) VALUES (?, ?::Date, ?::Date, ?)";
        try (PreparedStatement stm = Connect.getConnection().prepareStatement(sql)) {
            stm.setInt(1, holiday.getIdEmployee());
            stm.setString(2,holiday.getStartDate().toString());
            stm.setString(3, holiday.getEndDate().toString());
            stm.setString(4, holiday.getConge().name());
            stm.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	@Override
	public Holiday findElement(int id) {
		sql = "SELECT * FROM Holidays where id = "+id;
	     Holiday holiday = null;
	      try {
	    	  Statement st = Connect.getConnection().createStatement();
	    	  ResultSet rs = st.executeQuery(sql);
	    	  if(rs.next()) {
	    		  holiday = new Holiday(rs.getInt("id"),
	    				  rs.getInt("IdEmployee"),
	    				  LocalDate.parse(rs.getString("startDate")),
	    				  LocalDate.parse(rs.getString("endDate")),
	    				  TypeConge.valueOf(rs.getString("holidayType")));
	    	  }
	      }
	      catch(SQLException e) {
	    	  e.printStackTrace();
	      }
	      return holiday;
	}
	
}
