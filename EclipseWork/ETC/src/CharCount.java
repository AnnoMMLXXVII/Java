import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CharCount {

	public static void main(String... args) {
		countChars(new File("src/output.txt"));
	}

	public static void countChars(File file) {
		Map<Character, Integer> letterCount = new HashMap<>();
		int totalCharCount = 0;
		try (Scanner z = new Scanner(new FileReader(file))) {
			while (z.hasNextLine()) {
				String sentence = z.nextLine();
				for (int i = 0; i < sentence.length(); i++) {
					addToMap(letterCount, sentence.charAt(i));
					totalCharCount++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println(letterCount.toString());
		System.out.println("\n--------------------\n");
		System.out.println("Total Character Count: " + totalCharCount);
	}

	private static Map<Character, Integer> addToMap(Map<Character, Integer> hashMap, Character character) {
		if (hashMap.containsKey(character)) {
			hashMap.put(character, hashMap.get(character) + 1);
		} else {
			hashMap.put(character, 1);
		}
		return hashMap;
	}
}
