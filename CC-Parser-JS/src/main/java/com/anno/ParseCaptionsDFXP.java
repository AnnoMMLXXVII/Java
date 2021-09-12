package main.java.com.anno;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ParseCaptionsDFXP {

	private static final String APOSTROPHE = "'";
	private static final String LEFT_TAG_P = "<p";
	private static final String RIGHT_TAG_P = "p>";
	private static final String DIV_EDITABLE_TAG = "-us\">";
	private static final String PREFIX_TT_TAG = "<tt xmlns=\"http://www.w3.org/2006/10/ttaf1\">";
	private static final String PREFIX_BODY_TAG = "<body>";
	private static final String PREFIX_DIV_TAG = "<div"; //
	private static final String SUFFIX_DIV_TAG = "</div>";

	private static int slideNumber = 0;
	private static int imageCount = 0;
	private static int imageCountBound = 0;
	private static String imageTitle = "";
	private static String PREFIX_SLIDE_JQUERY;
	private static String PREFIX_TABLE_TAG;
	private static String IMAGE_TAG;
	private static String IMAGE_EXTRA_TAG;
	private static final String END_TAGS = "'</div></td></tr></tbody></table>');";

	private static final String SUFFIX_BODY_TAG = "</body>";
	private static final String SUFFIX_TT_TAG = "</tt>";
	private static final String CKEDITOR = "CKEDITOR.replace('div');";
	private static List<SlideBodyObject> slideBody = new ArrayList<>();
	private static List<SlideJSObject> slideJSObject = new ArrayList<>();
	private static List<String> disposedTags = new ArrayList<>();
	private static SlidePrefixObject prefixObject;
	private static SlideBodyObject bodyObject;
	private static SlideSuffixObject suffixObject;

	public static void main(String... args) {
		initialize();
		run();
	}

	private static void run() {
		SlideJSObject slideObject;
		File directory = new File("js-slides");
		imageCountBound = directory.list().length + 1;
		setImageTitle("NonInfectLowAirCOPD");
		List<File> files = Arrays.asList(directory.listFiles());
		for (File f : files) {
			String[] fileName = splitFileName(f.getName());
			slideObject = new SlideJSObject();
			constructPrefix(Integer.parseInt(fileName[1].trim()), Integer.parseInt(fileName[1].trim()));
			constructBody(f);
			constructSuffix();
			System.out.println();
			slideObject.setPrefix(getPrefixObject());
			slideObject.setBody(getBodyObject());
			slideObject.setSuffix(getSuffixObject());
			slideJSObject.add(slideObject);
		}
	}

	private static void initialize() {
		disposedTags.add(PREFIX_TT_TAG);
		disposedTags.add(PREFIX_BODY_TAG);
		disposedTags.add(SUFFIX_BODY_TAG);
		disposedTags.add(SUFFIX_TT_TAG);
		disposedTags.add(SUFFIX_DIV_TAG);
		initializeStaticValues();
	}

	private static SlideBodyObject parseFileCCDFXP(File file) {
		SlideBodyObject bodyObject = null;
		List<SlideLine> temp = new ArrayList<>();
		try (Scanner z = new Scanner(new FileReader(file))) {
			String line = "";
			while (z.hasNextLine()) {
				line = z.nextLine().trim();
				line = checkIfLineIsValid(line);
				if (!line.isEmpty() || !line.equals("")) {
					temp.add(new SlideLine(line));
				}
			}
		} catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		}
		removeTags(temp);
		printLines(temp);
		bodyObject = new SlideBodyObject(temp);
		slideBody.add(bodyObject);
		return bodyObject;
	}

	private static String checkIfLineIsValid(String s) {
		boolean found = false;
		for (String t : disposedTags) {
			if (t.equalsIgnoreCase(s)) {
				found = true;
			}
		}

		return found ? "" : runThroughReplaces(s);
	}

	private static String runThroughReplaces(String s) {
		return replaceDivContentEditableTag(
				replacePrefixDivTag(replaceRightPTag(replaceLeftPTag(replaceApostropheWithBreak(s)))));
	}

	private static List<SlideLine> removeTags(List<SlideLine> lines) {
		trimUnwanntedTags(lines, PREFIX_TT_TAG);
		trimUnwanntedTags(lines, PREFIX_BODY_TAG);
		trimUnwanntedTags(lines, SUFFIX_BODY_TAG);
		trimUnwanntedTags(lines, SUFFIX_TT_TAG);
		trimUnwanntedTags(lines, SUFFIX_DIV_TAG);
		return lines;
	}

	private static void trimUnwanntedTags(List<SlideLine> lines, String s) {
		SlideLine temp = new SlideLine(s);
		int index = -1;
		for (SlideLine sl : lines) {
			if (sl.equals(temp)) {
				index = lines.indexOf(sl);
			}
		}
		if (index != -1) {
			lines.remove(index);
		}
	}

	private static String replaceApostropheWithBreak(String s) {
		return s.replace(APOSTROPHE, "\\'");
	}

	private static String replaceLeftPTag(String s) {
		return s.replace(LEFT_TAG_P, "\'<p");
	}

	private static String replaceRightPTag(String s) {
		return s.replace(RIGHT_TAG_P, "p>'+");
	}

	private static String replacePrefixDivTag(String s) {
		return s.replace(PREFIX_DIV_TAG, "'<div");
	}

	private static String replaceDivContentEditableTag(String s) {
		return s.replace(DIV_EDITABLE_TAG, "-us\" contenteditable=\"true\">'+");
	}

	private static void printLines(List<SlideLine> lines) {
		for (SlideLine s : lines) {
			System.out.println(s);
		}
	}

	private static SlidePrefixObject prefixInitialization() {
		List<SlideLine> prefixLines = new ArrayList<>();
		prefixLines.add(new SlideLine(PREFIX_SLIDE_JQUERY));
		prefixLines.add(new SlideLine(PREFIX_TABLE_TAG));
		prefixLines.add(new SlideLine(IMAGE_TAG));
		prefixLines.add(new SlideLine(IMAGE_EXTRA_TAG));

		for (SlideLine sl : prefixLines) {
			System.out.println(sl.toString());
		}
		return new SlidePrefixObject(prefixLines);
	}

	private static SlideSuffixObject suffixInitialization() {
		List<SlideLine> suffixLines = new ArrayList<>();
		suffixLines.add(new SlideLine(END_TAGS));
		suffixLines.add(new SlideLine(CKEDITOR));
		printLines(suffixLines);
		return new SlideSuffixObject(suffixLines);
	}

	private static String[] splitFileName(String s) {
		return s.split("_");
	}

	protected static String getImageTitle() {
		return imageTitle;
	}

	protected static void setImageTitle(String imageTitle) {
		ParseCaptionsDFXP.imageTitle = imageTitle;
	}

	private static void constructPrefix(int slideCounter, int imageCounter) {
		slideNumber = slideCounter;
		imageCount = imageCounter;
		initializeStaticValues();
		prefixObject = prefixInitialization();
		setPrefixObject(prefixObject);
	}

	private static void constructBody(File f) {
		bodyObject = parseFileCCDFXP(f);
		setBodyObject(bodyObject);
	}

	private static void constructSuffix() {
		suffixObject = suffixInitialization();
		setSuffixObject(suffixObject);
	}

	private static void initializeStaticValues() {
		PREFIX_SLIDE_JQUERY = "$('#slide" + slideNumber + "').append(";
		PREFIX_TABLE_TAG = "'<table><tbody><tr><td>'+";
		IMAGE_TAG = "'<img src=\"./pictures/" + getImageTitle() + "_" + imageCount + "of" + imageCountBound + "'+";
		IMAGE_EXTRA_TAG = "'.png\" alt=\"\" srcset=\"\" height = \"450\" width=\"750\">"
				+ "</td></tr>'+\r\n'<tr><td>'+";
	}

	public static SlidePrefixObject getPrefixObject() {
		return prefixObject;
	}

	public static SlideBodyObject getBodyObject() {
		return bodyObject;
	}

	public static SlideSuffixObject getSuffixObject() {
		return suffixObject;
	}

	public static void setPrefixObject(SlidePrefixObject prefixObject) {
		ParseCaptionsDFXP.prefixObject = prefixObject;
	}

	public static void setBodyObject(SlideBodyObject bodyObject) {
		ParseCaptionsDFXP.bodyObject = bodyObject;
	}

	public static void setSuffixObject(SlideSuffixObject suffixObject) {
		ParseCaptionsDFXP.suffixObject = suffixObject;
	}

}
