
public class House {
	private String type;
	private int height;

	/**
	 * @param type
	 * @param height
	 */
	public House(String type, int height) {
		try {
			if (type != null && !type.trim().isBlank() && isValidType(type.trim()) && height > -1 ) {
				this.type = type;
				this.height = height;
			}
			else {
				throw new IllegalArgumentException("Invalid House Type or Invalid Height:\nHeight cannot be less than zero."
						+ "\nAvailable House Options: Cabin, Bungalow, High Rise");
			}
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}
	}

	/**
	 * @param type
	 * @return
	 */
	public boolean isValidType(String type) {
		return (type.equalsIgnoreCase("Cabin") || type.equalsIgnoreCase("Bungalow")
				|| type.equalsIgnoreCase("High Rise"));
	}

	/**
	 * 
	 */
	public void upgrade() {
		try {
			if (this.type.equalsIgnoreCase("Cabin")) {
				this.type = "Bungalow";
				this.height = this.height * 2;
			} else if (this.type.equalsIgnoreCase("Bungalow")) {
				this.type = "High Rise";
				this.height = this.height * 2;
			} else if (this.type.equalsIgnoreCase("High Rise")) {
				throw new IllegalArgumentException("Unable to Upgrade House - High Rise");
			}
		} catch (IllegalArgumentException|NullPointerException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		String s = "";
		try {
			s = String.format("%s%d", this.type.substring(0,1).toUpperCase(), this.height);
		}catch(NullPointerException e) {
			s = String.format("Unable to print House Infomation: %s", e.getMessage());
		}
		return s;
	}
}
