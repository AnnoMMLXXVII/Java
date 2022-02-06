
public class HVAC extends Appliance {

	public HVAC(String powerStatus, String room) {
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
		return "HVAC [powerStatus=" + getPowerStatus() + ", room=" + getRoom() + "]";
	}

//	private int t;
//
//	public HVAC() {
//		System.out.println("Welcome to the HVAC Check");
//	}
//
//	void lookinHVAC() {
//		checkHVAC();
//	}

}