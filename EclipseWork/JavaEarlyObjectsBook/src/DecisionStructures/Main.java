package DecisionStructures;

import java.util.Random;

public class Main {
    private static Random r = new Random();
    private static int rand = r.nextInt(500); // generates random number from 0 - 500


    public static void main(String args[]) {
        for (int i = 0; i < 5; i++) {
            System.out.println(rand + " --- " + example(rand));
            rand = r.nextInt(500); // generating a new randomNumber
        } // endae FOR
    } // endae MAIN


    // Example method will return false by default if the if statement is FALSE
    // if the 'if' statement is true... then it will change flag, exit the if statement, then return TRUE
    public static boolean example(int n) {
        boolean flag = false; // boolean set to false by default
        if (n % 2 == 0) {
            // if the statement is TRUE
            flag = true;    // changes flag to true
        } // endae IF
        // flag has been changed IF the 'if' statement is true
        return flag; // returns flag
    } // endae example
} // endae CLASS
