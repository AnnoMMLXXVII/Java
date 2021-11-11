package JavaFundamentals;

import java.util.Scanner;

public class MilesPerGallon {
    private static double miles;
    private static double gals;

    public static void main(String args[]) {
        Scanner z = new Scanner(System.in);
        System.out.println("Enter Miles Driven: ");
        miles = z.nextDouble();
        System.out.println("Enter Gallons Used: ");
        gals = z.nextDouble();
        double MPG = miles / gals;
        System.out.println("Miles per Gallon: " + MPG);
        z.close();
    } // endae main
} // endae CLASS
