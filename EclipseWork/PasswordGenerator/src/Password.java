public class Password {
	// Using Singleton method
	private static Password p = null;
	public static Password getInstance() {
		if (p == null) {
			p = new Password();
		}
		return p;
	} // endae Password
	private String passwordString = "ABCDEGFHIJKLMONPQRSTUVWXYZabcdefhijklmnopqrstuvwxyz1234567890!@#$%&";
	private char[] passCodes = new char[10];
	private Password() {
		for (int i = 0; i< 1; i++) {
//			if (i >0) {
//				System.out.println("\t" + i);
//			} // endae IF
			getPassword();
		}
	} // endae CONSTRUCTOR
	
	// calls genPassword and prints it 
	private void getPassword() {
		for (int i = 0; i< genPassword().length; i++) {
			System.out.println(genPassword());
		} // endae FOR
	} // endae FOR
	
	// generates a random series of characters
	private char[] genPassword() {
		for (int i = 0; i< passCodes.length; i++) {
			int rand = (int) (Math.random() * passwordString.length());
			passCodes[i]= passwordString.charAt(rand);
		} // endae getPassword();
		return passCodes;
	} // endae getPassword()
	
	public static void main(String args[]) {
		getInstance();
	}
	
	
} // endae CLASS
