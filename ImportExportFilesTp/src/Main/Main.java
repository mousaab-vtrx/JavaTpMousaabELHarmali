package Main;
import Controller.*;
import DAO.*;
import Model.*;
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
		DAOImportExportEmployeeImplement fileDAO = new DAOImportExportEmployeeImplement();
		DataImportExportEmployeeModel fileModel = new DataImportExportEmployeeModel(fileDAO);
		EmployeeController controllerEmployee = new EmployeeController(modelEmployee,viewEmp,fileModel);
		UserView viewUser = new UserView();
		UserDAOimplement daoUser = new UserDAOimplement();
		UserModel modelUSer = new UserModel(daoUser);
		UserLoginController controllerUser = new UserLoginController(viewUser,modelUSer,view);



	}

}
