import java.sql.Time;
import java.util.Random;

public class RandomAlphaNumeric {
    private Time t;
    private Random r;
    private SecurityManager sm;
    private static String[] alpha = {"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"};


    public Time getSecond() {
        t = new Time(System.currentTimeMillis());
        return t;
    }

    public SecurityManager getSM() {
        sm = new SecurityManager();
        return sm;
    }

    public int getRandom() {
        return r.nextInt(62) + 0;
    }

    public RandomAlphaNumeric() {
        r = new Random();
        getRandom();

    }

    public String getString() {
        String str = null;
        for (int i = 0; i < alpha.length; i++) {
            str = alpha[i];
        }
        return str;
    }

    public String getSubString(String str) {
        String conStr = null;
        String[] let = new String[str.length()];
        System.out.println(let.length);
        for (int j = 0, i = j; j < str.length() && i < let.length; j++, i++) {
            let[i] = str.substring(j);

            System.out.println(str.substring(j));
        }
        System.out.println(conStr);
        return conStr;
    }


    public static void main(String args[]) {
        RandomAlphaNumeric ran = new RandomAlphaNumeric();
        System.out.println(ran.getSecond());
//		System.out.println(ran.getSM());
//		ran.getRandom();
        System.out.println(alpha.length);
        ran.getSubString(ran.getString());
//		for (int i = 0; i<10; i++) {
////			System.out.println(i + " " +ran.getRandom() + " -- " + ran.getSubString(ran.getString()));
//		}

    }

}
