package main.java.com.anno.text.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import main.java.com.anno.slide.SlideLine;

public class TextParser {
//	private static final String SINGLE_ANS_REGEX = "ANS: ([A-F+])$";
//	private static final String MULTI_ANS_REGEX = "ANS: ((([A-F+])*,)\s)+[A-F]$";
	private static final String ANS_REGEX = "ANS: ((([A-F]),) )*[A-F]";
	private static final String INLINE_NUMBER_DELIM = "\s[0-9]\\.+\s(([A-z]+\s)+)";
	private static final String CHOICE_WITH_EXTRA_NEWLINE = "^[a-f].\\s\\n+";
	private static final String REMOVEABLE_LINK = "abirb.com/test".trim();
	private static final String START_OF_EMPTY_LINE = "^\n";
	private static final String REPLACE_TO_APOSTROPHE = "â€™";
	private static final String REMOVE_UNKNWON_1 = "â€";
	private static final String REMOVE_UNKNWON_2 = "Â";
	private static final String REMOVE_UNKNWON_3 = "™";
	private static final String REMOVE_UNKNWON_4 = "œ";
	private static final String REMOVE_UNKNWON_5 = "”";
	private static final String PLACEHOLDER_REMOVE = "REMOVE";

	private static final String NUMBERED_QUESTIONS = "^[0-9]+\\.";

	/*
	 * 1. Remove Link 2. Trim/Remove All empty Lines
	 * 
	 */

	public static void main(String... args) {
		String directoryName = "textToBeParsed";

		extractFilesFromDir(directoryName);
	}

	private static void extractFilesFromDir(String dir) {
		File directory = new File(dir);
		List<File> files = Arrays.asList(directory.listFiles());
		for (File f : files) {
			readLinesToNewSlides(f);
		}
	}

	private static void readLinesToNewSlides(File file) {
		String fileName = file.getName();
		List<SlideLine> fileLines = null;
		System.err.printf("-------- NEW FILE : %s----------\n", fileName);
		try (Scanner z = new Scanner(new FileReader(file))) {
			fileLines = new ArrayList<>();
			while (z.hasNextLine()) {
				String line = checkIfReplaceable(z.nextLine().trim());
				if (!line.isBlank() || !line.isEmpty() || !line.equals("\n") || !line.equalsIgnoreCase("")) {
					fileLines.add(new SlideLine(
							((line.isBlank() || line.contains(START_OF_EMPTY_LINE)) ? PLACEHOLDER_REMOVE : line)));
				}
			}
			fileLines = removeAllEmptyLines(fileLines);
//			printLines(fileLines);
			writeToUpdatedFile(fileName, fileLines);
		} catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		}
	}

	private static List<SlideLine> updateNumberedQuestions(List<SlideLine> fileLines) {
		for (SlideLine sl : fileLines) {
			if (sl.getLine().contains(NUMBERED_QUESTIONS)) {
				sl.setLine(newLineAfterEveryQuestion(sl.getLine()));
			}
		}
		return fileLines;
	}

	private static String newLineAfterEveryQuestion(String str) {
		String temp = str;
		return str.replaceAll(NUMBERED_QUESTIONS, "\n" + temp);
	}

	private static String checkIfReplaceable(String str) {
		return replaceRemovableLink(
				replaceMultiAnswerToBlank(replaceToApostrophe(newLineAfterEveryQuestion(removeUnknowns(str)))));
	}

	private static String removeUnknowns(String str) {
		return replaceUnknownFive(replaceUnknownFour(replaceUnknownThree(replaceUnknownTwo(replaceUnknownOne(str)))));
	}

	private static String replaceUnknownOne(String str) {
		return str.replace(REMOVE_UNKNWON_1, "");
	}

	private static String replaceUnknownTwo(String str) {
		return str.replace(REMOVE_UNKNWON_2, "");
	}

	private static String replaceUnknownThree(String str) {
		return str.replace(REMOVE_UNKNWON_3, "");
	}

	private static String replaceUnknownFour(String str) {
		return str.replace(REMOVE_UNKNWON_4, "");
	}

	private static String replaceUnknownFive(String str) {
		return str.replace(REMOVE_UNKNWON_5, " ");
	}

	private static String replaceToApostrophe(String str) {
		return str.replace(REPLACE_TO_APOSTROPHE, "\'");
	}

	private static String replaceMultiAnswerToBlank(String str) {
		return str.replaceAll(ANS_REGEX, "\n[ANS : REMOVED]\n");
	}

	private static String replaceRemovableLink(String str) {
		return str.replaceAll(REMOVEABLE_LINK, "");
	}

	private static List<SlideLine> removeAllEmptyLines(List<SlideLine> lines) {
		List<SlideLine> temp = new ArrayList<>();
		String slideLine = "---------";
		for (SlideLine sl : lines) {
			slideLine = sl.getLine();
			if (!slideLine.trim().equalsIgnoreCase(PLACEHOLDER_REMOVE.trim())) {
				temp.add(sl);
			}
		}
		return temp;
	}

	private static void printLines(List<SlideLine> lines) {
		for (SlideLine sl : lines) {
			System.out.println(sl.toString());
		}
	}

	private static void writeToUpdatedFile(String fileName, List<SlideLine> lines) {
		String dir = "updatedParsedText";
		try (FileWriter fw = new FileWriter(new File(dir + "/" + fileName + "_REMOVED_ANS.txt"), false)) {
			for (SlideLine sl : lines) {
				fw.write(sl.getLine() + "\n");
			}
			fw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
