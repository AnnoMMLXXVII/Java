package main.java.com.anno.driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.com.anno.shared.CC_Constant;

public class ParserToPTag {

	private static String lines;
	private static List<String> slideLines = new ArrayList<>();

	public static void main(String... args) {
		lines =
				  
				"\r\n" + 
				"Okay just reviewing you want to understand\r\n" + 
				" know what Methotrexate is you want to know the nausea and vomiting medication\r\n" + 
				"as well as pain medications given to these patients" + 
				"";
		formatAllLines(lines);
		printLines();
	}

	private static List<String> formatAllLines(String lines) {
		List<String> temp = Arrays.asList(lines.split("\r\n"));
		for (String s : temp) {
			s = replaceWithPTag(s);
			slideLines.add(s);
		}
		return slideLines;

	}

	private static String replaceWithPTag(String line) {
		String prefix = CC_Constant.LEFT_TAG_P + ">";
		String suffix = CC_Constant.RIGHT_TAG_P;
		return String.format("%s%s%s", prefix.trim(), line.trim(), suffix.trim());
	}

	private static void printLines() {
		for (String s : slideLines) {
			System.out.println(s);
		}
	}
}
