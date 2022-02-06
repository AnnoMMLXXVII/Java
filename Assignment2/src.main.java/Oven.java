
public class Oven extends Appliance {

	private int temperature;
	private int time;

	/**
	 * @param powerStatus
	 * @param room
	 */
	public Oven(String powerStatus, String room) {
		super(powerStatus, room);
		this.temperature = 0;
		this.time = 0;
	}

	/**
	 * @return the temperature
	 */
	public int getTemperature() {
		return temperature;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param temperature the temperature to set
	 */
	private void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	/**
	 * @param time the time to set
	 */
	private void setTime(int time) {
		this.time = time;
	}

	public void updateTime(int time) {
		setTime(time);
	}

	public void updateTemperature(int temperature) {
		setTemperature(temperature);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s temperature=%s time=%s", super.toString(), getTemperature(), getTime());
	}

//	private String item;
//	private int t;
//
//	public Oven() {
//		System.out.println("Welcome to the Oven Check");
//	}
//
//	void lookinOven() {
//		checkOven();
//	}

}