package MAINHoliday;

import CONTROLLERholiday.*;
import Controller.EmployeeController;
import DAO.EmployeeDAOimplement;
import DAOholiday.*;
import MODELholiday.*;
import Model.EmployeeModel;
import VIEWholiday.*;
import Controller.*;
import VIEW.*;
public class Main {

	public static void main(String[] args) {
		HolidayDAOimplement daoHoliday = new HolidayDAOimplement();
		EmployeeDAOimplement daoEmployee = new EmployeeDAOimplement();
		HolidayModel modelHoliday = new HolidayModel(daoHoliday,daoEmployee);
		EmployeeModel modelEmployee = new EmployeeModel(daoEmployee);
		UserInterface view = new UserInterface();
		HolidayController controllerHoliday = new HolidayController(view,modelHoliday);
		EmployeesView viewEmp = new EmployeesView();
		viewEmp = (EmployeesView)view.getEmployeePanel(); 
		EmployeeController controllerEmployee = new EmployeeController(viewEmp,modelEmployee);


	}

}
