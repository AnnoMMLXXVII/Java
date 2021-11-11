package LoopsAndFiles;

import java.math.BigInteger;
import java.util.Scanner;

public class PenniesForPay {
    private static PenniesForPay pfp;
    private int day;
    private int pennie = 1;
    private boolean isValid;

    public static PenniesForPay getInstance() {
        if (pfp == null) {
            pfp = new PenniesForPay();
        }
        return pfp;
    }

    public void mainRun() {
        Scanner z = new Scanner(System.in);
        prompt(z);
        if (getIsValid() == false) {
            mainRun();
        } else {
            displayTable();
            z.close();
        }
    }

    public void prompt(Scanner z) {
        System.out.println("Enter the number of days!");
        day = z.nextInt();
        isValid = validation(day);
        setIsValid(isValid);
    }

    private void setDay(int day) {
        this.day = day;
    } // endae setDay

    public int getDay() {
        return day;
    } // endae getDay

    private void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean getIsValid() {
        return isValid;
    }

    private boolean validation(int input) {
        boolean flag = false;
        if (input < 1) {
            System.out.println("Input cannot be less than one");
        } else {
            setDay(input);
            flag = true;
        }
        return flag;
    }

    private void displayTable() {
        long total = 1;
        System.out.printf("Day: %02d || %2d Cents\n", 1, total);
        for (int i = 2; i <= getDay(); i++) {
//			double cent = (double)(total/100);
            total = doublePennies(total);
            System.out.printf("Day: %02d || %2d Cents\n", i, total);
        }
        System.out.printf("$%.2f", ((float) total / 100));
    }

    private long doublePennies(long pen) {
        long sum = pen;
        sum = pen * 2;
        return sum;
    }

    public static void main(String args[]) {
        PenniesForPay.getInstance().mainRun();
    }

}
