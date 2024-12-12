package DAO;
import java.util.List;

import Model.Employees;
public interface EmployeeDAOI {
	public void addEmployee(Employees emp);
	public void modifyEmployee(Employees emp);
	public void deleteEmployee(int id);
	public List<Employees> getAllEmployees();
	
}
