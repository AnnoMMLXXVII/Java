package com.SelectionSort;

import java.util.Random;

public class NumberSorting {
    private Random r = new Random();
    ;
    private final int[] X = new int[(int) Math.pow(10, 1)];
    private final int[] C = new int[(int) Math.pow(10, 2)];
    private final int[] m = new int[(int) Math.pow(10, 3)];
    private final int[] mx4 = new int[(int) Math.pow(10, 4)];
    private final int[] mx5 = new int[(int) Math.pow(10, 5)];
    private final int[] M = new int[(int) Math.pow(10, 6)];
    private long runTime;

    public NumberSorting() {

        int base10 = 10;
        System.out.println("--- Selection Sort : O(n^2) --- \n");
        for (int i = 0; i < 9; i++) {
            System.out.printf("%d || O(n^2) = O(%d^2) = %d\n", i, base10, (long) Math.pow(base10, 2));
            base10 *= 10;
        }
        System.out.println();
        System.out.println("--- Linear Search : O(n) ---");
        System.out.println();
        base10 = 10;
        for (int i = 0; i < 9; i++) {
            System.out.printf("%d || O(n) = O(%d) = %d\n", i, base10, (long) base10);
            base10 *= 10;
        }
        System.out.println();
        System.out.println("--- Binary Search : O(log(n)) ---");
        System.out.println();
        base10 = 10;
        for (int i = 0; i < 9; i++) {
            System.out.printf("%d || O(log(n)) = O(log(%d)) = %.5f\n", i, base10, (double) (Math.log(base10) / Math.log(2)));
            base10 *= 10;
        }


//		initRun(); 
    }

    private void initRun() {
        callArray(X, 10);
        callArray(C, 100);
        callArray(m, 1000);
        callArray(mx4, 10000);
        callArray(mx5, 100000);
        callArray(M, 1000000);
    }

    protected void initializeArrayValues(int[] arr) {
        int rand = r.nextInt() % 10000 + 1;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[i] = rand;
            rand = r.nextInt() % 10000 + 1; // randomizing!
        } // endae FOR
    } // endae initializeArrayValues

    protected void selectionSort(int[] arr) {
        int n = arr.length;
        long start = System.nanoTime(); // starts timmer;
        for (int i = 0; i < n - 1; i++) { // iterate through array (n-1) times
            int min_idx = i; // Find the min element in unsorted Array
            for (int j = i + 1; j < n; j++) {
                // iterate up the array stopping at the spot before the end
                if (arr[j] < arr[min_idx]) {
                    // Compares to see if arr[j] < than the arr[min_idx]
                    min_idx = j; // assigns j to min_idx
                } // endae IF
            } // endae inner FOR
            // Swapping occurs here
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        } // endae FOR
        setRunTime(System.nanoTime() - start); // sets the difference in time (end-start)
        System.out.printf("%d nanoSeconds\n", getRunTime());
    } // endae sort method


    private void callArray(int[] arr, int inputSize) {
        initializeArrayValues(arr);
        System.out.printf("Inputs: %d || ", inputSize);
        selectionSort(arr);
//		System.out.printf("%s: %10d nanoseconds\n",FINALSTMT,(getRunTime()));
    } // endae callArray method

    public long getRunTime() {
        return runTime;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }

    public static void main(String args[]) {
        new NumberSorting();
    }

}
