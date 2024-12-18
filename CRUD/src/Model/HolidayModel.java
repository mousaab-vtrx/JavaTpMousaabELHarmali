package Model;
import java.util.ArrayList;

import DAO.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
public class HolidayModel {
    private HolidayDAOimplement daoMain;
    private Holiday holiday;
    private EmployeeDAOimplement daoSub;

    public HolidayModel(HolidayDAOimplement daoMain, EmployeeDAOimplement daoSub) {
        this.daoMain = daoMain;
        this.daoSub = daoSub;
        this.holiday = null;
    }

    public boolean demandHoliday(int idEmployee, LocalDate startDate, LocalDate endDate, TypeConge conge) {
        if (daoSub.findElement(idEmployee) == null) return false; 
        holiday = new Holiday(0, idEmployee, startDate, endDate, conge);
        int dayDiff = (int) ChronoUnit.DAYS.between(startDate, endDate);
        Employees emp = daoSub.findElement(idEmployee);
        if (dayDiff > 25 || emp.getSolde() <= 0) return false; 
        emp.setSolde(dayDiff);
        daoSub.updateElement(emp); 
        daoMain.addElement(holiday);
        return true;
    }

    public boolean updateHoliday(int id, int idEmployee, LocalDate startDate, LocalDate endDate, TypeConge conge) {
        if (daoSub.findElement(idEmployee) == null) return false; // Employee must exist
        holiday = new Holiday(id, idEmployee, startDate, endDate, conge);
        int dayDiff = (int) ChronoUnit.DAYS.between(startDate, endDate);
        Employees emp = daoSub.findElement(idEmployee);
        if (dayDiff > 25 || emp.getSolde() <= 0) return false; // Check for valid leave balance and duration
        emp.setSolde(dayDiff);
        daoSub.updateElement(emp); // Update employee's leave balance
        daoMain.updateElement(holiday); // Update the existing holiday
        return true;
    }

    public boolean deleteHoliday(int id) {
        if (daoMain.findElement(id) == null) return false; // Holiday must exist
        daoMain.deleteElement(id); // Delete the holiday
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


