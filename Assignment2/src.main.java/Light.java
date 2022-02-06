
public class Light extends Device {

	/**
	 * @param powerStatus
	 * @param room
	 */
	public Light(String powerStatus, String room) {
		super(powerStatus, room);
		// Rooms --> Bedroom, Kitchen, Living Room, bathrroom, Garage
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
		return "Light [powerStatus=" + getPowerStatus() + ", room=" + getRoom() + "]";
	}

//	private String PowerStatus;
//	private String room;

//	public Light() {
//		System.out.println("Welcome to the Light System");
//	}
//
//	void LightStatus() {
//		System.out.println("Light on or off");
//		ChangePowerStatus();
//	}
}