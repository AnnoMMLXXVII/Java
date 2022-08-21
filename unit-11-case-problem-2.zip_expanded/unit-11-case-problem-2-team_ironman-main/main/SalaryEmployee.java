/**
 * Name: Date: Assignment:
 * 
 * Purpose (Class Description): Model class that is the subtype of Employee
 * object. Overrides the getPayInfo method from the Employee Object.
 */
public class SalaryEmployee extends Employee {

	private final double MINIMUM_SALARY = 40000;
	private double salary;

	public SalaryEmployee(String id, String firstName, String lastName, double salary) {
		super(id, firstName, lastName);
		setSalary(salary);
	}

	/**
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(double salary) {
		this.salary = salary < MINIMUM_SALARY ? MINIMUM_SALARY : salary;
	}

	public String getPayInfo() {
		return String.format("$%.2f per year", getSalary());
	}

	public String toString() {
		return String.format("%s\nCompensation:\t%s\n", super.toString(), getPayInfo());
	}

}
