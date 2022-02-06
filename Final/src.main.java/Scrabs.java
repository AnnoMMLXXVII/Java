
public class Scrabs {

	public static void normalize(int[][] arr) {
		int[][] temp = arr;
		double[][] newArr = new double[arr.length][arr[0].length];
		int sum = 0;
		double avg = 0;
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				sum = sum + temp[i][j];
			}
			avg = sum / temp[i].length;
			sum = 0;
			for (int k = i; k < arr.length; k++) {
				for (int h = 0; h < arr[k].length; h++) {
					newArr[k][h] = arr[k][h] / avg;
				}
			}
		}
	}

	public static void scrabble(String[] words) {
		int total = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			sb.append(words[i] + " ");
			for (int j = 0; j < words[i].length(); j++) {
				total = total + getPoint(words[i].toUpperCase().charAt(j));
			}
		}
		System.out.printf("%sis worth %d\n", sb.toString(), total);
	}

	private static int getPoint(char letter) {
		char temp = letter;
		if (isOne(temp)) {
			return 1;
		} else if (isTwo(temp)) {
			return 2;
		} else if (isThree(temp)) {
			return 3;
		} else if (isFour(temp)) {
			return 4;
		} else if (isFive(temp)) {
			return 5;
		} else if (isEight(temp)) {
			return 8;
		} else if (isTen(temp)) {
			return 10;
		}
		return 0;
	}

	private static boolean isOne(char letter) {
		return (letter == ('A') || letter == ('E') || letter == ('I') || letter == ('L') || letter == ('N')
				|| letter == ('O') || letter == ('R') || letter == ('S') || letter == ('T') || letter == ('U'));
	}

	private static boolean isTwo(char letter) {
		return (letter == ('E') || letter == ('G'));
	}

	private static boolean isThree(char letter) {
		return (letter == ('B') || letter == ('C') || letter == ('M') || letter == ('P'));
	}

	private static boolean isFour(char letter) {
		return (letter == ('F') || letter == ('H') || letter == ('V') || letter == ('W') || letter == ('Y'));
	}

	private static boolean isFive(char letter) {
		return (letter == ('K'));
	}

	private static boolean isEight(char letter) {
		return (letter == ('J') || letter == ('X'));
	}

	private static boolean isTen(char letter) {
		return (letter == ('Q') || letter == ('Z'));
	}
}
