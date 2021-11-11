package JavaFundamentals;

import java.util.Scanner;

public class MaleFemalePercentage {
    static int malePop, femalePop;

    public static void main(String args[]) {
        Scanner z = new Scanner(System.in);
        System.out.println("Enter Population of Males: ");
        malePop = z.nextInt();
        System.out.println("Enter Population of Females: ");
        femalePop = z.nextInt();

        int totalPop = malePop + femalePop;
        double malePerct = (double) malePop / (double) totalPop;
        double femalePerct = (double) femalePop / (double) totalPop;

        System.out.printf("Male Percentage: %.2f\nFemale Percentage: %.2f\nTotal Population: %d", malePerct,
                femalePerct, totalPop);
        z.close();
    } // endae main
} // endae CLASS
