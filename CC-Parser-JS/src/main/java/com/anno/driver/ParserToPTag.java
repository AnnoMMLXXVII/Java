package main.java.com.anno.driver;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.com.anno.shared.CC_Constant;

public class ParserToPTag {

	private static String lines;
	private static String toStringLines = "";
	private static List<String> slideLines = new ArrayList<>();

	public static void main(String... args) {
		lines =
				"In adults, ITP may be medication induced or secondary to an autoimmune disorder. In all cases, it appears to be an immune system dysfunction in which antiplatelet antibodies are formed, which increases platelet destruction. Rather than the 7- to 10-day life span, the platelets may be destroyed in a matter of hours.   Clinical manifestations Bleeding may occur from the nose, around the gums, or from the gastrointestinal tract. In severe cases, bleeding in vital organs such as the brain may prove fatal.  Management If the thrombocytopenia becomes more profound, treatment with glucocorticoids has proven to be beneficial. Risk of hemorrhage is the major complication of any condition that results in thrombocytopenia, and the severity of the thrombocytopenia determines whether there is a risk of spontaneous bleeding. Although initial clinical manifestations of thrombocytopenia are ecchymosis and petechiae, as the platelet count decreases, normal activities such as tooth brushing or sneezing may produce spontaneous bleeding.    Pathophysiology In adults, ITP may be medication induced or secondary to an autoimmune disorder. In all cases, it appears to be an immune system dysfunction in which antiplatelet antibodies are formed, which increases platelet destruction. Rather than the 7- to 10-day life span, the platelets may be destroyed in a matter of hours.   Clinical manifestations Bleeding may occur from the nose, around the gums, or from the gastrointestinal tract. In severe cases, bleeding in vital organs such as the brain may prove fatal.  Management If the thrombocytopenia becomes more profound, treatment with glucocorticoids has proven to be beneficial. Risk of hemorrhage is the major complication of any condition that results in thrombocytopenia, and the severity of the thrombocytopenia determines whether there is a risk of spontaneous bleeding. Although initial clinical manifestations of thrombocytopenia are ecchymosis and petechiae, as the platelet count decreases, "
				;
		formatAllLines(lines, "\\.");
		printLines();
		copyToClipboard();
	}

	private static List<String> formatAllLines(String lines) {
		List<String> temp = Arrays.asList(lines.split("\r\n"));
		for (String s : temp) {
			s = replaceWithPTag(s);
			slideLines.add(s);
		}
		return slideLines;
	}
	
	private static List<String> formatAllLines(String lines, String delimeter) {
		List<String> temp = Arrays.asList(lines.split(delimeter));
		for (String s : temp) {
			s = replaceWithPTag(s);
			slideLines.add(s);
		}
		return slideLines;
	}
	
	private static List<String> formatAllLines(String lines, String delimeter, String additionalSuffix) {
		List<String> temp = Arrays.asList(lines.split(delimeter));
		for (String s : temp) {
			s = replaceWithPTag(s, additionalSuffix);
			slideLines.add(s);
		}
		return slideLines;

	}

	private static String replaceWithPTag(String line) {
		String prefix = CC_Constant.LEFT_TAG_P + ">";
		String suffix = CC_Constant.RIGHT_TAG_P;
		return String.format("%s%s%s", prefix.trim(), line.trim(), suffix.trim());
	}
	
	private static String replaceWithPTag(String line, String appendSuffix) {
		String prefix = CC_Constant.LEFT_TAG_P + ">";
		String suffix = CC_Constant.RIGHT_TAG_P;
		return String.format("%s%s%s", prefix.trim(), line.trim(), suffix.trim() + appendSuffix);
	}

	private static void printLines() {
		for (String s : slideLines) {
			System.out.println(s);
			toStringLines = toStringLines + s+"\n";
		}

	}

	private static void copyToClipboard() {
		StringSelection stringSelection = new StringSelection(toStringLines);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}
}
