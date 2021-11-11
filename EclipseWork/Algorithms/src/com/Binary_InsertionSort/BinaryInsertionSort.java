package com.Binary_InsertionSort;

import java.util.Random;

public class BinaryInsertionSort {
    private int[] arr;
    private Random r = new Random();

    public static void main(String args[]) {
        new BinaryInsertionSort();
    }

    public BinaryInsertionSort() {
        initRun();
    }

    public void initRun() {
        arr = initArr(arr, 10);
        printArr(arr);
        binInsertSort(arr, arr.length);
        System.out.println("\n\n SORTED \n\n");
        printArr(arr);
    }

    private int[] initArr(int arr[], int n) {
        int rand = r.nextInt(100) + 0; // generates random number
        arr = new int[n]; // new instance of array with init size
        for (int i = 0; i < n; i++) {
            arr[i] = rand; // populates with random number
            rand = r.nextInt(100) + 0; // generates another new number
        } // endae for
        return arr; // returns the arr
    } // endae initArr method

    public void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(" %d | ", arr[i]);

        }
    }

    int binSearch(int a[], int low, int high, int key) {
        if (high <= low) {
            return (key > a[low]) ? (low + 1) : low;
        }
        int mid = (low + high) / 2;
        if (key == a[mid]) {
            return mid + 1;
        }
        if (key > a[mid]) {
            return binSearch(arr, mid + 1, high, key);
        }
        return binSearch(arr, low, mid - 1, key);
    }

    void binInsertSort(int a[], int n) {
        int ins, i, j, tmp; // init vairables
//	    long start = System.nanoTime(); // start time!
        for (i = 1; i < n; ++i) { // O(n)
            j = i - 1;
            tmp = a[i];
            ins = binSearch(a, 0, j, a[i]); // (O(log(n))
            // return value of bin search to find the place to insert

            while (j >= ins) { // (O(n)
                // inserting current j value into the next value (ascending)
                a[j + 1] = a[j]; // swapping
                j--;
            }
            // inserting the stored value @ i, into the returned value of binSearch
            a[j + 1] = tmp; // finished swapping
//	        System.out.printf("\n%d || %d\n", i, ins);
        }
//	    setTime((int)(System.nanoTime() - start));
    }

}
