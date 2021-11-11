package com.Binary_InsertionSort;

import java.util.Arrays;

public class BinSertTest {
    private int[] arr = new int[]{9, 7, 3, 1, 5, 4, 2, 8};
    private int time;

    public static void main(String[] args) {

        new BinSertTest();
    }

    public BinSertTest() {
        initRun();
    }

    void initRun() {
        printArr(arr);
        int ins = -1;
//		binInsertSort(arr, arr.length);
//		insertSort(arr, arr.length);
        biSertSort02(arr, arr.length);
        System.out.println("\n\n");
        System.out.print("TIME: " + getTime());
//		printArr(arr);
    }

    public void printArr(int[] arr) {
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(" %d | ", arr[i]);
        }
        System.out.println();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    int binSearch(int a[], int low, int high, int key) {
        if (high == low) {
//			System.out.printf("\t\tHIGH==LOW: %2d==%2d\n", high, low);
            return (key > a[low]) ? (low + 1) : low;
        }
        int mid = (low + high) / 2;
//		System.out.println("\t\tMID: "+mid);
        if (key == a[mid]) {
//			System.out.printf("\tKEY == MID\n2d == %2d", key, a[mid]);
            return mid + 1;
        }
        return (key < a[mid]) ?
                binSearch(arr, low, mid - 1, key) :
                binSearch(arr, mid + 1, high, key);
    }

    int binSearch02(int[] a, int key) {
        int start = 0, end = a.length - 1, mid = (start + end) / 2;
        while (start < end) {
            if (key == a[mid]) {
                break;
            } else {
                if (key > a[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
                mid = (start + end) / 2;
            }
        }
        if (a[mid] == key) {
            return mid;
        }
        return -1;
    }


    int biSearch(int a[], int low, int high, int key) {
        int mid = -1;
        if (high == low) {
            return (key > a[low]) ? (low + 1) : low;
        }
        mid = (low + high) / 2;
        if (key == a[mid]) {
            return mid;
        }
        return (key < a[mid]) ? binSearch(arr, low, mid - 1, key) :
                binSearch(arr, mid + 1, high, key);
    }

    void binInsertSort(int a[], int n) {
        int ins, i, j, tmp; // init vairables
//	    long start = System.nanoTime(); // start time!
        int start = (int) System.nanoTime();
        for (i = 1; i < n; ++i) { // O(n)
            j = i - 1;
            tmp = a[i];
//	        System.out.println("\nINDEX: "+i);
            ins = binSearch02(a, a[i]); // (O(log(n))
//	        System.out.printf("Ins|Val = %2d|%2d", ins,a[ins]);

            // return value of bin search to find the place to insert
            while (j >= ins) { // (O(n)
                // inserting current j value into the next value (ascending)
//	        	System.out.printf("j >= ins : %d >= %d :: %s \n", j, ins,(j>=ins));
                a[j + 1] = a[ins]; // swapping
                j--;
            }
            // inserting the stored value @ i, into the returned value of binSearch
            a[j + 1] = tmp; // finished swapping
//	        printArr(arr);
        }
        setTime((int) (System.nanoTime() - start));
//	    setTime((int)(System.nanoTime() - start));
    }

    void biSertSort02(int[] arr, int n) {
        int i, tmp, j, start, innerS = 0, innerE = 0;
        start = (int) System.nanoTime();
        for (i = 1; i < arr.length; i++) {
            tmp = arr[i];
            j = Math.abs(Arrays.binarySearch(arr, 0, i, tmp) + 1);
            System.arraycopy(arr, j, arr, j + 1, i - j);
            arr[j] = tmp;

            printArr(arr);
        }
        setTime((int) (System.nanoTime() - start) + (innerE - innerS));
    }


    void insertSort(int a[], int n) {
        long start = System.nanoTime();
        for (int j = 1; j < n; j++) {
            int key = a[j];
            int i = j - 1;
            while ((i > -1) && (a[i] > key)) {
                a[i + 1] = a[i];
                i--;
            }
            a[i + 1] = key;
        }
        setTime((int) (System.nanoTime() - start));
    }

}
