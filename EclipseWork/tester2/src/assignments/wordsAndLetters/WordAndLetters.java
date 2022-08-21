package assignments.wordsAndLetters;

public class WordAndLetters {
	private static final String STR_WITH_SPACE = "Welcome to Mis2is2ip2i Bla4k Adam";
	private static final String STR_WITH_NO_SPACE = "Mis2is2ip2iBla4k";
	private static final String STR_WITH_MORE_NUMS = "M2i3s2i5s2i6p2i7B3la4k2";
	private static final String STR_WITH_DOUBLE_DIGIT_NUMS = "M10i23s9i15s2i6p15i7B3la4k11";
	private static final String NUMBERS_REGEX = "[0-9]";
	private static final String STR_WITH_SYMBOLS_AND_DOUBLE_DIGIT_NUMS = "M1*0i-23s9-i15s2i+6\tp15.i7B 3la 4k 11";

	public static void main(String[] args) {
		new WordAndLetters();
	}

	public WordAndLetters() {
		parseString(STR_WITH_SPACE);
		parseString(STR_WITH_NO_SPACE);
		parseString(NUMBERS_REGEX);
		parseString(STR_WITH_MORE_NUMS);
		parseString(STR_WITH_DOUBLE_DIGIT_NUMS);
		parseString(STR_WITH_SYMBOLS_AND_DOUBLE_DIGIT_NUMS);

	}

	private void parseString(String str) {
		StringBuilder sb = new StringBuilder();
		if (str.contains(" ")) {
			String[] temp = str.split(" ");
			for (int i = 0; i < temp.length; i++) {
				sb = parse(temp[i], sb);
				sb.append(" ");
			}
		} else {
			sb = parse(str, sb);
		}
		System.out.println(sb.toString());
	}

	private StringBuilder parse(String temp, StringBuilder sb) {
		String t = temp;
		for (int j = 0; j < t.length(); j++) {
			String current = t.substring(j, j + 1);
			int startIdx = j + 1;
			int endIdx = j + 2;
			String next = t.substring(startIdx, (endIdx) > t.length() ? startIdx : endIdx);
			if (next.matches(NUMBERS_REGEX)) {
				boolean isMultiDigit = false;
				String numberAppend = next;
				int i = ++endIdx;
				int z = ++startIdx;
				while ((numberAppend = t.substring(z, (i) > t.length() ? i - 1 : i)).matches(NUMBERS_REGEX)) {
					isMultiDigit = true;
					next += numberAppend;
					numberAppend = t.substring(z, (i) > t.length() ? i - 1 : i);
					++i;
					++z;
				}
				int repeat = Integer.parseInt(next);
				String repeatStr = "";
				if (repeat == 1) {
					repeatStr = current.repeat(repeat);
				} else if (repeat == 0) {
					repeatStr = current.repeat(0);
				} else {
					repeatStr = current.repeat(repeat - 1);
				}
				sb.append(repeatStr);
				if (isMultiDigit) {
					++j;
				}
//				sb.append(current.repeat(Integer.parseInt(next) == 1 ? Integer.parseInt(next)
//				: Integer.parseInt(next) == 0 ? 0 : Integer.parseInt(next) - 1));
			}
			if (!current.matches(NUMBERS_REGEX)) {
				sb.append(current);
			}
		}
		return sb;
	}

}
