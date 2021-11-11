package JavaFundamentals;

import java.util.Scanner;

public class IngredientAdjuster {
    private static double sugar = 1.5, butter = 1, flour = 2.75;
    private static int cookies = 48;

    public static void main(String[] args) {
        Scanner z = new Scanner(System.in);
        double sugarUnit = sugar / (double) cookies;
        double butterUnit = butter / (double) cookies;
        double flourUnit = flour / (double) cookies;
        System.out.println("How many cookies do you want to make?");
        double userCookies = z.nextDouble();

        sugar = sugarUnit * userCookies;
        butter = butterUnit * userCookies;
        flour = flourUnit * userCookies;

        System.out.printf("Inorder to Make " + userCookies + " Cookies the Ingredients inlude\n"
                        + "%.2f Cups of Sugar\n"
                        + "%.2f Cups of Butter\n"
                        + "%.2f Cups of Flour",
                sugar, butter, flour);
        z.close();

    } // endae main
} // endae CLASS
