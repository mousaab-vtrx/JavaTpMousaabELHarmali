package MODELholiday;
public class Holiday {
	private String startDate,endDate;
	private TypeConge conge;
	private int idEmployee;
	public Holiday(int idEmployee,String startDate, String endDate, TypeConge conge) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.conge = conge;
		this.idEmployee = idEmployee;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public TypeConge getConge() {
		return conge;
	}
	public void setConge(TypeConge conge) {
		this.conge = conge;
	}
	
	public int getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(int id) {
		this.idEmployee = id;
	}
	
	
	
	
}