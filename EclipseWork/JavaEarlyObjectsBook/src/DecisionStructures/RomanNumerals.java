package DecisionStructures;

import java.util.Scanner;

public class RomanNumerals {
    // Program to prompt user to enter ints 1-10 and convert them to Roman
    // numerals
    public static void main(String[] args) {
        System.out.println("Enter a Number 1 through 10\n");
        Scanner z = new Scanner(System.in);
        int integer = z.nextInt();
        if (integer == 1) {
            System.out.print("I");
        } // endae IF
        else if (integer == 2) {
            System.out.println("II");
        } // endae ELSE IF
        else if (integer == 3) {
            System.out.println("III");
        } // endae ELSE IF
        else if (integer == 4) {
            System.out.println("IV");
        } // endae ELSE IF
        else if (integer == 5) {
            System.out.println("V");
        } // endae ELSE IF
        else if (integer == 6) {
            System.out.println("VI");
        } // endae ELSE IF
        else if (integer == 7) {
            System.out.println("VII");
        } // endae ELSE IF
        else if (integer == 8) {
            System.out.println("VIII");
        } // endae ELSE IF
        else if (integer == 9) {
            System.out.println("IX");
        } // endae ELSE IF
        else if (integer == 10) {
            System.out.println("X");
        } // endae ELSE IF
        else {
            System.out.println("ERROR!!");
        }
        z.close();
    }

}
