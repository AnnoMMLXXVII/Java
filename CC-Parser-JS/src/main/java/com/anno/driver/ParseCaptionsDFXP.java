package main.java.com.anno.driver;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import main.java.com.anno.shared.CC_Constant;
import main.java.com.anno.slide.SlideBodyObject;
import main.java.com.anno.slide.SlideJSObject;
import main.java.com.anno.slide.SlideLine;
import main.java.com.anno.slide.SlidePrefixObject;
import main.java.com.anno.slide.SlideSuffixObject;

public class ParseCaptionsDFXP {

	private static int slideNumber = 0;
	private static int imageCount = 0;
	private static int imageCountBound = 0;
	private static String imageTitle = "";
	private static int imageOffSet = 0;
	private static int slideOffSet = 0;
	private static boolean offSetChanged = false;
	private static List<SlideBodyObject> slideBody = new ArrayList<>();
	private static List<SlideJSObject> slideJSObject = new ArrayList<>();
	private static List<String> disposedTags = new ArrayList<>();
	private static List<String> allLines = new ArrayList<>();
	private static Map<String, String> images = new HashMap<>();
	private static String jsClippyLines = "";
	private static SlidePrefixObject prefixObject;
	private static SlideBodyObject bodyObject;
	private static SlideSuffixObject suffixObject;

	public static void main(String... args) {
		images = WebScraper.parsingImagesFromSite(CC_Constant.IMAGE_REPOSITORY);
//		images.forEach((e, v) -> {
//			if (e.contains("kidneyFailure")) {
//				System.out.printf("%s -> %s\n", e, v);
//			}
//		});
		String path = "C:\\Users\\Haku Wei\\Documents\\git\\nursing-health-plan\\VOICE_THREAD_CC\\WEEK10\\Pancreatitis\\js-slides-Pancreatitis";
//		String imageName = args[1];
		initialize();
		run(path, "pancreatitis");
		copyToClipboard();
	}

	private static void run(String dir, String title) {
		validateArgs(title);
		SlideJSObject slideObject;
		File directory = new File(dir);
		imageCountBound = directory.list().length;
		setImageTitle(title);
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
		disposedTags.add(CC_Constant.PREFIX_TT_TAG);
		disposedTags.add(CC_Constant.PREFIX_BODY_TAG);
		disposedTags.add(CC_Constant.SUFFIX_BODY_TAG);
		disposedTags.add(CC_Constant.SUFFIX_TT_TAG);
		disposedTags.add(CC_Constant.SUFFIX_DIV_TAG);
		initializeStaticValues();
	}

	private static void validateArgs(String args) {
		if (args.isBlank() || args.isEmpty() || args.equals("")) {
			System.err.print("INVALID IMAGE FILE NAME : CANNOT BE EMPTY!");
			System.exit(0);
		}
	}

