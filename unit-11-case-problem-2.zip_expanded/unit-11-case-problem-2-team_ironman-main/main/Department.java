import java.util.ArrayList;
import java.util.List;

/**
 * Name: Date: Assignment:
 * 
 * Purpose (Class Description):	Model class that will represent the Department Object
 * Shall House the List of Employees
 */
public class Department {
	private String name;
	private List<Employee> employees;

	/**
	 * @param name
	 * @param employees
	 */
	public Department(String name, List<Employee> employees) {
		setName(name);
		setEmployees(employees);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the employees
	 */
	public List<Employee> getEmployees() {
		return employees;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(List<Employee> employees) {
		this.employees = employees == null ? new ArrayList<>() : employees;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Employee e : getEmployees()) {
			sb.append(e.toString()+"\n");
		}
		return sb.toString();
	}

}
