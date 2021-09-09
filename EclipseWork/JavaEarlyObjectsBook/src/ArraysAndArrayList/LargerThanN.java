package ArraysAndArrayList;

import java.util.Random;

public class LargerThanN {

	private static int[] arr;
	private static Random r;
	
	public static void main(String...args) {
		generateRandomNumbers();
		printGreaterThanN(50, arr);
	}

	public static void printGreaterThanN(int n, int... arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > n) {
				System.out.println(arr[i]);
			}
		}
	}

	public static int[] generateRandomNumbers() {
		r = new Random();
		int[] arr = new int[100];
		int rand = r.nextInt(100) + 0;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand;
			rand = r.nextInt(100) + 0;
		}
		return arr;
	}
}
