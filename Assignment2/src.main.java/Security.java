
public class Security extends Device {

	public Security(String powerStatus, String room) {
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
		return "Security [powerStatus=" + getPowerStatus() + ", room=" + getRoom() + "]";
	}

//	private int CamNum;
//	private int SensorNum;
//
//	public Security() {
//		System.out.println("You are in the Security Zone");
//	}
//
//	void checkCam() {
//		System.out.println("Camera Number?");
//		CamNum = sc.nextInt();
//		System.out.println("Displaying Camera Number " + CamNum);
//
//	}
//
//	void checkMS() {
//		System.out.println("Sensor Number?");
//		SensorNum = sc.nextInt();
//		System.out.println("Nothing suspicious detected on Camera " + CamNum + ",everything is stable");
//	}
}
