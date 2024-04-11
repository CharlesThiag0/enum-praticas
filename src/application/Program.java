package application;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Enter department's name: ");
        String departmentName = input.nextLine();
        System.out.print("\nEnter worker data:");
        System.out.print("Name: ");
        String workerName = input.nextLine();
        System.out.print("Level: ");
        String workerLevel = input.nextLine();
        System.out.print("Base salary: ");
        double baseSalary = input.nextDouble();

        Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel),
                                   baseSalary, new Department(departmentName));

        System.out.print("How many contracts to this worker ");
        int n = input.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.printf("Enter contract #%d data:\n", i +1);
            System.out.print("Date (DD/MM/YYYY): ");

            Date contractDate = simpleDateFormat.parse(input.next());

            System.out.print("Value per hour: ");
            double valuePerHour = input.nextDouble();

            System.out.print("Duration (hours): ");
            int hours = input.nextInt();

            HourContract hourContract = new HourContract(contractDate, valuePerHour, hours);
            worker.addContracts(hourContract);
        }

        System.out.print("\nEnter month and year to calculate income (MM/YYYY): ");
        String monthAndYear = input.next();

        int month = Integer.parseInt(monthAndYear.substring(0, 2));
        int year = Integer.parseInt(monthAndYear.substring(3));

        System.out.printf("\nName>>> %s", worker.getName());
        System.out.printf("\nDepartment: %s", worker.getDepartment().getName());
        System.out.printf("\nIncome for %s: %.2f", monthAndYear, worker.income(year, month));

        input.close();
    }
}
