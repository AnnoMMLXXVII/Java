package ClassesAndObjects;

import java.util.Scanner;

/*
 * Data Fields
 * 	- name
 * 	- id Number
 * 	- hourly Pay Rate
 * 	- hours Worked
 * 	Accessors and Mutators class
 * 	another method to find gross pay
 * 		- hours worked X hourly pay rate
 * 	Another program to ask the user to enter the data fro an employee
 *
 */
public class Payroll {
    private String name;
    private int idNumber;
    private double hoursWorked, hourlyPayRate, otWage, otHours;

    // Mutators
    public void setName(String name) {
        this.name = name;
    } // endae Mutator setName

    public void setIDNumber(int idNumber) {
        this.idNumber = idNumber;
    } // endae Mutator setidNumber

    public void setHourlyPayRate(double hourlyPayRate) {
        this.hourlyPayRate = hourlyPayRate;
    } // endae Mutator setHourlyPayRate

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;

        if (hoursWorked > 40) {
            this.otWage = getHourlyPayRate() * (3.0 / 2.0);
            this.otHours = hoursWorked - 40;
        } // endae IF
    } // endae Mutator setHoursWorked

    // Accessors
    public String getName() {
        return name;
    } // endae Accessor getName

    public int getIDNumber() {
        return idNumber;
    } // endae Accessor getIDNumber

    public double getHourlyPayRate() {
        return hourlyPayRate;
    } // endae Accessor getHourlyPayRate

    public double getHoursWorked() {

        return hoursWorked;
    } // endae Accessor hoursWorked

    public double getOTWage() {
        return otWage;
    }

    public double getOTHours() {
        return otHours;
    }

    public double grossPay() {
        double gross = getHourlyPayRate() * getHoursWorked();
        if (getHoursWorked() > 40) {
            gross = gross + (getOTWage() * getOTHours());
            return gross; // returns overtime plus regular gross pay
        } // endae IF
        return gross;
    }

    private Payroll employee1() {
        Payroll emp1 = new Payroll();
        Scanner z = new Scanner(System.in);
        System.out.println("Enter Name: ");
        String userName = z.nextLine();
        emp1.setName(userName);
        System.out.println("Enter ID Number: ");
        int userID = z.nextInt();
        emp1.setIDNumber(userID);
        System.out.println("Enter Pay Rate: ");
        double userRate = z.nextDouble();
        emp1.setHourlyPayRate(userRate);
        System.out.println("Enter Hours Worked: ");
        double userHours = z.nextDouble();
        emp1.setHoursWorked(userHours);
        emp1.grossPay();
        z.close();
        return emp1;
    }

    private Payroll employee2() {
        Payroll emp1 = new Payroll();
        emp1.setName("Dwight Schrute");
        emp1.setIDNumber(2);
        emp1.setHourlyPayRate(22.50);
        emp1.setHoursWorked(45);
        emp1.grossPay();
        return emp1;
    }

    private Payroll employee3() {
        Payroll emp1 = new Payroll();
        emp1.setName("Creed Bratton");
        emp1.setIDNumber(10);
        emp1.setHourlyPayRate(20);
        emp1.setHoursWorked(30);
        emp1.grossPay();
        return emp1;
    } // endae employee3

    private Payroll employee4() {
        Payroll emp1 = new Payroll();
        emp1.setName("Michael Scott");
        emp1.setIDNumber(1);
        emp1.setHourlyPayRate(27.50);
        emp1.setHoursWorked(40);
        emp1.grossPay();
        return emp1;
    } // endae employee3

    public void print(Payroll emp) {
        System.out.println(toString(emp) + "\n");
    }

    public String toString(Payroll emp) {
        if (emp.getHoursWorked() > 40) {
            String result = "Name:\t\t" + emp.getName() +
                    "\nID Number:\t" + emp.getIDNumber() +
                    "\nHourly Wage:\t" + emp.getHourlyPayRate() +
                    "\nOT Hours:\t" + emp.getOTHours() +
                    "\nOT Gross:\t" + emp.getOTWage() * emp.getOTHours() +
                    "\nGross Hours:\t" + emp.getHoursWorked() +
                    "\nGross Pay:\t" + emp.grossPay();
            return result;
        }
        ;

        String result = "Name:\t" + emp.getName() +
                "\nID Number:\t" + emp.getIDNumber() +
                "\nHourly Wage:\t" + emp.getHourlyPayRate() +
                "\nHours Worked:\t" + emp.getHoursWorked() +
                "\nGross Pay:\t" + emp.grossPay();
        return result;
    } // endae toString


    public static void main(String args[]) {
        Payroll p = new Payroll();

        p.print(p.employee1());
        p.print(p.employee2());
        p.print(p.employee3());
        p.print(p.employee4());


    } // endae MAIN

} // endae CLASS
