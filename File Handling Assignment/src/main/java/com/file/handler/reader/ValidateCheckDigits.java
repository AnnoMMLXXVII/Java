package com.file.handler.reader;

public class ValidateCheckDigits {

	public ValidateCheckDigits() {
		/*
		 *
		 */
	}

	public static boolean isValidAccount(String number) {
		int sum = 0;
		String firstFive = number.substring(0, number.length() - 1);
		for (int i = 1; i <= firstFive.length(); i++) {
			sum = sum + (Integer.parseInt(firstFive.substring(i - 1, i)));
		}
		int lastDigit = Integer.parseInt(number.substring(number.length() - 1));
		int mod = sum % 10;
		return (mod == lastDigit) ? true : false;
	}

}
