package LoopsAndFiles;

import java.util.Scanner;

// Program asks user for positive non-zero int val. 
//program should use a loop to get the sum of all intergers from 1 up to the number entered
// ie == user enters 50... 1+2+3+4+5 ... 50
public class SumOfNumbers {
    public static void main(String args[]) {
        Scanner z = new Scanner(System.in);
        System.out.println("Enter a Positive Integer: ");
        int userNum = z.nextInt();
        int total = sumOfNum(userNum);
        System.out.println("\n" + total);
    }

    private static int sumOfNum(int userNum) {
        int sum = 0;
        for (int i = 1; i <= userNum; i++) {
            System.out.printf("%02d+", i);
            if (i == userNum) {
                System.out.printf(" = ");
            }
            sum = sum + userNum;
        }
        return sum;
    }
}
