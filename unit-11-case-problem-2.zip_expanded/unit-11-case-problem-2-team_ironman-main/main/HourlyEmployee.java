/**
 * Name: Date: Assignment:
 * 
 * Purpose (Class Description): Model class that is the subtype of Employee.
 * Overrides the abstract method getPayInfo.
 */
public class HourlyEmployee extends Employee {

	private final double MINIMUM_HOURLY_PAY = 15.00;
	private double hourlyPay;

	public HourlyEmployee(String id, String firstName, String lastName, double hourlyPay) {
		super(id, firstName, lastName);
		setHourlyPay(hourlyPay);
	}

	/**
	 * @return the hourlyPay
	 */
	public double getHourlyPay() {
		return hourlyPay;
	}

	/**
	 * @param hourlyPay the hourlyPay to set
	 */
	public void setHourlyPay(double hourlyPay) {
		this.hourlyPay = hourlyPay < MINIMUM_HOURLY_PAY ? MINIMUM_HOURLY_PAY : hourlyPay;
	}

	@Override
	public String getPayInfo() {
		return String.format("$%.2f per hour", getHourlyPay());
	}

	public String toString() {
		return String.format("%s\nCompensation:\t%s\n", super.toString(), getPayInfo());
	}

}
