package MODELholiday;
import java.util.ArrayList;
import DAOholiday.*;
import DAO.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class HolidayModel {
    private HolidayDAOimplement daoMain;
    private Holiday holiday;
    private EmployeeDAOimplement daoSub;
    public HolidayModel(HolidayDAOimplement daoMain,EmployeeDAOimplement daoSub) {
        this.daoMain = daoMain;
        this.daoSub = daoSub;
        this.holiday = null;
        
    }

    public boolean demandHoliday(int idEmployee,String startDate, String endDate, TypeConge conge) {
    	int dayDiff = dateDiff(startDate,endDate);
    	if(daoSub.checKEmployeeAllowedHoliday(idEmployee,dayDiff)) {
        holiday = new Holiday(idEmployee,startDate,endDate,conge);
        daoSub.updateSolde(idEmployee, dayDiff);
    	}
        if (holiday != null) {
            return daoMain.addHoliday(holiday);
        }
        return false;
    }

    public boolean updateHoliday(int idEmployee,String startDate, String endDate, TypeConge conge) {
        holiday = new Holiday(idEmployee,startDate,endDate,conge);
        if (holiday != null) {
            return daoMain.updateDemamdedHoliday(holiday);
        }
        return false;
    }

    public boolean deleteHoliday(int id) {
       return daoMain.deleteHoliday(id);
    }

    public ArrayList<Object[]> displayHoliday() {
        return daoMain.displayHoliday();
    }
    public int dateDiff(String startDate, String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            
            Date date1 = sdf.parse(startDate);
            Date date2 = sdf.parse(endDate);
            
            long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
            
            return (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
   
}
