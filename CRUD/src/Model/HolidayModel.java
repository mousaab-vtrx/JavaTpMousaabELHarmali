package Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import DAO.HolidayDAOimplement;
import DAO.EmployeeDAOimplement;

public class HolidayModel {
    private HolidayDAOimplement daoMain;
    private EmployeeDAOimplement daoSub;

    public HolidayModel(HolidayDAOimplement daoMain, EmployeeDAOimplement daoSub) {
        this.daoMain = daoMain;
        this.daoSub = daoSub;
    }

    public boolean demandHoliday(int idEmployee, LocalDate startDate, LocalDate endDate, TypeConge conge) {
        if (daoSub.findElement(idEmployee) == null) return false;
        int dayDiff = (int) ChronoUnit.DAYS.between(startDate, endDate) ; 
        Employees emp = daoSub.findElement(idEmployee);

        if (dayDiff > 25 || dayDiff <= 0 || emp.getSolde() < dayDiff) return false;

        emp.setSolde(emp.getSolde() - dayDiff);
        daoSub.updateElement(emp);

        Holiday holiday = new Holiday(0, idEmployee, startDate, endDate, conge);
        daoMain.addElement(holiday);
        return true;
    }

    public boolean updateHoliday(int id, int idEmployee, LocalDate startDate, LocalDate endDate, TypeConge conge) {
        Holiday existingHoliday = daoMain.findElement(id);
        if (existingHoliday == null || daoSub.findElement(idEmployee) == null) return false;

        int oldDays = (int) ChronoUnit.DAYS.between(existingHoliday.getStartDate(), existingHoliday.getEndDate()) + 1;
        int newDays = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;

        Employees emp = daoSub.findElement(idEmployee);
        int adjustedSolde = emp.getSolde() + oldDays - newDays;

        if (adjustedSolde < 0 || newDays <= 0 || newDays > 25) return false;

        emp.setSolde(adjustedSolde);
        daoSub.updateElement(emp);

        Holiday holiday = new Holiday(id, idEmployee, startDate, endDate, conge);
        daoMain.updateElement(holiday);
        return true;
    }


    public boolean deleteHoliday(int id) {
        Holiday holiday = daoMain.findElement(id);
        if (holiday == null) return false;

        Employees emp = daoSub.findElement(holiday.getIdEmployee());
        int dayDiff = (int) ChronoUnit.DAYS.between(holiday.getStartDate(), holiday.getEndDate()) + 1;
        emp.setSolde(emp.getSolde() + dayDiff);
        daoSub.updateElement(emp);

        daoMain.deleteElement(id);
        return true;
    }

    public List<Object[]> displayHoliday() {
        List<Object[]> data = new ArrayList<>();
        List<Holiday> holidays = daoMain.getAllElements();

        for (Holiday element : holidays) {
            Object[] row = {
                element.getId(),
                element.getIdEmployee(),
                element.getStartDate(),
                element.getEndDate(),
                element.getConge()
            };
            data.add(row);
        }
        return data;
    }

    public List<String> getAllIds() {
        List<Employees> employees = daoSub.getAllElements();
        List<String> ids = new ArrayList<>();
        for (Employees element : employees) {
            ids.add(String.valueOf(element.getId()));
        }
        return ids;
    }
}
