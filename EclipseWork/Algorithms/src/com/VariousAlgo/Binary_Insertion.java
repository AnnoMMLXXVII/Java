package com.VariousAlgo;


import java.math.BigDecimal;
import java.util.Random;

public class Binary_Insertion {
    private int[] arr;
    private BigDecimal time;
    private BigDecimal timeI;

    public static void main(String args[]) {
        new Binary_Insertion();
    } // endae MAIN

    Binary_Insertion() {
        initRun();
    } // endae Constructor

    Binary_Insertion(boolean flag, int size) {
        arr = initArr(arr, size, 100);
        if (flag == true) {
            BinSertSort(arr);
        } // endae IF
        else {
            insertSort(arr, arr.length);
        } // endae ELSE
    } // endae Constructor @ 2param

    private void initRun() {
        int n = 6, nn = 5;
        BigDecimal zz = new BigDecimal(nn);
        BigDecimal sum = new BigDecimal(0), sumI = new BigDecimal(0), avg = new BigDecimal(0), avgI = new BigDecimal(0);
        for (int i = 1; i <= n; i++) {
            // loop becomes exponent number for base 10 powers
            for (int j = 0; j < nn; j++) {
                // inner loop will run each test 10 times and sum the times
                Binary_Insertion t1 = new Binary_Insertion(true, (int) Math.pow(10, i));
                sum = sum.add(t1.getTime());
                // adding the times (ns) for each run from j to nn
            }
            avg = sum.divide(zz); // computes average
            System.out.printf("Input(N) : 10^%d Avg  : %1.4e\n", i, avg);

            for (int j = 0; j < nn; j++) {
                Binary_Insertion t2 = new Binary_Insertion(false, (int) Math.pow(10, i));
                sumI = sumI.add(t2.getTimeI());
            }
            avgI = sumI.divide(zz); // computes average
            System.out.printf("Input(N) : 10^%d AvgI : %1.4e\n", i, avgI);
            System.out.println("--------------------------------------------------");
        } // endae FOR
    } // endae initRun Method

    private void BinSertSort(int[] arr) {
        int tmp, low = 0, high, mid, j, i;
        long start = System.nanoTime();
        for (i = 1; i < arr.length; i++) {
            tmp = arr[i];
            low = 0;
            high = i;
            // checking via binary search method
            while (low < high) {
                mid = (high + low) >>> 1;
                // logical right shift operator
                if (tmp >= arr[mid]) {
                    low = mid + 1;
                }
                // if tmp is >= to the mid val low upper half search
                else {
                    high = mid;
                }
                // tmp <= to mid, lower half or mid search
            }
            // Insert/swapping occurs
            j = i;
            while (j > low) {
                arr[j] = arr[j - 1];
                j--;
            } // endae WHILE
            arr[low] = tmp;
        } // endae FOR
        setTime(new BigDecimal(System.nanoTime() - start));
    } // endae BinSertSort method

    private void insertSort(int a[], int n) {
        long start = System.nanoTime();
        for (int j = 1; j < n; j++) {
            int key = a[j];
            int i = j - 1;
            while ((i > -1) && (a[i] > key)) {
                a[i + 1] = a[i];
                i--;
            }  // endae WHILE
            a[i + 1] = key;
        }  // endae FOR
        setTimeI(new BigDecimal(System.nanoTime() - start));
    } // endae insertSort method (original insertSort)

    private int[] initArr(int[] arr, int n, int max) {
        Random r = new Random();
        int rand = r.nextInt(max) + 0;
        // generates random number
        arr = new int[n];
        // new instance of array with init size
        for (int i = 0; i < n; i++) {
            arr[i] = rand;
            // populates with random number
            rand = r.nextInt(max) + 0;
            // generates another new number
        } // endae for
        return arr;
    } // endae initArr method

    private BigDecimal getTime() {
        return time;
    }

    private void setTime(BigDecimal time) {
        this.time = time;
    }

    private BigDecimal getTimeI() {
        return timeI;
    }

    private void setTimeI(BigDecimal timeI) {
        this.timeI = timeI;
    }
}