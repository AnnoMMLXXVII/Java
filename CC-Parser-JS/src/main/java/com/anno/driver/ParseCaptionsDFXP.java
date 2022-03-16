package main.java.com.anno.driver;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import main.java.com.anno.page.WixNursingNotesPage;
import main.java.com.anno.page.WixNursingNotesStepLib;
import main.java.com.anno.shared.CC_Constant;
import main.java.com.anno.slide.SlideBodyObject;
import main.java.com.anno.slide.SlideJSObject;
import main.java.com.anno.slide.SlideLine;
import main.java.com.anno.slide.SlidePrefixObject;
import main.java.com.anno.slide.SlideSuffixObject;
import main.java.com.anno.slide.WeeklySlideIndex;

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
	private static Map<Integer, List<String>> weeklyIndex = new HashMap<>();
	private static int week;
	private static int start = 0, end = 0;
	private static String jsClippyLines = "";
	private static SlidePrefixObject prefixObject;
	private static SlideBodyObject bodyObject;
	private static SlideSuffixObject suffixObject;

	// If ChromDriver not able to be closed in TaskManager -- taskkill /f /im
	// chromedriver.exe in CMD
	public static void main(String... args) {
		boolean flag = true;
		runConditionally(flag);
	}

	private static void runConditionally(boolean flag) {
		String path = "C:\\Users\\Haku Wei\\Documents\\git\\nursing-health-plan\\VOICE_THREAD_CC\\NUR-202\\WEEK9\\SpinalCordInjury\\js-slides-SpinalCordInjury";
		if (flag) {
			start = 0;
			end = 0;
			runWebScrapper(path);
		} else {
			runSeleniumScrapper(path);
		}
//		int week = 2;
//		String topic = "Connective Tissue Disease -- Arthritis";
//		String html = "diabetes";
//		updateNursingIndex(week, topic, html);
//		readINDEX_JSFile();
	}

	private static void runWebScrapper(String path) {
		images = WebScraper.parsingImagesFromSite(CC_Constant.IMAGE_REPOSITORY);
		initialize();
		run(path, "aSpinalCordInjury");
		copyToClipboard();
	}

	private static void runSeleniumScrapper(String path) {
		WixNursingNotesStepLib wixStepLib = null;
		initialize();
		wixStepLib = new WixNursingNotesStepLib();
		wixStepLib.setWixPage(new WixNursingNotesPage(WixNursingNotesStepLib.configDriver()));
		wixStepLib.startPageParsing(false);
		images = wixStepLib.getAllImagesFromParse("musculoSkeletalTrauma", 47);
//		images.forEach((e, v) -> {
//			if (e.contains("kidneyFailure")) {
//			System.out.printf("%s -> %s\n", e, v);
//			}
//		});
		run(path, "connTissueDiseaseArthritis");
		copyToClipboard();
//		wixPage.closeWindow();
	}

	private static void run(String dir, String title) {
		validateArgs(title);
		File directory = new File(dir);
		imageCountBound = directory.list().length;
		setImageTitle(title);
		generateSlides(Arrays.asList(directory.listFiles()));
	}

	private static void generateSlides(List<File> files) {
		Collections.sort(files, new Comparator<File>() {

			@Override
			public int compare(File o1, File o2) {
				String[] firstFile = splitFileName(o1.getName());
				String[] secondFile = splitFileName(o2.getName());
				return Integer.parseInt(firstFile[1].trim()) - Integer.parseInt(secondFile[1].trim());
			}

		});
		if (start <= 0 || end <= 0) {
			files.forEach(e -> {
				String[] fileName = splitFileName(e.getName());
				SlideJSObject slideObject = new SlideJSObject();
				constructPrefix(Integer.parseInt(fileName[1].trim()), Integer.parseInt(fileName[1].trim()));
				constructBody(e);
				constructSuffix();
				System.out.println();
				slideObject.setPrefix(getPrefixObject());
				slideObject.setBody(getBodyObject());
				slideObject.setSuffix(getSuffixObject());
				slideJSObject.add(slideObject);
			});
		} else {
			for (int i = start; i < end; i++) {
				String[] fileName = splitFileName(files.get(i - 1).getName());
				SlideJSObject slideObject = new SlideJSObject();
				constructPrefix(Integer.parseInt(fileName[1].trim()), Integer.parseInt(fileName[1].trim()));
				constructBody(files.get(i - 1));
				constructSuffix();
				System.out.println();
				slideObject.setPrefix(getPrefixObject());
				slideObject.setBody(getBodyObject());
				slideObject.setSuffix(getSuffixObject());
				slideJSObject.add(slideObject);
			}
		}
	}

	private static void updateNursingIndex(int week, String topic, String file) {
		List<String> weekSection = new ArrayList<>();
		String s = appendNewWeek(new WeeklySlideIndex(week, topic, file));
		System.out.println(s);

//		weeklyIndex.forEach((e,v) -> {
//			if(week == e) {
//				
//			}
//		});
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
//		if (s.contains("{") || s.contains("}")) {
//			return replaceDivContentEditableTag(replacePrefixDivTag(replaceOpenCurlyBracket(
//					replaceCloseBracket(replaceRightPTag(replaceLeftPTag(replaceApostropheWithBreak(s)))))));
//		} else {
		return replaceDivContentEditableTag(
				replacePrefixDivTag(replaceRightPTag(replaceLeftPTag(replaceApostropheWithBreak(s)))));
//		}
	}

	private static List<SlideLine> removeTags(List<SlideLine> lines) {
		trimUnwanntedTags(lines, CC_Constant.PREFIX_TT_TAG);
		trimUnwanntedTags(lines, CC_Constant.PREFIX_BODY_TAG);
		trimUnwanntedTags(lines, CC_Constant.SUFFIX_BODY_TAG);
		trimUnwanntedTags(lines, CC_Constant.SUFFIX_TT_TAG);
		trimUnwanntedTags(lines, CC_Constant.SUFFIX_DIV_TAG);
		trimUnwanntedTags(lines, CC_Constant.EMPTY_P_TAGS);
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

	private static String replaceOpenCurlyBracket(String s) {
		return removeUnwantedNewLine(s.replace(CC_Constant.OPEN_CURLY, "\'<p>"));
	}

	private static String replaceCloseBracket(String s) {
		return removeUnwantedNewLine(s.replace(CC_Constant.CLOSE_CURLY, "</p>'+"));
	}

	private static String removeUnwantedNewLine(String s) {
		return s.contains("\n") ? s.replace("\n", "") : s.replace("\r", "");
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

	private static String appendNewWeek(WeeklySlideIndex index) {
		List<String> list = new ArrayList<>();
		list.add("<li>\n");
		list.add("<a href='" + formatForHREF(index.getWeek(), index.getFile()) + "'>\n");
		list.add(index.getTopic() + "\n");
		list.add("</a>\n");
		list.add("</li>");
		return (list.toString().replace(",", "").replace("[", "").replace("]", "")).trim();
	}

	private static String formatForHREF(int week, String file) {
		return "./WEEK" + week + "/" + file + "/" + file + ".html";
	}

	private static void readINDEX_JSFile() {
		List<String> allLines = new ArrayList<>();
		System.out.println("PARSING");
		try (Scanner z = new Scanner(new FileReader(new File(CC_Constant.NUR202_INDEX)))) {
			while (z.hasNextLine()) {
				String line = z.nextLine();
				if (!line.trim().isBlank() || !line.trim().isEmpty() || !line.trim().equalsIgnoreCase("")) {
					allLines.add(line);
				}
			}
			parseWeeklySection(allLines);
		} catch (FileNotFoundException ex) {
			// Unable to Locate File
			ex.printStackTrace();
		}
	}

	private static void parseWeeklySection(List<String> lines) {
		boolean isSection = false;
		int i = 0;
		for (String line : lines) {
			String trimmed = line;
			isSection = trimmed.trim().equals(CC_Constant.INDEX_PREFIX_SECTION);
			if (isSection) {
				addToSection(i, lines);
			}
			i++;
		}
		weeklyIndex.forEach((e, v) -> {
			System.out.println(v.toString().replace(",", "").replace("[", "").replace("]", ""));
			v.forEach(k -> {
				System.out.println(k.toString().replace(",", "").replace("[", "").replace("]", ""));
			});
		});
	}

	private static void addToSection(int i, List<String> line) {
		List<String> section = new ArrayList<>();
		boolean toStop = false;
		int j = i;
		while (!toStop) {
			String trimmed = line.get(j);
			section.add(line.get(j));
			if (trimmed.trim().contains("<h3>")) {
				week = Integer.parseInt(trimmed.trim().replace("<h3>", "").replace("</h3>", "").replace("Week ", ""));
			}
			if (toStop = trimmed.trim().equalsIgnoreCase(CC_Constant.INDEX_SUFFIX_SECTION)) {
//				System.out.printf("Week %s\n\t->%s", week,
//						section.toString().replace(",", "").replace("[", "").replace("]", ""));
				weeklyIndex.put(week, section);

			}
			j++;
		}
	}

}