	private static SlideBodyObject parseFileCCDFXP(File file) {
		SlideBodyObject bodyObject = null;
		List<SlideLine> temp = new ArrayList<>();
		try (Scanner z = new Scanner(new FileReader(file))) {
			String line = "";
			while (z.hasNextLine()) {
				line = z.nextLine().trim();
				allLines.add(line);
				line = checkIfLineIsValid(line);
				if (!line.isEmpty() || !line.equals("")) {
					temp.add(new SlideLine(line));
				}
//				System.out.println(line);
			}
		} catch (FileNotFoundException fnf) {
			System.err.println("COULD NOT FIND FILE! " + file.getName());
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
		trimUnwanntedTags(lines, CC_Constant.PREFIX_TT_TAG);
		trimUnwanntedTags(lines, CC_Constant.PREFIX_BODY_TAG);
		trimUnwanntedTags(lines, CC_Constant.SUFFIX_BODY_TAG);
		trimUnwanntedTags(lines, CC_Constant.SUFFIX_TT_TAG);
		trimUnwanntedTags(lines, CC_Constant.SUFFIX_DIV_TAG);
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
		return s.replace(CC_Constant.APOSTROPHE, "\\'");
	}

	private static String replaceLeftPTag(String s) {
		return s.replace(CC_Constant.LEFT_TAG_P, "\'<p");
	}

	private static String replaceRightPTag(String s) {
		return s.replace(CC_Constant.RIGHT_TAG_P, "</p>'+");
	}

	private static String replacePrefixDivTag(String s) {
		return s.replace(CC_Constant.PREFIX_DIV_TAG, "'<div");
	}

	private static String replaceDivContentEditableTag(String s) {
		return s.replace(CC_Constant.DIV_EDITABLE_TAG, "-us\">'+");
	}

	private static void printLines(List<SlideLine> lines) {
		for (SlideLine s : lines) {
			System.out.println(s);
			jsClippyLines = jsClippyLines + s + "\n";

		}
	}

	private static SlidePrefixObject prefixInitialization() {
		List<SlideLine> prefixLines = new ArrayList<>();
		prefixLines.add(new SlideLine(CC_Constant.PREFIX_SLIDE_JQUERY));
		prefixLines.add(new SlideLine(CC_Constant.PREFIX_ARTICLE_TAG));
//		prefixLines.add(new SlideLine("'<br>'+"));
		prefixLines.add(new SlideLine(CC_Constant.IMAGE_TAG));
//		prefixLines.add(new SlideLine(CC_Constant.IMAGE_EXTRA_TAG));

//		for (SlideLine sl : prefixLines) {
//			System.out.println(sl.toString());
//		}
		printLines(prefixLines);
		return new SlidePrefixObject(prefixLines);
	}

	private static SlideSuffixObject suffixInitialization() {
		List<SlideLine> suffixLines = new ArrayList<>();
		suffixLines.add(new SlideLine(CC_Constant.END_TAGS));
//		suffixLines.add(new SlideLine(CC_Constant.CKEDITOR));
		printLines(suffixLines);
		return new SlideSuffixObject(suffixLines);
	}

	private static String[] splitFileName(String s) {
		return s.split("-");
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
		if (!offSetChanged) {
			bodyObject = parseFileCCDFXP(f);
		} else {
			List<SlideLine> slide = new ArrayList<>();
			slide.add(new SlideLine("'<p></p>'+"));
			bodyObject = new SlideBodyObject(slide);
		}
		setBodyObject(bodyObject);
	}

	private static void constructSuffix() {
		suffixObject = suffixInitialization();
		setSuffixObject(suffixObject);
	}

	private static void initializeStaticValues() {
		imageOffSet = getImageOffSet();
		slideOffSet = getSlideOffSet();
		String relativeImagePath = getImageTitle() + "-" + calcWithOffset(imageCount, imageOffSet) + "-"
				+ imageCountBound + ".png";
		String src = images.get(relativeImagePath);
		File imageFile = new File(relativeImagePath);
		String rootPath = imageFile.getAbsolutePath();
		CC_Constant.PREFIX_SLIDE_JQUERY = "$(\'#slide" + calcWithOffset(slideNumber, slideOffSet) + "\').append(";
		CC_Constant.PREFIX_ARTICLE_TAG = "'<article><div>'+";
		CC_Constant.IMAGE_TAG = "'<p>'+\n" + "'<img src=\"" + src + "\" alt=\"" + relativeImagePath
				+ "\" width = \"100%\">'+\n"
//				+ "'<canvas id =canvas" + imageCount + " height=\"163.08\" height=\"148.4\"></canvas>'+\n" 
				+ "'</p>'+\n";
	}

	private static String calcWithOffset(int count, int offset) {
		int result = count + offset;
		return result + "";
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

	private static int getImageOffSet() {
		return imageOffSet;
	}

	private static void setImageOffSet(int imageOffSet) {
		ParseCaptionsDFXP.imageOffSet = imageOffSet;
	}

	private static int getSlideOffSet() {
		return slideOffSet;
	}

	private static void setSlideOffSet(int slideOffSet) {
		ParseCaptionsDFXP.slideOffSet = slideOffSet;
	}

	private static void copyToClipboard() {
		StringSelection stringSelection = new StringSelection(jsClippyLines);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}
}
