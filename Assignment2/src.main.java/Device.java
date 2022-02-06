
/**
 * 
 * Class shall be the root for all sub-type Devices such as Entertainment,
 * Applicance, Security, Light
 * 
 * @author
 *
 */
public abstract class Device {
	// Instance Variables/Fields
	// Class/Global level variables
	private String powerStatus;
	private String room;

	/**
	 * @param powerStatus
	 * @param room
	 */
	public Device(String powerStatus, String room) {
		super();
		this.powerStatus = powerStatus;
		this.room = room;
	}

	/**
	 * @return the powerStatus
	 */
	public String getPowerStatus() {
		return powerStatus;
	}

	/**
	 * @return the room
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * @param powerStatus the powerStatus to set
	 */
	public void setPowerStatus(String powerStatus) {
		this.powerStatus = powerStatus;
	}

	/**
	 * @param room the room to set
	 */
	public void setRoom(String room) {
		this.room = room;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((powerStatus == null) ? 0 : powerStatus.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Device other = (Device) obj;
		if (powerStatus == null) {
			if (other.powerStatus != null)
				return false;
		} else if (!powerStatus.equals(other.powerStatus))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[powerStatus=" + powerStatus + ", room=" + room + "]";
	}

	// SHIFT+CTRL+S

//	// Constructor
//	public Device() {
//		System.out.print("Hello!");
//		powerStatus = "0";
//	}
//
//	Scanner sc = new Scanner(System.in);
////
//	public void ChangePowerStatus() {
//		PowerStatus = sc.next();
//		if (PowerStatus.equals("on")) {
//			System.out.println("Which Room?");
//			room = sc.next();
//			if (room.equals("BedRoom")) {
//				System.out.println(room + " Light turned ON");
//			} else if (room.equals("LivingRoom")) {
//				System.out.println(room + " Light turned ON");
//			} else if (room.equals("BathRoom")) {
//				System.out.println(room + " Light turned ON");
//			} else if (room.equals("Kitchen")) {
//				System.out.println(room + " Light turned ON");
//			}
//		} else if (PowerStatus.equals("off")) {
//			System.out.print("Which Room?");
//			room = sc.next();
//			if (room.equals("BedRoom")) {
//				System.out.println(room + " Light turned OFF");
//			} else if (room.equals("LivingRoom")) {
//				System.out.println(room + " Light turned OFF");
//			} else if (room.equals("BathRoom")) {
//				System.out.println(room + " Light turned OFF");
//			} else if (room.equals("Kitchen")) {
//				System.out.println(room + " Light turned OFF");
//			}
//
//		}
//	}
}
