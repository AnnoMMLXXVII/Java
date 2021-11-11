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
                "You might see this when you walk into a patient’s room. That’s why I’ve included this slide. It’s not uncommon to see this scenario and as a student nurse, I’m sure you will be very intimidated by looking at it.  When you look at it, and really break it down and see what is there, what is going on at the bedside. You will see on the left hand side is your primary IV.  It’s maybe a D5W, it may be lactated ringers, but that’s your primary IV going in. right next to it on the left is a smaller bag, and what you will see attached to it is an antibiotic.  On the right hand side is your blood. Your blood being infused through Y tubing.  The Y tubing and the special blood filter.  It has Y tubing because one side of the Y is going to be your blood, and in this case it looks like its type B.  The other side of the Y is normal saline.  They can be on a pump, they can be free flowing. But this is a normal scenario for you to see in a patient setting. It is not unusual to have multiple IV bags hanging and going into your patient. Take time in your clinical setting. Look at it; ask questions, illicit the help of the RN responsible for your patient and your instructor to really understand what is going on and what you are looking at in this scenario. But, remember, don’t ever change a pump setting or turn off an alarm on a pump. "
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
            toStringLines = toStringLines + s + "\n";
        }

    }

    private static void copyToClipboard() {
        StringSelection stringSelection = new StringSelection(toStringLines);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}
