package main.java.inheritance.employee;

public class Employee {
	
	protected String firstName;
	protected String lastName;
	protected String socialSecurityNumber;

	/**
	 * @param firstName
	 * @param lastName
	 * @param socialSecurityNumber
	 */
	public Employee(String firstName, String lastName, String socialSecurityNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
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
	 * @return the socialSecurityNumber
	 */
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	@Override
	public String toString() {
		return String.format("commission employee: %s %s%nsocial security number: %s%n", getFirstName(), getLastName(),
				getSocialSecurityNumber());
	}

}
