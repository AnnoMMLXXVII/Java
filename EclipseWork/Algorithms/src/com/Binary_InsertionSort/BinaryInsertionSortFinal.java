package com.Binary_InsertionSort;

import java.util.Arrays;
import java.util.Random;

public class BinaryInsertionSortFinal {

    private int[] arr;
    private Random r = new Random();
    private int time, InsertTime;
    ;

    public static void main(String args[]) {
        new BinaryInsertionSortFinal();
    }

    public BinaryInsertionSortFinal() {
        initRun();
    }

    public BinaryInsertionSortFinal(boolean flag, int size) {
        arr = initArr(arr, size);
        printArr(arr);
        if (flag == true) {
            binaryInsertSort(arr, arr.length);
            printArr(arr);
        }
//		else {
//			insertSort(arr, arr.length);
//		}
    }

    void initRun() {
        int n = 6, nn = 10;
        double avg = 0, avgI = 0;
        long sum = 0, sumI = 0;


        for (int i = 1; i <= 1; i++) {
//			System.out.printf("\n---------------------------------\nInput(N) : %d\n", (int)Math.pow(10, i));
//			for (int j = 0; j <=i; j++) {
            BinaryInsertionSortFinal bisf = new BinaryInsertionSortFinal(true, (int) Math.pow(10, i));
//				sum = sum +bisf.getTime();
//			}
//			avg = sum/nn;
//			System.out.printf("Avg: %16.2f\n", avg);

//			for (int j = 0; j < nn; j++) {
//				BinaryInsertionSortFinal bisf = new BinaryInsertionSortFinal(false, (int)Math.pow(10, i));
//				sumI = sumI +bisf.getInsertTime();
//			}
//			avgI = sumI/nn;
//			System.out.printf("AvgI: %15.2f\n", avgI);
        }
    }

    private int[] initArr(int arr[], int n) {
        int rand = r.nextInt(10) + 0; // generates random number
        arr = new int[n]; // new instance of array with init size
        for (int i = 0; i < n; i++) {
            arr[i] = rand; // populates with random number
            rand = r.nextInt(10) + 0; // generates another new number
        } // endae for
        return arr; // returns the arr
    } // endae initArr method

    void binaryInsertSort(int[] arr, int n) {
        int i, tmp, j, start, innerS = 0, innerE = 0;
        start = (int) System.nanoTime();
        for (i = 1; i < arr.length; i++) { // O(n)
            tmp = arr[i];
//					j = Math.abs(
            j = binSearch(arr, 0, i, 11);
//							+ 1); // O(lg(n))
            if (j == -1) {
                System.out.println("DNE: " + tmp);
            } else {
                printArr(arr);
                System.out.println("KEY = " + tmp + " || " + "RETURN: " + j);
            }
//			System.arraycopy(arr,  j, arr, j+1, i-j); // Copying all values after the inserted value O(n)
            arr[j] = tmp;
        }
        setTime((int) (System.nanoTime() - start) + (innerE - innerS));
    }

    public void printArr(int[] arr) {
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(" %d | ", arr[i]);
        }
        System.out.println();
    }

    int binSearch(int a[], int low, int high, int key) {
        if (high == low) {
//			System.out.printf("\t\tHIGH==LOW: %2d==%2d\n", high, low);
            return (key != a[low]) ? (low + 1) : low;
            // if (key > a[low]) { low+1; else low;
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
        setInsertTime((int) (System.nanoTime() - start));
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getInsertTime() {
        return InsertTime;
    }

    public void setInsertTime(int insertTime) {
        InsertTime = insertTime;
    }
}
