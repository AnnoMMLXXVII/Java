package com.Other.Sorts;


//Java program for implementation of Bubble Sort

import java.util.Random;

class Homework5 {
    // initialize instance variables
    private Random r = new Random();
    private long bubbleTime = 0;
    private int shellTime;
    private static int[] arr;

    // Main method -- calls empty Constructor
    public static void main(String args[]) {
        new Homework5();
    }

    Homework5() {
        initRun();
    } // default constructor to be called

    // private constructor
    private Homework5(boolean flag, int size) {
        // flag to indicate which sort to run
        arr = initArr(arr, size); // initialize array before beginning sort
        if (flag == true) {
            ShellSort(arr);
        } else {
            bubbleSort(arr);
        }
    }

    // initRun -- main logic to compute sum, average, and print
    void initRun() {
        // Local Variables
        int n = 6, nn = 5;
        double avgS = 0, avgB = 0;
        long sumS = 0, sumB = 0;
        Homework5 hw5 = null;
        // Outside loop called 6 times
        // pair of inner loops called 5 times
        // sum shell time sort or and bubble sort for each inner loop call
        // average times for each sort is (total sum / 5)
        // display results (print statements)
        for (int j = 1; j <= n; j++) {
            System.out.printf("Input(N): %d\n", (int) Math.pow(10, j));
            for (int i = 0; i < nn; i++) {
                hw5 = new Homework5(true, (int) Math.pow(10, j));
                sumS = sumS + hw5.getShellTime();
            } // FOR
            avgS = sumS / nn;
            System.out.printf("Shell Avg : %12.2f\n", avgS);
            // compare with O(n*log(n))
            for (int i = 0; i < nn; i++) {
                hw5 = new Homework5(false, (int) Math.pow(10, j));
                sumB = sumB + hw5.getBubbleTime();
            } // FOR
            avgB = sumB / nn;
            System.out.printf("Bubble Avg: %12.2f\n", avgB);
        } // FOR
    } // initRun

    // Bubble Sort Method
    private void bubbleSort(int arr[]) {
        int n = arr.length;
        long start = System.nanoTime(); // START TIME!
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                } // IF
            } // FOR
        } // FOR
        setBubbleTime((System.nanoTime() - start)); // END TIME!
    } // bubbleSort method

    // initialize array with random values between 0-100 (inclusive)
    private int[] initArr(int arr[], int n) {
        int rand = r.nextInt(100) + 0; // generates random number
        arr = new int[n]; // new instance of array with init size
        for (int i = 0; i < n; i++) {
            arr[i] = rand; // populates with random number
            rand = r.nextInt(100) + 0; // generates another new number
        } // endae for
        return arr; // returns the arr
    } // endae initArr method

    // Shell Sort method
    int ShellSort(int arr[]) {
        int n = arr.length;
        // Start with a big gap, then reduce the gap
        long start = System.nanoTime();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements a[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            for (int i = gap; i < n; i += 1) {
                // add a[i] to the elements that have been gap
                // sorted save a[i] in temp and make a hole at
                // position i
                int temp = arr[i];
                // shift earlier gap-sorted elements up until
                // the correct location for a[i] is found
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                } // FOR
                // put temp (the original a[i]) in its correct
                // location
                arr[j] = temp;
            } // FOR
        } // FOR
        setShellTime((int) (System.nanoTime() - start));
        return 0;
    } // ShellSort method

    //Accessor and Mutator methods
    // Accessor getShellTime
    public int getShellTime() {
        return shellTime;
    }

    // mutator setShellTime
    public void setShellTime(int shellTime) {
        this.shellTime = shellTime;
    }

    // mutator setBubbleTime
    public void setBubbleTime(long bubbleTime) {
        this.bubbleTime = bubbleTime;
    }

    // accessor getBubbleTime
    public long getBubbleTime() {
        return bubbleTime;
    }
} // CLASS

