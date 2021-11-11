import java.util.Random;
import java.util.Scanner;

/**
 * 
 * Game of Bulls and Cows For this question, you will write a Java program that
 * implements a game of Bulls and Cows in which the player needs to guess a
 * randomly generated secret 4-digits number. When the player takes a guess, the
 * program reveals the number of digits that match with the secret number. If
 * the matching digits are in the correct positions, they are called “bulls”, if
 * they are in different positions, they are called “cows”. After each guess,
 * the program reveals how many bulls and cows the player’s guess contains
 */
public class BullsAndCows {
	private static Scanner z;
	private static Random r;
	private static int[] guess;
	private static int[] secret;
	private static int attempt = 1;

	public static void main(String... args) {
		playBullsAndCows((int) Math.random());
	}

	private static void playBullsAndCows(int seed) {
		z = new Scanner(System.in);
		displayWelcome();
		boolean isCorrect = false;
		boolean hasQuit = false;
		guess = new int[4];
		secret = generateSecretDigits(new Random().nextInt());
		System.out.println("The game has begun. Dare to guess a unique 4-digit number?");
		while (!isCorrect && !hasQuit) {
			String userGuess = z.next();
			try {
				if (Integer.parseInt(userGuess) < 0 || userGuess.length() != 4) {
					System.out.printf(
							"Sorry. The guess you've provided is invalid: Must be non-negative and have 4 digits.\n");
				} else {
					for (int i = 0; i < guess.length; i++) {
						guess[i] = Integer.parseInt(userGuess.substring(i, i + 1));
					}
					int bull = getNumOfBulls(secret, guess);
					int cow = getNumOfCows(secret, guess);
					System.out.printf("Bulls: %d\n", bull);
					System.out.printf("Cows: %d\n", cow);
					if (cow == 0 && bull == 4) {
						isCorrect = true;
						printSuccessMessage(attempt);
					}
				}
			} catch (NumberFormatException e) {
				System.err.printf("Invalid Number! Please enter Digits only!\n");
			}
			if (attempt == 6) {
				System.out.print("Do you Want to Exit? [Y/N]: ");
				String response = z.next();
				while (!response.isEmpty() && !hasQuit) {
					if (response.equalsIgnoreCase("Y")) {
						System.out.printf("Game has ended after %d attempts\n", attempt);
						printEndMessage(secret);
						hasQuit = true;
					} else if (response.equalsIgnoreCase("N")) {
						break;
					} else {
						System.out.print("Give up? Yes[Y] or No[N]: ");
						response = z.next();
					}
				}
			}
			if (!hasQuit) {
				System.out.printf("Attempt #%d: ", attempt);
				attempt++;
			}
		}

	}

	private static void displayWelcome() {
		System.out.println("***************************************");
		System.out.println("* Welcome to the Cows and Bulls Game  *");
		System.out.println("***************************************");
		System.out.println("* Fun fact: This game is also known   *");
		System.out.println("* as Pigs and Bulls or Bulls and Cows *");
		System.out.println("***************************************");
		System.out.println("");
	}

	private static void printEndMessage(int[] secret) {
		System.out.printf("The Secret Code : ");
		printArray(secret);
		System.out.println();
		System.out.println("***********************************");
		System.out.println("* Thank you very much for playing *");
		System.out.println("***********************************");
	}

	private static void printSuccessMessage(int attempt) {
		System.out.println("CONGRATULATIONS!");
		System.out.printf("YOU GUESSED THE SECRET CODE ON %s ATTEMPT(S)!\n", attempt);
		printEndMessage(secret);
	}

	private static boolean contains(int[] array, int integer) {
		for (int i = 0; i < array.length; i++) {
			if (integer == array[i]) {
				return true;
			}
		}
		return false;
	}

	private static int[] generateSecretDigits(int seed) {
		r = new Random(seed);
		if (secret == null || secret.length == 0) {
			secret = new int[4];
		}
		for (int i = 0; i < secret.length; i++) {
			int digit = r.nextInt(9) + 0;
			if (!contains(secret, digit)) {
				secret[i] = digit;
			} else {
				secret[i] = (digit > 5) ? r.nextInt(9) + digit + 1 : r.nextInt((digit < 0) ? 0 : digit) + 0;
			}
		}
//		printArray(secret);
		return secret;
	}

	private static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.printf("%s", array[i]);
		}
		System.out.println();
	}

	private static int getNumOfBulls(int[] secret, int[] guess) {
		int bull = 0;
		if (secret.length != guess.length) {
			throw new IllegalArgumentException("Illegal Argument Exception: Guessed length does not match 4");
		}
		for (int i = 0, j = 0; i < secret.length && j < guess.length; i++, j++) {
			if (guess[i] == secret[j] && i == j) {
				bull++;
			}
		}
		return bull;
	}

	private static int getNumOfCows(int[] secret, int[] guess) {
		int cow = 0;
		if (secret.length != guess.length) {
			throw new IllegalArgumentException("Illegal Argument Exception: Guessed length does not match 4");
		}
		for (int i = 0; i < secret.length; i++) {
			for (int j = 0; j < guess.length; j++) {
				if (guess[i] == secret[j] && i != j) {
					cow++;
				}
			}
		}
		return cow;
	}
}
