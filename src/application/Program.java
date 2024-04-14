package application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;
import utils.ValidationUtils;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Enter deparment's name>> ");
        String departmentName = sc.nextLine();

        System.out.println("Enter woeker data>> ");
        System.out.print("Name>> ");
        String workerName = sc.nextLine();

        System.out.print("Level (JUNIOR, MID_LEVEL, SENIOR)>> ");
        String level = sc.next();

        System.out.print("Base salary>> ");
        double salary = sc.nextDouble();

        System.out.print("How many contracts to this worker?>> ");
        int totalContracts = sc.nextInt();

        Worker worker = new Worker(workerName, WorkerLevel.valueOf(level), salary, new Department(departmentName));

        // Entrada de dados para cada contrato
        inpuntContractsData(sc, sdf, worker, totalContracts);

        // Calculo de rendimento
        calculateIncome(sc, worker);

        sc.close();
    }

    private static void inpuntContractsData(Scanner sc, SimpleDateFormat sdf, Worker worker, int totalContracts) {
        ValidationUtils valid = new ValidationUtils();
        String INVALID_VALUE = "Invalid value. please enter a positive number.";

        for(int i = 0; i < totalContracts; i++) {
            Date contractDate = null;
            boolean valideDate = false;
            while(!valideDate) {
                System.out.printf("Enter contract #%d data:\n",i + 1);
                System.out.print("Date (DD/MM/YYYY)>> ");
                try {
                    contractDate = sdf.parse(sc.next());
                    valideDate = true;
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter date in the format DD/MM/YYYY.");
                }
            }

            double valuePerHour;
            while(true) {
                System.out.print("Value per hour>> ");
                valuePerHour = sc.nextDouble();

                try {
                    valuePerHour = valid.validValue(sc, valuePerHour);
                    break;
                }catch (IllegalArgumentException e) {
                    System.out.println(INVALID_VALUE);
                }
            }

            int durantionHours;
            while(true) {
                System.out.print("Durantion (hours)>> ");
                durantionHours = sc.nextInt();

                try {
                    durantionHours = valid.validValue(sc, durantionHours);
                    break;
                } catch(IllegalArgumentException e) {
                    System.out.println(INVALID_VALUE);
                }
            }

            worker.addContract(new HourContract(contractDate, valuePerHour, durantionHours));
        }
    }

    private static void calculateIncome(Scanner sc, Worker worker) {
        boolean validInput = false;
        int month = 0;
        int year = 0;


        while(!validInput) {
            System.out.print("Enter month and year to calculate income (MM/YYYY)>> ");
            String monthAndYear = sc.next();

            if(monthAndYear.matches("\\d{2}/\\d{4}")) {
                month = Integer.parseInt(monthAndYear.substring(0, 2));
                year = Integer.parseInt(monthAndYear.substring(3));

                if(month >= 1 && month <=12) {
                    validInput = true;
                } else {
                    System.out.println("Invalid month. Please enter a number between 1 and 12.");
                }

            } else {
                System.out.println("Invalid format. Please enter the date in the format MM/YYYY.");
            }
        }

        System.out.printf("Name>> %s\n", worker.getName());
        System.out.printf("Department>> %s\n", worker.getDepartment().getName());
        System.out.printf("Income for %02d/%04d: %.2f", month, year, worker.income(month, year));
    }

}
