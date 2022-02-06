package tester2;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Sample {
	public static void main(String... args) {
		new Sample();
	}

	public Sample() {
		int[] ages = generateAges(3);
		if (ages.length > 0) {
			print(ages);
		}
	}

	private int[] generateAges(int n) {
		try (Scanner z = new Scanner(System.in)) {
			return askForAges(new int[n], z);
		} catch (NullPointerException | NoSuchElementException ex) {
			return new int[0];
		}
	}

	private int[] askForAges(int[] ages, Scanner z) {
		System.out.printf("Please Enter an Age or Enter -1 to exit\n");
		try {
			int i = 0;
			while (i < ages.length) {
				int temp = z.nextInt();
				if (temp < 0) {
					return (ages.length > 0) ? ages : new int[0];
				}
				ages[i] = temp;
				System.out.printf("%s. Please Enter an Age or Enter -1 to exit\n", i + 1);
				i++;
			}
		} catch (InputMismatchException e) {
			System.err.println("Sorry. The please enter digits only (0,1,2,3...)");
			return (ages.length > 0) ? ages : new int[0];
		}
		return ages;
	}

	private void print(int[] ages) {
		for (int i = 0; i < ages.length; i++) {
			System.out.printf("Age %d = %s\n", i, ages[i]);
		}
	}
}
