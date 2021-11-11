import java.util.ArrayList;
import java.util.Scanner;

public class AP5 {

    private Scanner z = new Scanner(System.in);
    private int userNums;
    private int[] numArray = new int[10];


    public AP5() {
        double avg;
        System.out.println("Enter 10 numbers Please: ");
        numArray = initArray();
        printArray(numArray);
        System.out.println("MAX : " + findMax(numArray));
        System.out.println("MIN : " + findMin(numArray));
        avg = findAvg(numArray);
        System.out.println("Average: " + avg);
        elementsAvg(numArray, avg);
    }

    public static void main(String args[]) {
        new AP5();
    }


    private int[] initArray() {
        for (int i = 0; i < numArray.length; i++) {
//			System.out.println(i);
            userNums = z.nextInt();
            numArray[i] = userNums;

        }
        return numArray;
    }

    private void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + " | " + arr[i]);
        } // endae FOR
    } // endae printArray method

    private int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];

            }
        }
        return max;
    }

    private int findMin(int[] arr) {
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];

            }
        }
        return min;
    }

    private double findAvg(int[] arr) {
        double avg = 0;
        int sum = 0;
        double tick = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
            tick++;
        }
        avg = sum / tick++;
        return avg;
    }


    private void elementsAvg(int[] arr, double incomingAvg) {
        ArrayList<Integer> arrGreaterList = new ArrayList<Integer>();
        ArrayList<Integer> arrLesserList = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > incomingAvg) {
                arrGreaterList.add(arr[i]);
            } else {
                arrLesserList.add(arr[i]);
            }
        }
        System.out.println("Values Greater than Avg: ");
        for (int x : arrGreaterList) {
            System.out.print(x + "|");
        }
        System.out.println();
        System.out.println("Values Lesser than Avg: ");
        for (int x : arrLesserList) {
            System.out.print(x + "|");
        }
    }


}
