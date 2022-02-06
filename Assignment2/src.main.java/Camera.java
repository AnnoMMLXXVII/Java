
public class Camera extends Security {

	/**
	 * @param powerStatus
	 * @param room
	 */
	public Camera(String powerStatus, String room) {
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
		return "Camera [powerStatus=" + getPowerStatus() + ", room=" + getRoom() + "]";
	}

//	private int CamNum;
//
//	public Camera() {
//		System.out.println("Welcome to the Camera System");
//	}
//
//	void CheckCamera() {
//		checkCam();
//	}
}