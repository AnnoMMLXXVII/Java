package tester2;

public class DrawCross {
	public static void main(String args[]) {
//		drawCross(11);
		System.out.println(crazySubtraction("123"));
		System.out.println(crazySubtraction("223344"));
		System.out.println(crazySubtraction("12.33"));
		System.out.println(isOddDigits(123, 4213));
		System.out.println(isOddDigits(2, 5678902));
		System.out.println(multiplyRemainder(23, 37, 30, 2));
		System.out.println(multiplyRemainder(52, 73, 19, 10));
		System.out.println(subLR("123456"));
		System.out.println(subLR("92834"));
		System.out.println(subLR("-123"));
		System.out.println(subLR("-345020"));

	}

	private static void drawCross(int param) {
		if (param < 1) {
			return;
		}
		int half = param / 2;
		String line = "";
		final String hash = "#";
//		line = hash + addSpaces(half) + hash;		
		for (int i = 0, h = i, j = i; i < param; i++) {

			if (i == half) {
				System.out.println(hash);
			}
			System.out.println(line);
			removeSpaces(line);
		}

	}

	private static String removeSpaces(String string) {
		String first = string.substring(0, 1);
		String middle = string.substring(1, string.length() - 2);
		String last = string.substring(string.length() - 2, string.length() - 1);
		return string.substring(0, string.length() - 1);
	}

	private static int crazySubtraction(String string) {
		int sum = 0;
		int product = 1;
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == 46) {
				continue;
			}
			sum = Integer.parseInt(string.substring(i, i + 1)) + sum;
			product = product * Integer.parseInt(string.substring(i, i + 1));
		}
		return product - sum;
	}

	private static boolean isOddDigits(int first, int second) {
		return ((first % 2 != 0) && (second % 2 != 0));
	}

	private static int multiplyRemainder(int one, int two, int three, int divider) {
		int[] remainders = new int[3];
		int product = 1;
		remainders[0] = one % divider;
		remainders[1] = two % divider;
		remainders[2] = three % divider;
		for (Integer i : remainders) {
			product = product * i;
		}
		return product;
	}

	private static int subLR(String s) {
		boolean isNegative = false;
		boolean isOdd = false;
		System.out.printf("Original : %s and %s\n", s, s.length());
		int half = s.length() / 2;
		if (s.contains("-")) {
			s = s.replace("-", "");
			isNegative = true;
		}
		if (s.length() % 2 != 0) {
			half = (s.length() / 2) + 1;
			isOdd = true;
		}
		System.out.printf("Half : %s :: Length: %s\n", half, s.length());
		int left = 0;
		int right = 0;
		int start = 0;
		try {
			left = Integer.parseInt(s.substring(start, (isOdd) ? half - 1 : half));
			right = Integer.parseInt(s.substring(half, s.length()));
			System.out.printf("Left : %s ::: Right: %s\n", left, right);
		} catch (NumberFormatException e) {
			System.err.println(e.getLocalizedMessage());
		}
		return (isNegative) ? ((-1 * left) - right) : left - right;
	}
	
	

}
