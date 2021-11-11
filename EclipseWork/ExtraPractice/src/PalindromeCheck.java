
public class PalindromeCheck {

    public static void main(String... args) {
        String[] sample = {"racecar", "united", "obo", "alpha", null, "a", "BB", "Aa", " "};
        for (String s : sample) {
            System.out.printf("%s : %s%n", s, isPalindrome(s));
        }
    }

    public static boolean isPalindrome(String str) {
        String name = str;        // Makes copy of original string
        if (name == null || name.length() < 2 || name.equals(" ")) {
            return false;    // returns false if any of the conditions are true
        }
        int i = 0;                        // LOW
        int j = name.length() - 1;        // HIGH
        while (i <= j) {                // Condition
            if (name.charAt(i) != name.charAt(j)) {        // Breaks loop
                return false;
            }
            i++;
            j--;        // incrementor/decrementor
        }
        return true;        // returns true
    }
}
