
public class DuplicateTest {
	private static char c;

	public static void main(String... args) {
		duplicateTracker("AaBbRe-");  	// a = 2, b = 2, r = 1, e = 1, - = 1
		duplicateTracker("Aa Bb Re");  	// a = 2, b = 2, r = 1, e = 1, SPACE = 2
		duplicateTracker("ABCDEabcde"); // a = 2, b = 2, c = 2, d = 2, e = 2
		duplicateTracker("aBcDe"); 		// a = 1, b = 1, c = 1, d = 1, e = 1 			
	}
	
	private static void duplicateTracker(String str) {
		DuplicateTracker tracker = new DuplicateTracker();
		String lowerCase = str.toLowerCase().trim();
		System.out.println(lowerCase);
		for (int i = 0; i < lowerCase.length(); i++) {
			c = lowerCase.trim().toLowerCase().charAt(i);
			tracker.checkIfCharExists(c);
		}
			tracker.printChars();
	}
}
