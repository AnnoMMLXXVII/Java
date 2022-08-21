/**
 * Name: Date: Assignment:
 * 
 * Purpose (Class Description): Model class that will represent the Employee's
 * object. This Model Class will be the super class of the Salary and Hourly
 * Employee object
 */
public abstract class Employee {
	private String id;
	private String firstName;
	private String lastName;

	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 */
	public Employee(String id, String firstName, String lastName) {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public abstract String getPayInfo();

	public String toString() {
		return String.format("Employee Id :\t%s\nEmployee Name:\t%s", getId(), getFirstName() + " " + getLastName());
	}

}
