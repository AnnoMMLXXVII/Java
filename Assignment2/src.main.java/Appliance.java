
public class Appliance extends Device {

	/**
	 * @param powerStatus
	 * @param room
	 */
	public Appliance(String powerStatus, String room) {
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
		return super.toString();
	}

//	private String item, status;
//	private int t;

//	public Appliance() {
//		System.out.println("You are in the Appliance Check Zone");
//	}
//
//	void checkHVAC() {
//		System.out.println("Should I turn it OFF?");
//		status = sc.next();
//		if (status.equals("off")) {
//			System.out.println("Ok turning it OFF");
//		} else {
//			System.out.println("Ok Keeping it ON");
//		}
//	}
//
//	void checkRefri() {
//		System.out.println("What sholud I check for you in the Refrigerator?");
//		item = sc.next();
//		System.out.println("Yes " + item + " is there.You can come in the kitchen and take it");
//
//	}
//
//	void checkOven() {
//		System.out.println("What should I heat for you in the oven?");
//		item = sc.next();
//		System.out.println("For how many seconds?");
//		t = sc.nextInt();
//		System.out.println("Heating " + item + " for " + t + " seconds");
//
//	}
}
