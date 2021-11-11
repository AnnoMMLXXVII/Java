package JavaFundamentals;

import java.util.Scanner;

public class CookieCookie {
    public static void main(String args[]) {
        int bagCookies = 40;
        int calPerBag = 300;
        double calPerCookie = (double) calPerBag / (double) bagCookies;

        Scanner z = new Scanner(System.in);
        System.out.println("Enter Number of Cookies eaten: ");
        int CookiesEaten = z.nextInt();
        double calories = CookiesEaten * calPerCookie;
        System.out.println("Caloric instake: " + calories);

        z.close();

    } // endae main
} // endea CLASS
