import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FourStringDuplicateCheck {
	
	private Map<String, Integer> map = new HashMap<>();
	private Scanner z = new Scanner(System.in);
	
	public static void main(String...args) {
		new FourStringDuplicateCheck();
	}
	
	public FourStringDuplicateCheck() {
		stringCheck();
	}
	
	private void stringCheck() {
		System.out.printf("Enter a String: ");
		String input = z.nextLine();
		int i = 0; 
		while(!map.containsKey(input) && i++<4) {
			map.put(input, 1);
			System.out.printf("Enter a String: ");
			input = z.nextLine();
			i++;
		}
		System.err.println("String Already Exists! Please try Again!");
	}
	
}	
