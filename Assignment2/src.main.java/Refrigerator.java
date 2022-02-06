
public class Refrigerator extends Appliance {

	private boolean isOpen;

	/**
	 * @param powerStatus
	 * @param room
	 */
	public Refrigerator(String powerStatus, String room) {
		super(powerStatus, room);
		isOpen = false;
	}

	public void openRefrigerator() {
		setOpen(true);
	}

	/**
	 * @return the isOpen
	 */
	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * @param isOpen the isOpen to set
	 */
	private void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
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
		return "Refrigerator [powerStatus=" + getPowerStatus() + ", room=" + getRoom() + "]";
	}

//	private String item;
//	private int t;
//
//	public Refrigerator() {
//		System.out.println("Welcome to the Refrigerator Check");
//	}
//
//	void lookinRefri() {
//		checkRefri();
//	}

}