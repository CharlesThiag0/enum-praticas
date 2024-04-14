package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entities.enums.WorkerLevel;

public class Worker {
    private String name;
    private WorkerLevel level;
    private double baseSalary;

    private Department department;
    private List<HourContract> contracts = new ArrayList<>();

    public Worker() {};

    public Worker(String name, WorkerLevel level, double baseSalary, Department department) {
        this.name = name;
        this.level = level;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkerLevel getLevel() {
        return level;
    }

    public void setLevel(WorkerLevel level) {
        this.level = level;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void addContract(HourContract hourContract) {
        contracts.add(hourContract);
    }

    public void remove(HourContract hourContract) {
        contracts.remove(hourContract);
    }

    public double income(int month, int year) {
        double sum = this.baseSalary;
        Calendar calendar = Calendar.getInstance();

        for(HourContract c : contracts) {
            calendar.setTime(c.getDate());

            int c_year = calendar.get(Calendar.YEAR);
            int c_month = 1 + calendar.get(Calendar.MONTH);

            if(year == c_year && month == c_month) {
                sum += c.totalValue();
            }
        }

        return sum;
    }
}
