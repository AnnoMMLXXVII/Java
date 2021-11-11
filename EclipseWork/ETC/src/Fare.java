

import java.util.Scanner;
import java.io.*;

public class Fare {

    public static void main(String args[]) throws Exception {
        File file =
                new File("opal.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine())
            System.out.println(sc.nextLine());


        double fares[][] = {
                {8.84, 10.1, 5.04, 15.15},
                {10.2, 8.8, 5.04, 13.63},
                {7.9, 5, 4.28, 15.8},
                {8.8, 8.58, 4.28, 6.62},
                {6.7, 4.94, 5.04, 7.06},
                {2.47, 0, 3.52, 4.94},
                {0, 0, 0, 0}
        };

        // caclulate the partial sum now.
        double total_sum = calculateCap(fares);

        if (total_sum >= 63.2) {

            System.out.println("weekly fare cap is " + total_sum + " So..The WEEKLY CAPACITY FARE has reached");

        } else {

            System.out.println("Weekly fare cap is " + total_sum + "     So..The WEEKLY CAPACITY FARE has not reached ");
        }


        // get new fares for sunday.
        System.out.println("Please enter new fare data for each user on Sunday starting From user 1 to User 4:\n");
        double input;

        Scanner scan = new Scanner(System.in);
        for (int x = 0; x < 4; x++) {

            System.out.println("Enter fare of type double:");
            input = scan.nextDouble();
            while (input > 2.7) {
                System.out.println("The maximum limit for Sunday is 2.7");
                System.out.println("Please enter the fare again");
                input = scan.nextDouble();
            }
            fares[6][x] = input;

        }
        scan.close();
        System.out.println("Fares of each user for sunday added!");


        // calculate average fare for each user.
        double user_average_fares[] = getUserAverage(fares);
        System.out.println("Average fare for each user is: ");
        for (int x = 0; x < 4; x++) {
            System.out.println(user_average_fares[x]);
        }


        // print out the final data.
        System.out.println("Final data: ");
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 7; y++) {
                System.out.print(fares[y][x] + "\t");
            }
            System.out.println();
        }


    }

    static public double[] getUserAverage(double[][] fares_list) {

        double average_list[] = new double[4];

        double each_user_average = 0.0;
        double interim_sum = 0;
        int count = 0;

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 7; y++) {

                interim_sum += fares_list[y][x];

            }

            each_user_average = interim_sum / 7;
            average_list[count] = each_user_average;
            count++;

        }


        return average_list;
    }


    public static double calculateCap(double[][] fares_list) {

        double daily_sum = 0;
        double daily_average = 0;
        double total_sum = 0;

        for (int x = 0; x < 7; x++) {

            daily_sum = 0;

            for (int y = 0; y < 4; y++) {
                // get partial sum for each day.
                daily_sum += fares_list[x][y];

            }

            daily_average = daily_sum / 4;
            total_sum = total_sum + daily_average;

        }


        return total_sum;
    }


}
