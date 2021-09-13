package com.question.reverseStrings;

import java.util.Stack;

public class ReversingStrings {

	private static ReversingStrings instance;

	public static ReversingStrings getInstance() {
		if (instance == null) {
			synchronized (ReversingStrings.class) {
				if (instance == null) {
					return new ReversingStrings();
				}
			}
		}
		return instance;
	}
	
	public void reverseString_V1(String str) {
			Stack<String> stack = new Stack<>();
			int n = str.length();
			for (int i = 0; i < n; i++) {
				stack.push(str.substring(i, i + 1));
			}
			String reverseStr = "";
			for (int i = 0; i < n; i++) {
				reverseStr = reverseStr + stack.pop();
			}
			System.out.println(reverseStr);
	}
	
	public void reverseString_V2(String str) {
		Stack<Character> stack = new Stack<>();
		char[] letters = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			letters[i] = str.charAt(i);
			stack.push(letters[i]);
		}
		for (int i = 0; i < letters.length; i++) {
			System.out.print(stack.pop());
		}
		System.out.println();
	}

}
