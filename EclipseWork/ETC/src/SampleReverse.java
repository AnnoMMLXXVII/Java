import java.util.Arrays;

public class SampleReverse {

	/**
	 * @param args
	 */
	public static void main(String... args) {
		int[] oddNumberElements = { 1, 3, 5, 9, 11 };
		int[] evenNumberElements = { 1, 3, 5, 7, 9, 11 };
		reverseV2(oddNumberElements);
		System.out.println("------------------------");
		reverseV2(evenNumberElements);
	}

	private static void reverse(int[] array) {

		System.out.println("Array = " + Arrays.toString(array));

		int temp;
		int count = 0;
		int[] tempArr = new int[array.length];
		for (int i = array.length - 1; i >= 0; i--) {
			temp = array[i];
			tempArr[count] = temp;
			count++;

		}
		for (int j = 0; j < array.length; j++) {
			array[j] = tempArr[j];
		}
		System.out.println("Reversed Array = " + Arrays.toString(array));
	}

	private static void reverseV2(int[] array) {
		System.out.println("Array = " + Arrays.toString(array));
		int temp;
		for (int i = array.length - 1, j = 0; j <= i; i--, j++) {
			temp = array[i]; // Storing array[i] into temp variable
			array[i] = array[j]; // overwriting array[i] value with array[j] value
			array[j] = temp; // overwriting array[j] value with temp value
			System.out.printf("SWAPPED VALUES %d and %d\n", array[i], array[j]);
			System.out.println(Arrays.toString(array));
		}
	}

}
