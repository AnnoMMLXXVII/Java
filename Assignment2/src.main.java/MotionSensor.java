
public class MotionSensor extends Security {

	/**
	 * @param powerStatus
	 * @param room
	 */
	public MotionSensor(String powerStatus, String room) {
		super(powerStatus, room);
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
		return "MotionSensor [powerStatus=" + getPowerStatus() + ", room=" + getRoom() + "]";
	}

//	private int SensorNum;
//
//	public MotionSensor() {
//		System.out.println("Welcome to the MotionSensor System");
//	}
//
//	void CheckMotionSensor() {
//		checkMS();
//	}

}