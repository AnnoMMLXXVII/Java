package com.BinarySearch;

import java.util.Random;

public class BinSearchCS423 {

    public static boolean binarySearch(int arr[], int x) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2; // partitioning list into two halves (middle)
            // Check if x is present at true
            if (arr[m] == x) {  // if the middle is equal to the target (x)
                return true;
            }
            // If x greater, ignore left half
            if (arr[m] < x) { // lower half --> if middle is less than target
                l = m + 1;
            }
            // If x is smaller, ignore right half
            else { // upper half --> if middle is greater than target
                r = m - 1;
            }
        }
        // if we reach here, then element was
        // not present
        return false;
    }

    //generating random numbers between 1 to 100000
    public static int[] CreateArray(int n) {
        int Arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            Arr[i] = i; // element 0 = index i
            // will create an ordered list with the values of the index
            // 0 = 0; 1 = 1; 2 = 2 ...
        }
        return Arr;
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }

    public static void main(String[] args) {
        double T1, T2, T; // declaring variables

        for (int n = 10; n <= Math.pow(10, 8); n = n * 10) { // for loop starts at 10 up to < 100,000,000 (100 mil); incrementing bu\y 10*10
            int[] Arr = CreateArray(n); // declares and initializes new array

            Random R = new Random(); // instantiates new Random number with Random class
            int number = R.nextInt() % 100000 + 1; // Generating random number for target value in binary search

            //binary search time calculation
            T1 = System.nanoTime(); // start time
            binarySearch(Arr, number); // binary search aglorithm
            T2 = System.nanoTime(); // endae time

            T = T2 - T1; // finding the time of the algorithm

            System.out.println(" Input : " + n + " || " + T + " nanosecond");
        }

    } // end of MAIN
} // end CLASS