import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Name: Date: Assignment:
 * 
 * Purpose (Class Description): Demo class that will prompt the user to enter
 * the department's and employee's information
 */
public class DepartmentDemo {
	private Scanner z;

	public static void main(String[] args) {
		new DepartmentDemo();
	}
	
	/**
	 * Constructor for the Demo Class
	 * Instantiates a the global Scanner and ArrayList Object
	 * Calls the run() method
	 */
	public DepartmentDemo() {
		z = new Scanner(System.in);
		run();
	}

	/**
	 * Method that houses the main logic of this Demo Class. 
	 * First, ask for the Department Name
	 * Second, ask for the number of employees in the department
	 * Third, ask for N employee's information
	 * Fourth, display department's information 
	 */
	private void run() {
		System.out.println("Please enter the Department Name: ");
		String dept = z.nextLine();
		while (dept.equalsIgnoreCase("")) {
			dept = z.nextLine();
		}
		System.out.printf("Please enter number of employees in the %s department\n", dept);
		int deptEmployeeCount = askNumberOfEmployeesInTheDepartment(dept);
		List<Employee> employees = askForEmployeesForDepartment(deptEmployeeCount);
		Department department = new Department(dept, employees);
		System.out.printf("---%s Department Information---\n", department.getName());
		System.out.println(department.toString());
	}

	/**
	 * Helper Method that will ask for the number of employees under a particular
	 * department Forces users to enter a positive number and a valid number type
	 * 
	 * @param dept
	 * @return Integer
	 */
	private int askNumberOfEmployeesInTheDepartment(String dept) {
		int temp = 0;
		try {
			temp = z.nextInt();
			if (temp <= 0) {
				System.out.println("--Please Enter a positive integer.");
				return askNumberOfEmployeesInTheDepartment(dept);
			}
		} catch (InputMismatchException ex) {
			z.nextLine();
			System.err.println("--Invalid Input. Please Enter a Number.");
			return askNumberOfEmployeesInTheDepartment(dept);
		}
		z.nextLine();
		return temp;
	}

	/**
	 * Helper method that will ask for the Department's Employees with based on the
	 * parameter Each Employee is added to a list and the list will be returned
	 * 
	 * @param count
	 * @return List : Employee
	 */
	private List<Employee> askForEmployeesForDepartment(int count) {
		int i = 0;
		List<Employee> temp = new ArrayList<>();
		while (i < count) {
			System.out.printf("Please Enter Employee %d information:\n", i + 1);
			temp.add(askForEmployeeInfo(i + 1));
			i++;
		}
		return temp.isEmpty() ? new ArrayList<>() : temp;
	}

	/**
	 * Method that will ask for the Employee information (Id, First, Last, Pay)
	 * Utillizes the askForCompensation helper method Input validation such if the
	 * User validation for eac
	 * 
	 * @param count
	 * @return Employee
	 */
	private Employee askForEmployeeInfo(int count) {
		String id = "";
		String firstName = "";
		String lastName = "";
		String employeeType = "";
		double pay = -1.00;
		// Ask for Employee's Id
		System.out.printf("Enter Employee's %d Id (AlphaNumeric): ", count);
		id = z.next();
		// Ask for Employee's First Name
		System.out.printf("Enter Employee's %d First Name: ", count);
		firstName = z.next();
		// Ask for Employee's Last Name
		System.out.printf("Enter Employee's %d Last Name: ", count);
		lastName = z.next();
		// Ask for Employee's Pay Type
		System.out.print("Enter Type of Employee (Hourly or Salary): ");
		employeeType = z.next();
		/**
		 * Special condition that will keep loop true if entered value is empty OR not
		 * hourly, or not salary
		 */
		while (employeeType.trim().equalsIgnoreCase("") || !Arrays
				.asList("Hourly".toLowerCase(), "Salary".toLowerCase()).contains(employeeType.toLowerCase())) {
			System.out.println("--Please Re-Enter Type of Employee (Hourly or Salary)");
			employeeType = z.next();
			z.nextLine();
		}
		// Ask for Employee's Pay
		System.out.printf("Enter Employee's %s Pay: ", employeeType);
		pay = askForCompensation(employeeType);
		// Return Hourly or Salary Employee Object Based on Employee Type
		return employeeType.equalsIgnoreCase("Hourly") ? new HourlyEmployee(id, firstName, lastName, pay)
				: new SalaryEmployee(id, firstName, lastName, pay);
	}

	/**
	 * Helper Method with Try-Catch InputMisMatchException that will keep asking the
	 * user for a valid compensation Invalid Inputs can be AlphaNumeric, Alphabets,
	 * Negative Values
	 * 
	 * @param type
	 * @return
	 */
	private double askForCompensation(String type) {
		double pay = -1.00;
		try {
			pay = z.nextDouble();
			while (pay <= 0.00) {
				z.nextLine();
				System.out.printf("--Please Re-Enter the %s Pay\n", type);
				pay = z.nextDouble();
			}
		} catch (InputMismatchException ex) {
			// Recursive call to keep asking the users for a valid input
			z.nextLine();	
			System.err.println("--Invalid Input. Numbers only.");
			return askForCompensation(type);
		}
		return pay;
	}

}
