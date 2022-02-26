package four;

import java.util.Random;

public class RecusionForMod5 {

	public static void main(String... args) {
		Random r = new Random(System.currentTimeMillis());
		int[] array = new int[100];
		for (int i = 0; i < 100; i++) {
			array[i] = 0 + r.nextInt(99);
			r.nextInt(99);
		}
		printArray(array, 0);
	}

	private static int printArray(int[] array, int i) {
		if (i == array.length - 1) {
			System.out.printf("%s", array[i]);
			return -1;
		}
		if (i % 25 == 0 && i != 0) {
			System.out.println();
		}
		System.out.printf("%s,", array[i]);
		return printArray(array, ++i);
	}

}
