package JavaFundamentals;

import java.util.Scanner;

public class StringManipulator {
    private static String cityName;

    public static void main(String args[]) {
        Scanner z = new Scanner(System.in);
        System.out.println("Enter the name of your favorite city:");
        cityName = z.nextLine();
        int length = cityName.length();
        String upper = cityName.toUpperCase();
        String lower = cityName.toLowerCase();
        char firstInitial = cityName.charAt(0);

        System.out.printf("City Length: %d\n"
                        + "UPPERCASE: %s\n"
                        + "lowercase: %s\n"
                        + "First Letter: %s",
                length, upper, lower, firstInitial);
        z.close();
    } // endae main
} // endae CLASS
