package LoopsAndFiles;

import java.util.Scanner;

public class LetterCount {

    private static LetterCount lc;
    private static Scanner z;

    public static LetterCount getInstance() {
        if (lc == null) {
            lc = new LetterCount();
        } // endae IF
        return lc;
    } // endae getInstance

    public void mainRun() {
        z = new Scanner(System.in);
        System.out.println("Enter a Sentence!");
        String sentence = z.nextLine();
        System.out.println("Enter a Letter to find!");
        String letter = z.next();
        int count = 0;
        for (int i = 0; i < sentence.length(); i++) {
            if (letter.equals(sentence.substring(i, i + 1))) {
                count++;
            } // endae IF
        } // endae FOR
        System.out.printf("The letter '%s' appeared %02d times", letter, count);
    } // enae mainRun


    public static void main(String args[]) {
        LetterCount.getInstance().mainRun();
        ;
    } // endae main method
} // endae CLASS
