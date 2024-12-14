package DAOholiday;
import java.sql.SQLException;
import java.util.ArrayList;

import MODELholiday.*;
public interface HolidayDAOI {
	 public boolean updateDemamdedHoliday(Holiday holiday);
	 public ArrayList<Object[]> displayHoliday();
	 public boolean deleteHoliday(int id);
	public boolean addHoliday(Holiday holiday) ;
}
